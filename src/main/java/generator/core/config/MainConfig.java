package generator.core.config;

import java.util.List;

import lombok.Data;

/**
 * 项目主配置
 * @author Autom
 * @date 2020年2月8日
 * @version 1.0
 *
 */
@Data
public class MainConfig {
	
	private GenerateMode generateMode;
	
	private String projectPath;
	
	private String groupId;
	
	private String artifactId;
	
	private String version;
	
	private String basePackage;
	
	private String description;
	
	private List<ModuleConfig> modules;

	public MainConfig() {
		super();
		this.version = "0.0.1-SNAPSHOT";
		this.description = "";
	}
	
	
	
}
