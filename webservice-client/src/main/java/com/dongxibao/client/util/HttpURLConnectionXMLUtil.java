package com.dongxibao.client.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * 发送webservice接口报文
 * @author Dongxibao
 * @date 2020-05-30
 */
@Slf4j
public class HttpURLConnectionXMLUtil {
    public static InputStream send(String wsdlUrl, String xmlMessage) throws IOException {
        if (StringUtils.isEmpty(wsdlUrl)) {
            return null;
        }
        int timeout = 600000;
        InputStream inputStream = null;
        log.info("[HttpURLConnection]初始化 wsdlUrl：{}", wsdlUrl);
        URL url = new URL(wsdlUrl);
        log.info("[HttpURLConnection]开始设置 http 参数");
        try {
            HttpURLConnection httpURLConnection = ((HttpURLConnection) url.openConnection());
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(timeout);
            httpURLConnection.setReadTimeout(timeout);
            httpURLConnection.addRequestProperty("Content-Length",String.valueOf(xmlMessage.getBytes().length));
            httpURLConnection.addRequestProperty("Content-type","text/xml;charset=UTF-8");
            log.info("[HttpURLConnection]组装的请求报文：{}", new String(xmlMessage.getBytes()));
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.writeBytes(xmlMessage);
            dataOutputStream.flush();
            inputStream = httpURLConnection.getInputStream();
            // 中间存储，防止InputStream只能读取一次
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) > -1 ) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            byteArrayOutputStream.flush();
            InputStream in = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            String result = IOUtils.toString(in, StandardCharsets.UTF_8);
            log.info("[HttpURLConnection]返回报文 ：{}", result);
            // 返回原始的inputStream
            inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            log.error("[HttpURLConnection]接口调用失败！");
            log.error("[HttpURLConnection]错误提示：{};  {}",e,e.getMessage());
        }
        return inputStream;
    }

    public static void main(String [] args) throws IOException{
        System.out.println(new BufferedReader(new InputStreamReader(send("http://www.baidu.com","")))
                .lines().parallel().collect(Collectors.joining(System.lineSeparator())));
    }
}
