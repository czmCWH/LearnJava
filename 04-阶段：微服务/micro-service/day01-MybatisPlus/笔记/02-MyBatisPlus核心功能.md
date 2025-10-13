# 一、BaseMapper 的条件构造器
`MP` 支持各种复杂的 where 条件，可以满足日常开发的所有需求。

通过 `Wrapper` 条件构造器 来构造各种 where 条件。Wrapper 条件构造器的继承关系如：`笔记/img/02-MP核心功能/01-MP条件构造器继承关系.jpg`

1、`Wrapper` 条件构造器的使用说明：
* `QueryWrapper` 和 `LambdaQueryWrapper` 通常用来构建 select、delete、update 的 where 条件部分；
* `UpdateWrapper` 和 `LambdaUpdateWrapper` 通常只有在 set 语句比较特殊才使用;
* 尽量使用 `LambdaQueryWrapper` 和 `LambdaUpdateWrapper` 避免硬编码；

2、`Wrapper` 条件构造器的基本使用：`src/test/..../mapper/WrapperTest.java`

> 书写 Wrapper 时，需要写实体类的属性，直接敲 get 会自动匹配。

# 二、自定义拼接 SQL（使用较少）
我们可以利用 MP 的 Wrapper 来构建复杂的 Where 条件，然后自己定义 SQL 语句中剩下的部分。

1、自定义 SQL 实现步骤：
1. 在 Service 层基于 `Wrapper` 构建复杂的 where 条件；
2. 调用 Mapper 层方法并传递 Wrapper 条件；
3. 在 Mapper 层的方法参数中使用 `@Param("ew")` 注解声明 Wrapper 参数；在自定义 SQL 中以 `${}` 的方式拼接 Wrapper 参数。

> @Param("ew")、${ew.customSqlSegment} 都是固定写法

2、为什么把复杂的 where 条件写在 Service 层？

因为 where 条件是和业务相关的， Service 层尽量不能涉及到表字段的指定；而剩余的 Sql 写在 `mapper层` 或 `xml 文件` 中交给 mapper 处理。

代码实现：`/test/.../mapper/WrapperTest.java -> testCustomSqlSegment()`

# 三、Service 接口
MP 增强了 Mapper 获得了 单表的 CRUD 操作，但 Service 层依然得写相关的方法。
MP 也提供了 Service 接口，使得 Service 层获得 单表的 CRUD 操作方法，能更加高效处理 sql。使用步骤如下：

* 1、自定义 Service 接口继承 IService 接口；

```java
public interface IUserService extends IService<User> {}
```

* 2、自定义 Service 实现类，实现自定义接口并继承 ServicedImpl 类；
```java
public class UserServiceImpl 
        extends ServiceImpl<UserMapper, User>
        implements IUserService {
    
}
```

通过 IService 接口实现 单表的 CRUD 操作，以及使用 `Lambda` 查询条件的代码实现：`src/main/java/com/czm/controller/UserController.java`


# 四、IService 批量新增插入

配置 jdbc 的 `rewriteBatchedStatements=true` 性能最好。
`spring.datasource.url: jdbc:mysql://localhost:3306/mp?rewriteBatchedStatements=true`

代码实现：`src/test/java/com/czm/service/UserServiceTest.java`
