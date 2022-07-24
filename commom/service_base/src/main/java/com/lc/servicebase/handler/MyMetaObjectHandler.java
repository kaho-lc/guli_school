package com.lc.servicebase.handler;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

//实现字段自动填充
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    //添加时执行
    @Override
    public void insertFill(MetaObject metaObject) {
        //当edu_teacher表中的gmt_create字段插入值时，以插入时的时间值来进行插入，下同
        //需要注意的是，这里是属性名称，而非数据库表中字段名称
        this.setFieldValByName("gmtCreate", new Date(), metaObject);
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }

    //修改时执行
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }
}
