package com.czm.d8_generics.demo;

/**
 * 实现泛型类型，此类型可以进行比较，并且是通过其 类型参数 实现的

 * <T extends Number & Comparable<T>>，表示 类型参数 T 具备可比较性；
 * Comparable<Sport<T>>，表示泛型类型 Sport 具备可比较性；
 *
 */
public class Sport<T extends Number & Comparable<T>> implements Comparable<Sport<T>> {
    private T score;

    public Sport(T score) {
        this.score = score;
    }

    public T getScore() {
        return score;
    }

    public void setScore(T score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Sport{" +
                "score=" + score +
                '}';
    }

    @Override
    public int compareTo(Sport<T> o) {
        if (o == null) return 1;
        if (score != null) return score.compareTo(o.score);
        return o.score == null ? 0 : -1;  // 此时 this.score 为 null, 如果 o.score == null，则一样大；否则 o 大。
    }
}
