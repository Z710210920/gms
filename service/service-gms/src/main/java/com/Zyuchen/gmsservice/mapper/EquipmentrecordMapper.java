package com.Zyuchen.gmsservice.mapper;

import com.Zyuchen.gmsservice.entity.Equipmentrecord;
import com.Zyuchen.gmsservice.entity.vo.EquipmentrecordVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 器材借用记录 Mapper 接口
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-15
 */
public interface EquipmentrecordMapper extends BaseMapper<Equipmentrecord> {
    /*@Select("SELECT equipmentRecordId,equipmentrecord.equipmentId equipmentId,equipmentName, equipmentNumber,recordType,operator,operatorType,equipmentrecord.createtime createtime,equipmentrecord.modifiedtime createtime,equipmentrecord.isDeleted createtime\n" +
            "    FROM equipmentrecord, equipment \n" +
            "    where equipment.equipmentId = equipmentrecord.equipmentId;")
    public EquipmentrecordVO ListAll(){};*/


}
