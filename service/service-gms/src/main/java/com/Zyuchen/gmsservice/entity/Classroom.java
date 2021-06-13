package com.Zyuchen.gmsservice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 教室
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Classroom对象", description="教室")
public class Classroom implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "classRoomId", type = IdType.ID_WORKER_STR)
    private String classRoomId;

    @TableField("classRoomName")
    private String classRoomName;

    @ApiModelProperty(value = "最大人数，默认100人")
    private Integer maximum;

    private Integer current;

    private Boolean inService;

    @ApiModelProperty(value = "教室介绍")
    private String intro;

    @ApiModelProperty(value = "教室图片")
    private String picture;

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
