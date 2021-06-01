package com.Zyuchen.gmsservice.service.impl;

import com.Zyuchen.gmsservice.entity.Classselection;
import com.Zyuchen.gmsservice.entity.Signin;
import com.Zyuchen.gmsservice.entity.vo.SigninTable;
import com.Zyuchen.gmsservice.mapper.SigninMapper;
import com.Zyuchen.gmsservice.service.SigninService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 * 签到表 服务实现类
 * </p>
 *
 * @author Zyuchen
 * @since 2021-05-09
 */
@Service
public class SigninServiceImpl extends ServiceImpl<SigninMapper, Signin> implements SigninService {

    @Override
    public List<SigninTable> getAllSignInMessageByClassId(String classId) {
        List<SigninTable> signinList = baseMapper.selectSignInSituationByClassInfoId(classId);
        for(SigninTable st : signinList){
            QueryWrapper<Signin> wrapper = new QueryWrapper<>();
            wrapper.eq("classselection", st.getClassselectionId());
            st.setSignIn(this.list(wrapper));
        }
        return signinList;
    }
}
