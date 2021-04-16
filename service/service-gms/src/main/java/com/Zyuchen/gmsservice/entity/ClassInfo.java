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
 * 课程表
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Class对象", description="课程表")
public class ClassInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String COURSE_DRAFT = "Draft";
    public static final String COURSE_NORMAL = "Normal";

    @TableId(value = "classId", type = IdType.ID_WORKER_STR)
    private String classId;

    @TableField("classBeginTime")
    private Date classBeginTime;

    @TableField("coachId")
    private String coachId;

    @TableField("title")
    private String title;

    @TableField("courseId")
    private String courseId;

    @TableField("classPrice")
    private Integer classPrice;

    @TableField("classRoomId")
    private String classRoomId;

    @TableField("equipmentRecordId")
    private String equipmentRecordId;

    @ApiModelProperty(value = "总课时")
    @TableField("classTimes")
    private Integer classTimes;

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

    @ApiModelProperty(value = "课程封面")
    private String cover;

    @ApiModelProperty(value = "销售总量")
    @TableField("buyCount")
    private Long buyCount;

    @ApiModelProperty(value = "浏览量")
    @TableField("viewCount")
    private Long viewCount;

    @ApiModelProperty(value = "乐观锁")
    private Long version;

    @ApiModelProperty(value = "课程状态 Draft未发布  Normal已发布")
    private String status;


}
