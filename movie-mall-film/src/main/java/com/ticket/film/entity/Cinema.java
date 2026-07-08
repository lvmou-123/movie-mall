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
@TableName("tb_cinema")
@ApiModel(description = "影院信息")
public class Cinema implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "影院ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "影院名称", example = "万达影城（CBD店）")
    private String name;

    @ApiModelProperty(value = "影院地址", example = "北京市朝阳区建国路87号")
    private String address;

    @ApiModelProperty(value = "联系电话", example = "010-88886666")
    private String phone;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
