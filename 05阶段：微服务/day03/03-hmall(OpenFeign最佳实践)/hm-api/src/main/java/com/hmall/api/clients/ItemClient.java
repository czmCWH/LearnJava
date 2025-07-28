package com.hmall.api.clients;

import com.hmall.api.dto.ItemDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

// @FeignClient 注解接收一个 服务名称 来标记此接口为 OpenFeign 客户端，底层会实现一个动态代理去实现接口中的方法。
// OpenFeign 客户端根据 服务名称(value = "item-service") 去注册中心拉取 服务实例列表信息。OpenFeign 通过负载均衡器从服务列表中挑选一个 服务实例，用于发送 http 请求。
@FeignClient(value = "item-service")
public interface ItemClient {

    /**
     * OpenFeign 客户端采用 Spring MVC 注解来发送 http 请求，当然 OpenFeign 也有自身的一套注解，这样写避免学习成本问题。
     * 如下：根据商品id字符串数组，查询商品列表
     */
    @GetMapping("/items")
    List<ItemDTO> queryItemByIds(@RequestParam("ids") Collection<Long> ids);
}
