package com.Zyuchen.gmsservice.service;

import com.Zyuchen.gmsservice.entity.Equipment;
import com.Zyuchen.gmsservice.entity.Equipmentrecord;
import com.Zyuchen.gmsservice.entity.vo.EquipmentrecordVO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 设备登记表 服务类
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-15
 */
public interface EquipmentService extends IService<Equipment> {

    void addEquipmentByExcel(MultipartFile file, EquipmentService equipmentService, EquipmentrecordService equipmentrecordService, List<Equipment> list);
}
