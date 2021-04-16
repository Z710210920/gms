package com.Zyuchen.gmsservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "课时信息")
@Data
public class ClassVideoVo {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Boolean free;
    @ApiModelProperty(value = "云服务器上存储的视频文件名称")
    private String videoOriginalName;
}
