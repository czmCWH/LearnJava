package com.czm.service;

import com.czm.dto.SetmealDTO;
import com.czm.dto.SetmealPageQueryDTO;
import com.czm.result.PageResult;

import java.util.List;

/**
 * 套餐管理 Service
 */
public interface SetmealService {

    /**
     * 新增套餐
     * @param dto
     */
    void addSetmeal(SetmealDTO dto);

    /**
     * 套餐分页查询
     * @param queryDTO
     * @return
     */
    PageResult page(SetmealPageQueryDTO queryDTO);

    /**
     * 根据套餐 ids 批量删除套餐
     * @param ids
     */
    void delete(List<Long> ids);

    /**
     * 修改套餐
     */
    void update(SetmealDTO dto);

    /**
     * 修改套餐 起售和停售 状态
     * @param status 1起售 0停售
     */
    void startOrStop(Integer status, Long id);
}
