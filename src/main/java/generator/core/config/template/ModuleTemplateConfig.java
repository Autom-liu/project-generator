package generator.core.config.template;

import java.util.List;

import generator.core.config.TableColumn;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class ModuleTemplateConfig extends BaseTemplateConfig {
	
	private String className;

	private String beanClassName;
	
	private List<JavaFieldDesc> javaFields;
	
	private String dtoClassName;
	
	private String voClassName;
	
	private String queryClassName;
	
	private String serviceClassName;
	
	private String mapperClassName;
	
	private String mapperVariable;

	private List<TableColumn> columns;
	
	@Data
	public static class JavaFieldDesc {
		
		private String type;
		
		private String variableName;
		
	}
	
}
