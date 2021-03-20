package com.Zyuchen.gmsservice.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class EquipmentQuery {

    @TableField("equipmentName")
    private String equipmentName;

    private boolean equipmentLeftNumber;

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public boolean getEquipmentLeftNumber() {
        return equipmentLeftNumber;
    }

    public void setEquipmentLeftNumber(boolean equipmentLeftNumber) {
        this.equipmentLeftNumber = equipmentLeftNumber;
    }
}
