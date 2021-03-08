package com.Zyuchen.gmsservice.controller;

import com.Zyuchen.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gmsservice/user")
@Api(value = "用户登录", tags={"用户登录接口"})
@CrossOrigin
public class LoginController {

    @ApiOperation("用户登录")
    @PostMapping("login")
    public R login(/*@RequestBody @ApiParam("用户名") String username,
                   @RequestBody @ApiParam("密码") String password*/){
        /*if(username.equals("admin") && password.equals("admin")){
            return R.ok().data("token", "admin");
        }else{
            return R.error();
        }*/
        return R.ok().data("token", "admin");
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
