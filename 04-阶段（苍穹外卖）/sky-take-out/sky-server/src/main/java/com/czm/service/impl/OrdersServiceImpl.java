package com.czm.service.impl;

import com.czm.constant.MessageConstant;
import com.czm.context.BaseContext;
import com.czm.dto.OrdersSubmitDTO;
import com.czm.entity.*;
import com.czm.exception.OrderBusinessException;
import com.czm.mapper.*;
import com.czm.service.OrdersService;
import com.czm.vo.OrderSubmitVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * C端订单模块 - Service
 */

@Slf4j
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private AddressBookMapper addressBookMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Transactional  // 需要修改多张表，因此需要开启事务
    public OrderSubmitVO submit(OrdersSubmitDTO dto) {

        // 查询地址簿信息
        AddressBook addressBook = addressBookMapper.getById(dto.getAddressBookId());
        if (addressBook == null) {
            throw new OrderBusinessException(MessageConstant.ADDRESS_BOOK_IS_NULL);
        }

        // 查询下单人信息
//      Long userId =  userMapper.selectById(BaseContext.getCurrentId());
        Long userId = 4L;
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new OrderBusinessException(MessageConstant.USER_NOT_LOGIN);
        }

        // 查询当前用户 购物车列表数据
        List<ShoppingCart> cartList = shoppingCartMapper.list(userId);
        if (cartList == null || cartList.size() == 0) {
            throw new OrderBusinessException(MessageConstant.SHOPPING_CART_IS_NULL);
        }

        // 1、构造订单数据，存入 orders 表中
        Orders orders = new Orders();
        BeanUtils.copyProperties(dto, orders);
        orders.setNumber(System.currentTimeMillis() + "");   // 订单编号
        orders.setStatus(Orders.PENDING_PAYMENT);    // 订单状态
//        orders.setUserId(BaseContext.getCurrentId());   // 下单用户ID
        orders.setUserId(4L);
        orders.setOrderTime(LocalDateTime.now());   // 下单时间
        orders.setPayStatus(Orders.UN_PAID);    // 订单支付状态

        orders.setPhone(addressBook.getPhone());
        orders.setAddress(addressBook.getCityName() + addressBook.getDistrictName() + addressBook.getDetail());
        orders.setConsignee(addressBook.getConsignee());    // 收获人

        orders.setUserName(user.getName());       // 下单人

        // 调用 Mapper 插入订单数据
        ordersMapper.insert(orders);

        log.info("--- 插入订单ID = {}",orders.getId());


        // 2、构造订单商品明细，存入 order_detail 表中
        List<OrderDetail> orderDetailList = new ArrayList<>();
        // 循环遍历购物车列表数据，构造订单明细
        cartList.forEach(cart -> {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(cart, orderDetail, "id");  // 注意订单明细id不需要
            // 管理订单ID
            orderDetail.setOrderId(orders.getId());
            orderDetailList.add(orderDetail);
        });
        // 批量插入 订单明细
        orderDetailMapper.insertBatch(orderDetailList);

        // 3、清空购物车，删除当前用户的购物车
        shoppingCartMapper.clean(userId);

        // 4、构造订单对象返回
        return OrderSubmitVO.builder()
                .id(orders.getId())
                .orderNumber(orders.getNumber())
                .orderAmount(orders.getAmount())
                .orderTime(orders.getOrderTime())
                .build();
    }
}
