package com.example.vclassserver.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vcourse")
public class vcourse {

    @Id
    @Column(name = "cid")
    private String cid;

    @Column(name = "username")
    private String username;

    @Column(name = "cname")
    private String cname;

    @Column(name = "ctype")
    private String ctype;

    @Column(name = "cdesc")
    private String cdesc;

    @Column(name = "credit")
    private int credit;

    @Column(name = "cnum")
    private String cnum;

    @Column(name = "duration")
    private int duration;

    @Column(name = "teacher")
    private String teacher;

//    @OneToMany(mappedBy = "vcourse",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//    private List<vcourse_chapter> chapterList;

    public vcourse() {
    }

    public vcourse(String cid, String username, String cname, String ctype, String cdesc, int credit, String cnum, int duration, String teacher) {
        this.cid = cid;
        this.username = username;
        this.cname = cname;
        this.ctype = ctype;
        this.cdesc = cdesc;
        this.credit = credit;
        this.cnum = cnum;
        this.duration = duration;
        this.teacher = teacher;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getCdesc() {
        return cdesc;
    }

    public void setCdesc(String cdesc) {
        this.cdesc = cdesc;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getCnum() {
        return cnum;
    }

    public void setCnum(String cnum) {
        this.cnum = cnum;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
