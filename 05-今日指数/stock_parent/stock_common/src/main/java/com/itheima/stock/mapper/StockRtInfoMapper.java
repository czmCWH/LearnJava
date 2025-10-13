package com.itheima.stock.mapper;

import com.itheima.stock.pojo.entity.StockRtInfo;

/**
* @author chen
* @description 针对表【stock_rt_info(个股详情信息表)】的数据库操作Mapper
* @createDate 2025-09-03 19:38:04
* @Entity com.itheima.stock.pojo.entity.StockRtInfo
*/
public interface StockRtInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockRtInfo record);

    int insertSelective(StockRtInfo record);

    StockRtInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockRtInfo record);

    int updateByPrimaryKey(StockRtInfo record);

}
