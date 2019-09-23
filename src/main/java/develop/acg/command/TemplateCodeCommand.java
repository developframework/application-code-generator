package develop.acg.command;

import develop.acg.CodeType;
import develop.acg.CodeWriter;
import develop.acg.CustomTemplateReader;
import develop.acg.configuration.GeneratorConfiguration;
import develop.acg.configuration.Resource;
import develop.toolkit.base.utils.JavaBeanUtils;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author qiushui on 2019-08-26.
 */
public class TemplateCodeCommand implements Command {

    @Override
    public String name() {
        return "code";
    }

    @Override
    public int parametersLength() {
        return 5;
    }

    @Override
    public void execute(String currentPath, String[] parameters) {
        // code user manager 20 add
        try {
            Path filePath = matchFile(currentPath, parameters);
            if (filePath == null) {
                return;
            }
            GeneratorConfiguration configuration = readConfiguration(currentPath);
            Resource resource = configuration.getProject().matchResource(parameters[1]);
            int lineNumber = Integer.parseInt(parameters[3]);
            String[] methods = parameters[4].split(",");
            switch (parameters[2]) {
                case "controller": break;
                case "manager": modifyManager(currentPath, resource, filePath, methods, lineNumber);break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Path matchFile(String currentPath, String[] parameters) throws IOException {
        String filename = parameters[1] + parameters[2] + CodeType.JAVA_CLASS.getExtensionName();
        List<Path> list = Files
                .find(Path.of(currentPath), 10, (path, basicFileAttributes) ->
                        basicFileAttributes.isRegularFile() && path.endsWith(filename)
                )
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            System.out.printf("没有找到%s文件\n", filename);
            return null;
        } else {
            return list.get(0);
        }
    }

    private void modifyManager(String currentPath, Resource resource, Path filePath, String[] methods, int lineNumber) throws IOException{
        new CodeWriter(currentPath).insert(
                filePath,
                List.of(
                        new CodeWriter.InsertContent() {

                            @Override
                            public boolean predicate(int lineNumber, String line) {
                                return line.startsWith("import");
                            }

                            @Override
                            public String content(CustomTemplateReader customTemplateReader) {
                                return Stream
                                        .of(methods)
                                        .map(method -> {
                                            if((method.equals("add") || method.equals("modify"))) {
                                                return String.format(
                                                        "import com.github.developframework.resource.operate.%s%sResourceOperate;",
                                                        JavaBeanUtils.startUpperCaseText(method),
                                                        resource.isUnique() ? "Unique" : ""
                                                );
                                            } else {
                                                return String.format(
                                                        "import com.github.developframework.resource.operate.%sResourceOperate;",
                                                        JavaBeanUtils.startUpperCaseText(method)
                                                );
                                            }
                                        })
                                        .collect(Collectors.joining("\n"));
                            }

                            @Override
                            public boolean once() {
                                return true;
                            }
                        },
                        new CodeWriter.InsertContent() {
                            @Override
                            public boolean predicate(int number, String line) {
                                return number == lineNumber;
                            }

                            @Override
                            public String content(CustomTemplateReader customTemplateReader) {
                                Map<String, Object> data = Map.of(
                                        "resource", resource,
                                        "methods", methods
                                );
                                try (StringWriter writer = new StringWriter()) {
                                    customTemplateReader.getTemplate("method/operate.ftl").process(data, writer);
                                    return writer.toString();
                                } catch (TemplateException | IOException e) {

                                    throw new RuntimeException(e);
                                }
                            }
                        }
                )
        );
    }
}
