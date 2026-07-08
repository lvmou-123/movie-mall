package com.ticket.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_order")
@ApiModel(description = "订单信息")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "订单ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "订单编号", example = "ORD20260707123456")
    private String orderNo;

    @ApiModelProperty(value = "用户ID", example = "1")
    private Long userId;

    @ApiModelProperty(value = "场次ID", example = "1")
    private Long showId;

    @ApiModelProperty(value = "座位ID列表（逗号分隔）", example = "1,2,3")
    private String seatIds;

    @ApiModelProperty(value = "座位名称列表（逗号分隔）", example = "5排8座,5排9座")
    private String seatNames;

    @ApiModelProperty(value = "总价", example = "119.80")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "状态：0-待支付 1-已支付 2-已取消 3-已退款", example = "0")
    private Integer status;

    @ApiModelProperty(value = "支付时间")
    private LocalDateTime payTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
