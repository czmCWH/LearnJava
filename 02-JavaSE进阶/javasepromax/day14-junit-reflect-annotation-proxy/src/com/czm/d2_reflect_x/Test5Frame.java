package com.czm.d2_reflect_x;

public class Test5Frame {

    /*
     1、反射的作用
        * 基本作用：可以得到一个类的全部成分然后操作。
        * 可以破坏封装性。
        * 最重要的用途是：适合做java的框架，基本上，主流的框架都会基于反射设计出一些通用的功能。

     2、!!! 案例：对于任意一个对象，该框架都可以把对象的字段名和对应的值，保存到文件中去

     */

    public static void main(String[] args) throws Exception {
        Student stu = new Student("张帅", 19, '男',175, "打篮球");
        Teacher tch = new Teacher("牛老师", 28);

        ObjectFrame.saveObject(stu);
        ObjectFrame.saveObject(tch);
    }

    // Junit 单元测试，对相对路径支持不好，会导致报错！！！！！！！
//    @Test
//    public void testSave() throws Exception {
//        Student stu = new Student("张帅", 19, '男',175, "打篮球");
//        Teacher tch = new Teacher("牛老师", 28);
//
//        ObjectFrame.saveObject(stu);
//        ObjectFrame.saveObject(tch);
//    }

}
