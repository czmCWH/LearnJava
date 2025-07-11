package com.czm.mapper;

import com.czm.entity.ShoppingCart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    /**
     * 根据 商品信息 查询购物车存在的 商品信息
     */
    ShoppingCart selectBy(ShoppingCart shoppingCart);

    /**
     * 新增购物车 商品
     */
    @Insert("insert into shopping_cart (name, user_id, dish_id, setmeal_id, dish_flavor, number, amount, image, create_time) values (#{name}, " +
            "#{userId}, " +
            "#{dishId}, #{setmealId}, #{dishFlavor}, #{number}, #{amount}, #{image}, #{createTime})")
    void insert(ShoppingCart shoppingCart);

    /**
     * 根据购物车商品ID 更新数量
     * @param shoppingCart
     */
    @Update("update shopping_cart set number = #{number} where id = #{id}")
    void update(ShoppingCart shoppingCart);

    /**
     * 查询用户购物车全部商品
     */
    @Select("select * from shopping_cart where user_id = #{userId}")
    List<ShoppingCart> list(Long userId);

    /**
     * 清空购物车
     */
    @Delete("delete from shopping_cart where user_id = #{userId}")
    void clean(Long userId);
}
