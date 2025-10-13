package com.czm.mapper;

import com.czm.anno.AutoFill;
import com.czm.dto.SetmealPageQueryDTO;
import com.czm.entity.Setmeal;
import com.czm.enumeration.OperationType;
import com.czm.vo.DishItemVO;
import com.czm.vo.SetmealVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

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

    /**
     * 新增套餐
     * @param setmeal
     */
    @AutoFill(OperationType.INSERT)     // AOP 补充基础参数
    void insert(Setmeal setmeal);

    /**
     * 分页查询菜品，由于需要根据 套餐名称、分类类型、状态 条件进行查询，所以需要使用 动态 sql 实现
     * @param queryDTO
     * @return
     */
    Page<SetmealVO> list(SetmealPageQueryDTO queryDTO);

    /**
     * 根据 套餐ID 查询套餐
     */
    SetmealVO selectById(Long setmealId);

    /**
     * 根据 套餐ids批量查询 套餐list
     */
    List<SetmealVO> selectByIds(List<Long> setmealIds);

    /**
     * 批量删除套餐
     * @param setmealIds
     */
    void deleteBatch(List<Long> setmealIds);

    /**
     * 更新 套餐
     * @param setmeal
     */
    @AutoFill(OperationType.UPDATE)     // AOP 补充基础参数
    void update(Setmeal setmeal);

    /**
     * 根据id设置套餐的起售与停售
     * @param status
     * @param id
     */
    @Update("update setmeal set status = #{status} where id = #{id}")
    void updateStatus(Integer status, Long id);

    /**
     * 根据套餐名称、分类ID、状态查询符合的 套餐List
     */
    List<SetmealVO> listBySetmeal(Setmeal setmeal);

    /**
     * 根据套餐ID 查询所包含的 菜品 List
     */
    @Select("select * from setmeal_dish sd left join dish d on sd.dish_id = d.id where sd.setmeal_id = #{id}")
    List<DishItemVO> getDishItemBySetmealId(Long id);


    /**
     * 根据条件统计套餐数量 - 起售/停售
     */
    Integer countByMap(Map map);
}
