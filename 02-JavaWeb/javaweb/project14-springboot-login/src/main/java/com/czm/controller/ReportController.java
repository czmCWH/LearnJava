package com.czm.controller;

import com.czm.entity.JobOption;
import com.czm.entity.Result;
import com.czm.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 数据统计报表 Controller
 */
@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 统计员工表中 员工职位人数信息
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("--- 统计员工职位人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     * 统计员工表中男女人数
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        log.info("-- 统计员工表中男女人数");
        List<Map<String, Object>> list = reportService.getEmpGenderData();
        return Result.success(list);
    }

}
