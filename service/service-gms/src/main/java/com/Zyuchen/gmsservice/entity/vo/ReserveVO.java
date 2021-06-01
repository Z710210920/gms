package com.Zyuchen.gmsservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value="Reserve对象", description="预约表")
public class ReserveVO{
    private String reserveId;

    private String coachName;

    private String coachId;

    private String note;

    private String userName;

    private String userId;

    private Date reserveTime;

    private Integer reserveState;

    private Integer duration;
}
