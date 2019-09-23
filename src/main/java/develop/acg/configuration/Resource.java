package develop.acg.configuration;

import develop.acg.ResourceMode;
import develop.toolkit.base.utils.JavaBeanUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qiushui on 2019-08-22.
 */
@Getter
@Setter
public class Resource {

    private String name;

    private String table;

    private ResourceMode mode;

    private String description;

    private String idType;

    private boolean unique;

    private List<String> fields;
    
    private String startUpperName;

    public void setName(String name) {
        this.name = name;
        this.startUpperName = JavaBeanUtils.startUpperCaseText(name);
    }

    public List<Field> getFieldsValue() {
        return fields
                .stream()
                .map(fieldStr -> {
                    String[] parts = fieldStr.split("\\s+");
                    Field field = new Field();
                    field.setType(parts[0]);
                    field.setName(parts[1]);
                    field.setNullable(Boolean.parseBoolean(parts[2]));
                    field.setComment(parts[3]);
                    return field;
                })
                .collect(Collectors.toList());
    }

    public String getMiddleLineName() {
        return JavaBeanUtils.camelcaseToMiddleLine(name);
    }

    public String getUnderLineName() {
        return JavaBeanUtils.camelcaseToUnderline(name);
    }

    public String getLowerCaseName() {
        return JavaBeanUtils.startLowerCaseText(name);
    }

    public String getRepository() {
        return startUpperName + mode.getAlias() + "Repository";
    }

    public String getManager() {
        return startUpperName + "Manager";
    }

    public String getController() {
        return startUpperName + "Controller";
    }

    public String getEntity() {
        return startUpperName + mode.name();
    }

    public String getDto() {
        return startUpperName + "DTO";
    }

    public String getMapper() {
        return startUpperName + "Mapper";
    }
}
