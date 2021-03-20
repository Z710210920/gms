package com.Zyuchen.gmsservice.entity.vo;

import com.Zyuchen.gmsservice.entity.Equipmentrecord;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class EquipmentrecordVO{

    public EquipmentrecordVO(){}

    public EquipmentrecordVO(Equipmentrecord equipmentrecord){
        this.createtime = equipmentrecord.getCreatetime();
        this.equipmentId = equipmentrecord.getEquipmentId();
        this.equipmentRecordId = equipmentrecord.getEquipmentRecordId();
        this.equipmentNumber = equipmentrecord.getEquipmentNumber();
        this.recordType = equipmentrecord.getRecordType();
        this.operator = equipmentrecord.getOperator();
        this.operatorType = equipmentrecord.getOperatorType();
        this.modifiedtime = equipmentrecord.getModifiedtime();
        this.isdeleted = equipmentrecord.getIsdeleted();
        this.isReturn = equipmentrecord.getIsReturn();
    }

    private static final long serialVersionUID = 1L;

    private String equipmentName;

    private String operatorName;

    @TableId(value = "equipmentRecordId", type = IdType.ID_WORKER_STR)
    private String equipmentRecordId;

    @TableField("equipmentId")
    private String equipmentId;

    @TableField("equipmentNumbe")
    private Integer equipmentNumber;

    @ApiModelProperty(value = "1，入库 2，出库 3，借用 4，归还")
    @TableField("recordType")
    private Integer recordType;

    private String operator;

    @ApiModelProperty(value = "0，管理员\n1，教练\n2，用户")
    @TableField("operatorType")
    private Integer operatorType;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createtime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private Date modifiedtime;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer isdeleted;

    @ApiModelProperty(value = "归还")
    private Integer isReturn;

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentRecordId() {
        return equipmentRecordId;
    }

    public void setEquipmentRecordId(String equipmentRecordId) {
        this.equipmentRecordId = equipmentRecordId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Integer getEquipmentNumbe() {
        return equipmentNumber;
    }

    public void setEquipmentNumbe(Integer equipmentNumbe) {
        this.equipmentNumber = equipmentNumbe;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
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

    public Integer getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Integer isdeleted) {
        this.isdeleted = isdeleted;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Integer getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(Integer equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public Integer getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(Integer isReturn) {
        this.isReturn = isReturn;
    }
}
