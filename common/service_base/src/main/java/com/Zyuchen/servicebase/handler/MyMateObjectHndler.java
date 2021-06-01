package com.Zyuchen.servicebase.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMateObjectHndler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createtime", new Date(), metaObject);
        this.setFieldValByName("modifiedtime", new Date(), metaObject);
        this.setFieldValByName("deadline", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("modifiedtime", new Date(), metaObject);
    }
}
