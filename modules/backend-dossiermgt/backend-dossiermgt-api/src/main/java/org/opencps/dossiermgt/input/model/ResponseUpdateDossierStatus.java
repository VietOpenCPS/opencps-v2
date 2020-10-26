package org.opencps.dossiermgt.input.model;

public class ResponseUpdateDossierStatus extends BaseResponseApi{
    private String[] profileId;

    public String[] getProfileId() {
        return profileId;
    }

    public void setProfileId(String[] profileId) {
        this.profileId = profileId;
    }
}
