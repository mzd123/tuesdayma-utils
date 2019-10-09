package com.china.tuesdayma.test;

import com.china.tuesdayma.security.DesUtils;
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
public class DesDemo {

    @Test
    public void AesTest() throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeySpecException {
        String keyStr = DesUtils.getKeyStr();
        System.out.println("key值为：" + keyStr);

        String string = DesUtils.encrypt("mzd123", keyStr);
        System.out.println("加密结果为：" + string);


        String string1 = DesUtils.decrypt(string, keyStr);
        System.out.println("解密结果为：" + string1);
    }


}
