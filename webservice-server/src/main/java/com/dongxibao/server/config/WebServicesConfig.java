package com.dongxibao.server.config;

import com.dongxibao.server.webservice.TestServerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * WebServices配置
 *
 * @author Dongxibao
 * @date 2020-05-30
 */
@Slf4j
@Configuration("webServicesConfig")
public class WebServicesConfig {

    @Autowired
    private Bus bus;
    @Autowired
    private TestServerService testServerService;

    /**
     * 拦截指定路径
     * @return
     */
    @Bean
    public ServletRegistrationBean disServletDemandService() {
        return new ServletRegistrationBean(new CXFServlet(), "/webservice/*");
    }

    /**
     * 报文拦截打印
     * @return
     */
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        SpringBus springBus = new SpringBus();
        LoggingFeature logFeature = new LoggingFeature();
        logFeature.setPrettyLogging(true);
        logFeature.initialize(springBus);
        springBus.getFeatures().add(logFeature);
        return springBus;
    }

    /**
     * 发布服务
     * 游览器访问：http://localhost:8081/webservice/insert-user?wsdl
     *
     * wsdl2java -d E:\apache-cxf-3.3.4\genCXFcode -encoding utf-8 -p com.dongxibao.client.webservice http://localhost:8081/webservice/insert-user?wsdl
     * wsimport -s E:\genCXFcode -p com.dongxibao.client.webservice http://localhost:8081/webservice/insert-user?wsdl
     * @return
     */
    @Bean
    public Endpoint endpointInsertUser() {
        EndpointImpl endpoint = new EndpointImpl(bus, testServerService);
        try {
            endpoint.publish("/insert-user");
        } catch (Exception e) {
            log.error("[测试webservice插入用户]报错：[{}], [{}]", e, e.getMessage());
        }
        return endpoint;
    }
}
