package com.Zyuchen.gmsservice.controller;


import com.Zyuchen.common.Exception.DefinedException;
import com.Zyuchen.common.utils.JwtUtils;
import com.Zyuchen.common.utils.R;
import com.Zyuchen.gmsservice.entity.Coach;
import com.Zyuchen.gmsservice.entity.User;
import com.Zyuchen.gmsservice.entity.vo.LoginForm;
import com.Zyuchen.gmsservice.entity.vo.Register;
import com.Zyuchen.gmsservice.service.CoachService;
import com.Zyuchen.gmsservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/gmsservice/user")
@Api(value = "用户登录", tags={"用户登录接口"})
@CrossOrigin
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private CoachService coachService;

    @ApiOperation("用户登录")
    @PostMapping("login")
    public R gin(@RequestBody @ApiParam("用户") LoginForm loginForm){
        String token = userService.login(loginForm);
        return R.ok().data("token", token);
    }

/*    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("getLoginInfo")
    public R getLoginInfo(HttpServletRequest request){
        try {
            String userId = JwtUtils.getMemberIdByJwtToken(request);
            User user = userService.getLoginInfo(userId);
            return R.ok().data("item", user);
        }catch (Exception e){
            e.printStackTrace();
            throw new DefinedException(20001,"error");
        }
    }*/

    @ApiOperation("登录")
    @PostMapping("admin/login")
    public R admingin(@RequestBody @ApiParam("用户") LoginForm loginForm){
        System.out.println(loginForm.getCode());
        String token;
        if (loginForm.getRoles() == 0){
            token = coachService.login(loginForm);
        }else{
            token = userService.login(loginForm);
        }

        return R.ok().data("token", token);
    }

    @ApiOperation("注册")
    @PostMapping("register")
    public R register(@RequestBody @ApiParam("用户") Register register){
        userService.register(register);
        return R.ok();
    }

    @ApiOperation("用户信息获取")
    @GetMapping("info")
    public R info(String token){
        try {
            String roles = JwtUtils.getMemberRolesByJwtToken(token);
            if(roles.equals("[admin]")){
                return R.ok().data("roles", "[admin]").data("name","管理员").data("avatar","https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3740520249,3092805081&fm=26&gp=0.jpg");
            }else if(roles.equals("[coach]")){
                String Id = JwtUtils.getMemberIdByJwtToken(token);
                Coach coach = coachService.getById(Id);
                return R.ok()
                        .data("roles", roles)
                        .data("Id", coach.getCoachId())
                        .data("name", coach.getCoachName())
                        .data("avatar", coach.getAvatar());
            }else{
                String Id = JwtUtils.getMemberIdByJwtToken(token);
                User user = userService.getById(Id);
                return R.ok()
                        .data("roles", roles)
                        .data("Id", user.getUserId())
                        .data("name", user.getUserName())
                        .data("avatar", user.getAvatar());
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new DefinedException(20001,"error");
        }
    }

    @ApiOperation("退出登录")
    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }
}
