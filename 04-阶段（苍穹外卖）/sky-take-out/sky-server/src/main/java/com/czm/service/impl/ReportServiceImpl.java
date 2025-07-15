package com.czm.service.impl;


import com.czm.dto.GoodsSalesDTO;
import com.czm.entity.Orders;
import com.czm.mapper.OrdersMapper;
import com.czm.mapper.UserMapper;
import com.czm.service.ReportService;
import com.czm.service.WorkSpaceService;
import com.czm.vo.*;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    OrdersMapper ordersMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    private WorkSpaceService workspaceService;


    @Override
    public TurnoverReportVO turnoverStatistics(LocalDate begin, LocalDate end) {
        // 1、准备日期数据
        List<LocalDate> dateList = getDateList(begin, end);

        // 2、查询每日营业额列表数据
        // 查询已完成订单的每日营业额，查询 orders 表，条件：状态-已完成，下单时间
        List<Double> turnoverList = new ArrayList<>();

        dateList.forEach(date -> {
            Map map = new HashMap();
            map.put("status", Orders.COMPLETED);
            map.put("beginTime", LocalDateTime.of(date, LocalTime.MIN));    // 2024-05-17 00:00:00
            map.put("endTime", LocalDateTime.of(date, LocalTime.MAX));  // 2024-05-17 23:59:59.999999999
            // 调用 Mapper 获取每日营业额
            // select count(amount) from orders where status = 5 and order_time between '2024-05-17 00:00:00' and '2024-05-17 23:59:59.999999999'
            Double turnover = ordersMapper.sumByMap(map);
            // ⚠️：sql 语句中使用了 sum 函数，如果没有数据会返回 null
            turnover = turnover == null ? 0 : turnover;
            turnoverList.add(turnover);
        });

        // 3、构造 TurnoverReportVO 对象返回
        TurnoverReportVO turnoverReportVO = TurnoverReportVO.builder()
                .dateList(StringUtils.join(dateList, ","))
                .turnoverList(StringUtils.join(turnoverList, ","))
                .build();

        return turnoverReportVO;
    }

    @Override
    public UserReportVO userStatistics(LocalDate begin, LocalDate end) {
        // 1、构造时间 List 数据
        List<LocalDate> dateList = getDateList(begin, end);

        // 2、查询 user 表每日新增用户数据
        List<Integer> newUserList = new ArrayList<>();

        // 3、查询 user 表截止到每日总用户列表数据
        List<Integer> totalUserList = new ArrayList<>();

        // 循环遍历日期List统计每日的新增用户数
        dateList.forEach(date -> {
            // 1、每日新增
            // select count(*) from user where create_time between #{} and #{}
            // 或者
            // select count(*) from user where create_time >= 当天开始时间 and create_time <= 当天结束时间
            Map map = new HashMap();
            map.put("beginTime", LocalDateTime.of(date, LocalTime.MIN));    // 2024-05-17 00:00:00
            map.put("endTime", LocalDateTime.of(date, LocalTime.MAX));      // 2024-05-17 23:59:59.999999999
            // ⚠️：sql 语句中使用了 count 函数，如果没有数据会返回 0
            Integer newCount = userMapper.countByMap(map);
            newUserList.add(newCount);

            // 2、截止每日总数
            // select count(*) from user where create_time <= #{endTime}
            map.put("beginTime", null);  // 查询的是总数，所以不需要 beginTime
            Integer totalCount = userMapper.countByMap(map);
            totalUserList.add(totalCount);
        });

        // 4、构造 UserReportVO 对象返回
        return UserReportVO.builder()
                .dateList(StringUtils.join(dateList, ","))
                .newUserList(StringUtils.join(newUserList, ","))
                .totalUserList(StringUtils.join(totalUserList, ","))
                .build();
    }

    @Override
    public OrderReportVO orderStatistics(LocalDate begin, LocalDate end) {
        // 1、构造时间段 List 数据
        List<LocalDate> dateList = getDateList(begin, end);

        // 2、获取每日订单总数
        // 条件：订单状态=已完成 下单时间 >= 当天起始时间 and 下单时间 <= 当天结束时间
        List<Integer> orderCountList = new ArrayList<>();

        // 3、获取每日有效订单数
        // 条件：下单时间 >= 当天起始时间 and 下单时间 <= 当天结束时间
        List<Integer> validOrderCountList = new ArrayList<>();

        // 4、获取 时间段 内的订单总数
        // 条件：下单时间 >= begin and 下单时间 <= end
//        Map map = new HashMap();
//        map.put("beginTime", LocalDateTime.of(begin, LocalTime.MIN));    // 2024-05-10 00:00:00
//        map.put("endTime", LocalDateTime.of(end, LocalTime.MAX));      // 2024-05-16 23:59:59.999999999
        Integer totalOrderCount = 0;

        // 5、获取 时间段 内的有效订单总数
        Integer totalValidOrderCount = 0;

        dateList.forEach(date -> {
            Map map = new HashMap();
            map.put("beginTime", LocalDateTime.of(date, LocalTime.MIN));    // 2024-05-17 00:00:00
            map.put("endTime", LocalDateTime.of(date, LocalTime.MAX));      // 2024-05-17 23:59:59.999999999

            Integer count = ordersMapper.countByMap(map);
            orderCountList.add(count);

            // 已完成订单
            map.put("status", Orders.COMPLETED);
            Integer validCount = ordersMapper.countByMap(map);
            validOrderCountList.add(validCount);

            // ⚠️：forEach 中不允许实现外部变量的累加，有2种方法解决：
            // 方法1、改用在 for 循环中累加；
            // 方法2、stream 流的方式实现；
//            totalOrderCount += count;
//            totalValidOrderCount += validCount;
        });


        // 方式1：可以构造时间段等参数，再次查询一次。方式2：把单次查询的累加即可。
//        for (LocalDate date : dateList) {
//            Map map = new HashMap();
//            map.put("beginTime", LocalDateTime.of(date, LocalTime.MIN));    // 2024-05-17 00:00:00
//            map.put("endTime", LocalDateTime.of(date, LocalTime.MAX));      // 2024-05-17 23:59:59.999999999
//
//            Integer count = ordersMapper.countByMap(map);
//            orderCountList.add(count);
//
//            // 已完成订单
//            map.put("status", Orders.COMPLETED);
//            Integer validCount = ordersMapper.countByMap(map);
//            validOrderCountList.add(validCount);
//
//            totalOrderCount += count;
//            totalValidOrderCount += validCount;
//        }

        // 方法2、stream 流的方式实现；
//        totalOrderCount = orderCountList.stream().reduce(new BinaryOperator<Integer>() {  // 匿名内部类
//            @Override
//            public Integer apply(Integer integer, Integer integer2) {     // 累加器
//                return integer + integer2;
//            }
//        }).get();
        totalOrderCount = orderCountList.stream().reduce(0, Integer::sum);
        totalValidOrderCount = validOrderCountList.stream().reduce(0, Integer::sum);

        // 6、计算订单完成率
        Double orderCompletionRate = 0.0;
        if (totalOrderCount != 0) {
            orderCompletionRate = (totalValidOrderCount + 0.0) / totalOrderCount;
        }

        // 7、封装对象返回
        return OrderReportVO.builder()
                .dateList(StringUtils.join(dateList, ","))
                .orderCountList(StringUtils.join(orderCountList, ","))
                .validOrderCountList(StringUtils.join(validOrderCountList, ","))
                .totalOrderCount(totalOrderCount)
                .validOrderCount(totalValidOrderCount)
                .orderCompletionRate(orderCompletionRate)
                .build();
    }

    @Override
    public SalesTop10ReportVO top10(LocalDate begin, LocalDate end) {

        // 1、查询 order_detail 订单明细表，销量前10的商品 名字 + 销量
        // 需结合 orders 表，查询订单状态为 已完成的订单
        /*
         select od.name, sum(od.number) sumNum from orders o, order_detail od
         where o.id = od.order_id and o.status = 5
         group by name
         order by sumNum desc
         */

        Map map = new HashMap();
        map.put("beginTime", LocalDateTime.of(begin, LocalTime.MIN));   // 2025-07-10 00:00:00
        map.put("endTime", LocalDateTime.of(end, LocalTime.MAX));   // 2025-07-16 23:59:59.99999999
        map.put("status", Orders.COMPLETED);

        // 查询 所有订单的商品 名称+销量 List
        List<GoodsSalesDTO> list = ordersMapper.sumTop10(map);

//        List<String> nameList = new ArrayList<>();
//        List<Integer> numberList = new ArrayList<>();
//        for (GoodsSalesDTO dto : list) {
//            nameList.add(dto.getName());
//            numberList.add(dto.getNumber());
//        }

        List<String> nameList = list.stream().map(GoodsSalesDTO::getName).toList();
        List<Integer> numberList = list.stream().map(GoodsSalesDTO::getNumber).toList();

        return SalesTop10ReportVO.builder()
                .nameList(StringUtils.join(nameList, ","))
                .numberList(StringUtils.join(numberList, ","))
                .build();
    }

    /**
     * 获取日期列表数据
     */
    private List<LocalDate> getDateList(LocalDate begin, LocalDate end) {
        // 1、准备日期数据
        List<LocalDate> dateList = new ArrayList<LocalDate>();
        // 循环插入日期数据
        while (!begin.isAfter(end)) {   // isAfter 是闭区间，begin.isBefore(end) 是左包含
            dateList.add(begin);
            begin = begin.plusDays(1);  // ⚠️，如果忘记修改判断条件，小心死循环，导致 OOM报错
        }

        log.info("--- dateList = {}", dateList);
        return dateList;
    }

    /**
     * 导出近30天的运营数据报表
     */
    public void exportBusinessData(HttpServletResponse response) {
        LocalDate begin = LocalDate.now().minusDays(30);
        LocalDate end = LocalDate.now().minusDays(1);
        //查询概览运营数据，提供给Excel模板文件
        BusinessDataVO businessData = workspaceService.getBusinessData(LocalDateTime.of(begin,LocalTime.MIN), LocalDateTime.of(end, LocalTime.MAX));
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("template/运营数据报表模板.xlsx");
        try {
            //基于提供好的模板文件创建一个新的Excel表格对象
            XSSFWorkbook excel = new XSSFWorkbook(inputStream);
            //获得Excel文件中的一个Sheet页
            XSSFSheet sheet = excel.getSheet("sheet0");

            sheet.getRow(1).getCell(1).setCellValue(begin + "至" + end);
            //获得第4行
            XSSFRow row = sheet.getRow(3);
            //获取单元格
            row.getCell(2).setCellValue(businessData.getTurnover());
            row.getCell(4).setCellValue(businessData.getOrderCompletionRate());
            row.getCell(6).setCellValue(businessData.getNewUsers());
            row = sheet.getRow(4);
            row.getCell(2).setCellValue(businessData.getValidOrderCount());
            row.getCell(4).setCellValue(businessData.getUnitPrice());
            for (int i = 0; i < 30; i++) {
                LocalDate date = begin.plusDays(i);
                //准备明细数据
                businessData = workspaceService.getBusinessData(LocalDateTime.of(date,LocalTime.MIN), LocalDateTime.of(date, LocalTime.MAX));
                row = sheet.getRow(7 + i);
                row.getCell(1).setCellValue(date.toString());
                row.getCell(2).setCellValue(businessData.getTurnover());
                row.getCell(3).setCellValue(businessData.getValidOrderCount());
                row.getCell(4).setCellValue(businessData.getOrderCompletionRate());
                row.getCell(5).setCellValue(businessData.getUnitPrice());
                row.getCell(6).setCellValue(businessData.getNewUsers());
            }
            // 通过输出流将文件下载到客户端浏览器中
            ServletOutputStream out = response.getOutputStream();
            excel.write(out);

            // 关闭资源
            out.flush();
            out.close();
            excel.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
