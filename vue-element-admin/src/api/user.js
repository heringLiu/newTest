import http from '@/utils/request'

export async function login(data) {
  return await http.login("/users/login", data);
}

export async function getInfo() {
  return await http.get("/users/getInfo");
}

export async function logout() {
  return request({
    url: '/vue-element-admin/user/logout',
    method: 'post'
  })
}
// 获取用户菜单
export async function getMenulist() {
  return await http.get("/users/getMenuList");
}
