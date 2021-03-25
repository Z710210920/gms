package com.Zyuchen.gmsservice.service;

import com.Zyuchen.gmsservice.entity.ClassInfo;
import com.Zyuchen.gmsservice.entity.vo.ClassInfoForm;
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

    String saveClassInfo(ClassInfoForm classInfoForm);
}
