package com.Zyuchen.gmsservice.controller;


import com.Zyuchen.common.Exception.DefinedException;
import com.Zyuchen.common.utils.R;
import com.Zyuchen.gmsservice.entity.Classroom;
import com.Zyuchen.gmsservice.entity.Freefitness;
import com.Zyuchen.gmsservice.entity.Membershipcard;
import com.Zyuchen.gmsservice.entity.vo.FreefitnessVO;
import com.Zyuchen.gmsservice.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 自由训练 前端控制器
 * </p>
 *
 * @author Zyuchen
 * @since 2021-06-02
 */
@RestController
@RequestMapping("/gmsservice/freefitness")
@Api(value = "自由训练列表", tags={"自由训练接口"})
@CrossOrigin
public class FreefitnessController {
    @Autowired
    private FreefitnessService freefitnessService;

    @Autowired
    private AccountBookService accountBookService;

    @Autowired
    private MembershipcardService membershipcardService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private UserService userService;

    @ApiOperation(value="获取自由训练信息")
    @PostMapping("/findAllFreeFitnessMessage/{page}/{limit}/{userId}")
    public R findAllFreeFitnessMessage(@ApiParam(name = "page", value = "当前页码", required = true)
                                  @PathVariable Long page,

                                  @ApiParam(name = "limit", value = "每页记录数", required = true)
                                  @PathVariable Long limit,

                                  @ApiParam(name = "userId", value = "课程信息", required = true)
                                  @PathVariable String userId){
        System.out.println(userId);
        Page<Freefitness> ipage = new Page<>(page, limit);
        QueryWrapper<Freefitness> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("createtime");
        if(!StringUtils.isEmpty(userId)){
            if(!userId.equals("null")){
                wrapper.eq("userId",userId);
            }
        }
        freefitnessService.page(ipage, wrapper);
        Integer num = classroomService.getById("1400440956607430657").getCurrent();
        List<FreefitnessVO> fvolist = new ArrayList<>();
        if(userId.equals("null")){
            for(Freefitness ff : ipage.getRecords()){
                FreefitnessVO fvo = new FreefitnessVO();
                BeanUtils.copyProperties(ff, fvo);
                fvo.setUserName(userService.getById(ff.getUserId()).getUserName());
                fvolist.add(fvo);
            }
            return R.ok().data("item", fvolist).data("total", ipage.getTotal()).data("now", num);
        }
        return R.ok().data("item", ipage.getRecords()).data("total", ipage.getTotal()).data("now", num);
    }

    @ApiOperation(value="开始自由训练")
    @PostMapping("/addNewFreeFitnessMessage/{userId}")
    public R addNewFreeFitnessMessage(@ApiParam(name = "userId", value = "课程信息", required = true)
                                       @PathVariable String userId){
        Freefitness ff = new Freefitness();
        Classroom cr = classroomService.getById("1400440956607430657");
        if(cr.getCurrent() > cr.getMaximum()){
            throw new DefinedException(20001, "训练厂已满员");
        }
        Classroom newcr = new Classroom();
        newcr.setCurrent(cr.getCurrent()+1);
        newcr.setClassRoomId(cr.getClassRoomId());
        classroomService.updateById(newcr);
        Membershipcard mm = membershipcardService.getOne(new QueryWrapper<Membershipcard>().eq("ownerId", userId));
        BigDecimal bd;
        if(mm.getDeadline().before(new Date())){
            bd = new BigDecimal(50);
        }else{
            bd = new BigDecimal(0);
        }
        String abId = accountBookService.deductions(userId, bd, 3);
        ff.setUserId(userId);
        ff.setAccountBookId(abId);
        freefitnessService.save(ff);

        return R.ok();
    }

    @ApiOperation(value="结束自由训练")
    @PostMapping("/stopFreeFitnessMessage/{Id}")
    public R stopFreeFitnessMessage(@ApiParam(name = "Id", value = "课程信息", required = true)
                                      @PathVariable String Id){
        Freefitness ff = freefitnessService.getOne(new QueryWrapper<Freefitness>().eq("userId", Id).orderByDesc("createtime"));
        Classroom cr = classroomService.getById("1400440956607430657");
        if(cr.getCurrent() > 0){
            Classroom newcr = new Classroom();
            newcr.setCurrent(cr.getCurrent()-1);
            newcr.setClassRoomId(cr.getClassRoomId());
            classroomService.updateById(newcr);
        }
        ff.setModifiedtime(new Date());
        ff.setNote("已结束"+new Date());
        freefitnessService.updateById(ff);

        return R.ok();
    }

}

