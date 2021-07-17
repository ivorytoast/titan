package backend.nyc.com.titan.domain;

import java.io.Serializable;

public class SessionRelationshipId implements Serializable {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    private String id;
    private int version;


}
