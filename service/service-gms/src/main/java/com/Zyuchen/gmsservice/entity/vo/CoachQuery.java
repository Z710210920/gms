package com.Zyuchen.gmsservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CoachQuery {
    @ApiModelProperty(value="教练名称，模糊查询")
    private String coachName;

    @ApiModelProperty(value="教练真实名称，模糊查询")
    private String coachRealName;

    private String coachPhoneNumber;

    @ApiModelProperty(value="头衔， 1普通教练 2中级教练 3高级教练")
    private Integer level;

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachNickName) {
        this.coachName = coachName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCoachRealName() {
        return coachRealName;
    }

    public void setCoachRealName(String coachRealName) {
        this.coachRealName = coachRealName;
    }

    public String getCoachPhoneNumber() {
        return coachPhoneNumber;
    }

    public void setCoachPhoneNumber(String coachPhoneNumber) {
        this.coachPhoneNumber = coachPhoneNumber;
    }
}
