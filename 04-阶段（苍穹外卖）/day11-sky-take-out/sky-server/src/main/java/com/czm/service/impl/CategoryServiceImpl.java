package com.czm.service.impl;

import com.czm.constant.MessageConstant;
import com.czm.constant.StatusConstant;
import com.czm.context.BaseContext;
import com.czm.dto.CategoryDTO;
import com.czm.dto.CategoryPageQueryDTO;
import com.czm.entity.Category;
import com.czm.exception.DeletionNotAllowedException;
import com.czm.mapper.CategoryMapper;
import com.czm.mapper.DishMapper;
import com.czm.mapper.SetmealMapper;
import com.czm.result.PageResult;
import com.czm.service.CategoryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 分类模块，业务层
 */

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    // 分类 Mapper
    @Autowired
    private CategoryMapper categoryMapper;

    // 菜品 Mapper
    @Autowired
    private DishMapper dishMapper;

    // 套餐 Mapper
    @Autowired
    private SetmealMapper setmealMapper;

    @Override
    public void save(CategoryDTO dto) {
        Category category = new Category();
        // 拷贝属性
        BeanUtils.copyProperties(dto, category);

        // 补充默认属性
        category.setStatus(StatusConstant.DISABLE); // 分类状态，默认禁用
        // 补充创建时间、创建人、
        // 使用公共字段自动填充逻辑
//        category.setCreateTime(LocalDateTime.now());
//        category.setUpdateTime(LocalDateTime.now());
//        category.setCreateUser(BaseContext.getCurrentId());
//        category.setUpdateUser(BaseContext.getCurrentId());

        categoryMapper.insert(category);
    }

    @Override
    public PageResult pageQuery(CategoryPageQueryDTO dto) {
        // 设置 PageHelper 插件的 page、pageSize 参数
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        // 分页查询
        Page<Category> page = categoryMapper.pageQuery(dto);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void deleteById(Long id) {
        // 1、删除前，需要查询分类是否关联了菜品，如果关联了，则抛出业务异常
        Integer count = dishMapper.countByCategoryId(id);
        if (count > 0) {
            // 当前分类下有菜品，不能删除
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_DISH);
        }

        // 2、查询分类是否关联 套餐
        count = setmealMapper.countByCategoryId(id);
        if (count > 0) {
            // 当前分类下有菜品，不能删除
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_SETMEAL);
        }

        // 3、调用 Mapper 删除分类
        categoryMapper.deleteById(id);
    }

    @Override
    public void update(CategoryDTO dto) {
        // 1、补充默认参数
        Category category = new Category();
        BeanUtils.copyProperties(dto, category);

        // 设置修改时间、修改人
        // 使用公共字段自动填充逻辑
//        category.setUpdateTime(LocalDateTime.now());
//        category.setUpdateUser(BaseContext.getCurrentId());
        // 2、执行 sql 修改
        categoryMapper.update(category);
    }

    @Override
    public void startOrStop(Integer status, Long id) {
        // 1、补充普通参数（更新时间、更新人）
        Category category = Category.builder()
                .id(id)
                .status(status)
                .updateTime(LocalDateTime.now())
                .updateUser(BaseContext.getCurrentId())
                .build();
        // 2、更新分类信息
        categoryMapper.update(category);
    }

    @Override
    public List<Category> list(Integer type) {
        return categoryMapper.list(type);
    }

}
