package com.Zyuchen.gmsservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CoachQuery {
    @ApiModelProperty(value="教练名称，模糊查询")
    private String coachNickName;

    @ApiModelProperty(value="头衔， 1普通教练 2中级教练 3高级教练")
    private Integer level;

    @ApiModelProperty(value = "教练类型， 1 2 3")
    private Integer type;

    public String getCoachNickName() {
        return coachNickName;
    }

    public void setCoachNickName(String coachNickName) {
        this.coachNickName = coachNickName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
