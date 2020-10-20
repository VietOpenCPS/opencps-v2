package org.opencps.dossiermgt.action.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
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
import org.opencps.dossiermgt.model.*;
import org.opencps.dossiermgt.service.*;
import org.opencps.kernel.context.MBServiceContextFactoryUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.InputStream;
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
            String actionCodeRequestPayment = "";
            for(int i = 0; i< lengthListActionCode; i ++) {
                oneActionCode = listActionCode.getJSONObject(i);
                if(oneActionCode.getString("status").equals(status)) {
                    actionCode = oneActionCode.getString("actionCode");
                    break;
                } else if(oneActionCode.getString("status").equals(REQUEST_PAYMENT_STATUS)) {
                    actionCodeRequestPayment = oneActionCode.getString("actionCode");
                    break;
                }
            }

            if(actionCode.isEmpty() && actionCodeRequestPayment.isEmpty()) {
                throw new Exception("No action code mapped with refCode: " + profile.getRef_code() + ", status: " + status);
            }

            if(status.equals(FrequencyOfficeConstants.STATUS_RECEIVE)) {
                Dossier dossier;
                dossier = DossierLocalServiceUtil.getByRef(groupId, profile.getRef_code());
                if(Validator.isNotNull(dossier)) {
                    _log.error("TIEPNHAN|Ho so da duoc tiep nhan: " + dossier.getDossierNo());
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
                    throw new Exception("TIEPNHAN|No service code mapped when create dossier");
                }

                dossier = CPSDossierBusinessLocalServiceUtil.createDossierFrequency(
                        groupId, company, user, serviceContext, profile, actionCode);

                //Save file dinh kem
                this.addDossierFile(profile, groupId, serviceContext, dossier);
                return true;
            }

            //Case API RUT, CAP NHAT, THANH TOAN
            Dossier dossier = DossierLocalServiceUtil.getByRef(groupId, profile.getRef_code());
            if(Validator.isNull(dossier)) {
                throw new Exception("No dossier found to update with referenceId = " + profile.getRef_code());
            }

            //Case THANH TOAN
            if(status.equals(FrequencyOfficeConstants.STATUS_PAYMENT) && profile.getIs_request_payment().equals("false")) {
                //case MCDT
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

                CPSDossierBusinessLocalServiceUtil.updateDossierFrequencyAction(groupId, serviceContext,
                        dossier, profile, actionCode);
                return true;
            } else if (status.equals(FrequencyOfficeConstants.STATUS_PAYMENT) && profile.getIs_request_payment().equals("true")) {
                //Checklist 2: Luong 3.4
                //case cuc tan so gui yeu cau thanh toan
                //Cap nhat trang thai ho so => cho thanh toan
                if(actionCodeRequestPayment.isEmpty()) {
                    throw new Exception("THANHTOAN|Case send request payment - No action code mapped");
                }
                CPSDossierBusinessLocalServiceUtil.updateDossierFrequencyAction(groupId, serviceContext,
                        dossier, profile, actionCodeRequestPayment);
                //Sau khi update trang thai ho so 1 cua sang cho` thanh toan, se call api
                //SyncDossierToLGSPManual de dong bo trang thai ho so len DVC Bo (se de o cau hinh de auto call)
                return true;
            }

            //Case API RUT, CAP NHAT
            if(status.equals(FrequencyOfficeConstants.STATUS_PULL_OUT) || status.equals(FrequencyOfficeConstants.STATUS_UPDATE)) {
                CPSDossierBusinessLocalServiceUtil.updateDossierFrequencyAction(groupId, serviceContext, dossier, profile, actionCode);
                if(status.equals(FrequencyOfficeConstants.STATUS_UPDATE)) {
                    this.addDossierFile(profile, groupId, serviceContext, dossier);
                }
            }
            return true;
        } catch (Exception e) {
            _log.error("Error when crawlDossierLGSP for refCode " + profile.getRef_code() + " | Error: " + e.getMessage());
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
                    this.configJson.get(FrequencyOfficeConstants.CONFIG_GET_DETAIL_DOSSIERS) + "?profile_id=" + profileId;
//            String urlGetDetailDossier = "http://localhost:8080/o/rest/v2/hshc/dossier";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + token);

            JSONObject response = apiService.get(urlGetDetailDossier, headers);
//            JSONObject response = apiService.getNew(urlGetDetailDossier, token);
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

            if(profile.getStatus().equals(FrequencyOfficeConstants.STATUS_DENIED)) {
                if(Validator.isNull(profile.getProcess_officials()) || profile.getProcess_officials().isEmpty()) {
                    profile.setProcess_officials("Not found");
                }

                if(Validator.isNull(profile.getTime_of_process()) || profile.getTime_of_process().isEmpty()) {
                    profile.setTime_of_process(getCurrentDateStr(FORMAT_DATE_LGSP, new Date()));
                }
            }

            profile.setSource_id(dossier.getDossierNo());
            profile.setType("profile");
            profile.setFrom_unit_code(this.configJson.getString(FrequencyOfficeConstants.CONFIG_FROM_UNIT_CODE));
            profile.setTo_unit_code(new String[] {this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE)});
            JSONObject response = apiService.callApi(urlSyncDossier, headers, profile);
            _log.info("Result sync dossier: " + response);

        } catch (Exception e) {
            _log.error("Sync dossier to lgsp error: " + e.getMessage());
        }
    }

    @Override
    public void syncDossierToLGSPManual(String token, long dossierId) throws Exception {
        try {
            //case 3.5, 3.6
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
            //todo remove this line (dang test thanh toan)
            Integer statusProfile = 5;
//            Integer statusProfile = mappingDossierStatus(dossier.getDossierStatus());
            if(statusProfile.equals(0)) {
                throw new Exception("Status profile: 0");
            }

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

//            String urlSyncDossier = "http://localhost:8080/o/rest/v2/hshc/synDossierFake";
            JSONObject response = apiService.callApi(urlSyncDossier, headers, profile);
            _log.info("Result sync dossier manual: " + response);
        } catch (Exception e) {
            throw new Exception("SyncDossierToLGSPManual error with message: " + e.getMessage());
        }
    }

    @Override
    public void syncDossierToDVCBoManual(String token, long dossierId) throws Exception {
        try {
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

            String proceduresCode = this.getProceduresCodeQGFromServiceCodeFDS(dossier.getServiceCode());
            if(proceduresCode.isEmpty()) {
                throw new Exception("No proceduresCode found with dossierId: " + dossierId);
            }

            String creationDate = this.getCurrentDateStr(FORMAT_DATE_LGSP, dossier.getCreateDate());
            String acceptDate = this.getCurrentDateStr(FORMAT_DATE_LGSP, dossier.getReceiveDate());
            String appointmentDate = this.getCurrentDateStr(FORMAT_DATE_LGSP, dossier.getDueDate());

            if(creationDate.isEmpty()) {
                throw new Exception("Create date is empty with dossierId: " + dossierId);
            }

            if(Validator.isNull(dossier.getApplicantName()) || dossier.getApplicantName().isEmpty()) {
                throw new Exception("ApplicantName is empty with dossierId: " + dossierId);
            }

            if(Validator.isNull(dossier.getDelegateName()) || dossier.getDelegateName().isEmpty()) {
                throw new Exception("DelegateName is empty with dossierId: " + dossierId);
            }

            Integer statusProfile = mappingDossierStatus(dossier.getDossierStatus());
            //todo remove this
            statusProfile = 2;
            if(statusProfile.equals(0)) {
                throw new Exception("Status profile 0 with dossierId: " + dossierId);
            }

            ProfileInModel profile = new ProfileInModel();
            ProfileOwner profileOwner = new ProfileOwner();
            ProfileApplicant profileApplicant = new ProfileApplicant();
            profileOwner.setName(dossier.getApplicantName());
            profileOwner.setAddress(dossier.getAddress());
            profileOwner.setTel(dossier.getContactTelNo());
            profileApplicant.setName(dossier.getDelegateName());
            profileApplicant.setAddress(dossier.getDelegateAddress());
            profileApplicant.setEmail(dossier.getDelegateEmail());
            profileApplicant.setTel(dossier.getDelegateTelNo());
            profileApplicant.setIdentify(dossier.getDelegateIdNo());
            List<ProfileAttachment> listProfileAttachments = this.getProfileAttachments(dossier);
            List<ProfileDocFee> listProfileDocFees = this.getListProfileDocFee(dossier.getGroupId(), dossierId);

            profile.setStatus(FrequencyOfficeConstants.STATUS_RECEIVE);
            profile.setProfiles_status(String.valueOf(statusProfile));
            profile.setSource_id(dossier.getDossierNo());
            profile.setRef_code(dossier.getReferenceUid());
            profile.setProcedures_code(proceduresCode);
            profile.setType("profile");
            profile.setCreation_date(creationDate);
            profile.setFrom_unit_code(this.configJson.getString(FrequencyOfficeConstants.CONFIG_FROM_UNIT_CODE));
            profile.setTo_unit_code(new String[] {this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE)});
            profile.setProfileOwner(profileOwner);
            profile.setProfileApplicant(profileApplicant);
            profile.setAccept_date(acceptDate);
            profile.setAppointment_date(appointmentDate);
            if(Validator.isNotNull(listProfileAttachments) && listProfileAttachments.size() > 0) {
                profile.setProfileAttachments(listProfileAttachments);
            }

            if(Validator.isNotNull(listProfileDocFees) && listProfileDocFees.size() > 0) {
                profile.setProfileDocFees(listProfileDocFees);
            }

            if(Validator.isNotNull(dossier.getReleaseDate())) {
                profile.setProfiles_status(String.valueOf(FrequencyOfficeConstants.STATUS_LGSP_RETURNED_RESULT));
                profile.setIs_update("true");
            } else {
                profile.setProfiles_status(String.valueOf(FrequencyOfficeConstants.STATUS_LGSP_RECEIVED));
                profile.setIs_update("false");
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + token);

            String urlSyncDossier = this.configJson.getString(FrequencyOfficeConstants.CONFIG_URL) +
                    this.configJson.getString(FrequencyOfficeConstants.CONFIG_SYNC_DOSSIER_TO_DVC_BO);
//            String urlSyncDossier = "http://localhost:8080/o/rest/v2/hshc/synDossierFake";

            JSONObject response = apiService.callApi(urlSyncDossier, headers, profile);
            _log.info("Result sync dossier DVC Bo manual: " + response);
        } catch (Exception e) {
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
            _log.error("Error when get profile doc fee: " + e.getMessage());
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
            _log.error("Error when get procedures code from service code " + serviceCode + ": " + e.getMessage());
            return "";
        }
    }

    @Override
    public void sendStatusProfile(String token, long dossierId, long status) throws Exception {

        try {
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
            if(Validator.isNotNull(dossier.getMetaData()) && !dossier.getMetaData().isEmpty()) {
                metaDataJson = JSONFactoryUtil.createJSONObject(dossier.getMetaData());
            }

//            Integer statusProfile = Integer.valueOf(status);
            //toto undo this code
//            Integer statusProfile = mappingDossierStatus(dossier.getDossierStatus());
//            if(statusProfile.equals(0)) {
//                throw new Exception("Status profile: 0");
//            }

            String urlSyncDossier = this.configJson.getString(FrequencyOfficeConstants.CONFIG_URL) +
                    this.configJson.get(FrequencyOfficeConstants.CONFIG_SEND_STATUS_RECEIVER);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + token);

            Map<String, Object> body = new HashMap<>();
            body.put(FrequencyOfficeConstants.TYPE, "status");
            body.put(FrequencyOfficeConstants.SOURCE_ID, dossier.getDossierNo());
            body.put(FrequencyOfficeConstants.REF_CODE, dossier.getReferenceUid());
            body.put(FrequencyOfficeConstants.STATUS_PROFILE, String.valueOf(status));
            body.put(FrequencyOfficeConstants.PROCESS_OFFICIALS, "Admin");
            body.put(FrequencyOfficeConstants.TIME_OF_PROCESS, getCurrentDateStr(FORMAT_DATE_LGSP, new Date()));
            body.put(FrequencyOfficeConstants.FROM_UNIT_CODE, this.configJson.getString(FrequencyOfficeConstants.CONFIG_FROM_UNIT_CODE));
            if(Validator.isNull(metaDataJson)
                    || metaDataJson.getString("fromUnitCode").isEmpty()
                    || Validator.isNotNull(dossier.getDueDate())) {
                body.put(FrequencyOfficeConstants.TO_UNIT_CODE, new String[] {
                        this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE),
                        this.configJson.getString(FrequencyOfficeConstants.CONFIG_TO_UNIT_CODE_CUCTANSO)
                });
            } else {
                String fromUnitCode = metaDataJson.getString("fromUnitCode");
                body.put(FrequencyOfficeConstants.TO_UNIT_CODE, new String[] { fromUnitCode });
            }

            JSONObject response = apiService.callApi(urlSyncDossier, headers, body);
            _log.info("Result send dossier status: " + response);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void sendStatusProfileToDVCBo(String token, long dossierId) throws Exception {
        try {
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

            Integer statusProfile = 2;
            //toto undo this code
//            Integer statusProfile = mappingDossierStatus(dossier.getDossierStatus());
//            if(statusProfile.equals(0)) {
//                throw new Exception("Status profile: 0");
//            }

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

            JSONObject response = apiService.callApi(urlSyncDossier, headers, body);
            _log.info("Result send dossier status: " + response);
        } catch (Exception e) {
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
            _log.info("Error when mapping dossier status: " + e.getMessage());
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
                            profileAttachment.setAttachment_file_url(
                                    InetAddress.getLocalHost().getHostName() +
                                    "/o/rest/v2/dossiers/lgsp/download/"
                                    + fileEntry.getFileEntryId());
                            //todo remove this
                            profileAttachment.setContent_type("docx");
                            profileAttachment.setAttachment_file_url("https://dichvucong3.mic.gov.vn/documents/10180/1432020/2174501.docx?version=1.0");


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
                        profileAttachment.setAttachment_file_url(
                                InetAddress.getLocalHost().getHostName() +
                                        "/o/rest/v2/dossiers/lgsp/download/"
                                        + fileEntry.getFileEntryId());
                        listAttachments.add(profileAttachment);
                    }
                }
            }
            return listAttachments;
        } catch (Exception e) {
            _log.info("Error when get list attachments: " + e.getMessage());
            return null;
        }
    }

    private String getCurrentDateStr(String format, Date date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            return formatter.format(date);
        } catch (Exception e) {
            _log.error("Error when get current date str: " + e.getMessage());
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
            _log.error("Error when parse string date: " + e.getMessage());
            return "";
        }
    }
}
