package com.czm.controller.admin;

import com.czm.result.Result;
import com.czm.service.OrdersService;
import com.czm.service.ReportService;
import com.czm.vo.OrderReportVO;
import com.czm.vo.SalesTop10ReportVO;
import com.czm.vo.TurnoverReportVO;
import com.czm.vo.UserReportVO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * 数据统计模块
 */

@Slf4j
@RequestMapping("/admin/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private OrdersService ordersService;

    /**
     * 根据开始-结束时间 统计营业额数据
     */
    @GetMapping("/turnoverStatistics")
    public Result<TurnoverReportVO> turnoverStatistics(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                     @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        // @DateTimeFormat 注解用于在 Controller 的方法参数、DTO 实体类中接收时间参数，前端传递 "2025-07-15" 类型的字符串即可。
        log.info("--- 统计营业额数据 begin:{},end:{}", begin, end);
        TurnoverReportVO vo = reportService.turnoverStatistics(begin, end);
        return Result.success(vo);
    }

    /**
     * 用户统计
     */
    @GetMapping("/userStatistics")
    public Result<UserReportVO> userStatistics(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                               @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("--- 用户统计： begin:{},end:{}", begin, end);
        UserReportVO vo = reportService.userStatistics(begin, end);
        return Result.success(vo);
    }

    /**
     * 订单统计
     */
    @GetMapping("/orderStatistics")
    public Result<OrderReportVO> orderStatistics(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                                 @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("--- 订单统计： begin:{},end:{}", begin, end);
        OrderReportVO vo = reportService.orderStatistics(begin, end);
        return Result.success(vo);
    }

    /**
     * 商品销量排名前10
     */
    @GetMapping("/top10")
    public Result<SalesTop10ReportVO> top10(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("--- 商品销量排名 begin:{},end:{}", begin, end);
        SalesTop10ReportVO vo = reportService.top10(begin, end);
        return Result.success(vo);
    }

    /**
     * 导出运营数据报表
     * @param response
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response){
        reportService.exportBusinessData(response);
    }
}