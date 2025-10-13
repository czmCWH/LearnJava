package com.czm.d3_collections.Test03StackQueue;

import java.util.LinkedList;
import java.util.Queue;

public class Test02Queue {
    /*
      1、Queue
       Queue，队列，只能在头尾两端进行操作。
       队尾（rear），只能从 队尾添加 元素，一般叫做 入队。
       队头（front），只能从 队头移除 元素，一般叫做 出队。
       Queue 使用 先进先出的原则，First In First Out，FIFO。

       ⚠️：Queue 是一个 interface，它的实现类是 LinkedList。因为 LinkedList 进行 头/尾 处理元素效率高。

      2、Queue 常用方法
        boolean add(E e);   // 入队
        boolean offer(E e); // 入队
        E remove(); // 出队
        E poll();   // 出队
        E element();    // 返回队头
        E peek();   // 返回队尾
        int size();
        boolean isEmpty();
     */
    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(11);
        queue.offer(22);
        queue.add(33);
        queue.offer(44);
        System.out.println("--- 队头: " + queue.element());   // 11
        System.out.println("--- 队头: " + queue.peek());  // 11

        while (!queue.isEmpty()) {
            System.out.println("移除元素：" + queue.remove());
        }
        System.out.println("--- queue = " + queue);     // queue = []

    }
}
