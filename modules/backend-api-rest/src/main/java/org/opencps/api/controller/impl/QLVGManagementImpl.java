package org.opencps.api.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.api.controller.QLVGManagement;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.QLVBIntegrationAction;
import org.opencps.dossiermgt.action.impl.QLVBIntegrationActionImpl;
import org.opencps.dossiermgt.action.util.DossierFileUtils;
import org.opencps.dossiermgt.constants.QLVBConstants;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.service.CPSDossierBusinessLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.HttpURLConnection;
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
    private static final String SERVER_EOFFICE_TTTT = "TTTT";
    private static final String SERVER_EOFFICE_HAUGIANG = "HAUGIANG";

    private static final double MAX_FILE_SIZE = 10;
    private List<ServerConfig> listConfig;

    public QLVGManagementImpl() {
        this.listConfig = ServerConfigLocalServiceUtil.getByServerAndProtocol(API_SYNC_QLVB, API_SYNC_QLVB);
    }

    @Override
    public Response sendProfile(long dossierId) {
        try {
            if(Validator.isNull(this.listConfig) || this.listConfig.isEmpty()
                    || Validator.isNull(this.listConfig.get(0))) {
                throw new Exception("No config was found with protocol: " + API_SYNC_QLVB);
            }

            ServerConfig serverConfig = this.listConfig.get(0);
            if(Validator.isNull(serverConfig.getConfigs())
                    || serverConfig.getConfigs().isEmpty()) {
                throw new Exception("No config in server config");
            }

            JSONObject configJson = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
            if(Validator.isNull(configJson)) {
                throw new Exception("Parse config json error");
            }

            String eOfficeServer = configJson.getString(QLVBConstants.CONFIG_SERVER_EOFFICE);
            if(Validator.isNull(eOfficeServer) || eOfficeServer.isEmpty()) {
                throw new Exception("No eOffice server config");
            }

            QLVBIntegrationAction qlvbAction = new QLVBIntegrationActionImpl(serverConfig);
            String token;
            if(eOfficeServer.equals(SERVER_EOFFICE_HAUGIANG)) {
                token = qlvbAction.getTokenHG();
                qlvbAction.sendVBHG(token, dossierId);
            } else if(eOfficeServer.equals(SERVER_EOFFICE_TTTT)) {
                token = qlvbAction.getTokenTTTT();
                qlvbAction.sendDocTTTT(token, dossierId);
            }

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
            QLVBIntegrationAction docAction = new QLVBIntegrationActionImpl(serverConfig);
            docAction.doAction(groupId, serviceContext, dossier, actionCode);

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

    @Override
    public Response testReceiveDossierCTS(long dossierId) {
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        try {
            if(Validator.isNull(this.listConfig) || this.listConfig.isEmpty()) {
                throw new Exception("No config was found with protocol: " + API_SYNC_QLVB);
            }
            ServerConfig serverConfig = this.listConfig.get(0);
            QLVBIntegrationAction docAction = new QLVBIntegrationActionImpl(serverConfig);
            docAction.getDocEOfficeTTTT();
            jsonObject.put("message", "Success");
            jsonObject.put("code", "00");

            return Response.status(HttpURLConnection.HTTP_OK).entity(jsonObject.toJSONString()).build();
        } catch (Exception e) {
            jsonObject.put("message", "Server internal error");
            jsonObject.put("code", "05");
            _log.error("Error when update profile from Eoffice with id " + dossierId + ": " + e.getMessage());
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(jsonObject.toJSONString()).build();
        }
    }


    @Override
    public Response testSendDossierCTS(long dossierId) {
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        try {
            if(Validator.isNull(this.listConfig) || this.listConfig.isEmpty()) {
                throw new Exception("No config was found with protocol: " + API_SYNC_QLVB);
            }
            ServerConfig serverConfig = this.listConfig.get(0);
            QLVBIntegrationAction docAction = new QLVBIntegrationActionImpl(serverConfig);
            docAction.sendDocEOfficeTTTT();
            jsonObject.put("message", "Success");
            jsonObject.put("code", "00");

            return Response.status(HttpURLConnection.HTTP_OK).entity(jsonObject.toJSONString()).build();
        } catch (Exception e) {
            jsonObject.put("message", "Server internal error");
            jsonObject.put("code", "05");
            _log.error("Error when update profile from Eoffice with id " + dossierId + ": " + e.getMessage());
            return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(jsonObject.toJSONString()).build();
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
