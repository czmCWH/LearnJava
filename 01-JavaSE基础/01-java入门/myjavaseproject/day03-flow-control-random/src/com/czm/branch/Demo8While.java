package com.czm.branch;

public class Demo8While {
    public static void main(String[] args) {
        /*
         1、do-while 循环，先执行后判断
         do {
            循环体语句；
            又叫迭代语句；
         } while (循环条件);

         do-while 应用场景，比如刷票软件，循环的去购买，第一次购买时不需要判断是否有票直接购买，然后重复进行。
         */
        int i = 0;
        do {
            System.out.println("hello world " + i);
            i++;
        } while (i < 3);

        /*
         三种循环区别总结：
         1、for循环 和 while循环(先判断后执行);do...while(先执行后判断)
         2、for循环和while循环的执行流程是一模一样的，功能上无区别，for能做的while也能做，反之亦然。

         3、使用规范:如果已知循环次数建议使用for循环，如果不清楚要循环多少次建议使用while循环,
         4、其他区别:for循环中，控制循环的变量只在循环中使用。while循环中，控制循环的变量在循环后还可以继续使用。
         */
    }
}
