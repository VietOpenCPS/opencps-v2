package org.opencps.dossiermgt.action;

import org.opencps.dossiermgt.input.model.ProfileInModel;
import org.opencps.dossiermgt.input.model.ProfileReceiver;
import org.opencps.dossiermgt.input.model.ResponseDetailDossier;

import java.util.List;

public interface FrequencyIntegrationAction{
    public boolean crawlDossierLGSP(ProfileInModel profile) throws Exception;
    public String getToken() throws Exception;
    public List<ProfileReceiver> getDossiers(String token) throws Exception;
    public ProfileInModel getDetailDossier(String token, Integer profileId) throws Exception;
    public void updateStatusReceiver(String token, Integer profileId, String status) throws Exception; // API 3.7
    public void syncDossierToLGSP(String token, ProfileInModel profile) throws Exception;
    public void sendStatusProfile(String token, long dossierId) throws Exception; //API 3.10
}
