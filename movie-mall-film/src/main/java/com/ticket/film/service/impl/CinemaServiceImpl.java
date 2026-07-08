package com.ticket.film.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ticket.common.dto.Result;
import com.ticket.film.entity.Cinema;
import com.ticket.film.entity.Showtime;
import com.ticket.film.mapper.CinemaMapper;
import com.ticket.film.mapper.ShowtimeMapper;
import com.ticket.film.service.ICinemaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CinemaServiceImpl extends ServiceImpl<CinemaMapper, Cinema> implements ICinemaService {

    @Resource
    private ShowtimeMapper showtimeMapper;

    @Override
    public Result queryCinemasByFilmId(Long filmId) {
        List<Showtime> showtimes = showtimeMapper.selectList(
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<Showtime>()
                        .eq(Showtime::getFilmId, filmId)
                        .select(Showtime::getCinemaId)
                        .groupBy(Showtime::getCinemaId)
        );
        if (showtimes.isEmpty()) {
            return Result.ok(java.util.Collections.emptyList());
        }
        List<Long> cinemaIds = showtimes.stream()
                .map(Showtime::getCinemaId)
                .collect(Collectors.toList());
        List<Cinema> cinemas = listByIds(cinemaIds);
        return Result.ok(cinemas);
    }
}
