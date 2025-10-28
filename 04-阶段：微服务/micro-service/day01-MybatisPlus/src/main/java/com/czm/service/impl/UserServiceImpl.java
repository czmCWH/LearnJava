package com.czm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
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

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor        // ⚠️⚠️⚠️ 不必写 @Autowired 方式注入 UserPlusMapper
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
                // 1、构建 sql
                .set(User::getBalance, remainBalance)   // 更新余额
                // 当余额等于0时，设置用户状态为 2
                .set(remainBalance == 0, User::getStatus, 2)
                .eq(User::getId, id)
                .eq(User::getBalance, user.getBalance())    // ⚠️：乐观锁！！！
                // 2、执行 sql
                .update();
        /*
         此处更新用户信息存在并发风险！
            如果有多个线程同时查询用户信息，查到的信息是一样的，那么各自都会进行扣减。则出现2次扣减操作，只扣减了一次。
            解决方案：悲观锁、乐观锁。
            乐观锁，即先比较，后更新！
            .eq(User::getBalance, user.getBalance())，更新 balance 时添加 比较条件，即必须更新前的值与查询到的值相等才更新。
         */
    }

    @Override
    public List<UserVO> queryList(String name, Integer status, Integer minBalance, Integer maxBalance) {
        List<User> userList = this.lambdaQuery()
                .like(name != null, User::getUsername, name)
                .eq(status != null, User::getStatus, status)
                .ge(minBalance != null, User::getBalance, minBalance)
                .le(maxBalance != null, User::getBalance, maxBalance)
                .list();
        return BeanUtil.copyToList(userList, UserVO.class);
    }

    /**
     * 根据用户ID查询用户信息+地址信息
     */
    @Override
    public UserVO queryUserAndAddressById(Long userId) {
        // 1、查询用户信息
        User user = this.getById(userId);

        // 2、根据用户ID查询用户地址列表 - 使用 MP 静态工具类 Db 实现
        List<Address> addressList = Db.lambdaQuery(Address.class)
                    .eq(Address::getUserId, userId)
                    .list();
        // 3、封装VO
        UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
        if (CollUtil.isNotEmpty(addressList)) { // 健壮性判断
            List<AddressVO> addressVOList = BeanUtil.copyToList(addressList, AddressVO.class);
            userVO.setAddresses(addressVOList);
        }

        return userVO;
    }

    /**
     * 批量查询（用户信息+地址信息）- 使用 MP 静态工具类 Db 实现
     */
    @Override
    public List<UserVO> queryUserAndAddressByIds(List<Long> userIds) {
        // 1、根据ID集合批量查询用户
        List<User> userList = this.listByIds(userIds);
        if (CollUtil.isEmpty(userList)) {   // 健壮性判断
            return Collections.emptyList();
        }

        // 2、根据用户ID集合批量查询用户地址
        // 2.1、获取查询到的用户ID集合
        List<Long> ids = userList.stream().map(User::getId).toList();
        // 2.2、根据用户ID 查询地址
        List<Address> addressList = Db.lambdaQuery(Address.class)
                .in(Address::getUserId, ids)
                .list();

        // 2.3、转换地址VO
        List<AddressVO> addressVOList = BeanUtil.copyToList(addressList, AddressVO.class);
        // 2.4、把用户地址进行分组处理，相同用户的放入一个集合。
        Map<Long, List<AddressVO>> userAddressMap = new HashMap<>(0);
        if (CollUtil.isNotEmpty(addressList)) {
            userAddressMap = addressVOList.stream().collect(Collectors.groupingBy(AddressVO::getUserId));    // ⚠️ 根据用户ID分组
        }

        // 3、封装VO返回
        List<UserVO> userVOList = new ArrayList<>(0);
        // 循环用户列表，根据ID获取对应的地址列表
        for (User user : userList) {
            UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
            List<AddressVO> list = userAddressMap.get(user.getId());
            userVO.setAddresses(list);
        }

        return userVOList;
    }

}
