package com.czm.d8_generics.demo;

import java.util.List;

public interface MyList<E, T> extends List<E> {
    void setNo(T no);
}
