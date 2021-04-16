package com.Zyuchen.gmsservice.service;

import com.Zyuchen.gmsservice.entity.ClassChapter;
import com.Zyuchen.gmsservice.entity.vo.ClassChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 章节 服务类
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-22
 */
public interface ClassChapterService extends IService<ClassChapter> {
    List<ClassChapterVo> nestedList(String classId);
    boolean removeClassChapterById(String id);
    boolean removeClassInfoById(String classId);
}
