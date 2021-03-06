package generator.core.manager;

import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.Data;

@Data
public class ModulePathManager {
	
	private Path moduleBasePath;
	
	private Path moduleMainClassPath;
	
	private Path moduleResourcePath;
	
	private Path modulePath;
	
	private Path moduleBeanPath;
	
	private Path moduleDtoPath;
	
	private Path moduleMapperPath;
	
	private Path moduleQueryPath;
	
	private Path moduleServicePath;
	
	private Path moduleServiceImplPath;
	
	private Path moduleVoPath;
	
	private Path moduleWebPath;

	private Path moduleEnumsPath;
	
	public ModulePathManager(Path basePath, String projectName, String basePackage, String moduleName) {
		this.moduleBasePath = basePath.resolve(projectName + "-" + moduleName);
		Path path = Paths.get(basePackage, moduleName);
		this.modulePath = this.moduleBasePath.resolve(PathManager.RELATEED_JAVA_PATH).resolve(path);
		this.moduleMainClassPath = this.moduleBasePath.resolve(PathManager.RELATEED_JAVA_PATH).resolve(basePackage);
		this.moduleResourcePath = this.moduleBasePath.resolve(PathManager.RELATEED_RESOURCE_PATH);
		this.moduleBeanPath = this.modulePath.resolve("bean");
		this.moduleDtoPath = this.modulePath.resolve("dto");
		this.moduleMapperPath = this.modulePath.resolve("mapper");
		this.moduleQueryPath = this.modulePath.resolve("query");
		this.moduleServicePath = this.modulePath.resolve("service");
		this.moduleServiceImplPath = this.moduleServicePath.resolve("impl");
		this.moduleVoPath = this.modulePath.resolve("vo");
		this.moduleEnumsPath = this.modulePath.resolve("enums");
		this.moduleWebPath = this.modulePath.resolve("web/api");
	}

}
