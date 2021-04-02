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
                        JSONObject hsThiThucJson = JSONFactoryUtil.createJSONObject();
                        try {
                            //Cá Nhân
                            JSONObject caNhan = thanhvienJSON.getJSONObject("CaNhan");
                            if (caNhan != null) {
                                hsThiThucJson.put("CaNhan", caNhan);
                            }

                            //PhepNhapCanh
                            JSONObject phepNhapCanh = thanhvienJSON.getJSONObject("PhepNhapCanh");
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
                            JSONObject hsThiThucNoi = thanhvienJSON.getJSONObject("HsThiThucNoi");
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
        hsThiThucJson.put("Id", Validator.isNotNull(thanhvienJSON.getString("Id")) ? thanhvienJSON.getString("Id") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Ca_Nhan_Id", Validator.isNotNull(thanhvienJSON.getString("Ca_Nhan_Id"))? thanhvienJSON.getString("Ca_Nhan_Id") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Quoc_Tich_Hn_Id", Validator.isNotNull(thanhvienJSON.getString("Quoc_Tich_Hn_Id")) ? thanhvienJSON.getString("Quoc_Tich_Hn_Id") : org.json.JSONObject.NULL);
        hsThiThucJson.put("So_Ho_Chieu", Validator.isNotNull(thanhvienJSON.getString("So_Ho_Chieu")) ? thanhvienJSON.getString("So_Ho_Chieu") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Ma_To_Khai", Validator.isNotNull(thanhvienJSON.getString("Ma_To_Khai")) ? thanhvienJSON.getString("Ma_To_Khai") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Buoc_Xl", Validator.isNotNull(thanhvienJSON.getString("Buoc_Xl")) ? thanhvienJSON.getString("Buoc_Xl"): org.json.JSONObject.NULL);
        hsThiThucJson.put("So_Bien_Nhan", Validator.isNotNull(thanhvienJSON.getString("So_Bien_Nhan")) ? thanhvienJSON.getString("So_Bien_Nhan") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Ten", Validator.isNotNull(thanhvienJSON.getString("Ten")) ? thanhvienJSON.getString("Ten") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Ten_Kd", Validator.isNotNull(thanhvienJSON.getString("Ten_Kd")) ? thanhvienJSON.getString("Ten_Kd") : org.json.JSONObject.NULL);
        hsThiThucJson.put("So_Dien_Thoai", Validator.isNotNull(thanhvienJSON.getString("So_Dien_Thoai")) ? thanhvienJSON.getString("So_Dien_Thoai") : org.json.JSONObject.NULL);
        hsThiThucJson.put("So_Dien_Thoai_Cq", Validator.isNotNull(thanhvienJSON.getString("So_Dien_Thoai_Cq")) ? thanhvienJSON.getString("So_Dien_Thoai_Cq") : org.json.JSONObject.NULL);
        hsThiThucJson.put("So_Luong_Hc", Validator.isNotNull(thanhvienJSON.getString("So_Luong_Hc")) ? thanhvienJSON.getString("So_Luong_Hc") : org.json.JSONObject.NULL);
        hsThiThucJson.put("So_Luong_To_Khai", Validator.isNotNull(thanhvienJSON.getString("So_Luong_To_Khai")) ? thanhvienJSON.getString("So_Luong_To_Khai") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Giay_To_Kem_Theo", Validator.isNotNull(thanhvienJSON.getString("Giay_To_Kem_Theo")) ? thanhvienJSON.getString("Giay_To_Kem_Theo") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Noi_Hen_Tra_Kq_Id", Validator.isNotNull(thanhvienJSON.getString("Noi_Hen_Tra_Kq_Id")) ? thanhvienJSON.getString("Noi_Hen_Tra_Kq_Id") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Noi_Hen_Tra_Kq", Validator.isNotNull(thanhvienJSON.getString("Noi_Hen_Tra_Kq")) ? thanhvienJSON.getString("Noi_Hen_Tra_Kq") : org.json.JSONObject.NULL );
        hsThiThucJson.put("Ngay_Hen_Tra", Validator.isNotNull(thanhvienJSON.getString("Ngay_Hen_Tra")) ? thanhvienJSON.getString("Ngay_Hen_Tra") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Du_Kien_Thu", Validator.isNotNull(thanhvienJSON.getString("Du_Kien_Thu")) ? thanhvienJSON.getString("Du_Kien_Thu") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Don_Vi_Tien_Te", Validator.isNotNull(thanhvienJSON.getString("Don_Vi_Tien_Te")) ? thanhvienJSON.getString("Don_Vi_Tien_Te") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Ngay_Huy", Validator.isNotNull(thanhvienJSON.getString("Ngay_Huy")) ? thanhvienJSON.getString("Ngay_Huy") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Nguoi_Huy", Validator.isNotNull(thanhvienJSON.getString("Nguoi_Huy")) ? thanhvienJSON.getString("Nguoi_Huy") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Ly_Do_Huy", Validator.isNotNull(thanhvienJSON.getString("Ly_Do_Huy")) ? thanhvienJSON.getString("Ly_Do_Huy") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Ghi_Chu", Validator.isNotNull(thanhvienJSON.getString("Ghi_Chu")) ? thanhvienJSON.getString("Ghi_Chu"): org.json.JSONObject.NULL);
        hsThiThucJson.put("Nguoi_Tao", Validator.isNotNull(thanhvienJSON.getString("Nguoi_Tao")) ? thanhvienJSON.getString("Nguoi_Tao") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Ngay_Tao", Validator.isNotNull(thanhvienJSON.getString("Ngay_Tao")) ? thanhvienJSON.getString("Ngay_Tao") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Ngay_Lap_Phieu", Validator.isNotNull(thanhvienJSON.getString("Ngay_Lap_Phieu")) ? thanhvienJSON.getString("Ngay_Lap_Phieu") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Nguoi_Sua_Cuoi", Validator.isNotNull(thanhvienJSON.getString("Nguoi_Sua_Cuoi")) ? thanhvienJSON.getString("Nguoi_Sua_Cuoi") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Ngay_Sua_Cuoi", Validator.isNotNull(thanhvienJSON.getString("Ngay_Sua_Cuoi")) ? thanhvienJSON.getString("Ngay_Sua_Cuoi") :  org.json.JSONObject.NULL);
        hsThiThucJson.put("LOAI", Validator.isNotNull(thanhvienJSON.getString("LOAI")) ? thanhvienJSON.getString("LOAI") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Nguoi_Nhan", Validator.isNotNull(thanhvienJSON.getString("Nguoi_Nhan")) ? thanhvienJSON.getString("Nguoi_Nhan") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Ngay_Nhan", Validator.isNotNull(thanhvienJSON.getString("Ngay_Nhan")) ? thanhvienJSON.getString("Ngay_Nhan") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Bl_Ten_Cq_Ca_Nhan", Validator.isNotNull(thanhvienJSON.getString("Bl_Ten_Cq_Ca_Nhan")) ? thanhvienJSON.getString("Bl_Ten_Cq_Ca_Nhan") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Bl_Dia_Chi_Id", Validator.isNotNull(thanhvienJSON.getString("Bl_Dia_Chi_Id")) ? thanhvienJSON.getString("Bl_Dia_Chi_Id") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Bl_Dia_Chi_Chi_Tiet", Validator.isNotNull(thanhvienJSON.getString("Bl_Dia_Chi_Chi_Tiet")) ? thanhvienJSON.getString("Bl_Dia_Chi_Chi_Tiet") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Bl_So_Dien_Thoai", Validator.isNotNull(thanhvienJSON.getString("Bl_So_Dien_Thoai")) ? thanhvienJSON.getString("Bl_So_Dien_Thoai") : org.json.JSONObject.NULL);
        hsThiThucJson.put("So_Cv_Den", Validator.isNotNull(thanhvienJSON.getString("So_Cv_Den")) ? thanhvienJSON.getString("So_Cv_Den") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Ngay_Cv_Den", Validator.isNotNull(thanhvienJSON.getString("Ngay_Cv_Den")) ? thanhvienJSON.getString("Ngay_Cv_Den") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Loai_Cv_Den", Validator.isNotNull(thanhvienJSON.getString("Loai_Cv_Den")) ? thanhvienJSON.getString("Loai_Cv_Den") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Noi_Gui_Cv_Den", Validator.isNotNull(thanhvienJSON.getString("Noi_Gui_Cv_Den")) ? thanhvienJSON.getString("Noi_Gui_Cv_Den") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Noi_Dung_Tra_Loi", Validator.isNotNull(thanhvienJSON.getString("Noi_Dung_Tra_Loi")) ? thanhvienJSON.getString("Noi_Dung_Tra_Loi") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Ngay_Luu_Ho_So", Validator.isNotNull(thanhvienJSON.getString("Ngay_Luu_Ho_So")) ? thanhvienJSON.getString("Ngay_Luu_Ho_So") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Nguoi_Luu_Ho_So", Validator.isNotNull(thanhvienJSON.getString("Nguoi_Luu_Ho_So")) ? thanhvienJSON.getString("Nguoi_Luu_Ho_So") : org.json.JSONObject.NULL);
        hsThiThucJson.put("so_ho_so", arrayLength);
        hsThiThucJson.put("So_Giay_Hen", Validator.isNotNull(thanhvienJSON.getString("So_Giay_Hen")) ? thanhvienJSON.getString("So_Giay_Hen") : org.json.JSONObject.NULL);
        hsThiThucJson.put("Noi_Gui_Cv_Den_Id", Validator.isNotNull(thanhvienJSON.getString("Noi_Gui_Cv_Den_Id")) ? thanhvienJSON.getString("Noi_Gui_Cv_Den_Id"): org.json.JSONObject.NULL);
        hsThiThucJson.put("Loai_To_Khai", Validator.isNotNull(thanhvienJSON.getString("Loai_To_Khai")) ? thanhvienJSON.getString("Loai_To_Khai"): org.json.JSONObject.NULL);
        hsThiThucJson.put("Noi_Nop_Hs_Id", Validator.isNotNull(thanhvienJSON.getString("Noi_Nop_Hs_Id")) ? thanhvienJSON.getString("Noi_Nop_Hs_Id"): org.json.JSONObject.NULL);
        return hsThiThucJson;
    }

    private static JSONObject hsPhepNhapCanh(JSONObject phepNhapCanh ) {
        JSONObject hsThiThucJson = JSONFactoryUtil.createJSONObject();
        JSONArray arrayPhepNhapCanhChiTiet = JSONFactoryUtil.createJSONArray();
        JSONArray arrayChiTiet = JSONFactoryUtil.createJSONArray();
        JSONObject jsonPhepNhapCanh = JSONFactoryUtil.createJSONObject();
        JSONObject jsonChiTiet = JSONFactoryUtil.createJSONObject();

        JSONArray phepNhapCanhChiTiet = phepNhapCanh.getJSONArray("PhepNhapCanhChiTiet");
        JSONObject object = phepNhapCanhChiTiet.getJSONObject(0);
        try {
            //PhepNhapCanhChiTiet
            jsonChiTiet.put("Id", object.getString("Id"));
            jsonChiTiet.put("Phep_Nhap_Canh_Id", object.getString("Phep_Nhap_Canh_Id"));
            jsonChiTiet.put("Ten", object.getString("Ten"));
            jsonChiTiet.put("Ten_Kd", object.getString("Ten_Kd"));
            jsonChiTiet.put("Ngay_Sinh", object.getString("Ngay_Sinh"));
            jsonChiTiet.put("Def_Ngay_Sinh", object.getString("Def_Ngay_Sinh"));
            jsonChiTiet.put("Quoc_Tich_Id", object.getString("Quoc_Tich_Id"));
            jsonChiTiet.put("So_Ho_Chieu", object.getString("So_Ho_Chieu"));
            jsonChiTiet.put("Duoc_Nc_Tu_Ngay", object.getString("Duoc_Nc_Tu_Ngay"));
            jsonChiTiet.put("Duoc_Nc_Den_Ngay", object.getString("Duoc_Nc_Den_Ngay"));
            jsonChiTiet.put("Gia_Tri_Tt", object.getString("Gia_Tri_Tt"));
            jsonChiTiet.put("Ngay_Tao", object.getString("Ngay_Tao"));
            jsonChiTiet.put("Nguoi_Tao", object.getString("Nguoi_Tao"));
            jsonChiTiet.put("Muc_Dich_Id", object.getString("Muc_Dich_Id"));
            jsonChiTiet.put("Ky_Hieu_Tt", object.getString("Ky_Hieu_Tt"));
            jsonChiTiet.put("Hs_Thi_Thuc_Id", object.getString("Hs_Thi_Thuc_Id"));
            jsonChiTiet.put("So_Fax", object.getString("So_Fax"));
            jsonChiTiet.put("Ngay_Fax", object.getString("Ngay_Fax"));
            jsonChiTiet.put("Co_Quan_De_Nghi_Ten", object.getString("Co_Quan_De_Nghi_Ten"));
            jsonChiTiet.put("Co_Quan_De_Nghi_Id", object.getString("Co_Quan_De_Nghi_Id"));
            jsonChiTiet.put("Don_Vi_Nhan_Phep_Id", object.getString("Don_Vi_Nhan_Phep_Id"));
            arrayChiTiet.put(jsonChiTiet);
            arrayPhepNhapCanhChiTiet.put(arrayChiTiet);
            jsonPhepNhapCanh.put("PhepNhapCanhChiTiet", arrayPhepNhapCanhChiTiet);

            //PhepNhapCanh Body
            jsonPhepNhapCanh.put("Id", phepNhapCanh.getString("Id"));
            jsonPhepNhapCanh.put("So_Fax", phepNhapCanh.getString("So_Fax"));
            jsonPhepNhapCanh.put("Ngay_Fax", phepNhapCanh.getString("Ngay_Fax"));
            jsonPhepNhapCanh.put("Ma_So_Khach", phepNhapCanh.getString("Ma_So_Khach"));
            jsonPhepNhapCanh.put("Don_Vi_Cap_Phep_Id", phepNhapCanh.getString("Don_Vi_Cap_Phep_Id"));
            jsonPhepNhapCanh.put("Ngay_Tao", phepNhapCanh.getString("Ngay_Tao"));
            jsonPhepNhapCanh.put("Nguoi_Tao", phepNhapCanh.getString("Nguoi_Tao"));
            jsonPhepNhapCanh.put("Trang_Thai", phepNhapCanh.getString("Trang_Thai"));
            jsonPhepNhapCanh.put("Co_Quan_De_Nghi_Ten", phepNhapCanh.getString("Co_Quan_De_Nghi_Ten"));
            jsonPhepNhapCanh.put("Co_Quan_De_Nghi_Id", phepNhapCanh.getString("Co_Quan_De_Nghi_Id"));
            jsonPhepNhapCanh.put("Nguoi_Ky", phepNhapCanh.getString("Nguoi_Ky"));
            jsonPhepNhapCanh.put("Chuc_Vu", phepNhapCanh.getString("Chuc_Vu"));

            hsThiThucJson.put("PhepNhapCanh", jsonPhepNhapCanh);
        } catch (Exception e) {
            e.getMessage();
        }
        return hsThiThucJson;
    }

    private static JSONObject hsCaNhan(JSONObject canhan ){
        JSONObject hsThiThucJson = JSONFactoryUtil.createJSONObject();
        JSONObject jsonCaNhan = JSONFactoryUtil.createJSONObject();
        jsonCaNhan.put("Id", canhan.getString("Id"));
        jsonCaNhan.put("ca_nhan_goc_id", canhan.getString("ca_nhan_goc_id"));
        jsonCaNhan.put("noi_sinh", canhan.getString("noi_sinh"));
        jsonCaNhan.put("Ten", canhan.getString("Ten"));
        jsonCaNhan.put("Ten_Kd", canhan.getString("Ten_Kd"));
        jsonCaNhan.put("Ngay_Sinh", canhan.getString("Ngay_Sinh"));
        jsonCaNhan.put("Def_Ngay_Sinh", canhan.getString("Def_Ngay_Sinh"));
        jsonCaNhan.put("Gioi_Tinh", canhan.getString("Gioi_Tinh"));
        jsonCaNhan.put("Quoc_Tich_Hn_Id", canhan.getString("Quoc_Tich_Hn_Id"));
        jsonCaNhan.put("So_Ho_Chieu", canhan.getString("So_Ho_Chieu"));
        jsonCaNhan.put("Quoc_Tich_Goc_Id", canhan.getString("Quoc_Tich_Goc_Id"));
        jsonCaNhan.put("Ngay_Tao", canhan.getString("Ngay_Tao"));
        jsonCaNhan.put("Nguoi_Tao", canhan.getString("Nguoi_Tao"));
        hsThiThucJson.put("CaNhan", jsonCaNhan);
        return hsThiThucJson;
    }


    private static JSONArray hsAnh(JSONArray hsAnh , Dossier dossier){
        JSONArray hsThiThucArray = JSONFactoryUtil.createJSONArray();
        JSONObject hsThiThucJson = JSONFactoryUtil.createJSONObject();
        //HS Anh
        JSONObject jsonHsAnh = JSONFactoryUtil.createJSONObject();
        JSONArray arrayHsAnh = JSONFactoryUtil.createJSONArray();
        for (int t = 0; t < hsAnh.length(); t++) {
            JSONObject hsAnhJSON = hsAnh.getJSONObject(t);
            jsonHsAnh.put("Id", "");
            jsonHsAnh.put("Hs_Ho_So_Id", hsAnhJSON.getString("Hs_Ho_So_Id"));
            jsonHsAnh.put("Anh", hsAnhJSON.getString("Anh"));
            jsonHsAnh.put("Loai_Anh", hsAnhJSON.getString("Loai_Anh"));
            jsonHsAnh.put("Chu_Hs", hsAnhJSON.getString("Chu_Hs"));
            jsonHsAnh.put("Ngay_Tao", dossier.getCreateDate());
            jsonHsAnh.put("Nguoi_Tao", "");
            arrayHsAnh.put(jsonHsAnh);
        }
        hsThiThucJson.put("HsAnh", arrayHsAnh);
        hsThiThucArray.put(hsThiThucJson);
        return hsThiThucArray;
    }


    private static JSONArray hsTreEm(JSONArray hsTreEm , Dossier dossier){
        JSONArray hsThiThucArray = JSONFactoryUtil.createJSONArray();
        //HS tre em di cung
        JSONObject jsonHsTreEm = JSONFactoryUtil.createJSONObject();
        JSONArray arrayHsTreEm = JSONFactoryUtil.createJSONArray();
        JSONObject hsThiThucJson = JSONFactoryUtil.createJSONObject();

        for (int t = 0; t < hsTreEm.length(); t++) {
            JSONObject hsTreEmJSON = hsTreEm.getJSONObject(t);
            jsonHsTreEm.put("Id", "");
            jsonHsTreEm.put("Ten", hsTreEmJSON.getString("Ten"));
            jsonHsTreEm.put("Ten_Kd", hsTreEmJSON.getString("Ten_Kd"));
            jsonHsTreEm.put("Ngay_Sinh", hsTreEmJSON.getString("Ngay_Sinh"));
            jsonHsTreEm.put("Def_Ngay_Sinh", hsTreEmJSON.getString("Ngay_Sinh"));
            jsonHsTreEm.put("Quoc_Tich_Id", hsTreEmJSON.getString("Quoc_Tich_Id"));
            jsonHsTreEm.put("So_Ho_Chieu", hsTreEmJSON.getString("So_Ho_Chieu"));
            jsonHsTreEm.put("Qhgd_Id", hsTreEmJSON.getString("Qhgd_Id"));
            jsonHsTreEm.put("Hs_Thi_Thuc_Id", "");
            jsonHsTreEm.put("Ngay_Tao", dossier.getCreateDate());
            jsonHsTreEm.put("Nguoi_Tao", "");
            arrayHsTreEm.put(jsonHsTreEm);
        }
        hsThiThucJson.put("HsTreEmDiCung", arrayHsTreEm);
        hsThiThucArray.put(hsThiThucJson);
        return hsThiThucArray;
    }


    private static JSONArray hsThanNhan(JSONArray hsThanNhan, Dossier dossier){
        JSONArray hsThiThucArray = JSONFactoryUtil.createJSONArray();
        //Hs thân nhân
        JSONObject jsonHsThanNhan = JSONFactoryUtil.createJSONObject();
        JSONArray arrayHsThanNhan = JSONFactoryUtil.createJSONArray();
        JSONObject hsThiThucJson = JSONFactoryUtil.createJSONObject();

        for (int t = 0; t < hsThanNhan.length(); t++) {
            JSONObject hsThanNhanJSON = hsThanNhan.getJSONObject(t);
            jsonHsThanNhan.put("Id", hsThanNhanJSON.getString(""));
            jsonHsThanNhan.put("Qhgd_Id", hsThanNhanJSON.getString("Qhgd_Id"));
            jsonHsThanNhan.put("Ten", hsThanNhanJSON.getString("Ten"));
            jsonHsThanNhan.put("Ten_Kd", hsThanNhanJSON.getString("Ten_Kd"));
            jsonHsThanNhan.put("Ngay_Sinh", hsThanNhanJSON.getString("Ngay_Sinh"));
            jsonHsThanNhan.put("Def_Ngay_Sinh", hsThanNhanJSON.getString("Def_Ngay_Sinh"));
            jsonHsThanNhan.put("Quoc_Tich_Id", hsThanNhanJSON.getString("Quoc_Tich_Id"));
            jsonHsThanNhan.put("Dia_Chi_Thuong_Tru", hsThanNhanJSON.getString("Dia_Chi_Thuong_Tru"));
            jsonHsThanNhan.put("Nguoi_Tao", hsThanNhanJSON.getString(""));
            jsonHsThanNhan.put("Ngay_Tao", dossier.getCreateDate());
            arrayHsThanNhan.put(jsonHsThanNhan);
        }
        hsThiThucJson.put("HsThanNhan", arrayHsThanNhan);
        hsThiThucArray.put(hsThiThucJson);
        return hsThiThucArray;
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
