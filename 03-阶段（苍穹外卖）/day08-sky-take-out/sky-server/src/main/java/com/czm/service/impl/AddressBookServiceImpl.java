package com.czm.service.impl;

import com.czm.constant.StatusConstant;
import com.czm.context.BaseContext;
import com.czm.entity.AddressBook;
import com.czm.mapper.AddressBookMapper;
import com.czm.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    private AddressBookMapper addressBookMapper;

    @Override
    public void save(AddressBook addressBook) {
//        addressBook.setUserId(BaseContext.getCurrentId());
        addressBook.setUserId(4L);
        addressBook.setIsDefault(0);
        addressBookMapper.insert(addressBook);
    }

    @Override
    public List<AddressBook> list(AddressBook addressBook) {
        return addressBookMapper.list(addressBook);
    }

    @Override
    public AddressBook getById(Long id) {
        AddressBook addressBook = addressBookMapper.getById(id);
        return addressBook;
    }

    @Override
    public void update(AddressBook addressBook) {
        addressBookMapper.update(addressBook);
    }

    @Override
    public void deleteById(Long id) {
        addressBookMapper.delete(id);
    }

    @Override
    public void setDefault(AddressBook addressBook) {
        // 1、将当前用户的所有地址，设置为非默认地址
//        addressBook.setUserId(BaseContext.getCurrentId());
        addressBook.setUserId(4L);
        addressBook.setIsDefault(0);
        addressBookMapper.updateIsDefaultByUserId(addressBook);

        // 2、设置当前地址为默认地址
        // ⚠️：可以先查一下该地址是否是默认地址，然后取反
        addressBook.setIsDefault(1);
        addressBookMapper.update(addressBook);
    }
}
