package com.Zyuchen.gmsservice.controller;


import com.Zyuchen.common.utils.R;
import com.Zyuchen.gmsservice.entity.ClassChapter;
import com.Zyuchen.gmsservice.entity.vo.ClassChapterVo;
import com.Zyuchen.gmsservice.service.ClassChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 章节 前端控制器
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-22
 */
@RestController
@RequestMapping("/gmsservice/classchapter")
@CrossOrigin
@Api(value="课程章节管理", tags={"课程章节管理接口"})
public class ClassChapterController {
    @Autowired
    private ClassChapterService classchapterService;

    @ApiOperation(value = "嵌套章节数据列表")
    @GetMapping("nested-list/{classId}")
    public R nestedListByCourseId(
            @ApiParam(name = "classId", value = "课程ID", required = true)
            @PathVariable String classId){

        List<ClassChapterVo> classchapterVoList = classchapterService.nestedList(classId);
        return R.ok().data("items", classchapterVoList);
    }

    @ApiOperation(value = "新增章节")
    @PostMapping
    public R save(
            @ApiParam(name = "classchapterVo", value = "章节对象", required = true)
            @RequestBody ClassChapter classchapter){

        classchapterService.save(classchapter);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询章节")
    @GetMapping("{id}")
    public R getById(
            @ApiParam(name = "id", value = "章节ID", required = true)
            @PathVariable String id){

        ClassChapter classchapter = classchapterService.getById(id);
        return R.ok().data("item", classchapter);
    }

    @ApiOperation(value = "根据ID修改章节")
    @PutMapping("{id}")
    public R updateById(
            @ApiParam(name = "id", value = "章节ID", required = true)
            @PathVariable String id,

            @ApiParam(name = "classchapter", value = "章节对象", required = true)
            @RequestBody ClassChapter classchapter){

        classchapter.setId(id);
        classchapterService.updateById(classchapter);
        return R.ok();
    }

    @ApiOperation(value = "根据ID删除章节")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "章节ID", required = true)
            @PathVariable String id){

        boolean result = classchapterService.removeClassChapterById(id);
        if(result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }
}

