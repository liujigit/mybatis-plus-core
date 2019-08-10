package com.baomidou.mybatisplus.core.injector.methods;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * Copyright：botBrain.ai
 * Author: liuji
 * Date: 2019/8/9.
 * Description:
 */
public class Find extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql;
        SqlMethod mySqlMethod = SqlMethod.FIND;
        String selectColumns = sqlSelectColumns(tableInfo, true);
        String where = sqlWhereEntityWrapper(true, tableInfo);
        String comment = sqlComment();
        sql = String.format(mySqlMethod.getSql(), selectColumns, tableInfo.getTableName(), where, comment);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return addSelectMappedStatementForTable(mapperClass, mySqlMethod.getMethod(), sqlSource, tableInfo);
    }
}
