package com.Zyuchen.gmsservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CourseQuery {
    @ApiModelProperty(value="课程名称，模糊查询")
    private String name;

    @ApiModelProperty(value = "0，热身 1，拉伸放松 2，柔韧性 3，减脂 4，塑形 5，增肌")
    private Integer target;

    @ApiModelProperty(value = "0，全身 1，腹部 2，腿部 3，腰部 4，背部 5，胸部 6，手臂 7，肩部 8，臀部 9，颈部 10，脸部")
    private Integer part;

    @ApiModelProperty(value = "0，零基础 1，初学 2，进阶 3，强化 4，挑战")
    private Integer difficulty;

    @ApiModelProperty(value = "以分钟记")
    private Integer duration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public Integer getPart() {
        return part;
    }

    public void setPart(Integer part) {
        this.part = part;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
