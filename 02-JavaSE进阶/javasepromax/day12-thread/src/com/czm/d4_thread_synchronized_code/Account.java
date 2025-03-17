package com.czm.d4_thread_synchronized_code;

public class Account {
    private String cardId;
    private double money;

    public Account() {
    }

    public Account(String cardId, double money) {
        this.cardId = cardId;
        this.money = money;
    }

    // 开始取钱
    public void drawMoney(double amount) {
        String name = Thread.currentThread().getName();
        // ⚠️ 双引号的字符串对象，在内存只有一个！！！
        // 这会存在一个问题，不同账户的人会被一起等待锁住！
//        synchronized ("czm") {
        // 用 this 来表示唯一的锁，即账户相同的被锁住！
        synchronized (this) {   // 获取锁
            if (money >= amount) {
                System.out.println(name + " 取钱成功！取出 = " + amount);
                // 更新余额
                money -= amount;
                System.out.println(name + " 取完钱后，剩余 = " + this.money);
            } else {
                System.out.println(name + " 来取钱，余额不足！");
            }
        }   // 释放锁
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
