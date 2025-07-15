package com.czm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.czm.constant.MessageConstant;
import com.czm.context.BaseContext;
import com.czm.dto.*;
import com.czm.entity.*;
import com.czm.exception.DeletionNotAllowedException;
import com.czm.exception.OrderBusinessException;
import com.czm.mapper.*;
import com.czm.result.PageResult;
import com.czm.service.OrdersService;
import com.czm.utils.WeChatPayUtil;
import com.czm.vo.OrderPaymentVO;
import com.czm.vo.OrderStatisticsVO;
import com.czm.vo.OrderSubmitVO;
import com.czm.vo.OrderVO;
import com.czm.websocket.WebSocketServer;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    private WeChatPayUtil weChatPayUtil;

    @Autowired
    private WebSocketServer webSocketServer;

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

    @Override
    public OrderPaymentVO payment(OrdersPaymentDTO dto) throws Exception {
        // 1、获取当前登录用户信息得到 微信用户openid
//        Long userId = BaseContext.getCurrentId();
        Long userId = 4L;
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new OrderBusinessException(MessageConstant.USER_NOT_LOGIN);
        }
        // 2、调用微信支付接口，生成预支付交易单  --- 没有微信支付商户号，所以走不通，先注释掉！！！
//        JSONObject jsonObj = weChatPayUtil.pay(
//                dto.getOrderNumber(),   // 商户订单号
//                new BigDecimal("0.01"), // 支付金额
//                "苍穹外卖", // 商品描述
//                user.getOpenid()    // 微信用户的openid
//        );
        JSONObject jsonObj= new JSONObject();
        // 判断该订单是否交易过
        if (jsonObj.getString("code") != null && jsonObj.getString("code").equals("ORDERPAID")) {
            throw new OrderBusinessException("该订单已支付！");
        }

        // 3、返回签名对象给 前端
        OrderPaymentVO vo = jsonObj.toJavaObject(OrderPaymentVO.class);
        vo.setPackageStr(jsonObj.getString("package"));

        return vo;
    }

    @Override
    public void paySuccess(String outTradeNo, Integer payMethod) throws Exception {
        // 1、获取当前登录用户ID
//        Long userId = BaseContext.getCurrentId();
        Long userId = 4L;

        // 2、根据订单号查询当前用户的订单
        Orders ordersDB = ordersMapper.getByNumberAndUserId(outTradeNo, userId);

        // 3、根据订单ID更新订单状态、支付方式、支付状态、支付时间
        Orders orders = Orders.builder()
                .id(ordersDB.getId())
                .status(Orders.TO_BE_CONFIRMED)
                .payMethod(payMethod)
                .payStatus(Orders.PAID)
                .checkoutTime(LocalDateTime.now())
                .build();
        ordersMapper.update(orders);
    }

    @Override
    public PageResult page(OrdersPageQueryDTO dto) {
        // 1、设置 PageHelper 分页查询页码
        PageHelper.startPage(dto.getPage(), dto.getPageSize());

        // 2、组装查询参数
//        Long userId = BaseContext.getCurrentId();
//        Long userId = 4L;
//        Orders orders = new Orders();
//        orders.setUserId(userId);
//        orders.setStatus(dto.getStatus());

        // 3、调用 mapper 查询
        Page<Orders> page = ordersMapper.pageQuery(dto);

        // 查询每个订单的商品信息
        List<OrderVO> orderVOS = new ArrayList<>();
        page.forEach(order -> {
           OrderVO orderVO = new OrderVO();
           BeanUtils.copyProperties(order, orderVO);
           // 根据订单ID查询订单所有商品
           List<OrderDetail> orderDetails = orderDetailMapper.selectByOrderId(order.getId());
           orderVO.setOrderDetailList(orderDetails);
           orderVOS.add(orderVO);
        });
        // 4、返回查询结果
        return new PageResult(page.getTotal(), orderVOS);
    }

    @Override
    public void oneMoreOrders(Long id) {
        // 1、根据订单ID查询订单商品信息
        List<OrderDetail> orderDetailList = orderDetailMapper.selectByOrderId(id);

        // 2、将订单商品加入购物车
        // 商品加入购物车后，用户可以再次提交订单

//        Long userId = BaseContext.getCurrentId();
        Long userId = 4L;

        // 方式1，一个一个商品插入
//        orderDetailList.forEach(orderDetail -> {
//           ShoppingCart shoppingCart = new ShoppingCart();
//
//           BeanUtils.copyProperties(orderDetail, shoppingCart);
//           shoppingCart.setCreateTime(LocalDateTime.now());
//           shoppingCart.setUserId(userId);
//           shoppingCartMapper.insert(shoppingCart);
//        });

        // 方式2，批量插入
        List<ShoppingCart> shoppingCarts = orderDetailList.stream().map(x -> {
            ShoppingCart shoppingCart = new ShoppingCart();

            // 将原订单详情里面的菜品信息重新复制到购物车对象中
            BeanUtils.copyProperties(x, shoppingCart, "id");
            shoppingCart.setUserId(userId);
            shoppingCart.setCreateTime(LocalDateTime.now());
            return shoppingCart;
        }).collect(Collectors.toList());

        shoppingCartMapper.insertBatch(shoppingCarts);
    }

    @Override
    public void cancelOrderByUser(Long id) {
        // 1、查询需要被取消的订单信息
        Orders orders = new Orders();
//        orders.setId(Long.valueOf(id));
        orders.setId(id);
//        Long userId = BaseContext.getCurrentId();
        Long userId = 4L;
        orders.setUserId(userId);
        Orders cancelOrder = ordersMapper.getById(orders);

        // 2、判断订单状态是否可以取消
        if (cancelOrder == null) {
            throw new DeletionNotAllowedException(MessageConstant.ORDER_NOT_FOUND);
        } else if (cancelOrder.getStatus() > 2) {
            throw new DeletionNotAllowedException(MessageConstant.ORDER_STATUS_ERROR);
        } else {    // 待付款、待接单可以取消订单
            if (cancelOrder.getStatus() == Orders.TO_BE_CONFIRMED) {
                // ⚠️：如果订单处于待接单状态需要给用户退款
                //调用微信支付退款接口
//                weChatPayUtil.refund(
//                        cancelOrder.getNumber(), //商户订单号
//                        cancelOrder.getNumber(), //商户退款单号
//                        new BigDecimal(0.01),//退款金额，单位 元
//                        new BigDecimal(0.01));//原订单金额
                cancelOrder.setPayStatus(Orders.REFUND);
            }
            // 将订单状态修改为已取消状态
            cancelOrder.setStatus(Orders.CANCELLED);
            orders.setCancelReason("用户取消");
            orders.setCancelTime(LocalDateTime.now());
            ordersMapper.update(cancelOrder);
        }
    }

    @Override
    public OrderVO selectOrders(Integer id) {
        // 1、查询订单基本信息
        Orders orders = new Orders();
        orders.setId(Long.valueOf(id));
        Long userId = 4L;
        orders.setUserId(userId);
        Orders ordersDB = ordersMapper.getById(orders);
        if (ordersDB == null) {
            throw new DeletionNotAllowedException(MessageConstant.ORDER_NOT_FOUND);
        }

        // 2、查询订单商品信息
        List<OrderDetail> list = orderDetailMapper.selectByOrderId(ordersDB.getId());

        // 3、封装返回
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(ordersDB, orderVO);
        orderVO.setOrderDetailList(list);
        return orderVO;
    }

    @Override
    public PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO) {
        PageHelper.startPage(ordersPageQueryDTO.getPage(), ordersPageQueryDTO.getPageSize());

        Page<Orders> page = ordersMapper.pageQuery(ordersPageQueryDTO);

        // 部分订单状态，需要额外返回订单菜品信息，将Orders转化为OrderVO
        List<OrderVO> orderVOList = getOrderVOList(page);

        return new PageResult(page.getTotal(), orderVOList);
    }

    private List<OrderVO> getOrderVOList(Page<Orders> page) {
        // 需要返回订单菜品信息，自定义OrderVO响应结果
        List<OrderVO> orderVOList = new ArrayList<>();

        List<Orders> ordersList = page.getResult();
        if (!CollectionUtils.isEmpty(ordersList)) {
            for (Orders orders : ordersList) {
                // 将共同字段复制到OrderVO
                OrderVO orderVO = new OrderVO();
                BeanUtils.copyProperties(orders, orderVO);
                String orderDishes = getOrderDishesStr(orders);

                // 将订单菜品信息封装到orderVO中，并添加到orderVOList
                orderVO.setOrderDishes(orderDishes);
                orderVOList.add(orderVO);
            }
        }
        return orderVOList;
    }

    /**
     * 根据订单id获取菜品信息字符串
     */
    private String getOrderDishesStr(Orders orders) {
        // 查询订单菜品详情信息（订单中的菜品和数量）
        List<OrderDetail> orderDetailList = orderDetailMapper.selectByOrderId(orders.getId());

        // 将每一条订单菜品信息拼接为字符串（格式：宫保鸡丁*3；）
        List<String> orderDishList = orderDetailList.stream().map(x -> {
            String orderDish = x.getName() + "*" + x.getNumber() + ";";
            return orderDish;
        }).collect(Collectors.toList());

        // 将该订单对应的所有菜品信息拼接在一起
        return String.join("", orderDishList);
    }

    /**
     * 各个状态的订单数量统计
     *
     * @return
     */
    @Override
    public OrderStatisticsVO statistics() {
        // 根据状态，分别查询出待接单、待派送、派送中的订单数量
        Integer toBeConfirmed = ordersMapper.countStatus(Orders.TO_BE_CONFIRMED);
        Integer confirmed = ordersMapper.countStatus(Orders.CONFIRMED);
        Integer deliveryInProgress = ordersMapper.countStatus(Orders.DELIVERY_IN_PROGRESS);

        // 将查询出的数据封装到orderStatisticsVO中响应
        OrderStatisticsVO orderStatisticsVO = new OrderStatisticsVO();
        orderStatisticsVO.setToBeConfirmed(toBeConfirmed);
        orderStatisticsVO.setConfirmed(confirmed);
        orderStatisticsVO.setDeliveryInProgress(deliveryInProgress);
        return orderStatisticsVO;
    }

    @Override
    public OrderVO details(Long id) {
        // 根据id查询订单
        Orders od = new Orders();
//        orders.setId(Long.valueOf(id));
        od.setId(id);
        Orders orders = ordersMapper.getById(od);

        // 查询该订单对应的菜品/套餐明细
        List<OrderDetail> orderDetailList = orderDetailMapper.selectByOrderId(orders.getId());

        // 将该订单及其详情封装到OrderVO并返回
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(orders, orderVO);
        orderVO.setOrderDetailList(orderDetailList);

        return orderVO;
    }

    @Override
    public void confirm(OrdersConfirmDTO ordersConfirmDTO) {
        Orders orders = Orders.builder()
                .id(ordersConfirmDTO.getId())
                .status(Orders.CONFIRMED)
                .build();
        ordersMapper.update(orders);
    }

    @Override
    public void rejection(OrdersRejectionDTO ordersRejectionDTO) throws Exception {
        // 根据id查询订单
        Orders od = new Orders();
//        orders.setId(Long.valueOf(id));
        od.setId(ordersRejectionDTO.getId());
        Orders ordersDB = ordersMapper.getById(od);

        // 订单只有存在且状态为2（待接单）才可以拒单
        if (ordersDB == null || !ordersDB.getStatus().equals(Orders.TO_BE_CONFIRMED)) {
            throw new OrderBusinessException(MessageConstant.ORDER_STATUS_ERROR);
        }

        //支付状态
        Integer payStatus = ordersDB.getPayStatus();
//        if (payStatus == Orders.PAID) {
//            //用户已支付，需要退款
//            String refund = weChatPayUtil.refund(
//                    ordersDB.getNumber(),
//                    ordersDB.getNumber(),
//                    new BigDecimal(0.01),
//                    new BigDecimal(0.01));
//            log.info("申请退款：{}", refund);
//        }

        // 拒单需要退款，根据订单id更新订单状态、拒单原因、取消时间
        Orders orders = new Orders();
        orders.setId(ordersDB.getId());
        orders.setStatus(Orders.CANCELLED);
        orders.setRejectionReason(ordersRejectionDTO.getRejectionReason());
        orders.setCancelTime(LocalDateTime.now());

        ordersMapper.update(orders);
    }

    @Override
    public void cancel(OrdersCancelDTO ordersCancelDTO) throws Exception {
        // 根据id查询订单
        Orders od = new Orders();
//        orders.setId(Long.valueOf(id));
        od.setId(ordersCancelDTO.getId());
        Orders ordersDB = ordersMapper.getById(od);

        //支付状态
        Integer payStatus = ordersDB.getPayStatus();
//        if (payStatus == 1) {
//            //用户已支付，需要退款
//            String refund = weChatPayUtil.refund(
//                    ordersDB.getNumber(),
//                    ordersDB.getNumber(),
//                    new BigDecimal(0.01),
//                    new BigDecimal(0.01));
//            log.info("申请退款：{}", refund);
//        }

        // 管理端取消订单需要退款，根据订单id更新订单状态、取消原因、取消时间
        Orders orders = new Orders();
        orders.setId(ordersCancelDTO.getId());
        orders.setStatus(Orders.CANCELLED);
        orders.setCancelReason(ordersCancelDTO.getCancelReason());
        orders.setCancelTime(LocalDateTime.now());
        ordersMapper.update(orders);
    }

    @Override
    public void delivery(Long id) {
        // 根据id查询订单
        Orders od = new Orders();
//        orders.setId(Long.valueOf(id));
        od.setId(id);
        Orders ordersDB = ordersMapper.getById(od);

        // 校验订单是否存在，并且状态为3
        if (ordersDB == null || !ordersDB.getStatus().equals(Orders.CONFIRMED)) {
            throw new OrderBusinessException(MessageConstant.ORDER_STATUS_ERROR);
        }

        Orders orders = new Orders();
        orders.setId(ordersDB.getId());
        // 更新订单状态,状态转为派送中
        orders.setStatus(Orders.DELIVERY_IN_PROGRESS);

        ordersMapper.update(orders);
    }

    @Override
    public void complete(Long id) {
        // 根据id查询订单
        Orders od = new Orders();
//        orders.setId(Long.valueOf(id));
        od.setId(id);
        Orders ordersDB = ordersMapper.getById(od);

        // 校验订单是否存在，并且状态为4
        if (ordersDB == null || !ordersDB.getStatus().equals(Orders.DELIVERY_IN_PROGRESS)) {
            throw new OrderBusinessException(MessageConstant.ORDER_STATUS_ERROR);
        }

        Orders orders = new Orders();
        orders.setId(ordersDB.getId());
        // 更新订单状态,状态转为完成
        orders.setStatus(Orders.COMPLETED);
        orders.setDeliveryTime(LocalDateTime.now());

        ordersMapper.update(orders);
    }

    @Override
    public void reminder(Long id) {
        // 查询订单是否存在
        Orders od = new Orders();
//        orders.setId(Long.valueOf(id));
        od.setId(id);
        Orders orders = ordersMapper.getById(od);
        if (orders == null) {
            throw new OrderBusinessException(MessageConstant.ORDER_NOT_FOUND);
        }

        //基于WebSocket实现催单
        Map map = new HashMap();
        map.put("type", 2);//2代表用户催单
        map.put("orderId", id);
        map.put("content", "订单号：" + orders.getNumber());
        webSocketServer.sendToAllClient(JSON.toJSONString(map));
    }
}
