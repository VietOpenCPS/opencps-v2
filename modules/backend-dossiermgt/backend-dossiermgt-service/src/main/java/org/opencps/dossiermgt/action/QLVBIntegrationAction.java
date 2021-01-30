package org.opencps.dossiermgt.action;

import com.liferay.portal.kernel.service.ServiceContext;
import org.opencps.dossiermgt.model.Dossier;

public interface QLVBIntegrationAction {
    //QLVB Hau Giang
    public String getTokenHG() throws Exception;
    public boolean sendVBHG(String token, long dossierId) throws Exception;

    //QLVB TTTT
    public String getTokenTTTT() throws Exception;
    public boolean sendDocTTTT(String token, long dossierId) throws Exception;
    public void getDocEOfficeTTTT() throws Exception;
    public boolean sendDocEOfficeTTTT() throws Exception;

    //Do action QLVB
    public void doAction(long groupId, ServiceContext serviceContext, Dossier dossier, String actionCode);
}
