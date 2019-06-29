package generator.core.pom;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import generator.config.BasePath;
import generator.config.pom.PomConfig;

public class CommonPomGenerator extends ParentPomGenerator {
	
	private Path commonProjectPath;
	
	private static CommonPomGenerator generator;
	
	public static CommonPomGenerator getInstance() {
		if(generator == null) {
			generator = new CommonPomGenerator();
		}
		return generator;
	}
	
	protected CommonPomGenerator() {
		super();
		this.commonProjectPath = super.projectPath.resolve(PomConfig.Common.getArtifactId());
	}

	@Override
	protected void success() {
		super.success();
		PomConfig.Common.setCommonPath(commonProjectPath);
	}

	@Override
	public Path getTemplatePath() {
		Path path = super.templatePath.resolve("commonpom.ftl");
		return BasePath.TEST_RESOURCE.resolve(path);
	}

	@Override
	protected Object getConfig() {
		Map<String, String> ret = new HashMap<>();
		ret.put("parentGroup", PomConfig.getGroupId());
		ret.put("parentArtifact", PomConfig.getArtifactId());
		ret.put("artifactId", PomConfig.Common.getArtifactId());
		return ret;
	}

	@Override
	protected Path getTargetPath() {
		return this.commonProjectPath.resolve("pom.xml");
	}
	
}
