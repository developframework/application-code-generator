package develop.acg.configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qiushui on 2019-08-22.
 */
@Getter
@Setter
public class MongoDB {

    private String host;

    private String port;

    private String database;

    private String username;

    private String password;
}
