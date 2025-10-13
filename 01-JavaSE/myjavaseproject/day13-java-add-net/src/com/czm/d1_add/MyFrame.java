package com.czm.d1_add;

import javax.swing.*;
import java.awt.*;

/**
 * 使用 Swing 实现桌面窗口计算器功能
 */
@SuppressWarnings("serial")
public class MyFrame extends JFrame {
    public MyFrame() {
        super("我是一个Java桌面窗口");
        this.setBounds(100, 100, 450, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font ft = new Font("ping fang", Font.PLAIN, 20);

        JTextField tf1 = new JTextField(1);
        tf1.setBounds(0, 50, 100, 30);
        tf1.setFont(ft);
        this.add(tf1);

        JLabel lab1 = new JLabel("+");
        lab1.setBounds(110, 50, 20, 30);
        lab1.setFont(ft);
        this.add(lab1);

        JTextField tf2 = new JTextField(1);
        tf2.setBounds(130, 50, 100, 30);
        tf2.setFont(ft);
        this.add(tf2);

        JLabel lab2 = new JLabel("=");
        lab2.setBounds(240, 50, 20, 30);
        lab2.setFont(ft);
        this.add(lab2);

        JLabel lab3 = new JLabel("?");
        lab3.setBounds(270, 50, 60, 30);
        lab3.setFont(ft);
        this.add(lab3);

        JButton btn = new JButton("计算");
        btn.setFont(ft);
        btn.addActionListener((evt) -> {
            try {
                int n1 = Integer.parseInt(tf1.getText());
                int n2 = Integer.parseInt(tf2.getText());
                lab3.setText(n1 + n2 + "");
            } catch (NumberFormatException e) {
                JDialog dialog = new JDialog(this, true);
                dialog.setTitle("警告");
                int w = 200, h = 80;
                int x = getX() + ((getWidth() - w) >> 1);
                int y = getY() + ((getHeight() - h) >> 1);
                dialog.setBounds(x, y, w, h);

                JLabel lab = new JLabel("请输入正确的整数！");
                lab.setFont(new Font("ping fang", Font.PLAIN, 15));
                dialog.add(lab);

                dialog.setVisible(true);
            }
        });
        this.add(btn);

        this.setVisible(true);
    }
}
