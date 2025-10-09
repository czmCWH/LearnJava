package com.czm.d5_AnonymousClass.util;

import java.util.ArrayList;
import java.util.List;

public class Files {

    public interface Filter {
        boolean accept(String fileName);
    }

    public static String[] getAllFilenames(String dir, Filter filter) {
        // 1、先获取 dir 文件夹下的所有文件名
        String[] allFiles = {};

        // 2、进行过滤
        List<String> list = new ArrayList<String>();
        for (String name : allFiles) {
            if (filter.accept(name)) {
                list.add(name);
            }
        }

        // 3、返回封装起来的文件名
        return list.toArray(new String[list.size()]);
    }
}
