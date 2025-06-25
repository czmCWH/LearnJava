-- ==================================== 1、一对多关系，物理外键约束 ===================================
-- 创建表
create table dept(
    id int unsigned primary key auto_increment comment 'ID，主键',
    name varchar(10) not null unique comment '部门名称' ,
    create_time datetime default null comment '创建时间',
    update_time datetime default null comment '修改时间'
) comment '部门表';

-- 插入表数据
insert into dept values (1, '学工部', '2025-05-27 09:29:40', '2025-05-27 09:29:40'),
                        (2, '教研部', '2025-05-27 09:29:40', '2025-05-27 09:29:40'),
                        (3, '咨询部', '2025-05-27 09:29:40', '2025-05-27 09:29:40'),
                        (4, '就业部', '2025-05-27 09:29:40', '2025-05-27 09:29:40'),
                        (5, '人事部', '2025-05-27 09:29:40', '2025-05-27 09:29:40'),
                        (15, '行政部', '2025-05-27 09:30:20', '2025-05-27 09:30:20');

-- 创建表
create table emp(
    id          int unsigned primary key auto_increment comment 'ID 主键',
    username    varchar(20) not null unique  comment '用户名',
    password    varchar(50) default  '123456' comment '密码',
    name        varchar(10) not null comment '姓名',
    gender      tinyint unsigned not null comment '性别，1：男，2：女',
    phone       char(11) not null unique comment '手机号',
    job         tinyint unsigned comment '职位， 1班主任，2讲师，3学工主管，4教研主管，5咨询师',
    salary      int unsigned comment '薪资',
    image       varchar(300) comment '头像',
    entry_date  date comment '入职日期',
    create_time datetime comment '创建日期',
    update_time datetime comment '更新日期',
    dept_id     int unsigned comment '部门ID'
) comment '员工表';

-- 添加外键
alter table emp add constraint fk_emp_dept_id foreign key (dept_id) references dept (id);

-- ==================================== 2、一对一关系，单表拆分 ===================================
-- 创建 user 表
create table tb_user(
    id int unsigned primary key auto_increment comment 'ID',
    name varchar(10) not null comment '姓名',
    gender tinyint unsigned not null comment '性别，1 男  2 女',
    phone char(11) comment '手机号',
    degree varchar(10) comment '学历'
) comment '用户信息表';

insert into tb_user values (1, '张读书', 1, '12345678932', '博士'),
                           (2, '李学习', 2, '12345678933', '研究生'),
                           (3, '王自律', 1, '12345678934', '本科'),
                           (4, '赵偷懒', 2, '12345678935', '大专');

-- 创建 user_card 表
create table tb_user_card(
    id           int unsigned primary key auto_increment comment 'ID',
    nationality  varchar(10) not null comment '民族',
    birthday     date not null comment '生日',
    idCard       char(18) not null comment '身份证号',
    issued       varchar(20) not null comment '签发机关',
    expire_begin date not null comment '有效期限-开始',
    expire_end   date not null comment '有效期限=结束',
    user_id      int unsigned not null unique comment '用户ID',
    constraint fk_user_id foreign key (user_id) references tb_user (id)
) comment '用户信息表';

insert into tb_user_card values (1,'汉', '1983-11-08', '123122212927327132', '大清坤宁宫', '1900-10-23', '2010-10-22',1);


-- ==================================== 3、多对多关系，在多张表的基础上建立第三张中间表 ===================================
-- 学生表
create table tb_student(
    id int auto_increment primary key comment '主键ID',
    name varchar(10) comment '姓名',
    no varchar(10) comment '学号'
) comment '学生表';

insert into tb_student(name, no) values ('小明', '200001'), ('小李', '200001');

-- 课程表
create table tb_course(
    id int auto_increment primary key comment '主键ID',
    name varchar(10) comment '课程名称'
) comment '课程表';

insert into tb_course(name) values ('Java'), ('PHP'), ('C'), ('C++');

-- 中间表
create table tb_student_course(
    id int auto_increment primary key comment '主键',
    student_id int not null comment '学生ID',
    course_id int not null comment '课程ID',
    constraint fk_studentid foreign key (student_id) references tb_student(id),
    constraint fk_courseid foreign key (course_id) references tb_course(id)
) comment '学生课程中间表';

insert into tb_student_course(student_id, course_id) values (1,1),  (1,3),  (1,2);


