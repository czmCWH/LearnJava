package com.czm.d3_wrap_api;

import java.util.UUID;

public class Test03UUID {
    public static void main(String[] args) {
        /*
         1、UUID
         UUlD(Universally Unique Identifier)，通用唯一标识符。
         UUID 的目的是让分布式系统中的所有元素都能有唯一的标识符，而不需要通过中央控制端来做标识符的指定。

         可以利用 java.util.UUID 类的 randomUUID 方法生成一个 128 bit (32 位16 进制数)的随机 UUID。

         */

        System.out.println(UUID.randomUUID());

    }
}
