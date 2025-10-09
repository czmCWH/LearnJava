package com.czm.d6_Lambda;

public class OuterClass {
    private int age = 1;

    public class InnerClass {
        private int age = 2;
        void doSomething() {
//            int v = 33;   // 会导致 lambda 中的 v 变量报错！
            Teatable t = v -> {
                System.out.println(v);  // 22
                System.out.println(age);  // 2
                System.out.println(this.age);  // 2
                System.out.println(InnerClass.this.age);  // 2
                System.out.println(OuterClass.this.age);  // 1
            };

//            Teatable t1 = new Teatable() {
//                @Override
//                public void test(Integer value) {
//                    System.out.println(value);  // 22
//                    System.out.println(age);  // 2
//                    System.out.println(this.age);  // 使用 this 报错！因为 this 表示匿名类创建出来的对象。
//                    System.out.println(InnerClass.this.age);  // 2
//                    System.out.println(OuterClass.this.age);  // 1
//                }
//            };

            t.test(22);
//            t1.test(22);
        }
    }
}
