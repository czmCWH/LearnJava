package com.czm;

/**
  1、Java 的字面量
  字面量是指数据在程序中的书写格式。如：10、1.23、‘我’、"我的"、true、false ...

  2、变量
  变量是用来存储程序需要处理的数据。变量是内存中的一块区域。
  定义格式：
       数据类型 变量名称 = 数据;

  3、变量的特点
   变量里装的数据是可以被替换的。

  4、Java 变量的注意事项 ⚠️
     变量必须先声明才能使用；
     变量只能存储同类型的数据；
     变量从定义开始到最近的 } 的范围内有效；
     同一个范围内变量的名称不能一样；
     任何变量在使用之前都必须要先初始化(赋值)。
        局部变量：即方法内定义的变量，需要开发者手动初始化。可以在定义时可以没有初始值，使用变量时必须有值；
        非布局变量：即实例变量、类变量。编译器会自动给未初始化的变量设置一个初始值。

  5、关键字，也叫保留字 reserved word
   Java语言自己用到的一些词，有些特殊作用的，称之为关键字。例如：boolean 是关键字，true/false 是字面量。
   ⚠️ goto、const 未被 java 使用，但也属于关键字。

 6、标识符
   标识符是指（类、变量、方法...）的名字。标识符的命名规则：
    1、不限长度的 Java 字母、Java 数字序列，但必须以 Java 字母开头（区分大小写）。
    2、不能使用关键字；
    3、不能使用字面量 true、false、null；

   Java 字母是指，作为 Character.isJavaIdentifierStart 方法参数执行返回 true 的字符，如： ASCII 中的 A~Z、a~z、美元符、下划线、中文、韩文、日文等。
   Java 数字是指 ASCII 中的 0~9。
   Java 字母 或者 Java 数字 的判断，作为 Character.isJavaIdentifierPart 方法参数执行返回 true 的字符。

 命名建议：
     变量、方法名 使用小驼峰，如：isShow；
     类名 使用大驼峰，如：MyTools；
     常量 使用大写下划线，如：public static final int MAX_VALUE = Integer.MAX_VALUE;

  7、计算机中表示数据的最小单元是：一个字节
      一个字节(byte，简称B，是使用8个二进制位组成的，即8位)
      字节中的每个二进制位就称为位(bit，简称b)，1B=8b

  8、字符在计算机中是如何存储的呢？
   ASCII编码表:即美国信息交换标准编码，规定了现代英语、数字字符、和其他西欧字符对应的数字编号。
   字符的存储原理，存储的是字符在 ASCII码表中对应的数字编号的二进制。
   图片是无数个像素点组成的，每个像素点的数据用 0~255*255*255 表示其颜色
   声音存储的声波对应的二进制值。

  9、十进制转二进制的算法
    a、十进制和二进制转换
        十进制转二进制的快速方法是：除二取余法，从下往上拼接。
        二进制转十进制，依次按个十百千万乘以2的n-1次方，即：8421编码。
    b、八进制
    为了便于观察和表示二进制，推出了八进制和十六进制。
        把二进制每3位隔开表示，就是八进制，如：000～111，值的范围为：0～7
            如：97：二进制表示：01100001，转8进制：01，100，001 = 141 就是8进制的值
        把二进制每4位隔开，就是16进制的表示方法，如：0000～111，值的范围为：0～15（依次用0～9，A，B，C，D，E，F）
            如：97：二进制表示：01100001，转16进制：0110，0001 = 61 就是16进制的值
        Java程序中支持书写二进制、八进制、十六进制的数据，分别需要以0B或者0b、0、0X或者0x开头。

  10、计算机数据单位
    计算机表示数据的最小组成单元是:字节，1B=8b
    在B的基础上，计算机发展出了KB、MB、GB、TB、这些数据单位
       1B = 8b
       1KB = 1024B  即 2^10
       1MB = 1024KB
       1GB = 1024MB
       1TB = 1024GB
 */
public class VariableDemo {
    public static void main(String[] args) {

        // ⚠️⚠️⚠️ java 基本数据类型：4大类，总共8种。

        // ---------- 1、整型
        // byte 字节整型
        byte b = 127;
        System.out.println(b);

        // short 短整型
        short s = 127;
        System.out.println(s);

        // int 整型（默认类型，随便写的整数字面量默认是 int 类型）
        int age = 10;
        System.out.println(age);

        // java中书写二进制、八进制、十六进制，表示数字250
        int a1 = 0B11111010;    // 二进制：0B或者0b开头
        System.out.println("二进制 a1 = " + a1);

        int a2 = 0372;  // 八进制：0开头  8^2 * 3 + 8^1 * 7 + 8^0*2 = 250
        System.out.println("八进制 a2 = " + a2);

        int a3 = 0XFA;  // 十六进制：0X或者0x开头    // 16*15 + 10 = 250
        System.out.println("十六进制 a3 = " + a3);

        // long 长整型，以 L 或 l 结尾表示。
        long a4 = 100L;
        System.out.println("long 类型 =" + a4);

        // ---------- 2、浮点型
        // float 单精度浮点型，以 F 或 f 结尾表示，基本淘汰，因为其表示的数据精度不准。
        float v1 = 0.1F;
        //double 双精度浮点型，以 D 或 d 结尾来表示。随便写的小数字面量默认是 double 类型。
        double v2 = 0.1D;
        // 默认浮点型就是 double 类型
        double v3 = 123.4;

        // 科学技术法用 E 或 e 表示
        float v4 = 123.4E2F;
        double v5 = 123.4e2;
        System.out.println("v4 = " + v4);
        System.out.println("v5 = " + v5);

        // ---------- 3、字符和字符串
        // 用单引号表示字符，且只有一个字符
        char c1 = 'A';
        System.out.println("c1 = " + c1);
        // char 内存占用2个字节，emoji字符占用3个字节，所以不能用char表示。
//        System.out.println('🥰');
        System.out.println("🥰");

        // 用双引号表示字符串。⚠️，Java 中 String 是引用类型。
        String s1 = "hello";
        System.out.println("s1 = " + s1);

        // 转义字符，如：\t 空格，(\n 、\r)换行
        System.out.println("你好" + '\t' + '\"' + '帅' + '\'' );

        // ---------- 4、布尔
        boolean bool1 = true;
        boolean bool2 = false;
        System.out.println("bool1 = " + bool1);

        // ---------- 5、空值
        String str = null;
        System.out.println("str = " + str);


        // ---------- 6、在数字中使用下划线
        int n1 = 1_0000_0000;
        int n2 = 0xFF_EC_DE_5E;
        double n3 = 1.23_45_67;
        long n4 = 1___0000_0000L;
        System.out.println("n1 = " + n1);
        System.out.println("n2 = " + n2);
        System.out.println("n3 = " + n3);
        System.out.println("n4 = " + n4);
        // 注意：不能在浮点数的小数点前后使用下划线；不能在数字的前后使用下划线；不能在X、B、F、D、L、E等特殊字母的前后使用下划线；

        identifierExample();
    }



     static void identifierExample() {

        System.out.println("---- Java 中标识符");
        System.out.println(Character.isJavaIdentifierStart('哈'));   // true
        System.out.println(Character.isJavaIdentifierStart('$'));   // true

        System.out.println(Character.isJavaIdentifierPart('1'));    // true




    }
}
