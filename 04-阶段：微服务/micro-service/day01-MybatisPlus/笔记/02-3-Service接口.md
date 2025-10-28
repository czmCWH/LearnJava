# 一、增强 Service 接口
MP 增强了 Mapper 获得了 单表的 CRUD 操作，但 Service 层依然得写 Mapper 相关的方法。
MP 也提供了 Service 接口，使得 Service 层获得 `单表的CRUD操作` 方法，能更加高效处理 sql。

https://baomidou.com/guides/data-interface/#_top

## 1.1、IService 接口的基本功能
- save 新增记录
- remove 删除记录
- update 更新记录
- get 查询单条记录
- list 查询集合
- count 查询总记录数
- page 分页查询
- lambdaQuery/lambdaUpdate 避免在上面的的方法中 new wrapper，适用于复杂条件。

## 1.2、使用 MyBatis-Plus 的 Service 接口

* 步骤1、让自定义的 Service 接口继承 IService 接口：
```java
public interface IUserService extends IService<User> {
    
}
```

* 步骤2、让自定义 Service 接口的实现类继承 ServiceImpl 类：
```java
public class UserServiceImpl extends ServiceImpl<UserPlusMapper, User> implements IUserService {
    
}
```

* 步骤3、使用自定义的 Service 接口直接进行 `单表的CRUD操作`
代码实现：`src/.../UserController.java`、`UserServiceImpl.java`


## 1.3、项目开发中 BaseMapper 和 IService 提供的方法如何选择呢？
MyBatis-Plus 中 BaseMapper 和 IService 提供的方法有许多是重复的，那么项目开发中该如何使用他们？

基本的 单表的CRUD操作 可直接在 Controller 中调用 IService 即可；
对于复杂数据库表操作，可能需要编写业务逻辑，这就需要在 Service 中自定义方法来实现；如果业务逻辑实现时需要自定义 SQL 语句，
这就需要借助 BaseMapper 的自定义方法。


# 二、IService 批处理
案例：批量插入10万条用户数据
实现方案：
    - 普通 for 循环单个插入；
        速度极慢，不推荐！
    - IService 的批量插入方法 `saveBatch`；
        基于预编译的批处理，性能不错！
代码实现：`src/test/.../UserServiceTest.java`

* MyBatis-Plus 的 `saveBatch` 方法底层实现原理：
MP 采用的是 JDBC 底层的预编译方案，这种方案会把提交的 User 数据进行遍历编译为预编译 sql 语句，save操作时一次性提交到 MySql。
也就是1000条 User 数据只发送一个网络请求到 mysql。

由于进行了 预编译 sql 操作，性能也造成了影响。那么优化方式是，将1000条sql合并为一条sql，一次性执行。即，sql实现如下所示：
```sql
insert into tb_user values (....), (....), (....)....; 
```

优化方案1：自定义sql语句，利用<forecah> 标签实现
优化方案2：
    依然使用 IService 的批量插入方法 `saveBatch`。开启 MySql 驱动的 `rewriteBatchedStatements=true` 性能最好。
    即让 MySql 驱动对 sql 语句进行处理。
    `spring.datasource.url:jdbc:mysql://localhost:3306/mp?
    useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=Asia/shanghai&rewriteBatchedStatements=true`



