package com.Zyuchen.gmsservice.service.impl;

import com.Zyuchen.common.Exception.DefinedException;
import com.Zyuchen.common.utils.JwtUtils;
import com.Zyuchen.common.utils.MD5;
import com.Zyuchen.gmsservice.entity.User;
import com.Zyuchen.gmsservice.entity.vo.LoginForm;
import com.Zyuchen.gmsservice.entity.vo.Register;
import com.Zyuchen.gmsservice.entity.vo.UserBalanceMessage;
import com.Zyuchen.gmsservice.entity.vo.UserVO;
import com.Zyuchen.gmsservice.mapper.UserMapper;
import com.Zyuchen.gmsservice.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public String login(LoginForm loginForm) {
        String token = null;
        String loginName = loginForm.getUsername();
        String password = loginForm.getPassword();
        if(StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)){
            throw new DefinedException(20001, "error");
        }
        User user = baseMapper.selectOne(new QueryWrapper<User>().eq("userPhoneNumber", loginName));

        if(user == null){
            new DefinedException(20001, "用户名或密码错误！");
        }
        if(!MD5.encrypt(password).equals(user.getUserPassword())){
            new DefinedException(20001, "用户名或密码错误！");
        }

        if(user.getIsDeleted()){
            new DefinedException(20001, "用户名或密码错误！");
        }

        String code = loginForm.getCode().toLowerCase();
        String uuid = loginForm.getUuid();

        String trueCode = redisTemplate.opsForValue().get(uuid).toLowerCase();
        if(trueCode == null){
            new DefinedException(20001,"验证码失效,请重新获取");
        }else if(trueCode.equals(code)){
            token = JwtUtils.getJwtToken(user.getUserId(), user.getUserName());
        }else{
            throw new DefinedException(20001,"验证码错误");
        }
        return token;
    }

    @Override
    public User getLoginInfo(String userId) {
        User user = baseMapper.selectById(userId);
        /*LoginInfoVo loginInfoVo = new LoginInfoVo();
        BeanUtils.copyProperties(user, loginInfoVo);*/
        return user;
    }

    @Override
    public void register(Register register) {
        String code = register.getCode().toLowerCase();
        String uuid = register.getUuid();

        String trueCode = redisTemplate.opsForValue().get(uuid).toLowerCase();
        if(trueCode == null){
            throw new DefinedException(20001,"验证码失效,请重新获取");
        }else if(trueCode.equals(code)) {
            User usertest = baseMapper.selectOne(new QueryWrapper<User>().eq("userPhoneNumber", register.getUserPhoneNumber()));
            if(usertest != null){
                throw new DefinedException(20001, "该手机号已注册！");
            }
            User user = new User();
            BeanUtils.copyProperties(user, register);
            user.setUserPassword(MD5.encrypt(user.getUserPassword()));
            baseMapper.insert(user);
        }else{
            throw new DefinedException(20001,"验证码错误");
        }
    }

    @Override
    public UserBalanceMessage selectByUserPhoneNumber(String phoneNumber) {
        return baseMapper.selectBalanceMessage(phoneNumber).get(0);
    }

    @Override
    public IPage<UserVO> pageListUserCondition(Page<UserVO> pageUser, QueryWrapper<UserVO> wrapper) {
        return baseMapper.pageListUserCondition(pageUser, wrapper);
    }

}
