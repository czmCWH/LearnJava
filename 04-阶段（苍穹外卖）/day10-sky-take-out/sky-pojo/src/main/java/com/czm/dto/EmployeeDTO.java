package com.czm.dto;

import lombok.Data;

import java.io.Serializable;

/***
 * 请求参数实体类，用于封装 员工信息 请求参数。
 */

@Data
public class EmployeeDTO implements Serializable {

    private Long id;

    private String username;

    private String name;

    private String phone;

    private String sex;

    private String idNumber;

}
