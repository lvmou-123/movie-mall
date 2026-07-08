package com.ticket.film.controller;

import com.ticket.common.dto.Result;
import com.ticket.film.entity.Film;
import com.ticket.film.service.IFilmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/film")
@Api(tags = "电影相关接口")
public class FilmController {

    @Resource
    private IFilmService filmService;

    @GetMapping("/{id}")
    @ApiOperation("根据ID查询电影详情")
    @ApiImplicitParam(name = "id", value = "电影ID", required = true, paramType = "path", dataTypeClass = Long.class, example = "1")
    public Result queryFilmById(@PathVariable("id") Long id) {
        return filmService.queryById(id);
    }

    @GetMapping("/list")
    @ApiOperation("查询电影列表（可按状态筛选）")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "status", value = "电影状态：0-待上映 1-热映中 2-已下架", paramType = "query", dataTypeClass = Integer.class, example = "1"),
        @ApiImplicitParam(name = "current", value = "当前页码", paramType = "query", dataTypeClass = Integer.class, example = "1")
    })
    public Result queryFilmList(@RequestParam(value = "status", required = false) Integer status,
                                @RequestParam(value = "current", defaultValue = "1") Integer current) {
        return filmService.queryFilmsByStatus(status, current);
    }

    @GetMapping("/search")
    @ApiOperation("搜索电影")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "keyword", value = "搜索关键词", required = true, paramType = "query", dataTypeClass = String.class, example = "流浪地球"),
        @ApiImplicitParam(name = "current", value = "当前页码", paramType = "query", dataTypeClass = Integer.class, example = "1")
    })
    public Result searchFilms(@RequestParam("keyword") String keyword,
                              @RequestParam(value = "current", defaultValue = "1") Integer current) {
        return filmService.searchFilms(keyword, current);
    }
}
