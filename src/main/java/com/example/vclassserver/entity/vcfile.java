package com.example.vclassserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vcfile")
public class vcfile {

    @Id
    @Column(name = "fid")
    private String fid;

    @Column(name = "cid")
    private String cid;

    @Column(name = "chid")
    private String chid;

    @Column(name = "username")
    private String username;

    @Column(name = "fname")
    private String fname;

    @Column(name = "ftype")
    private String ftype;

    @Column(name = "ftag")
    private String ftag;

    @Column(name = "chnum")
    private int chnum;

    public vcfile() {
    }

    public vcfile(String fid, String cid, String chid, String username, String fname, String ftype, String ftag, int chnum) {
        this.fid = fid;
        this.cid = cid;
        this.chid = chid;
        this.username = username;
        this.fname = fname;
        this.ftype = ftype;
        this.ftag = ftag;
        this.chnum = chnum;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getChid() {
        return chid;
    }

    public void setChid(String chid) {
        this.chid = chid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    public String getFtag() {
        return ftag;
    }

    public void setFtag(String ftag) {
        this.ftag = ftag;
    }

    public int getChnum() {
        return chnum;
    }

    public void setChnum(int chnum) {
        this.chnum = chnum;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
