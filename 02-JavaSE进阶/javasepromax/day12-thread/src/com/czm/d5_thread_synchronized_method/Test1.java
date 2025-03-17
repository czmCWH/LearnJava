package com.czm.d5_thread_synchronized_method;

public class Test1 {
    /*
     1、同步方法
        作用：把访问共享资源的 核心方法 给上锁，以此保证线程安全。
        语法：
            修饰符 synchronized 返回值类型 方法名称(形式参列表) {
                操作共享资源的代码
            }

        原理：每次只能一个线程进入，执行完毕以后自动解锁，其他线程才可以进来执行。

     2、同步方法底层原理
        同步方法其实底层也是有隐式锁对象的，只是锁的范围是整个方法代码；
        如果方法是实例方法：同步方法默认用 this 作为的锁对象；
        如果方法是静态方法：同步方法默认用 类名.class 作为的锁对象；

     3、同步代码 和 同步方法 的异同
        范围上：同步代码块锁的范围更小，同步方法锁的范围更大
        可读性：同步方法更好。
        实际开发中这2种随便！
     */
    public static void main(String[] args) {
        // 1、创建一个账号对象
        Account abc = new Account("abc", 1000);

        // 2、创建2个线程代表2个人取钱
        new DrawThread("小明", abc).start();
        new DrawThread("小红", abc).start();

    }
}
