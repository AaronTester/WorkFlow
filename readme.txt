1.模块划分
a.flow-center
  用户基本功能节点
b.flow-config
  流程配置中心
c.flow-entry-api
  具体业务实体类
d.flow-message
  消息中心
e.flow-shcheduler
  定时任务中心
f.flow-report
  报表功能
2.jar版本依赖
  <maven.compiler.source>1.8</maven.compiler.source>
  <maven.compiler.target>1.8</maven.compiler.target>
  <junit.version>4.12</junit.version>
  <log4j.version>1.2.17</log4j.version>
  <lombok.version>1.16.18</lombok.version>
  <mysql.version>5.1.48</mysql.version>
  <druid.version>1.1.16</druid.version>
  <mybatis.spring.boot.version>1.3.0</mybatis.spring.boot.version>
3.flow-entry-api具体实体类
  User:用户对象 对应表：tbl_user
  Group：部门对象 对应表：tbl_group
  Role:角色对象 对应表：tbl_role
  用户角色对应表：tbl_user_role_mapping
  角色功能对应表
4.整合fastjson是添加配置类JsonConfig
5.增加日志配置 logback.xml
