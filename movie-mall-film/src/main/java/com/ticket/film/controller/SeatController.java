package com.ticket.film.controller;

import com.ticket.common.dto.Result;
import com.ticket.film.service.ISeatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/seat")
@Api(tags = "座位相关接口")
public class SeatController {

    @Resource
    private ISeatService seatService;

    @GetMapping("/list/{showId}")
    @ApiOperation("根据场次ID查询座位列表")
    @ApiImplicitParam(name = "showId", value = "场次ID", required = true, paramType = "path", dataTypeClass = Long.class, example = "1")
    public Result querySeatsByShowId(@PathVariable("showId") Long showId) {
        return seatService.querySeatsByShowId(showId);
    }
}
