package com.Zyuchen.gmsservice.controller;


import com.Zyuchen.common.utils.R;
import com.Zyuchen.gmsservice.entity.AccountBook;
import com.Zyuchen.gmsservice.entity.User;
import com.Zyuchen.gmsservice.entity.vo.AccountBookQuery;
import com.Zyuchen.gmsservice.entity.vo.AccountBookVO;
import com.Zyuchen.gmsservice.entity.vo.UserQuery;
import com.Zyuchen.gmsservice.entity.vo.UserVO;
import com.Zyuchen.gmsservice.service.AccountBookService;
import com.Zyuchen.gmsservice.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 账簿 前端控制器
 * </p>
 *
 * @author Zyuchen
 * @since 2021-05-11
 */
@RestController
@RequestMapping("/gmsservice/account-book")
@Api(value="账单管理", tags={"账单管理接口"})
@CrossOrigin
public class AccountBookController {

    @Autowired
    private AccountBookService accountBookService;

    @Autowired
    private UserService userService;

    @PostMapping("pageUserCondition/{current}/{limit}")
    @ApiOperation("用户账单分页条件查询")
    public R pageListUserCondition(@PathVariable @ApiParam("当前页") long current,
                                   @PathVariable @ApiParam("每页记录数") long limit,
                                   @ApiParam("查询条件")
                                   @RequestBody(required = false)
                                           AccountBookQuery accountBookQuery){
        //创建page对象
Page<AccountBook> page = new Page<>(current,limit);
QueryWrapper<AccountBook> wrapper = new QueryWrapper<>();
String userId = accountBookQuery.getUserId();
Integer type = accountBookQuery.getType();
if(!StringUtils.isEmpty(userId)){
    wrapper.eq("userId",userId);
}
if(!StringUtils.isEmpty(type) && type != -1){
    wrapper.like("type",type);
}
wrapper.orderByDesc("createtime");
//分页数据储存在pageTeacher对象里面
accountBookService.page(page, wrapper);


        long total =  page.getTotal();
        List<AccountBook> records = page.getRecords();
        List<AccountBookVO> abolist = new ArrayList<>();
        for(AccountBook ab : records){
            AccountBookVO abo = new AccountBookVO();
            BeanUtils.copyProperties(ab, abo);
            User user = userService.getById(ab.getUserId());
            if(user != null){
                abo.setUserName(user.getUserName());
            }
            abolist.add(abo);
        }

        return R.ok().data("total", total).data("item", abolist);
    }

    @PostMapping("pageCoachCondition/{current}/{limit}")
    @ApiOperation("教练分页条件查询")
    public R pageListCoahCondition(@PathVariable @ApiParam("当前页") long current,
                                   @PathVariable @ApiParam("每页记录数") long limit,
                                   @ApiParam("查询条件")
                                   @RequestBody(required = false)
                                           AccountBookQuery accountBookQuery){
        //创建page对象
        Page<AccountBookVO> page = new Page<>(current,limit);
        QueryWrapper<AccountBookVO> wrapper = new QueryWrapper<>();
        QueryWrapper<AccountBookVO> wrapper1 = new QueryWrapper<>();
        String coachId = accountBookQuery.getCoachId();
        Integer type = accountBookQuery.getType();
        if(!StringUtils.isEmpty(coachId)){
            wrapper.eq("coach.coachId",coachId);
            wrapper1.eq("coach.coachId",coachId);
        }
        if(!StringUtils.isEmpty(type) && type != -1){
            wrapper.like("type",type);
            wrapper1.like("type",type);
        }
        wrapper.orderByDesc("createtime");
        wrapper1.orderByDesc("createtime");
        //分页数据储存在pageTeacher对象里面
        page = accountBookService.VOpageByReserve(page, wrapper);
        Page<AccountBookVO> page1 = new Page<>(current, limit);
        page1 = accountBookService.VOpageByClassSelection(page1, wrapper1);


        long total =  page.getTotal();
        total += page1.getTotal();
        List<AccountBookVO> records = page.getRecords();
        records.addAll(page1.getRecords());

        return R.ok().data("total", total).data("item", records);
    }
}

