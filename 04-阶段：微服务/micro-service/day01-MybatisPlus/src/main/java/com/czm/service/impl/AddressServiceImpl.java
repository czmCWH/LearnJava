package com.czm.service.impl;

import com.czm.pojo.entity.Address;
import com.czm.mapper.AddressMapper;
import com.czm.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author czm
 * @since 2025-07-17
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

}
