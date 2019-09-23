package develop.acg;

import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;


/**
 * 代码定义
 *
 * @author qiushui on 2019-08-22.
 */
@Getter
@Setter
public class CodeDefinition {

    private CodeType codeType;

    private String template;

    private Path path;

    private String fileName;

    private Object data;

    public CodeDefinition(CodeType codeType) {
        this.codeType = codeType;
    }
}
