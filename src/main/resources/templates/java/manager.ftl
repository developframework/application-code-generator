package ${project.basePackage}.manager;

<#if resource.unique>
import com.github.developframework.resource.operate.AddUniqueResourceOperate;
<#else>
import com.github.developframework.resource.operate.AddResourceOperate;
</#if>
import com.github.developframework.resource.DefaultRegister;
import com.github.developframework.resource.spring.${resource.mode.alias?lower_case}.${resource.mode.alias}ResourceManager;
<#if resource.mode == 'DOC'>
import org.springframework.data.mongodb.core.MongoOperations;
</#if>
import org.springframework.stereotype.Service;
import ${project.basePackage}.dto.${resource.dto};
import ${project.basePackage}.entities.${resource.mode?lower_case}.${resource.entity};
import ${project.basePackage}.mapper.${resource.mapper};
import ${project.basePackage}.repositories.${resource.mode.alias?lower_case}.${resource.repository};

<#include "class-comment.ftl">
@Service
@DefaultRegister(dtoClass = ${resource.dto}.class, mapperClass = ${resource.mapper}.class)
public class ${resource.manager} extends ${resource.mode.alias}ResourceManager<${resource.entity}, ${resource.idType}, ${resource.repository}> {

    <#if resource.mode == 'PO'>
    public ${resource.manager}(${resource.repository} repository) {
        super(repository, ${resource.entity}.class, "${resource.lowerCaseName}");
    }
    <#else>
    public ${resource.manager}(${resource.repository} repository, MongoOperations mongoOperations) {
        super(repository, mongoOperations, ${resource.entity}.class, "${resource.lowerCaseName}");
    }
    </#if>
<#assign methods = ["add"]>
<#include "../method/operate.ftl">
}