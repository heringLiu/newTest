import http from '@/utils/request';
import exportExcel from '@/utils/request';

export default {
    // 查询职工列表
    async getEmpList(params) {
        return await http.post("/emp/queryAll", params);
    },
    // 新增职工
    async addEmp(params) {
        return await http.post("/emp/addEmp", params);
    },
    // 编辑职工
    async updateEmp(params) {
        return await http.post("/emp/updateEmp", params);
    },
    // 删除职工
    async deleteEmp(params) {
        return await http.post("/emp/deleteEmp", params);
    },
    // 部门选项
    async getDeptSelect() {
        return await http.get("/dept/getDeptSelect");
    },
    // 学历选项
    async getDegreeSelect() {
        return await http.get("/degree/getDegreeSelect");
    },
    // 导出excel
    async exportTemplate(params) {
        return await http.download("emp/exportTemplate",params);
    },

}