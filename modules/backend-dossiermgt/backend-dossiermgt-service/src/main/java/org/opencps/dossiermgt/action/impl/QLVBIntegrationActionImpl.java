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
import com.vpcp.example.ServiceTest;
import com.vpcp.services.AgencyServiceImp;
import com.vpcp.services.KnobstickServiceImp;
import com.vpcp.services.VnptProperties;
import com.vpcp.services.model.GetEdocResult;
import com.vpcp.services.model.GetReceivedEdocResult;
import com.vpcp.services.model.Knobstick;
import org.opencps.backend.dossiermgt.serviceapi.ApiThirdPartyService;
import org.opencps.backend.dossiermgt.serviceapi.ApiThirdPartyServiceImpl;
import org.opencps.communication.model.ServerConfig;
import org.opencps.dossiermgt.action.QLVBIntegrationAction;
import org.opencps.dossiermgt.action.util.DossierFileUtils;
import org.opencps.dossiermgt.constants.QLVBConstants;
import org.opencps.dossiermgt.input.model.FileVBModel;
import org.opencps.dossiermgt.input.model.QLVBModel;
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
    public boolean getDocEOfficeTTTT() throws Exception {
        try {
            String endpoint = "http://103.16.1.119:8080";
            String systemId = "001.00.26.G14";
            String secret = "A3N5ZEKAM6zxG95Y1y6HvfjzDIvn1gR3pctTYb1TjFp7";
            String storePathDir = "e:/test";
            String logError = "e:/test";
            String logErrorNew = "d:/test";

            int maxConnection = 10, retry = 3;
            VnptProperties vnptProperties = new VnptProperties(endpoint, systemId , secret, storePathDir, maxConnection, retry, logError, 1);
            AgencyServiceImp agencyService = new AgencyServiceImp(vnptProperties);
            KnobstickServiceImp knobstickService = new KnobstickServiceImp(vnptProperties);
            ServiceTest serviceTest = new ServiceTest();

            GetReceivedEdocResult resultGetListDocument = knobstickService.getReceivedEdocList(serviceTest.getJsonHeader());

            if(Validator.isNull(resultGetListDocument)) {
                throw new Exception("List VB from Eoffice is null");
            }

            String pstart = "---------";
            System.out.println(pstart+"status:"+resultGetListDocument.getStatus());
            System.out.println(pstart+"Desc:"+resultGetListDocument.getErrorDesc());
            System.out.println(pstart+"Size:"+resultGetListDocument.getKnobsticks().size());

            if(resultGetListDocument.getKnobsticks().size() == 0) {
                throw new Exception("No Knobsticks in result");
            }

            int sizeListDocument = resultGetListDocument.getKnobsticks().size();
            for(Knobstick item : resultGetListDocument.getKnobsticks()){
                try{
                    System.out.println("docId: "+item.getId());
                    String json2 = createHeaderJsonGetDoc(item.getId());
                    GetEdocResult getEdocResult = knobstickService.getEdoc(json2);
                    if(getEdocResult!=null) {
                        System.out.println(pstart+"status:"+getEdocResult.getStatus());
                        System.out.println(pstart+"Desc:"+getEdocResult.getErrorDesc());
                        System.out.println(pstart+"Data:"+getEdocResult.getData());
                        System.out.println(pstart+"file:"+getEdocResult.getFilePath());
                        //prase du lieu tu edxml
                        /*File file = new File(getEdocResult.getFilePath());
                        InputStream inputStream = new FileInputStream(file);
                        // goi tin status
                        if(serviceTest.getJsonHeader().matches("status")){
                            Status ed =  StatusXmlParser.parse(inputStream);
                            //lay thong tin trong header
                            MessageStatus messageHeader2 = (MessageStatus) ed.getHeader().getMessageHeader();
                        }
                        //goi tin edoc
                        else{
                            Ed ed =  EdXmlParser.getInstance().parse(inputStream);
                            //lay thong tin trong header
                            MessageHeader messageHeader = (MessageHeader) ed.getHeader().getMessageHeader();
                            //lay thong tin file dinh kem
                            List<Attachment> attachment = ed.getAttachments();
                            //lay thong tin trong body
                            Body body = ed.getBody();
                        }*/

                        if(getEdocResult.getStatus().equals("OK")){
                            String json = createHeaderJsonUpdate(item.getId(),"done") ;
//                            GetChangeStatusResult  GetChangeStatusResult2 = knobstickService.updateStatus(json);
                            System.err.println(item.getId());
                        }
                        else{
                            String json = createHeaderJsonUpdate(item.getId(),"fail") ;
//                            GetChangeStatusResult  GetChangeStatusResult2 = knobstickService.updateStatus(json);
                            System.err.println("errrr : " + item.getId());
                        }

                    }
                }catch(Exception ex){
                    _log.warn("Error when save itemId: " + item.getId());
                    _log.warn("Still running...");
                }
            }

            return false;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private static String createHeaderJsonGetDoc(String docId){
        StringBuffer stringBuffer = new StringBuffer();
        String filePath = "xml";
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
}
