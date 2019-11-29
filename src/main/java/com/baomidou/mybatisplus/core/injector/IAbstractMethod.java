package com.baomidou.mybatisplus.core.injector;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * Copyright：botBrain.ai
 * Author: liuji
 * Date: 2019/11/29.
 * Description:
 */
public abstract class IAbstractMethod extends AbstractMethod {


    public MappedStatement insert(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.INSERT_ONE;
        String columnScript = SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlColumnMaybeIf(Constants.ENTITY_DOT),
                LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
        String valuesScript = SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlPropertyMaybeIf(Constants.ENTITY_DOT),
                LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), columnScript, valuesScript);
        return this.injectUpsertStatement(sqlMethod, sql, mapperClass, modelClass, tableInfo);
    }

    public MappedStatement insertReturn(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.INSERT_RETURN;
        String columnScript = SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlColumnMaybeIf(Constants.ENTITY_DOT),
                LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
        String valuesScript = SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlPropertyMaybeIf(Constants.ENTITY_DOT),
                LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
        String returning = tableInfo.getAllSqlSelect();
        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), columnScript, valuesScript, returning);
        return this.injectUpsertStatement(sqlMethod, sql, mapperClass, modelClass, tableInfo);
    }

    public MappedStatement updateById(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        boolean logicDelete = tableInfo.isLogicDelete();
        SqlMethod sqlMethod = SqlMethod.UPDATE_BY_ID;
        final String additional = optlockVersion() + tableInfo.getLogicDeleteSql(true, false);
        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(),
                sqlSet(logicDelete, false, tableInfo, false, ENTITY, ENTITY_DOT),
                tableInfo.getKeyColumn(), ENTITY_DOT + tableInfo.getKeyProperty(), additional);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return addUpdateMappedStatement(mapperClass, modelClass, sqlMethod.getMethod(), sqlSource);
    }

    public MappedStatement updateByIdReturn(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        boolean logicDelete = tableInfo.isLogicDelete();
        SqlMethod sqlMethod = SqlMethod.UPDATE_BY_ID_RETURN;
        final String additional = optlockVersion() + tableInfo.getLogicDeleteSql(true, false);
        String returning = tableInfo.getAllSqlSelect();
        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(),
                sqlSet(logicDelete, false, tableInfo, false, ENTITY, ENTITY_DOT),
                tableInfo.getKeyColumn(), ENTITY_DOT + tableInfo.getKeyProperty(), additional, returning);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addReturnMappedStatement(tableInfo, mapperClass, modelClass, sqlMethod.getMethod(), sqlSource, new NoKeyGenerator(), null, null);
    }

    public MappedStatement updateReturn(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.UPDATE_RETURN;
        String returning = tableInfo.getAllSqlSelect();
        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(),
                sqlSet(true, true, tableInfo, true, ENTITY, ENTITY_DOT),
                sqlWhereEntityWrapper(true, tableInfo),
                sqlComment(), returning);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addReturnMappedStatement(tableInfo, mapperClass, modelClass, sqlMethod.getMethod(), sqlSource, new NoKeyGenerator(), null, null);
    }


    public MappedStatement upsertReturn(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.UPSERT_RETURN;
        String columnScript = SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlColumnMaybeIf(Constants.ENTITY_DOT),
                LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
        String valuesScript = SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlPropertyMaybeIf(Constants.ENTITY_DOT),
                LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);

        String upsert = this.sqlUpsert(true, tableInfo, true, ENTITY, ENTITY_DOT);
//        String upsert = UPDATE_NEWLINE+ sqlSet(true, false, tableInfo, true, ENTITY, ENTITY_DOT);

        String returning = tableInfo.getAllSqlSelect();
        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), columnScript, valuesScript, upsert, returning);
        return this.injectUpsertStatement(sqlMethod, sql, mapperClass, modelClass, tableInfo);
    }

    public MappedStatement upsert(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.UPSERT;
        String columnScript = SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlColumnMaybeIf(Constants.ENTITY_DOT),
                LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
        String valuesScript = SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlPropertyMaybeIf(Constants.ENTITY_DOT),
                LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);

        String upsert = this.sqlUpsert(true, tableInfo, true, ENTITY, EXCLUDED_DOT);
//        String upsert = UPDATE_NEWLINE+ sqlSet(true, false, tableInfo, true, ENTITY, ENTITY_DOT);
        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), columnScript, valuesScript, upsert);
        return this.injectUpsertStatement(sqlMethod, sql, mapperClass, modelClass, tableInfo);
    }


    protected MappedStatement injectUpsertStatement(SqlMethod sqlMethod, String sql, Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        KeyGenerator keyGenerator = new NoKeyGenerator();
        String keyProperty = null;
        String keyColumn = null;
        // 表包含主键处理逻辑,如果不包含主键当普通字段处理
        if (StringUtils.isNotEmpty(tableInfo.getKeyProperty())) {
            if (tableInfo.getIdType() == IdType.AUTO) {
                /** 自增主键 */
                keyGenerator = new Jdbc3KeyGenerator();
                keyProperty = tableInfo.getKeyProperty();
                keyColumn = tableInfo.getKeyColumn();
            } else {
                if (null != tableInfo.getKeySequence()) {
                    keyGenerator = TableInfoHelper.genKeyGenerator(tableInfo, builderAssistant, sqlMethod.getMethod(), languageDriver);
                    keyProperty = tableInfo.getKeyProperty();
                    keyColumn = tableInfo.getKeyColumn();
                }
            }
        }
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addReturnMappedStatement(tableInfo, mapperClass, modelClass, sqlMethod.getMethod(), sqlSource, keyGenerator, keyProperty, keyColumn);
    }


    /**
     * SQL 更新 set 语句
     *
     * @param logic  是否逻辑删除注入器
     * @param table  表信息
     * @param alias  别名
     * @param prefix 前缀
     * @return sql
     */
    protected String sqlUpsert(boolean logic, TableInfo table, boolean judgeAliasNull, String alias, String prefix) {
        String sqlScript = table.getAllSqlUpsert(logic, prefix);
        if (judgeAliasNull) {
            sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format("%s != null", alias), true);
        }
        sqlScript = Constants.UPDATE_NEWLINE + SqlScriptUtils.convertSet(sqlScript);
        return sqlScript;
    }
}
