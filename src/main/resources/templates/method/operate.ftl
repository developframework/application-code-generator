<#list methods as method>
<#if method == 'add'>

    <#if resource.unique>
    @SuppressWarnings("unused")
    private AddUniqueResourceOperate<${resource.entity}, ${resource.dto}, ${resource.idType}> addResourceOperate() {
        return new AddUniqueResourceOperate<>(${resource.dto}.class, ${resource.name}Mapper.class) {

            @Override
            public AddCheckExistsLogic<${resource.entity}, ${resource.dto}, ${resource.idType}> configureCheckExistsLogic() {
                return null;
            }

            @Override
            protected void prepare(${resource.dto} dto, ${resource.entity} entity) {

            }
        };
    }
    <#else>
    @SuppressWarnings("unused")
    private AddResourceOperate<${resource.entity}, ${resource.dto}, ${resource.idType}> addResourceOperate() {
        return new AddResourceOperate<>(${resource.dto}.class, ${resource.name}Mapper.class) {

            @Override
            protected void prepare(${resource.dto} dto, ${resource.entity} entity) {

            }
        };
    }
    </#if>
<#elseif method == 'modify'>

    @SuppressWarnings("unused")
    private ModifyResourceOperate<${resource.entity}, ${resource.dto}, ${resource.idType}> modifyResourceOperate() {
        return new ModifyResourceOperate<>(${resource.dto}.class, ${resource.name}Mapper.class) {

            @Override
            protected void prepare(${resource.dto} dto, ${resource.entity} entity) {

            }
        };
    }
<#elseif method == 'remove'>

    @SuppressWarnings("unused")
    private RemoveResourceOperate<${resource.entity}, ${resource.idType}> removeResourceOperate() {
        return new RemoveResourceOperate<>() {

            @Override
            protected void after(${resource.entity} entity) {

            }
        };
    }
<#elseif method == 'search'>

    @SuppressWarnings("unused")
    private SearchResourceOperate<${resource.entity}, ${resource.idType}> searchResourceOperate() {
        return new SearchResourceOperate<>() {

            @Override
            public void after(${resource.entity} entity) {

            }
        };
    }
</#if>
</#list>