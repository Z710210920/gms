package com.Zyuchen.gmsservice.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ClassInfoVO{
    private String classId;
    private Date classBeginTime;
    private String coachId;
    private String coachName;
    private Integer currentTimes;
    private String title;
    private String courseId;
    private String courseName;
    private Integer classPrice;
    private Integer target;
    private Integer part;
    private Integer difficulty;
    private String classRoomId;
    private String classRoomName;
    private String equipmentRecordId;
    private Integer classTimes;
    private Date createtime;
    private Date modifiedtime;
    private Boolean isDeleted;
    private String cover;
    private Long buyCount;
    private Long viewCount;
    private Long version;
    private String status;
    private String description;
    private String coachPhoneNumber;
    private String classselectionId;
}
