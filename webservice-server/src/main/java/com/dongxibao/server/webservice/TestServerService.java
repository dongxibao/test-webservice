package com.dongxibao.server.webservice;

import com.dongxibao.server.common.RestResult;
import com.dongxibao.server.entity.UserDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 测试webservice插入用户  服务端
 * 
 * @author Dongxibao
 * @date 2020-05-30
 */
@WebService(targetNamespace = "http://insertuser.server.dongxibao.com/")
public interface TestServerService {

	/**
	 * 测试webservice插入用户  服务端
	 * @param userDTO
	 * @return
	 */
	@WebMethod(action="http://insertuser.server.dongxibao.com/user")
	RestResult<String> insertUser(@WebParam(name = "userDTO",
            targetNamespace = "http://insertuser.server.dongxibao.com/") UserDTO userDTO);
}
