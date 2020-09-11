package org.opencps.dossiermgt.input.model;

public class ResponseServiceDossier extends BaseResponseApi{
    private Integer profileId;
    private String sourceId;

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }
}
