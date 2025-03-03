package com.czm.branch;

public class WhileDemo7 {
    public static void main(String[] args) {
        /*
         1、while 循环，先判断后执行
         while (循环条件) {
            循环体语句(被重复执行的代码)
         }
         */
        var i = 0;
        while (i < 3) {
            System.out.println("hello world!" + i);
            i++;
        }

        /*
         while 和 for 功能上完全一样，for能解决的while一样能解决，反之亦然。
         使用规范：知道循环几次，使用for；不知道几次的使用 while。
         例如：
         使用 while 案例：世界最高山峰珠穆朗玛峰高度是:8848.86米=8848860毫米，假如我有一张足够大的纸，它的厚度是0.1毫米。请问:该纸张折叠多少次，可以折成珠穆朗玛峰的高度?
         */
        double peakHeight = 8848860;
        double paper = 0.1;
        int count = 0;
        while (paper < peakHeight) {
            paper *= 2;
            count++;
        }
        System.out.println("一共要折叠:" + count);
        System.out.println("纸张最后高度：" + paper);

    }
}
