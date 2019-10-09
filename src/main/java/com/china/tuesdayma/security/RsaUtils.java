package com.china.tuesdayma.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;

/**
 * @Author: mzd
 * @Date: 2019/10/9 17:26
 */
public class RsaUtils {
    /**
     * 私钥加密
     *
     * @param encryptStr    需要加密的参数
     * @param privateKeyStr 私钥
     * @return
     */
    public static String encrypt(String encryptStr, String privateKeyStr) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeySpecException, InvalidKeyException {
        String result = "";
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyStr));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        //初始化cipher，选择加密
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(encryptStr.getBytes("UTF-8"));
        result = Base64.encodeBase64String(bytes);
        //result = parseByte2HexStr(bytes);
        return result;
    }

    /**
     * 公钥解密
     *
     * @param decryptStr   需要解密的参数
     * @param publicKeyStr 公钥
     * @return
     */
    public static String decrypt(String decryptStr, String publicKeyStr) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeySpecException {
        String result = "";
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyStr));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        //初始化cipher，选择解密
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        // byte[] bytes = cipher.doFinal(parseHexStr2Byte(decryptStr));
        byte[] bytes = cipher.doFinal(Base64.decodeBase64(decryptStr));
        result = new String(bytes);
        return result;
    }

    /**
     * 获取公私钥
     *
     * @return
     */
    public static HashMap<String, String> getPublicKeyAndPrivateKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        HashMap hashMap = new HashMap();
        hashMap.put("privateKey", Base64.encodeBase64String(privateKey.getEncoded()));
        hashMap.put("publicKey", Base64.encodeBase64String(publicKey.getEncoded()));
        return hashMap;
    }


}
