package org.opencps.dossiermgt.action.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.portal.kernel.json.JSONArray;
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
import org.opencps.backend.dossiermgt.logistic.ViettelPostTerm;
import org.opencps.backend.dossiermgt.service.util.UtilsFile;
import org.opencps.backend.dossiermgt.serviceapi.ApiThirdPartyService;
import org.opencps.backend.dossiermgt.serviceapi.ApiThirdPartyServiceImpl;
import org.opencps.communication.model.ServerConfig;
import org.opencps.dossiermgt.action.FrequencyIntegrationAction;
import org.opencps.dossiermgt.constants.FrequencyOfficeConstants;
import org.opencps.dossiermgt.input.model.*;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.service.CPSDossierBusinessLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.kernel.context.MBServiceContextFactoryUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class FrequencyIntegrationActionImpl implements FrequencyIntegrationAction {
    private JSONObject configJson;
    private ApiThirdPartyService apiService;
    private Log _log = LogFactoryUtil.getLog(FrequencyIntegrationActionImpl.class);
    private static final String SERVER_CONFIG_NULL = "There is no server config frequency";
    private static final String PARSE_CONFIG_JSON_FAIL= "Create object json from config error";
    private ObjectMapper objectMapper;
    private ServerConfig serverConfig;

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
    public boolean crawlDossierLGSP(ProfileInModel profile) throws Exception{
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

            String serviceCodeCTS = profile.getProcedures_code();
            JSONArray listServiceConfig = this.configJson.getJSONArray("serviceConfig");
            if(Validator.isNull(listServiceConfig) || listServiceConfig.length() == 0) {
                throw new Exception("No service config");
            }

            //get action code
            String status = profile.getStatus();
            JSONArray listActionCode = this.configJson.getJSONArray("actions");
            int lengthListActionCode = listActionCode.length();
            JSONObject oneActionCode;
            String actionCode = "";
            for(int i = 0; i< lengthListActionCode; i ++) {
                oneActionCode = listActionCode.getJSONObject(i);
                if(oneActionCode.getString("status").equals(status)) {
                    actionCode = oneActionCode.getString("actionCode");
                    break;
                }
            }

            //counting payment
            if(Validator.isNotNull(profile.getProfileDocFees())) {
                final Integer PAYMENT_ONE_GATE = 1;
                List<ProfileDocFee> profileDocFeeList = profile.getProfileDocFees();
                int totalFee = 0;
                for(ProfileDocFee oneDocFee: profileDocFeeList) {
                    if(Validator.isNotNull(oneDocFee.getPrice())) {
                        totalFee += Integer.parseInt(oneDocFee.getPrice());
                    }
                }

                ProfilePaymentFDS payment = new ProfilePaymentFDS();
                payment.setRequestPayment(PAYMENT_ONE_GATE);
                payment.setFeeAmount(totalFee);
                profile.setPayment(payment);
            }

            if(status.equals(FrequencyOfficeConstants.STATUS_RECEIVE)) {
                Dossier dossier = null;
                dossier = DossierLocalServiceUtil.getByRef(groupId, profile.getRef_code());
                if(Validator.isNotNull(dossier)) {
                    _log.error("Ho so da duoc tiep nhan: " + dossier.getDossierNo());
                    return true;
                }
                //mapping service config info
                int length = listServiceConfig.length();
                JSONObject oneServiceConfig;

                for(int i = 0; i< length; i ++) {
                    oneServiceConfig = listServiceConfig.getJSONObject(i);
                    if(oneServiceConfig.getString("procedures_code").equals(serviceCodeCTS)) {
                        profile.setServiceCode(oneServiceConfig.getString("serviceCode"));
                        profile.setGovAgencyCode(oneServiceConfig.getString("govAgencyCode"));
                        profile.setTemplateNo(oneServiceConfig.getString("templateNo"));
                        break;
                    }
                }

                if(Validator.isNull(profile.getServiceCode())
                        || profile.getServiceCode().isEmpty()
                        || Validator.isNull(profile.getGovAgencyCode())
                        || profile.getGovAgencyCode().isEmpty()
                        || Validator.isNull(profile.getTemplateNo())
                        || profile.getTemplateNo().isEmpty()
                ) {
                    throw new Exception("No service code mapped when create dossier");
                }

                dossier = CPSDossierBusinessLocalServiceUtil.createDossierFrequency(
                        groupId, company, user, serviceContext, profile);
                this.addDossierFile(profile, groupId, serviceContext, dossier);
            } else {
                Dossier dossier = DossierLocalServiceUtil.getByRef(groupId, profile.getRef_code());
                if(Validator.isNull(dossier)) {
                    throw new Exception("No dossier found to update with referenceId = " + profile.getRef_code());
                }
                CPSDossierBusinessLocalServiceUtil.updateDossierFrequencyAction(groupId, serviceContext, dossier, profile, actionCode);
                if(status.equals(FrequencyOfficeConstants.STATUS_UPDATE)){
                    this.addDossierFile(profile, groupId, serviceContext, dossier);
                }
            }
            return true;
        }catch (Exception e) {
            _log.error("Error when crawlDossierLGSP: " + e.getMessage());
            return false;
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
                    Thread.sleep(3000);
                }
            }
        }catch (Exception e) {
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
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ProfileReceiver> getDossiers(String token) throws Exception {
        try{
            String urlGetDossiers = this.configJson.getString(FrequencyOfficeConstants.CONFIG_URL) +
                    this.configJson.get(FrequencyOfficeConstants.CONFIG_GET_LIST_DOSSIERS) + "?unit_code=" +
                    this.configJson.get(FrequencyOfficeConstants.CONFIG_UNIT_CODE);
//            String urlGetDossiers = "http://localhost:8080/o/rest/v2/hshc/dossiers";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + token);

            JSONObject response = apiService.get(urlGetDossiers, headers);

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
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ProfileInModel getDetailDossier(String token, Integer profileId) throws Exception {
        try{
            String urlGetDetailDossier = this.configJson.getString(FrequencyOfficeConstants.CONFIG_URL) +
                    this.configJson.get(FrequencyOfficeConstants.CONFIG_GET_DETAIL_DOSSIERS) + "?unit_code=" +
                    this.configJson.get(FrequencyOfficeConstants.CONFIG_UNIT_CODE) + "&profile_id=" + profileId;
//            String urlGetDetailDossier = "http://localhost:8080/o/rest/v2/hshc/dossier";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + token);

            JSONObject response = apiService.get(urlGetDetailDossier, headers);
            if(Validator.isNull(response)) {
                throw new Exception("Response get list dossier null");
            }

            String profileDetail  = response.getString("profileInmodel");
            if(Validator.isNull(profileDetail)) {
                throw new Exception("Profile in model is null");
            }

            objectMapper = new ObjectMapper();
            return objectMapper.readValue(profileDetail, ProfileInModel.class);
        }catch (Exception e) {
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
            headers.set("Authorization", "Bearer " + token);

            JSONObject response = apiService.callApi(urlUpdateStatusReceiver, headers, null);
            if(Validator.isNull(response)) {
                throw new Exception("Response update status Receiver null");
            }
            String statusUpdate = response.getString("status");
            _log.info("Response update status Receiver: " + statusUpdate);

        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void syncDossierToLGSP(String token, ProfileInModel profile) throws Exception {
        try {
            long groupId = serverConfig.getGroupId();
            String urlSyncDossier = this.configJson.getString(FrequencyOfficeConstants.CONFIG_URL) +
                    this.configJson.get(FrequencyOfficeConstants.CONFIG_SYNC_DOSSIER);

//            String urlSyncDossier = "http://localhost:8080/o/rest/v2/hshc/synDossierFake";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Dossier dossier = DossierLocalServiceUtil.getByRef(groupId, profile.getRef_code());
            if(Validator.isNull(dossier)) {
                throw new Exception("No dossier found to update with referenceId = " + profile.getRef_code());
            }

            if(Validator.isNull(dossier.getDossierNo()) || dossier.getDossierNo().isEmpty()) {
                throw new Exception("DossierNo is null with referenceId = " + profile.getRef_code());
            }
            profile.setSource_id(dossier.getDossierNo());
            JSONObject response = apiService.callApi(urlSyncDossier, headers, profile);
            _log.info("Result syn dossier: " + response);

        } catch (Exception e) {
            _log.error("Sync dossier to lgsp error: " + e.getMessage());
        }
    }

    @Override
    public void sendStatusProfile(String token, long dossierId) throws Exception {

    }
}
