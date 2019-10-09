package com.china.tuesdayma.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: mzd
 * @Date: 2019/10/9 17:26
 */
@Slf4j
public class AesUtils {
    /**
     * 加密
     *
     * @param encryptStr 需要加密的参数
     * @param keyStr     加密的key
     * @return
     */
    public static String encrypt(String encryptStr, String keyStr) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        String result = "";
        //获取key对象
        Key key = new SecretKeySpec(keyStr.getBytes(), "UTF-8");
        //获取Cipher对象,采用ECB工作模式
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        //初始化cipher，选择加密
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] bytes = cipher.doFinal(encryptStr.getBytes("UTF-8"));
        result = Base64.encodeBase64String(bytes);
        return result;
    }

    /**
     * 解密
     *
     * @param decryptStr 需要解密的参数
     * @param keyStr     解密的key
     * @return
     */
    public static String decrypt(String decryptStr, String keyStr) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        String result = "";
        //获取key对象
        Key key = new SecretKeySpec(keyStr.getBytes(), "UTF-8");
        //获取Cipher对象,采用ECB工作模式
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        //初始化cipher，选择解密
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] bytes = cipher.doFinal(decryptStr.getBytes("UTF-8"));
        result = new String(bytes);
        return result;
    }

    /**
     * 获取key
     *
     * @return
     */
    public static String getKeyStr() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        return new String(secretKey.getEncoded(), "UTF-8");
    }
}
