package generator.core.core.exec.impl;

import generator.core.config.DatabaseConfig;
import generator.core.config.MainConfig;
import generator.core.config.ModuleConfig;
import generator.core.config.TableConfig;
import generator.core.config.TableColumn;
import generator.core.config.template.MybatisGenerateTemplateConfig;
import generator.core.manager.ModulePathManager;
import generator.core.manager.PathManager;
import generator.core.utils.ConfigConverterUtil;
import generator.core.utils.DBUtil;
import generator.core.utils.GeneratorUtil;
import generator.core.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisExcutorV2 extends MybatisExcutorImpl {

    private ModulePathManager modulePath;

    public MybatisExcutorV2(MainConfig configuration, ModuleConfig moduleConfig) {
        super(configuration, moduleConfig);
        Map<String, ModulePathManager> modulePathManagerMap = super.pathManager.getModulePathManagerMap();
        this.modulePath = modulePathManagerMap.get(moduleConfig.getModuleName());
    }

    private static final String beginningDelimiter = "`";

    private static final String endingDelimiter = "`";

    private static final Map<String, String> javaTypeMapping = new HashMap<>();

    private static final Map<String, String> jdbcTypeMapping = new HashMap<>();

    static {
        javaTypeMapping.put("int", "Integer");
        javaTypeMapping.put("tinyint", "Integer");
        javaTypeMapping.put("integer", "Integer");
        javaTypeMapping.put("bigint", "Long");
        javaTypeMapping.put("float", "Float");
        javaTypeMapping.put("double", "Double");
        javaTypeMapping.put("decimal", "BigDecimal");
        javaTypeMapping.put("tinyInt", "Integer");
        javaTypeMapping.put("varchar", "String");
        javaTypeMapping.put("char", "String");
        javaTypeMapping.put("date", "Date");
        javaTypeMapping.put("dateTime", "Date");
        javaTypeMapping.put("timestamp", "Date");

        jdbcTypeMapping.put("INT", "INTEGER");
    }

    /**
     * 自己实现的Mybatis代码生成器
     * @throws Exception
     */
    @Override
    public void generate() throws Exception {
        // 确保目录已创建
        super.beforeGenerate();
        MybatisGenerateTemplateConfig templateConfig = ConfigConverterUtil.getBaseTemplateConfig(configuration, moduleConfig.getModuleName(), MybatisGenerateTemplateConfig.class);
        DatabaseConfig databaseConfig = configuration.getDatabaseConfig();
        // 获取JDBC数据库连接
        DBUtil.getConnection(databaseConfig);
        final List<TableConfig> tables = moduleConfig.getTables();
        for (TableConfig tableConfig : tables) {
            generateOne(tableConfig, templateConfig);
        }
    }

    private void generateOne(TableConfig tableConfig, MybatisGenerateTemplateConfig templateConfig) throws IOException {
        List<TableColumn> tableColumns = DBUtil.descColumnList(tableConfig);
        initTableColumns(tableColumns);
        tableConfig.setColumns(tableColumns);
        generateBean(tableConfig, templateConfig);
        generateMapper(templateConfig);
        generateXml(templateConfig);
    }

    private void generateXml(MybatisGenerateTemplateConfig templateConfig) throws IOException {
        String className = templateConfig.getDomainObjectName();
        String templateString = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("mapperxml.ftl"));
        GeneratorUtil.putTemplate(className + "xml", templateString);
        Path toPath = modulePath.getModuleResourcePath().resolve("mapper").resolve(className + "Mapper.xml");
        GeneratorUtil.generate(className + "xml", toPath, templateConfig);
    }

    private void generateMapper(MybatisGenerateTemplateConfig templateConfig) throws IOException {
        final String className = templateConfig.getDomainObjectName() + "Mapper";
        String templateString = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("BeanMapper.ftl"));
        GeneratorUtil.putTemplate(className, templateString);
        Path toPath = modulePath.getModuleMapperPath().resolve(className + ".java");
        GeneratorUtil.generate(className, toPath, templateConfig);
    }

    private void generateBean(TableConfig tableConfig, MybatisGenerateTemplateConfig templateConfig) throws IOException {
        final String className = tableConfig.getDomainObjectName();
        String templateString = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("Bean.ftl"));
        GeneratorUtil.putTemplate(className, templateString);
        Path toPath = modulePath.getModuleBeanPath().resolve(className + ".java");
        BeanUtils.copyProperties(tableConfig, templateConfig);
        GeneratorUtil.generate(className, toPath, templateConfig);
    }

    private void initTableColumns(List<TableColumn> tableColumns) {
        for (TableColumn tableColumn : tableColumns) {
            final String javaField = StringUtil.lineToHump(StringUtils.uncapitalize(tableColumn.getColumn()));
            final String javaType = javaTypeMapping.get(tableColumn.getDataType());
            tableColumn.setJavaField(javaField);
            tableColumn.setJavaType(javaType);
            tableColumn.setOriginColumn(tableColumn.getColumn());
            tableColumn.setColumn(beginningDelimiter + tableColumn.getColumn() + endingDelimiter);
            String jdbcType = tableColumn.getDataType().toUpperCase();
            if (jdbcTypeMapping.containsKey(jdbcType)) {
                jdbcType = jdbcTypeMapping.get(jdbcType);
            }
            tableColumn.setJdbcType(jdbcType);
        }
    }
}
