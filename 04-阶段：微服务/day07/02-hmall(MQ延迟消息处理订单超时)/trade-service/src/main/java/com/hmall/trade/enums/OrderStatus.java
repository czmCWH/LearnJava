package com.hmall.trade.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author ChenPi
 * @since 2025/02/27 15:26
 */
@Getter
public enum OrderStatus {
    /**
     * '订单的状态，1、未付款 2、已付款,未发货 3、已发货,未确认
     * 4、确认收货，交易成功 5、交易取消，订单关闭 6、交易结束，已评价',
     */
    UNPAID(1, "未付款"),
    PAID(2, "已付款,未发货"),
    DELIVERED(3, "已发货,未确认"),
    CONFIRMED(4, "确认收货，交易成功"),
    CANCELLED(5, "交易取消，订单关闭"),
    FINISHED(6, "交易结束，已评价"),
    ;

    @EnumValue
    private final Integer code;
    @JsonValue
    private final String desc;

    OrderStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
