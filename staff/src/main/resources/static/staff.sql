/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : staff

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 24/07/2023 09:43:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for degree
-- ----------------------------
DROP TABLE IF EXISTS `degree`;
CREATE TABLE `degree` (
  `emp_degree_code` int NOT NULL,
  `emp_degree_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`emp_degree_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of degree
-- ----------------------------
BEGIN;
INSERT INTO `degree` VALUES (1, '大专');
INSERT INTO `degree` VALUES (2, '本科');
INSERT INTO `degree` VALUES (3, '研究生');
COMMIT;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `dept_code` int NOT NULL COMMENT '部门编号',
  `dept_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  PRIMARY KEY (`dept_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of dept
-- ----------------------------
BEGIN;
INSERT INTO `dept` VALUES (1, '业务部');
INSERT INTO `dept` VALUES (2, '后勤部');
INSERT INTO `dept` VALUES (3, '人事部');
COMMIT;

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '职工ID',
  `name` varchar(50) DEFAULT NULL COMMENT '职工姓名',
  `sex` int DEFAULT NULL COMMENT '性别 0 女 1 男',
  `age` int DEFAULT NULL COMMENT '职工年龄',
  `emp_dept_code` int DEFAULT NULL COMMENT '部门 1业务部，2后勤部，3人事部',
  `emp_degree_code` int DEFAULT NULL COMMENT '学历 1大专，2本科，3研究生',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1041 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of emp
-- ----------------------------
BEGIN;
INSERT INTO `emp` VALUES (1, '老张', 0, 43, 1, 1);
INSERT INTO `emp` VALUES (1000, '张一', 1, 25, 1, 2);
INSERT INTO `emp` VALUES (1001, '张二', 0, 26, 3, 2);
INSERT INTO `emp` VALUES (1002, '张三', 1, 27, 2, 1);
INSERT INTO `emp` VALUES (1003, '张四', 0, 28, 3, 1);
INSERT INTO `emp` VALUES (1004, '张五', 1, 29, 2, 1);
INSERT INTO `emp` VALUES (1005, '张六', 0, 29, 2, 2);
INSERT INTO `emp` VALUES (1006, '张七', 1, 33, 1, 3);
INSERT INTO `emp` VALUES (1007, '张八', 1, 32, 1, 2);
INSERT INTO `emp` VALUES (1008, '张九', 0, 33, 1, 1);
INSERT INTO `emp` VALUES (1009, '李一', 0, 45, 1, 3);
INSERT INTO `emp` VALUES (1010, '李二', 0, 19, 3, 2);
INSERT INTO `emp` VALUES (1011, '李三', 1, 28, 1, 3);
INSERT INTO `emp` VALUES (1012, '李四', 0, 46, 2, 3);
INSERT INTO `emp` VALUES (1013, '李五', 1, 58, 1, 1);
INSERT INTO `emp` VALUES (1014, '李六', 0, 22, 3, 3);
INSERT INTO `emp` VALUES (1015, '李七', 1, 26, 2, 1);
INSERT INTO `emp` VALUES (1016, '李八', 1, 25, 3, 3);
INSERT INTO `emp` VALUES (1017, '李九', 0, 29, 2, 3);
INSERT INTO `emp` VALUES (1018, '王一', 1, 45, 2, 2);
INSERT INTO `emp` VALUES (1019, '王二', 0, 21, 1, 2);
INSERT INTO `emp` VALUES (1020, '王三', 1, 21, 1, 1);
INSERT INTO `emp` VALUES (1021, '王四', 1, 23, 1, 1);
INSERT INTO `emp` VALUES (1022, '王五', 0, 33, 1, 1);
INSERT INTO `emp` VALUES (1023, '王六', 1, 45, 3, 2);
INSERT INTO `emp` VALUES (1024, '王七', 1, 35, 1, 3);
INSERT INTO `emp` VALUES (1025, '王八', 1, 41, 2, 2);
INSERT INTO `emp` VALUES (1026, '王九', 0, 25, 1, 1);
INSERT INTO `emp` VALUES (1027, '赵一', 1, 26, 3, 3);
INSERT INTO `emp` VALUES (1028, '赵二', 1, 20, 2, 2);
INSERT INTO `emp` VALUES (1029, '赵三', 0, 21, 3, 3);
INSERT INTO `emp` VALUES (1030, '赵四', 1, 19, 2, 3);
INSERT INTO `emp` VALUES (1031, '赵五', 0, 35, 2, 1);
INSERT INTO `emp` VALUES (1032, '赵六', 1, 24, 1, 3);
INSERT INTO `emp` VALUES (1033, '赵七', 1, 29, 1, 1);
INSERT INTO `emp` VALUES (1034, '赵八', 0, 33, 1, 3);
INSERT INTO `emp` VALUES (1035, '赵九', 1, 45, 1, 2);
COMMIT;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限编号',
  `label` varchar(50) DEFAULT NULL COMMENT '权限名称',
  `parent_id` bigint DEFAULT NULL COMMENT '父权限ID',
  `parent_name` varchar(50) DEFAULT NULL COMMENT '父权限名称',
  `code` varchar(50) DEFAULT NULL COMMENT '授权标识符',
  `path` varchar(100) DEFAULT NULL COMMENT '路由地址',
  `name` varchar(50) DEFAULT NULL COMMENT '路由名称',
  `url` varchar(100) DEFAULT NULL COMMENT '授权路径',
  `type` tinyint DEFAULT NULL COMMENT '权限类型(0-目录 1-菜单 2-按钮)',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `order_num` int DEFAULT NULL COMMENT '排序',
  `is_delete` tinyint DEFAULT '0' COMMENT '是否删除(0-未删除，1-已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES (1, '系统管理', 0, '顶级菜单', 'sys:manager', '/system', 'system', '/system/system', 0, 'el-icon-menu', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, 0, 0);
INSERT INTO `sys_permission` VALUES (2, '部门管理', 1, '系统管理', 'sys:department', '/department', 'department', '/system/department/department', 1, 'el-icon-s-tools', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (3, '新增', 2, '部门管理', 'sys:department:add', NULL, NULL, NULL, 2, 'el-icon-plus', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (4, '修改', 2, '部门管理', 'sys:department:edit', NULL, NULL, NULL, 2, 'el-icon-edit', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (5, '删除', 2, '部门管理', 'sys:department:delete', NULL, NULL, NULL, 2, 'el-icon-delete', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (6, '用户管理', 1, '系统管理', 'sys:user', '/userList', 'userList', '/system/user/userList', 1, 'el-icon-s-custom', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (7, '新增', 6, '用户管理', 'sys:user:add', NULL, NULL, NULL, 2, 'el-icon-plus', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (8, '修改', 6, '用户管理', 'sys:user:edit', NULL, NULL, NULL, 2, 'el-icon-edit', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (9, '删除', 6, '用户管理', 'sys:user:delete', NULL, NULL, NULL, 2, 'el-icon-delete', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (10, '角色管理', 1, '系统管理', 'sys:role', '/roleList', 'roleList', '/system/role/roleList', 1, 'el-icon-s-tools', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (11, '新增', 10, '角色管理', 'sys:role:add', NULL, NULL, NULL, 2, 'el-icon-plus', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (12, '修改', 10, '角色管理', 'sys:role:edit', NULL, NULL, NULL, 2, 'el-icon-edit', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (13, '删除', 10, '角色管理', 'sys:role:delete', NULL, NULL, NULL, 2, 'el-icon-delete', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (14, '菜单管理', 1, '系统管理', 'sys:menu', '/menuList', 'menuList', '/system/menu/menuList', 1, 'el-icon-s-tools', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (15, '新增', 14, '权限管理', 'sys:menu:add', NULL, NULL, NULL, 2, 'el-icon-plus', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (16, '修改', 14, '权限管理', 'sys:menu:edit', NULL, NULL, NULL, 2, 'el-icon-edit', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (17, '删除', 14, '权限管理', 'sys:menu:delete', NULL, NULL, NULL, 2, 'el-icon-delete', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (18, '职工管理', 0, '顶级菜单', 'sys:staff', '/staff', 'staff', '/staff/index', 0, 'el-icon-menu', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, 2, 0);
INSERT INTO `sys_permission` VALUES (19, '职工列表', 18, '职工管理', 'sys:emp', '/empList', 'empList', '/staff/empList/empList', 1, 'el-icon-s-tools', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, 2, 0);
INSERT INTO `sys_permission` VALUES (20, '新增', 19, '职工列表', 'sys:emp:add', NULL, NULL, NULL, 2, 'el-icon-plus', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (21, '修改', 19, '职工列表', 'sys:emp:edit', NULL, NULL, NULL, 2, 'el-icon-edit', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (22, '删除', 19, '职工列表', 'sys:emp:delete', NULL, NULL, NULL, 2, 'el-icon-delete', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (23, '分配角色', 6, '用户管理', 'sys:user:assign', '', '', '', 2, 'el-icon-setting', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (24, '分配权限', 10, '角色管理', 'sys:role:assign', '', '', '', 2, 'el-icon-setting', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (25, '查询', 2, '部门管理', 'sys:department:select', '', '', '', 2, 'el-icon-search', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (26, '查询', 6, '用户管理', 'sys:user:select', '', '', '', 2, 'el-icon-search', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (27, '查询', 10, '角色管理', 'sys:role:select', '', '', '', 2, 'el-icon-search', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (28, '查询', 14, '菜单管理', 'sys:menu:select', '', '', '', 2, 'el-icon-search', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (29, '订单管理', 18, '资料管理', 'resource:order', '/resource/order', 'OrderList', '/resource/order/orderList', 1, 'el-icon-setting', NULL, NULL, NULL, 2, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role_code` varchar(50) NOT NULL COMMENT '角色编码',
  `role_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '角色名称',
  `create_user` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '备注',
  `is_delete` tinyint DEFAULT '0' COMMENT '是否删除(0-未删除，1-已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, 'ROLE_SYSTEM', '超级管理员', 1, '2022-04-25 14:44:23', '2022-04-25 14:44:23', NULL, 0);
INSERT INTO `sys_role` VALUES (2, 'ROLE_SYSTEM', '系统管理员', 1, '2022-04-25 14:44:23', '2022-04-25 14:44:23', '拥有系统管理功能模块的权限', 0);
INSERT INTO `sys_role` VALUES (3, 'ROLE_RESOURCE', '资料管理员', NULL, NULL, NULL, '拥有资料管理模块的功能权限', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_Id` bigint NOT NULL COMMENT '角色ID',
  `permission_Id` bigint NOT NULL COMMENT '权限ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission` VALUES (1, 1);
INSERT INTO `sys_role_permission` VALUES (1, 2);
INSERT INTO `sys_role_permission` VALUES (1, 3);
INSERT INTO `sys_role_permission` VALUES (1, 4);
INSERT INTO `sys_role_permission` VALUES (1, 5);
INSERT INTO `sys_role_permission` VALUES (1, 25);
INSERT INTO `sys_role_permission` VALUES (1, 6);
INSERT INTO `sys_role_permission` VALUES (1, 7);
INSERT INTO `sys_role_permission` VALUES (1, 8);
INSERT INTO `sys_role_permission` VALUES (1, 9);
INSERT INTO `sys_role_permission` VALUES (1, 23);
INSERT INTO `sys_role_permission` VALUES (1, 26);
INSERT INTO `sys_role_permission` VALUES (1, 10);
INSERT INTO `sys_role_permission` VALUES (1, 11);
INSERT INTO `sys_role_permission` VALUES (1, 12);
INSERT INTO `sys_role_permission` VALUES (1, 13);
INSERT INTO `sys_role_permission` VALUES (1, 24);
INSERT INTO `sys_role_permission` VALUES (1, 27);
INSERT INTO `sys_role_permission` VALUES (1, 14);
INSERT INTO `sys_role_permission` VALUES (1, 15);
INSERT INTO `sys_role_permission` VALUES (1, 16);
INSERT INTO `sys_role_permission` VALUES (1, 17);
INSERT INTO `sys_role_permission` VALUES (1, 28);
INSERT INTO `sys_role_permission` VALUES (1, 18);
INSERT INTO `sys_role_permission` VALUES (1, 19);
INSERT INTO `sys_role_permission` VALUES (1, 20);
INSERT INTO `sys_role_permission` VALUES (1, 21);
INSERT INTO `sys_role_permission` VALUES (1, 22);
INSERT INTO `sys_role_permission` VALUES (2, 1);
INSERT INTO `sys_role_permission` VALUES (2, 2);
INSERT INTO `sys_role_permission` VALUES (2, 3);
INSERT INTO `sys_role_permission` VALUES (2, 4);
INSERT INTO `sys_role_permission` VALUES (2, 5);
INSERT INTO `sys_role_permission` VALUES (2, 25);
INSERT INTO `sys_role_permission` VALUES (2, 6);
INSERT INTO `sys_role_permission` VALUES (2, 7);
INSERT INTO `sys_role_permission` VALUES (2, 8);
INSERT INTO `sys_role_permission` VALUES (2, 9);
INSERT INTO `sys_role_permission` VALUES (2, 23);
INSERT INTO `sys_role_permission` VALUES (2, 26);
INSERT INTO `sys_role_permission` VALUES (2, 10);
INSERT INTO `sys_role_permission` VALUES (2, 11);
INSERT INTO `sys_role_permission` VALUES (2, 12);
INSERT INTO `sys_role_permission` VALUES (2, 13);
INSERT INTO `sys_role_permission` VALUES (2, 24);
INSERT INTO `sys_role_permission` VALUES (2, 27);
INSERT INTO `sys_role_permission` VALUES (2, 14);
INSERT INTO `sys_role_permission` VALUES (2, 15);
INSERT INTO `sys_role_permission` VALUES (2, 16);
INSERT INTO `sys_role_permission` VALUES (2, 17);
INSERT INTO `sys_role_permission` VALUES (2, 28);
INSERT INTO `sys_role_permission` VALUES (3, 18);
INSERT INTO `sys_role_permission` VALUES (3, 19);
INSERT INTO `sys_role_permission` VALUES (3, 20);
INSERT INTO `sys_role_permission` VALUES (3, 21);
INSERT INTO `sys_role_permission` VALUES (3, 22);
INSERT INTO `sys_role_permission` VALUES (3, 29);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `role_id` bigint NOT NULL COMMENT '角色编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (4, 2);
INSERT INTO `sys_user_role` VALUES (6, 3);
INSERT INTO `sys_user_role` VALUES (5, 3);
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int NOT NULL COMMENT '主键',
  `user_id` varchar(50) NOT NULL COMMENT '用户账号',
  `user_pwd` varchar(200) NOT NULL COMMENT '用户密码',
  `user_name` varchar(200) DEFAULT NULL COMMENT '用户名称',
  `is_account_non_expired` int NOT NULL COMMENT '帐户是否过期(1-未过期，0-已过期)',
  `is_account_non_locked` int NOT NULL COMMENT '帐户是否被锁定(1-未锁定，0-已锁定',
  `is_credentials_non_expired` int NOT NULL COMMENT '密码是否过期(1-未过期，0-已过期)',
  `is_enabled` int NOT NULL COMMENT '帐户是否可用(1-可用，0-禁用)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES (1, 'admin', '$2a$10$TO4k63ZUuFY/.TBP1ELJvepnYkMOUYwe/dniHA7TDu.6iLiVaZoOG', '管理员', 1, 1, 1, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
