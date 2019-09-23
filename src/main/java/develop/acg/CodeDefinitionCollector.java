package develop.acg;

import develop.acg.configuration.Project;
import develop.acg.configuration.Resource;
import lombok.Getter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author qiushui on 2019-08-22.
 */
public class CodeDefinitionCollector {

    private Project project;

    @Getter
    private List<CodeDefinition> codeDefinitions = new LinkedList<>();

    private static final Object[][] PARAMETERS = {
            // pom.xml
            {CodeType.POM, TemplateNameConstants.POM, "pom"},
            // application.yml
            {CodeType.YAML, TemplateNameConstants.APPLICATION, "application"},
            // application-dev.yml
            {CodeType.YAML, TemplateNameConstants.APPLICATION_DEV, "application-dev"},
            // application-prd.yml
            {CodeType.YAML, TemplateNameConstants.APPLICATION_PRD, "application-prd"},
            // logback-spring.yml
            {CodeType.XML, TemplateNameConstants.LOGBACK, "logback-spring"},
            // logback-dev.yml
            {CodeType.XML, TemplateNameConstants.LOGBACK, "logback-dev"},
            // logback-prd.yml
            {CodeType.XML, TemplateNameConstants.LOGBACK, "logback-prd"},
            // .gitignore
            {CodeType.GITIGNORE, TemplateNameConstants.GITIGNORE, ""},
            // Dockerfile
            {CodeType.DOCKERFILE, TemplateNameConstants.DOCKERFILE, "Dockerfile"},
    };

    public CodeDefinitionCollector(Project project) {
        this.project = project;
    }

    public void collectNewProject(Project project) {
        for (Object[] parameter : PARAMETERS) {
            addCodeDefinition(parameter);
        }
        for (Resource resource : project.getResources()) {
            collectResource(resource);
        }
    }

    public void collectResource(Resource resource) {
        // Entity类
        addResourceEntityCodeDefinition(resource);
        // Repository类
        addResourceRepositoryCodeDefinition(resource);
        // Mapper类
        addResourceMapperCodeDefinition(resource);
        // DTO类
        addResourceDTOCodeDefinition(resource);
        // Manager类
        addResourceManagerCodeDefinition(resource);
        // Controller类
        addResourceControllerCodeDefinition(resource);
        // Application类
        addResourceApplicationCodeDefinition(resource);
        // kite文件
        addResourceKiteCodeDefinition(resource);
    }

    private void addCodeDefinition(Object[] parameter) {
        CodeDefinition codeDefinition = new CodeDefinition((CodeType) parameter[0]);
        codeDefinition.setTemplate((String) parameter[1]);
        codeDefinition.setPath(Paths.get(""));
        codeDefinition.setFileName((String) parameter[2]);
        codeDefinition.setData(project);
        codeDefinitions.add(codeDefinition);
    }

    private void resourceCodeDefinition(String component, Resource resource, CodeType codeType, String template, Path path, String fileName) {
        CodeDefinition codeDefinition = new CodeDefinition(codeType);
        codeDefinition.setTemplate(template);
        codeDefinition.setPath(path);
        codeDefinition.setFileName(fileName);
        codeDefinition.setData(
                Map.of(
                        "component", component,
                        "project", project,
                        "resource", resource,
                        "date", LocalDate.now()
                )
        );
        codeDefinitions.add(codeDefinition);
    }

    private void addResourceEntityCodeDefinition(Resource resource) {
        resourceCodeDefinition(
                "entity",
                resource,
                CodeType.JAVA_CLASS,
                TemplateNameConstants.ENTITY,
                Paths.get(project.getBasePackagePath(), "entities", resource.getMode().name().toLowerCase()),
                resource.getEntity()
        );
    }

    private void addResourceRepositoryCodeDefinition(Resource resource) {
        resourceCodeDefinition(
                "repository",
                resource,
                CodeType.JAVA_CLASS,
                TemplateNameConstants.REPOSITORY,
                Paths.get(project.getBasePackagePath(), "repositories", resource.getMode().getAlias().toLowerCase()),
                resource.getRepository()
        );
    }

    private void addResourceMapperCodeDefinition(Resource resource) {
        resourceCodeDefinition(
                "mapper",
                resource,
                CodeType.JAVA_CLASS,
                TemplateNameConstants.MAPPER,
                Paths.get(project.getBasePackagePath(), "mapper"),
                resource.getMapper()
        );
    }

    private void addResourceDTOCodeDefinition(Resource resource) {
        resourceCodeDefinition(
                "entity",
                resource,
                CodeType.JAVA_CLASS,
                TemplateNameConstants.DTO,
                Paths.get(project.getBasePackagePath(), "dto"),
                resource.getDto()
        );
    }

    private void addResourceManagerCodeDefinition(Resource resource) {
        resourceCodeDefinition(
                "manager",
                resource,
                CodeType.JAVA_CLASS,
                TemplateNameConstants.MANAGER,
                Paths.get(project.getBasePackagePath(), "manager"),
                resource.getManager()
        );
    }

    private void addResourceControllerCodeDefinition(Resource resource) {
        resourceCodeDefinition(
                "controller",
                resource,
                CodeType.JAVA_CLASS,
                TemplateNameConstants.CONTROLLER,
                Paths.get(project.getBasePackagePath(), "controller"),
                resource.getController()
        );
    }

    private void addResourceApplicationCodeDefinition(Resource resource) {
        resourceCodeDefinition(
                "application",
                resource,
                CodeType.JAVA_CLASS,
                TemplateNameConstants.CLASS_APPLICATION,
                Paths.get(project.getBasePackagePath()),
                project.getApplicationName() + "Application"
        );
    }

    private void addResourceKiteCodeDefinition(Resource resource) {
        resourceCodeDefinition(
                "kite",
                resource,
                CodeType.XML,
                TemplateNameConstants.KITE,
                Paths.get("kite"),
                "kite-" + resource.getMiddleLineName()
        );
    }
}
