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
package com.baomidou.mybatisplus.core.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/*

               :`
                    .:,
                     :::,,.
             ::      `::::::
             ::`    `,:,` .:`
             `:: `::::::::.:`      `:';,`
              ::::,     .:::`   `@++++++++:
               ``        :::`  @+++++++++++#
                         :::, #++++++++++++++`
                 ,:      `::::::;'##++++++++++
                 .@#@;`   ::::::::::::::::::::;
                  #@####@, :::::::::::::::+#;::.
                  @@######+@:::::::::::::.  #@:;
           ,      @@########':::::::::::: .#''':`
           ;##@@@+:##########@::::::::::: @#;.,:.
            #@@@######++++#####'::::::::: .##+,:#`
            @@@@@#####+++++'#####+::::::::` ,`::@#:`
            `@@@@#####++++++'#####+#':::::::::::@.
             @@@@######+++++''#######+##';::::;':,`
              @@@@#####+++++'''#######++++++++++`
               #@@#####++++++''########++++++++'
               `#@######+++++''+########+++++++;
                `@@#####+++++''##########++++++,
                 @@######+++++'##########+++++#`
                @@@@#####+++++############++++;
              ;#@@@@@####++++##############+++,
             @@@@@@@@@@@###@###############++'
           @#@@@@@@@@@@@@###################+:
        `@#@@@@@@@@@@@@@@###################'`
      :@#@@@@@@@@@@@@@@@@@##################,
      ,@@@@@@@@@@@@@@@@@@@@################;
       ,#@@@@@@@@@@@@@@@@@@@##############+`
        .#@@@@@@@@@@@@@@@@@@#############@,
          @@@@@@@@@@@@@@@@@@@###########@,
           :#@@@@@@@@@@@@@@@@##########@,
            `##@@@@@@@@@@@@@@@########+,
              `+@@@@@@@@@@@@@@@#####@:`
                `:@@@@@@@@@@@@@@##@;.
                   `,'@@@@##@@@+;,`
                        ``...``

 _ _     /_ _ _/_. ____  /    _
/ / //_//_//_|/ /_\  /_///_/_\      Talk is cheap. Show me the code.
     _/             /
 */

/**
 * <p>suffix：使用@TableName注解：例如：表名 "table-name${suffix}" ,建议在表名上加上双引号，防止表名含大写字母</p>
 * Mapper 继承该接口后，无需编写 mapper.xml 文件，即可获得CRUD功能
 * <p>这个 Mapper 支持 id 泛型</p>
 *
 * @author hubin
 * @since 2016-01-23
 */
public interface BaseMapper<T> extends Mapper<T> {

    /**
     * 插入一条记录
     *
     * @param suffix 表名补充字段
     * @param entity 实体对象
     * @return
     */
    int insert(@Param(Constants.SUFFIX) String suffix, @Param(Constants.ENTITY) T entity);

    /**
     * 根据 ID 删除
     *
     * @param suffix 表名补充字段
     * @param id     主键ID
     * @return
     */
    int deleteById(@Param(Constants.SUFFIX) String suffix, @Param(Constants.ID) Serializable id);

    /**
     * 根据 columnMap 条件，删除记录
     *
     * @param suffix
     * @param columnMap 表字段 map 对象
     * @return
     */
    int deleteByMap(@Param(Constants.SUFFIX) String suffix, @Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    /**
     * 根据 entity 条件，删除记录
     *
     * @param suffix  表名补充字段
     * @param wrapper 实体对象封装操作类（可以为 null）
     * @return
     */
    int delete(@Param(Constants.SUFFIX) String suffix, @Param(Constants.WRAPPER) Wrapper<T> wrapper);

    /**
     * 删除（根据ID 批量删除）
     *
     * @param suffix 表名补充字段
     * @param idList 主键ID列表(不能为 null 以及 empty)
     * @return
     */
    int deleteBatchIds(@Param(Constants.SUFFIX) String suffix, @Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

    /**
     * 根据 ID 修改
     *
     * @param suffix 表名补充字段
     * @param entity 实体对象
     * @return
     */
    int updateById(@Param(Constants.SUFFIX) String suffix, @Param(Constants.ENTITY) T entity);

    /**
     * 根据 whereEntity 条件，更新记录
     *
     * @param suffix        表名补充字段
     * @param entity        实体对象 (set 条件值,可以为 null)
     * @param updateWrapper 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
     * @return
     */
    int update(@Param(Constants.SUFFIX) String suffix, @Param(Constants.ENTITY) T entity, @Param(Constants.WRAPPER) Wrapper<T> updateWrapper);


    /**
     * 根据 对象（entity） 查询
     *
     * @param suffix       表名补充字段
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     * @return
     */
    List<T> find(@Param(Constants.SUFFIX) String suffix, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * @param suffix 表名补充字段
     * @param entity 实体对象
     * @return
     */
    default List<T> find(String suffix, T entity) {
        return this.find(suffix, new QueryWrapper<>(entity));
    }

    /**
     * @param suffix 表名补充字段
     * @return
     */
    default List<T> find(String suffix) {
        return this.find(suffix, (T) null);
    }

    /**
     * 根据 ID 查询
     *
     * @param suffix 表名补充字段
     * @param id     主键ID
     * @return
     */
    T selectById(@Param(Constants.SUFFIX) String suffix, @Param(Constants.ID) Serializable id);

    /**
     * 查询（根据ID 批量查询）
     *
     * @param suffix 表名补充字段
     * @param idList 主键ID列表(不能为 null 以及 empty)
     * @return
     */
    List<T> selectBatchIds(@Param(Constants.SUFFIX) String suffix, @Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

    /**
     * 查询（根据 columnMap 条件）
     *
     * @param suffix
     * @param columnMap 表字段 map 对象
     * @return
     */
    List<T> selectByMap(@Param(Constants.SUFFIX) String suffix, @Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    /**
     * 根据 entity 条件，查询一条记录
     *
     * @param suffix       表名补充字段
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     * @return
     */
    T selectOne(@Param(Constants.SUFFIX) String suffix, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 Wrapper 条件，查询总记录数
     *
     * @param suffix       表名补充字段
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     * @return
     */
    Integer selectCount(@Param(Constants.SUFFIX) String suffix, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 entity 条件，查询全部记录
     *
     * @param suffix       表名补充字段
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     * @return
     */
    List<T> selectList(@Param(Constants.SUFFIX) String suffix, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 Wrapper 条件，查询全部记录
     *
     * @param suffix       表名补充字段
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     * @return
     */
    List<Map<String, Object>> selectMaps(@Param(Constants.SUFFIX) String suffix, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 Wrapper 条件，查询全部记录
     * <p>注意： 只返回第一个字段的值</p>
     *
     * @param suffix       表名补充字段
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     * @return
     */
    List<Object> selectObjs(@Param(Constants.SUFFIX) String suffix, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 entity 条件，查询全部记录（并翻页）
     *
     * @param suffix       表名补充字段
     * @param page         分页查询条件（可以为 RowBounds.DEFAULT）
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     * @return
     */
    IPage<T> selectPage(@Param(Constants.SUFFIX) String suffix, IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 Wrapper 条件，查询全部记录（并翻页）
     *
     * @param suffix       表名补充字段
     * @param page         分页查询条件
     * @param queryWrapper 实体对象封装操作类
     * @return
     */
    IPage<Map<String, Object>> selectMapsPage(@Param(Constants.SUFFIX) String suffix, IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
}
