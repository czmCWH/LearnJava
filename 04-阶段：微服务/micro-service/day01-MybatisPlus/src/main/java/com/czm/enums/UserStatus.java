package com.czm.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum UserStatus {
    // 枚举值
    NORMAL(1,"正常"),
    FREEZE(2, "冻结");

    @EnumValue      // @EnumValue 注解表示 保存到数据库中的值
    private final Integer code;
    @JsonValue      // @JsonValue 注解表示 在序列化时使用的是这个属性的值
    private final String message;

    UserStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
