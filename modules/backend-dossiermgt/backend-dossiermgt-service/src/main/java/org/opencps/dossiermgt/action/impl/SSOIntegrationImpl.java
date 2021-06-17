package org.opencps.dossiermgt.action.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import org.opencps.backend.dossiermgt.serviceapi.ApiThirdPartyService;
import org.opencps.backend.dossiermgt.serviceapi.ApiThirdPartyServiceImpl;
import org.opencps.communication.model.ServerConfig;
import org.opencps.dossiermgt.action.SSOIntegration;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class SSOIntegrationImpl implements SSOIntegration {
    private JSONObject configJson;
    private ApiThirdPartyService apiService;
    private Log _log = LogFactoryUtil.getLog(SSOIntegrationImpl.class);
    private static final String SERVER_CONFIG_NULL = "There is no server config frequency";
    private static final String PARSE_CONFIG_JSON_FAIL= "Create object json from config error";
    private ObjectMapper objectMapper;
    private ServerConfig serverConfig;
    private static final Integer STATUS_CODE_TOKEN_NULL = 1;
    private static final Integer STATUS_CODE_USER_MIC_NULL = 2;
    private static final Integer STATUS_CODE_SERVER_ERROR = 3;
    private static final Integer STATUS_CODE_SUCCESS = 5;
    private static final Integer STATUS_CODE_USER_FDS_NULL = 6;

    public SSOIntegrationImpl(ServerConfig serverConfig) throws Exception {
        this.apiService = new ApiThirdPartyServiceImpl();

        if(Validator.isNull(serverConfig.getConfigs())
                || serverConfig.getConfigs().isEmpty()) {
            throw new Exception(SERVER_CONFIG_NULL);
        }
        this.configJson = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
        this.serverConfig = serverConfig;
        if(Validator.isNull(this.configJson)) {
            throw new Exception(PARSE_CONFIG_JSON_FAIL);
        }
    }

    @Override
    public String getUrlSSo(String state, String redirectUrl) throws Exception{
        try {
            String authServer = this.configJson.getString("authServer");
            String authEndpoint =  this.configJson.getString("authEndpoint");
            String clientId = this.configJson.getString("clientId");
            String callbackUrl = this.configJson.getString("callbackUrl");
            String scope = this.configJson.getString("scope");
            String acrValues = this.configJson.getString("arcValues");
            state = state + "@" + redirectUrl;
            state = Base64.getEncoder().encodeToString(state.getBytes());
            String urlSSO = authServer + authEndpoint + "?response_type=code" + "&client_id=" + clientId
                    + "&redirect_uri=" + callbackUrl + "&scope=" + scope + "&acr_values=" + acrValues + "&state="
                    + state;
            return urlSSO;
        } catch (Exception e) {
            _log.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public JSONObject getToken(String authorizationCode) throws Exception {
        try {
            String authServer = this.configJson.getString("authServer");
            String tokenEndpoint = this.configJson.getString("tokenEndpoint");
            String clientId = this.configJson.getString("clientId");
            String callbackUrl = this.configJson.getString("callbackUrl");
            String clientSecret = this.configJson.getString("clientSecret");

            String urlGetToken = authServer + tokenEndpoint;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("grant_type", "authorization_code");
            body.add("code", authorizationCode);
            body.add("redirect_uri", callbackUrl);
            body.add("client_id", clientId);
            body.add("client_secret", clientSecret);

            return this.apiService.callApiEncode(urlGetToken, headers, body, false, null);
        } catch (Exception e) {
            _log.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public JSONObject doAuthMic(HttpServletRequest request, String authorizationCode) throws Exception {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        try {
            //Get access token from MIC server
            JSONObject tokenInfo = this.getToken(authorizationCode);
            if(Validator.isNull(tokenInfo) || !tokenInfo.has("access_token")) {
                result.put("statusCode", STATUS_CODE_TOKEN_NULL);
                return result;
            }

            String accessToken = tokenInfo.getString("access_token");
            String idToken     = tokenInfo.getString("id_token");

            String authServer = this.configJson.getString("authServer");
            String userInfoEndpoint = this.configJson.getString("userInfoEndpoint");

            //Get user info from MIC server
            String urlGetUserInfo = authServer + userInfoEndpoint;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.set("Authorization", "Bearer " + accessToken);
            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            JSONObject userInfo =  this.apiService.callApiEncode(urlGetUserInfo, headers, body, false, null);

            if(Validator.isNull(userInfo) || userInfo.length() == 0) {
                result.put("statusCode", STATUS_CODE_USER_MIC_NULL);
                return result;
            }

            String soCmnd     = userInfo.getString("SoCMND");
            String soDinhdanh = userInfo.getString("SoDinhDanh");

            String identityUser = Validator.isNotNull(soCmnd) && !soCmnd.isEmpty() ? soCmnd : soDinhdanh;

            if(Validator.isNull(identityUser) || identityUser.isEmpty()) {
                throw new Exception("No identity found with token: " + accessToken);
            }
            final int EMPLOYEE_ACTIVE = 1;

            Employee employee = EmployeeLocalServiceUtil.findByWorkingStatusAndEmployeeNo(EMPLOYEE_ACTIVE, identityUser);
            if(Validator.isNull(employee)) {
                result.put("statusCode", STATUS_CODE_USER_FDS_NULL);
                return result;
            }

            if(Validator.isNull(employee.getMappingUserId()) || employee.getMappingUserId() == 0) {
                throw new Exception("No mapping user Id for identity user: " + identityUser);
            }

            User userLogin = UserLocalServiceUtil.fetchUser(employee.getMappingUserId());

            if(Validator.isNull(userLogin)) {
                result.put("statusCode", STATUS_CODE_USER_FDS_NULL);
                return result;
            }

            if(userLogin.getStatus() != WorkflowConstants.STATUS_APPROVED) {
                throw new Exception("User " + employee.getMappingUserId() + " has been locked");
            }

            HttpSession session = request.getSession();
            session = renewSession(request, session);
            session.setAttribute("_EMPLOYEE_NO", identityUser);
            session.setMaxInactiveInterval(36000);

            result.put("statusCode", STATUS_CODE_SUCCESS);
            result.put("idToken", idToken);
            result.put("accessToken", accessToken);

            return result;
        } catch (Exception e) {
            _log.error(e);
            throw new Exception(e.getMessage());
        }
    }

    private HttpSession renewSession(HttpServletRequest request, HttpSession session) throws Exception {

        String[] protectedAttributeNames = new String[] { "CAS_LOGIN", "HTTPS_INITIAL", "LAST_PATH",
                "OPEN_ID_CONNECT_SESSION" };

        Map<String, Object> protectedAttributes = new HashMap<>();

        for (String protectedAttributeName : protectedAttributeNames) {
            Object protectedAttributeValue = session.getAttribute(protectedAttributeName);

            if (protectedAttributeValue == null) {
                continue;
            }

            protectedAttributes.put(protectedAttributeName, protectedAttributeValue);
        }

        session.invalidate();

        session = request.getSession(true);

        for (String protectedAttributeName : protectedAttributeNames) {
            Object protectedAttributeValue = protectedAttributes.get(protectedAttributeName);

            if (protectedAttributeValue == null) {
                continue;
            }

            session.setAttribute(protectedAttributeName, protectedAttributeValue);
        }

        return session;
    }
}
