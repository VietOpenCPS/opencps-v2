package org.opencps.dossiermgt.action.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.backend.dossiermgt.logistic.ViettelPostTerm;
import org.opencps.backend.dossiermgt.service.util.UtilsFile;
import org.opencps.backend.dossiermgt.serviceapi.ApiThirdPartyService;
import org.opencps.backend.dossiermgt.serviceapi.ApiThirdPartyServiceImpl;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.FrequencyIntegrationAction;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.FrequencyOfficeConstants;
import org.opencps.dossiermgt.constants.KeyPayTerm;
import org.opencps.dossiermgt.input.model.*;
import org.opencps.dossiermgt.model.*;
import org.opencps.dossiermgt.rest.utils.SyncServerTerm;
import org.opencps.dossiermgt.service.*;
import org.opencps.kernel.context.MBServiceContextFactoryUtil;
import org.opencps.kernel.prop.PropKeys;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.*;
import java.net.InetAddress;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class FrequencyIntegrationActionImpl implements FrequencyIntegrationAction {
    private JSONObject configJson;
    private ApiThirdPartyService apiService;
    private Log _log = LogFactoryUtil.getLog(FrequencyIntegrationActionImpl.class);
    private static final String SERVER_CONFIG_NULL = "There is no server config frequency";
    private static final String PARSE_CONFIG_JSON_FAIL= "Create object json from config error";
    private ObjectMapper objectMapper;
    private ServerConfig serverConfig;
    private static final String FORMAT_DATE_LGSP = "yyyy/MM/dd HH:mm:ss";
    private static final String REQUEST_PAYMENT = "true";
    private static final String REQUEST_PAYMENT_STATUS = "YEUCAU_THANHTOAN";


    public FrequencyIntegrationActionImpl(ServerConfig serverConfig) throws Exception{
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
    public boolean crawlDossierLGSP(ProfileInModel profile, String token) throws Exception{
        try {
            long groupId = serverConfig.getGroupId();
            long companyId = serverConfig.getCompanyId();
            User user = UserLocalServiceUtil.getUserByEmailAddress(companyId,
                    this.configJson.getString("username"));

            if(Validator.isNull(user)) {
                throw new Exception("No user found");
            }

            Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
            if(Validator.isNull(company)) {
                throw new Exception("No company found");
            }

            long userId = user.getUserId();
            ServiceContext serviceContext = MBServiceContextFactoryUtil.create(companyId, groupId, userId);
            if(Validator.isNull(serviceContext)) {
                throw new Exception("No service context");
            }

            String serviceCodeCTS = "";
            String status = profile.getStatus();
            Dossier dossier = null;
            boolean isAutoNextAction = false;

            JSONArray listServiceConfig = this.configJson.getJSONArray("serviceConfig");
            if(Validator.isNull(listServiceConfig) || listServiceConfig.length() == 0) {
                throw new Exception("No service config");
            }

            int length = listServiceConfig.length();
            JSONObject oneServiceConfig;

            //Check is auto action and mapping service code
            if(status.equals(FrequencyOfficeConstants.STATUS_RECEIVE)) {
                serviceCodeCTS = profile.getProcedures_code();

                for(int i = 0; i< length; i ++) {
                    oneServiceConfig = listServiceConfig.getJSONObject(i);
                    if(oneServiceConfig.getString("procedures_code").equals(serviceCodeCTS)) {
                        profile.setServiceCode(oneServiceConfig.getString("serviceCode"));
                        profile.setGovAgencyCode(oneServiceConfig.getString("govAgencyCode"));
                        profile.setTemplateNo(oneServiceConfig.getString("templateNo"));

                        if(oneServiceConfig.has("autoNextAction")
                                && oneServiceConfig.getBoolean("autoNextAction")) {
                            isAutoNextAction = true;
                        }

                        break;
                    }
                }
            } else {
                dossier = DossierLocalServiceUtil.getByRef(groupId, profile.getRef_code());
                if(Validator.isNull(dossier)) {
                    _log.info(status + "|" + " ReferenceId " + profile.getRef_code() + " not found in DB MCDT" +
                            ", this record will be delete on LGSP");
                    return true;
                }

                serviceCodeCTS = dossier.getServiceCode();

                for(int i = 0; i< length; i ++) {
                    oneServiceConfig = listServiceConfig.getJSONObject(i);
                    if(oneServiceConfig.getString("serviceCode").equals(serviceCodeCTS)) {

                        if(oneServiceConfig.has("autoNextAction")
                                && oneServiceConfig.getBoolean("autoNextAction")) {
                            isAutoNextAction = true;
                        }

                        break;
                    }
                }
            }

            //get action code
            JSONArray listActionCode = this.configJson.getJSONArray("actions");
            int lengthListActionCode = listActionCode.length();
            JSONObject oneActionCode;
            String actionCode = "";
            String actionCodeRequestPayment = "";
            String statusConst = "";

            for(int i = 0; i< lengthListActionCode; i ++) {
                oneActionCode = listActionCode.getJSONObject(i);
                statusConst   = oneActionCode.getString("status");

                if(statusConst.equals(status)) {
                    actionCode = oneActionCode.getString("actionCode");

                    if(isAutoNextAction && !oneActionCode.getString("actionCodeAuto").isEmpty()) {
                        actionCode = oneActionCode.getString("actionCodeAuto");
                    }
                }

                if(statusConst.equals(REQUEST_PAYMENT_STATUS)) {
                    actionCodeRequestPayment = oneActionCode.getString("actionCode");
                }
            }

            if(actionCode.isEmpty() && actionCodeRequestPayment.isEmpty()) {
                throw new Exception("No action code mapped with refCode: " + profile.getRef_code() + ", status: " + status);
            }

            _log.info("Doing action: " + actionCode + "...");

            //Crawling
            if(status.equals(FrequencyOfficeConstants.STATUS_RECEIVE)) {
                _log.info("TIEPNHAN|Receiving dossier with ref code: " + profile.getRef_code() + "...");
                Dossier dossierNew;
                dossierNew = DossierLocalServiceUtil.getByRef(groupId, profile.getRef_code());
                if(Validator.isNotNull(dossierNew)) {
                    _log.error("TIEPNHAN|Ho so da duoc tiep nhan: " + dossierNew.getDossierNo());
                    return true;
                }

                if(Validator.isNull(profile.getServiceCode())
                        || profile.getServiceCode().isEmpty()
                        || Validator.isNull(profile.getGovAgencyCode())
                        || profile.getGovAgencyCode().isEmpty()
                        || Validator.isNull(profile.getTemplateNo())
                        || profile.getTemplateNo().isEmpty()
                ) {
                    throw new Exception("TIEPNHAN|No service code mapped when create dossier");
                }

                dossierNew = CPSDossierBusinessLocalServiceUtil.createDossierFrequency(
                        groupId, company, user, serviceContext, profile, actionCode);

                //Save file dinh kem
                this.addDossierFile(profile, groupId, serviceContext, dossierNew);

                //Khong can gui cho cac don vi khac neu khong biet ho so tu dau (hoac truc tiep tai mot cua)
                if (profile.getFrom_unit_code().isEmpty()) {
                    _log.info("TIEPNHAN|Received dossier with ref code: " + profile.getRef_code());
                    return true;
                }

                if(isAutoNextAction) {
                    if(!profile.getFrom_unit_code()
                            .equals(this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE))) {
                        //Check list 3 when dossier from PMNV and auto processing is enable, sync dossier to DVC
                        // and sync dossier status to PMNV with profiles_status = 4,
                        _log.info("Auto send dossier to DVC BO and status to PMNV...");
                        //Sync dossier to DVC BO
                        this.syncDossierToDVCBoManual(token, dossierNew.getDossierId(), false);

                    }
                    //Sync dossier status to both DVC BO and PMNV
                    this.sendStatusProfile(token, dossierNew.getDossierId(), null);
                } else {
                    if(profile.getFrom_unit_code().equals(this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE))) {
                        //Check list 2 when dossier from DVCBO, have to sync to PMNV
                        this.syncDossierToPMNVManual(token, profile);
                    }
                }

                _log.info("TIEPNHAN|Received dossier with ref code: " + profile.getRef_code());
                return true;
            }

            //Case API RUT, CAP NHAT, THANH TOAN, TU CHOI, KET QUA

            //Lay from unit code cua dossier
            JSONObject metaDataJson = null;
            if(dossier == null) {
                throw new Exception("No dossier was found");
            }

            if (Validator.isNotNull(dossier.getMetaData()) && !dossier.getMetaData().isEmpty()) {
                metaDataJson = JSONFactoryUtil.createJSONObject(dossier.getMetaData());
            }

            String fromUnitCode = "";
            if (Validator.isNotNull(metaDataJson)
                    && !metaDataJson.getString("fromUnitCode").isEmpty()) {
                fromUnitCode = metaDataJson.getString("fromUnitCode");
            }

            if(fromUnitCode.isEmpty()) {
                throw new Exception("Dossier has no fromUnitCode with ref code: " + profile.getRef_code());
            }

            //Case THANH TOAN
            if(status.equals(FrequencyOfficeConstants.STATUS_PAYMENT) && profile.getIs_request_payment().equals("false")) {
                //case MCDT
                _log.info("THANHTOAN|Receiving payment info with ref code: " + profile.getRef_code() + "...");
                PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByDossierId(dossier.getGroupId(), dossier.getDossierId());
                if(Validator.isNull(paymentFile)) {
                    throw new Exception("THANHTOAN|No payment file for dossierId: " + dossier.getDossierId());
                }
                String urlBienLai;

                if(Validator.isNotNull(profile.getProfileAttachments()) && profile.getProfileAttachments().size() > 0) {
                    //get thong tin url in bien lai(hien h chi co 1 payment nen chi lay 1 url dau tien)
                    List<ProfileAttachment> profileAttachments = profile.getProfileAttachments();

                    for(ProfileAttachment oneProfileAttach: profileAttachments) {
                        urlBienLai = oneProfileAttach.getAttachment_file_url();
                        if(!urlBienLai.isEmpty()) {
                            break;
                        }
                    }
                }
                final Integer DONE_PAY_MONEY = 5;

                ProfilePaymentFDS payment = new ProfilePaymentFDS();
                payment.setRequestPayment(DONE_PAY_MONEY);
                payment.setFeeAmount((int)paymentFile.getPaymentAmount());

                profile.setPayment(payment);

                this.doActionFrequency(groupId, serviceContext, dossier, profile, actionCode);
                _log.info("THANHTOAN|Received payment info with ref code: " + profile.getRef_code());
                return true;
            } else if (status.equals(FrequencyOfficeConstants.STATUS_PAYMENT) && profile.getIs_request_payment().equals("true")) {
                //Checklist 2: Luong 3.4
                //case cuc tan so gui yeu cau thanh toan
                //Cap nhat trang thai ho so => cho thanh toan
                if(actionCodeRequestPayment.isEmpty()) {
                    throw new Exception("THANHTOAN|Case send request payment - No action code mapped");
                }
                this.doActionFrequency(groupId, serviceContext, dossier, profile, actionCodeRequestPayment);
                //Sau khi update trang thai ho so 1 cua sang cho` thanh toan, se call api
                //SyncDossierToLGSPManual de dong bo trang thai ho so len DVC Bo (se de o cau hinh de auto call)
                return true;
            }

            //Case API RUT, CAP NHAT
            if(status.equals(FrequencyOfficeConstants.STATUS_PULL_OUT) || status.equals(FrequencyOfficeConstants.STATUS_UPDATE)) {
                _log.info("CAPNHAT|RUT|Updating dossier with ref code: " + profile.getRef_code() + "...");
                this.doActionFrequency(groupId, serviceContext, dossier, profile, actionCode);
                if(status.equals(FrequencyOfficeConstants.STATUS_UPDATE)) {
                    this.addDossierFile(profile, groupId, serviceContext, dossier);
                }
                _log.info("CAPNHAT|RUT|Updated dossier with ref code: " + profile.getRef_code());
                return true;
            }

            //Case API TU CHOI
            if(status.equals(FrequencyOfficeConstants.STATUS_DENIED)) {
                _log.info("TUCHOI|Updating dossier with ref code: " + profile.getRef_code() + "...");
                this.doActionFrequency(groupId, serviceContext, dossier, profile, actionCode);
                _log.info("TUCHOI|Updated dossier with ref code: " + profile.getRef_code());
                return true;
            }

            //Case API KET QUA
            if(status.equals(FrequencyOfficeConstants.STATUS_RESULT)) {
                //If dossier is from PMNV, when PMNV tra KET QUA, update to MCDT and send dossier KETQUA to DVCBO
                _log.info("KETQUA|Updating dossier with ref code: " + profile.getRef_code() + "...");
                this.doActionFrequency(groupId, serviceContext, dossier, profile, actionCode);
                if(isAutoNextAction && !profile.getFrom_unit_code().isEmpty() && !profile.getFrom_unit_code()
                        .equals(this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE))) {
                    //If enable auto next action and dossier is not from DVC bo, update dossier to DVC BO with
                    // api 3.12 and and profiles_status = 10
                    _log.info("-----Auto next action for ref code " + profile.getRef_code()
                            + " with action code " + actionCode);
                    this.syncDossierToDVCBoManual(token, dossier.getDossierId(), true);
                    _log.info("-----Done auto next action");
                }

                _log.info("KETQUA|Updated dossier with ref code: " + profile.getRef_code());
                return true;
            }

            _log.info("No status match for dossier referenceId " + profile.getRef_code() + ", status: " + status);
            return false;
        } catch (Exception e) {
            _log.error("Error when crawlDossierLGSP for refCode " + profile.getRef_code() + " | Error: " + e.getMessage());
            _log.error(e);
            return false;
        }
    }

    private void doActionFrequency(long groupId, ServiceContext serviceContext,
                                   Dossier dossier, ProfileInModel profile, String actionCode) throws Exception {
        try {
            FrequencyDoAction frequencyDoAction = CPSDossierBusinessLocalServiceUtil.updateDossierFrequencyAction(
                    groupId, serviceContext, dossier,
                    profile, actionCode);

            if(Validator.isNotNull(frequencyDoAction.getProcessAction())) {
                _log.info("Doing postAction with action code: " + actionCode);
                ProcessAction processAction = frequencyDoAction.getProcessAction();
                String postAction = processAction.getPostAction();
                if (Validator.isNotNull(postAction) && !postAction.isEmpty()) {
                    _log.info("---Post Action data: " + postAction);
                    String result = processPostAction(postAction, groupId, dossier);
                    _log.info("---Result post Action: " + result);
                }
                _log.info("Done postAction!!!");
            } else {
                _log.info("No process action with action code: " + actionCode);
            }

        } catch (Exception e) {
            _log.error(e);
            throw new Exception(e.getMessage());
        }
    }

    private String processPostAction(String postAction, long groupId, Dossier dossier) {
        try {
            JSONObject jsonPostData = JSONFactoryUtil.createJSONObject(postAction);
            if (jsonPostData != null) {
                JSONObject jsonCallAPI = JSONFactoryUtil.createJSONObject(jsonPostData.getString("CALL_API"));
                if (jsonCallAPI != null && jsonCallAPI.has(DossierTerm.SERVER_NO)) {
                    String serverNo = jsonCallAPI.getString(DossierTerm.SERVER_NO);
                    if (Validator.isNotNull(serverNo)) {
                        ServerConfig serverConfig = ServerConfigLocalServiceUtil.getByCode(groupId, serverNo);
                        if (serverConfig != null) {
                            JSONObject configObj = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
                            //
                            String method = StringPool.BLANK;
                            if (configObj != null && configObj.has(KeyPayTerm.METHOD)) {
                                method = configObj.getString(KeyPayTerm.METHOD);
                                System.out.println("method: " + method);
                            }
                            //params
                            JSONObject jsonParams = null;
                            if (configObj != null && configObj.has(KeyPayTerm.PARAMS)) {
                                jsonParams = JSONFactoryUtil
                                        .createJSONObject(configObj.getString(KeyPayTerm.PARAMS));
                            }
                            if (jsonParams != null) {
                                JSONObject jsonHeader = JSONFactoryUtil
                                        .createJSONObject(jsonParams.getString(KeyPayTerm.HEADER));
                                JSONObject jsonBody = JSONFactoryUtil
                                        .createJSONObject(jsonParams.getString(KeyPayTerm.BODY));

                                String authStrEnc = StringPool.BLANK;
                                String apiUrl = StringPool.BLANK;
                                StringBuilder sb = new StringBuilder();
                                try {
                                    URL urlVal = null;
                                    String groupIdRequest = StringPool.BLANK;
                                    StringBuilder postData = new StringBuilder();
                                    Iterator<?> keys = jsonBody.keys();
                                    while (keys.hasNext()) {
                                        String key = (String) keys.next();
                                        if (!StringPool.BLANK.equals(postData.toString())) {
                                            postData.append(StringPool.AMPERSAND);
                                        }
                                        postData.append(key);
                                        postData.append(StringPool.EQUAL);
                                        postData.append(jsonBody.get(key));
                                    }

                                    if (configObj.has(SyncServerTerm.SERVER_USERNAME)
                                            && configObj.has(SyncServerTerm.SERVER_SECRET)
                                            && Validator
                                            .isNotNull(configObj.getString(SyncServerTerm.SERVER_USERNAME))
                                            && Validator
                                            .isNotNull(configObj.getString(SyncServerTerm.SERVER_SECRET))) {
                                        authStrEnc = Base64.getEncoder()
                                                .encodeToString((configObj.getString(SyncServerTerm.SERVER_USERNAME)
                                                        + StringPool.COLON
                                                        + configObj.getString(SyncServerTerm.SERVER_SECRET))
                                                        .getBytes());
                                    }
                                    if (configObj.has(SyncServerTerm.SERVER_URL)) {
                                        apiUrl = configObj.getString(SyncServerTerm.SERVER_URL);
                                        if (apiUrl.contains("{_dossierId}")) {
                                            apiUrl = apiUrl.replace("{_dossierId}", String.valueOf(dossier.getDossierId()));
                                        }
                                        if (apiUrl.contains("{_dossierCounter}")) {
                                            apiUrl = apiUrl.replace("{_dossierCounter}",
                                                    String.valueOf(dossier.getDossierCounter()));
                                        }
                                        if (apiUrl.contains("{_dossierNo}")) {
                                            apiUrl = apiUrl.replace("{_dossierNo}",
                                                    String.valueOf(dossier.getDossierNo()));
                                        }
                                    }
                                    if (configObj.has(SyncServerTerm.SERVER_GROUP_ID)) {
                                        groupIdRequest = configObj.getString(SyncServerTerm.SERVER_GROUP_ID);
                                    }
                                    if (jsonHeader != null && Validator.isNotNull(groupIdRequest)) {
                                        if (jsonHeader.has(Field.GROUP_ID)) {
                                            groupIdRequest = String.valueOf(jsonHeader.getLong(Field.GROUP_ID));
                                        }
                                    }

                                    if (HttpMethods.GET.equals(method)) {
                                        if (Validator.isNotNull(postData.toString())) {
                                            urlVal = new URL(apiUrl + StringPool.QUESTION + postData.toString());
                                        } else {
                                            urlVal = new URL(apiUrl);
                                        }
                                    } else {
                                        urlVal = new URL(apiUrl);
                                    }
                                    _log.debug("API URL: " + apiUrl);
                                    java.net.HttpURLConnection conn = (java.net.HttpURLConnection) urlVal
                                            .openConnection();
                                    conn.setRequestProperty(Field.GROUP_ID, groupIdRequest);
                                    conn.setRequestMethod(method);
                                    conn.setRequestProperty(javax.ws.rs.core.HttpHeaders.ACCEPT, javax.ws.rs.core.MediaType.APPLICATION_JSON);
                                    if (Validator.isNotNull(authStrEnc)) {
                                        conn.setRequestProperty(javax.ws.rs.core.HttpHeaders.AUTHORIZATION, "Basic " + authStrEnc);
                                    }
                                    if (HttpMethods.POST.equals(method) || HttpMethods.PUT.equals(method)) {
                                        conn.setRequestProperty(javax.ws.rs.core.HttpHeaders.CONTENT_TYPE,
                                                javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED);
                                        conn.setRequestProperty(javax.ws.rs.core.HttpHeaders.CONTENT_LENGTH, StringPool.BLANK
                                                + Integer.toString(postData.toString().getBytes().length));

                                        conn.setUseCaches(false);
                                        conn.setDoInput(true);
                                        conn.setDoOutput(true);
                                        _log.debug("POST DATA: " + postData.toString());
                                        OutputStream os = conn.getOutputStream();
                                        os.write(postData.toString().getBytes());
                                        os.close();
                                    }

                                    BufferedReader brf = new BufferedReader(
                                            new InputStreamReader(conn.getInputStream()));

                                    int cp;
                                    while ((cp = brf.read()) != -1) {
                                        sb.append((char) cp);
                                    }
                                    _log.debug("RESULT PROXY: " + sb.toString());
                                    return sb.toString();
                                } catch (IOException e) {
                                    _log.debug(e);
                                    //_log.debug("Something went wrong while reading/writing in stream!!");
                                }
                            }
                        }
                    }
                }
            }
            return "";
        } catch (Exception e) {
            _log.error(e);
            _log.error("Error when process post action: " + e.getMessage());
            return "";
        }
    }

    private void addDossierFile(ProfileInModel profile, long groupId, ServiceContext serviceContext, Dossier dossier) throws Exception{
        try {
            String urlFile = "";
            String fileType = "";
            String displayName = "";
            String mimeType = "";
            List<ProfileAttachment> listFileAttachment = profile.getProfileAttachments();

            if(Validator.isNotNull(listFileAttachment) && listFileAttachment.size() > 0) {
                for(ProfileAttachment fileAttach: listFileAttachment) {
                    urlFile  = fileAttach.getAttachment_file_url();
                    fileType = fileAttach.getContent_type();

                    if(Validator.isNull(fileType) || fileType.isEmpty() || urlFile.isEmpty() || Validator.isNull(urlFile)) {
                        continue;
                    }

                    mimeType = UtilsFile.getMimeType(fileType);
                    if(mimeType.isEmpty()) {
                        continue;
                    }

                    displayName = Validator.isNotNull(fileAttach.getAttachment_name())
                            ? fileAttach.getAttachment_name() + "." + fileType
                            : System.currentTimeMillis() + "." + fileType;

                    InputStream inputStream = new URL(urlFile).openStream();
                    if(Validator.isNull(inputStream)) {
                        continue;
                    }

                    CPSDossierBusinessLocalServiceUtil.addDossierFileFrequency(groupId, serviceContext,
                            inputStream, "", dossier, displayName, mimeType,
                            "false", "", "", "");
                    //let a little time to handle input stream
                }
            }
        }catch (Exception e) {
            _log.error(e);
            throw new Exception(e.getMessage());
        }
    }

    public String getToken() throws Exception{
        try {
            String urlGetToken = this.configJson.getString(FrequencyOfficeConstants.CONFIG_URL)
                    + this.configJson.getString(FrequencyOfficeConstants.CONFIG_GET_TOKEN);
            String userName = this.configJson.getString(FrequencyOfficeConstants.CONFIG_USER_LGSP);
            String password = this.configJson.getString(FrequencyOfficeConstants.CONFIG_PASS_LGSP);
            String authStr = userName + ":" + password;
            String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("Authorization", "Basic " + base64Creds);

            JSONObject response = apiService.callApi(urlGetToken, headers, null);
            if(Validator.isNull(response)) {
                throw new Exception("Response get token null");
            }
            String token = response.getString("access_token");
            if(Validator.isNull(token)){
                throw new Exception("Token is empty");
            }
            return token;
        }catch (Exception e) {
            _log.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ProfileReceiver> getDossiers(String token) throws Exception {
        try{
            String urlGetDossiers = this.configJson.getString(FrequencyOfficeConstants.CONFIG_URL) +
                    this.configJson.get(FrequencyOfficeConstants.CONFIG_GET_LIST_DOSSIERS) + "?unit_code=" +
                    this.configJson.get(FrequencyOfficeConstants.CONFIG_UNIT_CODE);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + token);

            JSONObject response = apiService.get(urlGetDossiers, headers, null);

            if(Validator.isNull(response)) {
                throw new Exception("Response get list dossier null");
            }

            String profileReceivers = response.getString("profileReceivers");

            if(Validator.isNull(profileReceivers)) {
                throw new Exception("Response profileReceivers null");
            }
            objectMapper = new ObjectMapper();
            return Arrays.asList(objectMapper.readValue(profileReceivers, ProfileReceiver[].class));
        }catch (Exception e) {
            _log.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ProfileInModel getDetailDossier(String token, ProfileReceiver profileReceiver) throws Exception {
        try{
            Integer profileId = profileReceiver.getProfileId();
            String urlGetDetailDossier = this.configJson.getString(FrequencyOfficeConstants.CONFIG_URL) +
                    this.configJson.get(FrequencyOfficeConstants.CONFIG_GET_DETAIL_DOSSIERS) + "?profile_id=" + profileId;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + token);

            SyncTrackingInfo syncTrackingInfo = new SyncTrackingInfo();
            syncTrackingInfo.urlSaveTracking = this.configJson.getString(FrequencyOfficeConstants.CONFIG_URL_LOCAL)
                    + this.configJson.getString(FrequencyOfficeConstants.CONFIG_SAVE_SYNC_TRACKING);
            syncTrackingInfo.groupId = this.configJson.getLong(FrequencyOfficeConstants.CONFIG_GROUP_ID);
            syncTrackingInfo.api     = urlGetDetailDossier;
            syncTrackingInfo.fromUnit = Validator.isNotNull(profileReceiver.getFromUnitCode())
                    ? profileReceiver.getFromUnitCode() : "";
            syncTrackingInfo.toUnit   = new String[]{this.configJson.getString(
                    FrequencyOfficeConstants.CONFIG_FROM_UNIT_CODE)};
            syncTrackingInfo.groupId  = this.serverConfig.getGroupId();

            JSONObject response = apiService.get(urlGetDetailDossier, headers, syncTrackingInfo);
            if(Validator.isNull(response)) {
                throw new Exception("Response get one dossier null");
            }

            String profileDetail  = response.getString("profileOutmodel");
            if(Validator.isNull(profileDetail)) {
                throw new Exception("Profile in model is null");
            }

            objectMapper = new ObjectMapper();
            return objectMapper.readValue(profileDetail, ProfileInModel.class);
        }catch (Exception e) {
            _log.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void updateStatusReceiver(String token, Integer profileId, String status) throws Exception {
        //case 3.7
        try {
            String urlUpdateStatusReceiver = this.configJson.getString(FrequencyOfficeConstants.CONFIG_URL) +
                    this.configJson.get(FrequencyOfficeConstants.CONFIG_UPDATE_STATUS_RECEIVER) + "?profile_id=" +
                    profileId + "&status=" + status;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Accept", "*");
            headers.set("Authorization", "Bearer " + token);

            JSONObject response = apiService.callApi(urlUpdateStatusReceiver, headers, null);
            if(Validator.isNull(response)) {
                throw new Exception("Response update status Receiver null");
            }
            String statusUpdate = response.getString("status");
            _log.info("Response update status Receiver: " + statusUpdate);

        }catch (Exception e) {
            _log.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void syncDossierToPMNVManual(String token, ProfileInModel profile) throws Exception {
        try {
            _log.info("Syncing dossier to PMNV...");
            if(!profile.getStatus().equals(FrequencyOfficeConstants.STATUS_RECEIVE)) {
                throw new Exception("Profile status from DVCBO or MCDT is not TIEPNHAN and being: " + profile.getStatus());
            }

            long groupId = serverConfig.getGroupId();
            String urlSyncDossier = this.configJson.getString(FrequencyOfficeConstants.CONFIG_URL) +
                    this.configJson.get(FrequencyOfficeConstants.CONFIG_SYNC_DOSSIER);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + token);

            Dossier dossier = DossierLocalServiceUtil.getByRef(groupId, profile.getRef_code());
            if(Validator.isNull(dossier)) {
                throw new Exception("No dossier found with referenceId = " + profile.getRef_code());
            }

            if(Validator.isNull(dossier.getDossierNo()) || dossier.getDossierNo().isEmpty()) {
                throw new Exception("DossierNo is null with referenceId = " + profile.getRef_code());
            }

            profile.setSource_id(dossier.getDossierNo());
            profile.setType("profile");
            profile.setFrom_unit_code(this.configJson.getString(FrequencyOfficeConstants.CONFIG_FROM_UNIT_CODE));
            profile.setTo_unit_code(new String[]{
                    this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE_CUCTANSO)
            });
            SyncTrackingInfo syncTrackingInfo = this.transformDataToSyncTrackingInfo(profile, null,
                    dossier.getGroupId(), dossier.getServiceCode(), null, urlSyncDossier);

            JSONObject response = apiService.callApiAndTracking(urlSyncDossier, syncTrackingInfo, headers, profile);
            _log.info("Result sync dossier: " + response);
            _log.info("Sync dossier " + dossier.getReferenceUid() + " to PMNV done!!!");
        } catch (Exception e) {
            _log.error(e);
            _log.warn("Sync dossier to PMNV error: " + e.getMessage());
            _log.warn("Still running...");
        }
    }

    private SyncTrackingInfo transformDataToSyncTrackingInfo(ProfileInModel profile, Map<String, Object> map,
                                                             long groupId, String serviceCode, String[] listToUnitCode
            , String urlCallLgsp) {
        try {
            if(Validator.isNull(map) && Validator.isNull(profile)) {
                return null;
            }
            SyncTrackingInfo syncTrackingInfo = new SyncTrackingInfo();
            syncTrackingInfo.groupId = groupId;
            syncTrackingInfo.urlSaveTracking = this.configJson.getString(FrequencyOfficeConstants.CONFIG_URL_LOCAL)
                    + this.configJson.getString(FrequencyOfficeConstants.CONFIG_SAVE_SYNC_TRACKING);
            syncTrackingInfo.serviceCode = serviceCode;
            syncTrackingInfo.api = urlCallLgsp;

            if(Validator.isNotNull(profile)) {
                syncTrackingInfo.dossierNo = Validator.isNotNull(profile.getSource_id()) ? profile.getSource_id() : "";
                syncTrackingInfo.referenceUid = Validator.isNotNull(profile.getRef_code()) ? profile.getRef_code() : "";
                syncTrackingInfo.fromUnit = Validator.isNotNull(profile.getFrom_unit_code())
                        ? profile.getFrom_unit_code() : "";
                syncTrackingInfo.toUnit = Validator.isNotNull(profile.getTo_unit_code())
                        ? profile.getTo_unit_code() : new String[]{};
                return syncTrackingInfo;
            }

            syncTrackingInfo.dossierNo = Validator.isNotNull(map.get(FrequencyOfficeConstants.SOURCE_ID))
                    ? map.get(FrequencyOfficeConstants.SOURCE_ID).toString() : "";
            syncTrackingInfo.referenceUid = Validator.isNotNull(map.get(FrequencyOfficeConstants.REF_CODE))
                    ? map.get(FrequencyOfficeConstants.REF_CODE).toString() : "";
            syncTrackingInfo.fromUnit = Validator.isNotNull(map.get(FrequencyOfficeConstants.FROM_UNIT_CODE))
                    ? map.get(FrequencyOfficeConstants.FROM_UNIT_CODE).toString() : "";
            syncTrackingInfo.toUnit   = Validator.isNotNull(listToUnitCode) ? listToUnitCode: new String[]{};
            return syncTrackingInfo;
        } catch (Exception e) {
            _log.error(e);
            _log.warn("Error when transform data to sync tracking: " + e.getMessage());
            _log.warn("Still running...");
            return null;
        }
    }

    private ProfileInModel transformDossierToProfile(Dossier dossier, Integer statusProfile) throws Exception {
        try {
            //case 3.3, 3.5, 3.6, 3,7
            _log.info("---Transforming dossier " + dossier.getDossierNo() + " to profile...");
            ProfileInModel profile = new ProfileInModel();
            String proceduresCode = this.getProceduresCodeQGFromServiceCodeFDS(dossier.getServiceCode());
            if(proceduresCode.isEmpty()) {
                throw new Exception("No proceduresCode found with dossierNo: " + dossier.getDossierNo());
            }

            String creationDate = this.getCurrentDateStr(FORMAT_DATE_LGSP, dossier.getCreateDate());
            String acceptDate = this.getCurrentDateStr(FORMAT_DATE_LGSP, dossier.getReceiveDate());
            String appointmentDate = this.getCurrentDateStr(FORMAT_DATE_LGSP, dossier.getDueDate());

            if(creationDate.isEmpty()) {
                throw new Exception("Create date is empty with dossierNo: " + dossier.getDossierNo());
            }

            ProfileOwner profileOwner = new ProfileOwner();
            ProfileApplicant profileApplicant = new ProfileApplicant();
            profileOwner.setName(Validator.isNotNull(dossier.getApplicantName()) ? dossier.getApplicantName() : "");
            profileOwner.setAddress(Validator.isNotNull(dossier.getAddress()) ? dossier.getAddress() : "");
            profileOwner.setTel(Validator.isNotNull(dossier.getContactTelNo()) ? dossier.getContactTelNo() : "");
            profileApplicant.setName(Validator.isNotNull(dossier.getDelegateName()) ? dossier.getDelegateName() : "");
            profileApplicant.setAddress(Validator.isNotNull(dossier.getDelegateAddress()) ? dossier.getDelegateAddress() : "");
            profileApplicant.setEmail(Validator.isNotNull(dossier.getDelegateEmail()) ? dossier.getDelegateEmail() : "");
            profileApplicant.setTel(Validator.isNotNull(dossier.getDelegateTelNo()) ? dossier.getDelegateTelNo() : "");
            profileApplicant.setIdentify(Validator.isNotNull(dossier.getDelegateIdNo()) ? dossier.getDelegateIdNo() : "");
            List<ProfileAttachment> listProfileAttachments = this.getProfileAttachments(dossier);
            List<ProfileDocFee> listProfileDocFees = this.getListProfileDocFee(dossier.getGroupId(), dossier.getDossierId());

            profile.setStatus(FrequencyOfficeConstants.STATUS_RECEIVE);
            profile.setSource_id(dossier.getDossierNo());
            profile.setRef_code(dossier.getReferenceUid());
            profile.setProcedures_code(proceduresCode);
            profile.setType("profile");
            profile.setFrom_unit_code(this.configJson.getString(FrequencyOfficeConstants.CONFIG_FROM_UNIT_CODE));
            profile.setProfileOwner(profileOwner);
            profile.setProfileApplicant(profileApplicant);
            profile.setCreation_date(creationDate);
            profile.setAccept_date(acceptDate);
            profile.setAppointment_date(appointmentDate);
            if(Validator.isNotNull(listProfileAttachments) && listProfileAttachments.size() > 0) {
                profile.setProfileAttachments(listProfileAttachments);
            }

            if(Validator.isNotNull(listProfileDocFees) && listProfileDocFees.size() > 0) {
                profile.setProfileDocFees(listProfileDocFees);
            }

            _log.info("---Transformed dossier " + dossier.getDossierNo() + "!");
            return profile;
        } catch (Exception e) {
            _log.error(e);
            throw new Exception( "Create profile PMNV error: " + e.getMessage());
        }
    }

    @Override
    public void syncDossierAndStatusToLGSPManual(String token, long dossierId, String isSendMultipleUnit) throws Exception {
        try {
            //case 3.3, 3.5, 3.6, 3,7
            _log.info("Calling 3.3, 3.5, 3.6, 3.7...");
            Dossier dossier = DossierLocalServiceUtil.getDossier(dossierId);
            if(Validator.isNull(dossier)) {
                throw new Exception("No dossier found with id: " + dossierId);
            }

            if(Validator.isNull(dossier.getDossierNo())) {
                throw new Exception("No dossierNo found with id: " + dossierId);
            }

            if(Validator.isNull(dossier.getReferenceUid())) {
                throw new Exception("No ReferenceId found with id: " + dossierId);
            }

            JSONObject metaDataJson = null;
            if (Validator.isNotNull(dossier.getMetaData()) && !dossier.getMetaData().isEmpty()) {
                metaDataJson = JSONFactoryUtil.createJSONObject(dossier.getMetaData());
            }

            String fromUnitCode = "";
            if (Validator.isNotNull(metaDataJson)
                    && !metaDataJson.getString("fromUnitCode").isEmpty()) {
                fromUnitCode = metaDataJson.getString("fromUnitCode");
            }

            if(dossier.getOriginality() == FrequencyOfficeConstants.HOSO_TRUC_TIEP) {
                fromUnitCode = this.configJson.getString(FrequencyOfficeConstants.CONFIG_FROM_UNIT_CODE);
            }

            if(fromUnitCode.isEmpty()) {
                throw new Exception("Dossier online has no fromUnitCode with ref code: " + dossier.getReferenceUid());
            }

            Integer statusProfile = mappingDossierStatus(dossier.getDossierStatus());
            if(statusProfile.equals(0)) {
                throw new Exception("Status profile: 0");
            }

            //Ho so khong tu DVC BO
            if(!fromUnitCode.equals(this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE))) {
                _log.info("Ho so " + dossier.getDossierNo()  +" voi from_unit_code "
                        + fromUnitCode + " khong tu DVC BO..." );
                if(fromUnitCode.equals(this.configJson.getString(FrequencyOfficeConstants.CONFIG_FROM_UNIT_CODE))
                        && isSendMultipleUnit.equals("true")) {
                    //Checklist 5, case 1.5, Ho so tiep nhan tai MOTCUA, xu ly o PMNV
                    _log.info("Ho so " + dossier.getDossierNo() + " duoc gui truc tiep tai mot cua");
                    if(statusProfile.equals(FrequencyOfficeConstants.STATUS_LGSP_RECEIVED)) {
                        //Case ho so moi duoc tiep nhan tai mot cua va dong bo sang PMNV
                        ProfileInModel profilePMNV = this.transformDossierToProfile(dossier, statusProfile);
                        this.syncDossierToPMNVManual(token, profilePMNV);
                    }
                }

                _log.info("Switch to calling 3.12...");
                if(statusProfile.equals(FrequencyOfficeConstants.STATUS_LGSP_DONE)) {
                    _log.info("Ho so o trang thai cho tra ket qua...");
                    _log.info("Switch to calling 3.13...");
                    this.sendStatusProfileToDVCBo(token, dossierId);
                    return;
                }

                if(statusProfile.equals(FrequencyOfficeConstants.STATUS_LGSP_DENIED)) {
                    _log.info("Dossier has been reject, not sending to DVC Bo...");
                    return;
                }

                this.syncDossierToDVCBoManual(token, dossierId, true);

                return;
            }

            //Ho so from DVCBO
            ProfileInModel profile = new ProfileInModel();

            if(statusProfile.equals(FrequencyOfficeConstants.STATUS_LGSP_DENIED)) {
                profile.setStatus(FrequencyOfficeConstants.STATUS_DENIED);
            } else if(statusProfile.equals(FrequencyOfficeConstants.STATUS_LGSP_RETURNED_RESULT)) {
                profile.setStatus(FrequencyOfficeConstants.STATUS_RESULT);
            } else if(statusProfile.equals(FrequencyOfficeConstants.STATUS_LGSP_REQUIRE_PAPER)) {
                profile.setStatus(FrequencyOfficeConstants.STATUS_UPDATE);
            } else if(statusProfile.equals(FrequencyOfficeConstants.STATUS_LGSP_REQUIRE_MONEY)) {
                profile.setStatus(FrequencyOfficeConstants.STATUS_PAYMENT);
            } else {
                throw new Exception("Status profile is invalid with value: " + statusProfile);
            }

            profile.setSource_id(dossier.getDossierNo());
            profile.setRef_code(dossier.getReferenceUid());
            profile.setType("profile");
            profile.setFrom_unit_code(this.configJson.getString(FrequencyOfficeConstants.CONFIG_FROM_UNIT_CODE));
            profile.setTo_unit_code(new String[] {this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE)});
            profile.setTime_of_process(getCurrentDateStr(FORMAT_DATE_LGSP, new Date()));
            profile.setProcess_officials("Admin");

            List<ProfileAttachment> listProfileAttachments = this.getProfileAttachments(dossier);
            if(Validator.isNotNull(listProfileAttachments) && listProfileAttachments.size() > 0) {
                profile.setProfileAttachments(listProfileAttachments);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + token);

            String urlSyncDossier = this.configJson.getString(FrequencyOfficeConstants.CONFIG_URL);

            if(profile.getStatus().equals(FrequencyOfficeConstants.STATUS_PAYMENT)) {
                //case 3.7
                urlSyncDossier += this.configJson.getString(FrequencyOfficeConstants.CONFIG_PAYMENT);
                profile.setIs_request_payment(REQUEST_PAYMENT);
                PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByDossierId(dossier.getGroupId(), dossierId);

                if(Validator.isNull(paymentFile)) {
                    throw new Exception("No payment file for dossierId: " + dossierId);
                }
                profile.setTotal_amount(String.valueOf(paymentFile.getPaymentAmount()));
            } else {
                urlSyncDossier += this.configJson.getString(FrequencyOfficeConstants.CONFIG_SYNC_DOSSIER);
            }

            SyncTrackingInfo syncTrackingInfo = this.transformDataToSyncTrackingInfo(profile, null,
                    dossier.getGroupId(), dossier.getServiceCode(), null, urlSyncDossier);
            JSONObject response = apiService.callApiAndTracking(urlSyncDossier, syncTrackingInfo, headers, profile);
            _log.info("Result sync dossier manual: " + response);
            _log.info("Done call 3.3, 3.5, 3.6, 3.7");
        } catch (Exception e) {
            _log.error(e);
            throw new Exception("SyncDossierToLGSPManual error with message: " + e.getMessage());
        }
    }

    @Override
    public void syncDossierToDVCBoManual(String token, long dossierId, boolean isUpdate) throws Exception {
        try {
            _log.info("Calling 3.12 syncing dossier to DVC Bo after receive from PMNV...");
            Dossier dossier = DossierLocalServiceUtil.getDossier(dossierId);
            if(Validator.isNull(dossier)) {
                throw new Exception("No dossier found with id: " + dossierId);
            }

            if(Validator.isNull(dossier.getDossierNo())) {
                throw new Exception("No dossierNo found with id: " + dossierId);
            }

            if(Validator.isNull(dossier.getReferenceUid())) {
                throw new Exception("No ReferenceId found with id: " + dossierId);
            }

            Integer statusProfile = mappingDossierStatus(dossier.getDossierStatus());
            ProfileInModel profile = transformDossierToProfile(dossier, statusProfile);
            if(Validator.isNull(profile)) {
                throw new Exception("Profile after transform is null");
            }
            profile.setProfiles_status(String.valueOf(statusProfile));
            profile.setTo_unit_code(new String[] {this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE)});

            if(isUpdate) {
                _log.info("Is update");
                profile.setIs_update("true");
            } else {
                _log.info("Is create");
                profile.setIs_update("false");
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + token);

            String urlSyncDossier = this.configJson.getString(FrequencyOfficeConstants.CONFIG_URL) +
                    this.configJson.getString(FrequencyOfficeConstants.CONFIG_SYNC_DOSSIER_TO_DVC_BO);

            SyncTrackingInfo syncTrackingInfo = this.transformDataToSyncTrackingInfo(profile, null,
                    dossier.getGroupId(), dossier.getServiceCode(), null, urlSyncDossier);

            JSONObject response = apiService.callApiAndTracking(urlSyncDossier, syncTrackingInfo, headers, profile);
            _log.info("Result api 3.12 sync dossier DVC Bo manual: " + response);
        } catch (Exception e) {
            _log.error(e);
            throw new Exception(e.getMessage());
        }
    }

    private List<ProfileDocFee> getListProfileDocFee(long groupId, long dossierId) {
        try {
            List<ProfileDocFee> listDocFees = new ArrayList<>();
            ProfileDocFee docFee = new ProfileDocFee();
            PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByDossierId(groupId, dossierId);
            if(Validator.isNull(paymentFile)) {
                throw new Exception("No payment file with dossierId: " + dossierId);
            }

            docFee.setPrice(String.valueOf(paymentFile.getPaymentAmount()));
            docFee.setFee_name(paymentFile.getPaymentFee());
            listDocFees.add(docFee);
            return listDocFees;
        } catch (Exception e) {
            _log.error(e);
            _log.warn("Warning when get profile doc fee fail: " + e.getMessage());
            return null;
        }
    }

    private String getProceduresCodeQGFromServiceCodeFDS(String serviceCode) {
        try {
            JSONArray listServiceConfig = this.configJson.getJSONArray("serviceConfig");
            if(Validator.isNull(listServiceConfig) || listServiceConfig.length() == 0) {
                throw new Exception("No service config");
            }
            int length = listServiceConfig.length();
            JSONObject oneServiceConfig;

            for(int i = 0; i< length; i ++) {
                oneServiceConfig = listServiceConfig.getJSONObject(i);
                if(oneServiceConfig.getString("serviceCode").equals(serviceCode)) {
                    return oneServiceConfig.getString("procedures_code");
                }
            }

            return "";
        } catch (Exception e) {
            _log.error(e);
            _log.warn("Warning when get procedures code from service code " + serviceCode + " fail: " + e.getMessage());
            return "";
        }
    }

    @Override
    public void sendStatusProfile(String token, long dossierId, String isSendMultipleUnit) throws Exception {

        try {
            _log.info("Calling 3.11...");
            Dossier dossier = DossierLocalServiceUtil.getDossier(dossierId);
            if (Validator.isNull(dossier)) {
                throw new Exception("No dossier found with id: " + dossierId);
            }

            if (Validator.isNull(dossier.getDossierNo())) {
                throw new Exception("No dossierNo found with id: " + dossierId);
            }

            if (Validator.isNull(dossier.getReferenceUid())) {
                throw new Exception("No ReferenceId found with id: " + dossierId);
            }
            JSONObject metaDataJson = null;
            if (Validator.isNotNull(dossier.getMetaData()) && !dossier.getMetaData().isEmpty()) {
                metaDataJson = JSONFactoryUtil.createJSONObject(dossier.getMetaData());
            }

            Integer statusProfile = mappingDossierStatus(dossier.getDossierStatus());
            if (statusProfile.equals(0)) {
                throw new Exception("Status profile: 0");
            }

            String urlSyncDossier = this.configJson.getString(FrequencyOfficeConstants.CONFIG_URL) +
                    this.configJson.get(FrequencyOfficeConstants.CONFIG_SEND_STATUS_RECEIVER);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + token);

            String listToUnitCode[];
            Map<String, Object> body = new HashMap<>();
            body.put(FrequencyOfficeConstants.TYPE, "status");
            body.put(FrequencyOfficeConstants.SOURCE_ID, dossier.getDossierNo());
            body.put(FrequencyOfficeConstants.REF_CODE, dossier.getReferenceUid());
            body.put(FrequencyOfficeConstants.STATUS_PROFILE, String.valueOf(statusProfile));
            body.put(FrequencyOfficeConstants.PROCESS_OFFICIALS, "Admin");
            body.put(FrequencyOfficeConstants.TIME_OF_PROCESS, getCurrentDateStr(FORMAT_DATE_LGSP, new Date()));
            body.put(FrequencyOfficeConstants.FROM_UNIT_CODE, this.configJson.getString(FrequencyOfficeConstants.CONFIG_FROM_UNIT_CODE));
            String fromUnitCode = "";
            if (Validator.isNull(metaDataJson)
                    || metaDataJson.getString("fromUnitCode").isEmpty()) {
                body.put(FrequencyOfficeConstants.TO_UNIT_CODE, new String[]{
                        this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE),
                        this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE_CUCTANSO)
                });
                listToUnitCode = new String[]{
                        this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE),
                        this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE_CUCTANSO)
                };
            } else {
                //Neu co fromUnitCode thi ho so gui tu dau, se gui trang thai lai don vi do
                fromUnitCode = metaDataJson.getString("fromUnitCode");
                body.put(FrequencyOfficeConstants.TO_UNIT_CODE, new String[]{fromUnitCode});
                listToUnitCode = new String[]{
                        fromUnitCode
                };
            }

            if(dossier.getOriginality() == FrequencyOfficeConstants.HOSO_TRUC_TUYEN) {
                if (statusProfile.equals(FrequencyOfficeConstants.STATUS_LGSP_RECEIVED)) {
                    if (!fromUnitCode.isEmpty()
                            && !fromUnitCode.equals(this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE))) {
                        //Neu ho so ko nhan tu DVC Bo va ho so dang o trang thai new thi gui cho DVC Bo (Checklist 3, luong 1.7)
                        this.syncDossierToDVCBoManual(token, dossierId, false);
                    }
                }
                else if(statusProfile.equals(FrequencyOfficeConstants.STATUS_LGSP_PROCESSING)) {
                    //Neu trang thai sang dang xu ly, thi gui cho ca hai
                    _log.info("Updating processing");
                    if (fromUnitCode.equals(this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE))) {
                        //Neu ho so nhan tu DVC Bo thi gui cho ca hai
                        body.put(FrequencyOfficeConstants.TO_UNIT_CODE, new String[]{
                                this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE),
                                this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE_CUCTANSO)
                        });
                        listToUnitCode = new String[]{
                                this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE),
                                this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE_CUCTANSO)
                        };
                    }
                }
            } else if(dossier.getOriginality() == FrequencyOfficeConstants.HOSO_TRUC_TIEP) {
                if(Validator.isNull(isSendMultipleUnit)
                        || isSendMultipleUnit.isEmpty()
                        || isSendMultipleUnit.equals("false")) {
                    body.put(FrequencyOfficeConstants.TO_UNIT_CODE, new String[]{
                            this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE)
                    });
                    listToUnitCode = new String[]{
                            this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE)
                    };
                } else if (isSendMultipleUnit.equals("true")) {
                    body.put(FrequencyOfficeConstants.TO_UNIT_CODE, new String[]{
                            this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE),
                            this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE_CUCTANSO)
                    });
                    listToUnitCode = new String[]{
                            this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE),
                            this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE_CUCTANSO)
                    };
                }
            }

            SyncTrackingInfo syncTrackingInfo = this.transformDataToSyncTrackingInfo(null, body,
                    dossier.getGroupId(), dossier.getServiceCode(), listToUnitCode, urlSyncDossier);

            JSONObject response = apiService.callApiAndTrackingWithMapBody(urlSyncDossier, syncTrackingInfo, headers, body);
            _log.info("Result send dossier status: " + response);
            _log.info("Done 3.11!!!!");
        } catch (Exception e) {
            _log.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void sendStatusProfileToDVCBo(String token, long dossierId) throws Exception {
        try {
            _log.info("Calling 3.13 sending status profile to DVC BO...");
            Dossier dossier = DossierLocalServiceUtil.getDossier(dossierId);
            if(Validator.isNull(dossier)) {
                throw new Exception("No dossier found with id: " + dossierId);
            }

            if(Validator.isNull(dossier.getDossierNo())) {
                throw new Exception("No dossierNo found with id: " + dossierId);
            }

            if(Validator.isNull(dossier.getReferenceUid())) {
                throw new Exception("No ReferenceId found with id: " + dossierId);
            }

            Integer statusProfile = mappingDossierStatus(dossier.getDossierStatus());
            if(statusProfile.equals(0)) {
                throw new Exception("Status profile: 0");
            }

            String urlSyncDossier = this.configJson.getString(FrequencyOfficeConstants.CONFIG_URL) +
                    this.configJson.get(FrequencyOfficeConstants.CONFIG_SEND_STATUS_PROFILE_TO_DVC_BO);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + token);

            Map<String, Object> body = new HashMap<>();
            body.put(FrequencyOfficeConstants.TYPE, "status");
            body.put(FrequencyOfficeConstants.SOURCE_ID, dossier.getDossierNo());
            body.put(FrequencyOfficeConstants.REF_CODE, dossier.getReferenceUid());
            body.put(FrequencyOfficeConstants.STATUS_PROFILE, statusProfile);
            body.put(FrequencyOfficeConstants.PROCESS_OFFICIALS, "Admin");
            body.put(FrequencyOfficeConstants.TIME_OF_PROCESS, getCurrentDateStr(FORMAT_DATE_LGSP, new Date()));
            body.put(FrequencyOfficeConstants.FROM_UNIT_CODE, this.configJson.getString(FrequencyOfficeConstants.CONFIG_FROM_UNIT_CODE));
            body.put(FrequencyOfficeConstants.TO_UNIT_CODE, new String[] {
                    this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE)
            });

            String[] listToUnitCode = new String[] {
                    this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE)
            };

            SyncTrackingInfo syncTrackingInfo = this.transformDataToSyncTrackingInfo(null, body,
                    dossier.getGroupId(), dossier.getServiceCode(), listToUnitCode, urlSyncDossier);
            JSONObject response = apiService.callApiAndTrackingWithMapBody(urlSyncDossier, syncTrackingInfo, headers, body);
            _log.info("Result send dossier status: " + response);
            _log.info("Done call 3.13 sent status profile to DVC BO");
        } catch (Exception e) {
            _log.error(e);
            throw new Exception(e.getMessage());
        }
    }

    private Integer mappingDossierStatus(String dossierStatus) {
        //get action code
        try {
            JSONArray listDossierStatus = this.configJson.getJSONArray(FrequencyOfficeConstants.CONFIG_STATUS);
            int lengthListDossierStatus = listDossierStatus.length();
            JSONObject oneDossierStatus;
            for(int i = 0; i< lengthListDossierStatus; i ++) {
                oneDossierStatus = listDossierStatus.getJSONObject(i);
                if(oneDossierStatus.getString(FrequencyOfficeConstants.CONFIG_STATUS_MCDT).equals(dossierStatus)) {
                    return Integer.valueOf(oneDossierStatus.getString(FrequencyOfficeConstants.CONFIG_STATUS_LGSP));
                }
            }
            return 0;
        } catch (Exception e) {
            _log.error(e);
            _log.error("Error when mapping dossier status: " + e.getMessage());
            return 0;
        }
    }

    private List<ProfileAttachment> getProfileAttachments(Dossier dossier) {
        try {
            List<ProfileAttachment> listAttachments = new ArrayList<>();
            FileEntry fileEntry;
            long dossierId = dossier.getDossierId();
            DossierAction lastAction = DossierActionLocalServiceUtil.getByNextActionId(dossierId, 0);
            if(Validator.isNull(lastAction)) {
                throw new Exception("No last action with dossierId: " + dossierId);
            }

            List<DossierDocument> listDossierDocuments =
                    DossierDocumentLocalServiceUtil.getByDID_DAIDList(dossier.getGroupId(), dossierId, lastAction.getDossierActionId());

            //Case file in dossier document
            if(Validator.isNotNull(listDossierDocuments) && listDossierDocuments.size() > 0) {
                for(DossierDocument oneDocument : listDossierDocuments) {
                    if(Validator.isNotNull(oneDocument.getDocumentFileId()) && oneDocument.getDocumentFileId() > 0) {
                        ProfileAttachment profileAttachment = new ProfileAttachment();
                        fileEntry = DLAppLocalServiceUtil.getFileEntry(
                                oneDocument.getDocumentFileId());
                        if(Validator.isNotNull(fileEntry)) {
                            profileAttachment.setContent_type(fileEntry.getExtension());
                            profileAttachment.setAttachment_name(fileEntry.getFileName());
                            profileAttachment.setIs_verified(0);
                            profileAttachment.setDescription("");
                            profileAttachment.setIs_deleted(0);
                            profileAttachment.setAttachment_file_url(PropsUtil.get(PropKeys.PORTAL_DOMAIN) +
                                    "/c/document_library/get_file?uuid=" + fileEntry.getUuid() + "&groupId=" + fileEntry.getGroupId());
//                            profileAttachment.setContent_type("docx");
//                            profileAttachment.setAttachment_file_url("https://dichvucong3.mic.gov.vn/documents/10180/1432020/2174501.docx?version=1.0");
                            listAttachments.add(profileAttachment);
                        }
                    }
                }

                if(Validator.isNotNull(listAttachments) && listAttachments.size()>0) {
                    return listAttachments;
                }
            }

            //Case file in dossier file
            if(Validator.isNull(lastAction.getServiceProcessId())
                    || Validator.isNull(lastAction.getActionCode())
                    || Validator.isNull(lastAction.getStepCode())
                    || Validator.isNull(lastAction.getFromStepCode())) {
                throw new Exception("Last action is invalid with dossierActionId" + lastAction.getDossierActionId());
            }

            ProcessAction processAction = ProcessActionLocalServiceUtil.fetchByF_GID_SID_AC_PRE_POST(lastAction.getGroupId(),
                    lastAction.getServiceProcessId(), lastAction.getActionCode(), lastAction.getFromStepCode(), lastAction.getStepCode());

            if(Validator.isNull(processAction)) {
                throw new Exception("No process action with serviceProcessId: " + lastAction.getServiceProcessId() +", actionCode: " +
                        lastAction.getActionCode() + ", fromStepCode: " + lastAction.getFromStepCode() + ", stepCode: " + lastAction.getStepCode());
            }

            if(Validator.isNull(processAction.getCreateDossierFiles()) || processAction.getCreateDossierFiles().isEmpty()) {
                throw new Exception("No create dossier file in process action with serviceProcessId: " + lastAction.getServiceProcessId() +", actionCode: " +
                        lastAction.getActionCode() + ", fromStepCode: " + lastAction.getFromStepCode() + ", stepCode: " + lastAction.getStepCode());
            }

            List<DossierFile> listDossierFiles = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO(dossierId,
                    processAction.getCreateDossierFiles(), false);

            if(Validator.isNull(listDossierFiles) || listDossierFiles.isEmpty()) {
                throw new Exception("No dossier file with dossierId: " + dossierId + ", fileTemplateNo: " + processAction.getCreateDossierFiles());
            }

            for(DossierFile oneDossierFile : listDossierFiles) {
                if(Validator.isNotNull(oneDossierFile.getFileEntryId()) && oneDossierFile.getFileEntryId() > 0) {
                    ProfileAttachment profileAttachment = new ProfileAttachment();
                    fileEntry = DLAppLocalServiceUtil.getFileEntry(
                            oneDossierFile.getFileEntryId());
                    if(Validator.isNotNull(fileEntry)) {
                        profileAttachment.setContent_type(fileEntry.getExtension());
                        profileAttachment.setAttachment_name(fileEntry.getFileName());
                        profileAttachment.setIs_verified(0);
                        profileAttachment.setDescription("");
                        profileAttachment.setIs_deleted(0);
                        profileAttachment.setAttachment_file_url(PropsUtil.get(PropKeys.PORTAL_DOMAIN) +
                                "/c/document_library/get_file?uuid=" + fileEntry.getUuid() + "&groupId=" + fileEntry.getGroupId());
                        listAttachments.add(profileAttachment);
                    }
                }
            }
            return listAttachments;
        } catch (Exception e) {
            _log.error(e);
            _log.warn("Warning when get list attachments fail: " + e.getMessage());
            _log.warn("Still running...");
            return null;
        }
    }

    private String getCurrentDateStr(String format, Date date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            return formatter.format(date);
        } catch (Exception e) {
            _log.error(e);
            _log.warn("Warning when get format date: " + e.getMessage());
            return "";
        }
    }

    private String getDateStrFromDateStr(String currentFormat, String newFormat, String strDate) {
        try {
            SimpleDateFormat formatCurrent = new SimpleDateFormat(currentFormat);
            SimpleDateFormat formatNew     = new SimpleDateFormat(newFormat);
            String reformattedStr = formatNew.format(formatCurrent.parse(strDate));
            return reformattedStr;
        } catch (Exception e) {
            _log.error(e);
            _log.error("Error when parse string date: " + e.getMessage());
            return "";
        }
    }
}
