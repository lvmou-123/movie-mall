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
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_show")
@ApiModel(description = "电影场次信息")
public class Showtime implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "场次ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "电影ID", example = "1")
    private Long filmId;

    @ApiModelProperty(value = "影院ID", example = "1")
    private Long cinemaId;

    @ApiModelProperty(value = "影厅名称", example = "IMAX厅")
    private String hallName;

    @ApiModelProperty(value = "影厅类型", example = "IMAX")
    private String hallType;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "票价", example = "59.90")
    private BigDecimal price;

    @ApiModelProperty(value = "总座位数", example = "200")
    private Integer totalSeats;

    @ApiModelProperty(value = "剩余座位数", example = "150")
    private Integer remainingSeats;

    @ApiModelProperty(value = "状态：0-未开始 1-放映中 2-已结束", example = "0")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
