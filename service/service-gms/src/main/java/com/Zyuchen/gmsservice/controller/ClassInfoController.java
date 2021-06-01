package com.Zyuchen.gmsservice.controller;


import com.Zyuchen.common.Exception.DefinedException;
import com.Zyuchen.common.utils.R;
import com.Zyuchen.gmsservice.entity.ClassInfo;
import com.Zyuchen.gmsservice.entity.User;
import com.Zyuchen.gmsservice.entity.vo.ClassInfoForm;
import com.Zyuchen.gmsservice.entity.vo.ClassInfoQuery;
import com.Zyuchen.gmsservice.entity.vo.ClassPublishVo;
import com.Zyuchen.gmsservice.service.ClassInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 * 课程表 前端控制器
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-22
 */
@RestController
@RequestMapping("/gmsservice/classInfo")
@CrossOrigin
@Api(value="课程管理", tags={"课程管理接口"})
public class ClassInfoController {

    @Autowired
    private ClassInfoService classInfoService;

    @ApiOperation(value = "新增课程")
    @PostMapping("save-class-info")
    public R saveClassInfo(
            @ApiParam(name = "ClassInfoForm", value = "课程基本信息", required = true)
            @RequestBody ClassInfoForm classInfoForm){

        String classId = classInfoService.saveClassInfo(classInfoForm);
        if(!StringUtils.isEmpty(classId)){
            return R.ok().data("classId", classId);
        }else{
            return R.error().message("保存失败");
        }
    }

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping("getClassInfo/{id}")
    public R getById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        ClassInfoForm classInfoForm = classInfoService.getClassInfoFormById(id);
        return R.ok().data("item", classInfoForm);
    }

    @ApiOperation(value="根据ID修改课程")
    @PutMapping("updateClassInfo/{id}")
    public R updateById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id,
            @ApiParam(name = "classinfo", value = "课程对象", required = true)
            @RequestBody ClassInfo classInfo){
        classInfo.setClassId(id);
        System.out.println(classInfo.toString());
        classInfoService.updateById(classInfo);
        return R.ok();
    }

    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("classPublishInfo/{id}")
    public R getCoursePublishVoById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        ClassPublishVo classPublishVo = classInfoService.getClassPublishVoById(id);
        return R.ok().data("item", classPublishVo);
    }

    @ApiOperation(value = "根据id发布课程")
    @PutMapping("publishClass/{id}")
    public R publishClassById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        classInfoService.publishClassById(id);
        return R.ok();
    }

    @ApiOperation(value = "分页课程列表")
    @GetMapping("{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "classInfoQuery", value = "查询对象", required = false)
                    ClassInfoQuery classInfoQuery){

        Page<ClassInfo> pageParam = new Page<>(page, limit);

        classInfoService.pageQuery(pageParam, classInfoQuery);
        List<ClassInfo> records = pageParam.getRecords();

        long total = pageParam.getTotal();

        return  R.ok().data("total", total).data("item", records);
    }

    @ApiOperation(value = "根据ID删除课程")
    @DeleteMapping("delete/{id}")
    public R removeById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        boolean result = classInfoService.removeClassInfoById(id);
        if(result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }

    @ApiOperation(value = "根据ID删除课程")
    @PostMapping("nextPeriod/{classInfoid}")
    public R nextPeriod(
            @ApiParam(name = "classInfoid", value = "课程ID", required = true)
            @PathVariable String classInfoid){

        ClassInfo classInfo = classInfoService.getById(classInfoid);
        if(classInfo.getCurrentTimes()+1 > classInfo.getClassTimes()){
            throw new DefinedException(20001,"课程已完结");
        }
        classInfo.setCurrentTimes(classInfo.getCurrentTimes()+1);
        classInfoService.updateById(classInfo);

        return R.ok();
    }
}

