package com.czm.d4_generics.Test01;

/**
 * 自定义泛型类型
 * T，称为 类型参数，或者类型占位符。
 */
public class Student<N, T> {
    private N n;
    private T score;
    public Student(N n, T score) {
        this.score = score;
    }

    public T getScore() {
        return score;
    }

    public void setScore(T score) {
        this.score = score;
    }

    public N getN() {
        return n;
    }
    public void setN(N n) {
        this.n = n;
    }
}
