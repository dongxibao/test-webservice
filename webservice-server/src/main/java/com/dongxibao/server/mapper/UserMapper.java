package com.dongxibao.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dongxibao.server.entity.UserDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName UserMapper
 * @description userMapper
 * @author Dongxibao
 * @date 2020/1/4
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDTO> {

}
