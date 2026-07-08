package com.ticket.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ticket.common.dto.Result;
import com.ticket.order.entity.Order;

import java.util.List;

public interface IOrderService extends IService<Order> {
    Result createOrder(Long showId, List<Long> seatIds);

    Result payOrder(Long orderId);

    Result cancelOrder(Long orderId);

    Result queryUserOrders(Integer current);
}
