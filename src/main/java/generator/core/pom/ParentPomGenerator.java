package generator.core.pom;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import generator.config.BasePath;
import generator.config.pom.PomConfig;
import lombok.Getter;

@Getter
public class ParentPomGenerator extends AbstractPomGenerator {
	
	private boolean exist;
	
	protected Path projectPath;
	
	protected Path templatePath;
	
	private static ParentPomGenerator instance;

	public static ParentPomGenerator getInstance() {
		if(instance == null) {
			ParentPomGenerator instance = new ParentPomGenerator();
			return instance;
		}
		return instance;
	}
	
	protected ParentPomGenerator() {
		this.projectPath = Paths.get("../", PomConfig.getArtifactId());
		this.templatePath = Paths.get("generator", "template", "pom");
	}
	
	@Override
	protected Object getConfig() {
		Map<String, String> ret = new HashMap<>();
		ret.put("groupId", PomConfig.getGroupId());
		ret.put("artifactId", PomConfig.getArtifactId());
		ret.put("projectName", PomConfig.getProjectName());
		ret.put("description", PomConfig.getDescription());
		return ret;
	}
	
	@Override
	protected void success() {
		PomConfig.setProjectPath(projectPath);
	}

	/**
	 * To make sure that the generate method would not process
	 * if the generate method has been processed
	 */
	@Override
	public void generate() {
		if(!exist) {
			super.generate();
		}
	}

	@Override
	public Path getTemplatePath() {
		Path path = templatePath.resolve("parentpom.ftl");
		return BasePath.TEST_RESOURCE.resolve(path);
	}

	@Override
	protected Path getTargetPath() {
		return this.projectPath.resolve("pom.xml");
	}
	
}
