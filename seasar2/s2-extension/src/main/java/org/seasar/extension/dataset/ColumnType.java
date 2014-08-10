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
package org.seasar.extension.dataset;

/**
 * カラムの型をあらわすインターフェースです。
 * 
 * @author higa
 * 
 */
public interface ColumnType {

    /**
     * 適切な型に変換します。
     * 
     * @param value
     *            値
     * @param formatPattern
     *            フォーマットするためのパターン
     * @return 変換後の値
     */
    Object convert(Object value, String formatPattern);

    /**
     * 等しいかどうかを返します。
     * 
     * @param arg1
     *            引数1
     * @param arg2
     *            引数2
     * @return 等しいかどうか
     */
    boolean equals(Object arg1, Object arg2);

    /**
     * 型を返します。
     * 
     * @return 型
     */
    Class getType();
}