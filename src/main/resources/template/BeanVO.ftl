package ${basePackage}.${moduleName}.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ${className} implements Serializable {
    private static final long serialVersionUID = 1L;

    <#list javaFields as f>
    private ${f.type} ${f.variableName};
    
    </#list>
}
