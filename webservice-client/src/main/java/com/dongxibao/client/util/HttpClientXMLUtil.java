package com.dongxibao.client.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * 发送webservice接口报文
 * @author Dongxibao
 * @date 2020-05-30
 */
@Slf4j
public class HttpClientXMLUtil {

    public static InputStream send(String reXML,String username,String password,String url,String headVeaule) throws IOException {
        int timeout = 600000;
        log.info("[HttpClientXMLUtil] 发送SOAP请求");
        HttpClient client = new HttpClient();
        // 如果需要用户名密码验证；不需要验证登录则不需要以下2行
        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
            UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials(username, password);
            client.getState().setCredentials(AuthScope.ANY, usernamePasswordCredentials);
        }
        GetMethod getMethod = new GetMethod(url);
        PostMethod postMethod = new PostMethod(url);
        // 设置连接超时
        client.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
        // 设置读取时间超时
        client.getHttpConnectionManager().getParams().setSoTimeout(timeout);
        // 然后把Soap请求数据添加到PostMethod中
        RequestEntity requestEntity = new StringRequestEntity(reXML,"text/xml", "UTF-8");
        // 设置请求头部，否则可能会报 “no SOAPAction header” 的错误
        postMethod.setRequestHeader("SOAPAction", headVeaule);
        getMethod.setRequestHeader("SOAPAction", headVeaule);
        // 设置请求体
        postMethod.setRequestEntity(requestEntity);
        log.info("[HttpClientXMLUtil]请求报文：{}", reXML);
        int status = client.executeMethod(postMethod);
        log.info("[HttpClientXMLUtil]返回报文：{}", postMethod.getResponseBodyAsString());
        InputStream is = postMethod.getResponseBodyAsStream();
        return is;
    }
}
