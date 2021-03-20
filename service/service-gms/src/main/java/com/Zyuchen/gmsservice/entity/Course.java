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
 * 课程名称表
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Course对象", description="课程名称表")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "courseId", type = IdType.ID_WORKER_STR)
    private String courseId;

    private String name;

    @ApiModelProperty(value = "0，热身 1，拉伸放松 2，柔韧性 3，减脂 4，塑形 5，增肌")
    private Integer target;

    @ApiModelProperty(value = "0，全身 1，腹部 2，腿部 3，腰部 4，背部 5，胸部 6，手臂 7，肩部 8，臀部 9，颈部 10，脸部")
    private Integer part;

    @ApiModelProperty(value = "0，零基础 1，初学 2，进阶 3，强化 4，挑战")
    private Integer difficulty;

    @ApiModelProperty(value = "以分钟记")
    private Integer duration;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createtime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private Date modifiedtime;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer isdeleted;

    public boolean reasonable(){
        return this.getDifficulty() >= 0 && this.getDifficulty() <= 4 &&
                this.getPart() >= 0 && this.getPart() <= 10 &&
                this.getTarget() >= 0 && this.getTarget() <= 5;
    }
}
