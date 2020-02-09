package ${basePackage}.${moduleName}.query;

import ${basePackage}.common.query.PageQuery;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper=true)
public class ${className} extends PageQuery {

	private static final long serialVersionUID = 1L;

}
