# Spring Boot 项目常用注解
@SpringBootApplication --- 启动类声明注解，SpringApplication.run 方法来启动程序。
    包含 @ComponentScan 组件扫描注解。

- Controller 层
  @RestController，修饰类，请求处理类。<==> @Controller + @ResponseBody
    @Controller，声明为 bean 对象。
    @ResponseBody，接收响应请求，将方法返回值(实体对象、集合)以 `JSON/XML` 数据直接响应。

  @RequestMapping(value = "/info", method = RequestMethod.GET)，修饰方法、类，请求映射路径注解。
    - @RequestMapping 修饰类时，常用来对其简化版抽取公共路径。
    - 简化版 @RequestMapping，限定特定 HTTP 方法：
      @PostMapping("/depts") 		// 增
      @DeleteMapping("/depts") 	// 删
      @PutMapping("/depts") 		// 改
      @GetMapping("/depts")		// 查

  修饰请求方法形参数的常用注解：
    @RequestParam(value = "id", required = false, defaultValue = "default")，`绑定单个 URL查询参数 或 form表单`，可省略。如：/depts?id=1
      - 用于接收数组，如：@RequestParam(value = "categories") List<String> categories
      - 接收多个不确定参数时，封装为 DTO 实体类 或者 @RequestParam Map<String, Object> params
    @PathVariable("路径参数名称")，用于绑定 `单个URL路径参数`，如：/depts/{id}/{name}
    @RequestBody，绑定`请求体中的json数据`到 DTO 数据传输对象。

- Dao层 / Mapper层(Mybatis)
  - @Repository 基本不用，后续使用 Mybatis 的注解


- IOC 控制反转
  @Component，修饰 实现类，声明 bean 对象的基础注解。其衍生注解：
  @Controller，标准在控制层，都用 @RestController。
  @Service("DeptServiceImpl2") ，标注在业务逻辑层。
  @Repository，标注在数据访问层，由于与 mybatis 整合，很少用。

- DI 依赖注入
  - @Autowired，修饰实现类的属性变量，自动注入该变量指定接口类型的 bean对象。
  - 解决同接口多个实现类时，bean注入的问题
    - @Primary，修饰实现类
    - @Autowired + @Qualifier("deptServiceImpl2")，修饰成员变量
    - @Resource(name = "DeptServiceImpl2")，JavaEE规范，等价于  @Autowired + @Qualifier
  

- 日志技术 - Logback
  - @slf4j 注解类即可使用，前提引入lombok依赖；


- 项目参数配置化
  - `@Value("${配置文件中的key}")`，修饰Bean的成员属性，用于从配置文件中注入属性值到Bean的字段中
  - `@ConfigurationProperties`，用于将将一组相关的配置属性绑定到一个 Java 对象。


- 全局异常处理器，统一捕获 Controller 抛出的异常
  `@RestControllerAdvice`，用于标记全局异常处理类
  `@ExceptionHandler`，用于在异常处理类中，定义方法来处理特定类型的异常


- 统一拦截技术
  - Filter 过滤器，Servlet 规范
    - 必须使用 `@ServletComponentScan` 标记启动类，开启对 Servlet 组件的支持
    - @WebFilter(urlPatterns = "/app/*")，用于指定该过滤器拦截哪些请求接口
  - Interceptor 拦截器，属于 Spring 框架
    - 需 `@Configuration+WebMvcConfigurer`，向 Spring 环境注册 Interceptor 拦截器

