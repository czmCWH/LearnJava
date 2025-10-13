package com.czm.d3_collections.Test01ArrayList;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 实现 Iterable 接口用于 for-each 遍历
 */
public class Classroom implements Iterable<String> {
    private String[] students ;
    public Classroom(String... students) {
        this.students = students;
    }

    public String[] getStudents() {
        return students;
    }

    @Override
    public Iterator<String> iterator() {
        return new ClassroomIterator();
    }

    private class ClassroomIterator implements Iterator<String> {
        // 定义迭代器的游标
        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor < students.length;
        }

        @Override
        public String next() {
            return students[cursor++];
        }
    }
}
