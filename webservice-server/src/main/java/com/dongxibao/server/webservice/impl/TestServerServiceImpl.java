package com.dongxibao.server.webservice.impl;

import com.dongxibao.server.common.RestResult;
import com.dongxibao.server.entity.UserDTO;
import com.dongxibao.server.mapper.UserMapper;
import com.dongxibao.server.webservice.TestServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * 测试webservice插入用户  服务端
 *
 * @author Dongxibao
 * @date 2020-05-30
 */
@Slf4j
@Component("testServerServiceImpl")
@WebService(serviceName = "TestServerService", targetNamespace = "http://insertuser.server.dongxibao.com/")
public class TestServerServiceImpl implements TestServerService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public RestResult<String> insertUser(UserDTO userDTO) {
        int insert = userMapper.insert(userDTO);
        if (insert > 0) {
            return new RestResult<>("插入成功", true);
        } else {
            return new RestResult<>("插入失败", false);
        }
    }
}
