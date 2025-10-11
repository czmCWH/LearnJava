package com.czm.d4_polymorphism;

public class Test01 {
    /*
     1、多态（Polymorphism）
      多态是指 具有多种形态；同一操作作用于不同的对象，产生不同的执行结果。

     2、多态的体现
      父类（接口）类型指向子类对象，调用子类重写的方法。

      多态的原理：
        JVM 会根据引用变量指向的具体对象来调用对应的方法。
        这个行为叫做：虚方法调用（virtual method invocation），类似于 C++ 中的虚函数调用。

     3、多态的特点
      多态是对象、行为的多态，Java中的属性(成员变量)不谈多态

      多态不能直接调用子类独有的功能。可以通过 强制类型转换解决。
      强制类型转换语法：子类 变量名 = (子类) 父类型变量;

     4、多态的好处
      在多态形式下，右边对象是解耦合的，更便于扩展和维护。
      定义方法时，使用父类类型的形参，可以接收一切子类对象，扩展性更强、更便利。

     5、instanceof
      可以通过 instanceof 判断某个类型是否属于某种类型。
      instanceof 执行时会进行编译时类型检查‌，如果两个实例无继承关系编译器直接阻止该判断。

      进行强制类型转换，编译阶段不会报错。但是，运行时，如果发现对象的真实类型与强转后的类型不同会报错(ClassCastException)。
      强转时，java 建议使用 instanceof 关键字，判断当前对象的真实类型，再进行强转：
            p instanceof Student

     */

    public static void main(String[] args) {

        // 多态的体现：testAnimal 方法参数类型 Animal 接收子类的对象
        testAnimal(new Dog());
        testAnimal(new Cat());

        // 多态的体现：testEat 方法参数类型 Eatable 接收子类的对象
        testEat(new Dog());
        testEat(new Cat());


        // 多态时，类方法的调用只看声明的类型
        Dog dog = new Dog();
        dog.sleep();    // Dog --- 趴着睡！
        Animal animal = new Dog();
        animal.sleep(); // Animal --- sleep



        // 多态时，访问成员变量是根据声明类型来访问，访问成员方法时才会根据具体对象类型。
        // ⚠️ 所以，Java 中 封装 不建议将成员变量暴露出来！！！
        Dog dog2 = new Dog();
        System.out.println("dog2.age = " + dog2.age);   // 2
        System.out.println("dog2.getAAage = " + dog2.getAAage());   // 1
        System.out.println("dog2.getDAage = " + dog2.getDAage());   // 2

        Animal animal2 = new Dog();
        System.out.println("animal2.age = " + animal2.age); // 1
        System.out.println("animal2.getAAage = " + animal2.getAAage()); // 1


        Dog dog3 = new Dog();
        System.out.println(dog3 instanceof Dog);    // true
        System.out.println(dog3 instanceof Animal); // true
        System.out.println(dog3 instanceof Eatable);    // true
//        if (dog3 instanceof String) { // 无继承关系，编译时类型检查‌会直接报错！！！
//            System.out.println("dog3 instanceof String");
//        }

    }

    static void testAnimal(Animal animal) {
        animal.speak();
    }

    static void testEat(Eatable eat) {
        eat.eat();
    }
}
