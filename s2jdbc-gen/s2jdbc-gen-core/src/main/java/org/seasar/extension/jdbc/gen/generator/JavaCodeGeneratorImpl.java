/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.extension.jdbc.gen.generator;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import org.seasar.extension.jdbc.gen.JavaCode;
import org.seasar.extension.jdbc.gen.JavaCodeGenerator;
import org.seasar.extension.jdbc.gen.util.ConfigurationUtil;
import org.seasar.extension.jdbc.gen.util.JavaFileUtil;
import org.seasar.extension.jdbc.gen.util.TemplateUtil;
import org.seasar.extension.jdbc.gen.util.WriterUtil;
import org.seasar.framework.util.FileOutputStreamUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author taedium
 * 
 */
public class JavaCodeGeneratorImpl implements JavaCodeGenerator {

    protected Configuration configuration;

    protected File destDir;

    protected String encoding;

    public JavaCodeGeneratorImpl(Configuration configuration, File destDir,
            String encoding) {
        this.configuration = configuration;
        this.destDir = destDir;
        this.encoding = encoding;
    }

    public void generate(JavaCode javaCode) {
        File file = createJavaFile(javaCode);
        Writer writer = openWriter(file);
        try {
            Template template = ConfigurationUtil.getTemplate(configuration,
                    javaCode.getTemplateName());
            TemplateUtil.process(template, javaCode, writer);
        } finally {
            WriterUtil.close(writer);
        }
    }

    protected File createJavaFile(JavaCode javaCode) {
        File packageDir = new File(destDir, JavaFileUtil
                .getPackageDirName(javaCode.getClassName()));
        if (!packageDir.exists()) {
            packageDir.mkdirs();
        }
        return new File(destDir, JavaFileUtil.getJavaFileName(javaCode
                .getClassName()));
    }

    protected Writer openWriter(File file) {
        return new OutputStreamWriter(FileOutputStreamUtil.create(file),
                Charset.forName(encoding));
    }

}