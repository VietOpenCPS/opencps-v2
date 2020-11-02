package org.opencps.dossiermgt.action;

import org.opencps.dossiermgt.input.model.ProfileInModel;
import org.opencps.dossiermgt.input.model.ProfileReceiver;
import java.util.List;

public interface FrequencyIntegrationAction {
    public boolean crawlDossierLGSP(ProfileInModel profile, String token) throws Exception;
    public String getToken() throws Exception; // API 3.1
    public void syncDossierToPMNVManual(String token, ProfileInModel profile) throws Exception; //API 3.2
    public void syncDossierAndStatusToLGSPManual(String token, long dossierId) throws Exception; //API 3.3, 3.5, 3.6, 3.7
    public void updateStatusReceiver(String token, Integer profileId, String status) throws Exception; // API 3.8
    public List<ProfileReceiver> getDossiers(String token) throws Exception; // API 3.9
    public ProfileInModel getDetailDossier(String token, Integer profileId) throws Exception; //API 3.10
    public void sendStatusProfile(String token, long dossierId) throws Exception; //API 3.11
    public void syncDossierToDVCBoManual(String token, long dossierId) throws Exception; //API 3.12
    public void sendStatusProfileToDVCBo(String token, long dossierId) throws Exception; //API 3.13
}
