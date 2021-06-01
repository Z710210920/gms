package com.Zyuchen.gmsservice.service;

import com.Zyuchen.gmsservice.entity.Signin;
import com.Zyuchen.gmsservice.entity.vo.SigninTable;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 签到表 服务类
 * </p>
 *
 * @author Zyuchen
 * @since 2021-05-09
 */
public interface SigninService extends IService<Signin> {

    List<SigninTable> getAllSignInMessageByClassId(String classId);
}
