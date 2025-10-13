package com.czm.service.impl;

import com.czm.constant.MessageConstant;
import com.czm.constant.StatusConstant;
import com.czm.dto.DishDTO;
import com.czm.dto.DishPageQueryDTO;
import com.czm.entity.Dish;
import com.czm.entity.DishFlavor;
import com.czm.exception.DeletionNotAllowedException;
import com.czm.mapper.DishFlavorMapper;
import com.czm.mapper.DishMapper;
import com.czm.mapper.SetmealDishMapper;
import com.czm.result.PageResult;
import com.czm.service.DishService;
import com.czm.vo.DishVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜品模块 Service
 */

@Slf4j
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Transactional      // 涉及操作多张表数据，需要开启事务
    public void addDish(DishDTO dto) {
        // 1、构建菜品基本信息数据，将其存入dish表中
        Dish dish = new Dish();
        // 拷贝属性值
        BeanUtils.copyProperties(dto, dish);

        // 调用 Mapper 保存
        dishMapper.insert(dish);

        log.info("----dish id = {}", dish.getId());

        // 2、构建菜品 口味列表 数据，将其存入 dish_flavor 表中
        List<DishFlavor> dishFlavorList = dto.getFlavors();
        if (dishFlavorList != null && dishFlavorList.size() > 0) {
            // 给 口味列表数据 补充关联的 菜品ID
            dishFlavorList.forEach(dishFlavor -> {
                dishFlavor.setDishId(dish.getId());
            });

            // 调用 mapper 保存，批量插入口味列表数据
            dishFlavorMapper.insertBatch(dishFlavorList);
        }
    }

    @Override
    public PageResult page(DishPageQueryDTO dto) {
        // 1、设置分页参数
        PageHelper.startPage(dto.getPage(), dto.getPageSize());

        // 2、调用 mapper 查询
        Page<DishVO> page = dishMapper.list(dto);

        // 3、封装 PageResult 对象并返回
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void delete(List<Long> ids) {
        // 1、删除菜品之前，菜品是否起售
        ids.forEach(dishId -> {
           Dish dish = dishMapper.selectById(dishId);
           if (dish != null && dish.getStatus() == StatusConstant.ENABLE) {
               throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
           }
        });

        // 2、菜品被套餐关联，不允许删除
        Integer count = setmealDishMapper.countByDishId(ids);
        if (count > 0) {
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }
        // 3、批量删除 菜品表 中的记录
        dishMapper.deleteBatch(ids);

        // 4、删除 dish_flavor 菜品口味关系表 中的信息
        dishFlavorMapper.deleteBatch(ids);
    }

    @Override
    public DishVO getById(Long id) {
        // 1、根据ID查询菜品基本信息
        Dish dish = dishMapper.selectById(id);

        // 2、查询菜品的口味list
        List<DishFlavor> list = dishFlavorMapper.selectByDishId(id);

        // 3、构造 DishVO 对象并返回
        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish, dishVO);
        dishVO.setFlavors(list);

        return dishVO;
    }

    @Transactional  // ⚠️：修改多张表，需要开启事务！！！
    public void update(DishDTO dto) {
        // 1、修改菜品表的基本信息 dish 表
        // ⚠️：更新、新增时，需要传入的事 表对应的实体类，而不是dto
        Dish dish = new Dish();
        BeanUtils.copyProperties(dto, dish);

        dishMapper.update(dish);

        // 2、修改 菜品口味表的信息 dish_flavor 表
        // 先删除所有旧数据
        dishFlavorMapper.deleteByDishId(dto.getId());
        // 后保存
        List<DishFlavor> dishFlavorList = dto.getFlavors();
        if (dishFlavorList != null && dishFlavorList.size() > 0) {
            // 给 菜品口味记录 关联 菜品ID
            dishFlavorList.forEach(dishFlavor -> {
                dishFlavor.setDishId(dish.getId());
            });
            // 批量插入
            dishFlavorMapper.insertBatch(dishFlavorList);
        }
    }

    @Override
    public List<DishVO> listWithFlavor(Dish dish) {
        // 1、先查询菜品 dish表
        List<DishVO> dishVOList = dishMapper.listByDish(dish);
        // 2、根据菜品ID 查询口味
        dishVOList.forEach(dishVO -> {
            List<DishFlavor> flavors = dishFlavorMapper.selectByDishId(dishVO.getId());
            dishVO.setFlavors(flavors);
        });
        return dishVOList;
    }

    @Override
    public void startOrStop(Integer status, Long id) {
        Dish dish = new Dish();
        dish.setId(id);
        dish.setStatus(status);
        dishMapper.update(dish);
    }

    @Override
    public List<Dish> getByCategoryId(Long categoryId) {
        List<Dish> dishes = dishMapper.getByCategoryId(categoryId);
        return dishes;
    }

}
