<kite-configuration xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                    xmlns="https://github.com/developframework/kite/schema"
                    xsi:schemaLocation="
	https://github.com/developframework/kite/schema kite-configuration.xsd">

    <template-package namespace="kite-${resource.middleLineName}">

        <template id="${resource.middleLineName}">
        <#list resource.fieldsValue as field>
            <property data="${field.name}" />
        </#list>
        </template>

    </template-package>

</kite-configuration>