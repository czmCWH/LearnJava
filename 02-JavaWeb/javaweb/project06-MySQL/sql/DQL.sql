-- =========================== 1、DQL 基本查询 ===========================
-- 1、查询多个字段
select username, name, gender, entrydate from emp;

-- 2、查询所有字段（通配符查询）不推荐，效率低
select * from emp;

-- 3、为查询字段设置别名
select username as 用户名, entrydate as 入职日期 from emp;
select username 用户名, entrydate 入职日期 from emp;
select username 用户名, entrydate '入职 日期' from emp;    -- 中间有空格需加引号

-- 4、查询入职日期，不要重复
-- 去除 username + entrydate 的重复记录
select distinct entrydate, username from emp;


-- =========================== 2、DQL-条件查询 ===========================
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

-- =========================== DQL-分组查询 ===========================

-- DQL-分组查询 - 聚合函数
-- 1、count 统计

-- job 不为 null 的有多少条
select count(job) from emp;
-- 统计一共有多少条数据
select count(1) from emp;
select count(*) from emp;   -- 推荐使用，因为底层做了优化

-- 2、avg 平均数
select avg(salary) from emp;

-- 3、min 最小值
select min(salary) from emp;
select max(salary) from emp;

-- 4、sum 求和
select sum(salary) from emp;


-- DQL-分组查询
-- 1、根据性别分组查询
-- 分组查询只支持查询分组字段以及聚合函数，其它字段没有意义！
select count(*) from emp group by gender;
select gender, count(*) from emp group by gender;

-- 2、先根据条件查询，再把查询结果组
select  job, count(*) from emp where entrydate <= '2020-01-01' group by job having count(*) >= 2;


-- -- =========================== DQL-排序查询  ===========================
-- 升序，从小到大，默认升序
select * from emp order by salary asc;
-- 降序，从大到小
select * from emp order by salary desc;

-- 多字段排序，只有当第一个字段值相同时，才会根据第二个字段进行排序
select * from emp order by entrydate desc, update_time asc;


-- =========================== DQL-分页查询  ===========================
-- 1、从起始索引0开始查询员工数，每页2条数据
select * from emp limit 0, 2;

-- 2、查询 第1页，每页2条
select * from emp limit 0, 2;
select * from emp limit 2;  -- 第一页可以省略起始索引

-- 3、查询 第2页，每页2条
select * from emp limit 2, 2;

-- 3、查询 第3页，每页2条
select * from emp limit 4, 2;