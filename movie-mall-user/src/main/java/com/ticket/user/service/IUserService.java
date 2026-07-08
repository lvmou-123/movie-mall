package com.ticket.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ticket.common.dto.LoginFormDTO;
import com.ticket.common.dto.Result;
import com.ticket.user.entity.User;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 吕家豪
 * @since 2026-07-07
 */
public interface IUserService extends IService<User> {

    Result sendCode(String phone, HttpSession session);

    Result login(LoginFormDTO loginForm, HttpSession session);

    Result sign();

    Result signCount();

}
