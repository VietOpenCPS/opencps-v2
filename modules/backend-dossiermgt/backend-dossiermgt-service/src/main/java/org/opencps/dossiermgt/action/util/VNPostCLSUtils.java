package org.opencps.dossiermgt.action.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.dossiermgt.rest.utils.SyncServerTerm;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Base64;
import java.util.Date;

public class VNPostCLSUtils {
    public static String getPostalVNPost( Long groupId, String serverCode, String key, String postalCode) {


        try {
            String serverCodeFind = Validator.isNotNull(serverCode) ? serverCode : "THONG_TIN_MA_BUU_GUI_VNPOST";

            ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(groupId, serverCodeFind);
            StringBuilder sb = new StringBuilder();
            _log.info("SERVER PROXY: " + sc.getConfigs());
            if (sc != null) {
                JSONObject configObj = JSONFactoryUtil.createJSONObject(sc.getConfigs());
                String apiUrl;
                String serverUrl = StringPool.BLANK;

                URL urlVal = null;
                serverUrl = configObj.getString(SyncServerTerm.SERVER_URL);
                apiUrl = serverUrl + key + StringPool.FORWARD_SLASH + postalCode;
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

    public static String insertCLS(long groupId, String serverCode, long dossierId, String serviceCode,
                                   String serviceName, String dossierNo, long fees, String applicantName,
                                   String delegateName, String applicantIdNo, String address, Date receiveDate, String postalAddress) {

        try {
            String serverCodeFind = Validator.isNotNull(serverCode) ? serverCode : "VNPOST_CLS";
            ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(groupId, serverCodeFind);
            StringBuilder sb = new StringBuilder();

            if (sc != null) {
                String serverUrl = StringPool.BLANK;
                JSONObject configObj = JSONFactoryUtil.createJSONObject(sc.getConfigs());
                JSONObject jsonObject = JSONFactoryUtil.createJSONObject(configObj.getString(ServerConfigTerm.NHAN_THONG_BAO_KET_QUA));
                serverUrl = jsonObject.getString(SyncServerTerm.SERVER_URL);

                String userName = jsonObject.getString(SyncServerTerm.SERVER_USERNAME);
                String password = jsonObject.getString(SyncServerTerm.SERVER_SECRET);
                String basicAuth = "Basic " + Base64.getEncoder().encodeToString((userName + ":" + password).getBytes());
                _log.debug("basicAuth "+basicAuth);

                URL urlVoid = new URL(serverUrl);
                JSONObject jsonBody = JSONFactoryUtil.createJSONObject();

                jsonBody.put(SyncServerTerm.SERVER_USERNAME, configObj.getString(SyncServerTerm.SERVER_USERNAME));
                jsonBody.put(SyncServerTerm.SERVER_SECRET, configObj.getString(SyncServerTerm.SERVER_SECRET));
                jsonBody.put("dossierId",dossierId);
                jsonBody.put("MaThuTuc",serviceCode);
                jsonBody.put("TenThuTuc",serviceName);
                jsonBody.put("MaHoSo",dossierNo);
                jsonBody.put("LePhi",fees);
                jsonBody.put("ChuHoSo",applicantName);
                jsonBody.put("NguoiNop",delegateName);
                jsonBody.put("CMT",applicantIdNo);
                jsonBody.put("DiaChi",address);
                jsonBody.put("NgayTiepNhan",APIDateTimeUtils.convertDateToString(receiveDate, APIDateTimeUtils._NORMAL_DATE));
                jsonBody.put("DiaChiBC",postalAddress);

                _log.debug("POST DATA: " + jsonBody.toString());
                java.net.HttpURLConnection conn = (java.net.HttpURLConnection) urlVoid.openConnection();

                conn.setRequestProperty ("Authorization", basicAuth);
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
