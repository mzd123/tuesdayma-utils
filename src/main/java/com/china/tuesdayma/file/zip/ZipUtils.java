package com.china.tuesdayma.file.zip;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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
     * @param zipFilePath     压缩包所在的路径
     * @param outPutDirectory 解压到哪里
     * @param ifdelete        是否删除压缩包
     * @param list            存储删除的路径
     */
    public static void unzip(String zipFilePath, String outPutDirectory, boolean ifdelete, List<String> list) {
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
                    file = new File(outPutDirectory, entry.getName());
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
                        String path = outPutDirectory + "/" + file.getName().substring(0, file.getName().lastIndexOf("."));
                        File file1 = new File(path);
                        file1.mkdir();
                        list.add(file.getAbsolutePath());
                        unzip(file.getAbsolutePath(), path, true, list);
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
}
