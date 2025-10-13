package com.czm.d1_test;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class UserService {
    /**
     * 给定一个身份证号，计算出用户的年龄
     */
    public Integer getAge(String idcard) {
        if (idcard == null || idcard.length() != 18) {
            throw new IllegalArgumentException("无效的身份证号码");
        }
        String birthday = idcard.substring(6, 14);
        LocalDate parse = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyyMMdd"));
        return Period.between(parse, LocalDate.now()).getYears();
    }

    /**
     * 给定一个身份证号，计算该用户的性别
     */
    public String getGender(String idcard) {
        if (idcard == null || idcard.length() != 18) {
            throw new IllegalArgumentException("无效的身份证号码");
        }
        return Integer.parseInt(idcard.substring(16, 17)) % 2 == 1 ? "男" : "女";
    }
}
