package generator.core.pom;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import generator.config.BasePath;
import generator.config.pom.PomConfig;

public class CommonServicePomGenerator extends ParentPomGenerator {
	
	private static CommonServicePomGenerator instance;
	
	private Path projectPath;
	
	protected CommonServicePomGenerator() {
		projectPath = super.projectPath.resolve(PomConfig.CommonService.getArtifactId());
	}
	
	public static CommonServicePomGenerator getInstance() {
		if(instance == null) {
			return new CommonServicePomGenerator();
		}
		return instance;
	}

	@Override
	protected Object getConfig() {
		Map<String, String> ret = new HashMap<>();
		ret.put("artifactId", PomConfig.CommonService.getArtifactId());
		ret.put("parentGroup", PomConfig.getGroupId());
		ret.put("parentArtifact", PomConfig.getArtifactId());
		ret.put("commonGroup", PomConfig.getGroupId());
		ret.put("commonArtifact", PomConfig.Common.getArtifactId());
		return ret;
	}

	@Override
	protected void success() {
		super.success();
		PomConfig.CommonService.setCommonPath(projectPath);
	}

	@Override
	public Path getTemplatePath() {
		Path path = super.templatePath.resolve("commonservice.ftl");
		return BasePath.TEST_RESOURCE.resolve(path);
	}

	@Override
	protected Path getTargetPath() {
		return this.projectPath.resolve("pom.xml");
	}
	
}
