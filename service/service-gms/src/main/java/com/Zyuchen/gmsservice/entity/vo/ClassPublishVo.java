package com.Zyuchen.gmsservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "课程发布信息")
@Data
public class ClassPublishVo {
    private static final long serialVersionUID = 1L;

    private String title;
    private String cover;
    private Integer classTimes;
    private String courseName;
    private String coachName;
    private String price;//只用于显示
}
