package com.czm.d2_xml;

// 定义 Contact 类存储 XML 文件中解析的数据
public class Contact {
    private int id;
    private String name;
    private char gender;
    private String email;
    private boolean vip;

    public Contact() {
    }

    public Contact(int id, String name, char gender, String email, boolean vip) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.vip = vip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", vip=" + vip +
                '}' + '\n';
    }
}
