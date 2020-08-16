package ${basePackage}.${moduleName}.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class ${className} implements Serializable {
    private static final long serialVersionUID = 1L;

    <#list columns as f>
    /** ${f.comment} **/
    <#if !f.isNullable&&!f.isPrimary>
    @NotNull(message = "${f.comment}字段不能为空")
    <#if f.characterMaximnmLength??>
    @Length(min = 1, max = ${f.characterMaximnmLength}, message = "${f.comment}字段长度不符合要求")
    </#if>
    </#if>
    <#if f.characterMaximnmLength??>
    @Length(max = ${f.characterMaximnmLength}, message = "${f.comment}字段长度不符合要求")
    </#if>
    private ${f.javaType} ${f.javaField};
    
    </#list>
}
