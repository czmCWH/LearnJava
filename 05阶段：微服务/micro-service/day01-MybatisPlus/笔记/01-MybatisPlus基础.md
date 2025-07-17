在实际开发中使用 `MyBatis`，我们常常需要编写大量重复的 `CRUD 代码`（指 Create（创建）、Read（读取）、Update（更新）、Delete（删除）‌四种核心数据操作），
这促使了一系列 `MyBatis` 增强框架的诞生，比如：

* `Fluent-MyBatis`：阿里云开发的 MyBatis 增强框架（来自于阿里云·云效产品团队）。
* `MyBatis-Plus`：老牌的 MyBatis 增强框架，开源于 2016 年。
* `Mybatis-Flex`：API 设计与 MyBatis-Plus 高度兼容，支持低成本的框架迁移‌。由社区 2023 年发布。

程序员鱼皮对这几款框架的介绍：<https://mp.weixin.qq.com/s/2ENgTPsmPLi2qaxVswttWg>

# 一、Mybatis-Plus

`MyBatisPlus` (简称 `MP`)是一个 `MyBatis` 的**增强工具**，在 `MyBatis` 的基础上只做增强不做改变，为简化开发、提高效率而生。
官网：<https://baomidou.com/>，可参考官网提供视频学习！！！

> MyBatis-Plus 不影响原有 MyBatis 的所有功能，只是做了功能增强，它默认提供 单表 的 CRUD 操作。

### 回顾 `Mybatis` 的 `CRUD` 操作案例：
1. 执行 `笔记/sql/01-mp.sql` 创建数据库、表；
2. 编写 `UserMapper.java` 和 `UserMapper.xml` 查询数据库；
3. 执行 `/test/../UserMapperTest.java` 测试类中的方法，验证使用 `Mybatis` 的 `CRUD` 操作；


## 1、MP 增强 Mapper
把案例用 Mybatis-Plus 实现基本步骤：

* 步骤1、引入 `MybatisPlus` 依赖，代替 `Mybatis` 依赖； 

* 步骤2、自定义 `Mapper` 接口并继承 `BaseMapper`；

```java
public interface UserPlusMapper extends BaseMapper<User> {
}
```

* 步骤3、在 `User`实体类 上添加注解声明 数据库表 信息，如果不写则采用默认映射方式。

* 步骤4、在 `application.yml` 中根据需要添加配置；

* 使用增强 Mapper 执行 `CRUD` 操作，代码实现：`/test/../UserPlusMapperTest.java` 

## 2、MP 默认映射数据库表

自定义 `Mapper` 继承自 `BaseMapper<实体类>` 后，`MyBatis-Plus` 通过扫描实体类，并基于反射获取实体类信息 作为 数据库表信息，
默认映射数据库表规则如下：
1. 把实体类的类名 驼峰转下划线 后， 作为数据库表名； 
2. 把实体类中属性名为 id 的作为 主键字段； 
3. 把实体类中所有属性名 驼峰转下划线 作为数据库表中的字段名；

## 3、MP 映射实体类的常用注解

当使用 MP 自定义 Mapper 时，传入的实体类并不规范，无法正确默认映射到数据库表时，可以使用 MP 的注解来正确映射实体类到 数据库表。
MP 作用在 实体类的常用注解：

1. `@TableName`：用来指定数据库表名；
2. `@TableId`：用来指定表中的主键字段信息；
3. `@TableField`：用来指定表中的普通字段信息；

> 常用注解说明： `笔记/img/01-MP基础/02-Mybatis-plus常用注解.jpg`

日常开发中，指定数据库表名一般以 `tb_` 开头，因为数据库中对象有 view、table、function 等，使用 `tb_` 开头 便于区分。

### @TableField 使用场景
1. 成员变量名与数据库字段名不一致，用 `value` 属性重新设置；
2. 成员变量名以`is`开头，且是布尔值；
3. 成员变量名与数据库关键字冲突，把 `value` 接收属性名用 斜单引号；
4. 成员变量不是数据库字段时，把 `exist` 属性设置为 false 来排除此字段，即 `@TableField(exist = false)`；


## 4、MP 常见配置
见 `笔记/img/01-MP基础/03-MyBatis-Plus 常见配置.jpg`，也可以通过 `MyBatis-Plus` 官网查询。
