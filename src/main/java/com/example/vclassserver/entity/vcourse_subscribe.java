package com.example.vclassserver.entity;

import com.example.vclassserver.embedded.vcourse_subscribe_pk;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "vcourse_subscribe")
public class vcourse_subscribe {

    @EmbeddedId
    private vcourse_subscribe_pk id;

    public vcourse_subscribe() {
    }

    public vcourse_subscribe(vcourse_subscribe_pk id) {
        this.id = id;
    }

    public vcourse_subscribe_pk getId() {
        return id;
    }

    public void setId(vcourse_subscribe_pk id) {
        this.id = id;
    }
}
