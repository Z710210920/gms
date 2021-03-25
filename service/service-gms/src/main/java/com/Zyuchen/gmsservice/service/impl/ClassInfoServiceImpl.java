package com.Zyuchen.gmsservice.service.impl;

import com.Zyuchen.common.Exception.DefinedException;
import com.Zyuchen.gmsservice.entity.ClassDescription;
import com.Zyuchen.gmsservice.entity.ClassInfo;
import com.Zyuchen.gmsservice.entity.vo.ClassInfoForm;
import com.Zyuchen.gmsservice.mapper.ClassMapper;
import com.Zyuchen.gmsservice.service.ClassDescriptionService;
import com.Zyuchen.gmsservice.service.ClassInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程表 服务实现类
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-22
 */
@Service
public class ClassInfoServiceImpl extends ServiceImpl<ClassMapper, ClassInfo> implements ClassInfoService {

    @Autowired
    private ClassDescriptionService classDescriptionService;

    @Override
    public String saveClassInfo(ClassInfoForm classInfoForm) {
        ClassInfo classInfo = new ClassInfo();
        classInfo.setStatus(ClassInfo.COURSE_DRAFT);
        BeanUtils.copyProperties(classInfoForm, classInfo);
        boolean resultClassInfo = this.save(classInfo);

        if(!resultClassInfo){
            throw new DefinedException(20001, "课程信息保存失败");
        }

        //保存课程详情信息
        ClassDescription classDescription = new ClassDescription();
        classDescription.setDescription(classInfoForm.getDescription());
        classDescription.setClassId(classInfo.getClassId());
        boolean resultDescription = classDescriptionService.save(classDescription);
        if(!resultDescription){
            throw new DefinedException(20001, "课程详情信息保存失败");
        }

        return classInfo.getClassId();
    }
}
