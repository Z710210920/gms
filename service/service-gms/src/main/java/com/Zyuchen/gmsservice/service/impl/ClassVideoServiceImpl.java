package com.Zyuchen.gmsservice.service.impl;

import com.Zyuchen.common.Exception.DefinedException;
import com.Zyuchen.gmsservice.client.VodClient;
import com.Zyuchen.gmsservice.entity.ClassVideo;
import com.Zyuchen.gmsservice.entity.vo.ClassVideoForm;
import com.Zyuchen.gmsservice.mapper.ClassVideoMapper;
import com.Zyuchen.gmsservice.service.ClassVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-22
 */
@Service
public class ClassVideoServiceImpl extends ServiceImpl<ClassVideoMapper, ClassVideo> implements ClassVideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    public boolean getCountByClassChapterId(String chapterId) {
        QueryWrapper<ClassVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapterId", chapterId);
        Integer count = baseMapper.selectCount(queryWrapper);
        return null != count && count > 0;
    }

    @Override
    public void saveVideoInfo(ClassVideoForm classvideoForm) {

        ClassVideo classvideo = new ClassVideo();
        BeanUtils.copyProperties(classvideoForm, classvideo);
        boolean result = this.save(classvideo);

        if(!result){
            throw new DefinedException(20001, "课时信息保存失败");
        }
    }

    @Override
    public ClassVideoForm getClassVideoFormById(String id) {
        //从video表中取数据
        ClassVideo classvideo = this.getById(id);
        if(classvideo == null){
            throw new DefinedException(20001, "数据不存在");
        }

        //创建videoInfoForm对象
        ClassVideoForm classVideoForm = new ClassVideoForm();
        BeanUtils.copyProperties(classvideo, classVideoForm);
        this.setIsFree(classVideoForm);
        return classVideoForm;
    }

    @Override
    public boolean removeVideoById(String id) {
        //删除视频资源
        //查询云端视频id
        ClassVideo video = baseMapper.selectById(id);
        String videoSourceId = video.getVideoSourceId();
        //删除视频资源
        if(!StringUtils.isEmpty(videoSourceId)){
            vodClient.removeVideo(videoSourceId);
        }

        Integer result = baseMapper.deleteById(id);
        return null != result && result > 0;
    }

    @Override
    public boolean removeClassInfoById(String classId) {
        QueryWrapper<ClassVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("classId", classId);
        queryWrapper.select("videoSourceId");
        List<ClassVideo> videoList = baseMapper.selectList(queryWrapper);

        //得到所有视频列表的云端原始视频id
        List<String> videoSourceIdList = new ArrayList<>();
        for (int i = 0; i < videoList.size(); i++) {
            ClassVideo classVideo = videoList.get(i);
            String videoSourceId = classVideo.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)){
                videoSourceIdList.add(videoSourceId);
            }
        }

        //调用vod服务删除远程视频
        if(videoSourceIdList.size() > 0){
            vodClient.removeVideoList(videoSourceIdList);
        }

        QueryWrapper<ClassVideo> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("classId", classId);
        Integer count = baseMapper.delete(queryWrapper2);
        return null != count && count > 0;
    }

    @Override
    public void setIsFree(ClassVideoForm videoInfoForm) {
        if (videoInfoForm.getFree() == null){
            videoInfoForm.setFree(videoInfoForm.getIsFree() == 1);
        }else{
            if (videoInfoForm.getFree()){
                videoInfoForm.setIsFree(1);
            }else{
                videoInfoForm.setIsFree(0);
            }
        }
    }

    @Override
    public void updateVideoInfoById(ClassVideoForm videoInfoForm) {
        //保存课时基本信息
        ClassVideo classVideo = new ClassVideo();
        BeanUtils.copyProperties(videoInfoForm, classVideo);
        boolean result = this.updateById(classVideo);
        if(!result){
            throw new DefinedException(20001, "课时信息保存失败");
        }
    }
}
