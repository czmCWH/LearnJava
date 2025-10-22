package com.czm.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 操作日志实体类 - 用来向数据库表中存储操作日志
 */
@Data
public class OperateLog {
    // ID
    private Integer id;
    // 操作人ID
    private Integer operateEmpId;
    // 操作时间
    private LocalDateTime operateTime;
    // 操作类名
    private String className;
    // 操作方法名
    private String methodName;
    // 操作方法参数
    private String methodParams;
    // 操作方法返回值
    private String returnValue;
    // 操作耗时
    private Long costTime;
}