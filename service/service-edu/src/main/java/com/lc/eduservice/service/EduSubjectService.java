package com.lc.eduservice.service;

import com.lc.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.eduservice.entity.subject.SubjectOne;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author kaho
 * @since 2022-06-14
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file , EduSubjectService eduSubjectService);

    List<SubjectOne> getAllOneTwoSubject();
}
