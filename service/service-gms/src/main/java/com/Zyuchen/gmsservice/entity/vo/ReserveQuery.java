package com.Zyuchen.gmsservice.entity.vo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value="Reserve对象", description="预约表")
public class ReserveQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("coachId")
    private String coachPhoneNumber;

    @TableField("userId")
    private String userPhoneNumber;

    @ApiModelProperty(value = "-1.拒绝预约 0.预约但未确认 1.确认但未执行 2.执行中 3.执行完成 4.已评价")
    @TableField("reserveState")
    private Integer reserveState;
}
