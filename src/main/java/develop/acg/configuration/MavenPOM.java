package develop.acg.configuration;

import develop.toolkit.base.struct.ThreeValues;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qiushui on 2019-08-22.
 */
@Getter
@Setter
public class MavenPOM {

    private String groupId;

    private String artifactId;

    private String version;

    private String author;

    private String description;

    private List<String> dependencies;

    public List<ThreeValues<String, String, String>> getDependenciesValue() {
        return dependencies
                .stream()
                .map(dependency -> {
                    String[] parts = dependency.split(":");
                    return ThreeValues.of(parts[0], parts[1], parts.length > 2 ? parts[2] : null);
                })
                .collect(Collectors.toList());
    }
}
