package ${basePackage}.${moduleName}.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

import lombok.Data;

@Data
@Table(name = "${tableName}")
public class ${domainObjectName} {

<#list columns as field>
    /** ${field.comment} **/
<#if field.isPrimary>
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
</#if>
    @Column(name = "${field.column}")
    private ${field.javaType} ${field.javaField};

</#list>

}
