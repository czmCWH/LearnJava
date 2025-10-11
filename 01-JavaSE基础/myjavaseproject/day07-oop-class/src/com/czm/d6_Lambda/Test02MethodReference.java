package com.czm.d6_Lambda;

import java.util.Arrays;

public class Test02MethodReference {

    /*
     1、方法引用 Method Reference
      如果 Lambda 中的内容仅仅是调用某个方法，可以使用方法引用（Method Reference）来简化。

      简化的种类如下：
        引用静态方法：   className::staticMethodName
        引用特定对象的实例方法：    ObjectName::instanceMethodName
        引用特定类型的任意对象的实例方法：   ClassName::methodName
        引用构造方法：     className::new
        引用当前类中定义的实例方法：      this::instanceMethodName
        引用父类中定义的实例方法：       super::instanceMethodName

     */

    public static void main(String[] args) {

        Methodable m = (a, b) -> Math.max(a, b);
        System.out.println(m.method(10, 20));

        // 1、Lambda 表达式 使用 方法引用 简化：引用静态方法
        Methodable m2 = Math::max;
        System.out.println(m2.method(10, 20));

        // 2、Lambda 表达式 使用 方法引用 简化：引用特定对象的实例方法
        execute(v -> System.out.println(v), 10);
        // 对象::实例方法
        execute(System.out::println, 10);

        execute(v -> new Person().setA(v), 20);
        // 对象::实例方法
//        execute(Person::new, 20).setA(20);

        // 3、Lambda 表达式 使用 方法引用 简化：引用特定类型的任意对象的实例方法
        String[] strings = { "ad", "abb", "adc", "Ad", "AAb" };
        Arrays.sort(strings);
        System.out.println(Arrays.toString(strings));   // [AAb, Ad, abb, ad, adc]

//        Arrays.sort(strings, (o1, o2) -> o1.compareToIgnoreCase(o2));
        // 默认会把 参数1 作为 compareToIgnoreCase 的调用者，把 参数2 作为入参
        Arrays.sort(strings, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(strings));   // [AAb, abb, Ad, ad, adc]

        // 4、Lambda 表达式 使用 方法引用 简化：引用构造方法
        Methodable2 m3 = v -> new Human(v);
        m3.test(19);
        Methodable2 m4 = Human::new;
        m4.test(19);
        Methodable2 m5 = int[]::new;
        m5.test(19);


    }

    public static void execute(Methodable2 m, int v) {
        m.test(v);
    }

    public class Animal {
        public void setA(int a) {
            System.out.println("person set a = " + a);
        }

        public void show() {
            Methodable2 m = v -> setA(v);
            m.test(19);
            // 5、Lambda 表达式 使用 方法引用 简化：引用当前类中定义的实例方法
            Methodable2 m2 = this::setA;
            m2.test(19);

        }
    }

    public class Dog extends Animal {
        public void doSomething() {
            Methodable2 m = v -> super.setA(v);
            m.test(19);

            // 6、Lambda 表达式 使用 方法引用 简化：引用父类中定义的实例方法
            Methodable2 m2 = super::setA;
            m2.test(19);
        }
    }
}
