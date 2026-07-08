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
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_film")
@ApiModel(description = "电影信息")
public class Film implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "电影ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "电影名称", example = "流浪地球3")
    private String name;

    @ApiModelProperty(value = "电影描述")
    private String description;

    @ApiModelProperty(value = "导演", example = "郭帆")
    private String director;

    @ApiModelProperty(value = "演员", example = "吴京、刘德华")
    private String actors;

    @ApiModelProperty(value = "时长（分钟）", example = "128")
    private Integer duration;

    @ApiModelProperty(value = "上映日期", example = "2026-01-01")
    private LocalDate releaseDate;

    @ApiModelProperty(value = "状态：0-待上映 1-热映中 2-已下架", example = "1")
    private Integer status;

    @ApiModelProperty(value = "海报URL")
    private String poster;

    @ApiModelProperty(value = "评分", example = "8.5")
    private BigDecimal rating;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
