package com.Zyuchen.gmsservice.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 教练信息表
 * </p>
 *
 * @author Zyuchen
 * @since 2021-02-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Coach对象", description="教练信息表")
public class Coach implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "coachId", type = IdType.ID_WORKER_STR)
    private String coachId;

    @TableField("coachNickName")
    private String coachNickName;

    @TableField("coachRealName")
    private String coachRealName;

    @TableField("coachPhoneNumber")
    private String coachPhoneNumber;

    @TableField("coachIdentityNumber")
    private String coachIdentityNumber;

    @TableField("coachBalance")
    private Integer coachBalance;

    @TableField("coachPrivilege")
    private Integer coachPrivilege;

    @TableField("coachPassword")
    private String coachPassword;

    @TableField("coachPaymentCode")
    private Integer coachPaymentCode;

    @TableField("coachType")
    private Boolean coachType;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createtime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private Date modifiedtime;

    @ApiModelProperty(value = "逻辑删除")
    @TableField("isDeleted")
    private Boolean isDeleted;

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public String getCoachNickName() {
        return coachNickName;
    }

    public void setCoachNickName(String coachNickName) {
        this.coachNickName = coachNickName;
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

    public String getCoachIdentityNumber() {
        return coachIdentityNumber;
    }

    public void setCoachIdentityNumber(String coachIdentityNumber) {
        this.coachIdentityNumber = coachIdentityNumber;
    }

    public Integer getCoachBalance() {
        return coachBalance;
    }

    public void setCoachBalance(Integer coachBalance) {
        this.coachBalance = coachBalance;
    }

    public Integer getCoachPrivilege() {
        return coachPrivilege;
    }

    public void setCoachPrivilege(Integer coachPrivilege) {
        this.coachPrivilege = coachPrivilege;
    }

    public String getCoachPassword() {
        return coachPassword;
    }

    public void setCoachPassword(String coachPassword) {
        this.coachPassword = coachPassword;
    }

    public Integer getCoachPaymentCode() {
        return coachPaymentCode;
    }

    public void setCoachPaymentCode(Integer coachPaymentCode) {
        this.coachPaymentCode = coachPaymentCode;
    }

    public Boolean getCoachType() {
        return coachType;
    }

    public void setCoachType(Boolean coachType) {
        this.coachType = coachType;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getModifiedtime() {
        return modifiedtime;
    }

    public void setModifiedtime(Date modifiedtime) {
        this.modifiedtime = modifiedtime;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
