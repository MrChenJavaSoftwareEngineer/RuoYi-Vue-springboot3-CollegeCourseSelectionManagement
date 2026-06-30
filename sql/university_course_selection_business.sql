-- ----------------------------
-- 高校选课系统业务表和业务菜单初始化脚本
-- 导入顺序：ry_20250522.sql -> quartz.sql -> university_course_selection_business.sql
-- ----------------------------

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 1、学生表
-- ----------------------------
DROP TABLE IF EXISTS `学生`;
CREATE TABLE `学生` (
  `学号` varchar(32) NOT NULL COMMENT '学生唯一学号',
  `年级` bigint DEFAULT NULL COMMENT '学生所在年级',
  `姓名` varchar(50) NOT NULL COMMENT '学生姓名',
  `用户类型` varchar(20) DEFAULT '02' COMMENT '用户类型标识',
  `专业` varchar(100) NOT NULL COMMENT '学生所学专业',
  `逻辑删除` tinyint DEFAULT 0 COMMENT '软删除标记：0-未删除，1-已删除',
  PRIMARY KEY (`学号`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='存储学生基本信息';

INSERT INTO `学生` (`学号`, `年级`, `姓名`, `用户类型`, `专业`, `逻辑删除`) VALUES
('S2025001', 2025, '张三', '02', '软件工程', 0),
('S2025002', 2025, '李四', '02', '计算机科学与技术', 0),
('S2025003', 2024, '王五', '02', '数据科学与大数据技术', 0);

-- ----------------------------
-- 2、教师表
-- ----------------------------
DROP TABLE IF EXISTS `教师`;
CREATE TABLE `教师` (
  `教师工号` varchar(32) NOT NULL COMMENT '教师唯一工号',
  `姓名` varchar(50) NOT NULL COMMENT '教师姓名',
  `职称` varchar(50) DEFAULT NULL COMMENT '教师职称（教授、副教授等）',
  `部门` varchar(100) NOT NULL COMMENT '教师所在部门',
  `逻辑删除` tinyint DEFAULT 0 COMMENT '软删除标记：0-未删除，1-已删除',
  PRIMARY KEY (`教师工号`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='存储教师基本信息';

INSERT INTO `教师` (`教师工号`, `姓名`, `职称`, `部门`, `逻辑删除`) VALUES
('T2025001', '王老师', '教授', '计算机学院', 0),
('T2025002', '刘老师', '副教授', '软件学院', 0),
('T2025003', '陈老师', '讲师', '信息工程学院', 0);

-- ----------------------------
-- 3、教室表
-- ----------------------------
DROP TABLE IF EXISTS `教室`;
CREATE TABLE `教室` (
  `教室编号` varchar(32) NOT NULL COMMENT '教室唯一编号',
  `教室地址` varchar(200) NOT NULL COMMENT '教室具体位置',
  `教室容量` bigint DEFAULT NULL COMMENT '教室最大容纳人数',
  `逻辑删除` tinyint DEFAULT 0 COMMENT '软删除标记：0-未删除，1-已删除',
  PRIMARY KEY (`教室编号`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='存储教室基本信息';

INSERT INTO `教室` (`教室编号`, `教室地址`, `教室容量`, `逻辑删除`) VALUES
('JX101', '教学楼A-101', 120, 0),
('JX202', '教学楼B-202', 90, 0),
('SY301', '实验楼C-301', 60, 0);

-- ----------------------------
-- 4、课程表
-- ----------------------------
DROP TABLE IF EXISTS `课程`;
CREATE TABLE `课程` (
  `课程编号` varchar(32) NOT NULL COMMENT '课程唯一标识符',
  `课程名称` varchar(100) NOT NULL COMMENT '课程全称',
  `课程简介` varchar(500) DEFAULT NULL COMMENT '课程详细描述和内容介绍',
  `课程容量` bigint DEFAULT NULL COMMENT '课程可容纳的最大学生数',
  `学分` decimal(5,2) DEFAULT NULL COMMENT '课程学分值',
  `先修课程编号` varchar(32) DEFAULT NULL COMMENT '必须先修的课程编号，可为空',
  `所属专业` varchar(100) NOT NULL COMMENT '课程所属的专业领域',
  `逻辑删除` tinyint DEFAULT 0 COMMENT '软删除标记：0-未删除，1-已删除',
  PRIMARY KEY (`课程编号`),
  KEY `idx_课程_课程名称` (`课程名称`),
  KEY `idx_课程_所属专业` (`所属专业`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='存储课程基本信息';

INSERT INTO `课程` (`课程编号`, `课程名称`, `课程简介`, `课程容量`, `学分`, `先修课程编号`, `所属专业`, `逻辑删除`) VALUES
('CS101', 'Java程序设计', '面向对象程序设计基础与 Java 开发实践', 80, 3.00, NULL, '软件工程', 0),
('CS102', '数据库原理', '关系数据库设计、SQL 编程与事务处理', 80, 3.00, 'CS101', '计算机科学与技术', 0),
('CS103', '操作系统', '操作系统进程、内存、文件与设备管理基础', 70, 4.00, 'CS101', '计算机科学与技术', 0),
('CS104', '数据结构', '线性表、树、图、查找和排序算法', 90, 4.00, NULL, '软件工程', 0);

-- ----------------------------
-- 5、课程计划表
-- ----------------------------
DROP TABLE IF EXISTS `课程计划`;
CREATE TABLE `课程计划` (
  `课程计划编号` varchar(32) NOT NULL COMMENT '课程计划唯一编号',
  `类型` varchar(50) NOT NULL COMMENT '课程计划类型（如：必修、选修等）',
  `当天开始节次` bigint DEFAULT NULL COMMENT '当天课程开始节次',
  `当天结束节次` bigint DEFAULT NULL COMMENT '当天课程结束节次',
  `周内时间` bigint DEFAULT NULL COMMENT '周内上课时间（1-7表示周一到周日）',
  `结课时间` date DEFAULT NULL COMMENT '课程计划结束日期',
  `逻辑删除` tinyint DEFAULT 0 COMMENT '软删除标记：0-未删除，1-已删除',
  `课程名称` varchar(100) NOT NULL COMMENT '课程名称',
  `教室地址` varchar(200) NOT NULL COMMENT '教室具体地址',
  `授课老师` varchar(50) NOT NULL COMMENT '授课老师',
  PRIMARY KEY (`课程计划编号`),
  KEY `idx_课程计划_课程名称` (`课程名称`),
  KEY `idx_课程计划_授课老师` (`授课老师`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='存储课程计划安排';

INSERT INTO `课程计划` (`课程计划编号`, `类型`, `当天开始节次`, `当天结束节次`, `周内时间`, `结课时间`, `逻辑删除`, `课程名称`, `教室地址`, `授课老师`) VALUES
('CP2025001', '必修', 1, 2, 1, '2026-01-10', 0, 'Java程序设计', '教学楼A-101', '王老师'),
('CP2025002', '必修', 3, 4, 2, '2026-01-10', 0, '数据库原理', '教学楼B-202', '刘老师'),
('CP2025003', '选修', 5, 6, 3, '2026-01-10', 0, '操作系统', '实验楼C-301', '陈老师'),
('CP2025004', '必修', 7, 8, 4, '2026-01-10', 0, '数据结构', '教学楼A-101', '王老师');

-- ----------------------------
-- 6、选课记录表
-- ----------------------------
DROP TABLE IF EXISTS `选课记录`;
CREATE TABLE `选课记录` (
  `选课记录ID` bigint NOT NULL AUTO_INCREMENT COMMENT '选课记录唯一标识',
  `学号` varchar(32) NOT NULL COMMENT '学生学号，关联学生表',
  `成绩` decimal(5,2) DEFAULT NULL COMMENT '学生课程成绩，可为空',
  `选课时间` datetime DEFAULT NULL COMMENT '选课操作时间',
  `逻辑删除` tinyint DEFAULT 0 COMMENT '软删除标记：0-未删除，1-已删除',
  `课程名称` varchar(100) NOT NULL COMMENT '课程名称',
  PRIMARY KEY (`选课记录ID`),
  UNIQUE KEY `uk_选课记录_学号_课程名称` (`学号`, `课程名称`),
  KEY `idx_选课记录_课程名称` (`课程名称`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='存储学生选课记录';

INSERT INTO `选课记录` (`学号`, `成绩`, `选课时间`, `逻辑删除`, `课程名称`) VALUES
('S2025001', NULL, NOW(), 0, 'Java程序设计'),
('S2025002', NULL, NOW(), 0, '数据库原理');

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- 7、业务菜单和按钮权限
-- 说明：以下数据依赖若依基础表 sys_menu、sys_role_menu 已存在。
-- ----------------------------

INSERT IGNORE INTO `sys_menu` VALUES
('2000', '选课管理', '0', '4', 'courseSelection', NULL, '', '', 1, 0, 'M', '0', '0', '', '选课管理', 'admin', sysdate(), '', NULL, '高校选课业务目录'),
('2001', '学生管理', '2000', '1', 'student', 'system/学生/index', '', '', 1, 0, 'C', '0', '0', 'system:学生:list', '用户管理', 'admin', sysdate(), '', NULL, '学生管理菜单'),
('2002', '教师管理', '2000', '2', 'teacher', 'system/教师/index', '', '', 1, 0, 'C', '0', '0', 'system:教师:list', 'people', 'admin', sysdate(), '', NULL, '教师管理菜单'),
('2003', '教室管理', '2000', '3', 'classroom', 'system/教室/index', '', '', 1, 0, 'C', '0', '0', 'system:教室:list', 'education', 'admin', sysdate(), '', NULL, '教室管理菜单'),
('2004', '课程管理', '2000', '4', 'course', 'system/课程/index', '', '', 1, 0, 'C', '0', '0', 'system:课程:list', '课程管理', 'admin', sysdate(), '', NULL, '课程管理菜单'),
('2005', '课程计划', '2000', '5', 'coursePlan', 'system/课程计划/index', '', '', 1, 0, 'C', '0', '0', 'system:课程计划:list', '选课管理', 'admin', sysdate(), '', NULL, '课程计划管理菜单'),
('2006', '选课记录', '2000', '6', 'courseRecord', 'system/选课记录/index', '', '', 1, 0, 'C', '0', '0', 'system:选课记录:list', '选课记录', 'admin', sysdate(), '', NULL, '选课记录管理菜单'),
('2007', '可选课程', '2000', '7', 'coursePlanVo', 'system/课程计划vo/index', '', '', 1, 0, 'C', '0', '0', 'system:课程计划vo:list', '课程管理', 'admin', sysdate(), '', NULL, '学生可选课程菜单'),
('2008', '我的选课', '2000', '8', 'courseRecordVo', 'system/选课记录vo/index', '', '', 1, 0, 'C', '0', '0', 'system:选课记录vo:list', '选课记录', 'admin', sysdate(), '', NULL, '学生已选课程菜单');

INSERT IGNORE INTO `sys_menu` VALUES
('2010', '学生查询', '2001', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:学生:query', '#', 'admin', sysdate(), '', NULL, ''),
('2011', '学生新增', '2001', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:学生:add', '#', 'admin', sysdate(), '', NULL, ''),
('2012', '学生修改', '2001', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:学生:edit', '#', 'admin', sysdate(), '', NULL, ''),
('2013', '学生删除', '2001', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:学生:remove', '#', 'admin', sysdate(), '', NULL, ''),
('2014', '学生导出', '2001', '5', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:学生:export', '#', 'admin', sysdate(), '', NULL, '');

INSERT IGNORE INTO `sys_menu` VALUES
('2020', '教师查询', '2002', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:教师:query', '#', 'admin', sysdate(), '', NULL, ''),
('2021', '教师新增', '2002', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:教师:add', '#', 'admin', sysdate(), '', NULL, ''),
('2022', '教师修改', '2002', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:教师:edit', '#', 'admin', sysdate(), '', NULL, ''),
('2023', '教师删除', '2002', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:教师:remove', '#', 'admin', sysdate(), '', NULL, ''),
('2024', '教师导出', '2002', '5', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:教师:export', '#', 'admin', sysdate(), '', NULL, '');

INSERT IGNORE INTO `sys_menu` VALUES
('2030', '教室查询', '2003', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:教室:query', '#', 'admin', sysdate(), '', NULL, ''),
('2031', '教室新增', '2003', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:教室:add', '#', 'admin', sysdate(), '', NULL, ''),
('2032', '教室修改', '2003', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:教室:edit', '#', 'admin', sysdate(), '', NULL, ''),
('2033', '教室删除', '2003', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:教室:remove', '#', 'admin', sysdate(), '', NULL, ''),
('2034', '教室导出', '2003', '5', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:教室:export', '#', 'admin', sysdate(), '', NULL, '');

INSERT IGNORE INTO `sys_menu` VALUES
('2040', '课程查询', '2004', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:课程:query', '#', 'admin', sysdate(), '', NULL, ''),
('2041', '课程新增', '2004', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:课程:add', '#', 'admin', sysdate(), '', NULL, ''),
('2042', '课程修改', '2004', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:课程:edit', '#', 'admin', sysdate(), '', NULL, ''),
('2043', '课程删除', '2004', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:课程:remove', '#', 'admin', sysdate(), '', NULL, ''),
('2044', '课程导出', '2004', '5', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:课程:export', '#', 'admin', sysdate(), '', NULL, '');

INSERT IGNORE INTO `sys_menu` VALUES
('2050', '课程计划查询', '2005', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:课程计划:query', '#', 'admin', sysdate(), '', NULL, ''),
('2051', '课程计划新增', '2005', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:课程计划:add', '#', 'admin', sysdate(), '', NULL, ''),
('2052', '课程计划修改', '2005', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:课程计划:edit', '#', 'admin', sysdate(), '', NULL, ''),
('2053', '课程计划删除', '2005', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:课程计划:remove', '#', 'admin', sysdate(), '', NULL, ''),
('2054', '课程计划导出', '2005', '5', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:课程计划:export', '#', 'admin', sysdate(), '', NULL, '');

INSERT IGNORE INTO `sys_menu` VALUES
('2060', '选课记录查询', '2006', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:选课记录:query', '#', 'admin', sysdate(), '', NULL, ''),
('2061', '选课记录新增', '2006', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:选课记录:add', '#', 'admin', sysdate(), '', NULL, ''),
('2062', '选课记录修改', '2006', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:选课记录:edit', '#', 'admin', sysdate(), '', NULL, ''),
('2063', '选课记录删除', '2006', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:选课记录:remove', '#', 'admin', sysdate(), '', NULL, ''),
('2064', '选课记录导出', '2006', '5', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:选课记录:export', '#', 'admin', sysdate(), '', NULL, '');

INSERT IGNORE INTO `sys_menu` VALUES
('2070', '可选课程查询', '2007', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:课程计划vo:query', '#', 'admin', sysdate(), '', NULL, ''),
('2071', '可选课程新增', '2007', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:课程计划vo:add', '#', 'admin', sysdate(), '', NULL, ''),
('2072', '可选课程修改', '2007', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:课程计划vo:edit', '#', 'admin', sysdate(), '', NULL, ''),
('2073', '可选课程删除', '2007', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:课程计划vo:remove', '#', 'admin', sysdate(), '', NULL, ''),
('2074', '可选课程导出', '2007', '5', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:课程计划vo:export', '#', 'admin', sysdate(), '', NULL, '');

INSERT IGNORE INTO `sys_menu` VALUES
('2080', '我的选课查询', '2008', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:选课记录vo:query', '#', 'admin', sysdate(), '', NULL, ''),
('2081', '我的选课新增', '2008', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:选课记录vo:add', '#', 'admin', sysdate(), '', NULL, ''),
('2082', '我的选课修改', '2008', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:选课记录vo:edit', '#', 'admin', sysdate(), '', NULL, ''),
('2083', '我的选课删除', '2008', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:选课记录vo:remove', '#', 'admin', sysdate(), '', NULL, ''),
('2084', '我的选课导出', '2008', '5', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:选课记录vo:export', '#', 'admin', sysdate(), '', NULL, '');

-- 普通角色授权业务菜单。管理员账号会通过若依内置逻辑拥有全部权限。
INSERT IGNORE INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES
(2, 2000),
(2, 2001), (2, 2002), (2, 2003), (2, 2004), (2, 2005), (2, 2006), (2, 2007), (2, 2008),
(2, 2010), (2, 2011), (2, 2012), (2, 2013), (2, 2014),
(2, 2020), (2, 2021), (2, 2022), (2, 2023), (2, 2024),
(2, 2030), (2, 2031), (2, 2032), (2, 2033), (2, 2034),
(2, 2040), (2, 2041), (2, 2042), (2, 2043), (2, 2044),
(2, 2050), (2, 2051), (2, 2052), (2, 2053), (2, 2054),
(2, 2060), (2, 2061), (2, 2062), (2, 2063), (2, 2064),
(2, 2070), (2, 2071), (2, 2072), (2, 2073), (2, 2074),
(2, 2080), (2, 2081), (2, 2082), (2, 2083), (2, 2084);

ALTER TABLE `sys_menu` AUTO_INCREMENT = 2100;
