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
 * 设备登记表
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Equipment对象", description="设备登记表")
public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "equipmentId", type = IdType.ID_WORKER_STR)
    private String equipmentId;

    @TableField("equipmentName")
    private String equipmentName;

    @TableField("equipmentTotalNumber")
    private Integer equipmentTotalNumber;

    @TableField("equipmentLeftNumber")
    private Integer equipmentLeftNumber;

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
