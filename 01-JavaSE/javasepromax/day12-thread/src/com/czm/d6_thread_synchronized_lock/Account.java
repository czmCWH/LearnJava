package com.czm.d6_thread_synchronized_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private String cardId;
    private double money;

    private final Lock lk = new ReentrantLock();

    public Account() {
    }

    public Account(String cardId, double money) {
        this.cardId = cardId;
        this.money = money;
    }

    // 开始取钱
    public void drawMoney(double amount) {
        String name = Thread.currentThread().getName();
        try {
            lk.lock();  // 获得锁
            if (money >= amount) {
                System.out.println(name + " 取钱成功！取出 = " + amount);
                // 更新余额
                money -= amount;
                System.out.println(name + " 取完钱后，剩余 = " + this.money);
            } else {
                System.out.println(name + " 来取钱，余额不足！");
            }
        } finally {
            // 放在 finally 里释放锁，避免出现异常而无法释放锁
            lk.unlock();    // 释放锁
        }

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
