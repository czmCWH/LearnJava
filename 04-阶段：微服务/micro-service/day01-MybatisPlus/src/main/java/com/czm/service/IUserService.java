package com.czm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czm.pojo.dto.PageDTO;
import com.czm.pojo.entity.User;
import com.czm.pojo.query.UserQuery;
import com.czm.pojo.vo.UserVO;

import java.util.List;

/**
 * MyBatis-Plus 增强 Service
 */

public interface IUserService extends IService<User> {

    /**
     * 根据用户ID扣减金额
     * @param id
     * @param amount
     */
    void deductionBalanceById(Long id, Integer amount);

    /**
     * 根据用户ID扣减金额  -------- IService 的 Lambda 实现更新操作
     */
    void deductionBalanceByIdWithLambda(Long id, Integer amount);


    /**
     * 根据复杂条件查询用户列表
     */
    List<UserVO> queryList(String name, Integer status, Integer minBalance, Integer maxBalance);


    /**
     * 通过 Db 工具类查询单个用户信息
     */
    UserVO queryUserAndAddressById(Long userId);

    /**
     * 通过 Db 工具类批量查询用户信息
     */
    List<UserVO> queryUserAndAddressByIds(List<Long> userIds);


    /**
     * 根据查询条件分页查询用户
     */
    PageDTO<UserVO> queryUsersPage(UserQuery query);
}
