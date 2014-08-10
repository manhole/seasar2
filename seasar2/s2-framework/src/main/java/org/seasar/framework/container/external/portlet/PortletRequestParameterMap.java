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
package org.seasar.framework.container.external.portlet;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.portlet.PortletRequest;

import org.seasar.framework.container.external.AbstractUnmodifiableExternalContextMap;

/**
 * Portlet用のRequestParameterMapです。
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 */
public class PortletRequestParameterMap extends
        AbstractUnmodifiableExternalContextMap {

    private static final String NULL = "null";

    private final PortletRequest request;

    private final Set parameterNames = new HashSet();

    /**
     * {@link PortletRequestParameterMap}です。
     * 
     * @param request
     */
    public PortletRequestParameterMap(final PortletRequest request) {
        this.request = request;
        for (final Enumeration names = request.getParameterNames(); names
                .hasMoreElements();) {
            parameterNames.add(names.nextElement());
        }
    }

    protected Object getAttribute(String key) {
        if (parameterNames.contains(key)) {
            final String value = request.getParameter(key);
            if (NULL.equals(value)) {
                return "";
            }
            return value;
        }
        return null;
    }

    protected Iterator getAttributeNames() {
        return parameterNames.iterator();
    }

}
