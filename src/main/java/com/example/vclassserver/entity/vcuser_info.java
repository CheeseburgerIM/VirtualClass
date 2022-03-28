package com.example.vclassserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vcuser_info")
public class vcuser_info {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "link")
    private String link;

    @Column(name = "grade")
    private String grade;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private String status;

    @Column(name = "followers")
    private int followers;

    @Column(name = "following")
    private int following;

    @Column(name = "name")
    private String name;

    @Column(name = "subtitle")
    private String subtitle;

    @Column(name = "avatar")
    private String avatar;

    public vcuser_info() {
    }

    public vcuser_info(String username, String status,String name) {
        this.username = username;
        this.status = status;
        this.name = name;
        this.address=null;
        this.avatar="//VClass//static//defaultAvatar.jpg";
        this.followers=0;
        this.following=0;
        this.grade=null;
        this.link=null;
        this.subtitle=null;
    }

    public vcuser_info(String username, String link, String grade, String address, String status, int followers, int following, String name, String subtitle, String avatar) {
        this.username = username;
        this.link = link;
        this.grade = grade;
        this.address = address;
        this.status = status;
        this.followers = followers;
        this.following = following;
        this.name = name;
        this.subtitle = subtitle;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
