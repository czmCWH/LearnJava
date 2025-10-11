package com.czm.d3_annotation;

@MyUseAnnotation(value = "编程语言", bbb = {"java", "vue", "react"})
public class MyUseDemo {

    @MyUseAnnotation(value = "新能源汽车", aaa = 99, bbb = {"比亚迪", "小米", "奇瑞"})
    public void test() {

    }
}
