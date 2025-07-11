package com.czm.mapper;

import com.czm.anno.AutoFill;
import com.czm.dto.DishPageQueryDTO;
import com.czm.entity.Dish;
import com.czm.enumeration.OperationType;
import com.czm.result.PageResult;
import com.czm.vo.DishVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 菜品 mapper
 */

@Mapper
public interface DishMapper {

    /**
     * 根据分类ID查询菜品的数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 新增菜品
     * @param dish
     */
    @AutoFill(OperationType.INSERT) // 公共字段自动填充
//    @Options(useGeneratedKeys = true, keyProperty = "id")   // 数据插入成功后，获取主键值赋值给 dish.id
//    @Insert("insert into dish values (null, #{name}, #{categoryId}, #{price}, #{image}, #{description}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void insert(Dish dish);

    /**
     * 菜品分页查询。由于需要根绝 菜品名称、分类、状态 条件进行查询，所以需要使用 动态 sql 实现
     * @param dto
     * @return
     */
    Page<DishVO> list(DishPageQueryDTO dto);

    /**
     * 根据ID查询菜品
     */
    Dish selectById(Long id);

    /**
     * 根据 ID 列表 查询 菜品列表
     * @param ids
     * @return
     */
    List<DishVO> selectByIds(List<Long> ids);

    /**
     * 批量删除菜品
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 修改菜品
     * @param dish
     */
    @AutoFill(OperationType.UPDATE)     // 补充公共字段
    void update(Dish dish);
}
