package com.czm;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

/**
 * File 操作工具类
 */
public class Files {
    /**
     * 递归搜索 File 下面的所有文件
     * @param dir 接收目录对象
     * @param operation 搜索到文件后执行的回调
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

    /**
     * 复制文件到新的目标文件（只对文件拷贝）
     * @param src 被复制的文件
     * @param dest 目标文件
     */
    public static void copy(File src, File dest) {
        if (src == null || dest == null) return;
        if (!src.exists() || dest.exists()) return;
        if (src.isDirectory() || dest.isDirectory()) return;
        makeParentDir(dest);

//        FileInputStream fis = null;
//        FileOutputStream fos = null;
//        try {
//            fis = new FileInputStream(src);
//            fos = new FileOutputStream(dest);
//            byte[] buf = new byte[8192];   // 最多一次性读取 8192 个字节
//            int len;  // 实际读取的字节个数
//            while ((len = fis.read(buf)) != -1) {
//                fos.write(buf, 0, len);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (fis != null) fis.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                if (fos != null) fos.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        // 1、使用 try-with-resources 简化，使得 FileInputStream、FileOutputStream 自动 close
//        try (InputStream io = new FileInputStream(src); OutputStream os = new FileOutputStream(dest);) {
//            byte[] buf = new byte[8192];   // 最多一次性读取 8192 个字节
//            int len;  // 实际读取的字节个数
//            while ((len = io.read(buf)) != -1) {
//                os.write(buf, 0, len);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        // 2、使用缓冲输入、输出流优化
        try (InputStream io = new BufferedInputStream(new FileInputStream(src));
             OutputStream os = new BufferedOutputStream(new FileOutputStream(dest));) {
            byte[] bytes = new byte[8192];  // 最多一次性读取 8192 个字节
            int len;    // 实际读取的字节个数
            while ((len = io.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建文件的父目录
     * @param file
     */
    private static void makeParentDir(File file) {
        File parent = file.getParentFile();
        if (parent == null) return;    // 如果父目录存在，则不创建
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

    /**
     * 把内存中数据写入到新文件
     * @param bytes 二进制数据
     * @param file 写入到的文件
     */
    public static void write(byte[] bytes,  File file) {
        if (bytes == null || file == null) return;
        if (file.exists()) return;
        makeParentDir(file);

//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(file);
//            fos.write(bytes);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (fos != null) fos.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        // 1、使用 try-witch-resources 简化，使得 FileOutputStream 自动 close
//        try (FileOutputStream fos = new FileOutputStream(file);) {
//            fos.write(bytes);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // 2、使用缓冲输出流优化
        try (OutputStream os = new BufferedOutputStream(new FileOutputStream(file))) {
            os.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 读取某个文件的二进制数据
     * @param file 被读取的文件
     */
    public static byte[] read(File file) {
        if (file == null || !file.exists()) return null;
        if (file.isDirectory()) return null;
//        FileInputStream fis = null;
//        try {
//            fis = new FileInputStream(file);
//            byte[] bytes = new byte[(int) file.length()];
//            fis.read(bytes);
//            return bytes;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return null;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            try {
//                if (fis != null) fis.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        // 1、使用 try-with-resources 简化，使得 FileInputStream 自动 close
//        try (FileInputStream fis = new FileInputStream(file);) {
//            byte[] bytes = new byte[(int) file.length()];
//            fis.read(bytes);
//            return bytes;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return null;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }

        // 2、使用缓冲输入流优化
        try (InputStream is = new BufferedInputStream(new FileInputStream(file))) {
            byte[] bytes = new byte[(int) file.length()];
            is.read(bytes);
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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

        System.out.println("\n--- 写入数据到新文件，然后读取：");
        write("好好学习Java".getBytes(StandardCharsets.UTF_8), new File("/Users/chen/Desktop/abc/aa/1.txt"));
        byte[] bytes = read(new File("/Users/chen/Desktop/abc/aa/1.txt"));
        if (bytes != null) {
            System.out.println("读取到的文件 = " + new String(bytes, StandardCharsets.UTF_8));
        }

        System.out.println("\n--- copy 文件：");
        copy(new File("/Users/chen/Desktop/abc/aa/1.txt"), new File("/Users/chen/Desktop/aaa/2.txt"));

    }
}
