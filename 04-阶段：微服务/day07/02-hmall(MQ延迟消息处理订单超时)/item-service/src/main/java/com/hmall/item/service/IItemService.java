package com.hmall.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hmall.api.dto.ItemDTO;
import com.hmall.api.dto.OrderDetailDTO;
import com.hmall.item.domain.po.Item;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author itheima
 * @since 2023-05-05
 */
public interface IItemService extends IService<Item> {

    /**
     * 扣减库存
     * @param items
     */
    void deductStock(List<OrderDetailDTO> items);

    List<ItemDTO> queryItemByIds(Collection<Long> ids);

    /**
     * 恢复库存
     * @param items
     */
    void recoverStock(List<OrderDetailDTO> items);
}
