package com.czm.demo;

public class TestDemo1 {
    /*
     编程思维
        使用所学的lava技术解决问题的思维方式和编写代码实现出来的能力。

     提升编程思维和编程能力的建议
        1、编程思维、编程能力不是一朝一夕形成的，需要大量思考，练习和时间的沉淀。
        2、具体措施:前期，建议先模仿;后期自然就能创新了; 勤于练习代码，勤于思考孰能生巧。
     */

    /*
     1、案例
        根据 机票价格、购买月份、机票类型，计算当前优惠后的价格。

     2、本案例知识点：
        区间匹配用 if；值匹配用 switch。
     */
    public static void main(String[] args) {

        double price = getTicketPrice(1000, 9, "头等舱");
        System.out.println("---当前月份机票优惠价格 = " + price);

    }

    public static double getTicketPrice(double price, int month, String type) {

        if (month > 5 && month < 10) {  // 旅游旺季
            switch (type) {
                case "头等舱":
                    price = price * 0.9;
                    break;
                case "经济舱":
                    price *= 0.85;
                    break;
            }
        } else {    // 淡季
            switch (type) {
                case "头等舱":
                    price *= 0.7;
                    break;
                case "经济舱":
                    price *= 0.6;
                    break;
            }
        }
        return  price;
    }

}
