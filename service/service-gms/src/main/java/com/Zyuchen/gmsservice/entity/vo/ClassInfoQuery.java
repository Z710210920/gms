package com.Zyuchen.gmsservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "Course查询对象", description = "课程查询对象封装")
@Data
public class ClassInfoQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String COURSE_DRAFT = "Draft";
    public static final String COURSE_NORMAL = "Normal";

    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "讲师id")
    private String coachId;

    @ApiModelProperty(value = "一级类别id")
    private String courseId;

    private String userId;

    private String status;
}
