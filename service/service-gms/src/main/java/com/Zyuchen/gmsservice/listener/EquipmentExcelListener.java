package com.Zyuchen.gmsservice.listener;

import com.Zyuchen.common.Exception.DefinedException;
import com.Zyuchen.gmsservice.entity.Equipment;
import com.Zyuchen.gmsservice.entity.Equipmentrecord;
import com.Zyuchen.gmsservice.entity.excel.EquipmentData;
import com.Zyuchen.gmsservice.entity.vo.EquipmentrecordVO;
import com.Zyuchen.gmsservice.service.EquipmentService;
import com.Zyuchen.gmsservice.service.EquipmentrecordService;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;

public class EquipmentExcelListener extends AnalysisEventListener<EquipmentData> {

    public EquipmentService equipmentService;
    public EquipmentrecordService equipmentrecordService;
    public List<Equipment> list;

    public EquipmentExcelListener() { }

    public EquipmentExcelListener(EquipmentService equipmentService, EquipmentrecordService equipmentrecordService,  List<Equipment> list) {
        this.equipmentService = equipmentService;
        this.equipmentrecordService = equipmentrecordService;
        this.list = list;
    }

    @Override
    public void invoke(EquipmentData equipmentData, AnalysisContext analysisContext) {
        if(equipmentData == null){
            throw new DefinedException(20001, "文件数据为空");
        }else{
            Equipment excistEquipment = this.excistOneEquipment(equipmentService, equipmentData.toEquipment());
            if(excistEquipment == null){
                excistEquipment = equipmentData.toEquipment();
                excistEquipment.setEquipmentLeftNumber(excistEquipment.getEquipmentTotalNumber());
                equipmentService.save(excistEquipment);
            }else{
                Integer total = equipmentData.toEquipment().getEquipmentTotalNumber();
                excistEquipment.setEquipmentTotalNumber(excistEquipment.getEquipmentTotalNumber()+total);
                excistEquipment.setEquipmentLeftNumber(excistEquipment.getEquipmentLeftNumber()+total);
                equipmentService.updateById(excistEquipment);
            }
            Equipmentrecord er = new Equipmentrecord();
            er.setEquipmentId(excistEquipment.getEquipmentId());
            er.setOperator("管理员");
            er.setOperatorType(0);
            er.setRecordType(1);
            er.setEquipmentNumber(equipmentData.toEquipment().getEquipmentTotalNumber());
            equipmentrecordService.save(er);
            list.add(equipmentData.toEquipment());
        }
    }

    public Equipment excistOneEquipment(EquipmentService equipmentService, Equipment equipment){
        QueryWrapper<Equipment> wrapper = new QueryWrapper<>();
        wrapper.eq("equipmentName", equipment.getEquipmentName());
        return equipmentService.getOne(wrapper);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
