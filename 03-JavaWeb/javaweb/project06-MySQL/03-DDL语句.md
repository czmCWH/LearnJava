# DDL 语句定义数据库对象

## 1、数据库操作

如果是在【终端】环境，需先登录数据库，然后执行如下命令：

```mysql
-- 查询所有数据库
mysql> show databases

-- 查询当前数据库
mysql> select database();

-- 使用/切换数据库
mysql> use 数据库名;

-- 创建数据库
mysql> create database [if not exists] 数据库名 [default charset utf8mb4]；

-- 删除数据库
mysql> drop database [if exists] 数据库名;
```

* ⚠️ 注意：
* 上述语法中的 `database`，也可以替换成 `schema`。(`create schema`)
* `MySQL8` 版本中，默认字符集为 `utf8mb4`。


## 2、创建表

```mysql
create table 表名称(
    字段名1 字段类型 [约束] [comment 字段名1注释],
    ...
    字段名2 字段类型 [约束] [comment 字段名2注释]
)[comment 表注释]
```

### DDL 表约束

* 约束：约束是作用于表中 字段 上的规则，用于限制存储在表中的数据；
* 目的：保证数据库中数据的正确性、有效性和完整性。

`not null`，非空约束，限制该字段值不能为null。
`unique`，唯一约束，保证字段的所有数据都是唯一、不重复的。
`primary key`，主键约束，主键是一行数据的唯一标识，要求非空且唯一。
`primary key auto_increment`，主键自增。如果删掉一条数据后，自增的值依然被占用。
`default`，默认约束，保存数据时，如果未指定该字段值，则采用默认值。
`foreign key`，外键约束，让两张表的数据建立连接，保证数据的一致性和完整性。

### 数据类型

MySQL中的数据类型有很多，主要分为三类：数值类型、字符串类型、日期时间类型。

* 数值类型：`unsigned` 表示无符号类型，表示只能取0及正数；不加默认是 `signed`，表示可以取负数；
* 字符串类型：`char` 存储确定字符串长度；`varchar` 存储不确定的字符串长度。
* 日期时间类型： `date、datetime`


⚠️，从学习资料里面查询下载！！！


```mysql
create table user(
    id int primary key comment '唯一标识',
    username varchar(20) not null unique comment '用户名',
    name varchar(20) not null comment '姓名',
    age int comment '年龄',
    gender char(1) default '男' comment '性别'
) comment '用户信息表';
```

### 如何设计表结构?
a. 阅读页面原型及需求文档;
b. 分析表中包含的字段、字段的类型、约束;
c. 创建表结构，添加基础字段(id,create time,update time);

如下案例，创建一个员工表：

```mysql
create table emp(
    id int unsigned primary key auto_increment comment '主键ID',
    username varchar(20) not null unique comment '用户名',
    name varchar(20) not null comment '姓名',
    gender char(1) not null default '男' comment '性别',
    phone char(11) not null unique comment '手机号',
    job tinyint comment '职位: 1-班主任,2-讲师,3-学生主管,4-教研主管,5-咨询师',
    salary float(7, 2) comment '薪资',
    image varchar(100) comment '图片路径',
    entrydate date comment '入职日期',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '更新时间'
) comment '员工表';
```

## 3、操作表，不常用

```mysql
-- 查询当前数据库的所有表
show tables;

-- 查询表结构
desc user;

-- 查询创建表语句
show create table user;

-- 添加字段
alter table user add qq varchar(20) unique comment '用户qq号';
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
```
