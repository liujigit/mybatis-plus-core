/*
 * Copyright (c) 2011-2020, baomidou (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.baomidou.mybatisplus.core.toolkit;

/**
 * mybatis_plus 自用常量集中管理
 *
 * @author miemie
 * @since 2018-07-22
 */
public interface Constants extends StringPool {

    /**
     * project name
     */
    String MYBATIS_PLUS = "mybatis-plus";

    /**
     * MD5
     */
    String MD5 = "MD5";
    /**
     * 实体类
     */
    String ENTITY = "et";

    /**
     * 表明的后缀 ${suffix}
     */
    String SUFFIX = "suffix";

    /**
     * 冲突字段
     */
    String CONFLICT = "conflict";

    String NOTHING = "nothing";

    String UPDATE = "update";

    String SET="set";

    String UPDATE_NEWLINE = UPDATE + NEWLINE;

    String EXCLUDED = "excluded";

    String EXCLUDED_DOT = EXCLUDED + DOT;

    String RETURN="returning";

    String ID="id";

    /**
     * 实体类 带后缀 ==> .
     */
    String ENTITY_DOT = ENTITY + DOT;
    /**
     * wrapper 类
     */
    String WRAPPER = "ew";
    /**
     * wrapper 类 带后缀 ==> .
     */
    String WRAPPER_DOT = WRAPPER + DOT;
    /**
     * wrapper 类的属性 entity
     */
    String WRAPPER_ENTITY = WRAPPER_DOT + "entity";
    /**
     * wrapper 类的属性 sqlSegment
     */
    String WRAPPER_SQLSEGMENT = WRAPPER_DOT + "sqlSegment";
    /**
     * wrapper 类的属性 emptyOfNormal
     */
    String WRAPPER_EMPTYOFNORMAL = WRAPPER_DOT + "emptyOfNormal";
    /**
     * wrapper 类的属性 nonEmptyOfNormal
     */
    String WRAPPER_NONEMPTYOFNORMAL = WRAPPER_DOT + "nonEmptyOfNormal";
    /**
     * wrapper 类的属性 nonEmptyOfEntity
     */
    String WRAPPER_NONEMPTYOFENTITY = WRAPPER_DOT + "nonEmptyOfEntity";
    /**
     * wrapper 类的属性 emptyOfWhere
     */
    String WRAPPER_EMPTYOFWHERE = WRAPPER_DOT + "emptyOfWhere";
    /**
     * wrapper 类的判断属性 nonEmptyOfWhere
     */
    String WRAPPER_NONEMPTYOFWHERE = WRAPPER_DOT + "nonEmptyOfWhere";
    /**
     * wrapper 类的属性 entity 带后缀 ==> .
     */
    String WRAPPER_ENTITY_DOT = WRAPPER_DOT + "entity" + DOT;
    /**
     * UpdateWrapper 类的属性 sqlSet
     */
    String U_WRAPPER_SQL_SET = WRAPPER_DOT + "sqlSet";

    String U_ENTITY_SQL_UPSERT = ENTITY_DOT + "sqlUpsert";

    /**
     * QueryWrapper 类的属性 sqlSelect
     */
    String Q_WRAPPER_SQL_SELECT = WRAPPER_DOT + "sqlSelect";
    /**
     * wrapper 类的属性 sqlComment
     */
    String Q_WRAPPER_SQL_COMMENT = WRAPPER_DOT + "sqlComment";
    /**
     * columnMap
     */
    String COLUMN_MAP = "cm";
    /**
     * columnMap.isEmpty
     */
    String COLUMN_MAP_IS_EMPTY = COLUMN_MAP + DOT + "isEmpty";
    /**
     * collection
     */
    String COLLECTION = "coll";
    /**
     * where
     */
    String WHERE = "WHERE";

    /**
     * 乐观锁字段
     */
    String MP_OPTLOCK_VERSION_ORIGINAL = "MP_OPTLOCK_VERSION_ORIGINAL";
    String MP_OPTLOCK_VERSION_COLUMN = "MP_OPTLOCK_VERSION_COLUMN";
    String MP_OPTLOCK_ET_ORIGINAL = "MP_OPTLOCK_ET_ORIGINAL";

    String WRAPPER_PARAM = "MPGENVAL";
    String WRAPPER_PARAM_FORMAT = "#{%s.paramNameValuePairs.%s}";
}
