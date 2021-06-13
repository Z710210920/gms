package com.Zyuchen.gmsservice.controller;


import com.Zyuchen.common.Exception.DefinedException;
import com.Zyuchen.common.utils.R;
import com.Zyuchen.gmsservice.entity.*;
import com.Zyuchen.gmsservice.entity.vo.SigninTable;
import com.Zyuchen.gmsservice.service.SigninService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 签到表 前端控制器
 * </p>
 *
 * @author Zyuchen
 * @since 2021-05-09
 */
@RestController
@RequestMapping("/gmsservice/signin")
@CrossOrigin
@Api(value = "签到列表", tags={"签到接口"})
public class SigninController {

    @Autowired
    private SigninService signinService;
    @ApiOperation(value="获取指定课程的所有选课信息")
    @PostMapping("/findAllSignInMessage/{page}/{limit}/{classInfoId}")
    public R findAllSignInMessage(@ApiParam(name = "page", value = "当前页码", required = true)
                                @PathVariable Long page,

                                @ApiParam(name = "limit", value = "每页记录数", required = true)
                                @PathVariable Long limit,

                                @ApiParam(name = "classInfoId", value = "课程信息", required = true)
                                      @PathVariable String classInfoId){
        System.out.println(classInfoId);
        List<SigninTable> signinList = signinService.getAllSignInMessageByClassId(classInfoId);

        return R.ok().data("item", signinList).data("total", signinList.size());
    }

    @ApiOperation(value="课程签到")
    @PostMapping("/signin")
    public R signin(@ApiParam(name = "Signin", value = "课程信息", required = true)
                        @RequestBody(required = false) Signin signin){
        QueryWrapper<Signin> wrapper = new QueryWrapper<>();
        wrapper.eq("classselection", signin.getClassselection());
        wrapper.eq("currentTimes", signin.getCurrentTimes());
        if(signinService.getOne(wrapper) != null){
            throw new DefinedException(20001, "已签到");
        }
        signinService.save(signin);

        return R.ok();
    }
}

