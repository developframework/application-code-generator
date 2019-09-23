package develop.acg;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author qiushui on 2019-08-29.
 */
public class CustomTemplateReader {

    private static final String FOLDER = "templates";

    private String currentPath;

    private Configuration classPathConfiguration;

    private Configuration localConfiguration;

    public CustomTemplateReader(String currentPath) {
        this.currentPath = currentPath;
        classPathConfiguration = new Configuration(Configuration.getVersion());
        classPathConfiguration.setClassForTemplateLoading(CodeWriter.class, "/" + FOLDER);
        classPathConfiguration.setDefaultEncoding(StandardCharsets.UTF_8.name());
        File dir = Path.of(currentPath, FOLDER).toFile();
        if (dir.exists() && dir.isDirectory()) {
            try {
                localConfiguration = new Configuration(Configuration.getVersion());
                localConfiguration.setDirectoryForTemplateLoading(dir);
                localConfiguration.setDefaultEncoding(StandardCharsets.UTF_8.name());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Template getTemplate(String template) throws IOException {
        if (localConfiguration != null && Files.exists(Path.of(currentPath, FOLDER, template))) {
            return localConfiguration.getTemplate(template);
        } else {
            return classPathConfiguration.getTemplate(template);
        }
    }
}
