package com.example.vclassserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vclog")
public class vclog {

    @Id
    @Column(name = "lid")
    private String lid;

    @Column(name = "cid")
    private String cid;

    @Column(name = "name")
    private String name;

    @Column(name = "activity")
    private String activity;

    @Column(name = "fname")
    private String fname;

    @Column(name = "timestamp")
    private String timestamp;

    public vclog() {
    }

    public vclog(String lid, String cid, String name, String activity, String fname, String timestamp) {
        this.lid = lid;
        this.cid = cid;
        this.name = name;
        this.activity = activity;
        this.fname = fname;
        this.timestamp = timestamp;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
