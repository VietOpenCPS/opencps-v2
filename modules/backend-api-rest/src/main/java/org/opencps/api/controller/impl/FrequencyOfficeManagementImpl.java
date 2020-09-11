package org.opencps.api.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import javafx.animation.ScaleTransition;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.controller.FrequencyOfficeManagement;
import org.opencps.api.controller.util.DossierUtils;
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

    private ObjectMapper objectMapper;
    private static final Log _log = LogFactoryUtil.getLog(FrequencyOfficeManagementImpl.class);
    private static final String DOSSIER_BTTTT = "DOSSIER_BTTTT";

    private InputStream base64toFile(byte[] contentDecoded) {
        try {
            return new ByteArrayInputStream(contentDecoded);
        } catch (Exception e) {
            _log.error("Error when parse file: " + e.getMessage());
        }
        return null;
    }

    private byte[] decodeBase64(String contentEncode) {
        try {
            byte[] encbytes = contentEncode.getBytes();

            Base64.Decoder dec = Base64.getDecoder();
            byte[] decbytes = dec.decode(encbytes);
            return decbytes;
        } catch (Exception e) {
            _log.error("Error when decode: " + e.getMessage());
        }
        return null;
    }

    private String getMimeType(String type) {
        switch (type) {
            case FrequencyOfficeConstants.DOC :
                return FrequencyOfficeConstants.MIME_DOC;
            case FrequencyOfficeConstants.DOCX :
                return FrequencyOfficeConstants.MIME_DOCX;
            case FrequencyOfficeConstants.CSV :
                return FrequencyOfficeConstants.MIME_CSV;
            case FrequencyOfficeConstants.JPEG :
                return FrequencyOfficeConstants.MIME_JPEG;
            case FrequencyOfficeConstants.JPG :
                return FrequencyOfficeConstants.MIME_JPG;
            case FrequencyOfficeConstants.PNG :
                return FrequencyOfficeConstants.MIME_PNG;
            case FrequencyOfficeConstants.PDF :
                return FrequencyOfficeConstants.MIME_PDF;
            case FrequencyOfficeConstants.XLS :
                return FrequencyOfficeConstants.MIME_XLS;
            case FrequencyOfficeConstants.XLSX :
                return FrequencyOfficeConstants.MIME_XLSX;
            default:
                return "";
        }
    }

    @Override
    public Response sendProfile() {
        try{
            //validator
            List<ServerConfig> listConfig = ServerConfigLocalServiceUtil.getByServerAndProtocol(DOSSIER_BTTTT, DOSSIER_BTTTT);

            ServerConfig serverConfig = listConfig.get(0);
            FrequencyIntegrationAction integrationAction = new FrequencyIntegrationActionImpl(serverConfig);
            String token = integrationAction.getToken();
            List<ProfileReceiver> listDossiers = integrationAction.getDossiers(token);

            for(ProfileReceiver oneDossier : listDossiers) {
                if(Validator.isNotNull(oneDossier.getStatus())) {
                    ProfileInModel profile = integrationAction.getDetailDossier(token,oneDossier.getProfileId());
                    if(Validator.isNotNull(profile)) {
                        //todo thong tin ho so

                    }
                }
            }

            return Response.status(HttpURLConnection.HTTP_OK).entity(null).build();
        } catch (Exception e) {
            _log.error("Error message when call api send file: " + e.getMessage());
           return null;
        }
    }
}
