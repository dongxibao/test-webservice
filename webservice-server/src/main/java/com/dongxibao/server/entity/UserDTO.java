package com.dongxibao.server.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @ClassName User
 * @description
 * @author Dongxibao
 * @date 2020/1/3
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
@TableName(value = "user", autoResultMap = true)
public class UserDTO {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @TableLogic
    private Integer delFlag;
    @TableField(exist = false)
    private Integer count;
    @Version
    private Integer version;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
