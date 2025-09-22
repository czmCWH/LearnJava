package com.czm.d4_generics.Test02;

public class Box<E> {
    private E element;
    public Box(E element) {
        this.element = element;
    }
    public E getElement() {
        return element;
    }
}
