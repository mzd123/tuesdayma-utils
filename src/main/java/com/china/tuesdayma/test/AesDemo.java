package com.china.tuesdayma.test;

import com.china.tuesdayma.security.AesUtils;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: mzd
 * @Date: 2019/9/30 16:10
 */
public class AesDemo {

    @Test
    public void AesTest() throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException {
        String keyStr =AesUtils.getKeyStr();
        System.out.println("key值为：" + keyStr);

        String string = AesUtils.encrypt("mzd123", keyStr);
        System.out.println("加密结果为：" + string);


        String string1 = AesUtils.decrypt(string, keyStr);
        System.out.println("解密结果为：" + string1);
    }


}
