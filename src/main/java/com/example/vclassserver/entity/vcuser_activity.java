package com.example.vclassserver.entity;

import com.example.vclassserver.embedded.vcuser_activity_pk;

import javax.persistence.*;

@Entity
@Table(name = "vcuser_activity")
public class vcuser_activity {

    @EmbeddedId
    private vcuser_activity_pk id;

    @Column(name = "times")
    private int times;

    public vcuser_activity() {
    }

    public vcuser_activity(vcuser_activity_pk id, int times) {
        this.id = id;
        this.times = times;
    }

    public vcuser_activity_pk getId() {
        return id;
    }

    public void setId(vcuser_activity_pk id) {
        this.id = id;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
