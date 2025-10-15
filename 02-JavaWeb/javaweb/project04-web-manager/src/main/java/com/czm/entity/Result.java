package com.czm.entity;

import lombok.Data;

/**
 * 定义实体类，用于统一响应结果类
 */
@Data
public class Result {
    private Integer code;   // 编码：1，成功；0，失败
    private String msg;     // 错误信息
    private Object data;    // 数据

    // 没有响应数据的 success
    public static Result success() {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        return result;
    }

    // 存在响应数据的 success
    public static Result success(Object data) {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        result.data = data;
        return result;
    }

    // 响应失败的数据
    public static Result error(String msg) {
        Result result = new Result();
        result.code = 0;
        result.msg = msg;
        return result;
    }
}
