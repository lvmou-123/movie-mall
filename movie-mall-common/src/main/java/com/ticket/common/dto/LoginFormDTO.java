package com.ticket.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "登录表单")
public class LoginFormDTO {
    @ApiModelProperty(value = "手机号", required = true, example = "13800138000")
    private String phone;
    @ApiModelProperty(value = "验证码（验证码登录时使用）", example = "123456")
    private String code;
    @ApiModelProperty(value = "密码（密码登录时使用）", example = "123456")
    private String password;
}
