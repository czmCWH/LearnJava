# DML 操作表数据
DML英文全称是Data Manipulation Language(数据操作语言)，用来对数据库中表的数据记录进行增、删、改操作。

## 1、添加数据(INSERT)

语法：

```mysql
-- 指定字段添加数据
insert into 表名(字段名1，字段名2) values (值1，值2);

-- 全部字段添加数据
insert into 表名 values (值1，值2，...);

--  批量添加数据(指定字段)
insert into 表名 (字段名1，字段名2) values (值1，值2),  (值1，值2);

-- 批量添加数据(全部字段)
insert into 表名 values (值1，值2，...)，(值1，值2,..);
```

## 2、修改数据(UPDATE)

语法：

```mysql
-- 修改数据
update 表名 set 字段名1 = 值1, 字段名2 = 值2, ...[ where 条件 ];
```

案例：

```mysql
update emp set name = "张飞" where username = "Jan2";

-- 修改表中某个字段所有的值，注意，此方式无法执行！！！
set sql_safe_updates=1;
update emp set entrydate = '2028-03-25';
```


## 3、删除数据(DELETE)

语法：

```mysql
delete from 表名 [where 条件];
```

案例：

```mysql
delete from emp where name = "张飞";
-- 删除表中的所有数据
-- delete from emp;
```

DELETE 语句不能删除某一个字段的值(如果要操作，可以使用UPDATE，将该字段的值置为NULL)。