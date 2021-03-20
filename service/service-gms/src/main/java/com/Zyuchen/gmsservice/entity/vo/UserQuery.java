package com.Zyuchen.gmsservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserQuery {
    @ApiModelProperty(value="教练名称，模糊查询")
    private String userName;

    private String userRealName;

    private String userPhoneNumber;

    private String userIdentityNumber;

    @ApiModelProperty(value="头衔， 0会员用户,1特约用户,2至尊用户")
    private Integer level;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userNickName) {
        this.userName = userName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserIdentityNumber() {
        return userIdentityNumber;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setUserIdentityNumber(String userIdentityNumber) {
        this.userIdentityNumber = userIdentityNumber;
    }
}
