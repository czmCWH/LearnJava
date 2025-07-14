package com.czm.controller.user;

import com.czm.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * 用户端：店铺相关 Controller  --- 访问 Redis 数据库
 * 如果：com.czm.admin 和 com.czm.user 包下面存在同名的 Controller，启动项目会报错，因为这2个 controller 都会添加到 spring 容器中，
 * 它们 Bean 的名称相同，会导致冲突。因此需要使用 @RestController 注解来指定它们 bean 的名称。
 */

@Slf4j
@RestController("userShopController")   // 指定 Bean 名称
@RequestMapping("/user/shop")
public class ShopController {

    public static final String key = "SHOP_STATUS";

    // 引入 RedisTemplate
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取店铺 营业状态
     * @return
     */
    @GetMapping("/status")
    public Result<Integer> getStatus() {

        Integer status = (Integer) redisTemplate.opsForValue().get(key);
        log.info("--- 获取店铺营业状态 = {} ", status == 1 ? "营业中" : "打样中");
        return Result.success(status);
    }
}
