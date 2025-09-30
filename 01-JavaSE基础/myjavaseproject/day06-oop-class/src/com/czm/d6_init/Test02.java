package com.czm.d6_init;

public class Test02 {
    public static void main(String[] args) {

        Student student = new Student();

        /*
         初始化块的执行顺序：
            --- 1 - Person - static block
            --- 2 - Student - static block
            --- 1 - Person - block
            --- Person constructor
            --- 2 - Student - block
            --- Student constructor

         */
    }
}
