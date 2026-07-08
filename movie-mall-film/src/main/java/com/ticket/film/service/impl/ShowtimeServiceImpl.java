package com.ticket.film.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ticket.common.dto.Result;
import com.ticket.film.entity.Showtime;
import com.ticket.film.mapper.ShowtimeMapper;
import com.ticket.film.service.IShowtimeService;
import org.springframework.stereotype.Service;

@Service
public class ShowtimeServiceImpl extends ServiceImpl<ShowtimeMapper, Showtime> implements IShowtimeService {

    @Override
    public Result queryShowtimesByFilmAndCinema(Long filmId, Long cinemaId) {
        LambdaQueryWrapper<Showtime> wrapper = new LambdaQueryWrapper<Showtime>()
                .eq(filmId != null, Showtime::getFilmId, filmId)
                .eq(cinemaId != null, Showtime::getCinemaId, cinemaId)
                .orderByAsc(Showtime::getStartTime);
        return Result.ok(list(wrapper));
    }

    @Override
    public Result queryShowtimeById(Long id) {
        Showtime showtime = getById(id);
        if (showtime == null) {
            return Result.fail("场次不存在！");
        }
        return Result.ok(showtime);
    }
}
