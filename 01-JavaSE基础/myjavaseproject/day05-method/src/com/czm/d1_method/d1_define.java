package com.czm.d1_method;

public class d1_define {
    /*
    1、方法
       方法是一种用于执行特定任务或操作的代码块，就是一个功能，它可以接收数据进行处理，并返回一个处理后的结果。
       方法是项目功能的基本单元。
       Java 中的方法就是其它编程语言中的函数。

    2、方法的完整定义格式：
       修饰符 返回值类型 方法名([形参列表]) {
          方法体代码(需要执行的功能代码)
          return 返回值;
       }
       修饰符，目前统一写 public static
     方法如何使用：
          方法必须被调用才能执行，调用格式：方法名(数据)

    3、方法的优点
    提高了代码的复用性，提高了开发效率；
    Java项目几乎就是一个一个方法组成的，每个方法是一个功能，这样能使程序的逻辑更清晰(可读性、维护性也更好)。
    */

    public static void main(String[] args) {

        int max = getMax(90, 100);
        System.out.println("较大值 = " + max);

        System.out.println("------ 可变参数 与 数组类型参数");
//        sum1();   // 如果数组作为参数，参数必须传递，否则会报错。
        sum1(new int[] {1,2,3});
        sum2(1,2,3);
        sum2();     // 可变参数不传递值，不会报错。

        System.out.println("---- 打印");
        testPrint();
    }

    // 1、方法的完整定义
    public static int getMax(int a, int b) {
        int max = a > b ? a : b;
        return max;
    }

    // 2、无参数、无返回值的方法
    // 不需要接收参数，不用书写形参列表；
    // 不返回任何值，返回值类型声明为 void；
    public static void printSome() {
        System.out.println("hello world !");
        System.out.println("hello world !");
        System.out.println("hello world !");
    }

    /**
     3、可变参数方法 与 数组类型的方法
     调用方法时，可变参数可以不传递任何值；
     可变参数必须是方法的最后一个参数；
     */
    public static int sum1(int[] numbers) {
        System.out.println(numbers);
        System.out.println(numbers.length);
        int result = 0;
        for (int number : numbers) {
            result += number;
        }
        return result;
    }
    public static int sum2(int... numbers) {
        System.out.println(numbers);
        System.out.println(numbers.length);
        int result = 0;
        for (int number : numbers) {
            result += number;
        }
        return result;
    }

    /*
     4、print 格式化打印
     */
    public static void testPrint() {
        // 如下所示，println 方法只能接收一个参数，因此遇到多个参数时只能拼接
        String name = "jack";
        byte age = 18;
        System.out.println("my name is " + name + " and age is " + age );

        // 使用 printf 来接收可变参数，格式化避免拼接
        System.out.printf("my name is %s and age is %d \n", name, age);
    }

    /*
     使用方法的注意事项：
        1、方法在类中的位置放前放后无所谓，但一个方法不能定义在另一个方法里面。

        2、方法的返回值类型写void(无返回声明)时，方法内不能使用return返回数据，如果方法的返回值类型写了具体类型，方法内部则必须使用return返回对应类型的数据。

        3、return语句的下面，不能编写代码，属于无效的代码，执行不到这儿。
、
        4、方法不调用就不会执行，调用方法时，传给方法的数据，必须严格匹配方法的参数情况。

        5、调用有返回值的方法，有3种方式:
            1、可以定义变量接收结果 2、或者直接输出调用，3、甚至直接调用（即调用方法不接收返回值）;

        6、调用无返回值的方法，只有1种方式:1、只能直接调用。
     */

    /*
     ⚠️
     1、栈帧（Frame）
      栈帧随着方法的调用而创建，随着方法结束而销毁，用于存储方法的局部变量信息等。
      可以理解为分配给方法的一段内存空间。

     2、方法在计算机中执行的原理：
        方法是存放在方法区内存；
        方法被调用的时候，是进入到栈内存中运行。栈结构只有一个进出口，所有先进后出。

     3、为什么方法要在栈内存中执行：
        a、确保方法中调用其他方法能够回到本方法。例如：main中调用自定义方法结束后回到main
        b、确保方法执行完毕后能够释放栈内存空间。
     */
}
