package com.example.vclassserver.entity;

import com.example.vclassserver.embedded.vtopic_liker_pk;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "vtopic_liker")
public class vtopic_liker {

    @EmbeddedId
    private vtopic_liker_pk id;

    public vtopic_liker() {
    }

    public vtopic_liker(vtopic_liker_pk id) {
        this.id = id;
    }

    public vtopic_liker_pk getId() {
        return id;
    }

    public void setId(vtopic_liker_pk id) {
        this.id = id;
    }
}
