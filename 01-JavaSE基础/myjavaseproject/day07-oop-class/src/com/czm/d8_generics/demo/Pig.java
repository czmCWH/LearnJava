package com.czm.d8_generics.demo;

public class Pig implements Comparable<Pig> {
    private Double weight;
    public Pig(Double weight) {
        this.weight = weight;
    }

    public Double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Pig{" +
                "weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Pig o) {
        if (o == null) return 1;
        return (int) (weight - o.weight);
    }
}
