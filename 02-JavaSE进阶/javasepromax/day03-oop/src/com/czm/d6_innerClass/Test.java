package com.czm.d6_innerClass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test {
    /*
     1、匿名内部类真实场景
        GUI SWING编程:桌面编程。
     2、
     */
    public static void main(String[] args) {
        // 1、创建一个窗口
        JFrame win  = new JFrame("登录一下");

        JPanel panel = new JPanel();
        win.add(panel);

        JButton btn = new JButton("登录");
        // 匿名内部类作为一个对象参数传递给方法使用。至于什么时候用匿名内部类要看实际开发的需要，遇到了才用，没有遇到不会主动用。
        // 还有一个很重要的作用:简化代码(新技术的基础)
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("---点击了登录");
                JOptionPane.showConfirmDialog(win, "没事别点击我！");
            }
        });
//        btn.addActionListener( e -> JOptionPane.showConfirmDialog(win, "没事别点击我！"););
        panel.add(btn);

        // 2、设置大小、
        win.setSize(500, 300);
        win.setLocationRelativeTo(null);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);
    }
}
