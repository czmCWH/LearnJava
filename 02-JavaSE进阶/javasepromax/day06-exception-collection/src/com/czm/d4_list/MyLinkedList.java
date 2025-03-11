package com.czm.d4_list;

import java.util.StringJoiner;

// 自定义一个单向链表
public class MyLinkedList<E> {
    // 节点的个数
    private int size = 0;

    MyLinkedList.Node<E> first;     // 头指针

    // 定义一个节点类，用于创建节点对象，封装了节点数据，以及下个节点对象的地址值。
    public static class Node<E> {
        E item;     // 节点的数据
        MyLinkedList.Node<E> next;   // 下个节点地址

        public Node(E item, MyLinkedList.Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    public int size() {
        return size;
    }

    // 添加节点数据
    public boolean add(E e) {
        //1、创建节点对象，封装数据
        MyLinkedList.Node<E> newNode = new MyLinkedList.Node<>(e, null);
        // 判断这个节点是否是第一个节点
        if (first == null) {
            first = newNode;
        } else {
            // 把这个节点加入到当前最后一个节点下一个位置。
            // 如何找到最后一个节点对象？通过头节点依次往后找，直到找到的是最后一个节点
            MyLinkedList.Node<E> temp = first;
            while (temp.next != null) {
                temp = temp.next;
            }
            // 拿到最后一个节点 temp，设置最后一个节点的 next 地址为新添加的节点
            temp.next = newNode;
        }
        size++;
        return true;
    }

    @Override
    public String toString() {
        // 遍历节点，看单向链表是否成功
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        MyLinkedList.Node<E> temp = first;
        while (temp != null) {
            sj.add(temp.item + "");
            temp = temp.next;
        }
        return sj.toString();
    }
}
