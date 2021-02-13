package ${basePackage}.${moduleName}.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ${className} implements Serializable {
    private static final long serialVersionUID = 1L;

    <#list javaFields as f>
    private ${f.type} ${f.variableName};
    
    </#list>
}
