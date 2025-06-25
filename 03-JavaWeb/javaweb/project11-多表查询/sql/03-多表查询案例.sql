-- ===================== 1、多表查询 ======================
-- 1、数据准备

# 部门表
create table dept(
    id          int unsigned primary key auto_increment comment 'ID，主键',
    name        varchar(10) not null unique comment '部门名称',
    create_time datetime comment '创建时间',
    update_time datetime comment '修改时间'
) comment '部门表';

insert into dept (id, name, create_time, update_time) values
        (1, '学工部', now(), now()),
        (2, '教研部', now(), now()),
        (3, '咨询部', now(), now()),
        (4, '就业部', now(), now()),
        (5, '人事部', now(), now());

-- 员工表
create table emp(
    id              int unsigned primary key auto_increment comment 'ID，主键',
    username        varchar(20) not null unique comment '用户名',
    password        varchar(32) not null comment '密码',
    name            varchar(10) not null comment '姓名',
    gender          tinyint unsigned not null comment '性别， 1 男，2 女',
    phone           char(11) not null unique comment '手机号',
    job             tinyint unsigned comment '职位，1 班主任，2 讲师，3 学工主管，4 教研主管 5 咨询师',
    salary          int unsigned comment '薪资',
    image           varchar(300) comment '头像',
    entry_date      date comment '入职日期',
    dept_id         int unsigned comment '关联的部门ID',
    create_time     datetime comment '创建时间',
    update_time     datetime comment '修改时间'
) comment '员工表';

insert into emp values
                    ( 1,'zhangsan', '123', '张三', 1, '13800138001', 1, 12000, 'avatar1.jpg', '2020-05-10', 1, now(), now()),
                    (2,'lisi', '123', '李四', 1, '13800138002', 2, 15000, 'avatar2.jpg', '2019-08-15', 2, now(), now()),
                    (3,'wangwu', '122', '王五', 1, '13800138003', 3, 18000, 'avatar3.jpg', '2018-03-22', 3, now(), now()),
                    (4,'zhaoliu', '123', '赵六', 1, '13800138004', 4, 20000, 'avatar4.jpg', '2021-01-05', 4, now(), now()),
                    ( 5,'xiaohong', '123', '小红', 2, '13800138005', 5, 10000, 'avatar5.jpg', '2022-07-18', 5, now(), now()),
                    (6,'xiaolan', '123', '小兰', 2, '13800138006', 1, 13000, 'avatar6.jpg', '2020-11-30', 1, now(), now()),
                    (7,'xiaoli', '123', '小丽', 2, '13800138007', 2, 16000, 'avatar7.jpg', '2019-04-12', 2, now(), now()),
                    (8,'xiaoming', '123', '小明', 1, '13800138008', 3, 19000, 'avatar8.jpg', '2021-09-25', 3, now(), now()),
                    (9,'xiaofang', '123', '小芳', 2, '13800138009', 4, 21000, 'avatar9.jpg', '2018-06-08', 4, now(), now()),
                    (10,'xiaogang', '123', '小刚', 1, '13800138010', 5, 11000, 'avatar10.jpg', '2023-02-14', 5, now(), now());


insert into emp values
                    (11,'fugui', '123', '富贵', 2, '13800138019', null, 21000, 'avatar11.jpg', '2018-06-08', null, now(), now()),
                    (12,'gouer', '123', '狗儿', 1, '13800138011', null, 11000, 'avatar12.jpg', '2023-02-14', null, now(), now());


-- ===================== 2、内连接多表查询 ======================
-- 1、查询所有员工的ID、姓名、所属部门
# 隐式内联接查询
select emp.id, emp.name, dept.name from emp, dept where emp.dept_id = dept.id;

# 显式内联接查询
select emp.id, emp.name, dept.name from emp inner join dept on emp.dept_id = dept.id;

-- 2、查询性别为男，薪资高于15000 的员工ID、姓名、所属部门
# 隐式内联接查询（常用）
# 可以给表取别名简化书写，一旦为表指定了别名，就要通过别名来指定字段名，而不能再使用表名了。
select t1.id, t1.name, t2.name, t1.salary from emp t1, dept t2 where t1.dept_id = t2.id and t1.gender = 2 and t1.salary > 15000;

# 显式内联接查询，消除笛卡尔积放在 on 后面；正常的条件查询放在 where  后面；
select t1.id, t1.name, t2.name, t1.salary from emp t1 join dept t2 on t1.dept_id = t2.id where t1.gender = 1 and t1.salary > 15000;


-- ===================== 3、外连接多表查询 ======================
-- 1、查询员工表 所有员工 的姓名、对应的部门名称（左外连接），没有部门的员工也会查询出来
# 查那张表的全部信息就写在 left 的左边
select emp.*, dept.name from emp left join dept on emp.dept_id = dept.id;

-- 2、查询部门表 所有部门名称 和对应员工名称，右外连接
select dept.*, emp.name from emp right join dept on emp.dept_id = dept.id;
# 改为左外
select dept.*, emp.name from dept left join emp on emp.dept_id = dept.id;

-- 3、查询工资高于 15000 的所有员工的姓名 和对应部门名称，左外连接
select emp.*, dept.name from emp left join dept on emp.dept_id = dept.id where emp.salary > 15000;


-- ===================== 4、子查询 ======================
-- 1、标量子查询
# 查询最早入职的员工信息
# 先查询最早入职的日期
select min(entry_date) from emp;
# 再把 最早入职的日期 作为条件去查询 员工信息
select * from emp where entry_date = (select min(entry_date) from emp);

# 查询在 小兰 入职之后员工信息
# 先查询小兰入职的日期
select entry_date from emp where emp.name = '小兰';
# 再把 小兰入职的日期 作为条件，查询其之后的信息
select * from emp where entry_date > (select entry_date from emp where emp.name = '小兰');

-- 2、列子查询
# 查询 教研部 和 咨询部 的所有员工信息
# 先查询 教研部 和 咨询部 的主键IDs
select id from dept where name = '教研部' or name = '咨询部';    -- 查询的结果是一列
# 再用 主键IDs 去匹配员工信息
select * from emp where dept_id in (select id from dept where name = '教研部' or name = '咨询部');

-- 3、行子查询
# 查询与 小兰 的薪资、职位都相同的员工的所有信息
# 先查询 小兰的薪资和职位
select salary, job from emp where name = '小兰';
# 再以 小兰的薪资和职位 为条件判断，查询其它员工
select * from emp where (salary, job) = (select salary, job from emp where name = '小兰');

-- 4、表子查询
# 查询入职日期是 2020-01-01 之后的员工信息，及其部门信息；
select * from emp where entry_date > '2020-01-01';
select t1.*, t2.name from (select * from emp where entry_date > '2020-01-01') t1, dept t2 where t1.dept_id = t2.id;

# 内连接查询也可以实现
select * from emp, dept where emp.dept_id = dept.id and emp.entry_date > '2020-01-01';


-- ===================== 5、查询案例 ======================
-- 1、查询"教研部”的"男性”员工，且在"2020-01-01”之后入职的员工信息
# 隐式内连接查询
select t1.*, t2.name from emp t1, dept t2 where t1.id = t2.id and t2.name = '教研部'
                                            and t1.gender = 1 and t1.entry_date > '2000-01-01';

-- 2、查询 工资 低于公司平均工资的 且 性别为男 的员工信息
# 标量子查询
select avg(emp.salary) from emp;
select * from emp where salary <= (select avg(emp.salary) from emp) and gender = 1;

-- 3、查询工资 低于本部门平均工资的员工信息
# 分组查询：根据部分分组，再求工资平均值。
select dept_id, avg(salary) from emp group by dept_id;
# 表子查询
select * from emp t1, (select dept_id, avg(salary) sa from emp group by dept_id) t2
                                                      where t1.dept_id = t2.dept_id and t1.salary < t2.sa;

-- 4、查询部门人数超过 2 人的部门名称
# 分组查询，每个部门下有多少人
select dept_id, count(*) cnt from emp group by emp.dept_id;
# 再查询部门人数超过2人的部门
select dept_id, count(*) cnt from emp group by emp.dept_id having cnt > 2;
# 连表查询，查询部门名称
select dept.name, dept_id, count(*) cnt from emp, dept where emp.dept_id = dept.id group by emp.dept_id having cnt > 2;


