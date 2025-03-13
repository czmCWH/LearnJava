package com.czm.d2_buffer_stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test4 {
    /*
     1、案例：读取文件内容，排序内容后，输出到指定文件
     2、
     */
    public static void main(String[] args) {
        try (
                // 创建缓冲字符输入流与源文件接通
                BufferedReader br = new BufferedReader(new FileReader("day10-io-code/src/czm03.txt"));
                // 创建缓冲字符输出流与源文件接通
                BufferedWriter bw = new BufferedWriter(new FileWriter("day10-io-code/src/czm03_out.txt"))
        ) {
            // 2、准备一个集合，存储原文每段落
            List<String> list = new ArrayList<>();

            // 3、按照每一行来读取
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            // 4、对每一行排序，Collections.sort 默认根据数组元素的第一个字符生序排序
            Collections.sort(list);

            // 5、把排序好的集合中的每段文章写出到新文件中去，每行都要换行。
            for (String str : list) {
                bw.write(str);
                bw.newLine();
            }
            System.out.println("---写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
