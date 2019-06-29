package generator.core.pom;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import generator.config.BasePath;
import generator.config.pom.ModulePomConfigFactory.ModulePomConfig;
import generator.config.pom.PomConfig;

public class ModulePomGenerator extends ParentPomGenerator {
	
	private ModulePomConfig moduleConfig;
	
	protected Path projectPath;
	
	public ModulePomGenerator(ModulePomConfig moduleConfig) {
		this.moduleConfig = moduleConfig;
		this.projectPath = super.projectPath.resolve(moduleConfig.getArtifactId());
	}

	@Override
	public Path getTemplatePath() {
		Path path = super.templatePath.resolve( moduleConfig.getModuleName() + ".ftl");
		return BasePath.TEST_RESOURCE.resolve(path);
	}

	@Override
	protected Object getConfig() {
		Map<String, String> ret = new HashMap<>();
		ret.put("parentGroup", PomConfig.getGroupId());
		ret.put("parentArtifact", PomConfig.getArtifactId());
		ret.put("artifactId", moduleConfig.getArtifactId());
		ret.put("dependentGroup", PomConfig.getGroupId());
		ret.put("denpentArtifact", PomConfig.CommonService.getArtifactId());
		ret.put("mainClass", moduleConfig.getMainClass());
		ret.put("basePackage", PomConfig.getBasePackage());
		return ret;
	}

	@Override
	protected Path getTargetPath() {
		return this.projectPath.resolve("pom.xml");
	}

	@Override
	protected void success() {
		super.success();
		moduleConfig.setProjectPath(projectPath);
	}
	
}
