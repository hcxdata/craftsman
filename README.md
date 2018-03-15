# 简易开发框架 
### 技术选项
- Spring4 
- Hibernate4 
- MyBatis3 
- Bootstrap3 
- AngularJS1.3

### 构建工具
- 后台: pom
- 前台: 无

### 框架能力
- flyway数据更新管理
- 多数据源
- jpa 与 mybatis 同时进行数据操作
- 分页功能
- 后台抛出错误前端自动提醒
- 安全验证

### 已开发功能
* 系统管理
  * 数据字典管理
  * 系统参数管理
* 权限管理
  * 菜单管理
  * 角色管理
  * 用户管理
  
### 启动方式
1. 创建空数据库
2. 更改craftsman-platform/src/main/resources/db.propertiesdb.properties文件内容
3. 启动应用即可，初始化登录用户为 admin/admin