package com.czm.d3_annotation;

/*
 1、注解(Annotation) --- 重点！
  注解，就是 Java 代码里的特殊标记，比如：@Override、@Test等。
  作用：让其他程序根据 注解信息 来决定怎么执行该程序。如：Junit 框架 @Test 注解的方法就可以被当成测试方法来执行。

  注解可以用在类上、构造器上、方法上、成员变量上、参数上、等位置处。

 2、自定义注解语法
  public @interface 注解名称 {
     public 属性类型 属性名() [default 默认值];
  }

  a、注解的特殊属性名：value
    如果注解中只有一个 value 属性，使用注解时，value名称可以不写！

 3、注解的原理：
  注解本质是一个继承自 Annotation 的接口；
  使用注解是创建 注解接口  的匿名内部类对象。如：使用 @MyAnnotationA 注解是创建一个匿名内部类对象
     @MyAnnotationA("只有一个 value 属性的注解！")
     private Integer count;

 如下，MyAnnotation 自定义注解用在类、成员变量、方法上：
 */

@MyAnnotation(name = "注解测试类", age = 22, hobby = {"打篮球", "打排球", "打乒乓球"})
public class Test01 {

    @MyAnnotation(name = "猫咪", age = 2, hobby = {"抓老鼠", "半夜不睡"})
    private static String tag;

    @MyAnnotation(name = "狗子", age = 2, hobby = {"看门", "吃屎"})
    public static void doSome() {
        System.out.println("--- 给方法添加自定义注解");
    }

    @MyAnnotationA("只有一个 value 属性的注解！")
    private Integer count;

    public static void main(String[] args) {
        String name = tag.getClass().getAnnotation(MyAnnotation.class).name();
        System.out.println(name);
    }
}
