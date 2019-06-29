package generator.config;

import java.nio.file.Path;
import java.nio.file.Paths;

public interface BasePath {
	/** src/main/java **/
	Path JAVA_PATH = Paths.get("src", "main", "java");
	
	/** src/main/resources **/
	Path RESOURCE_PATH = Paths.get("src", "main", "resources");
	
	/** src/test/java **/
	Path TEST_PATH = Paths.get("src", "test", "java");
	
	/** src/test/resources **/
	Path TEST_RESOURCE = Paths.get("src", "test", "resources");
}
