package com.baomidou.mybatisplus.core.injector.methods.pg;

import com.baomidou.mybatisplus.core.injector.IAbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;

/**
 * Copyright：botBrain.ai
 * Author: liuji
 * Date: 2019/8/12.
 * Description:
 */
public class UpdateReturn extends IAbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        return super.updateReturn(mapperClass, modelClass, tableInfo);
    }
}
