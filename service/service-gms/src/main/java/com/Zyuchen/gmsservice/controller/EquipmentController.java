package com.Zyuchen.gmsservice.controller;


import com.Zyuchen.common.utils.R;
import com.Zyuchen.gmsservice.entity.Coach;
import com.Zyuchen.gmsservice.entity.Equipment;
import com.Zyuchen.gmsservice.entity.Equipmentrecord;
import com.Zyuchen.gmsservice.entity.User;
import com.Zyuchen.gmsservice.entity.vo.EquipmentQuery;
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
import java.util.List;

/**
 * <p>
 * 设备登记表 前端控制器
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-15
 */
@RestController
@Api(value="设备管理", tags={"设备管理接口"})
@RequestMapping("/gmsservice/equipment")
@CrossOrigin
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EquipmentrecordService equipmentrecordService;

    @ApiOperation(value="获取所有设备")
    @GetMapping("/findAll")
    public R findAllCoach(){
        List<Equipment> list = equipmentService.list(null);

        return R.ok().data("item", list);
    }

    @ApiOperation(value="根据ID删除设备")
    @DeleteMapping("delete/{id}")
    public R removeEquipmentById(
            @ApiParam(name = "id", value = "设备ID", required = true)
            @PathVariable String id){
        boolean flag = equipmentService.removeById(id);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }

    @GetMapping("pageEquipment/{current}/{limit}")
    @ApiOperation("分页查询")
    public R pageListCoach(@PathVariable @ApiParam("当前页") long current,
                           @PathVariable @ApiParam("每页记录数") long limit){
        //创建page对象
        Page<Equipment> pageEquipment = new Page<>(current,limit);
        //分页数据储存在pageTeacher对象里面
        equipmentService.page(pageEquipment, null);

        long total =  pageEquipment.getTotal();
        List<Equipment> records = pageEquipment.getRecords();

        /*Map map = new HashMap();
        map.put("total", total);
        map.put("rows", records);
        return R.ok().data(map);*/

        return R.ok().data("total", total).data("item", records);
    }

    @PostMapping("pageEquipmentCondition/{current}/{limit}")
    @ApiOperation("分页条件查询")
    public R pageListCoachCondition(@PathVariable @ApiParam("当前页") long current,
                                    @PathVariable @ApiParam("每页记录数") long limit,
                                    @ApiParam("查询条件")
                                    @RequestBody(required = false)
                                            EquipmentQuery equipmentQuery){
        Page<Equipment> pageEquipment = new Page<>(current,limit);
        QueryWrapper<Equipment> wrapper = new QueryWrapper<>();

        String equipmentName = equipmentQuery.getEquipmentName();
        boolean equipmentLeftNumber = equipmentQuery.getEquipmentLeftNumber();
        //分页数据储存在pageEquipment对象里面
        if(!StringUtils.isEmpty(equipmentName)){
            wrapper.like("equipmentName",equipmentName);
        }
        if(!equipmentLeftNumber){
            wrapper.eq("equipmentLeftNumber", 0);
        }
        equipmentService.page(pageEquipment, wrapper);
        List<Equipment> records = pageEquipment.getRecords();
        long total = pageEquipment.getTotal();

        return R.ok().data("total", total).data("item", records);
    }

    //添加设备种类
    //通过获取上传的excel表，吧文件内容读取出来
    @ApiOperation(value="使用excel表添加设备内容")
    @PostMapping("/addByExcel")
    public R addEquipmentByExcel(MultipartFile file){
        List<Equipment> list = new ArrayList<>();
        equipmentService.addEquipmentByExcel(file, equipmentService, equipmentrecordService, list);

        return R.ok().data("item",list);
    }

    @ApiOperation(value = "新增设备")
    @PostMapping("save")
    public R save(
            @ApiParam(name = "equipment", value = "设备对象", required = true)
            @RequestBody Equipment equipment){
        equipmentService.save(equipment);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询设备")
    @GetMapping("get/{id}")
    public R getById(
            @ApiParam(name = "id", value = "设备ID", required = true)
            @PathVariable String id){
        Equipment equipment = equipmentService.getById(id);
        return R.ok().data("item", equipment);
    }

    @ApiOperation(value="根据ID修改设备")
    @PutMapping("update/{id}")
    public R updateById(
            @ApiParam(name = "id", value = "设备ID", required = true)
            @PathVariable String id,
            @ApiParam(name = "equipment", value = "设备对象", required = true)
            @RequestBody Equipment equipment){
        equipment.setEquipmentId(id);
        System.out.println(equipment.toString());
        equipmentService.updateById(equipment);
        return R.ok();
    }
}

