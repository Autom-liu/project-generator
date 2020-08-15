package generator.core.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.jasypt.util.text.BasicTextEncryptor;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.GeneratedKey;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.PluginConfiguration;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.springframework.util.StringUtils;

import generator.core.config.DatabaseConfig;
import generator.core.config.MainConfig;
import generator.core.config.ModuleConfig;
import generator.core.config.TableConfig;
import generator.core.config.template.ApplicationYmlConfig;
import generator.core.config.template.BasePomTemplateConfig;
import generator.core.config.template.BaseTemplateConfig;
import generator.core.manager.ModulePathManager;
import generator.core.manager.PathManager;

public class ConfigConverterUtil {
	
	private ConfigConverterUtil() {}
	
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
	
	public static ApplicationYmlConfig getApplicationYmlConfig(MainConfig config, ModuleConfig moduleConfig) {
		ApplicationYmlConfig applicationYmlConfig = new ApplicationYmlConfig();
		DatabaseConfig dbConfig = config.getDatabaseConfig();
		String connectIp = dbConfig.getConnectIp();
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		String encryKey = getEncryKey(config);
		applicationYmlConfig.setEncryKey(encryKey);
		textEncryptor.setPassword(encryKey);
		String encodedIp = textEncryptor.encrypt(connectIp);
		applicationYmlConfig.setEncryDataSourceIp(encodedIp);
		String password = dbConfig.getPassword();
		String encodePassword = textEncryptor.encrypt(password);
		applicationYmlConfig.setEncryPassword(encodePassword);
		applicationYmlConfig.setUserId(dbConfig.getUserId());
		if(moduleConfig != null) {
			applicationYmlConfig.setDevLogPath(StringUtil.defaultString(moduleConfig.getDevLogPath()));
			applicationYmlConfig.setProdLogPath(StringUtil.defaultString(moduleConfig.getProdLogPath()));
			applicationYmlConfig.setPort(StringUtil.defaultString(moduleConfig.getPort()));
			applicationYmlConfig.setContextPath(StringUtil.defaultString(moduleConfig.getContextPath()));
			applicationYmlConfig.setApplicationName(StringUtil.defaultString(moduleConfig.getApplicationName()));
		}
		
		return applicationYmlConfig;
	}
	
	private static String getEncryKey(MainConfig config) {
		String encryKey = config.getEncryKey();
		if(StringUtils.isEmpty(encryKey)) {
			return RandomStringUtils.randomAlphanumeric(16);
		}
		return encryKey;
	}
	
	public static Configuration getMybatisConfiguration(MainConfig config, ModuleConfig moduleConfig, PathManager pathManager) {
		Configuration configuration = new Configuration();
		Context context = new Context(ModelType.FLAT);
		context.setId("DB2Tables");
		context.setTargetRuntime("MyBatis3");
		context.addProperty("beginningDelimiter", "`");
		context.addProperty("endingDelimiter", "`");
		String moduleName = moduleConfig.getModuleName();
		DatabaseConfig databaseConfig = config.getDatabaseConfig();
		JDBCConnectionConfiguration connectionConfiguration = getJDBCConnectionConfiguration(databaseConfig);
		context.setJdbcConnectionConfiguration(connectionConfiguration);
		PluginConfiguration pluginConfiguration = getPluginConfiguration(config);
		context.addPluginConfiguration(pluginConfiguration);
		CommentGeneratorConfiguration commentConfiguration = getCommentGeneratorConfiguration();
		context.setCommentGeneratorConfiguration(commentConfiguration);
		JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = getJavaModelGeneratorConfiguration(config, moduleName, pathManager);
		context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
		SqlMapGeneratorConfiguration sqlMapConfiguration = getSqlMapGeneratorConfiguration(moduleName, pathManager);
		context.setSqlMapGeneratorConfiguration(sqlMapConfiguration);
		JavaClientGeneratorConfiguration javaClientConfiguration = getJavaClientGeneratorConfiguration(config, moduleName, pathManager);
		context.setJavaClientGeneratorConfiguration(javaClientConfiguration);
		List<TableConfig> tables = moduleConfig.getTables();
		for(TableConfig tableCfg : tables) {
			TableConfiguration tableConfiguration = getTableConfiguration(tableCfg, context);
			context.addTableConfiguration(tableConfiguration);
		}
		configuration.addContext(context);
		return configuration;
	}
	
	private static JDBCConnectionConfiguration getJDBCConnectionConfiguration(DatabaseConfig databaseConfig) {
		JDBCConnectionConfiguration connectionConfiguration = new JDBCConnectionConfiguration();
		String connectUrl = databaseConfig.getConnectUrl();
		String decodedUrl = connectUrl.replaceAll("\\$\\{connectIp\\}", databaseConfig.getConnectIp());
		connectionConfiguration.setConnectionURL(decodedUrl);
		connectionConfiguration.setDriverClass(databaseConfig.getDriverClass());
		connectionConfiguration.setUserId(databaseConfig.getUserId());
		connectionConfiguration.setPassword(databaseConfig.getPassword());
		return connectionConfiguration;
	}

	private static PluginConfiguration getPluginConfiguration(MainConfig config) {
		PluginConfiguration pluginConfiguration = new PluginConfiguration();
		String basePackage = config.getBasePackage();
		pluginConfiguration.setConfigurationType("tk.mybatis.mapper.generator.MapperPlugin");
		pluginConfiguration.addProperty("type", "tk.mybatis.mapper.generator.MapperPlugin");
		pluginConfiguration.addProperty("mappers", basePackage + ".common.service.Mapper");
		return pluginConfiguration;
	}
	
	private static CommentGeneratorConfiguration getCommentGeneratorConfiguration() {
		CommentGeneratorConfiguration commentConfiguration = new CommentGeneratorConfiguration();
		commentConfiguration.setConfigurationType("generator.core.core.MyCommentGenerator");
		return commentConfiguration;
	}
	
	private static JavaModelGeneratorConfiguration getJavaModelGeneratorConfiguration(MainConfig config, String moduleName, PathManager pathManager) {
		JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
		Map<String, ModulePathManager> modulePathManagerMap = pathManager.getModulePathManagerMap();
		ModulePathManager modulePathManager = modulePathManagerMap.get(moduleName);
		String basePackage = config.getBasePackage();
		String targetPackage = basePackage + "." + moduleName + ".bean";
		String targetProject = modulePathManager.getModuleBasePath().resolve(PathManager.RELATEED_JAVA_PATH).toAbsolutePath().toString();
		javaModelGeneratorConfiguration.setTargetPackage(targetPackage);
		javaModelGeneratorConfiguration.setTargetProject(targetProject);
		javaModelGeneratorConfiguration.addProperty("enableSubPackages", "true");
		// javaModelGeneratorConfiguration.addProperty("trimStrings", "false");
		
		return javaModelGeneratorConfiguration;
	}
	
	private static SqlMapGeneratorConfiguration getSqlMapGeneratorConfiguration(String moduleName, PathManager pathManager) {
		SqlMapGeneratorConfiguration sqlMapConfiguration = new SqlMapGeneratorConfiguration();
		Map<String, ModulePathManager> modulePathManagerMap = pathManager.getModulePathManagerMap();
		ModulePathManager modulePathManager = modulePathManagerMap.get(moduleName);
		String targetProject = modulePathManager.getModuleBasePath().resolve(PathManager.RELATEED_RESOURCE_PATH).toAbsolutePath().toString();
		sqlMapConfiguration.setTargetPackage("mapper");
		sqlMapConfiguration.setTargetProject(targetProject);
		sqlMapConfiguration.addProperty("enableSubPackages", "true");
		
		return sqlMapConfiguration;
	}
	
	private static JavaClientGeneratorConfiguration getJavaClientGeneratorConfiguration(MainConfig config, String moduleName, PathManager pathManager) {
		JavaClientGeneratorConfiguration javaClientConfiguration = new JavaClientGeneratorConfiguration();
		Map<String, ModulePathManager> modulePathManagerMap = pathManager.getModulePathManagerMap();
		ModulePathManager modulePathManager = modulePathManagerMap.get(moduleName);
		String basePackage = config.getBasePackage();
		String targetPackage = basePackage + "." + moduleName + ".mapper";
		String targetProject = modulePathManager.getModuleBasePath().resolve(PathManager.RELATEED_JAVA_PATH).toAbsolutePath().toString();
		javaClientConfiguration.setConfigurationType("XMLMAPPER");
		javaClientConfiguration.setTargetPackage(targetPackage);
		javaClientConfiguration.setTargetProject(targetProject);
		
		return javaClientConfiguration;
	}
	
	private static TableConfiguration getTableConfiguration(TableConfig tableCfg, Context context) {
		TableConfiguration tableConfiguration = new TableConfiguration(context);
		tableConfiguration.setSchema("mybatis");
		tableConfiguration.setTableName(tableCfg.getTableName());
		tableConfiguration.setDomainObjectName(tableCfg.getDomainObjectName());
		tableConfiguration.setAllColumnDelimitingEnabled(true);
		if(tableCfg.isGeneratedKey()) {
			GeneratedKey generatedKey = new GeneratedKey(tableCfg.getKeyColumn(), tableCfg.getSqlStatement(), tableCfg.isIdentity(), null);
			tableConfiguration.setGeneratedKey(generatedKey);
		}
		return tableConfiguration;
	}
	
}
