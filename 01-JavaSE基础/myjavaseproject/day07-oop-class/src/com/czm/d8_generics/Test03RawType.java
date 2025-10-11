package com.czm.d8_generics;

import com.czm.d8_generics.demo.BoxRaw;

public class Test03RawType {

    /*
      1、原始类型（Raw Type）
      原始类型是指 没有传递具体的类型给泛型的类型参数。
      当使用了原始类型时，编译器会给出 rawTypes 警告，可以使用 @SuppressWarnings 消除。
      将 非原始类型 赋值给 原始类型 时，编译器没有任何警告和错误。
      将 原始类型 赋值给 非原始类型 时，编译器会给出 unchecked 警告，可以使用 @SuppressWarnings 消除

     */

    @SuppressWarnings("Unchecked")
    public static void main(String[] args) {
        // 如下代码所示，Box 称为是 Box<E> 的原始类型（Raw Type）
        @SuppressWarnings("rawtypes")
        BoxRaw rawBox = new BoxRaw();
        // Box<String>、Box<Object> 是非原始类型
        BoxRaw<String> strBox = new BoxRaw<>();
        BoxRaw<Object> objBox = new BoxRaw<>();

        rawBox = strBox;    // ok
        rawBox = objBox;    // ok
        strBox = rawBox;    // 警告：Unchecked assignment:     消除 Unchecked 警告：@SuppressWarnings("Unchecked")
        objBox = rawBox;    // 警告：Unchecked assignment:

    }
}
