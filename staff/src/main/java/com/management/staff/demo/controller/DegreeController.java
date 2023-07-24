package com.management.staff.demo.controller;

import com.management.staff.demo.common.entity.ResultWrapper;
import com.management.staff.demo.entity.Degree;
import com.management.staff.demo.service.DegreeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Degree)表控制层
 *
 * @author makejava
 * @since 2023-07-19 20:01:11
 */
@RestController
@Api(tags = "学历信息")
@RequestMapping("degree")
public class DegreeController {
    /**
     * 服务对象
     */
    @Resource
    private DegreeService degreeService;

    // 获取学历列表
    @ApiOperation(value = "获取学历列表")
    @GetMapping("getDegreeSelect")
    public ResultWrapper getDeptSelect() {
        return ResultWrapper.success(this.degreeService.queryAll());
    }
}