package com.hmall.api.clients.fallback;

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
        return new ItemClient() {
            @Override
            public List<ItemDTO> queryItemByIds(Collection<Long> ids) {
                log.error("查询商品信息失败", cause);
                return CollUtils.emptyList();
            }

            @Override
            public ItemDTO queryItemById(Long id) {
                log.error("查询商品信息失败", cause);
                return null;
            }

            @Override
            public void deductStock(List<OrderDetailDTO> items) {
                log.error("扣减库存失败", cause);
                throw new RuntimeException(cause);
            }

            @Override
            public void recoverStock(List<OrderDetailDTO> items) {
                log.error("恢复库存失败", cause);
                throw new RuntimeException(cause);
            }
        };
    }
}
