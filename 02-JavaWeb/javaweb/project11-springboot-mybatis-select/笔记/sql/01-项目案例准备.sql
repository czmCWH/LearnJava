/*
项目案例：设计 员工模块 的表结构
步骤：
    1、阅读页面原型及需求文档，分析各个模块涉及到的表结构，及表结构之间的关系。
    2、根据页面原型及需求文档，分析各个表结构中具体的字段及约束。
表结构之间的关系：
    涉及三张表：dept（部门表）、emp（员工表）、emp_expr（工作经历表）
    dept(1) ---> emp(n)，一个部门会有多个员工
    emp(1) ---> emp_expr(n)，一个员工会有多段工作经历

dept、emp 相关的 sql 已经在 `02-多表关系` 中描述了。 如下所示，实现 员工工作经历表 表结构设计步骤：
 1、设计表结构的业务字段
    基本业务字段：begin、end、company、job

 2、设计对应的基础字段
    id，主键
    create_time
    update_time

 3、外键字段
    emp_id 员工ID

 */
create table emp_expr(
    id      int unsigned primary key auto_increment comment '主键ID',

    begin   date comment '开始日期',
    end     date comment '结束日期',
    company varchar(50) comment '公司名称',
    job     varchar(50) comment '职位',

    emp_id  int unsigned comment '员工ID'

    /*
      因为修改工作经历表是借助员工信息来更新的，工作经历信息是属于员工信息的附属信息，所以 创建时间、更新时间 字段可以不需要。
        create_time     datetime comment '创建时间',
        update_time     datetime comment '修改时间'
     */
) comment '员工工作经历表';



