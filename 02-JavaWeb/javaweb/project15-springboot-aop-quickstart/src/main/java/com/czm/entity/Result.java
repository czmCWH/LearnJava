package com.czm.entity;

import lombok.Data;

/**
 * 统一响应结果类
 */
@Data
public class Result {
    private Integer code;   // 编码：1，成功；0，失败
    private String msg;     // 错误信息
    private Object data;    // 数据

    public static Result success() {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        result.data = data;
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.code = 0;
        result.msg = msg;
        return result;
    }
}
