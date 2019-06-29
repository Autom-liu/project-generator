package generator.core.classes;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import generator.config.BasePath;
import generator.config.pom.PomConfig;

public class CommonProjectGenerator extends AbstractProjectGenerator {
	
	protected Path projectPath;
	
	public CommonProjectGenerator() {
		super();
		projectPath = PomConfig.Common.getCommonPath();
	}
	

	@Override
	public void generate() {
		super.generate();
	}


	@Override
	protected Path getTemplateBasepath() {
		return super.templateBase.resolve("common");
	}

	@Override
	protected Path getTargetBasepath() {
		String packagePath = PomConfig.getGroupId().replace(".", "/") + "/" + PomConfig.Common.getModuleName();
		Path path = BasePath.JAVA_PATH.resolve(packagePath);
		return projectPath.resolve(path);
	}

	@Override
	protected Path getProjectPath() {
		return projectPath;
	}

	@Override
	protected Object getConfig() {
		Map<String, String> ret = new HashMap<>();
		ret.put("basePackage", PomConfig.getGroupId());
		ret.put("moduleName", PomConfig.Common.getModuleName());
		return ret;
	}

	@Override
	protected void success() {
		
	}

}
