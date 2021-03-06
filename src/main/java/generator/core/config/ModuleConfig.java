package generator.core.config;

import java.util.List;

import lombok.Data;

@Data
public class ModuleConfig {
	
	private String moduleName;
	
	private String artifactId;
	
	private String mainClass;
	
	private boolean enable;
	
	private String devLogPath;
	
	private String prodLogPath;
	
	private String port;
	
	private String contextPath;
	
	private String applicationName;
	
	private List<TableConfig> tables;

	public ModuleConfig() {
		super();
	}
	
}
