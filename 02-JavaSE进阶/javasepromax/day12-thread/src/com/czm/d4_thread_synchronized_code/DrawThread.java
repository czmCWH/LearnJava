package com.czm.d4_thread_synchronized_code;

public class DrawThread extends Thread {
    private Account abc;
    public DrawThread(String name, Account account) {
        super(name);
        this.abc = account;
    }
    public void run() {
        abc.drawMoney(1000);
    }
}
