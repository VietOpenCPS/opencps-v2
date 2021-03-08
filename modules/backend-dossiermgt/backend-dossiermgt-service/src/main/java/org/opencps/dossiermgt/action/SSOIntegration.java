package org.opencps.dossiermgt.action;

import com.liferay.portal.kernel.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

public interface SSOIntegration {
    public String getUrlSSo(String state, String redirectUrl) throws Exception;
    public JSONObject getToken(String authorizationCode) throws Exception;
    public JSONObject doAuthMic(HttpServletRequest request, String authorizationCode) throws Exception;
}
