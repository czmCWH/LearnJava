package com.czm;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestPOI {

    // 需求1：通过 POI 框架生成 Miscrosoft office Excel 文件

    /**
     * 创建 Excel 文件填写内容
     */
    @Test
    public void testWrite() throws IOException {
        // 1、通过 POI 创建工作簿（文件）对象 --- excel 对象
        XSSFWorkbook workbook = new XSSFWorkbook();

        // 2、通过 workbook 创建 sheet 工作表对象
        XSSFSheet sheet = workbook.createSheet();

        // 3、通过 sheet 创建行对象
        XSSFRow row = sheet.createRow(0);

        // 4、通过 XSSFRow 创建单元格，并填充数据
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("爱好");

        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("张三");
        row1.createCell(1).setCellValue("打篮球");

        XSSFRow row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("李四");
        row2.createCell(1).setCellValue("吃饭");

        // 5、将 Excel 文件写入到指定文件
        FileOutputStream fos = new FileOutputStream("/Users/chen/Desktop/czm.xlsx");
        workbook.write(fos);
        System.out.println("--- 写入成功！");

        // 6、释放资源
        fos.close();
        workbook.close();
    }

    /**
     * 向已存在的 Excel 文件中填充内容。⚠️：此方式适用于先定义好 Excel 文件报表的样式，再写入数据。
     */
    @Test
    public void testWrite2() throws IOException {
        // 1、基于已经存在的 Excel 模版文件 通过 POI 创建工作簿（文件）对象 --- excel 对象
        FileInputStream fis = new FileInputStream("/Users/chen/Desktop/czm.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        // 2、获取已存在 Excel 文件中存在的 sheet
//        XSSFSheet sheet = workbook.createSheet();
        XSSFSheet sheet = workbook.getSheet("sheet0");

        // 3、通过 sheet 获取 row 对象
        // ⚠️⚠️⚠️：如果 Excel 不存在指定索引的行，则 row 返回为 null
        XSSFRow row = sheet.getRow(0);

        // 4、通过 XSSFRow 创建单元格，并填充数据
        row.getCell(0).setCellValue("姓名1");
        row.getCell(1).setCellValue("爱好1");

        XSSFRow row1 = sheet.getRow(1);
        row1.getCell(0).setCellValue("张三");
        row1.getCell(1).setCellValue("打篮球");

        XSSFRow row2 = sheet.getRow(2);
        row2.getCell(0).setCellValue("李四");
        row2.getCell(1).setCellValue("吃饭");

        // 5、将 Excel 文件写入到指定文件
        FileOutputStream fos = new FileOutputStream("/Users/chen/Desktop/czm.xlsx");
        workbook.write(fos);
        System.out.println("--- 写入成功！");

        // 6、释放资源
        fis.close();
        workbook.close();
    }


    // 需求2：通过 POI 读磁盘中指定的 Excel 文件到 Java 内存中，并输出到控制台

    /**
     *
     */
    @Test
    public void testRead() throws IOException {
        // 1、基于已经存在的 Excel 模版文件 通过 POI 创建工作簿（文件）对象 --- excel 对象
        FileInputStream fis = new FileInputStream("/Users/chen/Desktop/czm.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        // 2、获取已存在 Excel 文件中存在的 sheet
        XSSFSheet sheet = workbook.getSheet("sheet0");

//        XSSFRow row = sheet.getRow(0);
//        String name = row.getCell(0).getStringCellValue();
//        String hobby = row.getCell(1).getStringCellValue();
//        System.out.println(name + hobby);

        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();
        for (int i = firstRowNum; i <= lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            String name = row.getCell(0).getStringCellValue();
            String hobby = row.getCell(1).getStringCellValue();
            System.out.println(name + " --- " + hobby);
        }

        // 6、释放资源
        fis.close();
        workbook.close();
    }
}
