package develop.acg.command;

import develop.acg.configuration.GeneratorConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * @author qiushui on 2019-08-26.
 */
public interface Command {

    String name();

    int parametersLength();

    void execute(String currentPath, String[] parameters);

    default GeneratorConfiguration readConfiguration(String currentPath) throws IOException {
        InputStream inputStream = new FileInputStream(Path.of(currentPath, "acg.yml").toFile());
        GeneratorConfiguration configuration = new Yaml().loadAs(inputStream, GeneratorConfiguration.class);
        inputStream.close();
        return configuration;
    }
}
