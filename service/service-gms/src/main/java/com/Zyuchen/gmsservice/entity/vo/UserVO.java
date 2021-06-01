package com.Zyuchen.gmsservice.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserVO{

    private String userId;
    private String userName;
    private String userRealName;
    private String userPhoneNumber;
    private String userIdentityNumber;
    private String userPassword;
    private Date createtime;
    private Date modifiedtime;
    private Boolean isDeleted;
    private Integer level;
    private String avatar;
    private String intro;
    private BigDecimal balance;
    private Date deadline;
}
