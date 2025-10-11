package com.czm.d11_genericity_interface_x;

// 定义泛型接口
public interface Data<E> {
    public void add(E e);
    public void remove(E e);
    public void update(E e);
    E getById(int id);
}
