package org.opencps.dossiermgt.action;

import org.opencps.dossiermgt.input.model.ProfileInModel;
import org.opencps.dossiermgt.input.model.ProfileReceiver;

import java.util.List;

public interface FrequencyIntegrationAction{
    public void crawlDossierLGSP(ProfileInModel profile) throws Exception;
    public String getToken() throws Exception;
    public List<ProfileReceiver> getDossiers(String token) throws Exception;
    public ProfileInModel getDetailDossier(String token, Integer profileId) throws Exception;
}
