package com.ticket.film.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ticket.common.dto.Result;
import com.ticket.film.entity.Film;

public interface IFilmService extends IService<Film> {
    Result queryById(Long id);

    Result update(Film film);

    Result queryFilmsByStatus(Integer status, Integer current);

    Result searchFilms(String keyword, Integer current);
}
