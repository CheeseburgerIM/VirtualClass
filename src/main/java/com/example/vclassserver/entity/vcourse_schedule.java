package com.example.vclassserver.entity;

import com.example.vclassserver.embedded.vcourse_schedule_pk;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "vcourse_schedule")
public class vcourse_schedule {

    @EmbeddedId
    private vcourse_schedule_pk id;

    @Column(name = "content")
    private String content;

    @Column(name = "type")
    private String type;

    public vcourse_schedule() {
    }

    public vcourse_schedule(vcourse_schedule_pk id, String type, String content) {
        this.id = id;
        this.type = type;
        this.content = content;
    }

    public vcourse_schedule_pk getId() {
        return id;
    }

    public void setId(vcourse_schedule_pk id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
