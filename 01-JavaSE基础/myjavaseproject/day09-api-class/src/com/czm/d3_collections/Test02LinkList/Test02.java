package com.czm.d3_collections.Test02LinkList;

public class Test02 {

    /*
     1、链表
      链表中的节点是独立的对象，在内存中是不连续的，每个节点包含数据值和下一个节点的地址。
      链表的特点：
         a、查询慢，无论查询哪个数据都要从头开始找；
         b、链表增删相对快；

     2、双向链表
       特点：查询慢，增删相对较快，但对首尾元素进行增删改查的速度是极快的，
            因为它包含头尾节点指针，寻址快。
       因此，LinkedList新增了很多首尾操作的特有方法。

     3、

     */

    public static void main(String[] args) {
        // 实现一个自定义的单向链表
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
