# SQL 简介

SQL：一门操作关系型数据库的编程语言，定义操作所有关系型数据库的统一标准。

## SQL 分类：

* DDL，Data Definition Language，数据定义语言，用来定义数据库对象(数据库，表，字段)。（对结构操作）

* DML，Data Manipulation Language，数据操作语言，用来对数据库表中的数据进行增删改。（对数据操作）

* DQL，Data Query Language，数据查询语言，用来查询数据库中表的记录。

* DCL，Data Control Language，数据控制语言，用来创建数据库用户、控制数据库的访问权限。--- DBA 人员使用

## SQL 通用语法

SQL 语句可以单行或多行书写，以 `;分号` 结尾。

SQL 语句可以使用 空格/缩进 来增强语句的可读性。

MySQL 数据库的 SQL语句 **不区分大小写**。

## sql 注释
1、单行注释：`-- 注释内容（通用的）` 或 `# 注释内容（MySQL独有）`。
2、多行注释：`/* 注释内容 */`