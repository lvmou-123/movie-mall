package com.ticket.film.controller;

import com.ticket.common.dto.Result;
import com.ticket.film.service.ICinemaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/cinema")
@Api(tags = "影院相关接口")
public class CinemaController {

    @Resource
    private ICinemaService cinemaService;

    @GetMapping("/by-film/{filmId}")
    @ApiOperation("根据电影ID查询上映影院")
    @ApiImplicitParam(name = "filmId", value = "电影ID", required = true, paramType = "path", dataTypeClass = Long.class, example = "1")
    public Result queryCinemasByFilmId(@PathVariable("filmId") Long filmId) {
        return cinemaService.queryCinemasByFilmId(filmId);
    }
}
