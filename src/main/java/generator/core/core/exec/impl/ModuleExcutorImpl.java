package generator.core.core.exec.impl;

import java.io.File;
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
import generator.core.config.template.BasePomTemplateConfig;
import generator.core.config.template.DependentPomTemplateConfig;
import generator.core.config.template.ModuleTemplateConfig;
import generator.core.config.template.ModuleTemplateConfig.JavaFieldDesc;
import generator.core.core.exec.ModuleExcutor;
import generator.core.core.exec.MybatisExcutor;
import generator.core.manager.ModulePathManager;
import generator.core.manager.PathManager;
import generator.core.resource.support.JavaFileReader;
import generator.core.utils.ConfigConverterUtil;
import generator.core.utils.GeneratorUtil;

public class ModuleExcutorImpl extends ParentExcutor implements ModuleExcutor {
	
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
			webGenerate(templateConfig);
		}
		
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

}
