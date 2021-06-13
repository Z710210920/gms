package com.Zyuchen.gmsservice.service.impl;

import com.Zyuchen.common.Exception.DefinedException;
import com.Zyuchen.common.utils.JwtUtils;
import com.Zyuchen.common.utils.MD5;
import com.Zyuchen.gmsservice.entity.Coach;
import com.Zyuchen.gmsservice.entity.User;
import com.Zyuchen.gmsservice.entity.vo.LoginForm;
import com.Zyuchen.gmsservice.mapper.CoachMapper;
import com.Zyuchen.gmsservice.service.CoachService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * <p>
 * 教练信息表 服务实现类
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-01
 */
@Service
public class CoachServiceImpl extends ServiceImpl<CoachMapper, Coach> implements CoachService {

    private static final String ADMIN = "admin";
    private static final String NAME = "admin";

    private static final String USERNAMEORPASSWORD_ERROR = "用户名或密码错误！";
    private static final String USERNAMEORPASSWORDNOTEMPTY = "用户名或密码为空！";
    private static final String VARITYCODE_TIMEOUT = "验证码失效，请重新获取";
    private static final String VARITYCODE_ERROR = "验证码错误！";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public String login(LoginForm loginForm) {
        String token = "";
        String id = "";
        String name = "";
        String roles = "[coach]";
        String loginName = loginForm.getUsername();
        String password = loginForm.getPassword();
        String code = loginForm.getCode().toLowerCase();
        String uuid = loginForm.getUuid();

        try{
            String trueCode = Objects.requireNonNull(redisTemplate.opsForValue().get(uuid)).toLowerCase();
            if(loginName.equals(ADMIN)){
                if(password.equals(ADMIN)){
                    id = ADMIN;
                    name = NAME;
                    roles = "[admin]";
                }else{
                    throw new DefinedException(20001, USERNAMEORPASSWORD_ERROR);
                }
            }else{
                if(StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)){
                    throw new DefinedException(20001, USERNAMEORPASSWORDNOTEMPTY);
                }
                Coach coach = baseMapper.selectOne(new QueryWrapper<Coach>().eq("coachPhoneNumber", loginName));

                if(coach == null ||
                        !MD5.encrypt(password).equals(coach.getCoachPassword()) ||
                        Boolean.TRUE.equals(coach.getIsDeleted())){
                    throw new DefinedException(20001, USERNAMEORPASSWORD_ERROR);
                }
                id = coach.getCoachId();
                name = coach.getCoachName();
            }

            if(trueCode.equals(code)){
                token = JwtUtils.getJwtToken(id, name, roles);
                return token;
            }else{
                throw new DefinedException(20001, VARITYCODE_ERROR);
            }
        }catch (NullPointerException e){
            throw new DefinedException(20001, VARITYCODE_TIMEOUT);
        }
    }
}
