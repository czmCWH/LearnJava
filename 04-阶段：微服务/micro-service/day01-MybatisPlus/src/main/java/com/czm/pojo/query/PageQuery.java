package com.czm.pojo.query;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * 通用分页查询条件
 */
@Data
public class PageQuery {
    /** 起始页码 */
    private Integer pageNo;
    /** 没页条数 */
    private Integer pageSize;
    /** 排序字段名称 */
    private String sortBy;
    /** 排序方式 */
    private Boolean isAsc;

    /**
     * ⚠️ 封装分页条件，直接返回 Mybatis-Plus 的 分页 Page
     * @param orders 其它额外的排序方式
     */
    public <T>  Page<T> toMpPage(OrderItem ... orders){
        // 1.分页条件
        Page<T> p = Page.of(pageNo, pageSize);
        // 2.排序条件
        // 2.1.先看前端有没有传排序字段
        if (StrUtil.isNotBlank(sortBy)) {
            p.addOrder((new OrderItem()).setColumn(sortBy).setAsc(isAsc));
            return p;
        }
        // 2.2.再看有没有手动指定排序字段
        if(orders != null){
            p.addOrder(orders);
        }
        return p;
    }

    /**
     * 通用的默认排序方式
     */
    public <T> Page<T> toMpPage(String defaultSortBy, boolean isAsc){
        return this.toMpPage((new OrderItem()).setColumn(defaultSortBy).setAsc(isAsc));
    }

    /**
     * 默认按照 create_time 排序
     */
    public <T> Page<T> toMpPageDefaultSortByCreateTimeDesc() {
        return toMpPage("create_time", false);
    }

    /**
     * 默认按照 update_time 排序
     */
    public <T> Page<T> toMpPageDefaultSortByUpdateTimeDesc() {
        return toMpPage("update_time", false);
    }
}
