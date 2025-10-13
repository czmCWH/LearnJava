package com.czm.mapper;

import com.czm.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜品的口味 mapper
 */

@Mapper   // 启动类上加了 Mapper 扫描注解，该接口的 @mapper 注解可以省略
public interface DishFlavorMapper {

    /**
     * 批量插入口味列表，需采用动态 sql 实现
     * @param list
     */
    void insertBatch(List<DishFlavor> list);

    /**
     * 根据菜品IDs批量删除 关联的口味记录
     * @param dishIds
     */
    void deleteBatch(List<Long> dishIds);

    /**
     * 根据菜品ID查询口味列表
     * @param id
     */
    List<DishFlavor> selectByDishId(Long dishId);

    /**
     * 根据菜品ID 删除口味记录
     */
    void deleteByDishId(Long dishId);
}
