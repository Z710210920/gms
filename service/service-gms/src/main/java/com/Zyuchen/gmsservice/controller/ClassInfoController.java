package com.Zyuchen.gmsservice.controller;


import com.Zyuchen.common.utils.R;
import com.Zyuchen.gmsservice.entity.vo.ClassInfoForm;
import com.Zyuchen.gmsservice.service.ClassInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
    private ClassInfoService classService;

    @ApiOperation(value = "新增课程")
    @PostMapping("save-class-info")
    public R saveClassInfo(
            @ApiParam(name = "ClassInfoForm", value = "课程基本信息", required = true)
            @RequestBody ClassInfoForm classInfoForm){

        String classId = classService.saveClassInfo(classInfoForm);
        if(!StringUtils.isEmpty(classId)){
            return R.ok().data("classId", classId);
        }else{
            return R.error().message("保存失败");
        }
    }
}

