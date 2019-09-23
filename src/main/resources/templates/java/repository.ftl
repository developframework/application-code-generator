package ${project.basePackage}.repositories.${resource.mode.alias?lower_case};

<#if resource.mode == 'PO'>
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
<#else>
import org.springframework.data.mongodb.repository.MongoRepository;
</#if>
import ${project.basePackage}.entities.${resource.mode?lower_case}.${resource.entity};

<#include "class-comment.ftl">
public interface ${resource.repository} extends ${resource.mode.alias}Repository<${resource.entity}, ${resource.idType}><#if resource.mode == 'PO'>, JpaSpecificationExecutor<${resource.entity}></#if> {

}