package com.ticket.film.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ticket.common.dto.Result;
import com.ticket.film.entity.Film;
import com.ticket.film.mapper.FilmMapper;
import com.ticket.film.service.IFilmService;
import com.ticket.common.utils.CacheClient;
import com.ticket.common.utils.SystemConstants;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static com.ticket.common.utils.RedisConstants.CACHE_FILM_KEY;
import static com.ticket.common.utils.RedisConstants.CACHE_FILM_TTL;

@Service
public class FilmServiceImpl extends ServiceImpl<FilmMapper, Film> implements IFilmService {

    @Resource
    private CacheClient cacheClient;

    @Override
    public Result queryById(Long id) {
        Film film = cacheClient
                .queryWithPassThrough(CACHE_FILM_KEY, id, Film.class, this::getById, CACHE_FILM_TTL, TimeUnit.MINUTES);
        if (film == null) {
            return Result.fail("影片不存在！");
        }
        return Result.ok(film);
    }

    @Override
    @Transactional
    public Result update(Film film) {
        Long id = film.getId();
        if (id == null) {
            return Result.fail("影片id不能为空");
        }
        updateById(film);
        return Result.ok();
    }

    @Override
    public Result queryFilmsByStatus(Integer status, Integer current) {
        Page<Film> page = query()
                .eq(status != null && status >= 0, "status", status)
                .orderByDesc("release_date")
                .page(new Page<>(current, SystemConstants.DEFAULT_PAGE_SIZE));
        return Result.ok(page.getRecords());
    }

    @Override
    public Result searchFilms(String keyword, Integer current) {
        Page<Film> page = query()
                .like(StrUtil.isNotBlank(keyword), "name", keyword)
                .orderByDesc("release_date")
                .page(new Page<>(current, SystemConstants.DEFAULT_PAGE_SIZE));
        return Result.ok(page.getRecords());
    }
}
