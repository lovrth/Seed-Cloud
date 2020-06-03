### SEED-Cloud 微服务权限系统

SEED-Cloud是一款使用Spring Cloud Greenwich.SR1、Spring Cloud OAuth2构建的低耦合权限管理系统该系统具有如下特点：

序号 | 特点
---|---
1 | 前后端分离架构，客户端和服务端纯Token交互； 
2 | 认证服务器与资源服务器分离，方便接入自己的微服务系统
3 | 集成SpringBootAdmin
4 | 网关限流，网关日志
5 | 认证授权，数据权限，前后端参数校验等
6 | Doc Starter，几行配置自动生成系统api接口文档
7 | OAuth2 刷新令牌模式
8 | 微服务Docker化，使用Docker Compose一键部署

### 演示地址

本地部署账号密码：

账号 | 密码| 权限
---|---|---
mrbird | 1234qwer |超级管理员，拥有所有增删改查权限

SBA平台相关账号密码：

平台 | 账号| 密码
---|---|---
seed-admin | seed |123456


### 服务模块

seed模块：

服务名称 | 端口 | 描述
---|---|---
seed-register|8001|微服务注册中心
seed-auth| 8101| 微服务认证服务器 
seed-server-system| 8201 | 微服务子系统，系统核心模块
seed-server-test|8202 | 微服务子系统，Demo模块
seed-gateway|8301|微服务网关
seed-monitor|8401|微服务监控子系统


第三方模块：

服务名称 | 端口 | 描述
---|---|---
MySQL| 3306 |MySQL 数据库 
Redis| 6379 | K-V 缓存数据库 
Elasticsearch|9200 | 日志存储
Logstash|4560|日志收集
Kibana|5601|日志展示

