package ${project.basePackage}.controller;

import com.github.developframework.kite.core.data.DataModel;
import com.github.developframework.kite.core.data.HashDataModel;
import com.github.developframework.kite.spring.mvc.annotation.KiteNamespace;
import com.github.developframework.kite.spring.mvc.annotation.TemplateId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ${project.basePackage}.dto.${resource.dto};
import ${project.basePackage}.manager.${resource.manager};

<#include "class-comment.ftl">
@Controller
@RequestMapping("${resource.lowerCaseName}s")
@KiteNamespace("kite-${resource.middleLineName}")
public class ${resource.controller} {

    @Autowired
    private ${resource.manager} ${resource.lowerCaseName}Manager;

    @PostMapping
    public void add(@RequestBody ${resource.dto} dto) {
        ${resource.lowerCaseName}Manager.add(dto);
    }

    @PutMapping("{id}")
    public void modify(@PathVariable ${resource.idType} id, @RequestBody ${resource.dto} dto) {
        ${resource.lowerCaseName}Manager.modifyById(id, dto);
    }

    @DeleteMapping("id")
    public void remove(@PathVariable ${resource.idType} id) {
        ${resource.lowerCaseName}Manager.removeById(id);
    }

    @GetMapping("{id}")
    public DataModel findDetail(@PathVariable ${resource.idType} id) {
        return HashDataModel.singleton(
                ${resource.lowerCaseName}Manager.getResourceDefinition().getResourceName(),
                ${resource.lowerCaseName}Manager.findOneByIdRequired(id)
        );
    }
}