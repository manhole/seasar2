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
package org.seasar.extension.jdbc.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.seasar.extension.jdbc.DbmsDialect;
import org.seasar.extension.jdbc.ResultSetHandler;
import org.seasar.extension.jdbc.exception.SNonUniqueResultException;
import org.seasar.framework.convention.PersistenceConvention;

/**
 * Beanを返す {@link ResultSetHandler}です。
 * 
 * @author higa
 * 
 */
public class BeanResultSetHandler extends AbstractBeanResultSetHandler {

    /**
     * {@link BeanResultSetHandler}を作成します。
     * 
     * @param beanClass
     *            Beanクラス
     * @param dialect
     *            データベースの方言
     * @param persistenceConvention
     *            永続化層の規約
     * @param sql
     *            SQL
     */
    public BeanResultSetHandler(Class<?> beanClass, DbmsDialect dialect,
            PersistenceConvention persistenceConvention, String sql) {
        super(beanClass, dialect, persistenceConvention, sql);
    }

    public Object handle(ResultSet rs) throws SQLException,
            SNonUniqueResultException {
        Object ret = null;
        if (rs.next()) {
            ret = createRow(rs, createPropertyTypes(rs.getMetaData()));
            if (rs.next()) {
                throw new SNonUniqueResultException(sql);
            }
        }
        return ret;
    }
}