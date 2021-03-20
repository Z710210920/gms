package com.Zyuchen.gmsservice.controller;


import com.Zyuchen.common.utils.R;
import com.Zyuchen.gmsservice.entity.Classroom;
import com.Zyuchen.gmsservice.entity.vo.ClassroomQuery;
import com.Zyuchen.gmsservice.service.ClassroomService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 教室 前端控制器
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-19
 */
@RestController
@RequestMapping("/gmsservice/classroom")
@CrossOrigin
@Api(value="教室管理", tags={"教室管理接口"})
public class ClassroomController {
    @Autowired
    private ClassroomService classroomService;

    @ApiOperation(value="获取所有教室")
    @GetMapping("/findAll")
    public R findAllClassroom(){
        List<Classroom> list = classroomService.list(null);

        return R.ok().data("item", list);
    }

    @ApiOperation(value="根据ID删除教室")
    @DeleteMapping("delete/{id}")
    public R removeClassroomById(
            @ApiParam(name = "id", value = "教室ID", required = true)
            @PathVariable String id){
        boolean flag = classroomService.removeById(id);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }

    @GetMapping("pageClassroom/{current}/{limit}")
    @ApiOperation("分页查询")
    public R pageListClassroom(@PathVariable @ApiParam("当前页") long current,
                           @PathVariable @ApiParam("每页记录数") long limit){
        Page<Classroom> pageClassroom = new Page<>(current,limit);
        classroomService.page(pageClassroom, null);

        long total =  pageClassroom.getTotal();
        List<Classroom> records = pageClassroom.getRecords();

        return R.ok().data("total", total).data("item", records);
    }

    @PostMapping("pageClassroomCondition/{current}/{limit}")
    @ApiOperation("分页条件查询")
    public R pageListClassroomCondition(@PathVariable @ApiParam("当前页") long current,
                                    @PathVariable @ApiParam("每页记录数") long limit,
                                    @ApiParam("查询条件")
                                    @RequestBody(required = false)
                                            ClassroomQuery classroomQuery){
        //创建page对象
        Page<Classroom> pageClassroom = new Page<>(current,limit);
        QueryWrapper<Classroom> wrapper = new QueryWrapper<>();
        String classRoomName = classroomQuery.getClassRoomName();
        Integer maximum = classroomQuery.getMaximum();
        if(!StringUtils.isEmpty(maximum)){
            wrapper.ge("maximum",maximum*20);
            wrapper.le("maximum",(maximum+1)*20);
        }
        if(!StringUtils.isEmpty(classRoomName)){
            wrapper.eq("classRoomName",classRoomName);
        }
        //分页数据储存在pageTeacher对象里面
        classroomService.page(pageClassroom, wrapper);


        long total =  pageClassroom.getTotal();
        List<Classroom> records = pageClassroom.getRecords();


        return R.ok().data("total", total).data("item", records);
    }

    @ApiOperation(value = "新增教室")
    @PostMapping("save")
    public R save(
            @ApiParam(name = "classroom", value = "教室对象", required = true)
            @RequestBody Classroom classroom){
        classroomService.save(classroom);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询教室")
    @GetMapping("get/{id}")
    public R getById(
            @ApiParam(name = "id", value = "教室ID", required = true)
            @PathVariable String id){
        Classroom classroom = classroomService.getById(id);
        return R.ok().data("item", classroom);
    }

    @ApiOperation(value="根据ID修改教室")
    @PutMapping("update/{id}")
    public R updateById(
            @ApiParam(name = "id", value = "教室ID", required = true)
            @PathVariable String id,
            @ApiParam(name = "classroom", value = "教室对象", required = true)
            @RequestBody Classroom classroom){
        classroom.setClassRoomId(id);
        System.out.println(classroom.toString());
        classroomService.updateById(classroom);
        return R.ok();
    }
}

