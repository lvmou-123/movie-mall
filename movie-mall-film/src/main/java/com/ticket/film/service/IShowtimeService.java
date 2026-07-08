package com.ticket.film.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ticket.common.dto.Result;
import com.ticket.film.entity.Showtime;

public interface IShowtimeService extends IService<Showtime> {
    Result queryShowtimesByFilmAndCinema(Long filmId, Long cinemaId);

    Result queryShowtimeById(Long id);
}
