package com.lc.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;

/**
 * @author kaho
 * @description
 * @date 2022/8/14 22:17
 */
@Data
public class ChapterVo {
    private String id;

    private String title;

    private ArrayList<VideoVo> children = new ArrayList<>();
}
