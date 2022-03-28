package com.example.vclassserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vcomment")
public class vcomment {

    @Id
    @Column(name = "commentId")
    private String commentId;

    @Column(name = "topicId")
    private String topicId;

    @Column(name = "username")
    private String username;

    @Column(name = "content")
    private String content;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "likeNum")
    private int likeNum;

    @Column(name = "type")
    private String type;

    @Column(name = "rootCommentId")
    private String rootCommentId;

    public vcomment() {
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getRootCommentId() {
        return rootCommentId;
    }

    public void setRootCommentId(String rootCommentId) {
        this.rootCommentId = rootCommentId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getLike() {
        return likeNum;
    }

    public void setLike(int like) {
        this.likeNum = like;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public vcomment(String commentId, String topicId, String username, String content, String timestamp, String type, String rootCommentId) {
        this.commentId = commentId;
        this.topicId = topicId;
        this.username = username;
        this.content = content;
        this.timestamp = timestamp;
        this.likeNum = 0;
        this.type = type;
        this.rootCommentId = rootCommentId;
    }
}
