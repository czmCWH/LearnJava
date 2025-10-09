package com.czm.d5_AnonymousClass.util;

public class Networks {
    public interface Block {
        void success(Object response);
        void failure(String msg);
    }

    public static void get(String url, Block block) {
        // 1、根据 url 发送一个异步请求（开启一条子线程）

        // 2、请求完毕后
        boolean result = false;
        if (result) {
            Object res = null;
            block.success(res);
        } else {
            block.failure("fail");
        }
    }
}
