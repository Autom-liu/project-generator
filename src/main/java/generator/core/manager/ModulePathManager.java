package generator.core.manager;

import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.Data;

@Data
public class ModulePathManager {
	
	private Path moduleBasePath;
	
	private Path mainClassPath;
	
	private Path modulePath;
	
	private Path moduleBeanPath;
	
	private Path moduleDtoPath;
	
	private Path moduleMapperPath;
	
	private Path moduleQueryPath;
	
	private Path moduleServicePath;
	
	private Path moduleVoPath;
	
	private Path moduleWebPath;
	
	public ModulePathManager(Path basePath, String projectName, String basePackage, String moduleName) {
		this.moduleBasePath = basePath.resolve(projectName + "-" + moduleName);
		Path path = Paths.get(basePackage, moduleName);
		this.modulePath = this.moduleBasePath.resolve(PathManager.RELATEED_JAVA_PATH).resolve(path);
		this.mainClassPath = this.moduleBasePath.resolve(PathManager.RELATEED_JAVA_PATH).resolve(basePackage);
		this.moduleBeanPath = this.modulePath.resolve("bean");
		this.moduleDtoPath = this.modulePath.resolve("dto");
		this.moduleMapperPath = this.modulePath.resolve("mapper");
		this.moduleQueryPath = this.modulePath.resolve("query");
		this.moduleServicePath = this.modulePath.resolve("service");
		this.moduleVoPath = this.modulePath.resolve("vo");
		this.moduleWebPath = this.modulePath.resolve("web/api");
	}

}
