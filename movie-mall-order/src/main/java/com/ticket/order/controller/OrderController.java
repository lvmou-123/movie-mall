package com.ticket.order.controller;

import com.ticket.common.dto.Result;
import com.ticket.order.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")
@Api(tags = "订单相关接口")
public class OrderController {

    @Resource
    private IOrderService orderService;

    @PostMapping("/create")
    @ApiOperation("创建订单")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "showId", value = "场次ID", required = true, paramType = "query", dataTypeClass = Long.class, example = "1"),
        @ApiImplicitParam(name = "seatIds", value = "座位ID列表", required = true, paramType = "query", dataTypeClass = Long.class, example = "1,2,3", allowMultiple = true)
    })
    public Result createOrder(@RequestParam("showId") Long showId,
                              @RequestParam("seatIds") List<Long> seatIds) {
        return orderService.createOrder(showId, seatIds);
    }

    @PutMapping("/pay/{id}")
    @ApiOperation("支付订单")
    @ApiImplicitParam(name = "id", value = "订单ID", required = true, paramType = "path", dataTypeClass = Long.class, example = "1")
    public Result payOrder(@PathVariable("id") Long orderId) {
        return orderService.payOrder(orderId);
    }

    @PutMapping("/cancel/{id}")
    @ApiOperation("取消订单")
    @ApiImplicitParam(name = "id", value = "订单ID", required = true, paramType = "path", dataTypeClass = Long.class, example = "1")
    public Result cancelOrder(@PathVariable("id") Long orderId) {
        return orderService.cancelOrder(orderId);
    }

    @GetMapping("/list")
    @ApiOperation("查询用户订单列表")
    @ApiImplicitParam(name = "current", value = "当前页码", paramType = "query", dataTypeClass = Integer.class, example = "1")
    public Result queryUserOrders(@RequestParam(value = "current", defaultValue = "1") Integer current) {
        return orderService.queryUserOrders(current);
    }
}
