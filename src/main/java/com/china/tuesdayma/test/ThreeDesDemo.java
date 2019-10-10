package com.china.tuesdayma.test;

import com.china.tuesdayma.security.DesUtils;
import com.china.tuesdayma.security.ThreeDesUtils;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @Author: mzd
 * @Date: 2019/9/30 22:10
 */
public class ThreeDesDemo {

    @Test
    public void ThreeTest() throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeySpecException {
        String keyStr = ThreeDesUtils.getKeyStr();
        System.out.println("key值为：" + keyStr);

        String string = ThreeDesUtils.encrypt("mzd123", keyStr);
        System.out.println("加密结果为：" + string);


        String string1 = ThreeDesUtils.decrypt(string, keyStr);
        System.out.println("解密结果为：" + string1);
    }


}
