# DQL 查询表数据

DQL 英文全称是 Data Query Language(数据查询语言)，用来查询数据库表中的记录。

完整的DQL语句语法：

```mysql
select 字段列表 from 表名称 
where 条件列表 
group by 分组字段列表 
having 分组后条件列表 
order by 排序字段列表 
limit 分页参数;
```

## 1、DOL-基本查询

```mysql
-- 查询多个字段
select 字段1,字段2,字段3 from 表名;

-- 查询所有字段(通配符) --- 不推荐，效率低
select * from 表名;

-- 为查询字段设置别名 --- as 关键字可以省略
select 字段1 [as 别名1]，字段2 [as 别名2] from 表名;

-- 去除重复记录
select distinct 字段列表 from 表名;
```

案例：

```mysql
-- 1、查询多个字段
select username, name, gender, entrydate from emp;

-- 2、查询所有字段（通配符查询）
select * from emp;

-- 3、为查询字段设置别名
select username as 用户名, entrydate as 入职日期 from emp;
select username 用户名, entrydate 入职日期 from emp;
select username 用户名, entrydate '入职 日期' from emp;

-- 4、查询入职日期，不要重复
-- ⚠️⚠️⚠️去除 username + entrydate 的重复记录
select distinct entrydate, username from emp;
```

## 2、DQL-条件查询

`select 字段列表 from 表名 where 条件列表;`

* 比较运算符

~~~
>、>=、<，<=，=

<> 或 !=，不等于

between ... and ...，在某个范围之内（闭区间）

in(...)，在in之后的列表中的值，多选一

like 占位符，模糊匹配(_匹配单个字符，%匹配任意个字符)

is null，是null
~~~

* 逻辑运算符

~~~
&& 或 and，并且(多个条件同时成立)

or 或 ||，或者(多个条件任意一个成立)

not 或 !，非 ，不是
~~~

案例：
```mysql
select * from emp where name = '小明';

select * from emp where salary > 5000;

select * from emp where entrydate is null;
select * from emp where entrydate is not null;

select * from emp where name != '小明';
select * from emp where name <> '小明';

select * from emp where entrydate >= '2010-01-01' and entrydate <= '2035-01-01';
select * from emp where entrydate between '2010-01-01' and '2035-01-01';

select * from emp where entrydate between '2010-01-01' and '2035-01-01' and name = '小明';

select * from emp where name = '小明' or name = '张剑';
select * from emp where name in ('张剑', '小明');

select * from emp where name like '__';

select * from emp where name like '%剑%';
```

## 3、DQL-分组查询

### 聚合函数：将一列数据作为一个整体，进行纵向计算。

~~~
count，统计数量
max，最大值
min，最小值
avg，平均值
sum，求和
~~~

注意：
* null 值不参与所有聚合函数的运算
* 统计数量可以使用: `count(*)、count(字段)、count(常量)、推荐使用count(*)`

案例：
```mysql
-- job 不为 null 的有多少条
select count(job) from emp;
-- 统计一共有多少条数据，推荐使用
select count(*) from emp;

-- 2、avg 平均数
select avg(salary) from emp;

-- 3、min 最小值
select min(salary) from emp;
select max(salary) from emp;

-- 4、sum 求和
select sum(salary) from emp;
```

### 分组查询语法：

`select 字段名列表 from 表名 [where 条件列表] group by 分组字段名 [having 分组后过滤条件]`

where 与 having 的区别：
1、执行时机不同：where 是分组之前进行过滤，不满足 where 条件，不参与分组；而 having 是分组之后对结果进行过滤；
2、判断条件不同：where 不能对聚合函数进行判断，而 having 可以；

> 注意：
> 1、分组之后，查询的字段一般为聚合函数和分组字段，查询其他字段无任何意义。
> 2、执行顺序：where > 聚合函数 > having 。

案例：

```mysql
-- 1、根据性别分组查询
-- 分组查询只支持查询分组字段以及聚合函数，其它字段没有意义！
select count(*) from emp group by gender;
select gender, count(*) from emp group by gender;

-- 2、先根据条件查询，再把查询结果组
select  job, count(*) from emp where entrydate <= '2020-01-01' group by job having count(*) >= 2;


-- -- =========================== DQL-排序查询  ===========================
select * from emp order by salary asc;
select * from emp order by salary desc;

-- 多字段排序，只有当第一个字段值相同时，才会根据第二个字段进行排序
select * from emp order by entrydate desc, update_time asc;
```


## 4、DQL-排序查询

语法：

```mysql
select 字段列表 from 表名 [where 条件列表] [group by 分组字段名 having 分组后过滤条件] order by 排序字段 排序方式;
```
* 排序方式：升序(asc)、降序(desc)；默认为 升序(asc)，可以不写
* 如果是多字段排序，当第一个字段值相同时，才会根据第二个字段进行排序

## 5、DQL-分页查询

语法：

```mysql
select 字段列表 from 表名 [where 条件列表] [group by 分组字段名 having 分组后过滤条件] [order by 排序字段 排序方式] limit 起始索引, 查询记录数;
```

说明:
1. 起始索引从0开始，起始索引 = (查询页码 - 1) * 每页显示记录数
2. 分页查询是数据库的方言，不同的数据库有不同的实现，MySQL 中是 LIMIT，其它数据库可能不一样。
3. 如果查询的是第一页数据，起始索引可以省略，直接简写为 limit 10。

案例：

```mysql
-- 1、从起始索引0开始查询员工数，每页2条数据
select * from emp limit 0, 2;

-- 2、查询 第1页，每页2条
select * from emp limit 0, 2;
select * from emp limit 2;  -- 第一页可以省略起始索引

-- 3、查询 第2页，每页2条
select * from emp limit 2, 2;

-- 3、查询 第3页，每页2条
select * from emp limit 4, 2;
```

