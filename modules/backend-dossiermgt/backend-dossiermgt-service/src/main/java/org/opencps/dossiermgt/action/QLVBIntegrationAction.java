package org.opencps.dossiermgt.action;

public interface QLVBIntegrationAction {
    public String getTokenHG() throws Exception;
    public boolean sendVBHG(String token, long dossierId) throws Exception;
}
