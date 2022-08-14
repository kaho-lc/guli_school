package com.lc.eduservice.service;

import com.lc.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author kaho
 * @since 2022-07-24
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);
}
