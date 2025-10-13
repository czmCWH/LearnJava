package com.czm.service;

import com.czm.dto.CategoryDTO;
import com.czm.dto.CategoryPageQueryDTO;
import com.czm.entity.Category;
import com.czm.result.PageResult;

import java.util.List;

/**
 * 分类管理
 */

public interface CategoryService {
    /**
     * 新增分类
     * @param dto
     */
    void save(CategoryDTO dto);

    /**
     * 条件分页查询
     * @param dto
     * @return
     */
    PageResult pageQuery(CategoryPageQueryDTO dto);

    /**
     * 根据ID删除分类
     * @param id
     */
    void deleteById(Long id);

    /**
     * 修改分类
     * @param dto
     */
    void update(CategoryDTO dto);

    /**
     * 启用 或 禁用 分类
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 根据类型查询分类
     * @param type
     */
    List<Category> list(Integer type);
}
