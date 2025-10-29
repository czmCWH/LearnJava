# 一、MP 代码生成器
MP 代码生成器，是指可以直接生成 controller、service、mapper、entity 等代码，使得开发者重心放在真正的业务逻辑上。

MP 代码生成器的使用方式：
1. 参考官网，使用代码配置；
2. 官方插件 - Mybatis X
3.  MyBatisPlus 插件，使用见图：`/img/03-MP扩展`


# 二、MP 静态工具类（了解） - Db 
静态工具类 Db 与 IService 的功能基本相同，区别在于使用 Db 时需要指定实体类的类型。
静态工具类 Db 的基本使用：`src/test/.../DbTest.java`

## Db 的应用场景
- 需求：
  1. 改造根据id查询用户的接口，查询用户的同时，查询出用户对应的所有地址;
  2. 改造根据id批量查询用户的接口，查询用户的同时，查询出用户对应的所有地址;
  3. 实现根据用户id查询收货地址功能，需要验证用户状态，冻结用户抛出异常(练习);

- 实现：
  - 需求1、2实现方式：UserService 书写查询逻辑；并注入 AddressService 查询地址；
  - 需求3实现方式，AddressService 书写查询逻辑；并注入 UserService 校验用户状态；

- 问题：UserService 与 AddressService 之间存在相互注入，造成循环依赖的问题。
  - 解决这个问题，`MyBatis-Plus` 提供了 静态工具类 `Db`。

- 代码实现：`UserController.java -> getUser queryByIds`	
    
> 现实场景一般不会出现 2个 `Service` 相互彼此注入，而是采用注入 `Mapper`，所以不必考虑此类问题。


# 三、MP 逻辑删除
逻辑删除，是指基于代码逻辑模拟删除效果，但并不会真正删除数据。实现思路如下:
  - 在表中添加一个字段标记数据是否被删除，对应的实体类中添加成员变量；
  - 当删除数据时把标记置为1；
  - 查询时只查询标记为0的数据；

逻辑删除操作 sql 语句实现：
```sql
# and delted = 0 表示，避免重复删除操作
update user set deleted = 1 where id = 1 and deleted = 0;
```

查询操作：
```sql
select * from user where deleted = 0;
```

一旦使用了逻辑删除，sql语句执行都需要处理 deleted 相关的条件。而 MybatisPlus 提供了逻辑删除功能，无需改变方法调用的方式，而是在底层帮我们自动修改 CRUD 的语句。
开发者只需在 `application.yaml` 文件中配置逻辑删除的字段名称和值即可：
```yaml
mybatis-plus:
  global-config:
    db-config:
      logic-delete-field: deleted # 全局逻辑删除字段名(deleted为实体类属性名称)，此字段类型可以为 boolean、integer
      logic-delete-value: 1 # 逻辑已删除值。可选，默认值为 1
      logic-not-delete-value: 0 # 逻辑未删除值。可选，默认值为 0
```

## 逻辑删除存在的问题
- 会导致数据库表垃圾数据越来越多，影响查询效率；
- SQL中全都需要对逻辑删除字段做判断，影响查询效率；

> 因此，不太推荐采用逻辑删除功能，如果数据不能删除，可以采用把数据迁移到其它表的办法



# 四、枚举处理器
MyBatis 中存在一个 TypeHandler（类型处理器），用于自动实现数据库类型与Java类型的转换。MyBatis 对其进行了扩展，提供了 `MybatisEnumTypeHandler` 用于处理枚举。

步骤1、在 application.yml 中配置全局 枚举处理器，使之生效：
```yaml
mybatis-plus:
  configuration:
    default-enum-type-handler: com.baomidou.mybatisplus.core.handlers.MybatisEnumTypeHandler 
```

步骤2、创建枚举类，并定义相关的枚举值。代码实现：`src/.../enums/UserStatus.java`
```java
@Getter
public enum UserStatus {
    // 枚举值
    NORMAL(1,"正常"),
    FREEZE(2, "冻结");

    @EnumValue      // ⚠️ @EnumValue 注解表示 保存到数据库中的值
    private final Integer code;
    
    // 枚举在进行 JSON 序列化返回时，默认会返回枚举项名，这样返回给用户不友好。
    // Controller 层返回数据给前端时，是通过 SpringMVC 处理的，它提供了 @JsonValue 注解来标记枚举序列化时使用的值
    @JsonValue      // ⚠️ @JsonValue 注解表示 在序列化时使用的是这个属性的值
    private final String message;

    UserStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

```

步骤3、替换 pojo 中的 `User.java` 和 `UserVO.java` 的属性类型为枚举。
```java
@Data
public class UserVO {
    // 使用状态（1正常 2冻结）
//    private Integer status;
    private UserStatus status;
}
```


# 五、JSON 处理器
数据库表设计过程中，有些字段采用 json 数据类型，以字符串的形式存入表中。当查询表返回的到前端的是 JSON 字符串，如何解决直接返回为一个 JSON 对象？

MyBatis 无法实现将数据库中查询的 json 字符串与实体类做转换，MyBatis-Plus 提供了 `AbstractJsonTypeHandler` 类型处理器用于此操作。
实现步骤如下所示：

* 步骤1，使用 `@TableField(typeHandler =)` 注解，指定成员变量的类型处理器，并使之生效
```java
@Data
@TableName(value = "user", autoResultMap = true)    // 设置结果自动映射
public class User {

    // 详细信息
//    private String info;
  
    @TableField(typeHandler = JacksonTypeHandler.class)
    private UserInfo info;
    
}
```

* 步骤2，如上对JSON字符串进行了类型转换，因此存在类型嵌套。`autoResultMap = true` 设置结果自动映射。
