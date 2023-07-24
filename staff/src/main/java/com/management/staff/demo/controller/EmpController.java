package com.management.staff.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.DateUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.management.staff.demo.common.entity.ResultWrapper;
import com.management.staff.demo.entity.Emp;
import com.management.staff.demo.service.EmpService;
import com.management.staff.demo.vo.CollectDataExcelVo;
import com.management.staff.demo.vo.EmpQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (Emp)表控制层
 *
 * @author makejava
 * @since 2023-07-19 20:01:14
 */
@RestController
@RequestMapping("emp")
@Api(value = "职工接口", tags = {"职工接口"})
public class EmpController {
    /**
     * 服务对象
     */
    @Resource
    private EmpService empService;


    @ApiOperation("查询职工")
    @PostMapping("queryAll")
    public ResultWrapper queryAll(@RequestBody EmpQueryVo empQueryVo) {
        Page page = new Page(empQueryVo.getPageNo(),empQueryVo.getPageSize());
        IPage<Emp> iPage = this.empService.findUserListByPage(page, empQueryVo);

        return ResultWrapper.success(iPage);
    }

    //新增职工
    @ApiOperation("新增职工")
    @PostMapping("addEmp")
    public ResultWrapper addEmp(@RequestBody Emp emp) {
        if (this.empService.addEmp(emp)) {
            return ResultWrapper.success().message("添加成功");
        } else {
            return ResultWrapper.error().message("添加失败");
        }
    }
    //更新职工
    @PostMapping("updateEmp")
    public ResultWrapper updateEmp(@RequestBody Emp emp) {
        if (this.empService.update(emp) == 1) {
            return ResultWrapper.success().message("更新成功");
        } else {
            return ResultWrapper.error().message("更新失败");
        }
    }
    //删除职工
    @PostMapping("deleteEmp")
    public ResultWrapper deleteEmp(@RequestBody int id) {
        if (this.empService.deleteEmp(id) == 1) {
            return ResultWrapper.success().message("删除成功");
        } else {
            return ResultWrapper.error().message("删除失败");
        }
    }
    // 导出Excel
    @GetMapping("/exportTemplate")
    public void exportTemplate( HttpServletResponse response) throws IOException {
        List<CollectDataExcelVo> list = this.empService.queryExcel();
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("template"+ DateUtils.format(new Date(), "yyyy-MM-dd")+".xlsx", StandardCharsets.UTF_8.name()));

        EasyExcel.write(response.getOutputStream(), CollectDataExcelVo.class).sheet().doWrite(list);

    }


    }