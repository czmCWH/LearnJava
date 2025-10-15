-- =========================== 1、DQL 基本查询 ===========================
-- 1、查询指定字段
select username, name, gender, entrydate from emp;

-- 2、通配符查询，* 代表查询所有字段
-- 不推荐使用 * 查询所有字段，不直观、效率低；推荐：罗列出查询的所有字段
select * from emp;

-- 3、为查询字段设置别名
select username as 用户名, entrydate as 入职日期 from emp;
select username 用户名, entrydate 入职日期 from emp;
select username 用户名, entrydate '入职 日期' from emp;    -- 注意：取的别名中间有空格必须加引号，通常不用加引号

-- 4、查询入职日期，不要重复
-- 去除 username + entrydate 的重复记录
select distinct entrydate, username from emp;


-- =========================== 2、DQL-条件查询 ===========================
select * from emp where name = '小明';

select * from emp where salary > 5000;

-- 判断 null 值不能用 == ，需要用 is、is not
select * from emp where entrydate is null;
select * from emp where entrydate is not null;

-- 查询 name 不等于 小明
select * from emp where name != '小明';
select * from emp where name <> '小明';

-- 范围查询 entrydate 闭区间范围：'2010-01-01'～ '2035-01-01'
select * from emp where entrydate >= '2010-01-01' and entrydate <= '2035-01-01';
select * from emp where entrydate between '2010-01-01' and '2035-01-01';

-- 多条件查询，需要使用逻辑运算符（and、or）组装条件
select * from emp where entrydate between '2010-01-01' and '2035-01-01' and name = '小明';
-- 可以把某个查询条件括号起来，这样语句更清晰，效果是一样的
select * from emp where (entrydate between '2010-01-01' and '2035-01-01') and name = '小明';

select * from emp where name = '小明' or name = '张剑' or name = 'xiaoming';
select * from emp where name in ('张剑', '小明');

-- 模糊匹配查询，_ 通配符，代表单个字符；% 通配符，代表任意个字符
select * from emp where name like '__';
select * from emp where name like '%剑%';


-- =========================== 3、DQL-分组查询 ===========================

-- DQL-分组查询 - 聚合函数
-- 注意，所有的聚合参数不参与 null 值的统计

-- 1、count 统计

-- 统计 emp 表中 job 不为 null 的有多少条数据
select count(job) from emp;
-- 统计一共有多少条数据
select count(1) from emp;
select count(*) from emp;   -- 推荐使用，因为底层做了优化，性能最高。

-- 2、avg 平均数
-- 统计 emp 表中所有 salary 的平均值
select avg(salary) from emp;

-- 3、min 最小值
-- 统计 emp 表中所有 salary 的最小值、最大值
select min(salary) from emp;
select max(salary) from emp;

-- 4、sum 求和
-- 统计 emp 表中所有 salary 的和
select sum(salary) from emp;


-- DQL-分组查询
-- 1、根据性别分组查询
-- ⚠️ 分组查询时，select 后面的字段列表只支持 分组字段 or 聚合函数，其它字段没有意义！！！
select count(*) from emp group by gender;
select gender, count(*) from emp group by gender;

-- 2、先根据条件查询，再把查询结果组
-- 查询入职时间在 '2020-01-01'(包含) 以前的员工，并对结果根据职位分组，获取员工数量大于等于2的职位
select  job, count(*) from emp where entrydate <= '2020-01-01' group by job having count(*) >= 2;


-- -- =========================== 4、DQL-排序查询  ===========================
-- 根据薪资对用户信息进行升序、降序排序
-- 升序，从小到大，默认升序
select * from emp order by salary asc;
-- 降序，从大到小
select * from emp order by salary desc;

-- 多字段排序，只有当第一个字段值相同时，才会根据第二个字段进行排序
select * from emp order by entrydate desc, update_time asc;


-- =========================== 5、DQL-分页查询  ===========================
-- 1、从起始索引0开始查询员工数，每页2条数据
select * from emp limit 0, 2;

-- 2、查询 第1页，每页2条
select * from emp limit 0, 2;
select * from emp limit 2;  -- 第一页可以省略起始索引

-- 3、查询 第2页，每页2条
select * from emp limit 2, 2;

-- 3、查询 第3页，每页2条
select * from emp limit 4, 2;

-- 注意：起始索引 = (查询页码 - 1) * 每页显示记录数