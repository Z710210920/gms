package com.Zyuchen.gmsservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value="注册信息封装对象", description="注册")
public class Register implements Serializable {
    private String userName;

    private String userPhoneNumber;

    private String password;

    private String uuid;

    private String code;
}
