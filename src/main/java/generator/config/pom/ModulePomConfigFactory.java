package generator.config.pom;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import generator.config.BasePath;

public class ModulePomConfigFactory {
	
	private String moduleName;
	
	private static Map<String, ModulePomConfig> map = new HashMap<>();
	
	public ModulePomConfigFactory(String moduleName) {
		super();
		this.moduleName = moduleName;
		map.put(moduleName, new ModulePomConfig(moduleName));
	}

	public String getModuleName() {
		return moduleName;
	}
	
	public ModulePomConfig getConfig() {
		return map.get(moduleName);
	}
	
	public static ModulePomConfig getConfig(String moduleName) {
		return map.get(moduleName);
	}
	
	public class ModulePomConfig {
		private static final String BASE_POM_PATH = "pom/pom.properties";
		
		private String moduleName;
		
		private String artifactId;
		
		private String mainClass;
		
		private Path projectPath;

		public ModulePomConfig(String moduleName) {
			super();
			this.moduleName = moduleName;
			init();
		}
		
		public void init() {
			Properties pros = new Properties();
			try (InputStream in = Files.newInputStream(BasePath.RESOURCE_PATH.resolve(BASE_POM_PATH))) {
				pros.load(in);
				artifactId = pros.getProperty("pom."+ moduleName +".artifactId");
				mainClass = pros.getProperty("pom."+ moduleName +".mainClass");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public String getModuleName() {
			return moduleName;
		}

		public String getArtifactId() {
			return artifactId;
		}

		public String getMainClass() {
			return mainClass;
		}

		public Path getProjectPath() {
			return projectPath;
		}

		public void setProjectPath(Path projectPath) {
			this.projectPath = projectPath;
		}
		
	}
	
}
