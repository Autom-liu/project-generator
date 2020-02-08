package generator.core.config.template;

import lombok.Data;

@Data
public class BasePomTemplateConfig {

	private String groupId;
	
	private String artifactId;
	
	private String moduleName;
	
	private String version;
	
	private String description;
	
}
