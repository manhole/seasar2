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
package org.seasar.framework.convention;

/**
 * 永続化層の命名規約を表すインターフェースです。
 * 
 * @author higa
 * 
 */
public interface PersistenceConvention {

    /**
     * テーブル名をエンティティ名に変換します。
     * 
     * @param tableName
     *            テーブル名
     * @return エンティティ名
     */
    String fromTableNameToEntityName(String tableName);

    /**
     * エンティティ名をテーブル名に変更します。
     * 
     * @param entityName
     *            エンティティ名
     * @return テーブル名
     */
    String fromEntityNameToTableName(String entityName);

    /**
     * カラム名をプロパティ名に変換します。
     * 
     * @param columnName
     *            カラム名
     * @return プロパティ名
     */
    String fromColumnNameToPropertyName(String columnName);

    /**
     * プロパティ名をカラム名に変換します。
     * 
     * @param propertyName
     *            プロパティ名
     * @return カラム名
     */
    String fromPropertyNameToColumnName(String propertyName);

    /**
     * フィールド名をプロパティ名に変換します。
     * 
     * @param fieldName
     *            フィールド名
     * @return プロパティ名
     */
    String fromFieldNameToPropertyName(String fieldName);

    /**
     * プロパティ名をフィールド名に変換します。
     * 
     * @param propertyName
     *            プロパティ名
     * @return フィールド名
     */
    String fromPropertyNameToFieldName(String propertyName);
}
