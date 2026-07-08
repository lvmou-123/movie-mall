package com.ticket.film.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_seat")
@ApiModel(description = "座位信息")
public class Seat implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "座位ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "场次ID", example = "1")
    private Long showId;

    @ApiModelProperty(value = "行号", example = "5")
    private Integer rowNum;

    @ApiModelProperty(value = "列号", example = "8")
    private Integer colNum;

    @ApiModelProperty(value = "座位名称", example = "5排8座")
    private String seatName;

    @ApiModelProperty(value = "状态：0-可选 1-已售 2-锁定", example = "0")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
