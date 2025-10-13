package com.czm.service.impl;

import com.czm.constant.MessageConstant;
import com.czm.constant.StatusConstant;
import com.czm.dto.SetmealDTO;
import com.czm.dto.SetmealPageQueryDTO;
import com.czm.entity.Dish;
import com.czm.entity.Setmeal;
import com.czm.entity.SetmealDish;
import com.czm.exception.DeletionNotAllowedException;
import com.czm.exception.SetmealEnableFailedException;
import com.czm.mapper.DishMapper;
import com.czm.mapper.SetmealDishMapper;
import com.czm.mapper.SetmealMapper;
import com.czm.result.PageResult;
import com.czm.service.SetmealService;
import com.czm.vo.DishItemVO;
import com.czm.vo.DishVO;
import com.czm.vo.SetmealVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Autowired
    private DishMapper dishMapper;

    @Transactional      // 需要操作 setmeal 表 和 setmeal_dish 表，所以需要开启事务
    public void addSetmeal(SetmealDTO dto) {
        // 1、补充基础属性
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(dto, setmeal);

        // 2、调用 mapper 插入套餐
        setmealMapper.insert(setmeal);

        // 3、将套餐的菜品信息 插入到 套餐菜品关系表 setmeal_dish 中
        List<SetmealDish> setmealDishes = dto.getSetmealDishes();
        setmealDishes.forEach(setmealDish -> {
            setmealDish.setSetmealId(setmeal.getId());
        });

        setmealDishMapper.insert(setmealDishes);
    }

    @Override
    public PageResult page(SetmealPageQueryDTO queryDTO) {
        // 1、设置 PageHelper 分页参数
        PageHelper.startPage(queryDTO.getPage(), queryDTO.getPageSize());

        // 2、调用 Mapper 查询
        Page<SetmealVO> page = setmealMapper.list(queryDTO);

        // 3、封装 PageResult 对象并返回
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void delete(List<Long> ids) {
        // 1、删除套餐前需判断套餐是否有起售。

        // 查询所有的套餐
        List<SetmealVO> setmealVOS = setmealMapper.selectByIds(ids);
        // 判断套餐是否有起售
        setmealVOS.forEach(setmealVO -> {
            if (setmealVO.getStatus() == StatusConstant.ENABLE) {
                throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
            }
        });

        // 2、批量删除套餐记录
        setmealMapper.deleteBatch(ids);

        // 3、删除 setmeal_dish 套餐和菜品关系表的中记录
        setmealDishMapper.deleteBatch(ids);
    }

    @Transactional  // 更新套餐，需要修改 setmeal、setmeal_dish 表，所以需要开启事务
    public void update(SetmealDTO dto) {
        //1、修改 setmeal 套餐表
        // 补充基础参数，需要使用 Entity 实体类
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(dto, setmeal);

        setmealMapper.update(setmeal);

        // 2、修改 setmeal_dish 表中 套餐和菜品的关系
        // 先删除 套餐和菜品 原有的记录
        setmealDishMapper.deleteBySetmealId(setmeal.getId());

        // 3、将修改后的  套餐和菜品记录 批量插入 setmeal_dish 表中
        List<SetmealDish> setmealDishes = dto.getSetmealDishes();
        if (setmealDishes != null && setmealDishes.size() > 0) {
            setmealDishes.forEach(setmealDish -> {
                setmealDish.setSetmealId(setmeal.getId());
            });
            setmealDishMapper.insert(setmealDishes);
        }
    }

    @Override
    public void startOrStop(Integer status, Long id) {
        // ⚠️ 1、起售套餐时，需判断套餐内是否有停售菜品，有停售菜品提示"套餐内包含未启售菜品，无法启售"
        if (status == StatusConstant.ENABLE) {
            // 根据 套餐ID 获取对应的 菜品ID List
            List<SetmealDish> setmealDishes = setmealDishMapper.selectBySetmealId(id);
            if (setmealDishes != null && setmealDishes.size() > 0) {
                List<Long> dishIds = new ArrayList<>();
                setmealDishes.forEach(setmealDish -> {
                    dishIds.add(setmealDish.getDishId());
                });

                // 根据 菜品ids 批量查询 菜品
                List<DishVO> dishVOList = dishMapper.selectByIds(dishIds);
                dishVOList.forEach(dishVO -> {
                    if (dishVO.getStatus() == StatusConstant.DISABLE) {
                        throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ENABLE_FAILED);
                    }
                });
            }
        }

        // 调用 mapper 起售 或 停售 套餐
        setmealMapper.updateStatus(status,id);

    }

    /**
     * 根据套餐名称、分类ID、状态查询符合的 套餐List
     */
    @Override
    public List<SetmealVO> listBySetmeal(Setmeal setmeal) {
        return setmealMapper.listBySetmeal(setmeal);
    }

    @Override
    public List<DishItemVO> getDishItemById(Long id) {
        return setmealMapper.getDishItemBySetmealId(id);
    }


}
