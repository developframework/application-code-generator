package develop.acg.configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qiushui on 2019-08-29.
 */
@Getter
@Setter
public class Profile {

    private Mysql mysql;

    private MongoDB mongodb;
}
