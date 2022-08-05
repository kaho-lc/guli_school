package com.lc.eduservice.controller;


import com.lc.commomutils.ResultClass;
import com.lc.eduservice.entity.vo.CourseInfoVO;
import com.lc.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author kaho
 * @since 2022-07-24
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/course")
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    //添加课程基本信息
    @PostMapping("addCourseInfo")
    public ResultClass addCourseInfo(@RequestBody CourseInfoVO courseInfoVO){
        courseService.saveCourseInfo(courseInfoVO);
        return ResultClass.Success();
    }

}

