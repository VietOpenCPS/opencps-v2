package org.opencps.api.dossier.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "postConnectId",
        "dossierId",
        "groupId",
        "userId",
        "postService",
        "postType",
        "orderNumber",
        "postStatus",
        "syncState"
})
@XmlRootElement(name = "PostConnectDetailModel")
public class PostConnectDetailModel {
    protected long postConnectId;
    protected long dossierId;
    protected long groupId;
    protected int userId;
    protected int postService;
    protected int postType;
    protected String orderNumber;
    protected int postStatus;
    protected int syncState;

    public long getPostConnectId() {
        return postConnectId;
    }

    public void setPostConnectId(long postConnectId) {
        this.postConnectId = postConnectId;
    }

    public long getDossierId() {
        return dossierId;
    }

    public void setDossierId(long dossierId) {
        this.dossierId = dossierId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostService() {
        return postService;
    }

    public void setPostService(int postService) {
        this.postService = postService;
    }

    public int getPostType() {
        return postType;
    }

    public void setPostType(int postType) {
        this.postType = postType;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(int postStatus) {
        this.postStatus = postStatus;
    }

    public int getSyncState() {
        return syncState;
    }

    public void setSyncState(int syncState) {
        this.syncState = syncState;
    }
}
