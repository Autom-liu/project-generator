package generator.core.core.exec.impl;

import java.nio.file.Path;
import java.util.List;

import org.springframework.util.StringUtils;

import generator.core.config.MainConfig;
import generator.core.config.ModuleConfig;
import generator.core.config.TableConfig;
import generator.core.config.template.ModuleTemplateConfig;
import generator.core.core.exec.MybatisExcutor;
import generator.core.utils.ConfigConverterUtil;

public class ModuleExcutorV2 extends ModuleExcutorImpl {


    public ModuleExcutorV2(MainConfig configuration, ModuleConfig moduleConfig, MybatisExcutor mybatisExcutor) {
        super(configuration, moduleConfig, mybatisExcutor);
    }

    @Override
    public ModuleTemplateConfig resolveTemplateConfig(Path path, String className) throws Exception {
        ModuleTemplateConfig moduleTemplateConfig = ConfigConverterUtil.getBaseTemplateConfig(configuration, moduleConfig.getModuleName(), ModuleTemplateConfig.class);
        String dtoName = className + "DTO";
        String voName = className + "VO";
        String queryName = className + "Query";
        String mapperName = className + "Mapper";
        String serviceName = className + "Service";
        String mapperVariable = StringUtils.uncapitalize(mapperName);
        moduleTemplateConfig.setBeanClassName(className);
        moduleTemplateConfig.setDtoClassName(dtoName);
        moduleTemplateConfig.setQueryClassName(queryName);
        moduleTemplateConfig.setVoClassName(voName);
        moduleTemplateConfig.setServiceClassName(serviceName);
        moduleTemplateConfig.setMapperClassName(mapperName);
        moduleTemplateConfig.setMapperVariable(mapperVariable);
        TableConfig tableConfig = searchTableConfig(className);
        moduleTemplateConfig.setColumns(tableConfig.getColumns());
        return moduleTemplateConfig;
    }

    private TableConfig searchTableConfig(String className) {
        List<TableConfig> tables = super.moduleConfig.getTables();
        for (TableConfig table : tables) {
            if (table.getDomainObjectName().equals(className)) {
                return table;
            }
        }
        return null;
    }
}
