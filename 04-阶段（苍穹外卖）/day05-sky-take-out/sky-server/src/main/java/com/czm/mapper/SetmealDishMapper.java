package com.czm.mapper;

import com.czm.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    /**
     * 插入 套餐中包含的菜品信息
     * @param list
     */
    void insert(List<SetmealDish> list);

    /**
     * 根据套餐 ID list 批量删除 套餐关联的 菜品
     * @param setmealIds
     */
    void deleteBatch(List<Long> setmealIds);

    /**
     * 根据 套餐ID 批量删除
     * @param setmealId
     */

    void deleteBySetmealId(Long setmealId);

    /**
     * 根据菜品ID 查询所有关联的 菜品List
     * @param setmealId
     * @return
     */
    @Select("select * from setmeal_dish where setmeal_id = #{setmealId}")
    List<SetmealDish> selectBySetmealId(Long setmealId);
}
