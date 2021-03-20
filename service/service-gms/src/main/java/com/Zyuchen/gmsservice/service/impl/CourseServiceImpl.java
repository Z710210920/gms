package com.Zyuchen.gmsservice.service.impl;

import com.Zyuchen.gmsservice.entity.Course;
import com.Zyuchen.gmsservice.entity.excel.CourseData;
import com.Zyuchen.gmsservice.listener.CourseExcelListener;
import com.Zyuchen.gmsservice.mapper.CourseMapper;
import com.Zyuchen.gmsservice.service.CourseService;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程名称表 服务实现类
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-08
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Override
    public void addCourseByExcel(MultipartFile file, CourseService courseService, List<Course> list) {
        try{
            InputStream in = file.getInputStream();
            EasyExcel.read(in, CourseData.class, new CourseExcelListener(courseService, list)).sheet().doRead();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
