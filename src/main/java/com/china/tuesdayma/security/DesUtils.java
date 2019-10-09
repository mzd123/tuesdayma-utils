package com.china.tuesdayma.security;

import com.china.tuesdayma.common.Parse;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @Author: mzd
 * @Date: 2019/10/9 22:26
 */
public class DesUtils {

    /**
     * 加密
     *
     * @param encryptStr 需要加密的参数
     * @param keyStr     加密的key
     * @return
     */
    public static String encrypt(String encryptStr, String keyStr) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeySpecException {
        String result = "";
        //获取key对象--比aes复杂一点
        DESKeySpec desKeySpec = new DESKeySpec(keyStr.getBytes());
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        Key key = secretKeyFactory.generateSecret(desKeySpec);
        //获取Cipher对象,采用ECB工作模式
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        //初始化cipher，选择加密
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] bytes = cipher.doFinal(encryptStr.getBytes("UTF-8"));
        result = Base64.encodeBase64String(bytes);
        //result = parseByte2HexStr(bytes);
        return result;
    }

    /**
     * 解密
     *
     * @param decryptStr 需要解密的参数
     * @param keyStr     解密的key
     * @return
     */
    public static String decrypt(String decryptStr, String keyStr) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeySpecException {
        String result = "";
        //获取key对象
        DESKeySpec desKeySpec = new DESKeySpec(keyStr.getBytes());
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        Key key = secretKeyFactory.generateSecret(desKeySpec);
        //获取Cipher对象,采用ECB工作模式
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        //初始化cipher，选择解密
        cipher.init(Cipher.DECRYPT_MODE, key);
        // byte[] bytes = cipher.doFinal(parseHexStr2Byte(decryptStr));
        byte[] bytes = cipher.doFinal(Base64.decodeBase64(decryptStr));
        result = new String(bytes);
        return result;
    }

    /**
     * 获取key
     *
     * @return
     */
    public static String getKeyStr() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56);
        SecretKey secretKey = keyGenerator.generateKey();
        return Parse.parseByte2HexStr(secretKey.getEncoded());
    }
}
