/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.opencps.openidconnect.filter;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.liferay.portal.security.sso.openid.connect.*;
import com.liferay.portal.security.sso.openid.connect.constants.OpenIdConnectWebKeys;

import com.liferay.portal.security.sso.openid.connect.internal.OpenIdConnectProviderImpl;
import com.liferay.portal.security.sso.openid.connect.internal.configuration.OpenIdConnectProviderConfiguration;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


/**
 * Participates in every login and logout that triggers an HTTP request to
 * Liferay Portal.
 *
 * <p>
 * This class checks if the HTTP session attribute <code>CAS_FORCE_LOGOUT</code>
 * is set by CASAutoLogin and, if so, redirects the browser to the configured
 * CAS Logout URL.
 * </p>
 *
 * <p>
 * Next, if the session attribute <code>CAS_LOGIN</code> has not already been
 * set and no ticket parameter is received via the HTTP servlet request, the CAS
 * server login URL is constructed based on the configuration of the Login URL,
 * the Server name, and the Service URL and the browser is redirected to this
 * URL. If a ticket parameter was received, it will be validated.
 * </p>
 *
 * <p>
 * Validation includes sending a SAML request containing the ticket to the CAS
 * server, and in return receiving an assertion of user attributes. However,
 * only the principal attribute is used and it is set as the session attribute
 * <code>CAS_LOGIN</code> as mentioned earlier. It is important that the CAS
 * server issues a principal of the same type that the portal instance is
 * configured to use (e.g., screen name versus email address).
 * </p>
 *
 * @author Michael Young
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Tina Tian
 * @author Zsolt Balogh
 */
@Component(
        immediate = true,
        property = {
                "before-filter=Auto Login Filter", "servlet-context-name=",
                "servlet-filter-name=OpenCPS SSO OpenId Connect LogOut Filter",
                "url-pattern=/c/portal/logout"
        },
        service = {Filter.class, OpenIdConnectFilterLogOut.class}
)
public class OpenIdConnectFilterLogOut extends BaseFilter {


    @Override
    protected Log getLog() {
        return _log;
    }



    @Override
    protected void processFilter(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws Exception {

        HttpSession session = httpServletRequest.getSession();

        long companyId = _portal.getCompanyId(httpServletRequest);

        String pathInfo = httpServletRequest.getPathInfo();

        
        if (Validator.isNotNull(pathInfo) &&
                pathInfo.contains("/portal/logout")) {

            OpenIdConnectSession openIdConnectSession =
                    (OpenIdConnectSession)session.getAttribute(
                            OpenIdConnectWebKeys.OPEN_ID_CONNECT_SESSION);

            if(Validator.isNotNull(openIdConnectSession)) {
                String openIDConnectProviderName = openIdConnectSession.getOpenIdProviderName();

//                _log.info("openIDConnectProviderName : " + openIDConnectProviderName);
                OpenIdConnectProvider openIdConnectProvider = _openIdConnectProviderRegistry.getOpenIdConnectProvider(openIDConnectProviderName);

                String accessToken = openIdConnectSession.getAccessTokenValue();

                String refreshToken = openIdConnectSession.getRefreshTokenValue();

                String[] valuesAccessToken =  getAPILogOutKeycloakFromAccessToken(accessToken);

                String apiLogout = valuesAccessToken[0];

                String client_id = valuesAccessToken[1];

                String client_secret = openIdConnectProvider.getClientSecret();

//                _log.info("client_secret : " + client_secret);
                if(Validator.isNotNull(client_secret)){
                    Boolean statusLogout = postAPILogOutKeyCloak(apiLogout, client_id, client_secret,refreshToken, accessToken);

                    _log.debug("statusLogout Keycloak: " + statusLogout);
                } else {
                    _log.error("client_secret is : Null! Please Configure client_secret on portal-setup-wizard.properties");
                }
            }
            session.invalidate();

        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }

    private String[] getAPILogOutKeycloakFromAccessToken(String accessToken) throws JSONException {
        String[] splitAccessToken = accessToken.split("\\.");
        Base64.Decoder decoder = Base64.getDecoder();
        String bodyAccessToken = new String(decoder.decode(splitAccessToken[1]));
        JSONObject bodyAccess = JSONFactoryUtil.createJSONObject(bodyAccessToken);
        String iss = bodyAccess.get("iss").toString();
        String client_id = bodyAccess.get("azp").toString();
        String ApiLogout = iss + "/protocol/openid-connect/logout";
        return new String[] {ApiLogout, client_id};
    }

    private Boolean postAPILogOutKeyCloak(String url,String client_id, String client_secret, String refresh_token, String access_token) throws Exception  {

        StringBuilder paramsBody = new StringBuilder();
        paramsBody.append("client_id").append(StringPool.EQUAL).append(client_id).append(StringPool.AMPERSAND);
        paramsBody.append("client_secret").append(StringPool.EQUAL).append(client_secret).append(StringPool.AMPERSAND);
        paramsBody.append("refresh_token").append(StringPool.EQUAL).append(refresh_token);

        String body = paramsBody.toString();
        byte[] postDataBytes = body.toString().getBytes("UTF-8");
        int postDataLength = postDataBytes.length;

        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + access_token);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("charset", "utf-8");
        connection.setRequestProperty("Content-Length", Integer.toString(postDataLength));


        try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
            wr.write(postDataBytes);
        }
        StringBuilder sb = new StringBuilder();
        try {
            connection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String output = "";

            sb = new StringBuilder();

            while ((output = bufferedReader.readLine()) != null) {
                sb.append(output);
            }
            bufferedReader.close();
            int responseCode = connection.getResponseCode();
            if(responseCode==204) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }


    }
    private static final Log _log = LogFactoryUtil.getLog(OpenIdConnectFilterLogOut.class);

    @Reference
    private Http _http;

    @Reference
    private Portal _portal;

    @Reference
    private OpenIdConnectProviderRegistry _openIdConnectProviderRegistry;

}
