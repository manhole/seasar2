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
package org.seasar.extension.jdbc.gen.command;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.seasar.extension.jdbc.gen.GenerationContext;
import org.seasar.extension.jdbc.gen.Generator;
import org.seasar.extension.jdbc.gen.exception.IllegalVersionValueRuntimeException;
import org.seasar.extension.jdbc.gen.generator.GeneratorImpl;
import org.seasar.framework.log.Logger;
import org.seasar.framework.util.StringUtil;
import org.seasar.framework.util.TextUtil;

/**
 * @author taedium
 * 
 */
public class GenerateVersionCommand extends AbstractCommand {

    protected static Logger logger = Logger
            .getLogger(GenerateVersionCommand.class);

    protected String schemaInfoColumnName = "VERSION";

    protected String schemaInfoTableName = "SCHEMA_INFO";

    protected File sqlFileDestDir = new File("db", "dml");

    protected String sqlFileEncoding = "UTF-8";

    protected char statementDelimiter = ';';

    /** テンプレートファイルのエンコーディング */
    protected String templateFileEncoding = "UTF-8";

    /** テンプレートファイルの格納ディレクトリ */
    protected File templateFilePrimaryDir = null;

    protected File versionFileDestDir = new File("db");

    protected String versionFileName = "version.txt";

    protected String versionTemplateFileName = "txt/version.ftl";

    protected String updateVersionSqlFileName = "update-version.sql";

    protected String updateVersionTemplateFileName = "sql/update-version.ftl";

    protected Generator generator;

    public GenerateVersionCommand() {
    }

    public String getSchemaInfoColumnName() {
        return schemaInfoColumnName;
    }

    public void setSchemaInfoColumnName(String schemaInfoColumnName) {
        this.schemaInfoColumnName = schemaInfoColumnName;
    }

    public String getSchemaInfoTableName() {
        return schemaInfoTableName;
    }

    public void setSchemaInfoTableName(String schemaInfoTableName) {
        this.schemaInfoTableName = schemaInfoTableName;
    }

    public File getSqlFileDestDir() {
        return sqlFileDestDir;
    }

    public void setSqlFileDestDir(File sqlFileDestDir) {
        this.sqlFileDestDir = sqlFileDestDir;
    }

    public String getSqlFileEncoding() {
        return sqlFileEncoding;
    }

    public void setSqlFileEncoding(String sqlFileEncoding) {
        this.sqlFileEncoding = sqlFileEncoding;
    }

    /**
     * @return Returns the statementDelimiter.
     */
    public char getStatementDelimiter() {
        return statementDelimiter;
    }

    /**
     * @param statementDelimiter
     *            The statementDelimiter to set.
     */
    public void setStatementDelimiter(char statementDelimiter) {
        this.statementDelimiter = statementDelimiter;
    }

    public String getTemplateFileEncoding() {
        return templateFileEncoding;
    }

    public void setTemplateFileEncoding(String templateFileEncoding) {
        this.templateFileEncoding = templateFileEncoding;
    }

    public File getTemplateFilePrimaryDir() {
        return templateFilePrimaryDir;
    }

    public void setTemplateFilePrimaryDir(File templateFilePrimaryDir) {
        this.templateFilePrimaryDir = templateFilePrimaryDir;
    }

    /**
     * @return Returns the versionFileDestDir.
     */
    public File getVersionFileDestDir() {
        return versionFileDestDir;
    }

    /**
     * @param versionFileDestDir
     *            The versionFileDestDir to set.
     */
    public void setVersionFileDestDir(File versionFileDestDir) {
        this.versionFileDestDir = versionFileDestDir;
    }

    public String getVersionFileName() {
        return versionFileName;
    }

    public void setVersionFileName(String versionFileName) {
        this.versionFileName = versionFileName;
    }

    public String getVersionTemplateFileName() {
        return versionTemplateFileName;
    }

    public void setVersionTemplateFileName(String versionTemplateFileName) {
        this.versionTemplateFileName = versionTemplateFileName;
    }

    public String getUpdateVersionSqlFileName() {
        return updateVersionSqlFileName;
    }

    public void setUpdateVersionSqlFileName(String updateVersionSqlFileName) {
        this.updateVersionSqlFileName = updateVersionSqlFileName;
    }

    public String getUpdateVersionTemplateFileName() {
        return updateVersionTemplateFileName;
    }

    public void setUpdateVersionTemplateFileName(
            String updateVersionTemplateFileName) {
        this.updateVersionTemplateFileName = updateVersionTemplateFileName;
    }

    @Override
    protected void doValidate() {
    }

    @Override
    protected void doInit() {
        generator = createGenerator();
    }

    @Override
    protected void doExecute() {
        int currentVersion = readVersion();
        int nextVersion = currentVersion + 1;
        generateUpdateVersionSql(nextVersion);
        generateVersion(nextVersion);
        logger.log("DS2JDBCGen0004", new Object[] { currentVersion,
                nextVersion, updateVersionSqlFileName });
    }

    @Override
    protected void doDestroy() {
    }

    protected int readVersion() {
        File versionFile = new File(versionFileDestDir, versionFileName);
        if (!versionFile.exists()) {
            return 0;
        }
        String value = TextUtil.readUTF8(versionFile);
        if (StringUtil.isNumber(value)) {
            return Integer.valueOf(value.trim());
        }
        throw new IllegalVersionValueRuntimeException(versionFileName, value);
    }

    protected Generator createGenerator() {
        return new GeneratorImpl(templateFileEncoding, templateFilePrimaryDir);
    }

    protected void generateUpdateVersionSql(int version) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("version", version);
        model.put("schemaInfoTableName", schemaInfoTableName);
        model.put("schemaInfoColumnName", schemaInfoColumnName);
        model.put("delimiter", statementDelimiter);
        GenerationContext context = new GenerationContext(model,
                sqlFileDestDir, new File(sqlFileDestDir,
                        updateVersionSqlFileName),
                updateVersionTemplateFileName, sqlFileEncoding, true);
        generator.generate(context);
    }

    protected void generateVersion(int version) {
        Map<String, Integer> model = new HashMap<String, Integer>();
        model.put("version", version);
        GenerationContext context = new GenerationContext(model,
                sqlFileDestDir, new File(versionFileDestDir, versionFileName),
                versionTemplateFileName, "UTF-8", true);
        generator.generate(context);
    }

    protected Logger getLogger() {
        return logger;
    }
}
