package com.management.staff.demo.controller;

import com.management.staff.demo.common.entity.ResultWrapper;
import com.management.staff.demo.entity.Dept;
import com.management.staff.demo.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Dept)表控制层
 *
 * @author makejava
 * @since 2023-07-19 20:01:14
 */
@Api(value = "部门接口", tags = "部门接口")
@RestController
@RequestMapping("dept")
public class DeptController {
    /**
     * 服务对象
     */
    @Resource
    private DeptService deptService;

    // 获取部门列表
    @ApiOperation("获取部门列表")
    @GetMapping("getDeptSelect")
    public ResultWrapper getDeptSelect() {
        return ResultWrapper.success(this.deptService.queryAll());
    }

}