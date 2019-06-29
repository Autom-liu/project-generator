package generator.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

public class GlobalConfig {
	
	private static String basePackage;
	
	private static String projectName;
	
	static {
		Properties pros = new Properties();
		try (InputStream in = Files.newInputStream(BasePath.RESOURCE_PATH.resolve("global/project.properties"))) {
			pros.load(in);
			basePackage = pros.getProperty("project.basePackage");
			projectName = pros.getProperty("project.projectName");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getBasePackage() {
		return basePackage;
	}
	
	public static String getProjectName() {
		return projectName;
	}
	
}
