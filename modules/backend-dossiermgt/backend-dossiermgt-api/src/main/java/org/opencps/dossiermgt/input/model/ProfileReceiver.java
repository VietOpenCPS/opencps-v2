package org.opencps.dossiermgt.input.model;

public class ProfileReceiver {
    private Integer profileId;
    private String fromUnitCode;
    private String toUnitCode;
    private String createTime;
    private String status;
    private String status_profile;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public String getFromUnitCode() {
        return fromUnitCode;
    }

    public void setFromUnitCode(String fromUnitCode) {
        this.fromUnitCode = fromUnitCode;
    }

    public String getToUnitCode() {
        return toUnitCode;
    }

    public void setToUnitCode(String toUnitCode) {
        this.toUnitCode = toUnitCode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_profile() {
        return status_profile;
    }

    public void setStatus_profile(String status_profile) {
        this.status_profile = status_profile;
    }
}
