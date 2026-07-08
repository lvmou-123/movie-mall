package com.ticket.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "用户信息（脱敏）")
public class UserDTO {
    @ApiModelProperty(value = "用户ID", example = "1")
    private Long id;
    @ApiModelProperty(value = "昵称", example = "小明")
    private String nickName;
    @ApiModelProperty(value = "头像URL")
    private String avatar;
}
