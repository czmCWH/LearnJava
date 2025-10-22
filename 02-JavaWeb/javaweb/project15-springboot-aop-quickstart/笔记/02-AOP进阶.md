# 一、Spring AOP 通知类型
Spring AOP 通知，是指将共性功能抽取之后定义的方法。在 Spring AOP 中根据通知方法 执行时机 的不同，将通知类型分为以下常见的五类:

1. `@Around`：环绕通知，此注解标注的通知方法在目标方法前、后都被执行；--- 最重要，最强大!

2. `@Before`：前置通知，此注解标注的通知方法在目标方法前被执行；

3. `@After`：后置通知，此注解标注的通知方法在目标方法后被执行，`无论目标方法是否有异常都会执行`；

4. `@AfterReturning`：返回后通知，此注解标注的通知方法在目标方法后被执行，`目标方法有异常不会执行`；

5. `@AfterThrowing`：异常后通知，此注解标注的通知方法发生异常后执行；

> 代码实现：`/aspect/MyAspect1.java`

- 注意事项：⚠️
  - @Around 环绕通知需要自己调用 `Proceeding]oinPoint.proceed()` 来让原始方法执行，其他通知不需要考虑目标方法执行。
  - @Around 环绕通知方法的返回值，必须指定为 Object 类型，用来接收原始方法的返回值。

`` 注解，作用是将公共的切点表达式抽取出来，需要用到时引用该切点表达式即可。

## @PointCut 抽取切入点表达式
`@PointCut` 注解，作用是将公共的切点表达式抽取出来，需要用到时引用该切点表达式即可。

```java
/**
 * 使用 @Pointcut 注解 抽取公共切点表达式，提高代码的复用性
 *  ⚠️ 方法的权限修饰符对 切点表达式 的作用：
 *      private：当前声明的切入点表达式，仅能在当前切面类中引用该表达式
 *      public：当前声明的切入点表达式，在其它外部的切面类中也可以引用该表达式
 */
@Pointcut("execution(* com.czm.service.impl.DeptServiceImpl.*(..))")
public void pt() {}

// 在通知中，使用 方法调用的方式 引入 自定义的公共切入点表达式
@Around("pt()")
```

> 代码实现：`/aspect/MyAspect2.java`



# 二、Spring AOP  通知顺序
当有多个切面的切入点都匹配到了目标方法，目标方法运行时，多个通知方法都会被执行。

同一个切入点有多个通知方法时，它们的执行顺序如下：

* 默认顺序：在不同 切面类 中，默认按照切面类的 类名字母排序：
  - 目标方法前的通知方法（即前置通知）：字母排名靠前的先执行；
  - 目标方法后的通知方法：字母排名靠前的后执行；

* 自定义执行顺序：用 `@0rder(正负数字)` 加在 切面类 上来控制顺序：--- 推荐！
  - 目标方法前的通知方法（即前置通知）：数字小的先执行；
  - 目标方法后的通知方法：数字小的后执行；
  - 如果不写数字，即 `@0rder()`，则最后执行；
 
```java
@Slf4j
@Order(0)   // @Order 注解，用于控制 Spring AOP 通知执行的顺序
@Aspect
@Component
public class MyAspect2 {

}
```


# 三、切入点表达式
介绍：描述切入点方法的一种表达式。
作用：用来决定项目中的那些方法需要加入通知。
常见的2种使用形式：⚠️
    1. `execution(需要匹配的方法签名)`：根据方法签名来匹配；--- 优先考虑使用！
    2. `@annotation(注解的签名)`：根据注解匹配；

如果 `execution` 切入点表达式方便描述指定的方法，就使用 `execution` 表达式。否则，就使用 `@annotation` 切入点表达式。

## 3.1、声明切入点表达式 - 基于 execution
`execution` 主要根据方法的返回值、包名、类名、方法名、方法参数等信息来匹配，语法为：
```
execution(访问修饰符? 返回值 包名.类名.?方法名(方法参数) throws 异常?)
```
execution 语法解析：
  1、其中带 `?` 的表示问号这一部分可以在定义 execution 切面表达式 时省略：
    - `访问修饰符?`：即可以省略访问修饰符，如：public、protected；
    - `包名.类名.?`：即可以省略 `包名.类名`; --- ⚠️不建议省略!
    - `throws 异常?`：即可以省略方法上声明抛出的异常，不是实际抛出的异常；
  
  2、execution 切面表达式 中可以使用通配符描述切入点：
    - `*` 一个星号，表示单个独立的任意符号，可以通配任意返回值、包名、类名、方法名、任意类型的一个参数，也可以通配包、类、方法名的一部分。如下：
      ```java
      // * 依次表示 返回值任意类型、某一级包名是任意的、类名是任意的、update开头的任意方法、任意类型的一个方法参数
      execution(* com.*.service.*.update*(*))
      ```
    - `..` 2个点，表示多个连续的任意符号，可以通配 任意层级的包 或 任意类型、任意个数的参数。如下：
      ```java
      // .. 依次表示 com.czm下的任意层级的包、方法参数为任意个
      execution(* com.czm..DeptService.*(..))
      ```

  3、根据业务需要，可以使用 `且(&&)、或()、非(!)` 来组合比较复杂的切入点表达式

> 代码实现：`/aspect/MyAspect3.java`

### 书写 execution 切入点表达式注意点：
- 所有 业务方法名(service 层) 在命名时尽量规范，方便切入点表达式快速匹配。如:`findXxx，updateXxx`。
- 描述切入点方法通常 基于接口 描述，而不是直接描述实现类，增强拓展性。
- 在满足业务需要的前提下，尽量缩小切入点的匹配范围。如：包名尽量不使用..，使用*匹配单个包。


## 3.2、声明切入点表达式 - 基于注解
@annotation 切入点表达式，用于匹配标识有特定注解（一般为开发者自定义的注解）的方法。
代码实现：，`Log.java`，`/aspect/MyAspect4.java`，`DeptServiceImpl.java`
```java
// 步骤1、自定义 Log 注解
@Retention(RetentionPolicy.RUNTIME) 
@Target(ElementType.METHOD)    
public @interface Log {
}

// 步骤2、声明 @annotation 切入点表达式，方式1
@Around("@annotation(com.czm.anno.Log)")

// 2、声明 @annotation 切入点表达式，方式2
@Pointcut("@annotation(com.czm.anno.Log)")    // 表示只匹配 @Log 自定义注解的方法
public void pt2() {}

// 步骤3、在需要被 Spring AOP 控制的方法上添加 @Log 注解
@Log   // 自定义注解
public List<Dept> list() {
  // 1、调用 Mapper 方法，获取数据库中的数据并返回
  return deptMapper.list();
}
```

# 四、连接点
在 `Spring` 中用 `JoinPoint` 抽象了连接点，用它可以获得方法执行时的相关信息，如目标类名、方法名、方法参数等。
- 对于 `@Around` 通知，获取连接点信息只能使用 `ProceedingJoinPoint`；
- 对于其它四种通知，获取连接点信息只能使用 `JoinPoint` ，它是 `ProceedingJoinPoint` 的父类型；

> 代码实现：`/aspect/MyAspect5.java`