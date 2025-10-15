# DML 操作表数据
DML英文全称是 `Data Manipulation Language` (数据操作语言)，用来对数据库中表的数据记录进行增、删、改操作。

## 1、添加数据(INSERT)

语法：

```mysql
-- 指定字段添加数据
insert into 表名(字段名1，字段名2) values (值1，值2);

-- 全部字段添加数据
insert into 表名 values (值1，值2，...);

--  批量添加数据(指定字段)
insert into 表名 (字段名1，字段名2) values (值1，值2), (值1，值2);

-- 批量添加数据(全部字段)
insert into 表名 values (值1，值2，...)，(值1，值2,..);
```

> 插入数据注意事项：
> 1、插入数据时，指定的字段顺序需要与值的顺序是一一对应的；
> 2、字符串和日期型数据应该包含在引号中(单引号、双引号都可以，推荐单引号)；
> 3、插入的数据大小，应该在字段的规定范围内；


## 2、修改数据(UPDATE)

语法：

```mysql
-- 修改数据
update 表名 set 字段名1 = 值1, 字段名2 = 值2, ... [ where 条件 ];
```

> 如果不加 where 条件，则整张表中的数据都将会被修改。

案例：

```mysql
update emp set name = '张飞', username = 'zhangfei' where username = 'Jan2';

-- 修改表中某个字段所有的值，注意，此方式会提示无法执行，可以点击强制执行！！！
update emp set entrydate = '2028-03-25';
```

## 3、删除表中的数据(DELETE)

语法：

```mysql
delete from 表名 [where 条件];
```
> 如果不加 where 条件，会删除整张表的所有数据。
> DELETE 语句不能删除某一个字段的值(如果要操作，可以使用 UPDATE，将该字段的值置为NULL)。

案例：

```mysql
delete from emp where name = "张飞";
-- 删除表中的所有数据
-- delete from emp;
```
