package com.czm.mapper;

import com.czm.entity.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {
    /**
     * 批量保存工作经验 list
     * 由于工作经历的个数是动态的，所以需要使用动态 SQL 实现，基于XML开发
     */
    void insertBatch(List<EmpExpr> exprList);
}
