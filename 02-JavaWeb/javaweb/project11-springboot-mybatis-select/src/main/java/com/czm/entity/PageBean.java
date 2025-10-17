package com.czm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页结果封装类
 * ⚠️⚠️⚠️，定义实体类时，类型都要写包装类，如果用基本类型，它会有默认值。这些默认值可能会干扰项目业务逻辑。
 *  比如：int a; 默认值为 0；
 *      Integer a; 默认值为 null
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean<T> {
    /**
     * 总记录数
     */
    private Long total;
    /**
     * 数据列表
     */
    private List<T> rows;
}
