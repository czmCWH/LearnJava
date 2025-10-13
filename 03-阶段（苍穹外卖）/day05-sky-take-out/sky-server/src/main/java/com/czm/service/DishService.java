package com.czm.service;

import com.czm.dto.DishDTO;
import com.czm.dto.DishPageQueryDTO;
import com.czm.entity.Dish;
import com.czm.result.PageResult;
import com.czm.vo.DishVO;

import java.util.List;

/**
 * 菜品管理
 */

public interface DishService {

    /**
     * 新增菜品
     * @param dto
     */
    void addDish(DishDTO dto);

    /**
     * 分页查询菜品列表
     * @param dto
     * @return
     */
    PageResult page(DishPageQueryDTO dto);

    /**
     * 删除菜品
     * @param ids
     */
    void delete(List<Long> ids);

    /**
     * 根据ID查询菜品信息
     * @param id
     * @return
     */
    DishVO getById(Long id);

    /**
     * 修改菜品基本信息
     * @param dto
     */
    void update(DishDTO dto);
}
