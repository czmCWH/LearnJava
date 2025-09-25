package com.czm.d1_File;

import java.io.File;
import java.util.function.Consumer;

/**
 * File 操作工具类
 */
public class Files {
    /**
     * 递归搜索 File 下面的所有文件
     * @param dir 接收目录对象
     * @param consumer 搜索到文件后执行的回调
     */
    public static void search(File dir, Consumer<File> operation) {
        if (dir == null || operation == null) return;
        if (!dir.exists() || dir.isFile()) return;

        File[] files = dir.listFiles();
        for (File fl : files) {
            operation.accept(fl);
            if (fl.isFile()) {
                continue;
            }
            search(fl, operation);
        }

    }

    /**
     * 剪切、移动文件，不做覆盖操作
     * @param src 被剪切文件
     * @param dest 剪切到的文件
     * @return true 操作成功；false 操作失败
     */
    public static boolean move(File src, File dest) {
        if (src == null || dest == null) return false;
        if (!src.exists() || dest.exists()) return false;     // 如果 src 不存在，或者 dest 存在则不操作
        makeParentDir(dest);
        return src.renameTo(dest);
    }

    private static void makeParentDir(File dest) {
        File parent = dest.getParentFile();
        if (parent.exists()) return;    // 如果父目录存在，则不创建
        parent.mkdirs();    // 创建当前目录
    }


    /**
     * 删除指定文件
     * @param file 被删除文件
     */
    public static void delete(File file) {
        if (file == null || !file.exists()) return;
        if (file.isFile()) {
            file.delete();
            return;
        }
        cleanContent(file);
        file.delete();
    }

    /**
     * 清空文件夹下的所有内容
     * @param file
     */
    public static void cleanContent(File file) {
        if (file == null || !file.exists()) return;
        if (file.isFile()) return;
        File[] files = file.listFiles();
        for (File fl : files) {
            delete(fl);
        }
    }


    // 测试
    public static void main(String[] args) {
        search(new File("/Users/chen/Desktop/abc"), (file) -> {
            System.out.println(file.getPath());
        });

        boolean res = move(new File("/Users/chen/Desktop/abc/a/c/1.jpg"), new File("/Users/chen/Desktop/abc/WeChat903b76a65e2c712466894f0da9e53203" +
                ".jpg"));
        System.out.println("--- 剪切结果：" + res);

        cleanContent(new File("/Users/chen/Desktop/abc"));
    }
}
