package com.czm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 演示 - 在Java中操作Redis
 */

@SpringBootTest     // ⚠️ 单元测试时，需要使用 Spring IOC 容器环境时，则需添加 @SpringBootTest 注解
public class TestRedis {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * string 数据操作
     */
    @Test
    public void testString() {
        // 1、获取 ValueOperations 对象
        ValueOperations valOpt = redisTemplate.opsForValue();

        // 2、使用 ValueOperations 对象操作 String 类型的数据
        valOpt.set("name", "zhangsan");     // 对应 set 命令
        Object value = valOpt.get("name");  // 对应 get 命令
        System.out.println("--- name: " + value);

        // 案例：存储验证码，过期时间为10秒
        valOpt.set("code", "1234", 10, TimeUnit.SECONDS);
        Object codeValue = valOpt.get("code");
        System.out.println("--- code: " + codeValue);
    }

    /**
     * hash 类型的数据操作
     */
    @Test
    public void testHash() {
        // 1、获取 HashOperations 对象
        HashOperations hashOpt = redisTemplate.opsForHash();

        // 2、通过 HashOperations 对象，操作 hash 类型的数据
        hashOpt.put("user", "name", "zhangsan");
        hashOpt.put("user", "age", 18);
        hashOpt.put("user", "gender", "男");

        Object name = hashOpt.get("user", "name");
        System.out.println("--- name: " + name);

        // 获取 hash 中所有的 fields 属性
        Set fields = hashOpt.keys("user");
        System.out.println("--- all fields: " + fields);

        // 获取 hash 类型中所有的值
        List<Object> values = hashOpt.values("user");
        System.out.println("--- all values: " + values);

        // 获取 user 对象
        Map<Object, Object> map = hashOpt.entries("user");
        System.out.println("--- user: " + map);
    }

    /**
     * zset 类型数据操作
     */
    @Test
    public void testZSet() {
        // 1、获取 ZSetOperations 对象
        ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();

        // 2、通过 ZSetOperations 对象操作 ZSet 类型的数据
        zSetOps.add("zset01", "zhangsan", 50.5);
        zSetOps.add("zset01", "lisi", 30);
        zSetOps.add("zset01", "wangwu", 34.2);

        Set zset01 = zSetOps.range("zset01", 0, -1);
        System.out.println("--- zset01: " + zset01);

        Set zset01Score = zSetOps.rangeWithScores("zset01", 0, -1);
        System.out.println("--- zset01Score: " + zset01Score);
        for (Object o : zset01Score) {
             Double score = ((DefaultTypedTuple) o).getScore();
             System.out.println("--- score: " + score);
        }
    }

    /**
     * set 类型数据操作
     */
    @Test
    public void testSet() {
        // 1、获取 SetOperations 对象
        SetOperations setOpt = redisTemplate.opsForSet();

        // 2、通过 SetOperations 对象，操作 Set 类型数据

        setOpt.add("set01", "java", "go", "python");
        Set set01 = setOpt.members("set01");
        System.out.println("--- set01: " + set01);

        setOpt.add("set02", "java", "go", "C++");
        Set set02 = setOpt.members("set02");
        System.out.println("--- set02: " + set02);

        // 求交集
        Set intersect = setOpt.intersect("set01", "set02");
        System.out.println("--- 交集 intersect: " + intersect);

        // 求并集
        Set union = setOpt.union("set01", "set02");
        System.out.println("--- 并集 union：: " + union);

        // 求差集
        Set difference = setOpt.difference("set01", "set02");
        System.out.println("--- 并集 difference：: " + difference);

    }

    /**
     * list 类型的数据操作
     */
    @Test
    public void testList() {
        // 1、获取 ListOperations 对象
        ListOperations listOpt = redisTemplate.opsForList();

        // 2、通过 ListOperations 对象，操作 List 类型数据
        listOpt.leftPushAll("list01", "aaa", "bbb", "ccc");     // 在集合作左边依次添加多个元素
        listOpt.rightPushAll("list01", "111", "222", "333");    // 在集合作右边依次添加多个元素

        List list01 = listOpt.range("list01", 0, -1);
        System.out.println("--- list01: " + list01);

        listOpt.rightPop("list01");     // 删除集合右边1个元素
        List list02 = listOpt.range("list01", 0, -1);
        System.out.println("--- list02: " + list02);

        listOpt.leftPop("list01", 2);   // 删除集合左边2个元素
        List list03 = listOpt.range("list01", 0, -1);
        System.out.println("--- list03: " + list03);

    }

}
