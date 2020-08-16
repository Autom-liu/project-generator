package generator.core.config;

import lombok.Data;

import java.util.List;

@Data
public class TableConfig {

	private String tableName;
	
	private String domainObjectName;
	
	private boolean generatedKey;
	
	private String keyColumn;
	
	private String sqlStatement;
	
	private boolean identity;

	private List<TableColumn> columns;
}
