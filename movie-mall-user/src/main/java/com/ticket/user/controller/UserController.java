package com.ticket.user.controller;


import cn.hutool.core.bean.BeanUtil;
import com.ticket.common.dto.LoginFormDTO;
import com.ticket.common.dto.Result;
import com.ticket.common.dto.UserDTO;
import com.ticket.user.entity.User;
import com.ticket.user.service.IUserService;
import com.ticket.common.utils.UserHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口")
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping("code")
    @ApiOperation("发送手机验证码")
    @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query", dataTypeClass = String.class, example = "13800138000")
    public Result sendCode(@RequestParam("phone") String phone, HttpSession session) {
        return userService.sendCode(phone, session);
    }

    @PostMapping("/login")
    @ApiOperation("用户登录（支持验证码/密码登录）")
    public Result login(@RequestBody LoginFormDTO loginForm, HttpSession session){
        return userService.login(loginForm, session);
    }

    @PostMapping("/logout")
    @ApiOperation("用户登出")
    public Result logout(){
        return Result.fail("功能未完成");
    }

    @GetMapping("/me")
    @ApiOperation("获取当前登录用户信息")
    public Result me(){
        UserDTO user = UserHolder.getUser();
        return Result.ok(user);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据ID查询用户信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataTypeClass = Long.class, example = "1")
    public Result queryUserById(@PathVariable("id") Long userId){
        User user = userService.getById(userId);
        if (user == null) {
            return Result.ok();
        }
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        return Result.ok(userDTO);
    }

    @PostMapping("/sign")
    @ApiOperation("用户签到")
    public Result sign(){
        return userService.sign();
    }

    @GetMapping("/sign/count")
    @ApiOperation("获取签到次数")
    public Result signCount(){
        return userService.signCount();
    }
}