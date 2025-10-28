package com.czm.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czm.pojo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 改造为 Mybatis Plus 实现的 Mapper
 * 不用写任何 代码，此 Mapper 即可实现常用的 CRUD 操作。
 */

/**
 * Mapper 继承自 BaseMapper<实体类> 后，Mybatis Plus 的默认操作如下：
 *  1、把实体类的类名 驼峰转下划线 作为数据库表名；
 *  2、把实体类中属性名为 id 的作为 主键字段；
 *  3、把实体类中所有属性名 驼峰转下划线 作为数据库表中的字段名；
 */
public interface UserPlusMapper extends BaseMapper<User> {

    /**
     * MyBatis-Plus 自定义 SQL
     * @Param("ew")、${ew.customSqlSegment} 都是固定写法
     */
    @Update("update user set balance = balance - #{amount} ${ew.customSqlSegment}")
    void updateBalanceByWrapper(@Param("amount") int amount, @Param("ew") LambdaQueryWrapper<User> wrapper);

    /**
     * 根据用户ID扣减金额
     */
    @Update("update user set balance = balance - #{amount} where id = #{id}")
    void deductBalanceById(@Param("id") Long id, @Param("amount") Integer amount );
}
