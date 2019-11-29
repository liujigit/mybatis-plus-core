package com.baomidou.mybatisplus.core.mapper;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.util.AnnotationUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Copyright：botBrain.ai
 * Author: liuji
 * Date: 2019/8/12.
 * Description:
 */
public interface PgBaseMapper<T> extends BaseMapper<T> {
    /**
     * 插入一条记录并返回改内容完成信息
     *
     * @param suffix 表名补充字段
     * @param entity 实体对象
     * @return
     */
    T insertReturn(@Param(Constants.SUFFIX) String suffix, @Param(Constants.ENTITY) T entity);

    /**
     * @param suffix    表名补充字段
     * @param entity    实体对象
     * @param fieldName 冲突字段名（多字段用英文逗号隔开）
     * @return
     */
    int upsert(@Param(Constants.SUFFIX) String suffix, @Param(Constants.ENTITY) T entity, @Param(Constants.CONFLICT) String fieldName);

    /**
     * @param suffix    表名补充字段
     * @param entity    实体对象
     * @param fieldName 冲突字段名（多字段用英文逗号隔开）
     * @return
     */
    T upsertReturn(@Param(Constants.SUFFIX) String suffix, @Param(Constants.ENTITY) T entity, @Param(Constants.CONFLICT) String fieldName);


    /**
     * 根据 whereEntity 条件，更新记录
     * @param suffix
     * @param entity
     * @return
     */
    T updateByIdReturn(@Param(Constants.SUFFIX) String suffix, @Param(Constants.ENTITY) T entity);

    /**
     * 根据 whereEntity 条件，更新记录
     *
     * @param suffix        表名补充字段
     * @param entity        实体对象 (set 条件值,可以为 null)
     * @param updateWrapper 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
     * @return
     */
    List<T> updateReturn(@Param(Constants.SUFFIX) String suffix, @Param(Constants.ENTITY) T entity, @Param(Constants.WRAPPER) Wrapper<T> updateWrapper);

    default T saveReturn(String suffix, T entity) {
        Object id = AnnotationUtils.getFirst(entity, TableId.class);
        if (id == null) {
            return this.insertReturn(suffix, entity);
        } else {
            return this.updateByIdReturn(suffix, entity);
        }
    }

}
