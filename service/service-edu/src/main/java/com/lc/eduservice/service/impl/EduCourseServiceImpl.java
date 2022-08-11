package com.lc.eduservice.service.impl;

import com.lc.eduservice.entity.EduCourse;
import com.lc.eduservice.entity.EduCourseDescription;
import com.lc.eduservice.entity.vo.CourseInfoVO;
import com.lc.eduservice.mapper.EduCourseMapper;
import com.lc.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.servicebase.exceptionHandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author kaho
 * @since 2022-07-24
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    //课程描述注入
    @Autowired
    private EduCourseDescriptionServiceImpl courseDescriptionService;

    //添加课程基本信息
    @Override
    public String saveCourseInfo(CourseInfoVO courseInfoVO) {
        //向课程表中添加课程基本信息
        //将CourseInfoVO转换成eduCourse对象,使用BeanUtils
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVO, eduCourse);

        //返回值代表代表的是影响的行数
        int insert = baseMapper.insert(eduCourse);

        if (insert == 0) {
            throw new GuliException(2001, "添加课程信息失败");
        }

        //获取添加之后的课程id
        String id = eduCourse.getId();

        //向课程简介表中添加课程简介
        EduCourseDescription eduCourseDescription = new EduCourseDescription();

        eduCourseDescription.setDescription(courseInfoVO.getDescription());
        eduCourseDescription.setId(id);

        courseDescriptionService.save(eduCourseDescription);

        //返回课程id
        return id;
    }
}
