package com.ticket.order.consumer;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ticket.film.entity.Seat;
import com.ticket.film.entity.Showtime;
import com.ticket.film.mapper.SeatMapper;
import com.ticket.film.mapper.ShowtimeMapper;
import com.ticket.order.dto.OrderMessage;
import com.ticket.order.entity.Order;
import com.ticket.order.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class OrderConsumer {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private ShowtimeMapper showtimeMapper;

    @Resource
    private SeatMapper seatMapper;

    @Resource
    private RedissonClient redissonClient;

    @RabbitListener(queues = "order.create.queue")
    public void handleOrderCreation(OrderMessage msg) {
        log.debug("接收到下单消息: {}", msg);

        // 1.校验订单状态，防止重复处理
        Order order = orderMapper.selectById(msg.getOrderId());
        if (order == null || order.getStatus() != -1) {
            log.warn("订单不存在或已处理: orderId={}, status={}", msg.getOrderId(),
                    order == null ? null : order.getStatus());
            return;
        }

        // 2.校验场次
        Showtime showtime = showtimeMapper.selectById(msg.getShowId());
        if (showtime == null) {
            failOrder(msg.getOrderId(), "场次不存在");
            return;
        }
        if (showtime.getRemainingSeats() < msg.getSeatIds().size()) {
            failOrder(msg.getOrderId(), "余座不足");
            return;
        }

        // 3.分布式锁，防止并发抢座
        RLock lock = redissonClient.getLock("lock:seat:" + msg.getShowId());
        try {
            if (!lock.tryLock()) {
                failOrder(msg.getOrderId(), "系统繁忙，请重试");
                return;
            }

            // 4.校验座位是否可售
            List<Seat> seats = seatMapper.selectBatchIds(msg.getSeatIds());
            for (Seat seat : seats) {
                if (seat.getStatus() != 0) {
                    failOrder(msg.getOrderId(), "座位 " + seat.getSeatName() + " 已被锁定或售出");
                    return;
                }
            }

            // 5.执行下单事务
            doCreateOrder(msg, showtime, seats);
        } catch (Exception e) {
            log.error("下单处理异常", e);
            throw new RuntimeException("下单处理失败", e);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    @Transactional
    public void doCreateOrder(OrderMessage msg, Showtime showtime, List<Seat> seats) {
        // 锁定座位
        List<Long> seatIdList = seats.stream().map(Seat::getId).collect(Collectors.toList());
        seatMapper.update(
                new Seat().setStatus(1),
                new LambdaUpdateWrapper<Seat>()
                        .in(Seat::getId, seatIdList)
                        .eq(Seat::getStatus, 0)
        );

        // 扣减余座
        showtimeMapper.update(
                new Showtime(),
                new LambdaUpdateWrapper<Showtime>()
                        .eq(Showtime::getId, showtime.getId())
                        .setSql("remaining_seats = remaining_seats - " + seats.size())
        );

        // 更新订单状态为待支付并完善信息
        Order order = new Order();
        order.setId(msg.getOrderId());
        order.setShowId(showtime.getId());
        order.setSeatIds(seatIdList.stream().map(String::valueOf).collect(Collectors.joining(",")));
        order.setSeatNames(seats.stream().map(Seat::getSeatName).collect(Collectors.joining(",")));
        order.setTotalPrice(showtime.getPrice().multiply(BigDecimal.valueOf(seats.size())));
        order.setStatus(0);
        orderMapper.updateById(order);
    }

    private void failOrder(Long orderId, String reason) {
        orderMapper.update(null, new LambdaUpdateWrapper<Order>()
                .eq(Order::getId, orderId)
                .eq(Order::getStatus, -1)
                .set(Order::getStatus, 3));
        log.warn("下单失败: orderId={}, reason={}", orderId, reason);
    }
}
