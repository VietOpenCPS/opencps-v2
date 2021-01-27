package org.opencps.api.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.controller.QLVGManagement;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.QLVBIntegrationAction;
import org.opencps.dossiermgt.action.impl.QLVBIntegrationActionImpl;
import org.opencps.dossiermgt.action.util.DossierFileUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.FrequencyOfficeConstants;
import org.opencps.dossiermgt.constants.KeyPayTerm;
import org.opencps.dossiermgt.constants.QLVBConstants;
import org.opencps.dossiermgt.input.model.FrequencyDoAction;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.rest.utils.SyncServerTerm;
import org.opencps.dossiermgt.service.CPSDossierBusinessLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class QLVGManagementImpl implements QLVGManagement {
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final Log _log = LogFactoryUtil.getLog(QLVGManagementImpl.class);
    private static final String API_SYNC_QLVB = "API_SYNC_QLVB";
    private static final String[] LIST_ACTION_CODE = {"2100"};
    public static final String MIME_DOC  = "application/msword";
    public static final String MIME_DOCX  = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    public static final String MIME_CSV  = "text/csv";
    public static final String MIME_JPG  = "image/jpeg";
    public static final String MIME_PNG  = "image/png";
    public static final String MIME_PDF  = "application/pdf";
    public static final String MIME_XLS  = "application/vnd.ms-excel";
    public static final String MIME_XLSX  = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static final double MAX_FILE_SIZE = 10;
    @Override
    public Response sendProfile(long dossierId) {
        try {
            List<ServerConfig> listConfig = ServerConfigLocalServiceUtil.getByServerAndProtocol(API_SYNC_QLVB, API_SYNC_QLVB);
            if(Validator.isNull(listConfig) || listConfig.isEmpty()) {
                throw new Exception("No config was found with protocol: " + API_SYNC_QLVB);
            }
            ServerConfig serverConfig = listConfig.get(0);
            QLVBIntegrationAction qlvbAction = new QLVBIntegrationActionImpl(serverConfig);
            String token = qlvbAction.getTokenHG();

            qlvbAction.sendVBHG(token, dossierId);
            return Response.status(HttpURLConnection.HTTP_OK).entity(null).build();
        } catch (Exception e) {
            _log.error("Error when sync dossier: " + e.getMessage());
            return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(null).build();
        }
    }

    @Override
    public Response updateProfile(HttpServletRequest request, HttpHeaders header,
                                  Company company, Locale locale, User user,
                                  ServiceContext serviceContext, String id, File file,
                                  String displayName, String fileType,
                                  String actionCode) {
        _log.info("Eoffice system calling api FDS...");
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        try {
            BackendAuth auth = new BackendAuthImpl();
            if (!auth.isAuth(serviceContext)) {
                jsonObject.put("message", "Wrong credentials");
                jsonObject.put("code", "16");
                return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(jsonObject.toJSONString()).build();
            }

            if(Validator.isNull(id)
                    || Validator.isNull(file)
                    || Validator.isNull(displayName)
                    || Validator.isNull(fileType)
                    || Validator.isNull(actionCode)
                    || id.isEmpty()
                    || displayName.isEmpty()
                    || fileType.isEmpty()
                    || actionCode.isEmpty()
            ) {
                jsonObject.put("message", "Data is invalid");
                jsonObject.put("code", "01");
                return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(jsonObject.toJSONString()).build();
            }

            if(!this.mimeTypeValid(fileType)) {
                jsonObject.put("message", "File type is invalid");
                jsonObject.put("code", "01");
                return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(jsonObject.toJSONString()).build();
            }

            if(!actionCode.equals(LIST_ACTION_CODE[0])) {
                jsonObject.put("message", "Action code is invalid");
                jsonObject.put("code", "01");
                return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(jsonObject.toJSONString()).build();
            }

            double fileSize = (double)file.length() / (1024*1024);
            if(fileSize > MAX_FILE_SIZE) {
                jsonObject.put("message", "File size must < 10M");
                jsonObject.put("code", "01");
                return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(jsonObject.toJSONString()).build();
            }

            List<ServerConfig> listConfig = ServerConfigLocalServiceUtil.getByServerAndProtocol(API_SYNC_QLVB, API_SYNC_QLVB);
            if(Validator.isNull(listConfig)
                    || listConfig.isEmpty()
                    || Validator.isNull(listConfig.get(0))
                    || Validator.isNull(listConfig.get(0).getConfigs())
                    || listConfig.get(0).getConfigs().isEmpty()) {
                throw new Exception("No server config was found with protocol and server no = " +
                        API_SYNC_QLVB + " and id: " + id);
            }

            ServerConfig serverConfig = listConfig.get(0);
            long groupId = serverConfig.getGroupId();
            JSONObject configJson = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
            Dossier dossier = this.getDossierById(groupId, id);
            if(Validator.isNull(dossier)) {
                throw new Exception("No dossier was found with groupId: " +  groupId + " and id: " + id);
            }

            //Import file
            InputStream inputStream = DossierFileUtils.fileToInputStream(file);
            String dossierPartNo = configJson.getString(QLVBConstants.CONFIG_DOSSIER_PART_NO);

            CPSDossierBusinessLocalServiceUtil.addDossierFileFrequency(groupId, serviceContext,
                    inputStream, "", dossier, displayName, fileType, "false", dossierPartNo,
                    "", "" );

            //Do action
            this.doAction(groupId, serviceContext, dossier, actionCode);

            jsonObject.put("message", "Success");
            jsonObject.put("code", "00");

            _log.info("Eoffice system saved file to FDS!");
            return Response.status(HttpURLConnection.HTTP_OK).entity(jsonObject.toJSONString()).build();
        } catch (Exception e) {
            jsonObject.put("message", "Server internal error");
            jsonObject.put("code", "05");
            _log.error("Error when update profile from Eoffice with id " + id + ": " + e.getMessage());
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(jsonObject.toJSONString()).build();
        }
    }

    private void doAction(long groupId, ServiceContext serviceContext, Dossier dossier, String actionCode) throws Exception{
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
            _log.warn("Error when do action: " + e.getMessage());
            _log.warn("Still running...");
        }
    }

    private boolean mimeTypeValid(String mimeType) {
        try {
            switch (mimeType) {
                case MIME_DOC :
                case MIME_DOCX :
                case MIME_CSV :
                case MIME_JPG :
                case MIME_PNG :
                case MIME_PDF :
                case MIME_XLS :
                case MIME_XLSX :
                    return true;
                default:
                    return false;
            }
        } catch (Exception e) {
            _log.error("Error when check mime type is valid " + e.getMessage());
            return false;
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
            _log.info("Error when process post action: " + e.getMessage());
            return "";
        }
    }

    private Dossier getDossierById( long groupId, String id) throws Exception{
        try {
            long dossierId = GetterUtil.getLong(id);
            if (dossierId == 0) {
                //get dossier by referenceId
                Dossier dossier = DossierLocalServiceUtil.getByRef(groupId, id);
                if(Validator.isNotNull(dossier)) {
                    return dossier;
                }

                return DossierLocalServiceUtil.getByDossierNo(groupId, id);
            }

            return DossierLocalServiceUtil.getDossier(dossierId);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
