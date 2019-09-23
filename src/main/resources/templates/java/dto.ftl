package ${project.basePackage}.dto;

import com.github.developframework.resource.DTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

<#include "class-comment.ftl">
@Getter
@Setter
public class ${resource.dto} implements DTO {
<#list resource.fieldsValue as field>

    /* ${field.comment} */
    <#if !field.nullable>
    <#if field.type == 'String'>
    @NotBlank(message = "不能为空")
    <#else>
    @NotNull(message = "不能为空")
    </#if>
    </#if>
    private ${field.type} ${field.name};
</#list>
}