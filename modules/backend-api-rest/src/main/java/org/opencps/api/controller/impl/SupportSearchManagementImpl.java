package org.opencps.api.controller.impl;

import backend.auth.api.exception.BusinessExceptionImpl;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.constants.SupportSearchConstants;
import org.opencps.api.controller.SupportSearchManagement;
import org.opencps.api.controller.util.DossierUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierSync;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierSyncLocalServiceUtil;
import org.opencps.kernel.prop.PropKeys;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.impl.ApplicantLocalServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SupportSearchManagementImpl implements SupportSearchManagement {

    private static final int DICH_VU_CONG = 1;
    private static final int MOT_CUA = 2;
    private static final String SERVER_NO_DVC = "SERVER_DVC";
    private static final String PROTOCOL = "API_SYNC";
    private static final String REFUID = "refUid";

    private static final String EMAIL = "email";
    private static final String DATA = "data";

    @Override
    public Response getSupportSearchDossiers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user, ServiceContext serviceContext, String dossierId, Boolean isCallAgain, String referenceUid) {
        if(Validator.isNull(isCallAgain)){
            isCallAgain = true;
        }
        Integer type = null;
        String liferay_home = PropsUtil.get("liferay.home");
        if(liferay_home.contains("dvc")){
            type = DICH_VU_CONG;
        } else if(liferay_home.contains("mcdt")) {
            type = MOT_CUA;
        }

        JSONObject response = JSONFactoryUtil.createJSONObject();
        long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

        if(type==DICH_VU_CONG) {
            try {
                if(Validator.isNull(referenceUid)){
                    response.put(SupportSearchConstants.HO_SO_DVC, supportSearchDVC(groupId, dossierId));
                } else {
                    response.put(SupportSearchConstants.HO_SO_DVC, supportSearchDVC(groupId, referenceUid));
                }

                if(isCallAgain){
                    Dossier dossier = DossierUtils.getDossierNew(dossierId, groupId);
                    ServerConfig serverConfig = ServerConfigLocalServiceUtil.getByServerNO_PROTOCOL(dossier.getServerNo(), PROTOCOL, dossier.getGroupId());

                    JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());

                    String url = config.get("url").toString() + "/supportSearch/"
                            + dossier.getDossierId() + StringPool.QUESTION +StringPool.AMPERSAND+"isCallAgain"+StringPool.EQUAL+"false"+StringPool.AMPERSAND+REFUID+StringPool.EQUAL+dossier.getReferenceUid();

                    JSONObject responeUrl = sendRequestToURL(url, config.getLong("groupId"));
                    JSONObject hoSoMCDT = JSONFactoryUtil.createJSONObject();

                    if(Validator.isNotNull(responeUrl)){
                        hoSoMCDT = JSONFactoryUtil.createJSONObject(responeUrl.get(SupportSearchConstants.HO_SO_MCDT).toString());
                    }

                    response.put(SupportSearchConstants.HO_SO_MCDT, hoSoMCDT);
                }
            } catch (Exception e){
                _log.error(e);
                return BusinessExceptionImpl.processException(e);
            }
        } else if(type==MOT_CUA) {
            try {
                if(Validator.isNull(referenceUid)){
                    response.put(SupportSearchConstants.HO_SO_MCDT, supportSearchDVC(groupId, dossierId));
                } else {
                    response.put(SupportSearchConstants.HO_SO_MCDT, supportSearchDVC(groupId, referenceUid));
                }

                if(isCallAgain){
                    Dossier dossier = DossierUtils.getDossierNew(dossierId, groupId);
                    ServerConfig serverConfig = ServerConfigLocalServiceUtil.getByServerNO_PROTOCOL(SERVER_NO_DVC, PROTOCOL, dossier.getGroupId());
                    JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());

                    String url = config.get("url").toString() + "/supportSearch/"
                            + dossier.getDossierId() + StringPool.QUESTION + StringPool.AMPERSAND+"isCallAgain"+StringPool.EQUAL+"false"
                            +StringPool.AMPERSAND+REFUID+StringPool.EQUAL+dossier.getReferenceUid();

                    JSONObject responeUrl = sendRequestToURL(url, config.getLong("groupId"));
                    JSONObject hoSoDVC = JSONFactoryUtil.createJSONObject(responeUrl.get(SupportSearchConstants.HO_SO_DVC).toString());

                    response.put(SupportSearchConstants.HO_SO_DVC, hoSoDVC);
                }
            }
            catch (Exception e){
                _log.error(e);
                return BusinessExceptionImpl.processException(e);
            }
        }

        return Response.status(HttpURLConnection.HTTP_OK).entity(response.toJSONString()).build();
    }

    @Override
    public Response supportUser(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user, ServiceContext serviceContext) throws PortalException {
        JSONObject response = JSONFactoryUtil.createJSONObject();

        Date now = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.DATE, -30);
        Date thirtyDayAgo = cal.getTime();
        thirtyDayAgo.setHours(0);
        thirtyDayAgo.setMinutes(0);
        thirtyDayAgo.setSeconds(0);
        try {

            List<Applicant> applicantList = ApplicantLocalServiceUtil.getListApplicationByG_NotEqualZero_CDateToNow(thirtyDayAgo);

            if(applicantList.size() == 0){
                response.put("applicantList", applicantList.size());
                return Response.status(HttpURLConnection.HTTP_OK).entity(response.toJSONString()).build();
            }
            JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
            for(Applicant applicant : applicantList){
                User userQuery = UserLocalServiceUtil.getUser(applicant.getMappingUserId());
                if(Validator.isNull(userQuery.getEmailAddress())){
                    userQuery.setEmailAddress(applicant.getContactEmail());
                    userQuery.setModifiedDate(new Date());
                    UserLocalServiceUtil.updateUser(userQuery);
                    JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
                    jsonObject.put("Email", applicant.getContactEmail());
                    jsonArray.put(jsonObject);
                }

            }
            response.put("Message", "Update complete! " + jsonArray.length() + " Users has NULL EMAIL");
            response.put(DATA, jsonArray);
            return Response.status(HttpURLConnection.HTTP_OK).entity(response.toJSONString()).build();
        } catch (Exception ex){
            _log.error(ex.getMessage());
            return BusinessExceptionImpl.processException(ex);
        }



    }

    public JSONObject SupportSeachContent(long groupId, String key){
        JSONObject body = JSONFactoryUtil.createJSONObject();

        Dossier dossier = DossierUtils.getDossierNew(key, groupId);
        JSONObject dossierObject = SupportSearchConstants.convertDossierToJSONObject(dossier);
        body.put(SupportSearchConstants.INFO, dossierObject);

        List<DossierSync> ListDossierSync = DossierSyncLocalServiceUtil.findByG_DID(dossier.getGroupId(), dossier.getDossierId());
        JSONArray DossierSyncArray = SupportSearchConstants.convertListSyncToArray(ListDossierSync);
        body.put(SupportSearchConstants.DOSSIER_SYNC, DossierSyncArray);

        List<DossierAction> ListDossierAction =
                DossierActionLocalServiceUtil.findByG_DID(dossier.getGroupId(), dossier.getDossierId());

        JSONArray DossierActionArray = SupportSearchConstants.convertListActionToArray(ListDossierAction);
        body.put(SupportSearchConstants.DOSSIER_ACTION, DossierActionArray);

        List<DossierFile> dossierFileList = DossierFileLocalServiceUtil.findByDID_GROUP(dossier.getGroupId(), dossier.getDossierId());

        if(dossierFileList.size() >0) {
            JSONArray dossierFileArray = JSONFactoryUtil.createJSONArray();
            for( DossierFile dossierFile : dossierFileList ){
                JSONObject dossiferFileObject = JSONFactoryUtil.createJSONObject();
                String urlAPI = PropsUtil.get(PropKeys.PORTAL_DOMAIN)+"/o/rest/v2/dossiers/"+dossierFile.getDossierId()+"/files/"+dossierFile.getReferenceUid()+"/preview.pdf";
                dossiferFileObject.put(SupportSearchConstants.URL_DOSSIER_FILE, urlAPI);
                dossierFileArray.put(dossiferFileObject);
            }
            body.put(SupportSearchConstants.DOSSIER_FILE, dossierFileArray);
        } else {
            body.put(SupportSearchConstants.DOSSIER_FILE, JSONFactoryUtil.createJSONArray());
        }
        return body;
    }

    private JSONObject supportSearchDVC(long groupId, String key){

        JSONObject body = JSONFactoryUtil.createJSONObject();
        JSONObject dossierObj = SupportSeachContent(groupId, key);
        body.put(SupportSearchConstants.DOSSIER, dossierObj);
        Dossier dossier = DossierUtils.getDossierNew(key, groupId);

        List<Dossier> DossierBetweenList = DossierLocalServiceUtil.findDossierTransferByORIGIN_NO_ORIGIN_ID_ORIGINALITY(dossier.getDossierNo(), dossier.getDossierId(), null);

        if(DossierBetweenList.size()>0){

            Dossier dossierBetween = DossierBetweenList.get(0);

            JSONObject jsonObject = SupportSeachContent(groupId, String.valueOf(dossierBetween.getDossierId()));

            body.put(SupportSearchConstants.DOSSIER_BETWEEN, jsonObject);

        } else {
            body.put(SupportSearchConstants.DOSSIER_BETWEEN, JSONFactoryUtil.createJSONObject());
        }

        List<Dossier> DossierTransferList = DossierLocalServiceUtil.findDossierTransferByORIGIN_NO_ORIGIN_ID_ORIGINALITY(dossier.getDossierNo(), null, 2);

        if(DossierTransferList.size()>0){

            Dossier dossierTransfer = DossierTransferList.get(0);

            JSONObject jsonObject = SupportSeachContent(groupId, String.valueOf(dossierTransfer.getDossierId()));

            body.put(SupportSearchConstants.DOSSIER_TRANFER, jsonObject);

        } else {
            body.put(SupportSearchConstants.DOSSIER_TRANFER, JSONFactoryUtil.createJSONObject());
        }

        return body;
    }
    private JSONObject sendRequestToURL(String urlAddress, long groupId) throws IOException, JSONException {
        JSONObject myResponse = null;
        URL obj = new URL(urlAddress);
        java.net.HttpURLConnection connection = (java.net.HttpURLConnection) obj.openConnection();
        connection.setRequestProperty("groupId", String.valueOf(groupId));
        connection.setRequestMethod("GET");
        try {
            int responseCode = connection.getResponseCode();

            _log.debug("responseCode = " + responseCode);

            // Thực hiện đọc
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            if(responseCode==200){
                myResponse = JSONFactoryUtil.createJSONObject(response.toString());
            } else {
                myResponse = null;
            }
        } catch(Exception ex) {
            myResponse = null;
        }
        return myResponse;
    }

    Log _log = LogFactoryUtil.getLog(SupportSearchManagementImpl.class);
}
