package com.czm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 日志对象实体类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpLog {
    private Integer id; // 主键ID
    private LocalDateTime operateTime;
    private String info;
}
