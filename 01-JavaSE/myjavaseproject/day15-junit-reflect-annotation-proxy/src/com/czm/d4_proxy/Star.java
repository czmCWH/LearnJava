package com.czm.d4_proxy;

public class Star implements StarService {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Star() {}
    public Star(String name) {
        this.name = name;
    }

    @Override
    public void sing(String name) {
        System.out.println(this.name + "唱歌：" + name);
    }

    @Override
    public String dance() {
        System.out.println(this.name + "---表演跳舞！");
        return "表演结束，谢谢！";
    }
}
