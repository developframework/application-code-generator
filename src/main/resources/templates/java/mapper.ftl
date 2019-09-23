package ${project.basePackage}.mapper;

import com.github.developframework.resource.BasicMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import ${project.basePackage}.dto.${resource.dto};
import ${project.basePackage}.entities.${resource.mode?lower_case}.${resource.entity};

<#include "class-comment.ftl">
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ${resource.mapper} extends BasicMapper<${resource.entity}, ${resource.dto}> {

}