package com.china.tuesdayma.file.zip;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @Author: mzd
 * @Date: 2019/9/30 15:51
 */
@Slf4j
public class ZipUtils {
    /**
     * 解压
     * 支持递归解压，即：压缩包里面有压缩包
     *
     * @param zipFilePath 压缩包所在的路径
     * @param outPutPath  解压到哪里
     * @param ifdelete    是否删除压缩包
     * @param list        存储删除的路径
     */
    public static void unZip(String zipFilePath, String outPutPath, boolean ifdelete, List<String> list) {
        try {
            if (!list.contains(zipFilePath)) {
                list.add(zipFilePath);
            }
            ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFilePath));
            BufferedInputStream bin = new BufferedInputStream(zin);
            BufferedOutputStream bout = null;
            File file = null;
            ZipEntry entry;
            try {
                while ((entry = zin.getNextEntry()) != null && !entry.isDirectory()) {
                    file = new File(outPutPath, entry.getName());
                    if (!file.exists()) {
                        (new File(file.getParent())).mkdirs();
                    }
                    bout = new BufferedOutputStream(new FileOutputStream(file));
                    int b;
                    while ((b = bin.read()) != -1) {
                        bout.write(b);
                    }
                    bout.flush();
                    bout.close();
                    log.info(file + "解压成功");
                    ZipInputStream zin1 = new ZipInputStream(new FileInputStream(file.getAbsolutePath()));
                    if ((entry = zin1.getNextEntry()) != null && !entry.isDirectory()) {
                        String path = outPutPath + "/" + file.getName().substring(0, file.getName().lastIndexOf("."));
                        File file1 = new File(path);
                        file1.mkdir();
                        list.add(file.getAbsolutePath());
                        unZip(file.getAbsolutePath(), path, true, list);
                        zin1.closeEntry();
                    }
                    zin1.close();
                }
                zin.closeEntry();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bin.close();
                    zin.close();
                    if (bout != null) {
                        bout.close();
                    }
                    if (ifdelete) {
                        for (String str : list) {
                            log.info("删除压缩包：" + file + ",结果为：" + new File(str).delete());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return;
    }

    /**
     * 压缩
     *
     * @param filePath    需要压缩的文件路径
     * @param zipPathName 压缩之后的名字
     * @throws IOException
     */
    public static void doZip(String filePath, String zipPathName) {
        ZipOutputStream zipOutputStream = null;
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(new File(zipPathName)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File file = new File(filePath);
        if (!file.exists()) {
            log.error("file is not exists...");
        } else {
            try {
                doZipRecurrence(zipOutputStream, file.getName(), file);
                log.info("压缩成功");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (zipOutputStream != null) {
                        zipOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void doZipRecurrence(ZipOutputStream zipOutputStream, String parentPath, File file) throws IOException {
        File files[] = file.listFiles();
        if (files.length == 0) {
            //如果文件夹是空的
            zipOutputStream.putNextEntry(new ZipEntry(parentPath + "/"));
            zipOutputStream.closeEntry();
        } else {
            for (File f : files) {
                if (f.isDirectory()) {
                    //如果是文件夹--递归
                    doZipRecurrence(zipOutputStream, parentPath + "/" + f.getName(), f);
                } else {
                    //如果不是文件夹，则直接输出
                    doZipOutputStream(parentPath + "/" + f.getName(), f, zipOutputStream);
                }
            }
        }
    }


    private static void doZipOutputStream(String filePath, File f, ZipOutputStream zipOutputStream) throws IOException {
        byte[] buf = new byte[1024];
        zipOutputStream.putNextEntry(new ZipEntry(filePath));
        int len;
        FileInputStream in = new FileInputStream(f);
        while ((len = in.read(buf)) != -1) {
            zipOutputStream.write(buf, 0, len);
        }
        zipOutputStream.closeEntry();
        in.close();
    }


}
