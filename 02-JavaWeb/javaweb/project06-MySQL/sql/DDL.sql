-- ********************************** DDL 数据库操作
-- 写完sql语句后，cmd + 回车 开始执行

-- 1、创建数据库
create database czm_db01;

-- 2、查看当前数据库
select database();

-- 3、使用数据库
use czm_db01;

-- ********************************** DDL 创建表
-- 1、创建表
/*
create table user(
    id int comment '唯一标识',
    username varchar(20) comment '用户名',
    name varchar(20) comment '姓名',
    age int comment '年龄',
    gender char(1) comment '性别'
) comment '用户信息表';
*/

-- 2、创建带约束的表
create table user(
                     id int unsigned primary key comment '唯一标识',
                     username varchar(20) not null unique comment '用户名',
                     name varchar(20) not null comment '姓名',
                     age tinyint unsigned comment '年龄',
                     gender char(1) default '男' comment '性别'
) comment '用户信息表';

-- 3、案例-创建员工表
create table emp(
                    id int unsigned primary key auto_increment comment '主键ID',
                    username varchar(20) not null unique comment '用户名',
                    name varchar(20) not null comment '姓名',
                    gender char(1) not null default '男' comment '性别：男、女',
                    phone char(11) not null unique comment '手机号',
                    job tinyint unsigned comment '职位: 1-班主任,2-讲师,3-学生主管,4-教研主管,5-咨询师',
                    salary float(7, 2) comment '薪资',
                    image varchar(100) comment '图片路径',
                    entrydate date comment '入职日期',
                    create_time datetime not null comment '创建时间',
                    update_time datetime not null comment '更新时间'
) comment '员工表';


-- ********************************** DDL 表操作（非重点）

-- 查询当前数据库的所有表
show tables;

-- 查询表结构
desc user;

-- 查询创建表语句
show create table user;

-- 添加字段
alter table user add qq varchar(20) unique comment '用户qq号';
alter table user add password varchar(10) default '123456' comment '用户密码';
-- 修改字段类型
alter table user modify qq char(20);
-- 修改字段名与字段类型
alter table user change qq qq_new varchar(20) comment '用户qq号';
-- 删除字段
alter table user drop column qq_new;

-- 修改表名
alter table user to new_user;

-- 删除表，表中的全部数据也会被删除！！！
-- drop table if exists user;

