package generator.config.pom;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import generator.config.BasePath;

public class PomConfig {
	
	private static final String BASE_POM_PATH = "pom/pom.properties";
	
	private static String basePackage;
	
	private static String groupId;
	
	private static String artifactId;
	
	private static String projectName;
	
	private static String description;
	
	private static Path projectPath;

	static {
		Properties pros = new Properties();
		try (InputStream in = Files.newInputStream(BasePath.RESOURCE_PATH.resolve(BASE_POM_PATH))) {
			pros.load(in);
			basePackage = pros.getProperty("pom.parent.basePackage");
			groupId = pros.getProperty("pom.parent.groupId");
			artifactId = pros.getProperty("pom.parent.artifactId");
			projectName = pros.getProperty("pom.parent.projectName");
			description = pros.getProperty("pom.parent.description");
			Common.init(pros);
			CommonService.init(pros);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getBasePackage() {
		return basePackage;
	}

	public static String getGroupId() {
		return groupId;
	}

	public static String getArtifactId() {
		return artifactId;
	}

	public static String getProjectName() {
		return projectName;
	}

	public static String getDescription() {
		return description;
	}
	
	public static Path getProjectPath() {
		return projectPath;
	}

	public static void setProjectPath(Path projectPath) {
		PomConfig.projectPath = projectPath;
	}
	
	public static class Common {
		protected static String artifactId;
		
		protected static String moduleName;
		
		private static Path commonPath;
		
		public static void init(Properties pros) {
			PomConfig.Common.artifactId = pros.getProperty("pom.common.artifactId");
			PomConfig.Common.moduleName = pros.getProperty("pom.common.moduleName");
		}

		public static String getArtifactId() {
			return artifactId;
		}

		public static String getModuleName() {
			return moduleName;
		}

		public static Path getCommonPath() {
			return commonPath;
		}

		public static void setCommonPath(Path commonPath) {
			Common.commonPath = commonPath;
		}
	}
	
	public static class CommonService {
		protected static String artifactId;
		
		protected static String moduleName;
		
		private static Path commonPath;
		
		public static void init(Properties pros) {
			CommonService.artifactId = pros.getProperty("pom.commonService.artifactId");
			CommonService.moduleName = pros.getProperty("pom.commonService.moduleName");
		}

		public static String getArtifactId() {
			return artifactId;
		}

		public static String getModuleName() {
			return moduleName;
		}

		public static Path getCommonPath() {
			return commonPath;
		}

		public static void setCommonPath(Path commonPath) {
			CommonService.commonPath = commonPath;
		}
		
	}
}
