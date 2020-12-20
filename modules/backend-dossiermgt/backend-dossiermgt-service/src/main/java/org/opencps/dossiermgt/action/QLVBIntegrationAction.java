package org.opencps.dossiermgt.action;

public interface QLVBIntegrationAction {
    //QLVB Hau Giang
    public String getTokenHG() throws Exception;
    public boolean sendVBHG(String token, long dossierId) throws Exception;

    //QLVB TTTT
    public String getTokenTTTT() throws Exception;
    public boolean sendDocTTTT(String token, long dossierId) throws Exception;
    public boolean getDocEOfficeTTTT() throws Exception;
}
