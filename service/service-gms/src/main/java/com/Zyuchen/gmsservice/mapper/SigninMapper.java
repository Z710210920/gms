package com.Zyuchen.gmsservice.mapper;

import com.Zyuchen.gmsservice.entity.Signin;
import com.Zyuchen.gmsservice.entity.vo.SigninTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 签到表 Mapper 接口
 * </p>
 *
 * @author Zyuchen
 * @since 2021-05-09
 */
public interface SigninMapper extends BaseMapper<Signin> {
    List<SigninTable> selectSignInSituationByClassInfoId(String id);
}
