<template>
    <el-main>
        <!-- 查询条件 -->
        <el-form :inline="true" size="small" label-width="80px">
            <el-form-item label="">
                <el-input :inline="true" v-model="searchModel.name" placeholder="姓名" />
            </el-form-item>
            <el-form-item label="">
                <el-select placeholder="部门" filterable v-model="searchModel.empDeptCode">
                    <el-option v-for="item in deptSelectData" :key="item.deptCode" :value="item.deptCode"
                        :label="item.deptName"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="">
                <el-select placeholder="学历" filterable v-model="searchModel.empDegreeCode">
                    <el-option v-for="item in degreeSelectData" :key="item.empDegreeCode" :value="item.empDegreeCode"
                        :label="item.empDegreeName"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" @click="search()">搜索</el-button>
                <el-button type="primary" icon="el-icon-circle-plus-outline" @click="openAddWindow()">添加</el-button>
                <el-button type="primary" icon="el-icon-download" @click="exportExcel()">导出</el-button>
            </el-form-item>
        </el-form>
        <!-- 数据展示 -->
        <template>
            <el-table :data="tableData" border style="width: 100%">
                <el-table-column prop="num" label="序号" width="180" />
                <el-table-column prop="name" label="职工姓名" width="180" />
                <el-table-column prop="sex" label="性别" width="180" />
                <el-table-column prop="age" label="年龄" width="180" />
                <el-table-column prop="empDeptName" label="部门" width="180" />
                <el-table-column prop="empDegreeName" label="学历" width="180" />
                <el-table-column label="操作" width="180">
                    <template slot-scope="scope">
                        <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                        <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
                    </template>
                </el-table-column>


            </el-table>
            <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange"
                :current-page="pageNo" :page-sizes="[10, 20, 30, 40, 50]" :page-size="10"
                layout="total, sizes, prev, pager, next, jumper" :total="total"></el-pagination>
        </template>
        <empDialog :title="empDialogData.title" :visible="empDialogData.visible" :width="empDialogData.width"
            :height="empDialogData.height" @onClose="onClose()" @onConfirm="onConfirm()">
            <div slot="content">
                <el-form :model="emp" ref="empFrom" :rules="rules" label-width="80px" :inline="true" size="small">
                    <el-form-item label="姓名" prop="name">
                        <el-input v-model="emp.name"></el-input>
                    </el-form-item>
                    <el-form-item label="性别" prop="sex">
                        <el-select placeholder="性别" filterable v-model="emp.sex">
                            <el-option v-for="item in sexOptions" :key="item.value" :value="item.value"
                                :label="item.label"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="年龄" prop="sex">
                        <el-input v-model="emp.age"></el-input>
                    </el-form-item>
                    <el-form-item label="部门" prop="empDeptCode">
                        <el-select placeholder="部门" filterable v-model="emp.empDeptCode">
                            <el-option v-for="item in deptSelectData" :key="item.deptCode" :value="item.deptCode"
                                :label="item.deptName"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="学历" prop="empDegreeCode">
                        <el-select placeholder="学历" filterable v-model="emp.empDegreeCode">
                            <el-option v-for="item in degreeSelectData" :key="item.empDegreeCode"
                                :value="item.empDegreeCode" :label="item.empDegreeName"></el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
            </div>

        </empDialog>
    </el-main>
</template>

<script>
import empListApi from '@/api/empList';
import empDialog from '@/components/staff/empList/empDialog.vue';
import fileDownload from "js-file-download";

export default {
    name: "empList",
    components: {
        empDialog
    },
    data() {
        return {
            sexOptions: [
                {
                    value: 1,
                    label: '男'
                }, {
                    value: 0,
                    label: '女'
                },
            ],
            searchModel: {
                name: "",
                empDeptCode: "",
                empDegreeCode: "",
                pageNo: 1,
                pageSize: 10, 
            },
            tableData: [],
            empDialogData: {
                title: "",
                visible: false,
                width: 560,
                height: 170,
            },
            emp: {
                id: "",
                name: "",
                sex: "",
                age: "",
                empDeptCode: "",
                empDegreeCode: "",
                empDeptName: "",
                empDegreeName: "",
            },
            rules: {
                name: [{ required: true, message: '请输入职工姓名', trigger: 'blur' },],
                sex: [{ required: true, message: '请选择职工性别', trigger: 'blur' },],
                age: [{ required: true, message: '请输入职工年龄', trigger: 'change' },
                { type: 'number', message: '年龄必须为数字值' },],
                empDeptCode: [{ required: true, message: '请选择职工部门', trigger: 'blur', type: 'number', }],
                empDegreeCode: [{ required: true, message: '请选择职工学历', trigger: 'blur', type: 'number', },],
            },
            deptSelectData: [],
            degreeSelectData: [],
            pageNo:1,
            total:0,
        }
    },
    created() {
        this.search();
        this.getSelectData();
    },
    methods: {
        handleCurrentChange(page) {
            this.searchModel.pageNo = page;
            //调用查询方法 
            this.search();
        },
        async search() {
            console.log(this.searchModel);
            let res = await empListApi.getEmpList(this.searchModel);
            if (res.success) {
                this.tableData = res.data.records;
                this.total = res.data.total;
            }
        },
        handleSizeChange(size) {
            this.searchModel.pageSize = size;
            //将每页显示的数量交给成员变量 
            this.search();
        },
        async getSelectData() {
            let deptRes = await empListApi.getDeptSelect();
            deptRes.data.forEach(element => {
                this.deptSelectData.push({ deptCode: element.deptCode, deptName: element.deptName, });
            });
            let degreeRes = await empListApi.getDegreeSelect();
            degreeRes.data.forEach(element => {
                this.degreeSelectData.push({ empDegreeCode: element.empDegreeCode, empDegreeName: element.empDegreeName });
            });
        },
        async onConfirm() {
            this.$refs.empFrom.validate(async (valid) => {
                if (valid) {
                    let res = null;
                    if (this.emp.id === '') {
                        res = await empListApi.addEmp(this.emp);
                    } else {
                        res = await empListApi.updateEmp(this.emp);
                    }

                    if (res.success) {
                        this.$message.success(res.message);
                        this.search();
                        this.empDialogData.visible = false;
                    } else {
                        this.$message.error(res.message);
                    }
                }
            });
        },
        async onClose() {
            this.empDialogData.visible = false;
        },
        openAddWindow() {
            // 清空表单数据
            this.$resetForm("empFrom", this.emp);
            this.empDialogData.title = "新增";
            this.empDialogData.visible = true;
        },
        async handleDelete(row) {
            // console.log(row);
            let confirm = await this.$myconfirm("确定删除数据吗?");
            if (confirm) {
                let res = await empListApi.deleteEmp(row.id);
                if (res.success) {
                    this.$message.success(res.message);
                    this.search();
                    this.empDialogData.visible = false;
                } else {
                    this.$message.error(res.message);
                }
            }

        },
        async exportTemplate() {
            debugger
            let res = await empListApi.exportTemplate();
            debugger
        },
        handleEdit(row) {
            //数据回显 
            this.$objCopy(row, this.emp);
            console.log(row.empDeptName);

            console.log(this.emp.empDeptName);

            this.deptSelectData.forEach(element => {
                if (element.deptName === this.emp.empDeptName) {
                    this.emp.empDeptCode = element.deptCode;
                }
            });
            this.degreeSelectData.forEach(element => {
                if (element.empDegreeName === this.emp.empDegreeName) {
                    this.emp.empDegreeCode = element.empDegreeCode;
                }
            });
            this.sexOptions.forEach(element => {
                if (element.label === this.emp.sex) {
                    this.emp.sex = element.value;
                }
            });
            //设置窗口标题 
            this.empDialogData.title = "编辑职工信息";
            //显示编辑窗口 
            this.empDialogData.visible = true;
        },
        // 导出excel
        exportExcel() {
            //封装查询参数data
            let data = {
                param: this.searchModel,
            };
            this.$confirm('确定导出？', '操作提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
            }).then(() => {
                //调用java接口，取到java返回的文件流，定义类型为blob
                empListApi.exportTemplate(data, { responseType: "blob" }).then((res) => {
                    debugger
                    // let date = this.getDate();
                    //调用fileDownload
                    fileDownload(res.data, "职工名单.xlsx");
                    this.$message({
                        message: '成功！',
                        type: 'success'
                    });
                })
            })
        },
    }

};
</script>

<style lang="scss" scoped></style>