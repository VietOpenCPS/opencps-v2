package org.opencps.dossiermgt.action;

import com.liferay.portal.kernel.json.JSONObject;

public interface QLCDIntegrationAction {
    //QLCD
    public String getToken(String unit) throws Exception;
    public String sendData(String token, JSONObject body, String unit) throws Exception;
    public String createRequestSoap(JSONObject body) throws Exception;
}
