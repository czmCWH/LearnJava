package com.itheima.stock.mapper;

import com.itheima.stock.pojo.entity.StockBlockRtInfo;

/**
* @author chen
* @description 针对表【stock_block_rt_info(股票板块详情信息表)】的数据库操作Mapper
* @createDate 2025-09-03 19:38:04
* @Entity com.itheima.stock.pojo.entity.StockBlockRtInfo
*/
public interface StockBlockRtInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockBlockRtInfo record);

    int insertSelective(StockBlockRtInfo record);

    StockBlockRtInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockBlockRtInfo record);

    int updateByPrimaryKey(StockBlockRtInfo record);

}
