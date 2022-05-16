package com.lc.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lc.commomutils.ResultClass;
import com.lc.eduservice.entity.EduTeacher;
import com.lc.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author kaho
 * @since 2022-05-16
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    //注入service
    @Autowired
    private EduTeacherService eduTeacherService;

    //1.查询讲师表中的所有数据
    @GetMapping("/findAll")
    public ResultClass findAllTeacher() {
        //调用service中的方法实现查询所有的操作
        List<EduTeacher> eduTeacherList = eduTeacherService.list(null);

        return ResultClass.success().data("items", eduTeacherList);
    }

    //2.讲师的逻辑删除
    @DeleteMapping("/{id}")
    public ResultClass removeTeacher(@PathVariable String id) {
        boolean flag = eduTeacherService.removeById(id);

        return flag ? ResultClass.success() : ResultClass.ERROR();
    }

    //3.讲师分页查询
    @GetMapping("/pageTeacher/{current}/{limit}")
    public ResultClass pageListTeacher(@PathVariable Long current, @PathVariable Long limit) {
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);

        //调用方法实现分页操作
        //调用方法时，底层封装，把分页的所有数据都封装到pageTeacher对象里面
        eduTeacherService.page(pageTeacher, null);

        //总记录数
        long total = pageTeacher.getTotal();

        //获取每页数据的数据集合
        List<EduTeacher> pageTeacherRecords = pageTeacher.getRecords();

        //返回数据
        return ResultClass.success().data("total", total).data("rows", pageTeacherRecords);
    }
}