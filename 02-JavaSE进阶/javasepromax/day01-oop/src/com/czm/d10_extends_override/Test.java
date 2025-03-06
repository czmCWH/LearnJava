package com.czm.d10_extends_override;

public class Test {
    /*
     1、什么是方法重写?
        当子类觉得父类中的某个方法不好用或者无法满足自己的需求时，子类可以重写一个方法名称、参数列表一样的方法，去覆盖父类的这个方法，这就是方法重写。

        注意：重写后，方法的访问，Java 会遵循就近原则，访问重写的方法。

     2、方法重写的其它注意事项

        重写小技巧：使用 @Override 注解，他可以指定 java 编译器，检查我们方法重写的格式是否正确，代码可读性也会更好。

        子类重写父类方法时，访问权限必须大于或者等于父类该方法的权限(public>protected>缺省)。

        重写的方法返回值类型，必须与被重写方法的返回值类型一样，或者范围更小。

        私有方法、静态方法不能被重写，如果重写会报错的。

     3、重写的应用场景
        例如：重写 父类的 toString()，以便返回对象的内容。
     */
    public static void main(String[] args) {
        Tiger tiger = new Tiger();
        tiger.run();

        // ⚠️：调用 toString() 默认是调用 Object 中的方法，返回所谓对象的地址值。
        System.out.println(tiger.toString());
    }
}
