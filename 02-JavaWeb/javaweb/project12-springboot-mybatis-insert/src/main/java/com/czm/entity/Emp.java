package com.czm.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工信息
 */
@Data
public class Emp {
    private Integer id; // id 主键
    private String username;    // 用户名
    private String password;    // 密码
    private String name;        // 姓名
    private Integer gender;     // 性别：1男；2女
    private String phone;       // 手机号
    private Integer job;        // 职位：1：班主任；2:讲师；3:学工主管；4:教研主管；5:咨询部；
    private Integer salary;     // 薪资
    private String image;       // 头像
    private LocalDate entryDate;    // 入职日期
    private Integer deptId;     // 关联部门 ID
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // 封装部门名称
    private String deptName;

    // 封装工作经历列表数据
    private List<EmpExpr> exprList;
}
