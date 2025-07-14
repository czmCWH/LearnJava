package com.czm.controller.user;

import com.czm.context.BaseContext;
import com.czm.entity.AddressBook;
import com.czm.result.Result;
import com.czm.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 地址簿模块
 * 1、新增地址
 * 2、查询当前登录用户的所有地址列表
 * 3、根据地址ID查询地址信息
 * 4、根据ID修改地址
 * 5、根据ID删除地址
 * 6、设置默认地址
 * 7、查询默认地址
 */

@Slf4j
@RestController
@RequestMapping("/user/addressBook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    /**
     * 新增地址簿
     */
    @PostMapping
    public Result<String> insert(@RequestBody AddressBook addressBook) {    // @RequestBody 注解接收 json 参数
        addressBookService.save(addressBook);
        return Result.success();
    }

    /**
     * 查询当前登录用户的所有地址信息
     */
    @GetMapping("/list")
    public Result<List<AddressBook>> list() {
        AddressBook addressBook = new AddressBook();
//        addressBook.setUserId(BaseContext.getCurrentId());
        addressBook.setUserId(4L);
        List<AddressBook> list = addressBookService.list(addressBook);
        return Result.success(list);
    }

    /**
     * 根据地址ID查询地址信息
     */
    @GetMapping("/{id}")
    public Result<AddressBook> getById(@PathVariable Long id) {  // @PathVariable 注解接收URL路径请求参数
        AddressBook addressBook = addressBookService.getById(id);
        return Result.success(addressBook);
    }

    /**
     * 根据ID修改地址
     */
    @PutMapping
    public Result<String> update(@RequestBody AddressBook addressBook) {    // @RequestBody 注解接收json查询参数
        addressBookService.update(addressBook);
        return Result.success();
    }

    /**
     * 根据ID删除地址
     */
    @DeleteMapping
    public Result<String> deleteById(Long id) {     // 接收URL字符串查询参数
        addressBookService.deleteById(id);
        return Result.success();
    }

    /**
     * 设置默认地址
     */
    @PutMapping("/default")
    public Result<String> setDefault(@RequestBody AddressBook addressBook) {    // @RequestBody 注解接收 json 参数
        addressBookService.setDefault(addressBook);
        return Result.success();
    }

    /**
     * 查询默认地址
     */
    @GetMapping("/default")
    public Result<AddressBook> getDefault() {
        AddressBook addressBook = new AddressBook();
        addressBook.setIsDefault(1);
//        addressBook.setUserId(BaseContext.getCurrentId());
        addressBook.setUserId(4L);
        List<AddressBook> list = addressBookService.list(addressBook);

        if (list != null && list.size() == 1) {
            return Result.success(list.get(0));
        }

        return Result.error("没有查询到默认地址");
    }

}
