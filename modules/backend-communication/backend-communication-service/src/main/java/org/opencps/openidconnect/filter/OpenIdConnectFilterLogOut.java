package org.opencps.openidconnect.filter;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.settings.CompanyServiceSettingsLocator;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import com.liferay.portal.security.sso.openid.connect.internal.configuration.OpenIdConnectProviderConfiguration;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.liferay.portal.security.sso.openid.connect.OpenIdConnectSession;
import com.liferay.portal.security.sso.openid.connect.constants.OpenIdConnectWebKeys;

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
        configurationPid = "com.liferay.portal.security.sso.cas.configuration.CASConfiguration",
        immediate = true,
        property = {
                "before-filter=Auto Login Filter", "dispatcher=FORWARD",
                "dispatcher=REQUEST", "servlet-context-name=",
                "servlet-filter-name=SSO CAS Filter",
                "url-pattern=/c/portal/logout"
        },
        service = Filter.class
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

                String accessToken = openIdConnectSession.getAccessTokenValue();

                String refreshToken = openIdConnectSession.getRefreshTokenValue();

                String[] valuesAccessToken =  getAPILogOutKeycloakFromAccessToken(accessToken);

                String apiLogout = valuesAccessToken[0];

                String client_id = valuesAccessToken[1];

                String client_secret = Validator.isNotNull(System.getProperty("org.opencps.keycloak.client-secret"))
                        ? String.valueOf(System.getProperty("org.opencps.keycloak.client-secret"))
                        : null;
                if(Validator.isNotNull(client_secret)){
                    Boolean statusLogout = postAPILogOutKeyCloak(apiLogout, client_id, client_secret,refreshToken, accessToken);

                    _log.info("statusLogout Keycloak: " + statusLogout);
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
    	paramsBody.append("client_id").append("=").append(client_id).append("&");
    	paramsBody.append("client_secret").append("=").append(client_secret).append("&");
    	paramsBody.append("refresh_token").append("=").append(refresh_token);

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

}
