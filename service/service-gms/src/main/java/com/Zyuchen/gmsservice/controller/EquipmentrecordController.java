package com.Zyuchen.gmsservice.controller;


import com.Zyuchen.common.utils.R;
import com.Zyuchen.gmsservice.entity.Coach;
import com.Zyuchen.gmsservice.entity.Equipment;
import com.Zyuchen.gmsservice.entity.Equipmentrecord;
import com.Zyuchen.gmsservice.entity.User;
import com.Zyuchen.gmsservice.entity.vo.EquipmentQuery;
import com.Zyuchen.gmsservice.entity.vo.EquipmentrecordQuery;
import com.Zyuchen.gmsservice.entity.vo.EquipmentrecordVO;
import com.Zyuchen.gmsservice.service.CoachService;
import com.Zyuchen.gmsservice.service.EquipmentService;
import com.Zyuchen.gmsservice.service.EquipmentrecordService;
import com.Zyuchen.gmsservice.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 器材借用记录 前端控制器
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-15
 */
@RestController
@RequestMapping("/gmsservice/equipmentrecord")
@CrossOrigin
@Api(value="设备记录", tags={"设备记录接口"})
public class EquipmentrecordController {
    @Autowired
    private EquipmentrecordService equipmentrecordService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private CoachService coachService;
    @Autowired
    private UserService userService;

    @ApiOperation(value="获取所有设备记录")
    @GetMapping("/findAll")
    public R findAllCoach(){
        List<Equipmentrecord> list = equipmentrecordService.list(null);
        List<EquipmentrecordVO> finallist = this.joinNameToEquipmentRecord(list);
        return R.ok().data("item", finallist);
    }

    @ApiOperation(value="根据ID删除记录")
    @DeleteMapping("delete/{id}")
    public R removeEquipmentrecordById(
            @ApiParam(name = "id", value = "设备ID", required = true)
            @PathVariable String id){
        boolean flag = equipmentrecordService.removeById(id);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }

    @GetMapping("pageEquipmentrecord/{current}/{limit}")
    @ApiOperation("分页查询")
    public R pageListCoach(@PathVariable @ApiParam("当前页") long current,
                           @PathVariable @ApiParam("每页记录数") long limit){
        //创建page对象
        Page<Equipmentrecord> pageEquipmentrecord = new Page<>(current,limit);
        //分页数据储存在pageTeacher对象里面
        equipmentrecordService.page(pageEquipmentrecord, null);

        long total =  pageEquipmentrecord.getTotal();
        List<Equipmentrecord> records = pageEquipmentrecord.getRecords();
        List<EquipmentrecordVO> finallist = this.joinNameToEquipmentRecord(records);
        return R.ok().data("total", total).data("item", finallist);
    }

    @PostMapping("pageEquipmentrecordCondition/{current}/{limit}")
    @ApiOperation("分页条件查询")
    public R pageListEquipmentrecordCondition(@PathVariable @ApiParam("当前页") long current,
                                    @PathVariable @ApiParam("每页记录数") long limit,
                                    @ApiParam("查询条件")
                                    @RequestBody(required = false)
                                            EquipmentrecordQuery equipmentrecordQuery){
        Page<Equipmentrecord> pageEquipmentrecord = new Page<>(current,limit);
        QueryWrapper<Equipmentrecord> wrapper = new QueryWrapper<>();

        String equipmentId = equipmentrecordQuery.getEquipmentId();
        String begin = equipmentrecordQuery.getBegin();
        String end = equipmentrecordQuery.getEnd();
        String operator = equipmentrecordQuery.getOperator();
        Integer operatorType = equipmentrecordQuery.getOperatorType();
        Integer recordType = equipmentrecordQuery.getRecordType();

        //分页数据储存在pageEquipmentrecord对象里面
        if(!StringUtils.isEmpty(equipmentId)){
            wrapper.eq("equipmentId",equipmentId);
        }
        if(!StringUtils.isEmpty(operator)){
            wrapper.eq("operator",operator);
        }
        if(!StringUtils.isEmpty(operatorType)){
            wrapper.eq("operatorType",operatorType);
        }
        if(!StringUtils.isEmpty(recordType)){
            wrapper.eq("recordType",recordType);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("createtime",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("createtime",end);
        }
        equipmentrecordService.page(pageEquipmentrecord, wrapper);
        List<Equipmentrecord> records = pageEquipmentrecord.getRecords();
        long total = pageEquipmentrecord.getTotal();

        List<EquipmentrecordVO> finallist = this.joinNameToEquipmentRecord(records);
        return R.ok().data("total", total).data("item", finallist);
    }

    @ApiOperation(value = "根据ID查询设备")
    @GetMapping("get/{id}")
    public R getById(
            @ApiParam(name = "id", value = "设备ID", required = true)
            @PathVariable String id){
        Equipmentrecord equipmentrecord = equipmentrecordService.getById(id);/*
        List<EquipmentrecordVO> finallist = this.joinNameToEquipmentRecord(records);*/
        return R.ok().data("item", equipmentrecord);
    }

    public List<EquipmentrecordVO> joinNameToEquipmentRecord(List<Equipmentrecord> list){
        List<EquipmentrecordVO> finallist = new ArrayList<>();
        for(Equipmentrecord ev : list){
            EquipmentrecordVO evo = new EquipmentrecordVO(ev);
            Equipment equipment = equipmentService.getById(evo.getEquipmentId());
            evo.setEquipmentName(equipment.getEquipmentName());
            String operatorName;
            if(evo.getOperatorType() == 0){
                operatorName = "管理员";
            }else if(evo.getOperatorType() == 1){
                Coach coach = coachService.getById(evo.getOperator());
                operatorName = coach.getCoachName();
            }else{
                User user = userService.getById(evo.getOperator());
                operatorName = user.getUserName();
            }
            evo.setOperatorName(operatorName);
            finallist.add(evo);
        }
        return finallist;
    }

    @ApiOperation(value="根据ID修改设备")
    @PutMapping("update/{id}")
    public R updateById(
            @ApiParam(name = "id", value = "设备ID", required = true)
            @PathVariable String id,
            @ApiParam(name = "equipmentrecord", value = "设备记录对象", required = true)
            @RequestBody Equipmentrecord equipmentrecord){
        equipmentrecord.setEquipmentRecordId(id);
        System.out.println(equipmentrecord.toString());
        equipmentrecordService.updateById(equipmentrecord);
        return R.ok();
    }

    @ApiOperation(value="根据ID归还设备")
    @GetMapping("returnEquipment/{id}")
    public R returnEquipmentById(
            @ApiParam(name = "id", value = "设备记录ID", required = true)
            @PathVariable String id){
        System.out.println(id);
        Equipmentrecord equipmentrecord = equipmentrecordService.getById(id);
        equipmentrecord.setCreatetime(null);
        equipmentrecord.setModifiedtime(null);
        equipmentrecord.setIsReturn(1);
        equipmentrecordService.updateById(equipmentrecord);
        return R.ok();
    }

    @ApiOperation(value = "设备借用")
    @PostMapping("borrowEquipmentrecord")
    public R borrowEquipmentrecord(
            @ApiParam(name = "equipmentrecord", value = "设备记录对象", required = true)
            @RequestBody Equipmentrecord equipmentrecord){
        equipmentrecord.setRecordType(3);
        equipmentrecordService.save(equipmentrecord);
        return R.ok();
    }

}

