package com.baomidou.mybatisplus.core.injector.methods.pg;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;

/**
 * Copyright：botBrain.ai
 * Author: liuji
 * Date: 2019/8/10.
 * Description:
 */
public class InsertReturn extends Upsert {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        KeyGenerator keyGenerator = new NoKeyGenerator();
        SqlMethod sqlMethod = SqlMethod.INSERT_RETURN;
        String columnScript = SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlColumnMaybeIf(Constants.ENTITY_DOT),
                LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
        String valuesScript = SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlPropertyMaybeIf(Constants.ENTITY_DOT),
                LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
        String returning = tableInfo.getAllSqlSelect();
        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), columnScript, valuesScript,returning);
        return this.injectUpsertStatement(sqlMethod,sql,mapperClass, modelClass,tableInfo);
    }
}
