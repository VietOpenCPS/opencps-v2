package org.opencps.dossiermgt.action.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.rest.utils.SyncServerTerm;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

public class VNPostCLSUtils {
    public static String getParcelVNPost( Long groupId, String serverCode, String key, String parcelCode) {


        try {
            String serverCodeFind = Validator.isNotNull(serverCode) ? serverCode : "THONG_TIN_MA_BUU_GUI_VNPOST";

            ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(groupId, serverCodeFind);
            StringBuilder sb = new StringBuilder();
            _log.debug("SERVER PROXY: " + sc.getConfigs());
            if (sc != null) {
                JSONObject configObj = JSONFactoryUtil.createJSONObject(sc.getConfigs());
                String apiUrl;
                String serverUrl = StringPool.BLANK;

                URL urlVal = null;
                serverUrl = configObj.getString(SyncServerTerm.SERVER_URL);
                apiUrl = serverUrl + key + StringPool.FORWARD_SLASH + parcelCode;
                urlVal = new URL(apiUrl);

                java.net.HttpURLConnection conn = (java.net.HttpURLConnection) urlVal.openConnection();

                conn.setRequestProperty(Field.GROUP_ID, groupId.toString());
                conn.setRequestMethod(HttpMethod.GET);
                conn.setRequestProperty(HttpHeaders.ACCEPT, ConstantUtils.CONTENT_TYPE_JSON);

                BufferedReader brf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                int cp;
                while ((cp = brf.read()) != -1) {
                    sb.append((char) cp);
                }
               return sb.toString();
            }
            return null;
        } catch (Exception e) {
            _log.debug(e);
            return null;
        }

    }

    public static String insertCLS( HttpHeaders header, String serverCode, int dossierId, String serviceCode,
                                     String serviceName, String dossierNo, int fees, String applicantName,
                                     String delegateName, String applicantIdNo, String address, String postalAddress) {

        long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

        try {
            String serverCodeFind = Validator.isNotNull(serverCode) ? serverCode : "NHAN_THONG_BAO_KET_QUA";

            ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(groupId, serverCodeFind);
            StringBuilder sb = new StringBuilder();
            _log.debug("SERVER PROXY: " + sc.getConfigs());
            if (sc != null) {
                String serverUrl = StringPool.BLANK;

                JSONObject configObj = JSONFactoryUtil.createJSONObject(sc.getConfigs());
                serverUrl = configObj.getString(SyncServerTerm.SERVER_URL);
                URL urlVoid = new URL(serverUrl);

                JSONObject jsonBody = JSONFactoryUtil.createJSONObject();

                jsonBody.put(SyncServerTerm.SERVER_USERNAME, configObj.getString(SyncServerTerm.SERVER_USERNAME));
                jsonBody.put(SyncServerTerm.SERVER_SECRET, configObj.getString(SyncServerTerm.SERVER_SECRET));

                jsonBody.put("dossierId",dossierId);
                jsonBody.put("serviceCode",serviceCode);
                jsonBody.put("serviceName",serviceName);
                jsonBody.put("dossierNo",dossierNo);
                jsonBody.put("fees",fees);
                jsonBody.put("applicantName",applicantName);
                jsonBody.put("delegateName",delegateName);
                jsonBody.put("applicantIdNo",applicantIdNo);
                jsonBody.put("address",address);
                jsonBody.put("postalAddress",postalAddress);

                _log.debug("POST DATA: " + jsonBody.toString());

                java.net.HttpURLConnection conn = (java.net.HttpURLConnection) urlVoid.openConnection();

                conn.setRequestMethod(HttpMethod.POST);
                conn.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
                conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
                conn.setRequestProperty("Content-Length",StringPool.BLANK + Integer.toString(jsonBody.toString().getBytes().length));
                conn.setUseCaches(false);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                _log.debug("POST DATA: " + jsonBody.toString());
                OutputStream osLogin = conn.getOutputStream();
                osLogin.write(jsonBody.toString().getBytes());
                osLogin.close();

                BufferedReader brf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                int cp;
                while ((cp = brf.read()) != -1) {
                    sb.append((char) cp);
                }
                return sb.toString();
            }
            return null;
        } catch (Exception e) {
            _log.debug(e);
            return null;
        }

    }
    private static Log _log = LogFactoryUtil.getLog(DossierActionUtils.class.getName());

}
