package develop.acg.command;

import develop.toolkit.base.utils.CollectionAdvice;

import java.util.List;

/**
 * @author qiushui on 2019-08-26.
 */
public class CommandRegistry {

    private List<Command> commands;

    public CommandRegistry() {
        this.commands = List.of(
                new InitCommand(),
                new HelpCommand(),
                new TemplateCodeCommand(),
                new BuildCommand(),
                new NewCommand()
        );
    }

    public void executeCommand(String currentPath, String[] parameters) {
        Command matchCommand = getMatchCommand(parameters);
        if (parameters.length != matchCommand.parametersLength()) {
            new HelpCommand().execute(currentPath, parameters);
            return;
        }
        matchCommand.execute(currentPath, parameters);
    }

    private Command getMatchCommand(String[] args) {
        if (args.length == 0) {
            return new HelpCommand();
        } else {
            return CollectionAdvice.getFirstMatch(commands, args[0], Command::name).orElseGet(HelpCommand::new);
        }
    }
}
