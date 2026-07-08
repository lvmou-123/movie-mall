package com.ticket.film.controller;

import com.ticket.common.dto.Result;
import com.ticket.film.service.IShowtimeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/show")
@Api(tags = "电影场次相关接口")
public class ShowtimeController {

    @Resource
    private IShowtimeService showtimeService;

    @GetMapping("/{id}")
    @ApiOperation("根据ID查询场次详情")
    @ApiImplicitParam(name = "id", value = "场次ID", required = true, paramType = "path", dataTypeClass = Long.class, example = "1")
    public Result queryShowtimeById(@PathVariable("id") Long id) {
        return showtimeService.queryShowtimeById(id);
    }

    @GetMapping("/list")
    @ApiOperation("查询场次列表（可按电影和影院筛选）")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "filmId", value = "电影ID", paramType = "query", dataTypeClass = Long.class, example = "1"),
        @ApiImplicitParam(name = "cinemaId", value = "影院ID", paramType = "query", dataTypeClass = Long.class, example = "1")
    })
    public Result queryShowtimes(@RequestParam(value = "filmId", required = false) Long filmId,
                                 @RequestParam(value = "cinemaId", required = false) Long cinemaId) {
        return showtimeService.queryShowtimesByFilmAndCinema(filmId, cinemaId);
    }
}
