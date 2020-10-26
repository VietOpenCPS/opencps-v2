package org.opencps.api.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import javafx.animation.ScaleTransition;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.controller.FrequencyOfficeManagement;
import org.opencps.dossiermgt.action.FrequencyIntegrationAction;
import org.opencps.dossiermgt.action.impl.FrequencyIntegrationActionImpl;
import org.opencps.dossiermgt.constants.FrequencyOfficeConstants;
import org.opencps.dossiermgt.input.model.*;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.service.CPSDossierBusinessLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.kernel.context.MBServiceContextFactoryUtil;

import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Base64;
import java.util.List;

public class FrequencyOfficeManagementImpl implements FrequencyOfficeManagement {

    private ObjectMapper objectMapper = new ObjectMapper();
    private static final Log _log = LogFactoryUtil.getLog(FrequencyOfficeManagementImpl.class);
    private static final String DOSSIER_BTTTT = "DOSSIER_BTTTT";

    @Override
    public Response sendProfile() {
        //Api to crawl all profile with status TIEPNHAN or RUT
        try{
            //validator
            List<ServerConfig> listConfig = ServerConfigLocalServiceUtil.getByServerAndProtocol(DOSSIER_BTTTT, DOSSIER_BTTTT);

            ServerConfig serverConfig = listConfig.get(0);
            FrequencyIntegrationAction integrationAction = new FrequencyIntegrationActionImpl(serverConfig);
            String token = integrationAction.getToken();

            List<ProfileReceiver> listDossiers = integrationAction.getDossiers(token);
            boolean result;

            for(ProfileReceiver oneDossier : listDossiers) {
                _log.info("Handling profile: " + oneDossier.getProfileId());
                ProfileInModel profile = integrationAction.getDetailDossier(token, oneDossier.getProfileId());
                if(Validator.isNotNull(profile) && Validator.isNotNull(profile.getStatus())) {
                    result = integrationAction.crawlDossierLGSP(profile, token);

                    if(result) {
                        integrationAction.updateStatusReceiver(token, oneDossier.getProfileId(), FrequencyOfficeConstants.STATUS_SUCCESS);
                    } else {
                        integrationAction.updateStatusReceiver(token, oneDossier.getProfileId(), FrequencyOfficeConstants.STATUS_FAIL);
                    }
                }
                _log.info("Done crawl one profile id: " + oneDossier.getProfileId());
            }

            return Response.status(HttpURLConnection.HTTP_OK).entity(null).build();
        } catch (Exception e) {
            _log.error("Error message when call api send file: " + e.getMessage());
           return null;
        }
    }

    @Override
    public Response sendOneProfile(String profileId) {
        //API test crawl one profile
        try{
            //validator
            List<ServerConfig> listConfig = ServerConfigLocalServiceUtil.getByServerAndProtocol(DOSSIER_BTTTT, DOSSIER_BTTTT);

            ServerConfig serverConfig = listConfig.get(0);
            FrequencyIntegrationAction integrationAction = new FrequencyIntegrationActionImpl(serverConfig);
            String token = integrationAction.getToken();

            boolean result;

            ProfileInModel profile = integrationAction.getDetailDossier(token, Integer.valueOf(profileId));
            if(Validator.isNotNull(profile) && Validator.isNotNull(profile.getStatus())) {
//                integrationAction.updateStatusReceiver(token, Integer.valueOf(profileId), FrequencyOfficeConstants.STATUS_SUCCESS);
                boolean resultCrawl = integrationAction.crawlDossierLGSP(profile, token);
//                if(resultCrawl) {
//                    integrationAction.updateStatusReceiver(token, Integer.valueOf(profileId), FrequencyOfficeConstants.STATUS_SUCCESS);
//                } else {
//                    integrationAction.updateStatusReceiver(token, Integer.valueOf(profileId), FrequencyOfficeConstants.STATUS_FAIL);
//                }
            }

            return Response.status(HttpURLConnection.HTTP_OK).entity(null).build();
        } catch (Exception e) {
            _log.error("Error message when call api send one file: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Response getListDossierFake() {
        try {
            String response = "{\n" +
                    "    \"status\": \"SUCCESS\",\n" +
                    "    \"errorCode\": \"0\",\n" +
                    "    \"errorDesc\": \"\",\n" +
                    "    \"profileReceivers\": [\n" +
                    "        {\n" +
                    "            \"profileId\": 54,\n" +
                    "            \"fromUnitCode\": \"000.00.00.G14\",\n" +
                    "            \"toUnitCode\": \"000.00.25.G14\",\n" +
                    "            \"createTime\": \"2020-06-20T01:08:52.000+0000\",\n" +
                    "            \"status\": \"CAPNHAT\",\n" +
                    "            \"status_profile\": \"5\"\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}";

            Object responseObj = objectMapper.readValue(response, Object.class);
            return Response.status(HttpURLConnection.HTTP_OK).entity(objectMapper.writeValueAsString(responseObj)).build();
        } catch (Exception e) {
            _log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Response getDetailDossierFake() {
        try {
            String response = "{\n" +
                    "    \"status\": \"SUCCESS\",\n" +
                    "    \"errorCode\": \"0\",\n" +
                    "    \"errorDesc\": \"\",\n" +
                    "    \"profileInmodel\": {\n" +
                    "        \"status\": \"CAPNHAT\",\n" +
                    "        \"source_id\": \"HS_0154\",\n" +
                    "        \"ref_code\": \"TC_HS_00223\",\n" +
                    "        \"procedures_code\": \"TT_HS_1_00443\",\n" +
                    "        \"system_id\": \"2\",\n" +
                    "        \"system_received\": \"3\",\n" +
                    "        \"creation_date\": \"2020-09-09\",\n" +
                    "        \"applicants_type\": 3,\n" +
                    "        \"applicants_id\": \"001\",\n" +
                    "        \"org_impl_code\": \"\",\n" +
                    "        \"accept_date\": \"2020-09-09\",\n" +
                    "        \"appointment_date\": \"2020-09-09\",\n" +
                    "        \"return_type\": 1,\n" +
                    "        \"note\": \"\",\n" +
                    "        \"from_unit_code\": \"000.00.03.G33\",\n" +
                    "        \"to_unit_code\": [\n" +
                    "            \"000.00.26.G14\"\n" +
                    "        ],\n" +
                    "        \"data_eform\": \"ewogICAgICAgICJuYW1lIjoiSOG7kyBzxqEgdsSDbiBi4bqjbiIsXAogICAgICAgIH0=\",\n" +
                    "        \"profileAttachments\": [\n" +
                    "            {\n" +
                    "                \"id\": 256,\n" +
                    "                \"profile_id\": 37291,\n" +
                    "                \"content_type\": \"doc\",\n" +
                    "                \"attachment_name\": \"Văn bản thông báo\",\n" +
                    "                \"content_transfer_encoded\": null,\n" +
                    "                \"attachment_file_url\": \"https://dichvucong3.mic.gov.vn/documents/10180/810002/13101.docx?version=1.0\",\n" +
                    "                \"is_verified\": 1,\n" +
                    "                \"description\": null,\n" +
                    "                \"is_deleted\": 0\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"id\": 257,\n" +
                    "                \"profile_id\": 37291,\n" +
                    "                \"content_type\": \"doc\",\n" +
                    "                \"attachment_name\": \"Văn bản báo cáo\",\n" +
                    "                \"content_transfer_encoded\": null,\n" +
                    "                \"attachment_file_url\": null,\n" +
                    "                \"is_verified\": 0,\n" +
                    "                \"description\": null,\n" +
                    "                \"is_deleted\": 0\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"profileDocFees\": [\n" +
                    "            {\n" +
                    "                \"id\": 50,\n" +
                    "                \"profile_id\": 37291,\n" +
                    "                \"fee_name\": \"Phí giao dịch\",\n" +
                    "                \"fee_type\": \"Phí giao dịch\",\n" +
                    "                \"price\": \"15000\",\n" +
                    "                \"description\": null\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"profileDocPaper\": [\n" +
                    "            {\n" +
                    "                \"id\": 328,\n" +
                    "                \"profile_id\": 37291,\n" +
                    "                \"paper_name\": \"Giấy khai sinh\",\n" +
                    "                \"paper_type\": \"Thủ tục\",\n" +
                    "                \"amount\": 2,\n" +
                    "                \"description\": null\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"profileApplicant\": {\n" +
                    "            \"id\": 112,\n" +
                    "            \"name\": \"Nguyễn Văn B\",\n" +
                    "            \"address\": \"\",\n" +
                    "            \"email\": \"\",\n" +
                    "            \"tel\": \"\",\n" +
                    "            \"identify\": \"\",\n" +
                    "            \"business_license\": \"\",\n" +
                    "            \"business_license_date\": null,\n" +
                    "            \"profile_id\": 37291\n" +
                    "        },\n" +
                    "        \"profileOwner\": {\n" +
                    "            \"id\": 112,\n" +
                    "            \"profile_id\": 37291,\n" +
                    "            \"name\": \"Nguyễn văn A\",\n" +
                    "            \"address\": \"\",\n" +
                    "            \"tel\": \"\"\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";

            Object responseObj = objectMapper.readValue(response, Object.class);
            return Response.status(HttpURLConnection.HTTP_OK).entity(objectMapper.writeValueAsString(responseObj)).build();
        } catch (Exception e) {
            _log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Response synDossierFake(String profile) {
        try {
            _log.info(11111);
            _log.info(profile);
            return Response.status(HttpURLConnection.HTTP_OK).entity(null).build();

        } catch (Exception e) {
            _log.error("error when sync with mess: " + e.getMessage());
            return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(null).build();
        }
    }

    @Override
    public Response sendStatusProfile(long dossierId) {
        try {
            List<ServerConfig> listConfig = ServerConfigLocalServiceUtil.getByServerAndProtocol(DOSSIER_BTTTT, DOSSIER_BTTTT);
            ServerConfig serverConfig = listConfig.get(0);
            FrequencyIntegrationAction integrationAction = new FrequencyIntegrationActionImpl(serverConfig);
            String token = integrationAction.getToken();
            integrationAction.sendStatusProfile(token, dossierId);

            return Response.status(HttpURLConnection.HTTP_OK).entity(null).build();

        } catch (Exception e) {
            _log.error("Error when send status profile: " + e.getMessage());
            return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(null).build();
        }
    }

    @Override
    public Response sendStatusProfileToDVCBo(long dossierId) {
        try {
            List<ServerConfig> listConfig = ServerConfigLocalServiceUtil.getByServerAndProtocol(DOSSIER_BTTTT, DOSSIER_BTTTT);
            ServerConfig serverConfig = listConfig.get(0);
            FrequencyIntegrationAction integrationAction = new FrequencyIntegrationActionImpl(serverConfig);
            String token = integrationAction.getToken();
            integrationAction.sendStatusProfileToDVCBo(token, dossierId);

            return Response.status(HttpURLConnection.HTTP_OK).entity(null).build();

        } catch (Exception e) {
            _log.error("Error when send status profile: " + e.getMessage());
            return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(null).build();
        }
    }

    @Override
    public Response syncDossierToLGSPManual(long dossierId) {
        try {
            List<ServerConfig> listConfig = ServerConfigLocalServiceUtil.getByServerAndProtocol(DOSSIER_BTTTT, DOSSIER_BTTTT);
            ServerConfig serverConfig = listConfig.get(0);
            FrequencyIntegrationAction integrationAction = new FrequencyIntegrationActionImpl(serverConfig);
            String token = integrationAction.getToken();

            integrationAction.syncDossierToLGSPManual(token, dossierId);

            return Response.status(HttpURLConnection.HTTP_OK).entity(null).build();
        } catch (Exception e) {
            _log.error("Error when sync dossier: " + e.getMessage());
            return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(null).build();
        }
    }

    @Override
    public Response syncDossierToDVCBoManual(long dossierId) {
        try {
            List<ServerConfig> listConfig = ServerConfigLocalServiceUtil.getByServerAndProtocol(DOSSIER_BTTTT, DOSSIER_BTTTT);
            ServerConfig serverConfig = listConfig.get(0);
            FrequencyIntegrationAction integrationAction = new FrequencyIntegrationActionImpl(serverConfig);
            String token = integrationAction.getToken();

            integrationAction.syncDossierToDVCBoManual(token, dossierId);

            return Response.status(HttpURLConnection.HTTP_OK).entity(null).build();
        } catch (Exception e) {
            _log.error("Error when sync dossier: " + e.getMessage());
            return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(null).build();
        }
    }
}
