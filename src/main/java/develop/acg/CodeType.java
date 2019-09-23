package develop.acg;

import lombok.Getter;

/**
 * @author qiushui on 2019-08-22.
 */
@Getter
public enum CodeType {

    POM(".xml", ""),

    JAVA_CLASS(".java", CodeWriter.MAVEN_FOLDERS[0]),

    XML(".xml", CodeWriter.MAVEN_FOLDERS[1]),

    YAML(".yml", CodeWriter.MAVEN_FOLDERS[1]),

    GITIGNORE(".gitignore", ""),

    DOCKERFILE("", "");

    /* 扩展名 */
    private String extensionName;

    /* 文件夹 */
    private String basePath;

    CodeType(String extensionName, String basePath) {
        this.extensionName = extensionName;
        this.basePath = basePath;
    }

}
