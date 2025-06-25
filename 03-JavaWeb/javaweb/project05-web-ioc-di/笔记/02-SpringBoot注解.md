# Spring Boot 项目基础注解
@SpringBootApplication --- 启动类声明注解，SpringApplication.run 方法来启动程序。
    包含 @ComponentScan 注解。

- Controller 层
  @RestController，修饰类，请求处理类。<==> @Controller + @ResponseBody
    @Controller，声明为 bean 对象。
    @ResponseBody，接收响应请求，将方法返回值(实体对象、集合)以 `JSON/XML` 数据直接响应。

  @RequestMapping(value = "/info", method = RequestMethod.GET)，修饰方法 or 类，请求映射路径注解。
  简化版 @RequestMapping，限定特定 HTTP 方法：
    @PostMapping("/depts") 		// 增
    @DeleteMapping("/depts") 	// 删
    @PutMapping("/depts") 		// 改
    @GetMapping("/depts")		// 查
  @RequestMapping 修饰类时，来对其简化版抽取公共路径。

- IOC 控制反转
  @Component，修饰 实现类，声明 bean 对象的基础注解。其衍生注解：
    @Controller，标准在控制层，都用 @RestController。
    @Service，标注在业务逻辑层。
    @Repository，标注在数据访问层，由于与 mybatis 整合，很少用。

- DI 依赖注入
  @Autowired，修饰 实现类的属性变量，自动注入 该变量指定接口类型 的依赖 bean 对象。
  @Resource(name = "DeptServiceImpl2")，JavaEE规范，等价于  @Autowired + @Qualifier
 

- Mapper 层(Mybatis)
  
  