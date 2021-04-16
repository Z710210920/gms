package com.Zyuchen.gmsservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="登录信息封装对象", description="登录")
public class LoginForm implements Serializable {
    String username;

    String password;

    String valite;
}
