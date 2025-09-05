package com.czm.arraylist;

import java.util.ArrayList;
import java.util.Scanner;

public class FoodOperator {
    // 1、准备一个存储菜品对象的容器
    private ArrayList<Food> allFoods = new ArrayList<>();
    // 1、创建扫描器，接收用户输入数据
    private Scanner sc = new Scanner(System.in);

    // 2、开始向容器中添加菜品
    public void start() {
        while (true) {
            System.out.println("=====商家管理系统=====");
            System.out.println("输入1，上架菜品");
            System.out.println("输入2，删除菜品");
            System.out.println("输入3，展示所有菜品");
            // 等待用户输入操作
            int command = sc.nextInt();
            switch (command) {
                case 1:
                    addFood();
                    break;
                case 2:
                    break;
                case 3:
                    showAllFoods();
                    break;
                default:
                    System.out.println("输入操作类型错误！");
            }
        }
    }

    public void addFood() {
        System.out.println("=========开始上架菜品=========");
        Food fd = new Food();
        System.out.println("请输入菜品名称：");
        String name = sc.next();
        fd.setName(name);
        System.out.println("请输入菜品价格：");
        double price = sc.nextDouble();
        fd.setPrice(price);
        System.out.println("请输入菜品描述：");
        String desc = sc.next();
        fd.setDesc(desc);

        allFoods.add(fd);
        System.out.println("===菜品上架成功！");
    }

    public void showAllFoods() {
        System.out.println("===============全部菜品================");
        for (int i = 0; i < allFoods.size(); i++) {
            Food fd = allFoods.get(i);
            System.out.println(fd.getName() + "\t" + fd.getPrice() + "\t" + fd.getDesc());
        }
    }
}
