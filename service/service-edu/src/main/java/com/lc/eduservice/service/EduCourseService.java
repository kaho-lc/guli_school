package com.lc.eduservice.service;

import com.lc.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.eduservice.entity.vo.CourseInfoVO;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author kaho
 * @since 2022-07-24
 */
public interface EduCourseService extends IService<EduCourse> {
    //添加课程基本信息
    String saveCourseInfo(CourseInfoVO courseInfoVO);

    CourseInfoVO getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVO courseInfoVO);
}
