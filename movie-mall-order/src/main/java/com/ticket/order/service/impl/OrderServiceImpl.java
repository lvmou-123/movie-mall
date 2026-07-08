package com.ticket.order.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ticket.common.dto.Result;
import com.ticket.order.dto.OrderMessage;
import com.ticket.order.entity.Order;
import com.ticket.film.entity.Seat;
import com.ticket.film.entity.Showtime;
import com.ticket.order.mapper.OrderMapper;
import com.ticket.film.mapper.SeatMapper;
import com.ticket.film.mapper.ShowtimeMapper;
import com.ticket.order.service.IOrderService;
import com.ticket.common.utils.RedisIdWorker;
import com.ticket.common.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.ticket.order.config.RabbitMQConfig.ORDER_CREATE_ROUTING_KEY;
import static com.ticket.order.config.RabbitMQConfig.ORDER_EXCHANGE;

@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Resource
    private ShowtimeMapper showtimeMapper;

    @Resource
    private SeatMapper seatMapper;

    @Resource
    private RedisIdWorker redisIdWorker;

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public Result createOrder(Long showId, List<Long> seatIds) {
        if (showId == null || seatIds == null || seatIds.isEmpty()) {
            return Result.fail("参数错误");
        }

        Long userId = UserHolder.getUser().getId();

        // 1.创建订单，初始状态为处理中
        long orderId = redisIdWorker.nextId("order");
        Order order = new Order();
        order.setId(orderId);
        order.setOrderNo("ORD" + orderId);
        order.setUserId(userId);
        order.setShowId(showId);
        order.setSeatIds(seatIds.stream().map(String::valueOf).collect(Collectors.joining(",")));
        order.setStatus(-1);
        save(order);

        // 2.发送异步下单消息
        OrderMessage msg = new OrderMessage(orderId, showId, seatIds, userId);
        rabbitTemplate.convertAndSend(ORDER_EXCHANGE, ORDER_CREATE_ROUTING_KEY, msg);

        // 3.返回
        return Result.ok(orderId);
    }

    @Override
    @Transactional
    public Result payOrder(Long orderId) {
        Order order = getById(orderId);
        if (order == null) {
            return Result.fail("订单不存在");
        }
        if (order.getStatus() != 0) {
            return Result.fail("订单状态异常");
        }
        order.setStatus(1);
        order.setPayTime(LocalDateTime.now());
        updateById(order);
        return Result.ok();
    }

    @Override
    @Transactional
    public Result cancelOrder(Long orderId) {
        Order order = getById(orderId);
        if (order == null) {
            return Result.fail("订单不存在");
        }
        if (order.getStatus() != 0) {
            return Result.fail("订单已支付或处理中，不能取消");
        }

        // 释放座位
        order.setStatus(2);
        updateById(order);

        String[] seatIdArr = order.getSeatIds().split(",");
        for (String sid : seatIdArr) {
            seatMapper.update(
                    new Seat().setStatus(0),
                    new com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper<Seat>()
                            .eq(Seat::getId, Long.parseLong(sid))
                            .eq(Seat::getStatus, 1)
            );
        }
        // 恢复余座
        showtimeMapper.update(
                new Showtime(),
                new com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper<Showtime>()
                        .eq(com.ticket.film.entity.Showtime::getId, order.getShowId())
                        .setSql("remaining_seats = remaining_seats + " + seatIdArr.length)
        );
        return Result.ok();
    }

    @Override
    public Result queryUserOrders(Integer current) {
        Long userId = UserHolder.getUser().getId();
        Page<Order> page = lambdaQuery()
                .eq(Order::getUserId, userId)
                .orderByDesc(Order::getCreateTime)
                .page(new Page<>(current, 10));
        return Result.ok(page.getRecords());
    }
}
