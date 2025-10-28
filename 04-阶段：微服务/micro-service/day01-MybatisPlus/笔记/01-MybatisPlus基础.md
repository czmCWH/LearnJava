在实际开发中使用 `MyBatis`，我们常常需要编写大量重复的 `CRUD 代码`，这促使了一系列 `MyBatis` 增强框架的诞生，比如：
* `Fluent-MyBatis`：阿里云开发的 MyBatis 增强框架（来自于阿里云·云效产品团队）。
* `MyBatis-Plus`：老牌的 MyBatis 增强框架，开源于 2016 年。
* `Mybatis-Flex`：API 设计与 MyBatis-Plus 高度兼容，支持低成本的框架迁移‌。由社区 2023 年发布。

程序员鱼皮对这几款框架的介绍：<https://mp.weixin.qq.com/s/2ENgTPsmPLi2qaxVswttWg>

# 一、MyBatis-Plus
MyBatis-Plus (简称 `MP`)是一个 MyBatis 的增强工具，在 MyBatis 的基础上只做增强不做改变。
官网：<https://baomidou.com/>，可参考官网提供视频学习！！！

- MyBatis-Plus 的特点：
  1. 不影响原有 MyBatis 的所有功能；
  2. 通过简单配置，即可进行 `单表的 CRUD 操作`：Create（创建）、Read（读取）、Update（更新）、Delete（删除）四种核心数据操作；
  3. 丰富的功能：自动分页、逻辑删除等；

## 1.1、案例 - 使用 MyBatis 进行 CRUD 操作
- 创建数据库表：执行 `笔记/sql/01-mp.sql` 创建数据库、表；

- 实现如下功能：代码实现 `UserMapper.java` 和 `UserMapper.xml`
  新增用户功能
  根据id查询用户
  根据id批量查询用户
  根据id更新用户
  根据id删除用户

- 执行 CRUD 操作：`/test/../UserMapperTest.java`


## 1.2、使用 MyBatis-Plus 进行 CRUD 操作

### 步骤1、引入 `MybatisPlus` 依赖，代替 `Mybatis` 依赖；
```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-spring-boot3-starter</artifactId>
    <version>3.5.12</version>
</dependency>
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-jsqlparser</artifactId>
    <version>3.5.12</version>
</dependency>
```

### 步骤2、自定义 `Mapper` 接口继承 MyBatis-Plus 提供的 `BaseMapper`；
```java
// BaseMapper<实体类> 中默认提供了：insert、update、delete、select 操作；并且 MP 通过扫描实体类获取数据库表信息。
public interface UserPlusMapper extends BaseMapper<User> {
}
```

BaseMapper 提供的方法有：
- 

### 步骤3、配置 BaseMapper 的实体类
自定义 Mapper 继承自 `BaseMapper<实体类>` 后，MyBatis-Plus 通过扫描实体类，并基于反射获取实体类信息作为数据库表信息。
- MyBatis-Plus 默认映射数据库表规则如下：
  1. 把实体类的类名 驼峰转下划线 后， 作为数据库表名；
  2. 把实体类中属性名为 id 的作为 主键字段；
  3. 把实体类中所有属性名 驼峰转下划线 作为数据库表中的字段名；

当使用 BaseMapper 的 实体类 并不规范，无法正确默认映射到数据库表时，可以使用 MP 的注解来正确映射实体类到 数据库表。
- MP作用在实体类的常用注解：
  1. `@TableName`：用来指定数据库表名；
     - ⚠️，日常开发中，指定数据库表名一般以 `tb_` 开头，因为数据库中对象有 view、table、function 等，使用 `tb_` 开头 便于区分。
  2. `@TableId`：用来指定表中的主键字段信息；
  3. `@TableField`：用来指定表中的普通字段信息，常见场景如下：
     - 成员变量名与数据库表字段名不一致时，用 `value` 属性重新设置，如：@TableField("username")；；
     - 成员变量名以is开头，且是布尔值，如：@TableField("is_married")；
     - 成员变量名与数据库关键字冲突，把 `value` 接收属性名用 斜单引号，如：@TableField("`order`")；
     - 成员变量不是数据库字段，需设置 @TableField(exist = false)；
  
- 常用注解说明： `/img/01-MP基础/02-Mybatis-plus常用注解.jpg`

如下代码所示：
```java
@Data
@TableName(value = "user", autoResultMap = true)    // 设置结果自动映射
public class User {
    /*
      IdType 枚举用于指定主键值生成策略；
        AUTO，随数据自动增长
        NONE(1),
        INPUT(2), 需要通过 set 方法自行指定；(避免同时多次插入)
        ASSIGN_ID(3)，自动分配数值字符串ID。即使用接口 IdentifierGenerator 的方法 nextId 来生成 id，默认实现为 DefaultIdentifierGenerator 雪花算法。
        ASSIGN_UUID(4);
     */
    
    @TableId(value = "id", type = IdType.AUTO)  // 主键ID
    private Long id;
}
```

### 步骤4、配置 MyBatis-Plus
MyBatis-Plus 的配置项继承了 MyBatis 的原生配置，和一些自己特有的配置，具体可通过 `MyBatis-Plus` 官网查询。
在项目的 application.yml 可进行如下配置（大多不用配置）：
```yml
mybatis-plus:
  type-aliases-package: com.czm.pojo    # 别名扫描包，作用：在 Mapper.xml 中定义sql语句使用实体类时不用全类名，使用类名即可。
  mapper-locations: classpath*:/mapper/**/*.xml  # 指定扫描 Mapper.xml 文件地址，默认值
  configuration:
    map-underscore-to-camel-case: true # 是否开启下划线转驼峰的映射
    default-enum-type-handler: com.baomidou.mybatisplus.core.handlers.MybatisEnumTypeHandler
    cache-enabled: false  # 是否开启二级缓存，一般为 false
  global-config:
    db-config:
      id-type: assign_id  # 主键 id 为雪花算法生成
      update-strategy: not_null   # 更新策略：只更新非空字段
```

### 步骤5、使用增强 Mapper 执行 `CRUD` 操作，代码实现：`/test/../UserPlusMapperTest.java` 
