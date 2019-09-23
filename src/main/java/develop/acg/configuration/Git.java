package develop.acg.configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qiushui on 2019-08-29.
 */
@Getter
@Setter
public class Git extends Enable{

    private String username;

    private String userEmail;

    private String remote;
}
