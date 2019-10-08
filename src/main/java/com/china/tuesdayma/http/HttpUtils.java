package com.china.tuesdayma.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @Author: mzd
 * @Date: 2019/10/8 19:51
 */
@Slf4j
public class HttpUtils {
    /**
     * 发起post请求
     *
     * @param reqRemark     请求描述
     * @param url           链接
     * @param json          请求体json
     * @param orgCode       银联智慧医疗平台code
     * @param authorization 签名
     * @return
     */
    public static String post(String reqRemark, String url, String json, String orgCode, String authorization) {
        String returnValue = "";
        log.info(reqRemark + "请求url:" + url + ",请求体:" + json + ",orgCode:" + orgCode + ",authorization:" + authorization);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try {
            httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            StringEntity requestEntity = new StringEntity(json, Charset.forName("UTF-8"));
            httpPost.setHeader("Content-type", "application/json;charset=utf-8");
            httpPost.setHeader("Accept-type", "application/json,text/plain,*/*");
            httpPost.setHeader("OrgCode", orgCode);
            httpPost.setHeader("Authorization", authorization);
            httpPost.setEntity(requestEntity);
            returnValue = httpClient.execute(httpPost, responseHandler);
            log.info(reqRemark + "返回结果:" + returnValue);
        } catch (Exception e) {
            log.error("", e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                log.error("", e);
            }
        }
        return returnValue;
    }

}
