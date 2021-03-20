package com.Zyuchen.gmsservice.entity.excel;

import com.Zyuchen.gmsservice.entity.Equipment;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class EquipmentData {

    @ExcelProperty(index = 0)
    @TableField("equipmentName")
    private String equipmentName;

    @ExcelProperty(index = 1)
    @TableField("equipmentTotalNumber")
    private String equipmentTotalNumber;

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentTotalNumber() {
        return equipmentTotalNumber;
    }

    public void setEquipmentTotalNumber(String equipmentTotalNumber) {
        this.equipmentTotalNumber = equipmentTotalNumber;
    }

    public Equipment toEquipment() {
        Equipment equipment = new Equipment();
        equipment.setEquipmentName(this.equipmentName);
        equipment.setEquipmentTotalNumber(Integer.parseInt(this.equipmentTotalNumber));

        return equipment;
    }
}
