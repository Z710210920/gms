package com.Zyuchen.gmsservice.controller;


import com.Zyuchen.common.utils.R;
import com.Zyuchen.gmsservice.entity.vo.ClassVideoForm;
import com.Zyuchen.gmsservice.service.ClassVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-22
 */
@RestController
@RequestMapping("/gmsservice/classVideo")
@CrossOrigin
@Api(value="课程小结管理", tags={"课程小结管理接口"})
public class ClassVideoController {
    @Autowired
    private ClassVideoService classvideoService;

    @ApiOperation(value = "新增课时")
    @PostMapping("saveVideo")
    public R save(
            @ApiParam(name = "videoForm", value = "课时对象", required = true)
            @RequestBody ClassVideoForm videoInfoForm){
        classvideoService.setIsFree(videoInfoForm);
        classvideoService.saveVideoInfo(videoInfoForm);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询课时")
    @GetMapping("getVideo/{id}")
    public R getVideInfoById(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id){

        ClassVideoForm classVideoForm = classvideoService.getClassVideoFormById(id);
        return R.ok().data("item", classVideoForm);
    }

    @ApiOperation(value = "更新课时")
    @PutMapping("updateVideo/{id}")
    public R updateCourseInfoById(
            @ApiParam(name = "ClassVideoForm", value = "课时基本信息", required = true)
            @RequestBody ClassVideoForm classVideoForm,

            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id){
        classvideoService.setIsFree(classVideoForm);
        classvideoService.updateVideoInfoById(classVideoForm);
        return R.ok();
    }

    @ApiOperation(value = "根据ID删除课时")
    @DeleteMapping("deleteVideo/{id}")
    public R removeById(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id){

        boolean result = classvideoService.removeVideoById(id);
        if(result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }
}

