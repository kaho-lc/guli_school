package com.lc.eduservice.controller;


import com.lc.commomutils.ResultClass;
import com.lc.eduservice.entity.chapter.ChapterVo;
import com.lc.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author kaho
 * @since 2022-07-24
 */
@RestController
@RequestMapping("/eduservice/eduChapter")
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    //课程大纲列表，根据课程id来进行查询
    @GetMapping("getChapterVideo/{courseId}")
    public ResultClass getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> chapterVideo = eduChapterService.getChapterVideoByCourseId(courseId);

        return ResultClass.Success().data("allChapterVideo" , chapterVideo);
    }

}

