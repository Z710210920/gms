package com.Zyuchen.gmsservice.service;

import com.Zyuchen.gmsservice.entity.ClassInfo;
import com.Zyuchen.gmsservice.entity.vo.ClassInfoForm;
import com.Zyuchen.gmsservice.entity.vo.ClassInfoQuery;
import com.Zyuchen.gmsservice.entity.vo.ClassPublishVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程表 服务类
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-22
 */
public interface ClassInfoService extends IService<ClassInfo> {

    ClassInfoForm getClassInfoFormById(String id);

    String saveClassInfo(ClassInfoForm classInfoForm);

    ClassPublishVo getClassPublishVoById(String id);

    boolean publishClassById(String id);

    void pageQuery(Page<ClassInfo> pageParam, ClassInfoQuery courseQuery);

    boolean removeClassInfoById(String id);
}
