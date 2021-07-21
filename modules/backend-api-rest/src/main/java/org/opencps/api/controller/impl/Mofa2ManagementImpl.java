package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.*;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import org.opencps.api.controller.Mofa2Management;
import org.opencps.api.dossier.model.DossierInputModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.rest.utils.SyncServerTerm;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;
import org.apache.commons.httpclient.util.HttpURLConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;


public class Mofa2ManagementImpl implements Mofa2Management {
    private static Log _log = LogFactoryUtil.getLog(Mofa2ManagementImpl.class.getName());

    public static final String SEARCH ="search";
    public static final String CREATE ="create";
    public static final String CONNECT_MOFA2 ="CONNECT_MOFA2";
    @Override
    public Response createMofa2(HttpServletRequest request, HttpHeaders header, ServiceContext serviceContext, long dossierId) {
        try {
            String result = StringPool.BLANK;
            long groupId = 0L;
            DossierFile dossierFile = null;
            List<DossierFile> dossierFiles = DossierFileLocalServiceUtil.getDossierFileByDID_DPNO(dossierId, "TP01", false);
             dossierFile = dossierFiles.get(0);
            JSONObject fileJSON = JSONFactoryUtil.createJSONObject(dossierFile.getFormData());
            JSONArray thanhvienArr = fileJSON.getJSONArray("thanh_vien_doan");
            Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
            if(Validator.isNotNull(dossier)){
                groupId = dossier.getGroupId();
                result = insertMofa2(groupId, dossier, CONNECT_MOFA2, thanhvienArr);
            }
            _log.info("Result: " + result);
            return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
        }catch (Exception e){
            e.getMessage();
        }
        return null;
    }
    public static final String CA_NHAN = "CaNhan";
    public static final String PHEP_NHAP_CANH = "PhepNhapCanh";
    public static final String HS_THI_THUC_NOI = "HsThiThucNoi";
    public static String insertMofa2(long groupId, Dossier dossier, String serverCode, JSONArray arrayFile){
        String serverUrl = StringPool.BLANK;
        try {
            _log.debug("ServerCode :  " + serverCode + " groupId : " + groupId);
            ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(groupId, serverCode);
            StringBuilder sb = new StringBuilder();
            if (sc != null && arrayFile != null) {
                JSONObject configObj = JSONFactoryUtil.createJSONObject(sc.getConfigs());
                _log.info("Config: " + JSONFactoryUtil.looseSerialize(configObj));
                PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByG_DID(groupId, dossier.getDossierId());
                serverUrl = configObj.getString(CREATE);
                URL urlVoid = new URL(serverUrl);

                JSONObject jsonData = JSONFactoryUtil.createJSONObject();
                JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
                JSONObject jsonBody = JSONFactoryUtil.createJSONObject();

                // Phép nhập cảnh
                JSONArray hsThiThucArray = JSONFactoryUtil.createJSONArray();
                /** Thanh Vien */
                jsonBody.put("Ten", org.json.JSONObject.NULL);
                jsonBody.put("ID", org.json.JSONObject.NULL);
                jsonBody.put("ReferenceUid", dossier.getDossierId());
                jsonBody.put("DossierId", dossier.getDossierId());
                jsonBody.put("Ten_Kd",org.json.JSONObject.NULL);
                jsonBody.put("Ten_Co_Quan", org.json.JSONObject.NULL);
                jsonBody.put("So_Dien_Thoai", org.json.JSONObject.NULL);
                jsonBody.put("So_Dien_Thoai_Cq", org.json.JSONObject.NULL);
                jsonBody.put("So_Luong_Hc", org.json.JSONObject.NULL);
                jsonBody.put("So_Luong_To_Khai", org.json.JSONObject.NULL);
                jsonBody.put("Giay_To_Kem_Theo", org.json.JSONObject.NULL);
                jsonBody.put("Noi_Hen_Tra_Kq_Id",org.json.JSONObject.NULL);
                jsonBody.put("Noi_Hen_Tra_Kq", org.json.JSONObject.NULL);
                jsonBody.put("Ngay_Hen_Tra", org.json.JSONObject.NULL);
                jsonBody.put("Ngay_Huy", org.json.JSONObject.NULL);
                jsonBody.put("Nguoi_Huy", org.json.JSONObject.NULL);
                jsonBody.put("Ly_Do_Huy", org.json.JSONObject.NULL);
                jsonBody.put("Ghi_Chu", org.json.JSONObject.NULL);
                jsonBody.put("LOAI", org.json.JSONObject.NULL);
                jsonBody.put("Bl_Ten_Cq_Ca_Nhan", org.json.JSONObject.NULL);
                jsonBody.put("Bl_Dia_Chi_Id", org.json.JSONObject.NULL);
                jsonBody.put("Bl_Dia_Chi_Chi_Tiet", org.json.JSONObject.NULL);
                jsonBody.put("Bl_So_Dien_Thoai", org.json.JSONObject.NULL);
                jsonBody.put("So_Cv_Den", org.json.JSONObject.NULL);
                jsonBody.put("Ngay_Cv_Den", org.json.JSONObject.NULL);
                jsonBody.put("Loai_Cv_Den", org.json.JSONObject.NULL);
                jsonBody.put("Noi_Gui_Cv_Den", org.json.JSONObject.NULL);
                jsonBody.put("Noi_Dung_Tra_Loi", org.json.JSONObject.NULL);
                jsonBody.put("Ngay_Luu_Ho_So", org.json.JSONObject.NULL);
                jsonBody.put("Nguoi_Luu_Ho_So", org.json.JSONObject.NULL);
                jsonBody.put("So_Giay_Hen", org.json.JSONObject.NULL);
                jsonBody.put("Noi_Gui_Cv_Den_Id", org.json.JSONObject.NULL);
                jsonBody.put("So_Bien_Nhan", Validator.isNotNull(dossier.getDossierCounter()) ? dossier.getDossierCounter() : org.json.JSONObject.NULL);
                jsonBody.put("Du_Kien_Thu", Validator.isNotNull(paymentFile.getPaymentAmount()) ? paymentFile.getPaymentAmount() : org.json.JSONObject.NULL);
                jsonBody.put("Don_Vi_Tien_Te", "$");
                jsonBody.put("Nguoi_Tao", Validator.isNotNull(dossier.getUserName()) ? dossier.getUserName() : org.json.JSONObject.NULL);
                jsonBody.put("Ngay_Tao", APIDateTimeUtils.convertDateToString(dossier.getCreateDate(), APIDateTimeUtils._MONTH_DAY_YEAR_DATE));
                jsonBody.put("Nguoi_Nhan", Validator.isNotNull(dossier.getApplicantName()) ? dossier.getApplicantName() : org.json.JSONObject.NULL);
                jsonBody.put("Ngay_Nhan", APIDateTimeUtils.convertDateToString(dossier.getReceiveDate(), APIDateTimeUtils._MONTH_DAY_YEAR_DATE));
                jsonBody.put("so_ho_so", arrayFile.length());
                if (arrayFile != null) {
                    for (int i = 0; i < arrayFile.length(); i++) {
                        JSONObject thanhvienJSON = arrayFile.getJSONObject(i);
                        _log.info("thanhvienJSON" + JSONFactoryUtil.looseSerialize(thanhvienJSON));
                        JSONObject hsThiThucJson = JSONFactoryUtil.createJSONObject();
                        try {
                            //Cá Nhân
                            JSONObject caNhan = thanhvienJSON.getJSONObject(CA_NHAN);
                            if (caNhan != null) {
                                hsThiThucJson.put("CaNhan", caNhan);
                            }

                            //PhepNhapCanh
                            JSONObject phepNhapCanh = thanhvienJSON.getJSONObject(PHEP_NHAP_CANH);
                            if (phepNhapCanh != null) {
                                hsThiThucJson.put("PhepNhapCanh", phepNhapCanh);
                            }else{
                                hsThiThucJson.put("PhepNhapCanh", org.json.JSONObject.NULL);
                            }
                            //Body HsThiThuc
                            if (thanhvienJSON != null) {
                                hsThiThucJson = hsThiThuc(thanhvienJSON, hsThiThucJson, arrayFile.length());
                            }

                            //HS Anh
                            JSONArray hsAnh = thanhvienJSON.getJSONArray("HsAnh");
                            if (hsAnh != null) {
                                hsThiThucJson.put("HsAnh", hsAnh);
                            }else{
                                hsThiThucJson.put("HsAnh", org.json.JSONObject.NULL);
                            }

                            //HS tre em di cung
                            JSONArray hsTreEm = thanhvienJSON.getJSONArray("HsTreEmDiCung");
                            if (hsTreEm != null) {
                                hsThiThucJson.put("HsTreEmDiCung", hsTreEm);
                            }

                            //Hs thân nhân
                            JSONArray hsThanNhan = thanhvienJSON.getJSONArray("HsThanNhan");
                            if (hsThanNhan != null) {
                                hsThiThucJson.put("HsThanNhan", hsThanNhan);
                            }
                            //HS Thi Thuc Noi
                            JSONObject hsThiThucNoi = thanhvienJSON.getJSONObject(HS_THI_THUC_NOI);
                            if (hsThiThucNoi != null) {
                                hsThiThucJson.put("HsThiThucNoi", hsThiThucNoi);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        hsThiThucArray.put(hsThiThucJson);
                        jsonBody.put("HsThiThucs", hsThiThucArray);
                    }
                    jsonArray.put(jsonBody);
                    jsonData.put("data", jsonArray);
                }
                _log.info("JSON DATA : " + jsonData.toString());


                java.net.HttpURLConnection conn = (java.net.HttpURLConnection) urlVoid.openConnection();
                conn.setRequestMethod(HttpMethod.POST);
                conn.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
                conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
                conn.setRequestProperty(HttpHeaders.CONTENT_LENGTH, StringPool.BLANK + Integer.toString(jsonData.toString().getBytes().length));
                conn.setUseCaches(false);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStream osLogin = conn.getOutputStream();
                osLogin.write(jsonData.toString().getBytes());
                osLogin.close();

                BufferedReader brf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                int cp;
                while ((cp = brf.read()) != -1) {
                    sb.append((char) cp);
                }
                return sb.toString();
            }
        }catch (Exception e){
            e.getMessage();
        }
        return null;
    }

    private static JSONObject hsThiThuc(JSONObject thanhvienJSON, JSONObject hsThiThucJson, int arrayLength) {
        if(Validator.isNotNull(thanhvienJSON)){
                Iterator<String> keys = thanhvienJSON.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    String value = thanhvienJSON.getString(key);
                    if (Validator.isNotNull(value)) {
                        if(key.equals("so_ho_so")){
                            hsThiThucJson.put(key, arrayLength);
                        }else {
                            if(!key.equals(CA_NHAN) && !key.equals(HS_THI_THUC_NOI) && !key.equals(PHEP_NHAP_CANH)) {
                                hsThiThucJson.put(key, value);
                            }
                        }
                    }else{
                        hsThiThucJson.put(key, org.json.JSONObject.NULL);
                    }
                }
            }
//        hsThiThucJson.put("Duyet_Gia_Tri_Tt", Validator.isNotNull(thanhvienJSON.getString("xin_ky_hieu_tt")) ? thanhvienJSON.getString("xin_ky_hieu_tt"): org.json.JSONObject.NULL); // Số lần của thông tin duyệt
//        hsThiThucJson.put("Duyet_Tt_Tu_Ngay", Validator.isNotNull(thanhvienJSON.getString("Xin_Tt_Tu_Ngay")) ? thanhvienJSON.getString("Xin_Tt_Tu_Ngay"): org.json.JSONObject.NULL); // Duyệt nhập cảnh từ ngày
//        hsThiThucJson.put("Duyet_Tt_Den_Ngay", Validator.isNotNull(thanhvienJSON.getString("Xin_Tt_Den_Ngay")) ? thanhvienJSON.getString("Xin_Tt_Den_Ngay"): org.json.JSONObject.NULL); // Duyệt nhập cảnh đến ngày
        return hsThiThucJson;
    }

    @Override
    public Response getKTHoChieu(HttpServletRequest request, HttpHeaders header, ServiceContext serviceContext,
                                 long ktHoChieu, String soHC, String soPhep) {
        try {
            JSONObject result = JSONFactoryUtil.createJSONObject();
            HashMap<String, String> properties = new HashMap<String, String>();
            Map<String, Object> params = new HashMap<String, Object>();
            long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
            String endPointSearch = StringPool.BLANK;
            String username = StringPool.BLANK;
            String pwd = StringPool.BLANK;

            ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(groupId, CONNECT_MOFA2);
            if(Validator.isNotNull(sc)) {
                JSONObject configObj = JSONFactoryUtil.createJSONObject(sc.getConfigs());

                endPointSearch = configObj.getString(SEARCH);
                params.put("KtHoChieu", ktHoChieu);
                params.put("SoHc", soHC);
                params.put("SoPhep", soPhep);

                 result = callPostAPI(HttpMethod.POST, MediaType.APPLICATION_JSON, endPointSearch,
                        properties, params, username, pwd);
                return Response.status(HttpURLConnection.HTTP_OK).entity(result.toString()).build();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject callPostAPI(String httpMethod, String accept, String urlPath, HashMap<String, String> properties,
                                  Map<String, Object> params, String username, String password) {

        JSONObject response = JSONFactoryUtil.createJSONObject();

        java.net.HttpURLConnection conn = null;

        BufferedReader br = null;

        try {

            URL url = new URL(urlPath);

            conn = (java.net.HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(OpenCPSConfigUtil.getRestConnectionTimeout());
            conn.setReadTimeout(OpenCPSConfigUtil.getRestReadTimeout());
            conn.setRequestMethod(httpMethod);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setRequestProperty(ConstantUtils.VALUE_ACCEPT, accept);

            if (Validator.isNotNull(username) && Validator.isNotNull(password)) {
                String authString = username + StringPool.COLON + password;

                String authStringEnc = new String(Base64.getEncoder().encodeToString(authString.getBytes()));

                conn.setRequestProperty(ConstantUtils.VALUE_AUTHORIZATION, ConstantUtils.VALUE_BASIC + authStringEnc);
            }

            if (!properties.isEmpty()) {
                for (Map.Entry m : properties.entrySet()) {
                    conn.setRequestProperty(m.getKey().toString(), m.getValue().toString());
                }
            }

            StringBuilder postData = new StringBuilder();

            for (Map.Entry<String, Object> param : params.entrySet()) {
                if (postData.length() != 0)
                    postData.append(StringPool.AMPERSAND.charAt(0));
                postData.append(java.net.URLEncoder.encode(param.getKey(), ConstantUtils.UTF_8));
                postData.append(StringPool.EQUAL.charAt(0));
                postData.append(java.net.URLEncoder.encode(String.valueOf(param.getValue()), ConstantUtils.UTF_8));
            }

            byte[] postDataBytes = postData.toString().getBytes(ConstantUtils.UTF_8);

            conn.setRequestProperty(ConstantUtils.CONTENT_LENGTH, String.valueOf(postDataBytes.length));

            conn.getOutputStream().write(postDataBytes);

            br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;

            StringBuilder sb = new StringBuilder();

            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            response = JSONFactoryUtil.createJSONObject(sb.toString());

            conn.disconnect();

        } catch (MalformedURLException e) {
            _log.error("Can't invoke api " + urlPath);
        } catch (IOException e) {
            _log.error("Can't invoke api " + urlPath);
        } catch (JSONException e) {
            _log.error("Can't invoke api " + urlPath);
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    _log.error(e);
                }
            }

        }
        return response;
    }
}
