package com.czm.mapper;

import com.czm.dto.CategoryDTO;
import com.czm.dto.CategoryPageQueryDTO;
import com.czm.entity.Category;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 插入分类数据
     * @param category
     */
    @Insert("insert into category(id, type, name, sort, status, create_time, update_time, create_user, update_user) values (null, #{type}, #{name}," +
            " #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void insert(Category category);

    /**
     * 条件分页查询，使用 动态sql 实现
     * @param dto
     * @return
     */
    Page<Category> pageQuery(CategoryPageQueryDTO dto);

    /**
     * 根据 ID 删除分类
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    void deleteById(Long id);

    /**
     * 修改分类
     */
    void update(Category category);

    /**
     * 根据类型查询分类
     */
    List<Category> list(Integer type);
}
