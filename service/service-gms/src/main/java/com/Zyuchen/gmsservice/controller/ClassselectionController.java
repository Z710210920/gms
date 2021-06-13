package com.Zyuchen.gmsservice.controller;


import com.Zyuchen.common.Exception.DefinedException;
import com.Zyuchen.common.utils.R;
import com.Zyuchen.gmsservice.entity.ClassInfo;
import com.Zyuchen.gmsservice.entity.Classselection;
import com.Zyuchen.gmsservice.entity.Membershipcard;
import com.Zyuchen.gmsservice.entity.User;
import com.Zyuchen.gmsservice.entity.vo.ClassInfoQuery;
import com.Zyuchen.gmsservice.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * 用户选课表 前端控制器
 * </p>
 *
 * @author Zyuchen
 * @since 2021-05-09
 */
@RestController
@RequestMapping("/gmsservice/classselection")
@CrossOrigin
@Api(value = "选课列表", tags={"选课接口"})
public class ClassselectionController {
    @Autowired
    private ClassselectionService classselectionService;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountBookService accountBookService;

    @Autowired
    private ClassInfoService classInfoService;

    @Autowired
    private MembershipcardService membershipcardService;

    @ApiOperation(value="获取指定用户所有选课")
    @PostMapping("/findAllClassByUser/{page}/{limit}")
    public R findAllClassByUser(@ApiParam(name = "page", value = "当前页码", required = true)
                              @PathVariable Long page,

                          @ApiParam(name = "limit", value = "每页记录数", required = true)
                              @PathVariable Long limit,

                          @ApiParam(name = "userPhoneNumber", value = "查询对象", required = true)
                                      String userPhoneNumber){

        Page<User> pageUser = new Page<>(page,limit);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userPhoneNumber",userPhoneNumber);
        userService.page(pageUser, wrapper);
        if(pageUser.getSize() != 0){
            String userId = pageUser.getRecords().get(0).getUserId();
            Page<Classselection> pageClass = new Page<>(page,limit);
            QueryWrapper<Classselection> wrapperClassInfo = new QueryWrapper<>();
            wrapperClassInfo.eq("user",userId);
            classselectionService.page(pageClass, wrapperClassInfo);

            return R.ok().data("total", pageClass.getTotal()).data("item", pageClass.getRecords());
        }else{
            return R.ok().data("item", null);
        }
    }

    @ApiOperation(value="获取指定课程选择所有用户")
    @PostMapping("/findAllUserByClass/{page}/{limit}")
    public R findAllUserByClass(@ApiParam(name = "page", value = "当前页码", required = true)
                          @PathVariable Long page,

                          @ApiParam(name = "limit", value = "每页记录数", required = true)
                          @PathVariable Long limit, @RequestBody Classselection classselection){
        String classId = classselection.getClassId();
        Page<Classselection> pageUser = new Page<>(page,limit);
        QueryWrapper<Classselection> wrapperClassInfo = new QueryWrapper<>();
        wrapperClassInfo.eq("classId",classId);
        classselectionService.page(pageUser, wrapperClassInfo);
        List<Classselection> classSelectionList = pageUser.getRecords();
        System.out.println(pageUser.getTotal());
        List<User> userList = new ArrayList<User>();
        for(Classselection cs : classSelectionList){
            userList.add(userService.getById(cs.getUser()));
        }

        return R.ok().data("total", userList.size()).data("item", userList);
    }

    @ApiOperation(value="选课")
    @PostMapping("/SelectClass")
    public R SelectClass(
            @ApiParam(name = "classselection", value = "选课对象", required = true)
                    @RequestBody Classselection classselection){
    QueryWrapper<Classselection> wrapperClassInfo = new QueryWrapper<>();
    wrapperClassInfo.eq("classId", classselection.getClassId());
    wrapperClassInfo.eq("user", classselection.getUser());
    if(classselectionService.getOne(wrapperClassInfo) != null){
        throw new DefinedException(20001, "该用户已选课");
    }
    String classId = classselection.getClassId();
    ClassInfo classInfo = classInfoService.getById(classId);
    Membershipcard mc = membershipcardService.getOne(new QueryWrapper<Membershipcard>().eq("ownerId", classselection.getUser()));
    BigDecimal count = BigDecimal.valueOf(classInfo.getClassPrice());
    //会员不能免费上课
    /*if(mc.getDeadline().after(new Date())){
        count = BigDecimal.ZERO;
    }else{
        count = BigDecimal.valueOf(classInfo.getClassPrice());
    }*/
    ClassInfo ci = new ClassInfo();
    ci.setClassId(classId);
    ci.setBuyCount(classInfo.getBuyCount()+1);
    classInfoService.updateById(ci);
    accountBookService.deductions(classselection.getUser(), count, 1);
    classselectionService.save(classselection);

        return R.ok();
    }
}

