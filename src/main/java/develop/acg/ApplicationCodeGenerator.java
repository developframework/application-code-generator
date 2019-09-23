package develop.acg;

import develop.acg.command.CommandRegistry;

/**
 * @author qiushui on 2019-08-22.
 */
public class ApplicationCodeGenerator {

    public static void main(String[] args) {
        String currentPath = System.getProperty("user.dir");
        new CommandRegistry().executeCommand(currentPath, args);
    }
}
