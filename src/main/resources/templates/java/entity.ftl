package ${project.basePackage}.entities.${resource.mode?lower_case};

import com.github.developframework.resource.spring.${resource.mode.alias?lower_case}.${resource.mode};
import lombok.Getter;
import lombok.Setter;

<#switch resource.mode>
<#case "PO">
import javax.persistence.*;
<#break>
<#case "DOC">
import org.springframework.data.mongodb.core.mapping.Document;
<#break>
</#switch>

<#include "class-comment.ftl">
@Setter
@Getter
<#switch resource.mode>
<#case "PO">
@Entity
@Table(name = "${resource.table}")
<#break>
<#case "DOC">
@Document("${resource.table}")
<#break>
</#switch>
public class ${resource.entity} implements ${resource.mode}<${resource.idType}> {

<#if resource.mode == 'PO'>
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
</#if>
    private ${resource.idType} id;
<#list resource.fieldsValue as field>

<#if resource.mode == 'PO'>
    /* ${field.comment} */
    <#if !field.nullable>@Column(nullable = false)</#if>
    private ${field.type} ${field.name};
<#else>
    /* ${field.comment} */
    private ${field.type} ${field.name};
</#if>
</#list>
}