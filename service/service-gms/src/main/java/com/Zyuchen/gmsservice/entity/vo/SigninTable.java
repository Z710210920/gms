package com.Zyuchen.gmsservice.entity.vo;

import com.Zyuchen.gmsservice.entity.Signin;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class SigninTable{

    private static final long serialVersionUID = 1L;

    private String classselectionId;
    private String avatar;
    private Integer balance;
    private String userPhoneNumber;
    private String username;
    private Integer signIntimes;
    private List<Signin> signIn;
    private Date deadline;



}
