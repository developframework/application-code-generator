package develop.acg;

import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author qiushui on 2019-08-22.
 */
public class CodeWriter {

    public static String[] MAVEN_FOLDERS = {
            "src/main/java",
            "src/main/resources",
            "src/test/java",
            "src/test/resources"
    };

    private CustomTemplateReader customTemplateReader;

    public CodeWriter(String currentPath) {
        customTemplateReader = new CustomTemplateReader(currentPath);
    }

    public void write(String rootPath, List<CodeDefinition> codeDefinitions) throws IOException {
        initMavenProject(rootPath);
        for (CodeDefinition codeDefinition : codeDefinitions) {
            String filename = codeDefinition.getFileName() + codeDefinition.getCodeType().getExtensionName();
            Path path = Paths.get(rootPath, codeDefinition.getCodeType().getBasePath(), codeDefinition.getPath().toString());
            Files.createDirectories(path);
            Path filePath = Paths.get(path.toString(), filename);
            if (Files.exists(filePath)) {
                System.out.println("skip " + filePath);
            } else {
                try(OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(filePath), StandardCharsets.UTF_8)) {
                    customTemplateReader.getTemplate(codeDefinition.getTemplate()).process(codeDefinition.getData(), writer);
                    System.out.println("create " + filePath);
                } catch (TemplateException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void write(Path path, String filename, String template, Object data) throws IOException {
        Files.createDirectories(path);
        try(OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(Path.of(path.toString(), filename), StandardOpenOption.CREATE_NEW), StandardCharsets.UTF_8)) {
            customTemplateReader.getTemplate(template).process(data, writer);
            System.out.println("create " + path.toString());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    private void initMavenProject(String rootPath) throws IOException {
        for (String mavenFolder : MAVEN_FOLDERS) {
            Path path = Paths.get(rootPath, mavenFolder);
            Files.createDirectories(path);
        }
    }

    public void insert(Path filePath, List<InsertContent> insertContents) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        StringBuilder sb = new StringBuilder();
        Set<InsertContent> executedInsertContentSet = new HashSet<>();
        for (int i = 1; i <= lines.size(); i++) {
            String line = lines.get(i - 1);
            for (InsertContent insertContent : insertContents) {
                if(insertContent.once() && executedInsertContentSet.contains(insertContent)) {
                    continue;
                }
                if(insertContent.predicate(i, line)) {
                    sb.append(insertContent.content(customTemplateReader)).append('\n');
                    executedInsertContentSet.add(insertContent);
                }
            }
            sb.append(line).append('\n');
        }
        Files.writeString(filePath, sb.toString(), StandardCharsets.UTF_8);
        System.out.println("modify " + filePath);
    }

    public interface InsertContent {

        boolean predicate(int lineNumber, String line);

        String content(CustomTemplateReader customTemplateReader);

        default boolean once() {
            return false;
        }
    }
}
