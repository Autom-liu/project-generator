package generator.core.yml;

import java.nio.file.Path;

import generator.config.BasePath;
import generator.config.pom.PomConfig;

public class CommonServiceYmlGenerator extends AbstractYmlGenerator {

	@Override
	protected Path getSourcePath() {
		
		return BasePath.RESOURCE_PATH.resolve("resources/service");
	}

	@Override
	protected Path getTargetPath() {
		Path projectPath = PomConfig.CommonService.getCommonPath();
		return projectPath.resolve(BasePath.RESOURCE_PATH);
	}
	
}
