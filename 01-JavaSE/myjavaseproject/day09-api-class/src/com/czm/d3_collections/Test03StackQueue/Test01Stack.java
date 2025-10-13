package com.czm.d3_collections.Test03StackQueue;

import java.util.Stack;

public class Test01Stack {

    /*
      1、Stack
        Stack 栈，⚠️ 只能在一端进行操作。
        往栈中添加元素的操作，一般叫做 push，入栈。
        从栈中移除元素的操作，一般叫做 pop，出栈（只能移除栈顶元素，也叫做：弹出栈顶元素）。
        后进先出的原则，Last In First Out，LIFO。

        注意：此处说的 栈，是指数据结构。而函数的 “栈空间” 是指内存空间。

      2、Stack 常用 API
            public E push(E item)
            public synchronized E pop()
            public synchronized E peek()， 返回栈顶元素
            public synchronized int size()
            public boolean empty()， 查看栈是否为空
            public synchronized int search(Object o)，返回查找元素的索引（从1开始递增，栈顶为1）--- 很少用
     */

    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.peek());
        System.out.println("--- search = " + stack.search(2));  // search = 2
        // 循环 pop 栈元素，直到栈为空
        while(!stack.empty()) {
            System.out.print(stack.pop() + "、");
        }
        System.out.println("\n--- stack = " + stack);   // stack = []

    }
}
