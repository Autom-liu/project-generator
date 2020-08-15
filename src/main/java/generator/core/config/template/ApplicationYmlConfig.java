package generator.core.config.template;

import lombok.Data;

@Data
public class ApplicationYmlConfig {

	private String encryDataSourceIp;
	
	private String encryPassword;
	
	private String userId;
	
	private String encryKey;
	
	private String devLogPath;
	
	private String prodLogPath;
	
	private String port;
	
	private String contextPath;
	
	private String applicationName;
	
	private String beanPackage;
	
}
