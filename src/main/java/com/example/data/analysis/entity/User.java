package com.example.data.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.data.analysis.utils.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "用户类",description="用户表")
@TableName("User")
public class User extends BaseEntity<User> implements Serializable {

    private String name;

    private String loginName;

    private String password;

    private String phone;

    private String deptId;


}
