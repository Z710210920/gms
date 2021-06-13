package com.Zyuchen.gmsservice.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class AccountBookVO implements Serializable {

    private String id;
    private String userId;
    private String userName;
    private String coachId;
    private String coachName;
    private Integer type;
    private BigDecimal amount;
    private String note;
    private Date createtime;
    private Date modifiedtime;
    private Integer isdeleted;
}
