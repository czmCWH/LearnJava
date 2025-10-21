package com.czm.service.impl;

import com.czm.entity.JobOption;
import com.czm.mapper.EmpMapper;
import com.czm.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl extends ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public JobOption getEmpJobData() {
        // 1、调用 Mapper 接口获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData();

        // 2、组装结果，并返回
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();  // 获取职位 List
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();  // 获取职位对应的人数 List
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }
}
