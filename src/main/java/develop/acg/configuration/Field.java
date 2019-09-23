package develop.acg.configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qiushui on 2019-08-27.
 */
@Getter
@Setter
public class Field {

    private String type;

    private String name;

    private String comment;

    private boolean nullable;

}
