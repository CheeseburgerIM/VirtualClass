package com.example.vclassserver.embedded;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class vcomment_liker_pk implements Serializable {

    private String commentId;

    private String username;

    public vcomment_liker_pk() {
    }

    public vcomment_liker_pk(String commentId, String username) {
        this.commentId = commentId;
        this.username = username;
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
}
