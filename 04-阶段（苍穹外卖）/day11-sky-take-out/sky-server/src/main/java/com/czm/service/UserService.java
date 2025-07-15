package com.czm.service;

import com.czm.dto.UserLoginDTO;
import com.czm.entity.User;

/**
 * C端：用户相关业务
 */

public interface UserService {

    /**
     * 根据微信小程序授权码登录
     */
    User wxLogin(UserLoginDTO dto);
}
