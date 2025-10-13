package com.czm.mapper;

import com.czm.entity.AddressBook;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 地址簿模块
 */

@Mapper
public interface AddressBookMapper {

    /**
     * 新增地址博
     */
    @Insert("insert into address_book" + "(user_id, consignee, sex, phone, province_code, province_name, city_code, city_name, district_code, " +
            "district_name, detail, label, is_default) " + "values (#{userId}, #{consignee}, #{sex}, #{phone}, #{provinceCode}, #{provinceName}, #{cityCode}, #{cityName}, #{districtCode}, #{districtName}, #{detail}, #{label}, #{isDefault})")
    void insert(AddressBook addressBook);

    /**
     * 根据条件查询当前用户的地址列表
     */
    List<AddressBook> list(AddressBook addressBook);

    /**
     * 根据地址ID查询单个地址信息
     */
    @Select("select * from address_book where id = #{addressBookId}")
    AddressBook getById(Long addressBookId);

    /**
     * 根据ID修改单条地址信息
     */
    void update(AddressBook addressBook);

    /**
     * 根据ID删除地址
     */
    @Delete("delete from address_book where id = #{addressBookId}")
    void delete(Long addressBookId);

    /**
     * 根据用户ID，设置该用户所有默认地址
     * @param addressBook
     */
    @Update("update address_book set is_default = #{isDefault} where user_id = #{userId}")
    void updateIsDefaultByUserId(AddressBook addressBook);
}
