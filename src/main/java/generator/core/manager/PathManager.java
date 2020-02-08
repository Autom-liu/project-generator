package generator.core.manager;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class PathManager {
	
	private static PathManager instance = new PathManager();
	
	public static final Path RELATEED_JAVA_PATH = Paths.get("src", "main", "java");
	
	public static final Path RELATEED_RESOURCE_PATH = Paths.get("src", "main", "resources");
	
	public static final Path RELATEDD_TEST_PATH = Paths.get("src", "test", "java");
	
	public static final Path RELATEED_TEST_RESOURCE_PATH = Paths.get("src", "test", "resources");
	
	public static final Path PARENTPOM_TEMPLATE_PATH = RELATEED_RESOURCE_PATH.resolve("template/parentpom.ftl");
	
	public static final Path COMMONPOM_TEMPLATE_PATH = RELATEED_RESOURCE_PATH.resolve("template/commonpom.ftl");
	
	public static final Path BASESTATUSENUM_TEMPLATE_PATH = RELATEED_RESOURCE_PATH.resolve("template/BaseStatusEnum.ftl");
	
	public static final Path IERRORENUM_TEMPLATE_PATH = RELATEED_RESOURCE_PATH.resolve("template/IErrorEnum.ftl");
	
	private Path basePath;
	
	private Path baseCommonPath;
	
	private Path commonPath;
	
	private Path commonEnumsPath;
	
	private Path commonExceptionPath;
	
	private Path commonUtilPath;
	
	private Path baseCommonSvcPath;
	
	private Path commonSvcPath;
	
	private Path commonSvcBasePath;
	
	private Path commonSvcBeanPath;
	
	private Path commonSvcConfigPath;
	
	private Path commonSvcEnumPath;
	
	private Path commonSvcFactoryPath;
	
	private Path commonSvcProxyPath;
	
	private Path commonSvcQueryPath;
	
	private Path commonSvcServicePath;
	
	private Path commonSvcVoPath;
	
	private Path commonSvcWebPath;
	
	private Path commonSvcResourcePath;
	
	private Map<String, ModulePathManager> modulePathManagerMap;
	
	private PathManager() {
		this.modulePathManagerMap = new HashMap<>();
	}
	
	public static PathManager getInstance() {
		return instance;
	}
	
	public void initializePath(String basePath, String projectName, String basePackage) {
		initializePath(basePath, projectName, basePackage, Collections.emptyList());
	}
	
	public void initializePath(String basePath, String projectName, String basePackage, List<String> moduleNameList) {
		String basePackagePath = basePackage.replaceAll("\\.", "/");
		doInitBasePath(basePath, projectName, basePackagePath);
		doInitModulesPath(projectName, basePackagePath, moduleNameList);
	}
	
	private void doInitModulesPath(String projectName, String basePackagePath, List<String> moduleNameList) {
		for(String moduleName : moduleNameList) {
			ModulePathManager modulePath = new ModulePathManager(this.basePath, projectName, basePackagePath, moduleName);
			this.modulePathManagerMap.put(moduleName, modulePath);
		}
	}

	private void doInitBasePath(String basePath, String projectName, String basePackagePath) {
		this.basePath = Paths.get(basePath, projectName);
		Path baseCommonPath = Paths.get(basePackagePath, "common");
		this.baseCommonPath = this.basePath.resolve(projectName + "-common");
		this.commonPath = this.baseCommonPath.resolve(RELATEED_JAVA_PATH).resolve(baseCommonPath);
		this.commonEnumsPath = this.commonPath.resolve("enums");
		this.commonExceptionPath = this.commonPath.resolve("exception");
		this.commonUtilPath = this.commonPath.resolve("util");
		this.baseCommonSvcPath = this.basePath.resolve(projectName + "-common-service");
		this.commonSvcPath = this.baseCommonSvcPath.resolve(RELATEED_JAVA_PATH).resolve(baseCommonPath);
		this.commonSvcBasePath = this.commonSvcPath.resolve("base");
		this.commonSvcBeanPath = this.commonSvcPath.resolve("bean");
		this.commonSvcConfigPath = this.commonSvcPath.resolve("config");
		this.commonSvcEnumPath = this.commonSvcPath.resolve("enums");
		this.commonSvcFactoryPath = this.commonSvcPath.resolve("factory");
		this.commonSvcProxyPath = this.commonSvcPath.resolve("proxy");
		this.commonSvcQueryPath = this.commonSvcPath.resolve("query");
		this.commonSvcServicePath = this.commonSvcPath.resolve("service");
		this.commonSvcVoPath = this.commonSvcPath.resolve("vo");
		this.commonSvcWebPath = this.commonSvcPath.resolve("web");
	}
	
}
