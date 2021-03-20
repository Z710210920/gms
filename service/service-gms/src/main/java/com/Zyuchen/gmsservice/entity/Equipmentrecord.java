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
 * 器材借用记录
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Equipmentrecord对象", description="器材借用记录")
public class Equipmentrecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "equipmentRecordId", type = IdType.ID_WORKER_STR)
    private String equipmentRecordId;

    @TableField("equipmentId")
    private String equipmentId;

    @TableField("equipmentNumber")
    private Integer equipmentNumber;

    @ApiModelProperty(value = "1，入库 2，出库 3，借用 4，归还")
    @TableField("recordType")
    private Integer recordType;

    private String operator;

    @ApiModelProperty(value = "0，管理员\n1，教练\n2，用户")
    @TableField("operatorType")
    private Integer operatorType;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createtime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private Date modifiedtime;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer isdeleted;

    @TableField("isReturn")
    @ApiModelProperty(value = "归还")
    private Integer isReturn;
}
