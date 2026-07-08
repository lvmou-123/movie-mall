package com.ticket.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "滚动查询结果")
public class ScrollResult {
    @ApiModelProperty(value = "数据列表")
    private List<?> list;
    @ApiModelProperty(value = "最小时间戳", example = "1700000000000")
    private Long minTime;
    @ApiModelProperty(value = "偏移量", example = "0")
    private Integer offset;
}
