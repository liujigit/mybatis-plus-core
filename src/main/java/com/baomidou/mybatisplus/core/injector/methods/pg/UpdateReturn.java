package com.baomidou.mybatisplus.core.injector.methods.pg;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * Copyright：botBrain.ai
 * Author: liuji
 * Date: 2019/8/12.
 * Description:
 */
public class UpdateReturn extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.UPDATE_RETURN;
        String returning = tableInfo.getAllSqlSelect();
        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(),
                sqlSet(true, true, tableInfo, true, ENTITY, ENTITY_DOT),
                sqlWhereEntityWrapper(true, tableInfo),
                sqlComment(),returning);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addReturnMappedStatement(tableInfo,mapperClass, modelClass, sqlMethod.getMethod(), sqlSource, new NoKeyGenerator(), null, null);
    }
}
