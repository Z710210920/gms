package com.Zyuchen.gmsservice.service;

import com.Zyuchen.gmsservice.entity.Course;
import com.Zyuchen.gmsservice.entity.vo.CourseQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程名称表 服务类
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-08
 */
public interface CourseService extends IService<Course> {

    void addCourseByExcel(MultipartFile file, CourseService courseService, List<Course> list);
}
