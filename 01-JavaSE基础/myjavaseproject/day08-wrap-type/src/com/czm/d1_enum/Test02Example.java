package com.czm.d1_enum;

public class Test02Example {
    /*
     1、枚举的常见应用场景
        用来表示一组信息，然后作为参数进行传输

     2、需求，提供一个方法，可以完成向下取整，向上取证，四舍五入，去掉数部分。

     */
    public static void main(String[] args) {
        System.out.println( handleData(3.15, Constant.HALF_UP));
        System.out.println(handleData(3.15, 2));
        System.out.println(handleData2(3.15, Constant2.HALF_UP));
    }

    // 2、枚举非常适合作为信息标识和分类，很优雅，并且参数值被约束。--- 理想方案
    // 缺点：企业中也经常用常量，因为常量不需要对象化，枚举需要对象化。
    public static double handleData2(double number, Constant2 flag) {
        switch (flag) {
            case DOWN:
                // 向下取整
                number = Math.floor(number);
                break;
            case UP:
                // 向上取整
                number = Math.ceil(number);
                break;
            case HALF_UP:
                // 四舍五入
                number = Math.round(number);
                break;
            case DEL_LEFT:
                // 去掉小数部分
                number = (int)number;
                break;
        }
        return number;
    }


    // 1、使用常量方式作为信息标识和分类，虽然很优雅，但是参数值不被约束。
    public static double handleData(double number, int flag) {
        switch (flag) {
            case Constant.DOWN:
                // 向下取整
                number = Math.floor(number);
                break;
                case Constant.UP:
                    // 向上取整
                    number = Math.ceil(number);
                    break;
            case Constant.HALF_UP:
                // 四舍五入
                number = Math.round(number);
                break;
            case Constant.DEL_LEFT:
                // 去掉小数部分
                number = (int)number;
                break;
            default:
                break;
        }
        return number;
    }
}
