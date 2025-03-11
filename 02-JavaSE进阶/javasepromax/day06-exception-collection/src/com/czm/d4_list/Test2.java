package com.czm.d4_list;

import java.util.LinkedList;

public class Test2 {
    /*
     1、LinkedList 集合底层的原理
        LinkedList 集合是基于双(向)链表实现的。

     2、什么是 链表，（面试！！！）
        链表中的节点是独立的对象，在内存中是不连续的，每个节点包含数据值和下一个节点的地址。
        链表的特点：
            a、查询慢，无论查询哪个数据都要从头开始找；
            b、链表增删相对快；

     3、双向链表
        特点：查询慢，增删相对较快，但对首尾元素进行增删改查的速度是极快的，
             因为它包含头尾节点指针，寻址快。
        因此，LinkedList新增了很多首尾操作的特有方法。

     4、LinkedList 的应用场景
        a、用于设计队列。队列：先进先出、后进后出。只是在首尾操作元素，因此用 LinkedList 实现非常好！
        b、用于设计栈。栈：后进先出，先进后出。进栈/出栈，用 LinkedList 设计很合适！

     */
    public static void main(String[] args) {

        LinkedList<String> queue = new LinkedList<>();
        // 1、入队列
        queue.addLast("第1个元素");
        queue.addLast("第2个元素");
        queue.addLast("第3个元素");
        queue.addLast("第4个元素");
        System.out.println("---- 入队列 = " + queue);

        // 2、出队列
        queue.removeFirst();
        queue.removeFirst();
        queue.removeFirst();
        System.out.println("---- 出队列 = " + queue);

        System.out.println("--------------------");
        LinkedList<String> stack = new LinkedList<>();

        // 1、入栈、压栈 push
        stack.push("第1颗子弹");
//        stack.addFirst("第1颗子弹");
        stack.addFirst("第2颗子弹");
        stack.addFirst("第3颗子弹");
        stack.addFirst("第4颗子弹");
        System.out.println("----入栈 = " + stack);

        // 2、出栈、弹栈 pop
        stack.pop();
//        stack.removeFirst();
        stack.removeFirst();
        stack.removeFirst();
        System.out.println("----出栈 = " + stack);

    }
}
