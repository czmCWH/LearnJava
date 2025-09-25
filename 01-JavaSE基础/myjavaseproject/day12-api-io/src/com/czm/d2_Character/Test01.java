package com.czm.d2_Character;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Test01 {
    /*
     1、字符集（Character Set）
      在计算机里面，一个中文汉字是一个字符、一个英文字母是一个字符、一个阿拉伯数字是一个字符、一个标点符号是一个字符...。
      字符集，简称 Charset，由字符组成的集合。

     2、常见的字符集
      ASCII：128个字符（包括了英文字符大小写、阿拉伯数字等）；
      ISO-8859-1：支持欧洲部分语言文件，在有些环境也叫 Latin-1；
      GB2312：支持中文（包括了6763个汉字）；
      BIG5：支持繁体中文（包括了13053个汉字）；
      GBK：是对 GB2312、BIG5 的扩充，包括了 21003 个汉字，支持中日韩；
      GB18030：是对 GBK 的扩充，包括了 27484 个汉字；
      Unicode：⚠️包括了世界上所有的字符；

      ISO-8859-1、GB2312、BIG5、GBK、GB18030、Unicode 中都已经包括了 ASCII 中的所有字符。

     3、字符编码（Character Encoding）
      每个字符集都有对应的字符编码，它决定了每个字符如何转成二进制存储在计算机中。即：如果使用不同的字符集表示 “A” 字符，那么 “A” 字符的二进制编码不一样。

      ASCII：使用单字节编码，编码范围是：0x00 ~ 0x7F (0~127)。如：“A” 字符 ASCII 使用 一个字节表示。
      ISO-8859-1：使用单字节编码，编码范围是：0x00 ~ 0xFF，其中 0x00 ~ 0x7F 与 ASCII 表示一致，而 0x80~0x9F 是控制字符，0xA0~0xFF是文字符号。
      GB2312、BIG5、GBK：采用双字节表示一个汉字。
      GB18030：根据不同的字符类型，采用单字节、双字节、四字节表示一个字符。
      如上字符集都采用一种编码方式。

      Unicode：有很多编码方式，如：Unicode、UTF-8、UTF-16、UTF-32等。⚠️ 最常用的是 UTF-8 编码。
        UTF-8 采用 单字节、双字节、三字节、四字节 表示一个字符。非常灵活！

      总结：同一个字符串采用不同的编码方式，转换成的二进制是不一样的。

     4、乱码
      一般将【字符串】转为【二进制】的过程称为：编码（Encode）；
      一般将【二进制】转为【字符串】的过程称为：解码（Decode）；

      编码、解码时使用的字符编码必须要保持一致，否则会造成乱码。

     5、不同的解码方式，如何正确识别二进制？
      比如 一个字节值在 0～127 之间按照 单个字符解码；

     */
    public static void main(String[] args) throws Exception {

        System.out.println("--- 1、使用不同的编码方式，获取 \"AB你好\" 中的字符的二进制编码：");
        String str = "AB你好";

        byte[] bytes1 = str.getBytes(StandardCharsets.US_ASCII);
        System.out.println("US_ASCII = " + Arrays.toString(bytes1));    // [65, 66, 63, 63]

        byte[] bytes2 = str.getBytes(StandardCharsets.ISO_8859_1);
        System.out.println("ISO_8859_1 = " + Arrays.toString(bytes2));  // [65, 66, 63, 63]

        byte[] bytes3 = str.getBytes("GB2312");
        System.out.println("GB2312 = " + Arrays.toString(bytes3));  //  [65, 66, -60, -29, -70, -61]

        byte[] bytes4 = str.getBytes("BIG5");
        System.out.println("BIG5 = " + Arrays.toString(bytes4));    // [65, 66, -89, 65, -90, 110]

        byte[] bytes5 = str.getBytes("GBK");
        System.out.println("GBK = " + Arrays.toString(bytes5));     //  [65, 66, -60, -29, -70, -61]

        byte[] bytes6 = str.getBytes("GB18030");
        System.out.println("GB18030 = " + Arrays.toString(bytes6));     // [65, 66, -60, -29, -70, -61]

        byte[] bytes7 = str.getBytes(StandardCharsets.UTF_8);
        System.out.println("UTF-8 = " + Arrays.toString(bytes7));   // [65, 66, -28, -67, -96, -27, -91, -67]

        // 结论，只要来自 ASCII 中的字符，任何编码方式都只使用一个字节来表示。

        byte[] bytes8 = str.getBytes(Charset.defaultCharset());
        System.out.printf("JVM 的默认编码 %s = %s", Charset.defaultCharset().name(), Arrays.toString(bytes8));   // [65, 66, -28, -67, -96, -27, -91, -67]

        /*
         如果 String.getBytes 方法没有传参，则使用 JVM 默认字符编码，一般跟随 main 方法所在文件的字符编码。
         可以通过 Charset.defaultCharset() 获取 JVM 的默认字符编码。
         Charset 类的全名是 java.nio.charset.Charset。
         */


        System.out.println("\n--- 2、解码：");
        byte[] bytes = {65, 66, -28, -67, -96, -27, -91, -67};
        System.out.println("--- 使用 UTF-8 解码：" + new String(bytes, StandardCharsets.UTF_8));     // AB你好
        System.out.println("--- 使用 GBK 解码：" + new String(bytes, "GBK"));    // AB浣犲ソ

    }
}
