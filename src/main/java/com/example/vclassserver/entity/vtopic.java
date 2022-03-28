package com.example.vclassserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vtopic")
public class vtopic {

    @Id
    @Column(name = "topicId")
    private String topicId;

    @Column(name = "username")
    private String username;

    @Column(name = "fid")
    private String fid;

    @Column(name = "content")
    private String content;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "likeNum")
    private int likeNum;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "cover")
    private String cover;

    @Column(name = "commentNum")
    private int commentNum;

    @Column(name = "isTeacherReply")
    private int isTeacherReply;

    @Column(name = "userAvatar")
    private String userAvatar;

    public vtopic() {
    }

    public vtopic(String topicId, String username, String fid, String content, String timestamp, String title, String description, String cover, String userAvatar) {
        this.topicId = topicId;
        this.username = username;
        this.fid = fid;
        this.content = content;
        this.timestamp = timestamp;
        this.likeNum = 0;
        this.title = title;
        this.description = description;
        this.cover = cover;
        this.commentNum = 0;
        this.isTeacherReply = 0;
        this.userAvatar = userAvatar;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getIsTeacherReply() {
        return isTeacherReply;
    }

    public void setIsTeacherReply(int isTeacherReply) {
        this.isTeacherReply = isTeacherReply;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
}
