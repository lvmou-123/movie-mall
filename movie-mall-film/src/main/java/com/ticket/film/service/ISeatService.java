package com.ticket.film.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ticket.common.dto.Result;
import com.ticket.film.entity.Seat;

public interface ISeatService extends IService<Seat> {
    Result querySeatsByShowId(Long showId);
}
