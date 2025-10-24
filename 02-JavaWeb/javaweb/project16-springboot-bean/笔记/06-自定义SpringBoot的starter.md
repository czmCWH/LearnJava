# 自定义 SpringBoot 项目的 starter
场景：在实际开发中，经常会定义一些公共组件，提供给各个项目团队使用。而在 SpringBoot 的项目中，
一般会将这些公共组件封装为 SpringBoot 的 starter，这些 Starter 包含 起步依赖 和 自动配置 的功能。


案例需求：自定义 `aliyun-oss-spring-boot-starter`，完成阿里云0SS操作工具类 `AliyunOSSOperator` 的自动配置。
目标：引入起步依赖引入之后，要想使用阿里云0SS，注入 `AliyunOSSOperator` 直接使用即可。
实现步骤：
  1. 创建 `aliyun-oss-spring-boot-starter` 模块；
  2. 创建 `aliyun-oss-spring-boot-autoconfigure` 模块，在 `starter` 中引入该模块；
  3. 在 `aliyun-oss-spring-boot-autoconfigure` 模块中的定义自动配置功能，并定义自动配置文件 `META-INF/spring/xxxx.imports`；

