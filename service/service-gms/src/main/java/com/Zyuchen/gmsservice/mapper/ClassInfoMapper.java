package com.Zyuchen.gmsservice.mapper;

import com.Zyuchen.gmsservice.entity.ClassInfo;
import com.Zyuchen.gmsservice.entity.vo.ClassPublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程表 Mapper 接口
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-22
 */
public interface ClassInfoMapper extends BaseMapper<ClassInfo> {
    ClassPublishVo selectClassPublishVoById(String id);
}