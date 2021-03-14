package com.Zyuchen.gmsservice.listener;

import com.Zyuchen.common.Exception.DefinedException;
import com.Zyuchen.gmsservice.entity.Course;
import com.Zyuchen.gmsservice.entity.excel.CourseData;
import com.Zyuchen.gmsservice.service.CourseService;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;

public class CourseExcelListener extends AnalysisEventListener<CourseData> {

    public CourseService courseService;
    public List<Course> list;

    public CourseExcelListener() { }

    public CourseExcelListener(CourseService courseService, List<Course> list) {
        this.courseService = courseService;
        this.list = list;
    }

    @Override
    public void invoke(CourseData courseData, AnalysisContext analysisContext) {
        if(courseData == null){
            throw new DefinedException(20001, "文件数据为空");
        }else{
            Course excistCourse = this.excistOneCourse(courseService, courseData.toCourse());
            if(excistCourse == null){
                excistCourse = courseData.toCourse();
                if(excistCourse.reasonable()){
                    courseService.save(excistCourse);
                    list.add(excistCourse);
                }
            }
        }
    }

    public Course excistOneCourse(CourseService courseService, Course course){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("name",course.getName());
        wrapper.eq("difficulty",course.getDifficulty());
        wrapper.eq("duration",course.getDuration());
        wrapper.eq("part",course.getPart());
        wrapper.eq("target",course.getTarget());
        return courseService.getOne(wrapper);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
