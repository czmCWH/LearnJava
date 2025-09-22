package com.czm.d4_generics.Test02;

import java.util.List;

public interface MyList<E, T> extends List<E> {
    void setNo(T no);
}
