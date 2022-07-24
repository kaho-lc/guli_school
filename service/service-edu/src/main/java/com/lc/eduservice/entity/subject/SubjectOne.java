package com.lc.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kaho
 * @description 一级分类
 * @date 2022/6/21 18:30
 */
@Data
public class SubjectOne {
    //id
    private String id;

    //名称
    private String title;

    //一个一级分类有多个二级分类
    private List<SubjectTwo> children = new ArrayList<>();

}