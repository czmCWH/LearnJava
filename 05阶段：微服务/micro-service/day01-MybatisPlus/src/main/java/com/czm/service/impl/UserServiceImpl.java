package com.czm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.czm.enums.UserStatus;
import com.czm.mapper.UserPlusMapper;
import com.czm.pojo.entity.Address;
import com.czm.pojo.entity.User;
import com.czm.pojo.vo.AddressVO;
import com.czm.pojo.vo.UserVO;
import com.czm.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor        // ⚠️⚠️⚠️ 不必写 @Autowired 方式注入
public class UserServiceImpl extends ServiceImpl<UserPlusMapper, User> implements IUserService {

    private final UserPlusMapper userPlusMapper;

    /**
     * 根据用户ID扣减指定的金额
     * @param id
     * @param amount
     */
    @Override
    public void deductionBalanceById(Long id, Integer amount) {
        // 1、判断用户是否存在
        User user = this.getById(id);   // ⚠️：getById 是 ServiceImpl 父类中默认提供的
        if (user == null || user.getStatus() == UserStatus.FREEZE) {
            throw new RuntimeException("用户无法执行操作！");
        }

        // 2、判断余额是否可以扣减
        if (user.getBalance() < amount) {
            throw new RuntimeException("用户余额不足！");
        }

        // 3、执行扣减
        userPlusMapper.deductBalanceById(id, amount);
    }

    @Override
    public void deductionBalanceByIdWithLambda(Long id, Integer amount) {
        // 1、判断用户是否存在
        User user = this.getById(id);   // ⚠️：getById 是父类中默认提供的
        if (user == null || user.getStatus() == UserStatus.FREEZE) {
            throw new RuntimeException("用户无法执行操作！");
        }

        // 2、判断余额是否可以扣减
        if (user.getBalance() < amount) {
            throw new RuntimeException("用户余额不足！");
        }

        // 3、执行扣减
        // 获取扣减之后的余额
        int remainBalance = user.getBalance() - amount;

        // ⚠️：直接使用 lambda 来修改！！！！
        // update user set balance = #{remainBalance} [, stauts = 2] where id = #{id}
        this.lambdaUpdate()
                .set(User::getBalance, remainBalance)   // 更新余额
                // 当余额等于0时，设置用户状态为 2
                .set(remainBalance == 0, User::getStatus, 2)
                .eq(User::getId, id)
                .eq(User::getBalance, user.getBalance())    // ⚠️：乐观锁！！！
                .update();

    }

    @Override
    public UserVO queryUserAndAddressById(Long userId) {
        // 1、查询用户信息
        User user = this.getById(userId);
        UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);

        // 2、根据用户ID查询用户地址列表
        List<Address> addressList = Db.lambdaQuery(Address.class)
                    .eq(Address::getUserId, userId)
                    .list();
        List<AddressVO> addressVOList = BeanUtil.copyToList(addressList, AddressVO.class);
        userVO.setAddresses(addressVOList);

        return userVO;
    }

    @Override
    public List<UserVO> queryUserAndAddressByIds(List<Long> userIds) {
        // 1、根据ID集合批量查询用户
        List<User> userList = this.listByIds(userIds);
        List<UserVO> userVOList = BeanUtil.copyToList(userList, UserVO.class);

        // 2、根据用户ID集合批量查询用户地址
        List<Address> addressList = Db.lambdaQuery(Address.class)
                .in(Address::getUserId, userIds)
                .list();

        List<AddressVO> addressVOList = BeanUtil.copyToList(addressList, AddressVO.class);

        // 把查询的 地址列表 转换为 Map<用户ID：用户地址列表>
        Map<Long, List<AddressVO>> userAddressMap = addressVOList.stream().collect(Collectors.groupingBy(AddressVO::getUserId));

        // 循环用户列表，根据ID获取对应的地址列表
        for (UserVO userVO : userVOList) {
            List<AddressVO> list = userAddressMap.get(userVO.getId());
            userVO.setAddresses(list);
        }

        return userVOList;
    }

}
