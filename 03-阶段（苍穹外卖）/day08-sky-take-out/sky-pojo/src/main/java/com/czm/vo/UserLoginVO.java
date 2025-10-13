package com.czm.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVO implements Serializable {
    // 用户ID
    private Long id;
    // 用户唯一标识
    private String openid;
    // 用户令牌
    private String token;

}
