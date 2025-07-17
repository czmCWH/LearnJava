package com.czm.pojo.vo;

import com.czm.enums.UserStatus;
import com.czm.pojo.entity.UserInfo;
import lombok.Data;

import java.util.List;

/**
 * 用户VO实体
 */

@Data
public class UserVO {

    // 用户id
    private Long id;

    // 用户名
    private String username;

    // 详细信息
    private String info;
//    private UserInfo info;

    // 使用状态（1正常 2冻结）
//    private Integer status;
    private UserStatus status;

    //    账户余额
    private Integer balance;

    // 用户地址列表
    private List<AddressVO> addresses;
}
