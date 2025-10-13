package com.czm.d4_list_x;

public class Test3 {
    /*
     1、实现一个自定义的单向链表
     */
    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("a1");
        list.add("a2");
        list.add("a3");
        list.add("a4");
        list.add("a5");
        list.add("a6");
        list.add("a7");
        list.add("a8");
        list.add("a9");
        list.add("a10");
        list.add("a11");
        System.out.println(list);
    }
}
