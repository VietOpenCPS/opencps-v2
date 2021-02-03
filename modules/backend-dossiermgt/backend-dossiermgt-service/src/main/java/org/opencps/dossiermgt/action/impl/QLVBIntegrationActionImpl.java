package org.opencps.dossiermgt.action.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inet.xml.status.Status;
import com.inet.xml.status.header.MessageHeader;
import com.inet.xml.status.parser.StatusXmlParser;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.Validator;
import com.vpcp.example.ServiceTest;
import com.vpcp.services.AgencyServiceImp;
import com.vpcp.services.KnobstickServiceImp;
import com.vpcp.services.VnptProperties;
import com.vpcp.services.model.*;
import org.opencps.backend.dossiermgt.serviceapi.ApiThirdPartyService;
import org.opencps.backend.dossiermgt.serviceapi.ApiThirdPartyServiceImpl;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.QLVBIntegrationAction;
import org.opencps.dossiermgt.action.util.DossierFileUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.KeyPayTerm;
import org.opencps.dossiermgt.constants.QLVBConstants;
import org.opencps.dossiermgt.input.model.FileVBModel;
import org.opencps.dossiermgt.input.model.FrequencyDoAction;
import org.opencps.dossiermgt.input.model.QLVBModel;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.rest.utils.SyncServerTerm;
import org.opencps.dossiermgt.service.CPSDossierBusinessLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.*;
import java.net.InetAddress;
import java.net.URL;
import java.util.*;

public class QLVBIntegrationActionImpl implements QLVBIntegrationAction {
    private JSONObject configJson;
    private ApiThirdPartyService apiService;
    private Log _log = LogFactoryUtil.getLog(QLVBIntegrationActionImpl.class);
    private static final String SERVER_CONFIG_NULL = "There is no server config frequency";
    private static final String PARSE_CONFIG_JSON_FAIL= "Create object json from config error";
    private ObjectMapper objectMapper;
    private ServerConfig serverConfig;
    private String base64LgspToken;

    public QLVBIntegrationActionImpl(ServerConfig serverConfig) throws Exception{
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
        try {
            String  lgspAccessToken = "{\"AccessKey\":\"" + this.configJson.getString(QLVBConstants.CONFIG_ACCESS_KEY)
                    + "\",\"SecretKey\":\"" + this.configJson.getString(QLVBConstants.CONFIG_SECRET_KEY) +
                    "\",\"AppName\":\"" + this.configJson.getString(QLVBConstants.CONFIG_APP_NAME)
                    + "\",\"PartnerCode\":\"" + this.configJson.getString(QLVBConstants.CONFIG_PARTNER_CODE)
                    + "\",\"PartnerCodeCus\":\"" + this.configJson.getString(QLVBConstants.CONFIG_PARTNER_CODE_CUS) + "\"}";
            this.base64LgspToken = Base64.getEncoder().encodeToString(lgspAccessToken.getBytes());
        } catch (Exception e) {
            this.base64LgspToken = "";
        }

    }

    @Override
    public String getTokenHG() throws Exception {
        try {
            String urlGetToken = this.configJson.getString(QLVBConstants.CONFIG_URL)
                    + this.configJson.getString(QLVBConstants.CONFIG_GET_TOKEN)
                    + this.configJson.getString(QLVBConstants.CONFIG_USER_ID);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("lgspaccesstoken", this.base64LgspToken);
            headers.add("X-Authentication-Token", this.configJson.getString(QLVBConstants.CONFIG_X_AUTHENTICATION_TOKEN));

            JSONObject result = this.apiService.get(urlGetToken, headers, null);

            if(Validator.isNotNull(result.getJSONObject("data").getString("token"))) {
                return result.getJSONObject("data").getString("token");
            }

            throw new Exception("No token found");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    private List<FileVBModel> getListVB(long dossierId) {
        try {
            List<FileVBModel> listFileSend = new ArrayList<>();
            List<DossierFile> listDossierFile = DossierFileLocalServiceUtil.getAllDossierFile(dossierId);
            FileEntry fileEntry;
            File file;
            String fileBase64;

            for(DossierFile dossierFile: listDossierFile) {
                try {
                    if(Validator.isNull(dossierFile.getFileEntryId()) || dossierFile.getDossierFileId() == 0) {
                        throw new Exception("No dossier file or dossierFile is = 0 with dossierFileId " + dossierFile.getDossierFileId());
                    }
                    fileEntry = DLAppLocalServiceUtil.getFileEntry(dossierFile.getFileEntryId());
                    if(Validator.isNull(fileEntry)) {
                        throw new Exception("No fileEntry with dossierFileId " + dossierFile.getDossierFileId());
                    }
                    file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(),
                            true);
                    if(Validator.isNull(file)) {
                        throw new Exception("No file with fileEntry " + fileEntry.getFileEntryId());
                    }

                    fileBase64 = DossierFileUtils.fileToBase64(file);
                    if(Validator.isNull(fileBase64) || fileBase64.isEmpty()) {
                        throw new Exception("No fileBase64 with fileEntry " + fileEntry.getFileEntryId());
                    }

                    FileVBModel fileVBHG = new FileVBModel();
                    fileVBHG.size     = Validator.isNotNull(fileEntry.getSize()) ? fileEntry.getSize() : 0;
                    fileVBHG.fileName = Validator.isNotNull(fileEntry.getFileName()) ? fileEntry.getFileName(): "";
                    fileVBHG.fileByte = Validator.isNotNull(fileBase64) ? fileBase64 : "";
                    listFileSend.add(fileVBHG);
                } catch (Exception e) {
                    _log.warn("Error when create file base 64 with dossierFile "
                            + dossierFile.getDossierFileId() + ": " + e.getMessage());
                    _log.warn("Still running...");
                }
            }

            return listFileSend;
        } catch (Exception e) {
            _log.warn("Get list VBHG error with message: " + e.getMessage());
            _log.warn("Still running...");
        }
        return null;
    }

    @Override
    public boolean sendVBHG(String token, long dossierId) throws Exception {
        try {
            Dossier dossier = DossierLocalServiceUtil.getDossier(dossierId);
            if(Validator.isNull(dossier)) {
                throw new Exception("Not found dossier with dossierId: " + dossierId);
            }

            if(Validator.isNull(token) || token.isEmpty()) {
                throw new Exception("Not found token with dossierId: " + dossierId);
            }
            String urlSendVB = this.configJson.getString(QLVBConstants.CONFIG_URL)
                    + this.configJson.getString(QLVBConstants.CONFIG_SEND_VB);

            String trichYeu = dossier.getDossierName();
            List<FileVBModel> listVBHG = this.getListVB(dossierId);
            QLVBModel qlvbhgModel = new QLVBModel();
            qlvbhgModel.trichYeu = trichYeu;
            qlvbhgModel.maHoSo = Validator.isNotNull(dossier.getDossierNo()) ? dossier.getDossierNo() : "";
            qlvbhgModel.files = listVBHG;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("lgspaccesstoken", this.base64LgspToken);
            headers.add("X-Authentication-Token", token);

            this.apiService.callApiAndTracking(urlSendVB, null, headers, qlvbhgModel);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean sendDocTTTT(String token, long dossierId) throws Exception {
        try {
            Dossier dossier = DossierLocalServiceUtil.getDossier(dossierId);
            if(Validator.isNull(dossier)) {
                throw new Exception("Not found dossier with dossierId: " + dossierId);
            }

            if(Validator.isNull(token) || token.isEmpty()) {
                throw new Exception("Not found token with dossierId: " + dossierId);
            }

            String urlSendDoc = this.configJson.getString(QLVBConstants.CONFIG_URL)
                    + this.configJson.getString(QLVBConstants.CONFIG_SEND_VB);

            List<FileVBModel> listDoc = this.getListVB(dossierId);
            QLVBModel docTTTT = new QLVBModel();
            docTTTT.tenHoSo = Validator.isNotNull(dossier.getDossierName())
                    ? dossier.getDossierName() : "NAME_DOSSIER_NOT_DEFINE";
            docTTTT.documentCode = Validator.isNotNull(dossier.getDossierNo())
                    ? dossier.getDossierNo() : "CODE_DOSSIER_NOT_DEFINE";
            docTTTT.files = listDoc;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            headers.set("Authorization", "Bearer " + token);

            this.apiService.callApiAndTracking(urlSendDoc, null, headers, docTTTT);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String getTokenTTTT() throws Exception {
        try {
            String basicAuthBeforeHash = this.configJson.getString(QLVBConstants.CONFIG_CONSUMER_KEY) + ":" +
                    this.configJson.getString(QLVBConstants.CONFIG_CONSUMER_SECRET);
            String basicAuthBase64 = Base64.getEncoder().encodeToString(basicAuthBeforeHash.getBytes());

            String urlGetToken = this.configJson.getString(QLVBConstants.CONFIG_URL)
                    + this.configJson.getString(QLVBConstants.CONFIG_GET_TOKEN);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.set("Authorization", "Basic " + basicAuthBase64);

            JSONObject result = this.apiService.callApi(urlGetToken, headers, null);
            if(Validator.isNotNull(result.getString("access_token"))
                    && !result.getString("access_token").isEmpty()) {
                return result.getString("access_token");
            }
            throw new Exception("No token found");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void getDocEOfficeTTTT() throws Exception {
        try {
            String endpoint = this.configJson.getString(QLVBConstants.CONFIG_URL);
            String systemId = this.configJson.getString(QLVBConstants.CONFIG_SYSTEM_ID_RECEIVE_DOC);
            String secret   = this.configJson.getString(QLVBConstants.CONFIG_SECRET_KEY_RECEIVE_DOC);
            String fileSave = "receive";
            File exportDir = new File(fileSave);
            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }

            String storePathDir = exportDir.getAbsolutePath().replace('\\', '/');

            int maxConnection = 10, retry = 3;
            VnptProperties vnptProperties = new VnptProperties(endpoint, systemId , secret, storePathDir, maxConnection,
                    retry, storePathDir, 0);

            KnobstickServiceImp knobstickService = new KnobstickServiceImp(vnptProperties);
            GetReceivedEdocResult resultGetListDocument = null;

            try {
                 resultGetListDocument = knobstickService.getReceivedEdocList(this.createHeaderJson("eDoc", "status"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(Validator.isNull(resultGetListDocument)) {
                throw new Exception("List VB from Eoffice is null");
            }

            if(resultGetListDocument.getKnobsticks().size() == 0) {
                throw new Exception("No Knobsticks in result");
            }

            String pstart = "---------";
            _log.info(pstart+"status:"+resultGetListDocument.getStatus());
            _log.info(pstart+"Desc:"+resultGetListDocument.getErrorDesc());
            _log.info(pstart+"Size:"+resultGetListDocument.getKnobsticks().size());

            for(Knobstick item : resultGetListDocument.getKnobsticks()){
                try{
                    _log.info("docId: "+item.getId());
                    String json2 = createHeaderJsonGetDoc(storePathDir, item.getId());
                    GetEdocResult getEdocResult = knobstickService.getEdoc(json2);
                    if(Validator.isNull(getEdocResult)) {
                        _log.info("EDoc result is null with doc id: " + item.getId());
                        continue;
                    }
                    //prase du lieu tu edxml
                    File file = new File(getEdocResult.getFilePath());

                    InputStream inputStream = new FileInputStream(file);
                    // goi tin status
                    Status ed =  StatusXmlParser.parse(inputStream);
                    if(Validator.isNull(ed)) {
                        _log.info("Status doc is null with doc id " + item.getId());
                        continue;
                    }

                    MessageHeader messageHeader = (MessageHeader) ed.getHeader().getMessageHeader();
                    if(Validator.isNull(messageHeader)) {
                        _log.info("Message Header is null with doc id " + item.getId());
                        continue;
                    }

                    String dossierNo  = messageHeader.getResponseFor().getCode();
                    String statusCode = messageHeader.getStatusCode();

                    if(Validator.isNull(statusCode)) {
                        _log.info("statusCode is null with doc id " + item.getId());
                        continue;
                    }
                    final String STATUS_SUCCESS = "06";

                    if(!statusCode.equals(STATUS_SUCCESS)) {
                        _log.info("statusCode is error "+ statusCode + " with doc id " + item.getId());
                        continue;
                    }

                    Dossier dossier = DossierLocalServiceUtil.getByDossierNo(serverConfig.getGroupId(), dossierNo);
                    if(Validator.isNull(dossier)) {
                        _log.info("Dossier is null when get with groupId " + serverConfig.getGroupId() + " and " +
                                "dossierNo: " + dossierNo);
                        continue;
                    }

                    ServiceContext serviceContext = new ServiceContext();
                    serviceContext.setUserId(serverConfig.getUserId());
                    serviceContext.setCompanyId(serverConfig.getCompanyId());

                    String actionCode = this.configJson.getString(QLVBConstants.CONFIG_ACTION_CODE);
                    if(Validator.isNull(actionCode)) {
                        _log.info("ActionCode is null");
                        continue;
                    }

                    this.doAction(serverConfig.getGroupId(), serviceContext,
                            dossier, this.configJson.getString(QLVBConstants.CONFIG_ACTION_CODE));

                    GetChangeStatusResult result;

                    if (getEdocResult.getStatus().equals("OK")){
                        String json = createHeaderJsonUpdate(item.getId(),"done") ;
                        result = knobstickService.updateStatus(json);
                        if(result.getStatus().equals("OK")){
                            _log.info("Update dossier status done with " + dossierNo
                                    + " and item id " + item.getId() + " succeed");
                        } else {
                            _log.info("Update dossier status done with " + dossierNo
                                    + " and item id " + item.getId() + " error: " + result.getErrorCode());
                        }
                    }
                    else{
                        String json = createHeaderJsonUpdate(item.getId(),"fail") ;
                        result = knobstickService.updateStatus(json);
                        if(!result.getStatus().equals("OK")){
                            _log.info("Update dossier status fail with " + dossierNo
                                    + " and item id " + item.getId() + " succeed");
                        } else {
                            _log.info("Update dossier status fail with " + dossierNo
                                    + " and item id " + item.getId() + " error: " + result.getErrorCode());
                        }
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                    _log.warn("Error when save itemId: " + item.getId());
                    _log.warn("Still running...");
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean sendDocEOfficeTTTT() throws Exception {
        try {
            String endpoint = "http://ltvbtest.mic.gov.vn";
            String systemId = "000.00.00.G14";
            String secret = "A7ZVWDPOyaVmf7ayG24g88wsGlX1Uu/zP33w1NaxUDnE";
            String fileSave = "D:/fileXml/send";
            String edXMLFileLocation = "D:/fileXml/send/dossier_send.edxml";
            String time = Calendar.getInstance().getTime().getTime() + "";
//            String storePathDir = httpString + InetAddress.getLocalHost().getHostAddress() + "/imported";
//            String logError = httpString + InetAddress.getLocalHost().getHostAddress() + "/imported";
            String storePathDir = fileSave;
            String logError = fileSave;

            int maxConnection = 10, retry = 3;
            VnptProperties vnptProperties = new VnptProperties(endpoint, systemId , secret, storePathDir, maxConnection,
                    retry, logError, 1);
            AgencyServiceImp agencyService = new AgencyServiceImp(vnptProperties);
            KnobstickServiceImp knobstickService = new KnobstickServiceImp(vnptProperties);
            ServiceTest serviceTest = new ServiceTest();
            SendEdocResult sendEdocResult = null;
            try {
                sendEdocResult = knobstickService.sendEdoc(serviceTest.getJsonHeader(), edXMLFileLocation);

                if (sendEdocResult != null) {
                    _log.info("status:" + sendEdocResult.getStatus());
                    _log.info("Desc:" + sendEdocResult.getErrorDesc());
                    _log.info("DocID:" + sendEdocResult.getDocID());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return false;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void doAction(long groupId, ServiceContext serviceContext, Dossier dossier, String actionCode){
        try {
            FrequencyDoAction frequencyDoAction = CPSDossierBusinessLocalServiceUtil.updateDossierFrequencyAction(
                    groupId, serviceContext, dossier,
                    null, actionCode);

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
            }
            _log.info("No process action with action code: " + actionCode);
        } catch (Exception e) {
            _log.warn("Error when do action with dossierNo: "+ dossier.getDossierNo()
                    + "|error message: "+ e.getMessage());
            _log.warn("Still running...");
        }
    }

    private String createHeaderJson(String serviceType, String messageType) {
        return "{" +
                "\"servicetype\":\"" + serviceType + "\"" +
                ",\"messagetype\":\"" + messageType + "\"" +
                "}";
    }

    private static String createHeaderJsonGetDoc(String filePath, String docId){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");
        stringBuffer.append("\"filePath\":\""+filePath+"\"");
        stringBuffer.append(",\"docId\":\""+ docId +"\"");
        stringBuffer.append("}");
        String json = stringBuffer.toString();
        return json;
    }

    private static String createHeaderJsonUpdate(String docId, String status){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");
        stringBuffer.append("\"status\":\""+ status +"\"");
        stringBuffer.append(",\"docId\":"+ docId );
        stringBuffer.append("}");
        String	json = stringBuffer.toString();
        return json;
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
                                _log.info("method: " + method);
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
            _log.info("Error when process post action: " + e.getMessage());
            return "";
        }
    }
}
