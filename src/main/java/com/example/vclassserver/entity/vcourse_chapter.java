package com.example.vclassserver.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vcourse_chapter")
public class vcourse_chapter {

    @Id
    @Column(name = "chid")
    private String chid;

    @Column(name = "chname")
    private String chname;

    @Column(name = "chnum")
    private int chnum;

    @Column(name = "chtag")
    private String chtag;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="cid")
    private vcourse vcourse;

    public vcourse_chapter() {
    }

    public vcourse_chapter(String chid, String chname, int chnum, String chtag) {
        this.chid = chid;
        this.chname = chname;
        this.chnum = chnum;
        this.chtag = chtag;
    }

    public vcourse_chapter(String chid, String chname, int chnum, String chtag, com.example.vclassserver.entity.vcourse vcourse) {
        this.chid = chid;
        this.chname = chname;
        this.chnum = chnum;
        this.chtag = chtag;
        this.vcourse = vcourse;
    }

    public String getChid() {
        return chid;
    }

    public void setChid(String chid) {
        this.chid = chid;
    }

    public String getChname() {
        return chname;
    }

    public void setChname(String chname) {
        this.chname = chname;
    }

    public int getChnum() {
        return chnum;
    }

    public void setChnum(int chnum) {
        this.chnum = chnum;
    }

    public String getChtag() {
        return chtag;
    }

    public void setChtag(String chtag) {
        this.chtag = chtag;
    }

    public com.example.vclassserver.entity.vcourse getVcourse() {
        return vcourse;
    }

    public void setVcourse(com.example.vclassserver.entity.vcourse vcourse) {
        this.vcourse = vcourse;
    }
}
