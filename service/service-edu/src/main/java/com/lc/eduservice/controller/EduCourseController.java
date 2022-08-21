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
    public ResultClass addCourseInfo(@RequestBody CourseInfoVO courseInfoVO) {
        //返回添加之后的课程id，为了之后添加课程大纲使用
        String id = courseService.saveCourseInfo(courseInfoVO);
        return ResultClass.Success().data("courseId", id);
    }

    //根据课程查询课程基本信息
    @GetMapping("/getCourseInfo/{courseId}")
    public ResultClass getCourseInfo(@PathVariable String courseId) {
        CourseInfoVO courseInfoVO = courseService.getCourseInfo(courseId);
        return ResultClass.Success().data("courseInfoVo", courseInfoVO);
    }

    //修改课程基本信息
    @PostMapping("/updateCourseInfo")
    public ResultClass updateCourseInfo(CourseInfoVO courseInfoVO) {

        courseService.updateCourseInfo(courseInfoVO);

        return ResultClass.Success();
    }
}

