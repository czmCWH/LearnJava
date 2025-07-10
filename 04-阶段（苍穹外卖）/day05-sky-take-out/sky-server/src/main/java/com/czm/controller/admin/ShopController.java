package com.czm.controller.admin;

import com.czm.dto.CategoryDTO;
import com.czm.result.Result;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * 商家端：店铺相关 Controller  --- 访问 Redis 数据库
 */

@Slf4j
@RestController("adminShopController")      // 指定 Bean 名称
@RequestMapping("/admin/shop")
public class ShopController {

    public static final String key = "SHOP_STATUS";

    // 引入 RedisTemplate
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置店铺 营业状态
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    public Result<String> setStatus(@PathVariable Integer status) {     // @PathVariable 注解接收 status 路径参数
        log.info("--- 设置店铺营业状态 = {} ", status == 1 ? "营业中" : "打样中");

        // ⚠️：由于 营业状态 只是一个字段，如果用一张表来存储是没什么意义的。因此使用 基于 Redis 字符串来进行存储。
        redisTemplate.opsForValue().set(key, status);
        return Result.success();
    }

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
