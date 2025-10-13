package com.czm.d8_generics.demo;

public class Box<E> {
    private E element;
    public Box(E element) {
        this.element = element;
    }
    public E getElement() {
        return element;
    }
}
