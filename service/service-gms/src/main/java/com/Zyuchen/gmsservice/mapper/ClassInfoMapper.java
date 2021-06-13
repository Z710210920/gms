package com.Zyuchen.gmsservice.mapper;

import com.Zyuchen.gmsservice.entity.ClassInfo;
import com.Zyuchen.gmsservice.entity.vo.ClassInfoVO;
import com.Zyuchen.gmsservice.entity.vo.ClassPublishVo;
import com.Zyuchen.gmsservice.entity.vo.UserVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    IPage<ClassInfoVO> selectClassInfoVoPage(Page<ClassInfoVO> pageParam, @Param(Constants.WRAPPER) QueryWrapper<ClassInfo> queryWrapper);

    IPage<ClassInfoVO> selectedClassInfoVoPage(Page<ClassInfoVO> pageParam, @Param(Constants.WRAPPER) QueryWrapper<ClassInfo> queryWrapper);
}
