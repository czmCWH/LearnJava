package com.czm.d5_proxy;

/**
 * 用户业务接口
 */
public interface UserService {
    // 登录功能
    void login(String username, String password) throws Exception;

    // 删除用户
    void deleteUsers() throws Exception;

    // 查询用户
    String[] selectUsers() throws Exception;

}
