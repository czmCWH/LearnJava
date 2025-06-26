-- 项目案例：实现员工列表查询，需要操作3张表：dept、emp、emp_expr

-- 案例：创建员工工作经历表
# 员工表 和 工作经历表 => 1 : N；
# 逻辑外键字段为 员工ID，注意不需要添加物理外键约束；
# 创建时间、更新时间 字段可以不需要，因为修改工作经历表时，一般是借助员工信息表来更新的。
create table emp_expr(
    id      int unsigned primary key auto_increment comment '主键ID',
    emp_id  int unsigned comment '员工ID',
    begin   date comment '开始日期',
    end     date comment '结束日期',
    company varchar(50) comment '公司名称',
    job     varchar(50) comment '职位'
) comment '员工工作经历表';



