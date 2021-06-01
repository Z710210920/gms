package com.Zyuchen.gmsservice.controller;


import com.Zyuchen.common.utils.R;
import com.Zyuchen.gmsservice.entity.AccountBook;
import com.Zyuchen.gmsservice.entity.Membershipcard;
import com.Zyuchen.gmsservice.entity.vo.RenewalMessage;
import com.Zyuchen.gmsservice.entity.vo.TopUpMessage;
import com.Zyuchen.gmsservice.service.AccountBookService;
import com.Zyuchen.gmsservice.service.MembershipcardService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zyuchen
 * @since 2021-05-10
 */
@RestController
@RequestMapping("/gmsservice/membershipcard")
@Api(value = "会员卡", tags={"用户会员卡"})
@CrossOrigin
public class MembershipcardController {
    @Autowired
    private MembershipcardService membershipcardService;

    @Autowired
    private AccountBookService accountBookService;

    @ApiOperation("用户充值")
    @PostMapping("/top_up")
    public R top_up(@RequestBody @ApiParam("top_up") TopUpMessage topUpMessage){
        //充值
        accountBookService.deductions(topUpMessage.getUserId(), BigDecimal.ZERO.subtract(topUpMessage.getNumber()), 0);
        return R.ok();
    }

    @ApiOperation("续费会员卡")
    @PostMapping("/renewalcard")
    public R renewalcard(@RequestBody @ApiParam("renewalcard") RenewalMessage renewalMessage){
        //扣款
        long Mouth_Times = 31L * 24L * 3600L * 1000L;
        accountBookService.deductions(renewalMessage.getUserId(), renewalMessage.getTotalNumber(), 5);
        QueryWrapper<Membershipcard> wrapper = new QueryWrapper<>();
        wrapper.eq("ownerId", renewalMessage.getUserId());
        Membershipcard mc = membershipcardService.getOne(wrapper);
        Date date = new Date();
        Date newdate;
        if (mc.getDeadline().before(date)){
            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
            newdate = new Date(date.getTime() + renewalMessage.getLength()[renewalMessage.getType()] * Mouth_Times);
        }else{
            newdate = new Date(mc.getDeadline().getTime() + renewalMessage.getLength()[renewalMessage.getType()] * Mouth_Times);
        }
        mc.setDeadline(newdate);
        mc.setModifiedtime(null);
        membershipcardService.updateById(mc);
        return R.ok();
    }
}

