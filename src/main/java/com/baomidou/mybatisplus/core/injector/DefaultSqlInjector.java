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
package com.baomidou.mybatisplus.core.injector;

import com.baomidou.mybatisplus.core.injector.methods.*;
import com.baomidou.mybatisplus.core.injector.methods.pg.*;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * SQL 默认注入器
 *
 * @author hubin
 * @since 2018-04-10
 */
public class DefaultSqlInjector extends AbstractSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        return Stream.of(
                new Insert(),
                new InsertReturn(),
                new UpdateByIdReturn(),
                new Upsert(),
                new UpsertReturn(),
                new UpdateReturn(),
                new Find(),
                new Delete(),
                new DeleteByMap(),
                new DeleteById(),
                new DeleteBatchByIds(),
                new Update(),
                new UpdateById(),
                new SelectById(),
                new SelectBatchByIds(),
                new SelectByMap(),
                new SelectOne(),
                new SelectCount(),
                new SelectMaps(),
                new SelectMapsPage(),
                new SelectObjs(),
                new SelectList(),
                new SelectPage()
        ).collect(toList());
    }
}
