package org.opencps.dossiermgt.input.model;

import java.util.List;

public class ResponseListDossier extends BaseResponseApi{
    private List<ProfileReceiver> profileReceivers;

    public List<ProfileReceiver> getProfileReceivers() {
        return profileReceivers;
    }

    public void setProfileReceivers(List<ProfileReceiver> profileReceivers) {
        this.profileReceivers = profileReceivers;
    }
}
