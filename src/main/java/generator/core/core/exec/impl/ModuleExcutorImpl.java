package generator.core.core.exec.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import generator.core.config.MainConfig;
import generator.core.config.ModuleConfig;
import generator.core.config.TableConfig;
import generator.core.config.template.ApplicationYmlConfig;
import generator.core.config.template.BasePomTemplateConfig;
import generator.core.config.template.DependentPomTemplateConfig;
import generator.core.config.template.ModuleTemplateConfig;
import generator.core.config.template.ModuleTemplateConfig.JavaFieldDesc;
import generator.core.core.exec.ModuleExcutor;
import generator.core.core.exec.MybatisExcutor;
import generator.core.core.exec.YmlExcutor;
import generator.core.manager.ModulePathManager;
import generator.core.manager.PathManager;
import generator.core.resource.support.JavaFileReader;
import generator.core.utils.ConfigConverterUtil;
import generator.core.utils.GeneratorUtil;
import generator.core.utils.TemplateKey;

public class ModuleExcutorImpl extends ParentExcutor implements ModuleExcutor, YmlExcutor {
	
	private MybatisExcutor mybatisExcutor;
	
	private ModuleConfig moduleConfig;
	
	private ModulePathManager modulePath;

	public ModuleExcutorImpl(MainConfig configuration, ModuleConfig moduleConfig) {
		super(configuration);
		this.moduleConfig = moduleConfig;
		this.mybatisExcutor = new MybatisExcutorImpl(configuration, moduleConfig);
		Map<String, ModulePathManager> modulePathManagerMap = super.pathManager.getModulePathManagerMap();
		this.modulePath = modulePathManagerMap.get(moduleConfig.getModuleName());
	}

	@Override
	protected void pomGenerate() throws Exception {
		String moduleName = configuration.getArtifactId() + "-" + moduleConfig.getModuleName();
		String dependModule = configuration.getArtifactId() + "-common-service";
		DependentPomTemplateConfig pomTemplateConfig = ConfigConverterUtil.getBasePomTemplateConfig(configuration, moduleName, DependentPomTemplateConfig.class);
		BasePomTemplateConfig dependent = ConfigConverterUtil.getBasePomTemplateConfig(configuration, dependModule);
		pomTemplateConfig.setDependents(Collections.singletonList(dependent));
		pomTemplateConfig.setMainClass(moduleConfig.getMainClass());
		Path toPath = modulePath.getModuleBasePath().resolve("pom.xml");
		String templateString = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("modulepom.ftl"));
		GeneratorUtil.putTemplate(moduleName, templateString);
		GeneratorUtil.generate(moduleName, toPath, pomTemplateConfig);
		
	}

	@Override
	public void generate() throws Exception {
		pomGenerate();
		mainClassGenerate();
		mybatisExcutor.generate();
		Set<String> nameSet = moduleConfig.getTables().stream().map(TableConfig::getDomainObjectName).collect(Collectors.toSet());
		for(String name : nameSet) {
			Path path = modulePath.getModuleBeanPath().resolve(name + ".java");
			ModuleTemplateConfig templateConfig = resolveTemplateConfig(path, name);
			dtoGenerate(templateConfig);
			queryGenerate(templateConfig);
			serviceGenerate(templateConfig);
			voGenerate(templateConfig);
			enumsGenerate(templateConfig);
			webGenerate(templateConfig);
		}
		applicationYmlGenerate();
	}

	@Override
	public ModuleTemplateConfig resolveTemplateConfig(Path path, String className) throws Exception {
		JavaFileReader javaFileReader = new JavaFileReader();
		List<JavaFieldDesc> javaFileds = javaFileReader.readJavaFile(path);
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
		moduleTemplateConfig.setJavaFields(javaFileds);
		moduleTemplateConfig.setServiceClassName(serviceName);
		moduleTemplateConfig.setMapperClassName(mapperName);
		moduleTemplateConfig.setMapperVariable(mapperVariable);
		return moduleTemplateConfig;
	}

	@Override
	public void dtoGenerate(ModuleTemplateConfig templateConfig) throws Exception {
		templateConfig.setClassName(templateConfig.getDtoClassName());
		String className = templateConfig.getClassName();
		Path toPath = modulePath.getModuleDtoPath().resolve(className + ".java");
		String templateString = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("BeanDTO.ftl"));
		GeneratorUtil.putTemplate(className, templateString);
		GeneratorUtil.generate(className, toPath, templateConfig);
	}

	@Override
	public void queryGenerate(ModuleTemplateConfig templateConfig) throws Exception {
		templateConfig.setClassName(templateConfig.getQueryClassName());
		String className = templateConfig.getClassName();
		Path toPath = modulePath.getModuleQueryPath().resolve(className + ".java");
		String templateString = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("BeanQuery.ftl"));
		GeneratorUtil.putTemplate(className, templateString);
		GeneratorUtil.generate(className, toPath, templateConfig);
		
	}

	@Override
	public void serviceGenerate(ModuleTemplateConfig templateConfig) throws Exception {
		templateConfig.setClassName(templateConfig.getServiceClassName());
		String className = templateConfig.getClassName();
		Path toPath1 = modulePath.getModuleServicePath().resolve(className + ".java");
		String templateString1 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("BeanService.ftl"));
		GeneratorUtil.putTemplate(className, templateString1);
		GeneratorUtil.generate(className, toPath1, templateConfig);
		
		templateConfig.setClassName(className + "Impl");
		className = className + "Impl";
		Path toPath2 = modulePath.getModuleServiceImplPath().resolve(className + ".java");
		String templateString2 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("BeanServiceImpl.ftl"));
		GeneratorUtil.putTemplate(className, templateString2);
		GeneratorUtil.generate(className, toPath2, templateConfig);
		
	}

	@Override
	public void voGenerate(ModuleTemplateConfig templateConfig) throws Exception {
		templateConfig.setClassName(templateConfig.getVoClassName());
		String className = templateConfig.getClassName();
		Path toPath = modulePath.getModuleVoPath().resolve(className + ".java");
		String templateString = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("BeanVO.ftl"));
		GeneratorUtil.putTemplate(className, templateString);
		GeneratorUtil.generate(className, toPath, templateConfig);
		
	}

	private void enumsGenerate(ModuleTemplateConfig templateConfig) throws IOException {

		Path toPath1 = modulePath.getModuleEnumsPath().resolve("ErrorEnum.java");
		String templateString1 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("ErrorEnum.ftl"));
		GeneratorUtil.putTemplate(TemplateKey.ERROR_ENUM, templateString1);
		GeneratorUtil.generate(TemplateKey.ERROR_ENUM, toPath1, templateConfig);
	}

	@Override
	public void webGenerate(ModuleTemplateConfig templateConfig) throws Exception {
		File file = modulePath.getModuleWebPath().toFile();
		if(!file.exists()) {
			file.mkdirs();
		}
	}

	@Override
	public void mainClassGenerate() throws Exception {
		String mainClass = moduleConfig.getMainClass();
		Path toPath = modulePath.getModuleMainClassPath().resolve(mainClass + ".java");
		ModuleTemplateConfig moduleTemplateConfig = ConfigConverterUtil.getBaseTemplateConfig(configuration, moduleConfig.getModuleName(), ModuleTemplateConfig.class);
		moduleTemplateConfig.setClassName(mainClass);
		String templateString = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("MainApplication.ftl"));
		GeneratorUtil.putTemplate(mainClass, templateString);
		GeneratorUtil.generate(mainClass, toPath, moduleTemplateConfig);
		
	}

	@Override
	public void applicationYmlGenerate() throws Exception {
		ApplicationYmlConfig ymlConfig = ConfigConverterUtil.getApplicationYmlConfig(configuration, moduleConfig);
		String basePackage = configuration.getBasePackage();
		String moduleName = moduleConfig.getModuleName();
		ymlConfig.setBeanPackage(basePackage + "." + moduleName + ".bean");
		Path toPath1 = modulePath.getModuleResourcePath().resolve("application.yml");
		String templateString1 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("application.ftl"));
		GeneratorUtil.putTemplate(moduleName + TemplateKey.APPLICATION_YML, templateString1);
		GeneratorUtil.generate(moduleName + TemplateKey.APPLICATION_YML, toPath1, ymlConfig);
		
		Path toPath2 = modulePath.getModuleResourcePath().resolve("application-dev.yml");
		String templateString2 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("application-dev.ftl"));
		GeneratorUtil.putTemplate(moduleName + TemplateKey.APPLICATION_DEV_YML, templateString2);
		GeneratorUtil.generate(moduleName + TemplateKey.APPLICATION_DEV_YML, toPath2, ymlConfig);
		
		Path toPath3 = modulePath.getModuleResourcePath().resolve("application-prod.yml");
		String templateString3 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("application-prod.ftl"));
		GeneratorUtil.putTemplate(moduleName + TemplateKey.APPLICATION_PROD_YML, templateString3);
		GeneratorUtil.generate(moduleName + TemplateKey.APPLICATION_PROD_YML, toPath3, ymlConfig);
		
		Path toPath4 = modulePath.getModuleResourcePath().resolve("logback-spring.xml");
		String templateString4 = GeneratorUtil.readTemplateString(PathManager.resolveTemplatePath("logback-spring.ftl"));
		GeneratorUtil.putTemplate(moduleName + TemplateKey.LOGBACK_SPRING, templateString4);
		GeneratorUtil.generate(moduleName + TemplateKey.LOGBACK_SPRING, toPath4, ymlConfig);
		
	}

}
