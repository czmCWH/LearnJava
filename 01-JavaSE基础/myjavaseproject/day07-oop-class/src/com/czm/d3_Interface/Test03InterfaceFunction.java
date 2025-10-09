package com.czm.d3_Interface;

public class Test03InterfaceFunction {
    /*
     1、接口的升级问题
      如果接口需要升级，比如增加新的抽象方法。这会导致大幅的代码改动，以前实现接口的类都得实现新的抽象方法。
      若想在不改动以前实现类的前提下进行接口升级，从 Java8 开始，有如下2种方案：
        默认方法（Default Method）
        静态方法（Static Method）

     2、默认方法
      在接口中声明方法时，使用 default 修饰的方法，并且有默认的方法实现，称为默认方法；
      接口的默认方法只能是实例方法；

      默认方法的使用：
        a、当一个类实现的接口中有默认方法时，这个类可以：
            啥也不干，沿用接口的默认实现；
            重新定义默认方法，覆盖默认方法的实现；
            重新声明默认方法，将默认方法声明为抽象方法(此类必须是抽象类）；
        b、当一个接口继承的父接口中有默认方法时，这个接口可以：
            啥也不干，沿用接口的默认实现；
            重新定义默认方法，覆盖默认方法的实现；
            重新声明默认方法，将默认方法声明为抽象方法；

     3、默认方法的细节（了解）
      a、如果父类定义的非抽象方法与接口的默认方法相同时，最终将调用父类的方法；
      b、如果父类定义的抽象方法与接口的默认方法相同时，要求子类实现此抽象方法：
        可以通过 `接口名.super.默认方法` 调用接口的默认方法。
      c、如果(父)接口定义的默认方法与其他(父)接口定义的方法相同时，子类型必须实现此默认方法

     4、静态方法
      接口中定义的静态方法 只能通过接口名 调用，不能被继承。

     5、
     */

    public static void main(String[] args) {
        Child ch = new Child("小王");

        // CollegeStudent 实现了 TeachAbility 的默认方法
        ch.setTeach(new CollegeStudent());
        ch.doSpecialty();

        // Teahcer 未实现 TeachAbility 的默认方法
        ch.setTeach(new Teahcer());
        ch.doSpecialty();

        // 接口的静态方法
        A.doSomething();
        B.doSomething();
        C.doSomething();
    }

}
