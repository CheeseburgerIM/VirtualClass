package com.example.vclassserver.entity;

import com.example.vclassserver.embedded.vcourse_mindMap_pk;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "vcourse_mindMap")
public class vcourse_mindMap {

    @EmbeddedId
    vcourse_mindMap_pk id;

    @Column(name = "isRoot")
    String isRoot;

    @Column(name = "parentId")
    String parentId;

    @Column(name = "topic")
    String topic;

    @Column(name = "direction")
    String direction;

    @Column(name = "expanded")
    String expanded;

    public vcourse_mindMap() {
    }

    public vcourse_mindMap(vcourse_mindMap_pk id, String isRoot, String parentId, String topic, String direction, String expanded) {
        this.id = id;
        this.isRoot = isRoot;
        this.parentId = parentId;
        this.topic = topic;
        this.direction = direction;
        this.expanded = expanded;
    }

    public vcourse_mindMap_pk getId() {
        return id;
    }

    public void setId(vcourse_mindMap_pk id) {
        this.id = id;
    }

    public String getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(String isRoot) {
        this.isRoot = isRoot;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getExpanded() {
        return expanded;
    }

    public void setExpanded(String expanded) {
        this.expanded = expanded;
    }
}
