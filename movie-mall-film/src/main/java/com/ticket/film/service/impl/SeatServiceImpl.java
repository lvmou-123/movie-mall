package com.ticket.film.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ticket.common.dto.Result;
import com.ticket.film.entity.Seat;
import com.ticket.film.mapper.SeatMapper;
import com.ticket.film.service.ISeatService;
import org.springframework.stereotype.Service;

@Service
public class SeatServiceImpl extends ServiceImpl<SeatMapper, Seat> implements ISeatService {

    @Override
    public Result querySeatsByShowId(Long showId) {
        LambdaQueryWrapper<Seat> wrapper = new LambdaQueryWrapper<Seat>()
                .eq(Seat::getShowId, showId)
                .orderByAsc(Seat::getRowNum, Seat::getColNum);
        return Result.ok(list(wrapper));
    }
}
