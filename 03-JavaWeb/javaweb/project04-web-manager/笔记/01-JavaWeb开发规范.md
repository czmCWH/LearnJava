# 一、JavaWeb 开发规范

> 早期前后端混合开发：前后端项目在一个工程里，前端项目会放在 `src/main/resources/static/` 目录下。
> 
> 前后端分离开发：前端项目部署到 `nginx 服务器`；后端项目部署到 `tomcat 服务器`。

## 1、前后端分离开发

接口文档由页面原型+需求文档而来，接口文档4要素：请求路径、请求方式、请求参数、响应数据；

## 2、请求接口遵守 Restful 风格

`REST(REpresentational State Transfer)`，表述性状态转换，它是一种软件架构风格。
`REST风格` 使用 `URL定位资源`，`HTTP动词（GET/POST/PUT/DELETE）` 描述操作。
它没有业务单词出现url路径上，便于后期代码维护。

> 注意： 
> REST是风格，是约定方式，约定不是规定，可以打破，有些公司项目并不会使用。 
> 描述功能模块通常使用复数形式(加s)，表示此类资源，而非单个资源。如：users、books...


# 二、模拟网络请求工具
使用前后端分离开发模式时，后端可以借助如下工具对开发接口进行测试：
* Postman：使用简单，之前使用。
* Apifox：功能强大，现在常用。


## 1、Apifox

介绍：`Apifox` 是一款集成了Api文档、Api调试、Api Mock、Api测试的一体化协作平台。
作用：接口文档管理、接口请求测试、Mock服务。
官网：<https://apifox.com/>


# 三、实现案例 - 部门信息查询

案例：加载并读取 `dept.txt` 文本中的数据，并通过 `http` 请求响应返回。

实现思路
1、加载并读取 `dept.txt` 文本中的数据。
2、解析文本中的数据，并将其封装为集合。
3、响应数据(json格式)。

实现步骤：
步骤1、创建 `Spring boot` 模块；
步骤2、添加项目依赖 `lombok`、`commons-io`；
步骤3、创建 `controller` package，封装处理 http 请求类；
步骤4、创建 `entity` package，封装实体类数据；

##  统一响应结果对象
日常开发中返回的数据类型是多种多样的，这会导致后端不方便管理，难以维护，前端不方便解析。
因此，无论执行的是增删改查什么样的业务操作，需要都返回一个统一的响应结果对象。

响应结果对象
1、响应状态标识位
2、错误信息
3、响应数据

```java
public class Result {
    private Integer code;   // 编码请求执行成功或者失败：1，成功；0，失败
    private String msg;     // 错误信息
    private Object data;    // 响应数据，兼容所有格式
}
```

限制接口请求方式必须为GET，可以通过 @RequestMapping 注解的 method 属性指定，也可以使用 @GetMapping。

# 四、三层架构 + 面向接口编程