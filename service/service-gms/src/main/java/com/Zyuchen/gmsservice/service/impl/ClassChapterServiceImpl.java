package com.Zyuchen.gmsservice.service.impl;

import com.Zyuchen.common.Exception.DefinedException;
import com.Zyuchen.gmsservice.entity.ClassChapter;
import com.Zyuchen.gmsservice.entity.ClassVideo;
import com.Zyuchen.gmsservice.entity.vo.ClassChapterVo;
import com.Zyuchen.gmsservice.entity.vo.ClassVideoVo;
import com.Zyuchen.gmsservice.mapper.ClassChapterMapper;
import com.Zyuchen.gmsservice.service.ClassChapterService;
import com.Zyuchen.gmsservice.service.ClassVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 章节 服务实现类
 * </p>
 *
 * @author Zyuchen
 * @since 2021-03-22
 */
@Service
public class ClassChapterServiceImpl extends ServiceImpl<ClassChapterMapper, ClassChapter> implements ClassChapterService {
    @Autowired
    private ClassVideoService classvideoService;

    @Override
    public List<ClassChapterVo> nestedList(String classId) {

        //最终要的到的数据列表
        ArrayList<ClassChapterVo> classChapterVoArrayList = new ArrayList<>();

        //获取章节信息
        QueryWrapper<ClassChapter> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("classId", classId);
        List<ClassChapter> chapters = baseMapper.selectList(queryWrapper1);

        //获取课时信息
        QueryWrapper<ClassVideo> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("classId", classId);
        List<ClassVideo> classvideos = classvideoService.list(queryWrapper2);

        //填充章节vo数据
        int count1 = chapters.size();
        for (int i = 0; i < count1; i++) {
            ClassChapter classchapter = chapters.get(i);

            //创建章节vo对象
            ClassChapterVo classChapterVo = new ClassChapterVo();
            BeanUtils.copyProperties(classchapter, classChapterVo);
            classChapterVoArrayList.add(classChapterVo);

            //填充课时vo数据
            ArrayList<ClassVideoVo> videoVoArrayList = new ArrayList<>();
            int count2 = classvideos.size();
            for (int j = 0; j < count2; j++) {

                ClassVideo classvideo = classvideos.get(j);
                if(classchapter.getId().equals(classvideo.getChapterId())){

                    //创建课时vo对象
                    ClassVideoVo classvideoVo = new ClassVideoVo();
                    BeanUtils.copyProperties(classvideo, classvideoVo);
                    videoVoArrayList.add(classvideoVo);
                }
            }
            classChapterVo.setChildren(videoVoArrayList);
        }

        return classChapterVoArrayList;
    }

    @Override
    public boolean removeClassChapterById(String id) {

        //根据id查询是否存在视频，如果有则提示用户尚有子节点
        if(classvideoService.getCountByClassChapterId(id)){
            throw new DefinedException(20001,"该分章节下存在视频课程，请先删除视频课程");
        }

        Integer result = baseMapper.deleteById(id);
        return null != result && result > 0;
    }

    @Override
    public boolean removeClassInfoById(String classId) {
        QueryWrapper<ClassChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("classId", classId);
        Integer count = baseMapper.delete(queryWrapper);
        return null != count && count > 0;
    }
}
