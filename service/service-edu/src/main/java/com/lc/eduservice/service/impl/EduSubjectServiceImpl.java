package com.lc.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lc.eduservice.entity.EduSubject;
import com.lc.eduservice.entity.excel.SubjectData;
import com.lc.eduservice.entity.subject.SubjectOne;
import com.lc.eduservice.entity.subject.SubjectTwo;
import com.lc.eduservice.listener.SubjectListener;
import com.lc.eduservice.mapper.EduSubjectMapper;
import com.lc.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author kaho
 * @since 2022-06-14
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            //使用流的形式读取文件
            EasyExcel.read(file.getInputStream(), SubjectData.class, new SubjectListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SubjectOne> getAllOneTwoSubject() {
        //1.查询出所有的一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        //eq:等于
        wrapperOne.eq("parent_id", "0");

        List<EduSubject> oneEduSubjectList = baseMapper.selectList(wrapperOne);

        //2.查询出所有的二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        //ne:不等于
        wrapperOne.ne("parent_id", "0");

        List<EduSubject> twoEduSubjectList = baseMapper.selectList(wrapperTwo);

        //创建集合来存储最终封装的数据
        ArrayList<SubjectOne> finalSubjectList = new ArrayList<>();

        //3.封装一级分类
        //查询出来的所有的一级分类list集合遍历，得到每个一级分类对象，获取每个一级分类对象值
        //然后进行封装
        for (EduSubject eduSubject : oneEduSubjectList) {

            //获取出eduSubject中的值，放入OneSubject对象中
            SubjectOne subjectOne = new SubjectOne();

            //使用spring中的工具类
            BeanUtils.copyProperties(eduSubject, subjectOne);

            finalSubjectList.add(subjectOne);

            //循环遍历二级分类
            ArrayList<SubjectTwo> finalTwoSubjectList = new ArrayList<>();

            for (EduSubject tSubject : twoEduSubjectList) {

                if (eduSubject.getId().equals(tSubject.getParentId())) {

                    SubjectTwo subjectTwo = new SubjectTwo();

                    BeanUtils.copyProperties(tSubject, subjectTwo);

                    finalTwoSubjectList.add(subjectTwo);
                }
            }

            subjectOne.setChildren(finalTwoSubjectList);
        }

        //4.封装二级分类

        return finalSubjectList;
    }
}
