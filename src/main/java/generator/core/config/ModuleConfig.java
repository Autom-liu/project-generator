package generator.core.config;

import lombok.Data;

@Data
public class ModuleConfig {
	
	private String moduleName;
	
	private String artifactId;
	
	private boolean enable;

	private DatabaseConfig databaseConfig;

	public ModuleConfig() {
		super();
	}
	
}
