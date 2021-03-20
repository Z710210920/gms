package com.Zyuchen.gmsservice.service.impl;

import com.Zyuchen.gmsservice.entity.User;
import com.Zyuchen.gmsservice.mapper.UserMapper;
import com.Zyuchen.gmsservice.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
