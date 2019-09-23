package develop.acg.configuration;

import develop.toolkit.base.utils.CollectionAdvice;
import develop.toolkit.base.utils.JavaBeanUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

/**
 * @author qiushui on 2019-08-22.
 */
@Getter
@Setter
public class Project {

    private String basePackage;

    private String port;

    private Database database;

    private MavenPOM pom;

    private Component components;

    private List<Resource> resources;

    public String getBasePackagePath() {
        return basePackage.replaceAll("\\.", "/");
    }

    public Resource matchResource(String name) {
        Optional<Resource> firstMatch = CollectionAdvice.getFirstMatch(resources, name.toLowerCase(), resource -> resource.getName().toLowerCase());
        if (firstMatch.isPresent()) {
            return firstMatch.get();
        } else {
            System.out.printf("没有%s资源\n", name);
            System.exit(1);
            return null;
        }
    }

    public String getApplicationName() {
        return JavaBeanUtils.startUpperCaseText(pom.getArtifactId());
    }
}
