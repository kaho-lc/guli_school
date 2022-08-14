package com.lc.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lc.eduservice.entity.EduChapter;
import com.lc.eduservice.entity.EduVideo;
import com.lc.eduservice.entity.chapter.ChapterVo;
import com.lc.eduservice.entity.chapter.VideoVo;
import com.lc.eduservice.mapper.EduChapterMapper;
import com.lc.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author kaho
 * @since 2022-07-24
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    //注入章节service
    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //1.根据课程id查询课程里面所有的章节
        QueryWrapper<EduChapter> eduChapterQueryWrapper = new QueryWrapper<>();

        eduChapterQueryWrapper.eq("course_id", courseId);

        List<EduChapter> eduChapterList = baseMapper.selectList(eduChapterQueryWrapper);

        //2.根据课程id查询课程里面所有的小节
        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();

        eduChapterQueryWrapper.eq("course_id", courseId);

        List<EduVideo> eduVideoList = eduVideoService.list(eduVideoQueryWrapper);

        List<ChapterVo> finalList = new ArrayList<>();

        //3.遍历查询章节list集合进行封装
        for (EduChapter eduChapter : eduChapterList) {
            ChapterVo chapterVo = new ChapterVo();
            //复制
            BeanUtils.copyProperties(eduChapter, chapterVo);

            finalList.add(chapterVo);

            //4.遍历查询小节list集合，进行封装
            ArrayList<VideoVo> videoList = new ArrayList<>();

            for (EduVideo eduVideo : eduVideoList) {
                //判断小节里面的chapterId和章节里面的id是否一样
                if (eduVideo.getChapterId().equals(eduChapter.getId())) {
                    //封装
                    VideoVo videoVo = new VideoVo();

                    BeanUtils.copyProperties(eduVideo, videoVo);

                    videoList.add(videoVo);
                }
            }

            //将小节封装到其对应的章节中
            chapterVo.setChildren(videoList);

        }


        return finalList;
    }
}
