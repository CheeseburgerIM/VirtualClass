package com.example.vclassserver.entity;

import com.example.vclassserver.embedded.vcomment_liker_pk;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "vcomment_liker")
public class vcomment_liker {

    @EmbeddedId
    private vcomment_liker_pk id;

    public vcomment_liker() {
    }

    public vcomment_liker(vcomment_liker_pk id) {
        this.id = id;
    }

    public vcomment_liker_pk getId() {
        return id;
    }

    public void setId(vcomment_liker_pk id) {
        this.id = id;
    }
}
