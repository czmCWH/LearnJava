package com.czm.d5_thread_synchronized_method;

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
    // synchronized 同步方法
    public synchronized void drawMoney(double amount) {
        String name = Thread.currentThread().getName();
        if (money >= amount) {
            System.out.println(name + " 取钱成功！取出 = " + amount);
            // 更新余额
            money -= amount;
            System.out.println(name + " 取完钱后，剩余 = " + this.money);
        } else {
            System.out.println(name + " 来取钱，余额不足！");
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
