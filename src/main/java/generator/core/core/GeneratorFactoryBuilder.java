package generator.core.core;

import java.util.List;
import java.util.stream.Collectors;

import generator.core.config.MainConfig;
import generator.core.config.ModuleConfig;
import generator.core.manager.PathManager;
import generator.core.resource.ConfigReader;

public class GeneratorFactoryBuilder {

	
	public GeneratorFactory build(ConfigReader configReader) throws Exception {
		MainConfig configuration = configReader.parseConfiguration();
		List<ModuleConfig> enableModules = configuration.getModules().stream().filter(ModuleConfig::isEnable).collect(Collectors.toList());
		configuration.setModules(enableModules);
		GeneratorFactory generatorFactory = new GeneratorFactory(configuration);
		PathManager instance = PathManager.getInstance();
		String projectPath = configuration.getProjectPath();
		String artifactId = configuration.getArtifactId();
		String basePackage = configuration.getBasePackage();
		List<String> moduleNameList = configuration.getModules().stream().map(ModuleConfig::getModuleName).collect(Collectors.toList());
		instance.initializePath(projectPath, artifactId, basePackage, moduleNameList);
		return generatorFactory;
	}
	
}
