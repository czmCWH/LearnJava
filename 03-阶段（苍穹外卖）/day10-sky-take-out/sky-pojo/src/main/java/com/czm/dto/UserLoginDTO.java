package com.czm.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * C端用户登录
 */
@Data
public class UserLoginDTO implements Serializable {

    // 微信小程序登录授权码 code，只能使用一次
    private String code;

}
