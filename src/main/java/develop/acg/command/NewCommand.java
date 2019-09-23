package develop.acg.command;

import develop.acg.CodeDefinitionCollector;
import develop.acg.CodeWriter;
import develop.acg.ResourceMode;
import develop.acg.configuration.GeneratorConfiguration;
import develop.acg.configuration.Resource;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * @author qiushui on 2019-08-28.
 */
public class NewCommand implements Command {

    @Override
    public String name() {
        return "new";
    }

    @Override
    public int parametersLength() {
        return 1;
    }

    @Override
    public void execute(String currentPath, String[] parameters) {
        // acg new
        Resource resource = new Resource();
        Scanner scanner = new Scanner(System.in);
        System.out.print("entity: ");
        String entity = scanner.nextLine();
        if (entity.endsWith(ResourceMode.PO.name())) {
            resource.setName(entity.substring(0, entity.length() - 2));
            resource.setMode(ResourceMode.PO);
        } else if(entity.endsWith(ResourceMode.DOC.name())) {
            resource.setName(entity.substring(0, entity.length() - 3));
            resource.setMode(ResourceMode.DOC);
        } else {
            throw new RuntimeException("实体名错误");
        }
        System.out.print("table: ");
        resource.setTable(scanner.nextLine());
        System.out.print("description: ");
        resource.setDescription(scanner.nextLine());
        System.out.print("idType: ");
        resource.setIdType(scanner.nextLine());
        System.out.print("unique(true, false): ");
        resource.setUnique(scanner.nextBoolean());
        resource.setFields(List.of());
        try {
            GeneratorConfiguration configuration = readConfiguration(currentPath);
            CodeDefinitionCollector collector = new CodeDefinitionCollector(configuration.getProject());
            collector.collectResource(resource);
            new CodeWriter(currentPath).write(currentPath, collector.getCodeDefinitions());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
