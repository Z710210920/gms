package com.Zyuchen.gmsservice.entity.excel;

import com.Zyuchen.gmsservice.entity.Course;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CourseData {

    @ExcelProperty(index = 0)
    private String name;

    @ExcelProperty(index = 1)
    @ApiModelProperty(value = "0，热身 1，拉伸放松 2，柔韧性 3，减脂 4，塑形 5，增肌")
    private String target;

    @ExcelProperty(index = 2)
    @ApiModelProperty(value = "0，全身 1，腹部 2，腿部 3，腰部 4，背部 5，胸部 6，手臂 7，肩部 8，臀部 9，颈部 10，脸部")
    private String part;

    @ExcelProperty(index = 3)
    @ApiModelProperty(value = "0，零基础 1，初学 2，进阶 3，强化 4，挑战")
    private String difficulty;

    @ExcelProperty(index = 4)
    @ApiModelProperty(value = "以分钟记")
    private String duration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Course toCourse(){
        Course course = new Course();
        course.setName(this.name);
        course.setDifficulty(Integer.parseInt(this.difficulty));
        course.setDuration(Integer.parseInt(this.duration));
        course.setPart(Integer.parseInt(this.part));
        course.setTarget(Integer.parseInt(this.target));

        return course;
    }
}
