package com.example.vclassserver.embedded;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class vtopic_liker_pk implements Serializable {

    private String topicId;

    private String username;

    public vtopic_liker_pk() {
    }

    public vtopic_liker_pk(String topicId, String username) {
        this.topicId = topicId;
        this.username = username;
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
}
