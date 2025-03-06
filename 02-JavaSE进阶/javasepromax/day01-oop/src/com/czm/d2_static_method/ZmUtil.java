package com.czm.d2_static_method;

import java.util.Random;

// 工具类，里面都是静态方法
public class ZmUtil {

    // ⚠️⚠️⚠️ 工具类没有创建对象的需求，建议将工具类的构造器私有；
    private ZmUtil() {

    }

    // 生成登录验证码
    public static String createCode(int n) {
        String code = "";
        String data = "abCdFGHIJKLMNOPQRSTUVWXYZ01234evwxyzABcDfghijklmnopgrstuE56789";
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            int index = r.nextInt(data.length());
            char c = data.charAt(index);
            code += c;
        }
        return code;
    }
}
