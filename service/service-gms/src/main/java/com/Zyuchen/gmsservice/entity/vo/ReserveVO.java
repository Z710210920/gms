package com.Zyuchen.gmsservice.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(value="Reserve对象", description="预约表")
public class ReserveVO{
    private String reserveId;

    private String coachName;

    private String coachId;

    private String note;

    private String userName;
    private BigDecimal price;
    private String userId;
    private String accountBookId;
    private Date reserveTime;

    private Integer reserveState;

    private Integer duration;
}
