package com.czm;

import com.czm.controller.DeptController;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class Project16SpringbootBeanApplicationTests {

    // 获取IOC容器管理对象
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 测试 - 通过 IOC 容器获取 bean 对象的方式
     */
    @Test
    void testGetBean() {
        // 1、根据 bean 的名称获取
        DeptController bean1 = (DeptController) applicationContext.getBean("deptController");
        System.out.println("------ bean1 = " + bean1);

        // 2、根据 bean 的类型获取
        DeptController bean2 = applicationContext.getBean(DeptController.class);
        System.out.println("------ bean2 = " + bean2);

        // 3、根据 bean 的名称 及 类型获取
        DeptController bean3 = applicationContext.getBean("deptController", DeptController.class);
        System.out.println("------ bean3 = " + bean2);
    }

    /**
     * 测试 - 获取的 Bean 对象是否是单例
     */
    @Test
    void testScope() {
        for (int i = 0; i < 10; i++) {
            DeptController bean1 = (DeptController) applicationContext.getBean("deptController");
            System.out.println("------ bean1 = " + bean1);
        }
    }

    // 依赖注入 第三方 Bean
    @Autowired
    private SAXReader reader;

    @Test
    public void testThirdBean() throws DocumentException {
//        SAXReader reader = new SAXReader();   // 使用 Springboot 后，基本不会 new 对象了。

        Document document = reader.read(this.getClass().getClassLoader().getResource("1.xml"));
        Element root = document.getRootElement();
        String name = root.element("name").getText();
        String age = root.element("age").getText();

        System.out.println("--- name = " + name + ", age = " + age);
    }

    @Test
    public void testBeanName() {
        Object bean1 = applicationContext.getBean("getSAXReader");
        System.out.println("------ 获取第三方 bean 的名字 bean1 = " + bean1);
    }
}
