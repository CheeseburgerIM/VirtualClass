package com.example.vclassserver.embedded;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class vcourse_schedule_pk implements Serializable {

    private String cid;

    private String date;

    public vcourse_schedule_pk() {
    }

    public vcourse_schedule_pk(String cid, String date) {
        this.cid = cid;
        this.date = date;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
