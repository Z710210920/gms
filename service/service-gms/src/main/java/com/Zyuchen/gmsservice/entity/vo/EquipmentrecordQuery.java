package com.Zyuchen.gmsservice.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EquipmentrecordQuery {

    @TableField("equipmentId")
    private String equipmentId;

    @ApiModelProperty(value = "1，入库 2，出库 3，借用 4，归还")
    @TableField("recordType")
    private Integer recordType;

    private String operator;

    @ApiModelProperty(value = "0，管理员\n1，教练\n2，用户")
    @TableField("operatorType")
    private Integer operatorType;

    private String begin;

    private String end;

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
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

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
