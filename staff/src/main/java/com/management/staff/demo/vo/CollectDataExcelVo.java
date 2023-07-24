package com.management.staff.demo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

@Data
public class CollectDataExcelVo {
    @ExcelProperty(value = "ID", index = 0)
    @ColumnWidth(value = 15)
    private String id;
    @ExcelProperty(value = "姓名", index = 1)
    @ColumnWidth(value = 15)
    private String name;
    @ExcelProperty(value = "性别", index = 2)
    @ColumnWidth(value = 15)
    private String sex;
    @ExcelProperty(value = "年龄", index = 3)
    @ColumnWidth(value = 15)
    private String age;
    @ExcelProperty(value = "部门", index = 4)
    @ColumnWidth(value = 15)
    private String dept;
    @ExcelProperty(value = "学历", index = 5)
    @ColumnWidth(value = 15)
    private String degree;
}
