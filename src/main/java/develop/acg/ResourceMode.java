package develop.acg;

import lombok.Getter;

/**
 * @author qiushui on 2019-08-22.
 */
public enum ResourceMode {

    PO("Jpa"),
    DOC("Mongo");

    @Getter
    private String alias;

    ResourceMode(String alias) {
        this.alias = alias;
    }
}
