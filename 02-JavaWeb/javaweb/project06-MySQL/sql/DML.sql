-- DML：数据操作语言

-- 1、插入数据
-- 为 emp 表指定字段插入数据
insert into emp(username, name, gender, phone) values
    ("Jan", "张剑", "男", "12312312312");

-- 为 emp 表所有字段插入值
insert into emp values (null, "xiaoming", "小明", "男", "12345678909", 2, 6000, '001.jpg', '2000-01-01', now(), now());

-- 为 emp 表指定字段批量插入数据
insert into emp(username, name, gender, phone) values
                                                   ("Jan2", "张剑2", "男", "12312312313"),
                                                   ("Jan3", "张剑3", "男", "12312312314");

-- 2、修改数据
update emp set name = "张飞" where username = "Jan2";
-- 修改表中某个字段所有的值
update emp set entrydate = '2020-02-25';

-- 3、删除表中的数据
delete from emp where name = "张飞";
-- 删除表中的所有数据
delete from emp;
