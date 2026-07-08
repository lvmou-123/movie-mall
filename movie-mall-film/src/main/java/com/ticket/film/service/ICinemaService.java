package com.ticket.film.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ticket.common.dto.Result;
import com.ticket.film.entity.Cinema;

public interface ICinemaService extends IService<Cinema> {
    Result queryCinemasByFilmId(Long filmId);
}
