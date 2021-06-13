package com.Zyuchen.gmsservice.entity.vo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class AccountBookQuery implements Serializable {

    private String id;
    private String userId;
    private Integer type;
    private BigDecimal amount;
    private String note;
    private Date createtime;
    private Date modifiedtime;
    private Integer isdeleted;
    private String coachId;
}
