package generator.core.config;

import lombok.Data;

@Data
public class TableConfig {

	private String tableName;
	
	private String domainObjectName;
	
	private boolean generatedKey;
	
	private String keyColumn;
	
	private String sqlStatement;
	
	private boolean identity;
	
}
