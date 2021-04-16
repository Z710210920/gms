package com.Zyuchen.gmsservice.controller;

import com.Zyuchen.common.utils.R;
import com.Zyuchen.gmsservice.entity.User;
import com.Zyuchen.gmsservice.entity.vo.LoginForm;
import com.Zyuchen.gmsservice.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gmsservice/user")
@Api(value = "用户登录", tags={"用户登录接口"})
@CrossOrigin
public class LoginController {
    @Autowired
    private UserService userService;

    @ApiOperation("用户登录")
    @PostMapping("login")
    public R gin(@RequestBody @ApiParam("用户") LoginForm loginForm){
        Page<User> pageUser = new Page<>(1,1);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        String loginName = loginForm.getUsername();
        String password = loginForm.getPassword();
        if(!StringUtils.isEmpty(loginName)){
            wrapper.like("userPhoneNumber",loginName);
        }
        if(!StringUtils.isEmpty(password)){
            wrapper.eq("userPassword",password);
        }
        userService.page(pageUser, wrapper);


        long total =  pageUser.getTotal();
        if(total == 1){
            return R.ok().data("token", "admin");
        }else{
            return R.error();
        }
    }

    @ApiOperation("用户信息获取")
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles", "[admin]").data("name","admin").data("avatar","https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3740520249,3092805081&fm=26&gp=0.jpg");
    }

    @ApiOperation("退出登录")
    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }
}
