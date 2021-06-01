package com.Zyuchen.gmsservice.controller;


import com.Zyuchen.common.utils.R;
import com.Zyuchen.gmsservice.entity.AccountBook;
import com.Zyuchen.gmsservice.entity.Membershipcard;
import com.Zyuchen.gmsservice.entity.Reserve;
import com.Zyuchen.gmsservice.entity.vo.ReserveQuery;
import com.Zyuchen.gmsservice.entity.vo.ReserveVO;
import com.Zyuchen.gmsservice.service.AccountBookService;
import com.Zyuchen.gmsservice.service.MembershipcardService;
import com.Zyuchen.gmsservice.service.ReserveService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 预约表 前端控制器
 * </p>
 *
 * @author Zyuchen
 * @since 2021-05-27
 */
@RestController
@RequestMapping("/gmsservice/reserve")
@Api(value="预约管理", tags={"预约管理接口"})
@CrossOrigin
public class ReserveController {
    @Autowired
    private ReserveService reserveService;

    @Autowired
    private AccountBookService accountBookService;

    @Autowired
    private MembershipcardService membershipcardService;

    @ApiOperation("用户预约")
    @PostMapping("/add")
    public R add(@RequestBody @ApiParam("add") Reserve reserve){
        accountBookService.deductions(reserve.getUserId(), BigDecimal.valueOf(Math.ceil(reserve.getDuration()*1.0/30)*100), 2);
        reserveService.save(reserve);
        return R.ok();
    }

    @ApiOperation("教练拒绝预约")
    @PutMapping("/refused/{id}")
    public R refused(@ApiParam(name = "id", value = "预约单号", required = true)
                         @PathVariable String id){
        Reserve reserve = reserveService.getById(id);
        reserve.setReserveState(0);
        reserveService.updateById(reserve);
        accountBookService.deductions(reserve.getUserId(), BigDecimal.valueOf(Math.ceil(reserve.getDuration()*1.0/30)*(-100)), 4);

        return R.ok();
    }

    @ApiOperation("用户取消预约")
    @DeleteMapping("/cancel/{id}")
    public R cancel(@ApiParam(name = "id", value = "预约单号", required = true)
                     @PathVariable String id){
        Reserve reserve = reserveService.getById(id);
        accountBookService.deductions(reserve.getUserId(), BigDecimal.valueOf(Math.ceil(reserve.getDuration()*1.0/30)*(-100)), 4);
        reserveService.removeById(id);

        return R.ok();
    }

    @ApiOperation("预约更新状态")
    @PutMapping("/update/{id}")
    public R update(@ApiParam(name = "id", value = "预约单号", required = true)
                    @PathVariable String id){
        Reserve reserve = reserveService.getById(id);
        reserve.setReserveState(reserve.getReserveState()+1);
        reserveService.updateById(reserve);

        return R.ok();
    }

    @PostMapping("pageReserveCondition/{current}/{limit}")
    @ApiOperation("分页条件查询")
    public R pageListReserveCondition(@PathVariable @ApiParam("当前页") long current,
                                    @PathVariable @ApiParam("每页记录数") long limit,
                                    @ApiParam("查询条件")
                                    @RequestBody(required = false)
                                              ReserveQuery reserveQuery){
        QueryWrapper<ReserveVO> wrapper = new QueryWrapper<>();
        String coachPhoneNumber = reserveQuery.getCoachPhoneNumber();
        String userPhoneNumber = reserveQuery.getUserPhoneNumber();
        Integer reserveState = reserveQuery.getReserveState();
        if(!StringUtils.isEmpty(coachPhoneNumber)){
            wrapper.eq("coachPhoneNumber",coachPhoneNumber);
        }
        if(!StringUtils.isEmpty(userPhoneNumber)){
            wrapper.eq("userPhoneNumber",userPhoneNumber);
        }
        if(!StringUtils.isEmpty(reserveState)){
            wrapper.eq("reserveState",reserveState);
        }
        //分页数据储存在pageTeacher对象里面
        List<ReserveVO> list = reserveService.selectReserveVo(wrapper);


        long total =  list.size();

        return R.ok().data("total", total).data("item", list);
    }
}

