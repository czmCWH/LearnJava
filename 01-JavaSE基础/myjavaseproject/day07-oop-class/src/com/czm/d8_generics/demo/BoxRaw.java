package com.czm.d8_generics.demo;

public class BoxRaw<E> {
    private E element;

    public E getElement() {
        return element;
    }
    public void setElement(E element) {
        this.element = element;
    }
}
