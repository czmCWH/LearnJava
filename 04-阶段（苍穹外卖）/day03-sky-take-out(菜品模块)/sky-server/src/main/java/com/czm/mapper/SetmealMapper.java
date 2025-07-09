package com.czm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 套餐 Mapper
 */

@Mapper   // @Mapper 是 MyBatis 框架里面的
public interface SetmealMapper {

    /**
     * 根据 分类ID 查询 套餐 的数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from setmeal where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);
}
