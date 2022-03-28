package com.example.vclassserver.embedded;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class vcourse_mindMap_pk implements Serializable {

    private String cid;

    private String nodeId;

    public vcourse_mindMap_pk() {
    }

    public vcourse_mindMap_pk(String cid, String nodeId) {
        this.cid = cid;
        this.nodeId = nodeId;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
}
