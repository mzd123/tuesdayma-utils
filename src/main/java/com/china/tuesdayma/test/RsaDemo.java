package com.china.tuesdayma.test;

import com.china.tuesdayma.security.RsaUtils;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

/**
 * @Author: mzd
 * @Date: 2019/9/30 16:10
 */
public class RsaDemo {

    @Test
    public void AesTest() throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeySpecException {
        HashMap<String, String> keyStr = RsaUtils.getPublicKeyAndPrivateKey();
        String publicKey = keyStr.get("publicKey");
        String privateKey = keyStr.get("privateKey");
        System.out.println("publicKey：" + publicKey);
        System.out.println("privateKey：" + privateKey);

        String string = RsaUtils.encrypt("mzd123", privateKey);
        System.out.println("加密结果为：" + string);


        String string1 = RsaUtils.decrypt(string, publicKey);
        System.out.println("解密结果为：" + string1);
    }


}
