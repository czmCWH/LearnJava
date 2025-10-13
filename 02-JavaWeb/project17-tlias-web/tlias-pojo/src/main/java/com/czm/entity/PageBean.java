package com.czm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 封装分页列表记录
 * ⚠️⚠️⚠️，定义实体类时，类型都要写包装类，如果用基本类型，它会有默认值。
 *  比如：int a; 默认值为 0；
 *      Integer a; 默认值为 null
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean {
    private Long total;
    private List rows;
}
