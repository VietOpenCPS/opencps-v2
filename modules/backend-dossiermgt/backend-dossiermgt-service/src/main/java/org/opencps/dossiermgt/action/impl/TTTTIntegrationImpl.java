package org.opencps.dossiermgt.action.impl;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.TTTTIntegrationAction;
import org.opencps.dossiermgt.constants.IntegrateTTTTConstants;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.scheduler.PublishEventScheduler;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

public class TTTTIntegrationImpl implements TTTTIntegrationAction {
    private Log _log = LogFactoryUtil.getLog(TTTTIntegrationImpl.class);
    @Override
    public boolean syncDoActionDossier(Dossier dossier) throws Exception{
        List<ServerConfig> listServerConfig = ServerConfigLocalServiceUtil.getByProtocol(
                dossier.getGroupId(), ServerConfigTerm.TTTT_INTEGRATION);
        if (listServerConfig == null || (listServerConfig != null && listServerConfig.size() == 0)) {
            listServerConfig = ServerConfigLocalServiceUtil.getByProtocol(0, ServerConfigTerm.TTTT_INTEGRATION);
        }
        if (listServerConfig == null || listServerConfig.isEmpty()) {
            throw new Exception("No server config");
        }

        ServerConfig serverConfig = listServerConfig.get(0);
        JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= this.createBodyForCheckActionDossier(dossier, config);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity( config.getString("url"), request , String.class );

            if(response == null || response.getStatusCode() == null) {
                throw new Exception("Response from TTTT is null");
            }

            if(response.getStatusCode().value() < 200 || response.getStatusCode().value() > 300) {
                throw new Exception("Something happened on TTTT server with response: " + response.getStatusCode().value());
            }
            System.out.println("Sync data to TTTT successfully");
            return true;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private MultiValueMap<String, String> createBodyForCheckActionDossier(Dossier dossier, JSONObject serverConfig) {
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();

        String codeProfile = Validator.isNotNull(dossier.getDossierNo()) ? dossier.getDossierNo() : "";
        Integer siteId  = serverConfig.has("siteId") ? serverConfig.getInt("siteId") : 0;
        String codeTTHC = Validator.isNotNull(dossier.getServiceCode()) ? dossier.getServiceCode() : "";
        String nameTTHC = Validator.isNotNull(dossier.getServiceName()) ? dossier.getServiceName() : "";;
        Integer status;
        Integer formsReception ;
        Integer formsPayments = IntegrateTTTTConstants.FormsPaymentDirect;
        Integer level = IntegrateTTTTConstants.LEVEL;
        Integer isFromDVCQG = IntegrateTTTTConstants.IS_FROM_DVCQG;
        Integer isDVCBC;
        String data = "";

        String statusDossier = dossier.getDossierStatus() != null ? dossier.getDossierStatus() : "";

        if (statusDossier.equals("processing")) {
            status = IntegrateTTTTConstants.STATUS_RECEIVED;
        } else if (statusDossier.equals("interoperating") ||
                statusDossier.equals("releasing") ||
                statusDossier.equals("posting")) {
            status = IntegrateTTTTConstants.STATUS_PROCESSING;
        } else if (statusDossier.equals("done")) {
            status = IntegrateTTTTConstants.STATUS_DONE;
        } else {
            status = IntegrateTTTTConstants.STATUS_ANOTHER;
        }

        if(dossier.getOnline()) {
            formsReception = IntegrateTTTTConstants.FormsReceptionOnline;
        } else {
            formsReception = IntegrateTTTTConstants.FormsReceptionDirect;
        }

        if(dossier.getViaPostal() == 2) {
            isDVCBC = IntegrateTTTTConstants.DVBC_VNPOST;
        } else {
            isDVCBC = IntegrateTTTTConstants.DVBC_DIRECT;
        }

        map.add("codeProfile", codeProfile);
        map.add("siteId", String.valueOf(siteId));
        map.add("codeTTHC", codeTTHC);
        map.add("nameTTHC", nameTTHC);
        map.add("status", String.valueOf(status));
        map.add("formsReception", String.valueOf(formsReception));
        map.add("formsPayments", String.valueOf(formsPayments));
        map.add("level", String.valueOf(level));
        map.add("isFromDVCQG", String.valueOf(isFromDVCQG));
        map.add("isDVCBC", String.valueOf(isDVCBC));
        map.add("data", "");

        return map;
    }
}
