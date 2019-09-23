package develop.acg.command;

import develop.acg.CodeWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author qiushui on 2019-08-27.
 */
public class InitCommand implements Command {

    @Override
    public String name() {
        return "init";
    }

    @Override
    public int parametersLength() {
        return 1;
    }

    @Override
    public void execute(String currentPath, String[] parameters) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> data = new HashMap<>();
        String[] inputs = new String[] {"groupId", "artifactId"};
        for (String input : inputs) {
            System.out.printf("%s: ", input);
            data.put(input, scanner.nextLine());
        }
        try {
            new CodeWriter(currentPath).write(Path.of(currentPath, data.get("artifactId")), "acg.yml", "config.ftl", data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
