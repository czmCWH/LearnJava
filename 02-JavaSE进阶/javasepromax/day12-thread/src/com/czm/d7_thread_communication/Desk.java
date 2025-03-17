package com.czm.d7_thread_communication;

// 桌子，表示共享资源对象
public class Desk {
    private String data;

    public Desk() {
    }

    public Desk(String data) {
        this.data = data;
    }

    // synchronized 同步方法，保证线程安全
    public synchronized void getFood() throws Exception {
        String name = Thread.currentThread().getName();
        if (data == null) {
            this.notifyAll();  //  唤醒别人
            this.wait();    // 自己等待
        } else {
            // 有包子，开始消费！
            System.out.println(name + " 拿到了包子！");
            data = null;
            this.notifyAll();
            this.wait();
        }
    }

    public synchronized void putFood() throws Exception {
        String name = Thread.currentThread().getName();
        if (data == null) {     // 没有包子，开始做包子
            data = name + "做完了包子！";
            System.out.println(name + " 生产了包子！");
            this.notifyAll();
            this.wait();
        } else {
            this.notifyAll();
            this.wait();
        }
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
