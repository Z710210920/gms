package com.Zyuchen.gmsservice.service;

import com.Zyuchen.gmsservice.entity.ClassVideo;
import com.Zyuchen.gmsservice.entity.vo.ClassVideoForm;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-22
 */
public interface ClassVideoService extends IService<ClassVideo> {

    boolean getCountByClassChapterId(String chapterId);

    void saveVideoInfo(ClassVideoForm videoInfoForm);

    void updateVideoInfoById(ClassVideoForm vlassVideoForm);

    ClassVideoForm getClassVideoFormById(String id);

    boolean removeVideoById(String id);

    boolean removeClassInfoById(String classId);

    void setIsFree(ClassVideoForm videoInfoForm);
}
