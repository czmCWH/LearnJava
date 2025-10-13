package com.czm.service;

import com.czm.vo.OrderReportVO;
import com.czm.vo.SalesTop10ReportVO;
import com.czm.vo.TurnoverReportVO;
import com.czm.vo.UserReportVO;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;

/**
 * 统计数据报表
 */
public interface ReportService {

    /**
     * 统计营业额
     */
    TurnoverReportVO turnoverStatistics(LocalDate begin, LocalDate end);

    /**
     * 用户数量统计 - 根据开始时间-结束时间
     */
    UserReportVO userStatistics(LocalDate begin, LocalDate end);

    /**
     * 订单统计
     */
    OrderReportVO orderStatistics(LocalDate begin, LocalDate end);

    /**
     * 商品销量前10
     */
    SalesTop10ReportVO top10(LocalDate begin, LocalDate end);

    /**
     * 导出近30天的运营数据报表
     * @param response
     **/
    void exportBusinessData(HttpServletResponse response);
}
