package com.Zyuchen.gmsservice.entity;

import java.math.BigDecimal;

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
 *
 * </p>
 *
 * @author Zyuchen
 * @since 2021-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Membershipcard对象", description="")
public class Membershipcard implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "健身卡ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "用户ID")
    @TableField("ownerId")
    private String ownerId;

    @ApiModelProperty(value = "余额")
    private BigDecimal balance;


    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "会员卡有效期,插入时自动设置时间")
    private Date deadline;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createtime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private Date modifiedtime;

    @ApiModelProperty(value = "逻辑删除")
    @TableField("isDeleted")
    @TableLogic
    private Boolean isDeleted;


}
