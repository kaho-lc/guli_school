package com.lc.eduservice.controller;


import com.lc.commomutils.ResultClass;
import com.lc.eduservice.entity.subject.SubjectOne;
import com.lc.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author kaho
 * @since 2022-06-14
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;


    //添加课程分类
    //获取上传的文件，将文件中的内容读取出来
    @PostMapping("/addSubject")
    public ResultClass addSubject(MultipartFile file) {
        //上传excel文件过来
        eduSubjectService.saveSubject(file, eduSubjectService);
        return ResultClass.Success();
    }

    //课程分类列表(树形 )
    @GetMapping("/getAllSubject")
    public ResultClass getAllSubject() {
        //list集合中的泛型是一级分类
        List<SubjectOne> list = eduSubjectService.getAllOneTwoSubject();
        return ResultClass.Success().data("list", list);
    }
}