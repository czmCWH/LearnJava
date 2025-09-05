package com.czm.d3_operator;

public class Demo1 {
    public static void main(String[] args) {
        /*
        1、基本运算符
        注意：
            ⚠️：两个整数做除法，其结果一定是整数，因为最高类型是整数。
            与字符串做 +运算 时会被当成连接符，其结果还是字符串。

        'a' 的 Unicode 值是 97（ASCII 码兼容）。
        'A' 对应 65，'0' 对应 48

        */
        int i = 5;
        int j = 2;
        System.out.println(i + j);
        System.out.println(i - j);
        System.out.println(i / j);  // 打印：2，因为最高类型是 int，所以结果是 2
        System.out.println(i * j);
        System.out.println(i / j * 1.0);    // 打印：2.0
        System.out.println(1.0 * i / j);    // 打印：2.5
        System.out.println(i % j);  // 求余数，打印：1

        /*
         2、+ 运算符可以做连接符
         + 符号与【字符串】运算的时候是用作连接符的，其结果依然是个 字符串。
         */
        int a = 20;
        System.out.println("你的年纪是：" + a);
        System.out.println("你的年纪是：" + a + '岁'); // 打印：你的年纪是：20岁
        System.out.println(a + 'a' + "大小");     // 打印：117大小，因为字符a的编号是97，
        /*
           当 char 参与算术运算时，如下会触发 自动类型提升：
            char ➔ int（因为 char 是 16 位，int 是 32 位）。
            byte/short 也会提升为 int。
         */
        System.out.println('a' + 1);    // 打印：98，因为 char与int进行运算时，会触发自动类型提升，
        System.out.println("a" + 1);    // 打印：a1
        // 若希望输出字符 'b'，需显式将结果强制转换回 char：
        System.out.println((char)('a' + 1));  // 输出 b

        /*
         3、自增、自减运算符
         自增 ++，放在某个变量前面或者后面，对变量自身的值加1
         自减 --，放在某个变量前面或者后面，对变量自身的值减1

         ++、-- 如果不是单独使用(如在表达式中、或者同时有其它操作)，放在变量前后会存在明显区别
         放在变量的前面，先对变量进行+1、-1，再拿变量的值进行运算。
         放在变量的后面，先拿变量的值进行运算，再拿变量的值进行+1、-1。
         */
        System.out.println("-------------------");
        int num = 10;
        num++;
        System.out.println(num);
        ++num;
        System.out.println(num);

        int count = 10;
        int rs1 = ++count;
        System.out.println(count);  // 打印：11
        System.out.println(rs1);    // 打印：11，先加后用
        int total = 10;
        int rs2 = total++;
        System.out.println(total);  // 打印：11
        System.out.println(rs2);    // 打印：10，先用后加，即先把 total 赋值给 rs2
    }
}
