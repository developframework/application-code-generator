package ${project.basePackage};

import com.github.developframework.kite.boot.annotation.EnableKite;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ${project.pom.description}
 *
 * @author ${project.pom.author} on ${date}
 */
@EnableKite
@SpringBootApplication
public class ${project.applicationName}Application {

    public static void main(String[] args) {
        SpringApplication.run(${project.applicationName}Application.class, args);
    }
}