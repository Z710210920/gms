package com.Zyuchen.gmsservice.service.impl;

import com.Zyuchen.common.Exception.DefinedException;
import com.Zyuchen.common.utils.R;
import com.Zyuchen.gmsservice.entity.AccountBook;
import com.Zyuchen.gmsservice.entity.Membershipcard;
import com.Zyuchen.gmsservice.mapper.AccountBookMapper;
import com.Zyuchen.gmsservice.service.AccountBookService;
import com.Zyuchen.gmsservice.service.MembershipcardService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Wrapper;

/**
 * <p>
 * 账簿 服务实现类
 * </p>
 *
 * @author Zyuchen
 * @since 2021-05-11
 */
@Service
public class AccountBookServiceImpl extends ServiceImpl<AccountBookMapper, AccountBook> implements AccountBookService {

    @Autowired
    private MembershipcardService membershipcardService;
    @Override
    public void deductions(String userId, BigDecimal cost, int type) {
        QueryWrapper<Membershipcard> wrapper = new  QueryWrapper<Membershipcard>();
        wrapper.eq("ownerId", userId);
        Membershipcard mc = membershipcardService.getOne(wrapper);
        /*if(mc == null){
            mc = new Membershipcard();
            mc.setOwnerId(userId);
            membershipcardService.save(mc);
        }*/
        if(mc.getBalance().compareTo(cost) == -1){
            throw new DefinedException(20001, "账户余额不足，请充值！");
        }
        AccountBook ab = new AccountBook();
        ab.setUserId(userId);
        ab.setAmount(cost);
        ab.setType(type);
        mc.setBalance(mc.getBalance().subtract(cost));
        membershipcardService.updateById(mc);
        this.save(ab);
    }
}
