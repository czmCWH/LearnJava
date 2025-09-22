package com.czm.d4_generics;

import com.czm.d4_generics.demo.Box;

import java.util.*;

public class Test02Inherit {

    /*
      1、泛型类型的继承
        存在继承关系的类型，它们的泛型类型必须一样。

        public interface Iterable<T>
        public interface Collection<E> extends Iterable<E>
        public interface List<E> extends Collection<E>
        public class ArrayList<E> implements List<E>

        如下存在继承关系：
        Iterable<String>
            ⬆️
        Collection<String>
            ⬆️
        List<String>
            ⬆️
        MyList<String, Integer>、MyList<String, String>、MyList<String, Double>



     */

    public static void main(String[] args) {
        // 1、如下代码所示，虽然 Integer 继承自 Number，但是 b1 与 b2 的类型都是 Box 不存在继承关系，所以 b2 = b1 会报错。
        Box<Integer> b1 = new Box<>(12);
//        Box<Number> b2 = b1;  // error，报错

        // 2、如下代码继承正确
        Iterable<String> it = null;
        Collection<String> col = null;
        List<String> li = null;
        ArrayList<String> al = null;

        it = col;
        col = li;
        li = al;

        // 3、如下主类型存在继承，但是它们泛型类型不一样，所以不存在继承关系：
        List<Object> list = null;
        ArrayList<String> al2 = null;
//        list = al2  // error，报错

    }
}
