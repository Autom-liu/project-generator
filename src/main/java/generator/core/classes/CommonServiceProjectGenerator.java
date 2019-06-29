package generator.core.classes;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import generator.config.BasePath;
import generator.config.pom.PomConfig;

public class CommonServiceProjectGenerator extends AbstractProjectGenerator {
	
	private Path projectPath;
	
	public CommonServiceProjectGenerator() {
		projectPath = PomConfig.CommonService.getCommonPath();
	}

	@Override
	protected Path getTemplateBasepath() {
		return super.templateBase.resolve("common-service/common");
	}

	@Override
	protected Path getProjectPath() {
		return projectPath;
	}

	@Override
	protected Object getConfig() {
		Map<String, String> ret = new HashMap<>();
		ret.put("basePackage", PomConfig.getGroupId());
		ret.put("moduleName", PomConfig.CommonService.getModuleName());
		return ret;
	}

	@Override
	protected Path getTargetBasepath() {
		String packagePath = PomConfig.getGroupId().replace(".", "/") + "/" + PomConfig.CommonService.getModuleName();
		Path path = BasePath.JAVA_PATH.resolve(packagePath);
		return projectPath.resolve(path);
	}

	@Override
	protected void success() {
		
	}

	@Override
	public void generate() {
		super.generate();
	}

}
