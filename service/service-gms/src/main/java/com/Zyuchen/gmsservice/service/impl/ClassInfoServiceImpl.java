package com.Zyuchen.gmsservice.service.impl;

import com.Zyuchen.common.Exception.DefinedException;
import com.Zyuchen.gmsservice.entity.ClassDescription;
import com.Zyuchen.gmsservice.entity.ClassInfo;
import com.Zyuchen.gmsservice.entity.ClassVideo;
import com.Zyuchen.gmsservice.entity.vo.ClassInfoForm;
import com.Zyuchen.gmsservice.entity.vo.ClassInfoQuery;
import com.Zyuchen.gmsservice.entity.vo.ClassPublishVo;
import com.Zyuchen.gmsservice.mapper.ClassInfoMapper;
import com.Zyuchen.gmsservice.service.ClassChapterService;
import com.Zyuchen.gmsservice.service.ClassDescriptionService;
import com.Zyuchen.gmsservice.service.ClassInfoService;
import com.Zyuchen.gmsservice.service.ClassVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 课程表 服务实现类
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-22
 */
@Service
public class ClassInfoServiceImpl extends ServiceImpl<ClassInfoMapper, ClassInfo> implements ClassInfoService {

    @Autowired
    private ClassDescriptionService classDescriptionService;

    @Autowired
    private ClassVideoService classVideoService;

    @Autowired
    private ClassChapterService classChapterService;

    @Override
    public ClassInfoForm getClassInfoFormById(String id) {
        ClassInfo classInfo = this.getById(id);
        if(classInfo == null){
            throw new DefinedException(20001, "数据不存在");
        }
        ClassInfoForm classInfoForm = new ClassInfoForm();
        BeanUtils.copyProperties(classInfo, classInfoForm);

        ClassDescription classDescription = classDescriptionService.getById(id);
        if(classInfo != null){
            classInfoForm.setDescription(classDescription.getDescription());
        }

        return classInfoForm;
    }

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

    @Override
    public ClassPublishVo getClassPublishVoById(String id) {
        return baseMapper.selectClassPublishVoById(id);
    }

    @Override
    public boolean publishClassById(String id) {
        ClassInfo classInfo = new ClassInfo();
        classInfo.setClassId(id);
        classInfo.setStatus(classInfo.COURSE_NORMAL);
        Integer count = baseMapper.updateById(classInfo);
        return null != count && count > 0;
    }

    @Override
    public void pageQuery(Page<ClassInfo> pageParam, ClassInfoQuery classInfoQuery) {

        QueryWrapper<ClassInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("createtime");

        if (classInfoQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String title = classInfoQuery.getTitle();
        String coachId = classInfoQuery.getCoachId();
        String courseId = classInfoQuery.getCourseId();

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }

        if (!StringUtils.isEmpty(coachId) ) {
            queryWrapper.eq("coachId", coachId);
        }

        if (!StringUtils.isEmpty(courseId)) {
            queryWrapper.ge("courseId", courseId);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public boolean removeClassInfoById(String classId) {
        //根据id删除所有视频
        classVideoService.removeClassInfoById(classId);

        //根据id删除所有章节
        classChapterService.removeClassInfoById(classId);

        //根据id删除所有课程详情
        classDescriptionService.removeById(classId);

        //删除封面 TODO 独立完成

        Integer result = baseMapper.deleteById(classId);
        return null != result && result > 0;
    }
}
