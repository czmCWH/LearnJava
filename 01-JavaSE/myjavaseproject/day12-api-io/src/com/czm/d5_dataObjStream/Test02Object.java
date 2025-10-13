package com.czm.d5_dataObjStream;

import java.io.*;

public class Test02Object {
    /*
     1、对象流
      ObjectInputStream、ObjectOutputStream 支持引用类型的 I/O 操作。
      只有类和类的所有成员变量都实现了 java.io.Serializable 接口才能使用对象流进行 I/O 操作，否则会抛出 java.io.NotSerializableException 异常。

      Serializable 是一个标记接口（Maker Interface），不要求实现任何方法。

     2、对象的序列化和反序列化
      序列化（Serialization）
        将对象转换为可以存储或传输的数据；
        利用 ObjectOutputStream 可以实现对象的序列化；

      反序列化（Deserialization）
        从序列化后的数据中恢复对象；
        利用 ObjectInputStream 可以实现对象的反序列化。

      一般很少用序列化，Java 中大多数使用数据库。

     3、transient
     ⚠️ 被 transient 修饰的实例变量不会被序列化，反序列化获取该实例变量的值为 null。

     4、serialVersionUID
      每一个可序列化类都有一个 serialVersionUID，相当于版本号。
      默认情况下会根据类的详细信息计算出 serialVersionUID 的值，根据编译器实现的不同可能千差万别。
      一旦类的信息发送了修改，serialVersionUID 的值就会发生变化。

      如果序列化、反序列化的 serialVersionUID 不一致。
      会认定为序列化、反序列化时的类不兼容，抛出 java.io.InvalidClassException 异常。

      强烈建议每一个可序列化类都自定义 serialVersionUID，不要使用它的默认值。
      并且 serialVersionUID 必须是 static final long。
        private static final long serialVersionUID = 1L;

      例如：如果 Person 对象序列化时没有 score 实例变量，而反序列化有，则会报错：
      stream classdesc serialVersionUID = 8768914684806128343, local class serialVersionUID = 1179436070560118400

     */
    public static void main(String[] args) throws Exception {

        Person p = new Person("Jack", 18, 1.75);
        p.setCar(new Car(820000, "小米"));

        p.getBooks().add(new Book(22, "书籍1"));
        p.getBooks().add(new Book(32, "书籍2"));
        p.getBooks().add(new Book(42, "书籍3"));

        testObjectIO(p);

//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("/Users/chen/Desktop/1.txt")));
//        Person pd = (Person) ois.readObject();
//        System.out.println("读取 Person = " + pd);

    }

    static void testObjectIO(Person p) throws Exception {
        // 1、先后写入 Person、Car 2个对象
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("/Users/chen/Desktop/1.txt")));
        // 写入 Person 对象
        oos.writeObject(p);

        // 写入 Car 对象
        Car c = new Car(1000000, "吉利");
        oos.writeObject(c);

        oos.close();

        // 2、依次读取 Person、Car 对象
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("/Users/chen/Desktop/1.txt")));
        Person pd = (Person) ois.readObject();
        System.out.println("读取 Person = " + pd);

        Car cd = (Car) ois.readObject();
        System.out.println("读取 Car = " + cd);
        ois.close();
    }
}
