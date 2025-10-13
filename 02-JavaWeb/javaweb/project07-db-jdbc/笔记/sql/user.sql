-- 项目准备，在 czm_db01 数据库中创建 User 表并添加数据
create table user(
    id int unsigned primary key auto_increment comment 'ID 主键',
    username varchar(20) comment '用户名',
    password varchar(32) comment '密码',
    name varchar(10) comment '姓名',
    age tinyint unsigned comment '年龄'
) comment '用户表';

insert into user values (1, 'xiongda', '123456', '熊大', 18),
                        (2, 'xionger', '123456', '熊二', 18),
                        (3, 'guangtouqiang', '123456', '光头强', 18),
                        (4, 'jingcha', '123456', '警察', 35),
                        (5, 'siji', '123456', '司机', 32);