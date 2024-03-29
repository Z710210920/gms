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
 * 教练信息表
 * </p>
 *
 * @author Zyuchen
 * @since 2021-02-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Coach对象", description="教练信息表")
public class Coach implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "coachId", type = IdType.ID_WORKER_STR)
    private String coachId;

    @TableField("coachName")
    private String coachName;

    @TableField("coachRealName")
    private String coachRealName;

    @TableField("coachPhoneNumber")
    private String coachPhoneNumber;

    @TableField("coachIdentityNumber")
    private String coachIdentityNumber;

    @TableField("level")
    private Integer level;

    @TableField("coachPassword")
    private String coachPassword;

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

    @TableField("intro")
    private String intro;

    @TableField("avatar")
    private String avatar;
}
