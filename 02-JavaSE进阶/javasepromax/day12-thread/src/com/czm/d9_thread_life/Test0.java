package com.czm.d9_thread_life;

public class Test0 {

    /*
     1、进程
        a、正在运行的程序(软件)就是一个独立的进程。
        b、线程是属于进程的，一个进程中可以同时运行很多个线程，
        c、进程中的多个线程其实是并发和并行执行的。

     2、并发：CPU分时轮询的执行线程。
        进程中的线程是由CPU（单核单线程）负责调度执行的，但CPU能同时处理线程的数量有限，为了保证全部线程都能往前执行，
        CPU会轮询 为系统的每个线程服务，由于CPU切换的速度很快，给我们的感觉这些线程在同时执行，这就是并发

     3、并行：同一个时刻同时在执行。
        在同一个时刻上，同时有多个线程在被CPU（多核多线程）调度执行。

     4、多线程到底是怎么在执行的?
        并发和并行同时进行的!
    */
}
