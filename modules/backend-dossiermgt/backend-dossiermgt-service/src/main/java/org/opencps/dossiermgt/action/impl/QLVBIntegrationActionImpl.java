package org.opencps.dossiermgt.action.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.backend.dossiermgt.serviceapi.ApiThirdPartyService;
import org.opencps.backend.dossiermgt.serviceapi.ApiThirdPartyServiceImpl;
import org.opencps.communication.model.ServerConfig;
import org.opencps.dossiermgt.action.QLVBIntegrationAction;
import org.opencps.dossiermgt.action.util.DossierFileUtils;
import org.opencps.dossiermgt.constants.FrequencyOfficeConstants;
import org.opencps.dossiermgt.constants.QLVBConstants;
import org.opencps.dossiermgt.input.model.FileVBHGModel;
import org.opencps.dossiermgt.input.model.QLVBHGModel;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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
        String  lgspAccessToken = "{\"AccessKey\":\"" + this.configJson.getString(QLVBConstants.CONFIG_ACCESS_KEY)
                + "\",\"SecretKey\":\"" + this.configJson.getString(QLVBConstants.CONFIG_SECRET_KEY) +
                "\",\"AppName\":\"" + this.configJson.getString(QLVBConstants.CONFIG_APP_NAME)
                + "\",\"PartnerCode\":\"" + this.configJson.getString(QLVBConstants.CONFIG_PARTNER_CODE)
                + "\",\"PartnerCodeCus\":\"" + this.configJson.getString(QLVBConstants.CONFIG_PARTNER_CODE_CUS) + "\"}";
        this.base64LgspToken = Base64.getEncoder().encodeToString(lgspAccessToken.getBytes());
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

    private List<FileVBHGModel> getListVBHG(long dossierId) {
        try {
            List<FileVBHGModel> listFileSend = new ArrayList<>();
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

                    FileVBHGModel fileVBHG = new FileVBHGModel();
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
            List<FileVBHGModel> listVBHG = this.getListVBHG(dossierId);
            QLVBHGModel qlvbhgModel = new QLVBHGModel();
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
}
