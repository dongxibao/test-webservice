package com.dongxibao.client.test;

import com.dongxibao.client.common.GlobalInterface;
import com.dongxibao.client.util.HttpClientXMLUtil;
import com.dongxibao.client.util.HttpURLConnectionXMLUtil;
import com.dongxibao.client.webservice.RestResult;
import com.dongxibao.client.webservice.TestServerService;
import com.dongxibao.client.webservice.TestServerService_Service;
import com.dongxibao.client.webservice.UserDTO;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName TestInsertUser
 * @description 1
 * @author Dongxibao
 * @date 2020/5/30
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestInsertUser {

    @Test
    public void testInsertUser0() {
        JaxWsProxyFactoryBean factoryBeanDemand = new JaxWsProxyFactoryBean();
        factoryBeanDemand.setAddress(GlobalInterface.INSERT_USER);
        factoryBeanDemand.setServiceClass(TestServerService.class);
        TestServerService testServerService = (TestServerService)factoryBeanDemand.create();
        UserDTO userDTO = new UserDTO();
        userDTO.setAge(33);
        userDTO.setName("test0");
        RestResult restResult = testServerService.insertUser(userDTO);
        System.out.println(restResult.toString());
    }
    @Test
    public void testInsertUser() {
        TestServerService_Service testServerService_service = new TestServerService_Service();
        TestServerService testServerServiceImplPort = testServerService_service.getTestServerServiceImplPort();
        
        // 需要账号密码
        TestServerService testServerServiceImplPort = testServerService_service.getTestServerServiceImplPort();
        BindingProvider provider = (BindingProvider) testServerServiceImplPort;
        Map<String, Object> context = provider.getRequestContext();
        context.put(BindingProvider.USERNAME_PROPERTY, "username");
        context.put(BindingProvider.PASSWORD_PROPERTY, "password");
        
        UserDTO userDTO = new UserDTO();
        userDTO.setAge(22);
        userDTO.setName("test");
        RestResult restResult = testServerServiceImplPort.insertUser(userDTO);
        System.out.println(restResult.toString());
    }
    @Test
    public void testInsertUser2() {
        // 拼装报文
        String strXml = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <ns2:insertUser xmlns:ns2=\"http://insertuser.server.dongxibao.com/\">\n" +
                "      <ns2:userDTO>\n" +
                "        <age>22</age>\n" +
                "        <name>test</name>\n" +
                "      </ns2:userDTO>\n" +
                "    </ns2:insertUser>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";
        try {
            InputStream in = HttpURLConnectionXMLUtil.send(GlobalInterface.INSERT_USER,strXml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testInsertUser3() {
        // 拼装报文
        String strXml = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <ns2:insertUser xmlns:ns2=\"http://insertuser.server.dongxibao.com/\">\n" +
                "      <ns2:userDTO>\n" +
                "        <age>22</age>\n" +
                "        <name>test</name>\n" +
                "      </ns2:userDTO>\n" +
                "    </ns2:insertUser>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";
        try {
            InputStream in = HttpClientXMLUtil.send(strXml,null,null,GlobalInterface.INSERT_USER,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
