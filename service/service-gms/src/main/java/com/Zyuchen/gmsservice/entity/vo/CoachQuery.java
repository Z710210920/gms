package com.Zyuchen.gmsservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CoachQuery implements Serializable {
    @ApiModelProperty(value="教练名称，模糊查询")
    private String coachName;

    @ApiModelProperty(value="教练真实名称，模糊查询")
    private String coachRealName;

    private String coachPhoneNumber;

    @ApiModelProperty(value="头衔， 1普通教练 2中级教练 3高级教练")
    private Integer level;
}
