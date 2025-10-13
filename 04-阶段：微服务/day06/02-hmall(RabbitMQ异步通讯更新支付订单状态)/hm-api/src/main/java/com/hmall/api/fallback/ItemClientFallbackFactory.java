package com.hmall.api.fallback;

import com.hmall.api.clients.ItemClient;
import com.hmall.api.dto.ItemDTO;
import com.hmall.api.dto.OrderDetailDTO;
import com.hmall.common.utils.CollUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.Collection;
import java.util.List;

/**
 * 给 ItemClient（商品FeignClient）添加 Fallback 逻辑
 */
@Slf4j
public class ItemClientFallbackFactory implements FallbackFactory<ItemClient> {
    @Override
    public ItemClient create(Throwable cause) {
        // 返回需要被 fallback 逻辑处理的 FeignClient。如果 Sentinel 监控的 Feign 资源异常了，就会走此 FeignClient 中的逻辑。
        return new ItemClient() {
            // 当商品查询失败时，走 fallback 的逻辑
            @Override
            public List<ItemDTO> queryItemByIds(Collection<Long> ids) {
                log.info("--- 查询商品失败: ", cause);
                // 查询失败时，返回空数据
                return CollUtils.emptyList();
            }

            // 当扣减商品库存失败时，走 fallback 的逻辑
            @Override
            public void deductStock(List<OrderDetailDTO> items) {
                log.error("--- 扣减商品库存失败：: ", cause);
                throw  new RuntimeException(cause);
            }
        };
    }
}
