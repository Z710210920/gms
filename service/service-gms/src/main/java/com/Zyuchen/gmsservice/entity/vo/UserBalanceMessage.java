package com.Zyuchen.gmsservice.entity.vo;

import com.Zyuchen.gmsservice.entity.Signin;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserBalanceMessage {

    private String userId;
    private Integer balance;
    private Date deadline;
    private String userPhoneNumber;
    private String username;
}
