package generator.core.utils;

import generator.core.config.MainConfig;
import generator.core.config.template.BasePomTemplateConfig;
import generator.core.config.template.BaseTemplateConfig;

public class ConfigConverterUtil {
	
	
	public static BaseTemplateConfig getBaseTemplateConfig(MainConfig config, String moduleName) {
		BaseTemplateConfig baseTemplateConfig = new BaseTemplateConfig();
		String basePackage = config.getBasePackage();
		baseTemplateConfig.setBasePackage(basePackage);
		baseTemplateConfig.setModuleName(moduleName);
		return baseTemplateConfig;
	}

	public static<T extends BaseTemplateConfig> T getBaseTemplateConfig(MainConfig config, String moduleName, Class<T> clazz) throws Exception {
		T instance = clazz.newInstance();
		
		String basePackage = config.getBasePackage();
		instance.setBasePackage(basePackage);
		instance.setModuleName(moduleName);
		return instance;
	}
	
	public static BasePomTemplateConfig getBasePomTemplateConfig(MainConfig configuration, String moduleName) {
		BasePomTemplateConfig basePomTemplateConfig = new BasePomTemplateConfig();
		basePomTemplateConfig.setGroupId(configuration.getGroupId());
		basePomTemplateConfig.setArtifactId(configuration.getArtifactId());
		basePomTemplateConfig.setModuleName(moduleName);
		basePomTemplateConfig.setVersion(configuration.getVersion());
		basePomTemplateConfig.setDescription(configuration.getDescription());
		return basePomTemplateConfig;
	}
	
	public static<T extends BasePomTemplateConfig> T getBasePomTemplateConfig(MainConfig configuration, String moduleName, Class<T> clazz) throws Exception {
		T basePomTemplateConfig = clazz.newInstance();
		basePomTemplateConfig.setGroupId(configuration.getGroupId());
		basePomTemplateConfig.setArtifactId(configuration.getArtifactId());
		basePomTemplateConfig.setModuleName(moduleName);
		basePomTemplateConfig.setVersion(configuration.getVersion());
		basePomTemplateConfig.setDescription(configuration.getDescription());
		return basePomTemplateConfig;
	}
}
