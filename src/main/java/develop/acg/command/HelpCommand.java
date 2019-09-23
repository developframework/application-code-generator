package develop.acg.command;

/**
 * @author qiushui on 2019-08-26.
 */
public class HelpCommand implements Command {

    @Override
    public String name() {
        return "help";
    }

    @Override
    public int parametersLength() {
        return 1;
    }

    @Override
    public void execute(String currentPath, String[] parameters) {
        StringBuilder sb = new StringBuilder();
        sb
                .append("init\t\t初始化配置文件\n")
                .append("build\t\t自动创建项目\n")
                .append("new\t\t创建实体类\n")
                .append("code <entity name> <controller,manager> <lineNumber> <add,modify,remove,search>\t\t自动生成模板代码\n")
                .append("help\t\t帮助文档\n");
        System.out.println(sb);
    }
}
