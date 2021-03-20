package com.Zyuchen.gmsservice.controller;


import com.Zyuchen.common.utils.R;
import com.Zyuchen.gmsservice.entity.Coach;
import com.Zyuchen.gmsservice.entity.Course;
import com.Zyuchen.gmsservice.entity.vo.CoachQuery;
import com.Zyuchen.gmsservice.entity.vo.CourseQuery;
import com.Zyuchen.gmsservice.service.CourseService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程名称表 前端控制器
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-08
 */

@Api(value="课程管理", tags={"课程管理接口"})
@RestController
@RequestMapping("/gmsservice/course")
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;

    @ApiOperation(value="获取所有课程")
    @GetMapping("/findAll")
    public R findAllCoach(){
        List<Course> list = courseService.list(null);

        return R.ok().data("item", list);
    }

    @ApiOperation(value="根据ID删除课程")
    @DeleteMapping("delete/{id}")
    public R removeCourseById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){
        boolean flag = courseService.removeById(id);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }

    @GetMapping("pageCourse/{current}/{limit}")
    @ApiOperation("分页查询")
    public R pageListCoach(@PathVariable @ApiParam("当前页") long current,
                           @PathVariable @ApiParam("每页记录数") long limit){
        //创建page对象
        Page<Course> pageCourse = new Page<>(current,limit);
        //分页数据储存在pageTeacher对象里面
        courseService.page(pageCourse, null);

        long total =  pageCourse.getTotal();
        List<Course> records = pageCourse.getRecords();

        /*Map map = new HashMap();
        map.put("total", total);
        map.put("rows", records);
        return R.ok().data(map);*/

        return R.ok().data("total", total).data("item", records);
    }

    @PostMapping("pageCourseCondition/{current}/{limit}")
    @ApiOperation("分页条件查询")
    public R pageListCoachCondition(@PathVariable @ApiParam("当前页") long current,
                           @PathVariable @ApiParam("每页记录数") long limit,
                           @ApiParam("查询条件")
                           @RequestBody(required = false)
                                   CourseQuery courseQuery){
        Page<Course> pageCourse = new Page<>(current,limit);
        QueryWrapper<Course> wrapper = new QueryWrapper<>();

        String name = courseQuery.getName();
        Integer target = courseQuery.getTarget();
        Integer part = courseQuery.getPart();
        Integer difficulty = courseQuery.getDifficulty();
        Integer duration = courseQuery.getDuration();
        //分页数据储存在pageCourse对象里面
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(target)){
            wrapper.eq("target", target);
        }
        if(!StringUtils.isEmpty(part)){
            wrapper.eq("part", part);
        }
        if(!StringUtils.isEmpty(difficulty)){
            wrapper.eq("difficulty", difficulty);
        }
        if(!StringUtils.isEmpty(duration)){
            wrapper.between("duration", 20*duration, 20*duration+20);
        }
        courseService.page(pageCourse, wrapper);
        List<Course> records = pageCourse.getRecords();
        long total = pageCourse.getTotal();

        return R.ok().data("total", total).data("item", records);
    }

    //添加课程种类
    //通过获取上传的excel表，吧文件内容读取出来
    @ApiOperation(value="使用excel表添加课程内容")
    @PostMapping("/addByExcel")
    public R addCourseByExcel(MultipartFile file){
        List<Course> list = new ArrayList<Course>();
        courseService.addCourseByExcel(file, courseService, list);
        return R.ok().data("item",list);
    }

    @ApiOperation(value = "新增课程")
    @PostMapping("save")
    public R save(
            @ApiParam(name = "course", value = "课程对象", required = true)
            @RequestBody Course course){
        courseService.save(course);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping("get/{id}")
    public R getById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){
        Course course = courseService.getById(id);
        return R.ok().data("item", course);
    }

    @ApiOperation(value="根据ID修改课程")
    @PutMapping("update/{id}")
    public R updateById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id,
            @ApiParam(name = "course", value = "课程对象", required = true)
            @RequestBody Course course){
        course.setCourseId(id);
        System.out.println(course.toString());
        courseService.updateById(course);
        return R.ok();
    }

}

