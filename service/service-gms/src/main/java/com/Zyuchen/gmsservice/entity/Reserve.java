package com.Zyuchen.gmsservice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 预约表
 * </p>
 *
 * @author Zyuchen
 * @since 2021-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Reserve对象", description="预约表")
public class Reserve implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "reserveId", type = IdType.ID_WORKER_STR)
    private String reserveId;

    @TableField("coachId")
    private String coachId;

    @TableField("note")
    private String note;

    @TableField("userId")
    private String userId;

    @TableField("reserveTime")
    private Date reserveTime;

    @ApiModelProperty(value = "0.拒绝预约 1.预约但未确认 2.确认但未执行 3.执行中 4.执行完成 5.评价完成")
    @TableField("reserveState")
    private Integer reserveState;

    private Integer duration;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createtime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private Date modifiedtime;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Boolean isdeleted;


}
