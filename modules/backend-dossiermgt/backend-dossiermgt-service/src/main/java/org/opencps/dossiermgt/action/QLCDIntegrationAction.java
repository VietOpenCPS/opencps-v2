package org.opencps.dossiermgt.action;

import com.liferay.portal.kernel.json.JSONObject;

public interface QLCDIntegrationAction {
    //QLCD
    public String getToken() throws Exception;
    public String sendData(String token, JSONObject body) throws Exception;
    public String createRequestSoap(JSONObject body) throws Exception;
}
