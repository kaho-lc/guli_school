package com.lc.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lc.eduservice.entity.EduSubject;
import com.lc.eduservice.entity.excel.SubjectData;
import com.lc.eduservice.service.EduSubjectService;
import com.lc.servicebase.exceptionHandler.GuliException;

public class SubjectListener extends AnalysisEventListener<SubjectData> {
    //因为SubjectListener不能交给spring进行管理的，所以需要我们自己new ， 不能注入其他对象
    //不能实现数据库操作
    public EduSubjectService subjectService;

    public SubjectListener() {
    }

    public SubjectListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //读取excel的内容，一行一行的读
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        //判断excel中的一行是否为空
        if (subjectData == null) {
            throw new GuliException(20001, "文件数据为空");
        }

        //判断一级分类是否重复
        EduSubject existOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());

        if (existOneSubject == null) { //没有相同的一级分类，可以进行添加

            existOneSubject = new EduSubject();

            existOneSubject.setParentId("0");

            existOneSubject.setTitle(subjectData.getOneSubjectName());

            //保存到数据库当中
            subjectService.save(existOneSubject);
        }

        //获取一级分类的id值
        String pid = existOneSubject.getId();

        //判断二级分类是否重复
        EduSubject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);

        if (existTwoSubject == null) {

            existTwoSubject = new EduSubject();

            existTwoSubject.setParentId(pid);

            //二级分类名称
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());

            subjectService.save(existTwoSubject);
        }
    }

    //判断一级分类不能重复添加
    public EduSubject existOneSubject(EduSubjectService subjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();

        wrapper.eq("title", name);

        wrapper.eq("parent_id", "0");

        return subjectService.getOne(wrapper);

    }

    //判断二级分类不能重复添加
    public EduSubject existTwoSubject(EduSubjectService subjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();

        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);

        return subjectService.getOne(wrapper);

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}