package com.Zyuchen.gmsservice.controller;


import com.Zyuchen.common.utils.R;
import com.Zyuchen.gmsservice.entity.Coach;
import com.Zyuchen.gmsservice.entity.vo.CoachQuery;
import com.Zyuchen.gmsservice.service.CoachService;
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
 * 教练信息表 前端控制器
 * </p>
 *
 * @author Zyuchen
 * @since 2021-02-20
 */

/*
Swagger:api注释
api：类
apioparetion：方法
apiparam：参数
* */

@Api(value="教练管理", tags={"教练管理接口"})
@RestController
@RequestMapping("/gmsservice/coach")
@CrossOrigin
public class CoachController {

    @Autowired
    private CoachService coachService;

    @ApiOperation(value="获取所有教练")
    @GetMapping("/findAll")
    public R findAllCoach(){
        List<Coach> list = coachService.list(null);

        return R.ok().data("item", list);
    }

    @ApiOperation(value="根据ID删除教练")
    @DeleteMapping("delete/{id}")
    public R removeCoachById(
            @ApiParam(name = "id", value = "教练ID", required = true)
            @PathVariable String id){
        boolean flag = coachService.removeById(id);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }

    @GetMapping("pageCoach/{current}/{limit}")
    @ApiOperation("分页查询")
    public R pageListCoach(@PathVariable @ApiParam("当前页") long current,
                           @PathVariable @ApiParam("每页记录数") long limit){
        //创建page对象
        Page<Coach> pageCoach = new Page<>(current,limit);
        //分页数据储存在pageTeacher对象里面
        coachService.page(pageCoach, null);

        long total =  pageCoach.getTotal();
        List<Coach> records = pageCoach.getRecords();

        /*Map map = new HashMap();
        map.put("total", total);
        map.put("rows", records);
        return R.ok().data(map);*/

        return R.ok().data("total", total).data("item", records);
    }

    @PostMapping("pageCoachCondition/{current}/{limit}")
    @ApiOperation("分页条件查询")
    public R pageListCoachCondition(@PathVariable @ApiParam("当前页") long current,
                                    @PathVariable @ApiParam("每页记录数") long limit,
                                    @ApiParam("查询条件")
                                    @RequestBody(required = false)
                                            CoachQuery coachQuery){
        //创建page对象
        Page<Coach> pageCoach = new Page<>(current,limit);
        QueryWrapper<Coach> wrapper = new QueryWrapper<>();
        String coachName = coachQuery.getCoachName();
        Integer level = coachQuery.getLevel();
        String coachPhoneNumber = coachQuery.getCoachPhoneNumber();
        String coachRealName = coachQuery.getCoachRealName();
        if(!StringUtils.isEmpty(coachRealName)){
            wrapper.like("coachRealName",coachRealName);
        }
        if(!StringUtils.isEmpty(coachPhoneNumber)){
            wrapper.eq("coachPhoneNumber",coachPhoneNumber);
        }
        if(!StringUtils.isEmpty(coachName)){
            wrapper.like("coachName",coachName);
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level", level);
        }
        //分页数据储存在pageTeacher对象里面
        coachService.page(pageCoach, wrapper);


        long total =  pageCoach.getTotal();
        List<Coach> records = pageCoach.getRecords();


        return R.ok().data("total", total).data("item", records);
    }

    @ApiOperation(value = "新增教练")
    @PostMapping("save")
    public R save(
            @ApiParam(name = "coach", value = "教练对象", required = true)
            @RequestBody Coach coach){
        coachService.save(coach);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询教练")
    @GetMapping("get/{id}")
    public R getById(
            @ApiParam(name = "id", value = "教练ID", required = true)
            @PathVariable String id){
        Coach coach = coachService.getById(id);
        return R.ok().data("item", coach);
    }

    @ApiOperation(value="根据ID修改教练")
    @PutMapping("update/{id}")
    public R updateById(
            @ApiParam(name = "id", value = "教练ID", required = true)
            @PathVariable String id,
            @ApiParam(name = "coach", value = "教练对象", required = true)
            @RequestBody Coach coach){
        coach.setCoachId(id);
        System.out.println(coach.toString());
        coachService.updateById(coach);
        return R.ok();
    }
}

