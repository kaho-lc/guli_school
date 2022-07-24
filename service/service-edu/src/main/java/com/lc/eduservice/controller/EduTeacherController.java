package com.lc.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lc.commomutils.ResultClass;
import com.lc.eduservice.entity.EduTeacher;
import com.lc.eduservice.entity.vo.TeacherQuery;
import com.lc.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
@CrossOrigin
public class EduTeacherController {

    //注入service
    @Autowired
    private EduTeacherService eduTeacherService;

    //1.查询讲师表中的所有数据
    @GetMapping("/findAll")
    public ResultClass findAllTeacher() {
        //调用service中的方法实现查询所有的操作
        List<EduTeacher> eduTeacherList = eduTeacherService.list(null);
        return ResultClass.Success().data("items", eduTeacherList);
    }

    //2.讲师的逻辑删除
    @DeleteMapping("/{id}")
    public ResultClass removeTeacher(@PathVariable String id) {
        //简写形式
        return eduTeacherService.removeById(id) ? ResultClass.Success() : ResultClass.Error();
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
        return ResultClass.Success().data("total", total).data("rows", pageTeacherRecords);
    }

    //4.条件查询讲师并且实现分页,使用对象来传递参数
    //@RequestBody(required = false)代表着参数值可以为空
    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    public ResultClass pageTeacherCondition(@PathVariable Long current, @PathVariable Long limit, @RequestBody(required = false) TeacherQuery teacherQuery) {
        //创建page对象
        Page<EduTeacher> page = new Page<>(current, limit);

        //构建条件
        QueryWrapper<EduTeacher> eduTeacherQueryWrapper = new QueryWrapper<>();

        //判断条件是否为空，如果不为空，则拼接条件
        if (!StringUtils.isEmpty(teacherQuery.getName())) {
            //构建条件--模糊查询
            eduTeacherQueryWrapper.like("name", teacherQuery.getName());
        }

        if (!StringUtils.isEmpty(teacherQuery.getLevel())) {
            eduTeacherQueryWrapper.eq("level", teacherQuery.getLevel());
        }

        //gt: great equal
        if (!StringUtils.isEmpty(teacherQuery.getBegin())) {
            eduTeacherQueryWrapper.ge("gmt_create", teacherQuery.getBegin());
        }

        //le: less equal
        if (!StringUtils.isEmpty(teacherQuery.getEnd())) {
            eduTeacherQueryWrapper.le("gmt_create", teacherQuery.getEnd());
        }

        //对查询结果按照添加时间进行排序
        eduTeacherQueryWrapper.orderByDesc("gmt_create");

        //调用方法实现条件查询并且分页
        eduTeacherService.page(page, eduTeacherQueryWrapper);

        //得到返回的记录总数
        long total = page.getTotal();

        //得到所有的返回数据
        List<EduTeacher> records = page.getRecords();

        return ResultClass.Success().data("total", total).data("rows", records);

    }

    //5.添加讲师
    @PostMapping("/addTeacher")
    public ResultClass addTeacher(@RequestBody EduTeacher eduTeacher) {
        return eduTeacherService.save(eduTeacher) ? ResultClass.Success() : ResultClass.Error();

    }

    //6.修改讲师信息
    //先根据id查询讲师信息
    @GetMapping("/getTeacher/{id}")
    public ResultClass getTeacherById(@PathVariable String id) {

        EduTeacher eduTeacher = eduTeacherService.getById(id);

        return ResultClass.Success().data("teacher", eduTeacher);
    }

    //讲师修改
    @PostMapping("updateTeacher")
    public ResultClass updateTeacher(@RequestBody EduTeacher eduTeacher) {
        return eduTeacherService.updateById(eduTeacher) ? ResultClass.Success() : ResultClass.Error();
    }
}