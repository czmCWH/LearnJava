package com.czm.d3_collections.Test01ArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test05Api {
    /*
      👉 3、ArrayList 的基本使用
        public Object[] toArray()，把当前 ArrayList集合类型 转换成 Object[] 类型的数组。便于传递参数
        public <T> T[] toArray(T[] a)，接收一个数组对象参数，把当前集合类型转换为 与参数类型的数组。
        public void trimToSize()，缩小数组的容量为当前元素个数大小，性能优化时常用。
        public void ensureCapacity(int minCapacity)，提前扩容数组元素个数，性能优化时常用。
     */

    public static void main(String[] args) {
        Object obj1 = 11;   // 父类类型指向子类对象
        // a、为什么可以把 obj1 赋值给 obj2？
        // 因为 obj1 虽然是 Object 类型，但是它指向是一个 Integer 类型，所以可以赋值给 Integer 类型的 obj2。
        Integer obj2 = (Integer) obj1;
        System.out.println("obj2 = " + obj2);   // obj2 = 11

        Object[] array1 = {11, 22, 33};
        // b、为什么会报错？
        // 因为 array1 等价于 Object[] array1 = new Object[] {11, 22, 33}; 本质创建了一个 Object 类型的数组，数组内的元素是 Integer 类型。
        // 而 array2 是 Integer 类型的数组。二者类型不一致，所以类型转换失败。
//        Integer[] array2 = (Integer[]) array1;  // 报错：Ljava.lang.Object; cannot be cast to [Ljava.lang.Integer
//        System.out.println("array2 = " + array2);

        List<Integer> list3 = new ArrayList<>();
        list3.add(1);
        list3.add(2);
        list3.add(3);
        System.out.println("list3 = " + list3);     // list3 = [1, 2, 3]，Collections 类型默认实现了 toString 方法，所以可以直接打印数组元素。
        Object[] arrray = list3.toArray();  // 默认返回 Object[] 类型的数组
        Integer[] arrray2 = list3.toArray(new Integer[0]);  // 此处 new Integer[0] 参数仅仅告诉编译器返回值为 Integer[] 类型的数组，所以长度为0。
//        Integer[] arrray2 = list3.toArray(Integer[]::new);
        System.out.println("arrray2 = " + Arrays.toString(arrray2));    // arrray2 = [1, 2, 3]

        System.out.println("\n--- 数组缩容：trimToSize");
        ArrayList<Integer> list4 = new ArrayList<>();
        // 向数组内添加1万个元素：
        for (int i = 0; i < 10000; i++) {
            list4.add(i);
        }
        // ⚠️ clear 只是把所有元素设置为 null，size设置为0，但数组的实际容量不会改变。如果长时间不会使用这么大的容量，会导致内存浪费！
        list4.clear();
        System.out.println("list4 size = " + list4.size());
        for (int i = 0; i < 10; i++) {
            list4.add(i);
        }
        // trimToSize，根据当前数组容量进行缩容。达到优化内存的效果！
        list4.trimToSize();

        System.out.println("\n--- 扩容数组元素个数：ensureCapacity");
        // 如果一开始就知道数组需要添加元素的个数，可在创建时制定
        List<Integer> list5 = new ArrayList<>(10000);
        for (int i = 0; i < 10000; i++) {
            list5.add(i);
        }

        ArrayList<Integer> list6 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list6.add(i);
        }
        // 如果预估到数组要添加元素的个数时，可以在添加元素前进行扩容。避免在大量添加元素过程中，对数组进行多次扩容和拷贝！
        list6.ensureCapacity(list6.size() + 1000);
        for (int i = 0; i < 10000; i++) {
            list6.add(i);
        }
    }
}
