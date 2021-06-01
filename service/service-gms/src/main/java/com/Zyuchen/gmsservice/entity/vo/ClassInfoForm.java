package com.Zyuchen.gmsservice.entity.vo;

import com.Zyuchen.gmsservice.entity.ClassInfo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value="Class对象", description="课程表")
public class ClassInfoForm implements Serializable {

    private Date classBeginTime;

    private String title;

    private String coachId;

    private Integer classPrice;

    private String classRoomId;

    private Integer currentTimes;

    private String equipmentRecordId;

    private Integer classTimes;

    private String cover;

    private String status;

    private String description;

    private String courseId;
}
