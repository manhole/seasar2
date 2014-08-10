/*
 * Copyright 2004-2014 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.extension.jdbc.gen.internal.generator;

import java.io.File;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.seasar.extension.jdbc.gen.generator.GenerationContext;
import org.seasar.extension.jdbc.gen.internal.model.NamesAggregateModelFactoryImpl;
import org.seasar.extension.jdbc.gen.model.NamesAggregateModel;
import org.seasar.extension.jdbc.gen.model.NamesModel;
import org.seasar.framework.util.TextUtil;

import static org.junit.Assert.*;

/**
 * @author taedium
 * 
 */
public class GenerateNamesAggregateTest {

    private NamesAggregateModelFactoryImpl namesAggregateModelFactory;

    private GeneratorImplStub generator;

    /**
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        namesAggregateModelFactory = new NamesAggregateModelFactoryImpl(
                "hoge.entity", "Names");
        generator = new GeneratorImplStub();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        NamesModel namesModel = new NamesModel();
        namesModel.setEntityClassName("entity.Employee");
        namesModel.setShortEntityClassName("Employee");
        namesModel.setShortClassName("EmployeeNames");
        namesModel.setShortInnerClassName("_EmployeeNames");

        NamesModel namesModel2 = new NamesModel();
        namesModel2.setEntityClassName("entity.Department");
        namesModel2.setShortEntityClassName("Department");
        namesModel2.setShortClassName("DepartmentNames");
        namesModel2.setShortInnerClassName("_Department");

        NamesAggregateModel namesAggregateModel = namesAggregateModelFactory
                .getNamesAggregateModel(Arrays.asList(namesModel, namesModel2));

        GenerationContext context = new GenerationContextImpl(
                namesAggregateModel, new File("file"),
                "java/names-aggregate.ftl", "UTF-8", false);
        generator.generate(context);
        String path = getClass().getName().replace(".", "/") + ".txt";
        assertEquals(TextUtil.readUTF8(path), generator.getResult());
    }

}
