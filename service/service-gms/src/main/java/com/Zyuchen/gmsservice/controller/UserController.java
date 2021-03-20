package com.Zyuchen.gmsservice.controller;


import com.Zyuchen.common.utils.R;
import com.Zyuchen.gmsservice.entity.User;
import com.Zyuchen.gmsservice.entity.vo.UserQuery;
import com.Zyuchen.gmsservice.service.UserService;
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
 * 用户信息表 前端控制器
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-16
 */
@Api(value="用户管理", tags={"用户管理接口"})
@RestController
@RequestMapping("/gmsservice/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value="获取所有用户")
    @GetMapping("/findAll")
    public R findAllUser(){
        List<User> list = userService.list(null);

        return R.ok().data("item", list);
    }

    @ApiOperation(value="根据ID删除用户")
    @DeleteMapping("delete/{id}")
    public R removeUserById(
            @ApiParam(name = "id", value = "用户ID", required = true)
            @PathVariable String id){
        boolean flag = userService.removeById(id);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }

    @GetMapping("pageUser/{current}/{limit}")
    @ApiOperation("分页查询")
    public R pageListUser(@PathVariable @ApiParam("当前页") long current,
                           @PathVariable @ApiParam("每页记录数") long limit){
        //创建page对象
        Page<User> pageUser = new Page<>(current,limit);
        //分页数据储存在pageTeacher对象里面
        userService.page(pageUser, null);

        long total =  pageUser.getTotal();
        List<User> records = pageUser.getRecords();

        /*Map map = new HashMap();
        map.put("total", total);
        map.put("rows", records);
        return R.ok().data(map);*/

        return R.ok().data("total", total).data("item", records);
    }

    @PostMapping("pageUserCondition/{current}/{limit}")
    @ApiOperation("分页条件查询")
    public R pageListUserCondition(@PathVariable @ApiParam("当前页") long current,
                                    @PathVariable @ApiParam("每页记录数") long limit,
                                    @ApiParam("查询条件")
                                    @RequestBody(required = false)
                                            UserQuery userQuery){
        //创建page对象
        Page<User> pageUser = new Page<>(current,limit);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        String userName = userQuery.getUserName();
        String userIdentityNumber = userQuery.getUserIdentityNumber();
        String userPhoneNumber = userQuery.getUserPhoneNumber();
        String userRealName = userQuery.getUserRealName();
        Integer level = userQuery.getLevel();
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(userName)){
            wrapper.like("userName",userName);
        }

        if(!StringUtils.isEmpty(userRealName)){
            wrapper.like("userRealName",userRealName);
        }
        if(!StringUtils.isEmpty(userPhoneNumber)){
            wrapper.eq("userPhoneNumber", userPhoneNumber);
        }
        if(!StringUtils.isEmpty(userIdentityNumber)){
            wrapper.eq("userIdentityNumber", userIdentityNumber);
        }
        //分页数据储存在pageTeacher对象里面
        userService.page(pageUser, wrapper);


        long total =  pageUser.getTotal();
        List<User> records = pageUser.getRecords();


        return R.ok().data("total", total).data("item", records);
    }

    @ApiOperation(value = "新增用户")
    @PostMapping("save")
    public R save(
            @ApiParam(name = "user", value = "用户对象", required = true)
            @RequestBody User user){
        userService.save(user);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询用户")
    @GetMapping("get/{id}")
    public R getById(
            @ApiParam(name = "id", value = "用户ID", required = true)
            @PathVariable String id){
        User user = userService.getById(id);
        return R.ok().data("item", user);
    }

    @ApiOperation(value="根据ID修改用户")
    @PutMapping("update/{id}")
    public R updateById(
            @ApiParam(name = "id", value = "用户ID", required = true)
            @PathVariable String id,
            @ApiParam(name = "user", value = "用户对象", required = true)
            @RequestBody User user){
        user.setUserId(id);
        System.out.println(user.toString());
        userService.updateById(user);
        return R.ok();
    }
}

