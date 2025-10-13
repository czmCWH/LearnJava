package com.czm.service;

import com.czm.entity.AddressBook;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 地址簿模块
 */

public interface AddressBookService {

    /**
     * 新增地址簿
     */
    void save(AddressBook addressBook);

    /**
     * 查询当前用户的地址列表
     */
    List<AddressBook> list(AddressBook addressBook);

    /**
     * 根据地址ID查询单个地址信息
     */
    AddressBook getById(Long id);

    /**
     * 更新地址信息
     */
    void update(AddressBook addressBook);

    /**
     * 根据ID删除字符串
     */
    void deleteById(Long id);

    /**
     * 设置默认地址
     */
    void setDefault(AddressBook addressBook);
}
