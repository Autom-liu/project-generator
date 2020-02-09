package generator.core.config;

import java.util.List;

import lombok.Data;

@Data
public class ModuleConfig {
	
	private String moduleName;
	
	private String artifactId;
	
	private boolean enable;
	
	private List<TableConfig> tables;

	public ModuleConfig() {
		super();
	}
	
}
