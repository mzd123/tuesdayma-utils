package com.china.tuesdayma.http;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: mzd
 * @Date: 2019/10/8 19:51
 */
@Slf4j
public class HttpUtils {
    /**
     * 发起post请求
     *
     * @param reqRemark 请求描述
     * @param url       链接
     * @param json      请求体json
     * @param header    头部
     * @return
     */
    public static String post(String reqRemark, String url, String json, HashMap<String, String> header) {
        String returnValue = "";
        log.info("【" + reqRemark + "】    请求url:" + url + "   body:" + json + "   header:" + JSON.toJSONString(header));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try {
            httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            StringEntity requestEntity = new StringEntity(json, Charset.forName("UTF-8"));
            httpPost.setHeader("Content-type", "application/json;charset=utf-8");
            httpPost.setHeader("Accept-type", "application/json,text/plain,*/*");
            if (header != null && !header.isEmpty()) {
                for (Map.Entry<String, String> e : header.entrySet()) {
                    httpPost.setHeader(e.getKey(), e.getValue());
                }
            }
            httpPost.setEntity(requestEntity);
            returnValue = httpClient.execute(httpPost, responseHandler);
            log.info("【" + reqRemark + "】   返回结果:" + returnValue);
        } catch (Exception e) {
            log.error(returnValue + "error:", e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                log.error(returnValue + "error:", e);
            }
        }
        return returnValue;
    }


    public static String get(String reqRemark, String url, HashMap<String, String> body, HashMap<String, String> header) {
        String returnValue = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try {
            httpClient = HttpClients.createDefault();
            if (body != null && !body.isEmpty()) {
                url += "?";
                for (Map.Entry<String, String> e : body.entrySet()) {
                    url += e.getKey() + "=" + e.getValue() + "&";
                }
                url = url.substring(0, url.length() - 1);
            }
            log.info("【" + reqRemark + "】   请求url:" + url + "   header:" + JSON.toJSONString(header));
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Content-type", "application/json;charset=utf-8");
            httpGet.setHeader("Accept-type", "application/json,text/plain,*/*");
            if (header != null && !header.isEmpty()) {
                for (Map.Entry<String, String> e : header.entrySet()) {
                    httpGet.setHeader(e.getKey(), e.getValue());
                }
            }
            returnValue = httpClient.execute(httpGet, responseHandler);
            log.info("【" + reqRemark + "】   返回结果:" + returnValue);
        } catch (Exception e) {
            log.error(returnValue + "error:", e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                log.error(returnValue + "error:", e);
            }
        }
        return returnValue;
    }

}
