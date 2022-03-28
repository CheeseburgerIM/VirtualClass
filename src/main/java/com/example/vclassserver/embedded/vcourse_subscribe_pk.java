package com.example.vclassserver.embedded;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class vcourse_subscribe_pk implements Serializable {

    private String cid;

    private String username;

    public vcourse_subscribe_pk() {
    }

    public vcourse_subscribe_pk(String cid, String username) {
        this.cid = cid;
        this.username = username;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
