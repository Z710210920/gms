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
 *
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ClassVideo对象", description="")
public class ClassVideo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "视频ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "课程ID")
    @TableField("classId")
    private String classId;

    @ApiModelProperty(value = "章节ID")
    @TableField("chapterId")
    private String chapterId;

    @ApiModelProperty(value = "节点名称")
    private String title;

    @ApiModelProperty(value = "云端视频资源")
    @TableField("videoSourceId")
    private String videoSourceId;

    @ApiModelProperty(value = "原始文件名称")
    @TableField("videoOriginalName")
    private String videoOriginalName;

    @ApiModelProperty(value = "播放次数")
    @TableField("playCount")
    private Long playCount;

    @ApiModelProperty(value = "是否可以试听：0收费 1免费")
    @TableField("isFree")
    private Integer isFree;

    private Float duration;

    @ApiModelProperty(value = "Empty未上传 Transcoding转码中  Normal正常")
    private String status;

    @ApiModelProperty(value = "视频源文件大小（字节）")
    private Long size;

    @ApiModelProperty(value = "乐观锁")
    private Long version;

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
