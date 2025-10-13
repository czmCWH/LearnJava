# 一、代码生成器

可以直接生成 controller、service、mapper、entity 等代码，使得开发者重心放在真正的业务逻辑上。

## 方式1：IDEA 插件

## 方式2： MyBatis-Plus 官方的静态工具类


# 二、静态工具类（了解） - Db 
如果在 `Service` 层实现逻辑时，需要2个 `Service` 中相互注入彼此（即：UserService ---> AddressService，AddressService ---> UserService ），就会造成循环依赖。

为了解决这个问题，`MyBatis-Plus` 提供了 静态工具类 `Db`。代码实现如下：

    src/test/.../DbTest.java    // Db 工具类的基本使用
    UserController.java -> getUser queryByIds	// 查询用户信息并返回用户地址

> 现实场景一般不会出现 2个 `Service` 相互彼此注入，而是采用注入 `Mapper`，所以不必考虑此类问题。

# 三、枚举处理器
1、在 application.yml 中配置全局枚举处理器。

2、创建枚举类，并定义相关的枚举值。

3、替换 pojo 中的 `Entity` 和 `VO` 的属性类型为枚举。

代码实现：`User.jva` 和 `UserVO.java`

# 四、JSON 处理器
数据库表设计过程中，有些字段采用 json 数据类型。当查询表返回的到前端的是 JSON 字符串，如何解决直接返回为一个 JSON 对象？
