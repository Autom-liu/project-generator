package generator.core.config;

import java.util.List;

import lombok.Data;

@Data
public class DatabaseConfig {
	
	private String driverClass;
	
	private String connectIp;

	private String connectUrl;
	
	private String userId;
	
	private String password;
	
	private boolean enableSubPackage;
	
	private boolean trimString;
	
	private boolean forceBigDecimals;
	
	private List<TableConfig> tables;
	
}
