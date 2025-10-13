package com.hmall.item.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmall.api.dto.ItemDTO;
import com.hmall.api.dto.OrderDetailDTO;
import com.hmall.common.exception.BizIllegalException;
import com.hmall.common.utils.BeanUtils;
import com.hmall.item.domain.po.Item;
import com.hmall.item.mapper.ItemMapper;
import com.hmall.item.service.IItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author itheima
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

    @Override
    @Transactional
    public void deductStock(List<OrderDetailDTO> items) {
        String sqlStatement = "com.hmall.item.mapper.ItemMapper.updateStock";
        boolean r = false;
        try {
            r = executeBatch(items, (sqlSession, entity) -> sqlSession.update(sqlStatement, entity));
            
        } catch (Exception e) {
            log.error("更新库存异常", e);
            throw new BizIllegalException("库存不足！");
        }
        if (!r) {
            throw new BizIllegalException("库存不足！");
        }
    }

    @Override
    public List<ItemDTO> queryItemByIds(Collection<Long> ids) {
        return BeanUtils.copyList(listByIds(ids), ItemDTO.class);
    }

    @Override
    public boolean save(Item entity) {
        return super.save(entity);
    }

    @Override
    public boolean saveBatch(Collection<Item> entityList) {
        return super.saveBatch(entityList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void recoverStock(List<OrderDetailDTO> items) {
        String sqlStatement = "com.hmall.item.mapper.ItemMapper.recoverStock";
        boolean r = false;
        try {
            r = executeBatch(items, (sqlSession, entity) -> sqlSession.update(sqlStatement, entity));
        } catch (Exception e) {
            throw new BizIllegalException("更新库存异常，可能是库存不足!", e);
        }
        if (!r) {
            throw new BizIllegalException("库存不足！");
        }
        // todo 发送mq通知es更新

        // // 1. 批量查询当前库存
        // List<Long> itemIds = items.stream().map(OrderDetailDTO::getItemId).collect(Collectors.toList());
        // List<Item> currentItems = listByIds(itemIds);
        // Map<Long, Item> itemMap = currentItems.stream()
        //         .collect(Collectors.toMap(Item::getId, item -> item));
        //
        // // 2. 构建批量更新对象
        // List<Item> batchItems = items.stream()
        //         .map(dto -> {
        //             Item currentItem = itemMap.get(dto.getItemId());
        //             if (currentItem == null) {
        //                 throw new BizIllegalException("商品ID[" + dto.getItemId() + "]不存在");
        //             }
        //             Item updateItem = new Item();
        //             updateItem.setId(dto.getItemId());
        //             updateItem.setStock(currentItem.getStock() + dto.getNum());
        //             return updateItem;
        //         })
        //         .collect(Collectors.toList());
        //
        // // 3. 执行批量更新
        // boolean success = updateBatchById(batchItems);
        //
        // // 4. 结果校验
        // if (!success) {
        //     throw new BizIllegalException("批量恢复库存失败！");
        // }
    }
}
