package com.example.vclassserver.embedded;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class vcuser_activity_pk implements Serializable {

    private String username;

    private String date;

    public vcuser_activity_pk() {
    }

    public vcuser_activity_pk(String username, String date) {
        this.username = username;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
