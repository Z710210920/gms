package com.Zyuchen.gmsservice.service;

import com.Zyuchen.gmsservice.entity.Coach;
import com.Zyuchen.gmsservice.entity.vo.LoginForm;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 教练信息表 服务类
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-01
 */
public interface CoachService extends IService<Coach> {

    String login(LoginForm loginForm);
}
