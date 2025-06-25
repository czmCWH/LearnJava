# Mybatis 常用注解
- Mapper 层
    @Insert("insert into ...") 增
    @Delete("delete from ...") 删
    @Update("update dept set ...") 改
    @Select("select * from ...") 查
    @Results({@Result(...), ... }) 封装返回数据映射