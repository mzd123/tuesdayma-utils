package com.china.tuesdayma.test;

import com.china.tuesdayma.file.zip.ZipUtils;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @Author: mzd
 * @Date: 2019/9/30 16:10
 */
public class ZipDemo {

    @Test
    public void doUnzip() {
        ZipUtils.unzip("C:\\Users\\tuesd\\Desktop\\temp\\test.zip", "C:\\Users\\tuesd\\Desktop\\temp", true, new ArrayList<>());
    }
}
