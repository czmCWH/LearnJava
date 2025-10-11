package com.czm.d10_genericity_class_x;
// 定义一个泛型类
public class MyArrayList<E> {
    public Boolean add(E e) {
        System.out.println("--- 添加元素" + e);
        return true;
    }
    public Boolean remove(E e) {
        System.out.println("--- 删除元素" + e);
        return true;
    }

}
