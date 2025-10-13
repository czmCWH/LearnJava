package com.czm.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 套餐-菜品关系
 */

@Mapper
public interface SetmealDishMapper {

    /**
     * 统计 菜品ID 关联的套餐个数
     * @param id
     * @return
     */
    Integer countByDishId(List<Long> dishIds);
}
