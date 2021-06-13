package com.Zyuchen.gmsservice.service;

import com.Zyuchen.gmsservice.entity.User;
import com.Zyuchen.gmsservice.entity.vo.LoginForm;
import com.Zyuchen.gmsservice.entity.vo.Register;
import com.Zyuchen.gmsservice.entity.vo.UserBalanceMessage;
import com.Zyuchen.gmsservice.entity.vo.UserVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-16
 */
public interface UserService extends IService<User> {

    String login(LoginForm loginForm);

    User getLoginInfo(String userId);

    void register(Register register);

    UserBalanceMessage selectByUserPhoneNumber(String phoneNumber);

    IPage<UserVO> pageListUserCondition(Page<UserVO> pageUser, QueryWrapper<UserVO> wrapper);

    UserVO getUserVOById(String id);
}
