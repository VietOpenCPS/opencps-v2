package org.opencps.dossiermgt.input.model;

public class ResponseDetailDossier extends BaseResponseApi {
    private ProfileInModel profileInmodel;

    public ProfileInModel getProfileInmodel() {
        return profileInmodel;
    }

    public void setProfileInmodel(ProfileInModel profileInmodel) {
        this.profileInmodel = profileInmodel;
    }
}
