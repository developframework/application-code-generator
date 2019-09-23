package develop.acg.command;

import develop.acg.CodeDefinitionCollector;
import develop.acg.CodeWriter;
import develop.acg.configuration.GeneratorConfiguration;

import java.io.IOException;

/**
 * @author qiushui on 2019-08-26.
 */
public class BuildCommand implements Command {

    @Override
    public String name() {
        return "build";
    }

    @Override
    public int parametersLength() {
        return 1;
    }

    @Override
    public void execute(String currentPath, String[] parameters) {
        // acg build
        try {
            GeneratorConfiguration configuration = readConfiguration(currentPath);
            CodeDefinitionCollector collector = new CodeDefinitionCollector(configuration.getProject());
            collector.collectNewProject(configuration.getProject());
            new CodeWriter(currentPath).write(currentPath, collector.getCodeDefinitions());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
