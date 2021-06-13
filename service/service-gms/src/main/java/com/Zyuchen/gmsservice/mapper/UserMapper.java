package com.Zyuchen.gmsservice.mapper;

import com.Zyuchen.gmsservice.entity.User;
import com.Zyuchen.gmsservice.entity.vo.UserBalanceMessage;
import com.Zyuchen.gmsservice.entity.vo.UserVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-16
 */
public interface UserMapper extends BaseMapper<User> {
    List<UserBalanceMessage> selectBalanceMessage(String userPhoneNumber);

    IPage<UserVO> pageListUserCondition(Page<UserVO> pageUser, @Param(Constants.WRAPPER) QueryWrapper<UserVO> wrapper);

    UserVO getUserVOById(String id);
}
