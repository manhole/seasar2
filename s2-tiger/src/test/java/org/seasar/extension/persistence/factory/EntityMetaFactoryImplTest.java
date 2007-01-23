/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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
package org.seasar.extension.persistence.factory;

import java.io.File;
import java.util.Date;

import junit.framework.TestCase;

import org.seasar.extension.persistence.EntityMeta;
import org.seasar.extension.persistence.entity.Employee;
import org.seasar.framework.convention.impl.PersistenceConventionImpl;
import org.seasar.framework.util.ResourceUtil;

/**
 * @author higa
 * 
 */
public class EntityMetaFactoryImplTest extends TestCase {

    private EntityMetaFactoryImpl factory;

    @Override
    protected void setUp() {
        factory = new EntityMetaFactoryImpl();
        TableMetaFactoryImpl tableMetaFactory = new TableMetaFactoryImpl();
        tableMetaFactory
                .setPersistenceConvention(new PersistenceConventionImpl());
        factory.setTableMetaFactory(tableMetaFactory);
    }

    public void testGetEntityMeta() throws Exception {
        EntityMeta entityMeta = factory.getEntityMeta(Employee.class);
        assertSame(entityMeta, factory.getEntityMeta(Employee.class));
        Thread.sleep(1000);
        File file = ResourceUtil.getResourceAsFileNoException(Employee.class);
        file.setLastModified(new Date().getTime());
        assertNotSame(entityMeta, factory.getEntityMeta(Employee.class));
    }

    public void testCreateEntityMeta_name() throws Exception {
        EntityMeta entityMeta = factory.createEntityMeta(Employee.class);
        assertEquals("Employee", entityMeta.getName());
    }

    public void testCreateEntityMeta_tableMeta() throws Exception {
        EntityMeta entityMeta = factory.createEntityMeta(Employee.class);
        assertNotNull(entityMeta.getTableMeta());
    }
}