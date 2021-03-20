package com.Zyuchen.gmsservice.service.impl;

import com.Zyuchen.gmsservice.entity.Equipment;
import com.Zyuchen.gmsservice.entity.Equipmentrecord;
import com.Zyuchen.gmsservice.entity.excel.EquipmentData;
import com.Zyuchen.gmsservice.entity.vo.EquipmentrecordVO;
import com.Zyuchen.gmsservice.listener.EquipmentExcelListener;
import com.Zyuchen.gmsservice.mapper.EquipmentMapper;
import com.Zyuchen.gmsservice.service.EquipmentService;
import com.Zyuchen.gmsservice.service.EquipmentrecordService;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 设备登记表 服务实现类
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-15
 */
@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements EquipmentService {

    @Override
    public void addEquipmentByExcel(MultipartFile file, EquipmentService equipmentService, EquipmentrecordService equipmentrecordService, List<Equipment> list) {
        try{
            InputStream in = file.getInputStream();
            EasyExcel.read(in, EquipmentData.class, new EquipmentExcelListener(equipmentService, equipmentrecordService, list)).sheet().doRead();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
