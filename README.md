# 高校选课系统

基于 RuoYi-Vue SpringBoot3 改造的高校选课管理系统，采用前后端分离架构，后端为 Spring Boot 多模块工程，前端为 Vue2 + Element UI 管理端。系统在若依基础能力之上扩展了学生、教师、教室、课程、课程计划和选课记录等高校选课业务模块。

## 功能模块

- 学生信息管理：维护学号、姓名、年级、专业、用户类型等信息
- 教师信息管理：维护教师工号、姓名、职称、所属部门等信息
- 教室信息管理：维护教室编号、教室地址、教室容量等信息
- 课程信息管理：维护课程编号、课程名称、课程简介、课程容量、学分、先修课程、所属专业等信息
- 课程计划管理：维护课程类型、上课节次、周内时间、结课时间、课程名称、教室地址、授课老师等信息
- 选课记录管理：维护学生选课记录、课程成绩、选课时间等信息
- 学生选课接口：支持学生登录、查询可选课程、查询已选课程、提交选课
- 系统管理：用户、角色、菜单、部门、岗位、字典、参数配置、通知公告等
- 系统监控：在线用户、登录日志、操作日志、服务监控、缓存监控、定时任务等
- 系统工具：代码生成、接口文档、表单构建等

## 技术栈

### 后端

- Java 17
- Spring Boot 3.5.8
- Spring Security
- JWT
- MyBatis
- PageHelper
- MySQL 8.x
- Redis
- Druid
- Quartz
- Springdoc OpenAPI
- FastJSON2
- Apache POI

### 前端

- Vue 2.6.12
- Vue Router 3.4.9
- Vuex 3.6.0
- Element UI 2.15.14
- Axios
- ECharts
- Vue CLI 4.4.6

## 项目结构

```text
.
├── ruoyi-admin        # 后端启动模块、接口入口、配置文件
├── ruoyi-common       # 通用工具、注解、常量、基础实体
├── ruoyi-framework    # 框架核心、安全认证、配置、切面、拦截器
├── ruoyi-system       # 系统业务模块和高校选课业务模块
├── ruoyi-quartz       # 定时任务模块
├── ruoyi-generator    # 代码生成模块
├── ruoyi-ui           # Vue 前端项目
├── sql                # 数据库初始化脚本
├── bin                # 打包和运行脚本
├── ry.bat             # Windows 后端服务管理脚本
└── ry.sh              # Linux 后端服务管理脚本
```

## 环境要求

- JDK 17+
- Maven 3.6+
- MySQL 8.x
- Redis 5+
- Node.js >= 8.9
- npm >= 3.0

## 数据库初始化

1. 创建数据库：

```sql
CREATE DATABASE university_course_selection_system
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;
```

2. 按顺序导入 SQL：

```text
sql/ry_20250522.sql
sql/quartz.sql
sql/university_course_selection_business.sql
```

其中：

- `ry_20250522.sql` 为若依系统基础表和初始化数据
- `quartz.sql` 为定时任务相关表
- `university_course_selection_business.sql` 为本项目高校选课业务表、业务菜单和权限数据

3. 修改数据库连接配置：

```text
ruoyi-admin/src/main/resources/application-druid.yml
```

将 MySQL 地址、数据库名、用户名、密码修改为本地环境配置。

4. 启动 Redis，默认连接配置位于：

```text
ruoyi-admin/src/main/resources/application.yml
```

默认 Redis 地址为 `localhost:6379`。

## 后端启动

在项目根目录执行：

```bash
mvn clean package -Dmaven.test.skip=true
```

启动后端服务：

```bash
java -jar ruoyi-admin/target/ruoyi-admin.jar
```

后端默认访问地址：

```text
http://localhost:8080
```

接口文档地址：

```text
http://localhost:8080/swagger-ui.html
```

Druid 监控地址：

```text
http://localhost:8080/druid/login.html
```

## 前端启动

进入前端目录：

```bash
cd ruoyi-ui
```

安装依赖：

```bash
npm install
```

启动开发服务：

```bash
npm run dev
```

前端默认访问地址：

```text
http://localhost
```

开发环境中，前端通过 `/dev-api` 代理到后端 `http://localhost:8080`。

## 前端打包

测试环境打包：

```bash
npm run build:stage
```

生产环境打包：

```bash
npm run build:prod
```

打包产物默认生成在：

```text
ruoyi-ui/dist
```

## 主要接口

后台管理接口：

```text
/system/学生
/system/教师
/system/教室
/system/课程
/system/课程计划
/system/选课记录
/system/课程计划vo
/system/选课记录vo
```

学生选课接口：

```text
/loginUser/login1
/loginUser/getAllCoursePlan
/loginUser/getCoursePlan
/loginUser/selectCourse
```

## 默认账号

若依初始化脚本通常包含默认管理员账号：

```text
账号：admin
密码：admin123
```

如数据库数据已修改，请以实际账号为准。

业务 SQL 中包含少量学生、教师、教室、课程、课程计划和选课记录示例数据，可用于本地快速验证功能。

## 部署说明

后端可使用项目提供的脚本管理服务：

```bash
# Linux
./ry.sh start
./ry.sh stop
./ry.sh restart
./ry.sh status
```

Windows 可使用：

```text
ry.bat
bin/run.bat
bin/package.bat
```

前端生产环境构建后，将 `ruoyi-ui/dist` 部署到 Nginx 或其他静态资源服务器，并配置接口反向代理到后端服务。

## 上传 GitHub 前建议

- 不提交 `node_modules`、`target`、`dist` 等构建产物
- 检查数据库账号、密码、Token 密钥等敏感配置
- 首次部署请按顺序导入 `sql` 目录下的初始化脚本
- 如需公开部署，请修改默认账号密码、Druid 监控密码和 Token 密钥

## License

本项目基于 MIT License 开源。
