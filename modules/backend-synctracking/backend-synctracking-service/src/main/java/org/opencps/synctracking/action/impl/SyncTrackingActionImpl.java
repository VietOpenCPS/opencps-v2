package org.opencps.synctracking.action.impl;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.input.model.DossierTaxInfo;
import org.opencps.synctracking.action.IntegrationOutsideApi;
import org.opencps.synctracking.action.SyncTrackingAction;
import org.opencps.synctracking.action.TransformAction;
import org.opencps.synctracking.model.*;
import org.opencps.synctracking.service.DossierTaxLocalServiceUtil;
import org.opencps.synctracking.service.SyncTrackingLocalServiceUtil;
import org.opencps.synctracking.service.util.CommonServiceUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SyncTrackingActionImpl implements SyncTrackingAction {
    private static final String FORMAT_DATE_FRONT_END = "d/M/yyyy";
    private static final Integer MAX_LIMIT = 1000;
    private TransformAction transformAction;
    private IntegrationOutsideApi integrationOutsideApi;
    private Log _log = LogFactoryUtil.getLog(SyncTrackingActionImpl.class);
    private RestTemplate restTemplate;

    private static final Integer timeout = 10000 ;
    public SyncTrackingActionImpl(TransformAction transformAction, IntegrationOutsideApi integrationOutsideApi) {
        this.transformAction = transformAction;
        this.integrationOutsideApi = integrationOutsideApi;
        this.restTemplate = new RestTemplate(setConfigRestTemplate(timeout));
        this.restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
    }

    @Override
    public DtoResponse<SyncTrackingResponse> get(SyncTrackingQuery syncTrackingQuery) throws Exception {
        try {
            DtoResponse<SyncTrackingResponse> response = new DtoResponse<>();
            if(Validator.isNull(syncTrackingQuery)) {
                throw new Exception("No query param was found");
            }

            if(Validator.isNull(syncTrackingQuery.groupId) || syncTrackingQuery.groupId == 0) {
                throw new Exception("No groupId was found");
            }

            if(Validator.isNull(syncTrackingQuery.start)) {
                throw new Exception("No start index was found");
            }

            if(Validator.isNull(syncTrackingQuery.end)) {
                throw new Exception("No end index was found");
            }

            if(syncTrackingQuery.start >= syncTrackingQuery.end) {
                throw new Exception("Start is not smaller than end");
            }

            if(syncTrackingQuery.end >= MAX_LIMIT) {
                syncTrackingQuery.end = MAX_LIMIT;
                if(syncTrackingQuery.end < syncTrackingQuery.start) {
                    syncTrackingQuery.start = syncTrackingQuery.end - 10;
                }
            }

            String fromDateString = Validator.isNotNull(syncTrackingQuery.fromDate)
                    && !syncTrackingQuery.fromDate.isEmpty()
                    ? syncTrackingQuery.fromDate : "1/10/2020";

            String toDateString = Validator.isNotNull(syncTrackingQuery.toDate)
                    && !syncTrackingQuery.toDate.isEmpty()
                    ? syncTrackingQuery.toDate : "1/10/2100";

            Date fromDateParsed = CommonServiceUtils.stringToDate(fromDateString, FORMAT_DATE_FRONT_END);
            Date toDateParsed   = CommonServiceUtils.stringToDate(toDateString, FORMAT_DATE_FRONT_END);

            boolean hasDossierNo = false;
            boolean hasServiceCode = false;

            if(Validator.isNotNull(syncTrackingQuery.dossierNo) && !syncTrackingQuery.dossierNo.isEmpty()) {
                SyncTracking syncTracking;
                syncTracking = SyncTrackingLocalServiceUtil.getByDossierNo(syncTrackingQuery.groupId,
                        syncTrackingQuery.dossierNo);

                if(Validator.isNull(syncTracking)) {
                    syncTracking = SyncTrackingLocalServiceUtil.getByReferenceUid(syncTrackingQuery.groupId,
                            syncTrackingQuery.dossierNo);
                }

                if(Validator.isNotNull(syncTracking)) {
                    syncTrackingQuery.dossierNo = syncTracking.getReferenceUid();
                }
                hasDossierNo = true;
            }

            if(Validator.isNotNull(syncTrackingQuery.serviceCode) && !syncTrackingQuery.serviceCode.isEmpty()) {
                hasServiceCode = true;
            }
            List<SyncTracking> syncTrackingList;
            List<SyncTracking> syncTrackingPaginate;

            if(hasDossierNo && !hasServiceCode) {
                syncTrackingList = SyncTrackingLocalServiceUtil.getByReferenceUidAndDate(
                        syncTrackingQuery.groupId, syncTrackingQuery.dossierNo, fromDateParsed, toDateParsed,
                        0, MAX_LIMIT
                );
                syncTrackingPaginate = SyncTrackingLocalServiceUtil.getByReferenceUidAndDate(
                        syncTrackingQuery.groupId, syncTrackingQuery.dossierNo, fromDateParsed, toDateParsed,
                        syncTrackingQuery.start, syncTrackingQuery.end
                );
            } else if(!hasDossierNo && hasServiceCode) {
                syncTrackingList = SyncTrackingLocalServiceUtil.getByGroupIdAndServiceCodeAndDate(
                        syncTrackingQuery.groupId, syncTrackingQuery.serviceCode, fromDateParsed, toDateParsed,
                        0, MAX_LIMIT
                );
                syncTrackingPaginate = SyncTrackingLocalServiceUtil.getByGroupIdAndServiceCodeAndDate(
                        syncTrackingQuery.groupId, syncTrackingQuery.serviceCode, fromDateParsed, toDateParsed,
                        syncTrackingQuery.start, syncTrackingQuery.end
                );
            } else if(hasDossierNo && hasServiceCode) {
                syncTrackingList = SyncTrackingLocalServiceUtil.getByGroupIdAndDossierNoAndServiceCodeAndDate(
                        syncTrackingQuery.groupId, syncTrackingQuery.dossierNo, syncTrackingQuery.serviceCode,
                        fromDateParsed, toDateParsed,
                        0, MAX_LIMIT
                );
                syncTrackingPaginate = SyncTrackingLocalServiceUtil.getByGroupIdAndDossierNoAndServiceCodeAndDate(
                        syncTrackingQuery.groupId, syncTrackingQuery.dossierNo, syncTrackingQuery.serviceCode,
                        fromDateParsed, toDateParsed,
                        syncTrackingQuery.start, syncTrackingQuery.end
                );
            } else {
                syncTrackingList = SyncTrackingLocalServiceUtil.getByGroupIdAndDate(
                        syncTrackingQuery.groupId, fromDateParsed, toDateParsed,
                        0, MAX_LIMIT
                );
                syncTrackingPaginate = SyncTrackingLocalServiceUtil.getByGroupIdAndDate(
                        syncTrackingQuery.groupId, fromDateParsed, toDateParsed,
                        syncTrackingQuery.start, syncTrackingQuery.end
                );
            }

            if(Validator.isNull(syncTrackingList) || syncTrackingList.size() == 0) {
                response.total = 0;
                response.data  = new ArrayList<>();
                return response;
            }

            List<SyncTrackingResponse> syncTrackingResponse = transformAction.transForm(syncTrackingPaginate);

            response.total = syncTrackingList.size();
            response.data  = syncTrackingResponse;
            return response;
        } catch (Exception e) {
            _log.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean create(SyncTrackingQuery syncTrackingQuery) throws Exception {
        try {
            if(Validator.isNull(syncTrackingQuery)) {
                throw new Exception("No body param was found");
            }

            if(Validator.isNull(syncTrackingQuery.groupId) || syncTrackingQuery.groupId == 0) {
                throw new Exception("No groupId was found");
            }

            if(Validator.isNull(syncTrackingQuery.fromUnit) || syncTrackingQuery.fromUnit.isEmpty()) {
                throw new Exception("No from unit code was found");
            }

            if(Validator.isNull(syncTrackingQuery.toUnit) || syncTrackingQuery.toUnit.length == 0) {
                throw new Exception("No to unit code was found");
            }

            if(Validator.isNull(syncTrackingQuery.api) || syncTrackingQuery.api.isEmpty()) {
                throw new Exception("No api was found");
            }

            if(Validator.isNull(syncTrackingQuery.stateSync) || syncTrackingQuery.stateSync == 0) {
                throw new Exception("No state sync found");
            }

            String[] listToUnit = syncTrackingQuery.toUnit;
            for(String toUnitSingle: listToUnit) {
                syncTrackingQuery.toUnitSingle = toUnitSingle;
                SyncTrackingLocalServiceUtil.createSyncTrackingManual(syncTrackingQuery);
            }

            return true;
        } catch (Exception e) {
            _log.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean createSynTrackingDVCQG(SyncTrackingQuery syncTrackingQuery) throws Exception {
        try {
            if (Validator.isNull(syncTrackingQuery)) {
                throw new Exception("No body param was found");
            }

            if (Validator.isNull(syncTrackingQuery.bodyRequest) || syncTrackingQuery.bodyRequest.isEmpty()) {
                throw new Exception("No from bodyRequest was found");
            }

            SyncTrackingLocalServiceUtil.createSyncTrackingManual(syncTrackingQuery);
            return true;
        } catch (Exception e) {
            _log.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean createDossierTax(DossierTaxInput dossierTaxInput) throws Exception {
        try {
            if (Validator.isNull(dossierTaxInput)) {
                throw new Exception("No body param was found");
            }

            if (Validator.isNull(dossierTaxInput.dossierId) || dossierTaxInput.dossierId ==0) {
                throw new Exception("No from dossierId was found");
            }

            if (Validator.isNull(dossierTaxInput.dossierNo) || dossierTaxInput.dossierNo.isEmpty()) {
                throw new Exception("No from dossierNo was found");
            }

            if (Validator.isNull(dossierTaxInput.maSoThue) || dossierTaxInput.maSoThue.isEmpty()) {
                throw new Exception("No from maSoThue was found");
            }

            if (Validator.isNull(dossierTaxInput.soQuyetDinh) || dossierTaxInput.soQuyetDinh.isEmpty()) {
                throw new Exception("No from soQuyetDinh was found");
            }

            _log.info("Tạo dữ liệu thuế");
            DossierTaxLocalServiceUtil.createDossierTaxManual(dossierTaxInput);
            return true;
        }catch (Exception e){
            _log.error(e);
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public boolean updateDossierTax(DossierTaxInput dossierTaxInput) throws Exception {
        try {
            if (Validator.isNull(dossierTaxInput)) {
                throw new Exception("No body param was found");
            }

            if (Validator.isNull(dossierTaxInput.dossierNo) || dossierTaxInput.dossierNo.isEmpty()) {
                throw new Exception("No from dossierNo was found");
            }

            if (Validator.isNull(dossierTaxInput.maSoThue) || dossierTaxInput.maSoThue.isEmpty()) {
                throw new Exception("No from maSoThue was found");
            }

            if (Validator.isNull(dossierTaxInput.soQuyetDinh) || dossierTaxInput.soQuyetDinh.isEmpty()) {
                throw new Exception("No from soQuyetDinh was found");
            }
            _log.info("---- Mã hồ sơ ---: " + dossierTaxInput.dossierNo + "--- Mã số thuế--- :" + dossierTaxInput.maSoThue + " --- Số Quyết Định ---: " + dossierTaxInput.soQuyetDinh );
            DossierTax dossierTax = DossierTaxLocalServiceUtil.fetchDossierTaxByDMS(dossierTaxInput.dossierNo, dossierTaxInput.maSoThue, dossierTaxInput.soQuyetDinh);
            if (Validator.isNotNull(dossierTax)) {
                _log.info("Cập nhật dữ liệu thuế");
                dossierTax.setHoTenNguoiNopTien(dossierTaxInput.hoTenNguoiNopTien);
                dossierTax.setSoCmtNguoiNopTien(Integer.valueOf(dossierTaxInput.soCmtNguoiNopTien));
                dossierTax.setDiaChiNguoiNopTien(dossierTaxInput.diaChiNguoiNopTien);
                dossierTax.setTinhNguoiNopTien(dossierTaxInput.tinhNguoiNopTien);
                dossierTax.setHuyenNguoiNopTien(dossierTaxInput.huyenNguoiNopTien);
                dossierTax.setXaNguoiNopTien(dossierTaxInput.xaNguoiNopTien);
                dossierTax.setThoiGianThanhToan(APIDateTimeUtils.convertStringToDate(dossierTaxInput.thoiGianThanhToan,APIDateTimeUtils._NORMAL_DATE));
                dossierTax.setNgayNhanBienLai(APIDateTimeUtils.convertStringToDate(dossierTaxInput.ngayNhanBienLai,APIDateTimeUtils._NORMAL_DATE));
                dossierTax.setNgayThueTraThongBao(APIDateTimeUtils.convertStringToDate(dossierTaxInput.ngayThueTraThongBao,APIDateTimeUtils._NORMAL_DATE));
                dossierTax.setNgayTraThongBao(APIDateTimeUtils.convertStringToDate(dossierTaxInput.ngayTraThongBao,APIDateTimeUtils._NORMAL_DATE));
                dossierTax.setSoTienNop(Integer.valueOf(dossierTaxInput.soTienNop));
                dossierTax.setNoiDungThanhToan(dossierTaxInput.noiDungThanhToan);
                dossierTax.setTrangThaiThanhToan(dossierTaxInput.trangThaiThanhToan);
                dossierTax.setFileChungTu(dossierTaxInput.fileChungTu);
                dossierTax.setNgayQuyetDinh(APIDateTimeUtils.convertStringToDate(dossierTaxInput.ngayQuyetDinh,APIDateTimeUtils._NORMAL_DATE));
                if(Validator.isNotNull(dossierTaxInput.statusTBT)) {
                    dossierTax.setStatusTBT(Integer.valueOf(dossierTaxInput.statusTBT));
                }
                if(Validator.isNotNull(dossierTaxInput.statusCTT)) {
                    dossierTax.setStatusCTT(Integer.valueOf(dossierTaxInput.statusCTT));
                }
                DossierTaxLocalServiceUtil.updateDossierTax(dossierTax);
                return true;
            }
            return false;
        }catch (Exception e){
            _log.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public SyncTrackingResponse getSyncTracking(SyncTrackingQuery input) throws Exception {
        try {
            SyncTrackingResponse syncTrackingResponse = new SyncTrackingResponse();
            if (Validator.isNull(input)) {
                throw new Exception("No body param was found");
            }

            if (Validator.isNull(input.dossierNo) || input.dossierNo.isEmpty()) {
                throw new Exception("No from dossierNo was found");
            }
            SyncTracking syncTracking = SyncTrackingLocalServiceUtil.getByDossierNoAndProtocol(input.groupId, input.dossierNo, input.protocol);
            if(Validator.isNotNull(syncTracking)) {
                _log.debug("SyncTracking: " + JSONFactoryUtil.looseSerialize(syncTracking));
                syncTrackingResponse = transformAction.transForm(syncTracking);
            }

            return syncTrackingResponse;

        }catch (Exception e){
            e.getMessage();
        }
        return null;
    }

    @Override
    public DossierTaxResponse getDetailDossierTax(DossierTaxInput input) throws Exception {
        try {
            DossierTaxResponse response = new DossierTaxResponse();
            if (Validator.isNull(input)) {
                throw new Exception("No body param was found");
            }

            if (Validator.isNull(input.dossierNo) || input.dossierNo.isEmpty()) {
                throw new Exception("No from dossierNo was found");
            }

            if (Validator.isNull(input.maSoThue) || input.maSoThue.isEmpty()) {
                throw new Exception("No from Ma So Thue was found");
            }
            if (Validator.isNull(input.soQuyetDinh) || input.soQuyetDinh.isEmpty()) {
                throw new Exception("No from So Quyet Dinh was found");
            }

            DossierTax dossierTax = DossierTaxLocalServiceUtil.fetchDossierTaxByDMS(input.dossierNo, input.maSoThue, input.soQuyetDinh);
            if (Validator.isNotNull(dossierTax)) {
                _log.debug("SyncTracking: " + JSONFactoryUtil.looseSerialize(dossierTax));
                response = transformAction.transFormDossierTax(dossierTax);
            }

            return response;

        }catch (Exception e){
            e.getMessage();
        }
        return null;
    }

    @Override
    public String getDDossierTax(DossierTaxInput input) throws Exception {
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "*");
            DossierTaxInfo body = new DossierTaxInfo();
            String urlCall ="127.0.0.1:8081/o/rest/getDetailDossierTax";

            body.dossierNo = input.dossierNo;
			body.maSoThue = input.maSoThue;
			body.soQuyetDinh = input.soQuyetDinh;
			headers.add("groupId", String.valueOf(input.groupId));
			_log.info("Body: " + JSONFactoryUtil.looseSerialize(body));
			HttpEntity<DossierTaxInfo> entity = new HttpEntity<>(body, headers);
			ResponseEntity<String> response = restTemplate.postForEntity(urlCall, entity, String.class);
			if(Validator.isNotNull(response)){
				return response.getBody();
			}
			return null;
        }catch (Exception e){
            e.getMessage();
        }
        return null;
    }

    @Override
    public boolean resend(SyncTrackingQuery syncTrackingQuery, JSONObject config) throws Exception {
        try {
            if(Validator.isNull(syncTrackingQuery.trackingId) || syncTrackingQuery.trackingId == 0) {
                throw new Exception("No Tracking Id was found");
            }

            SyncTracking syncTrackingResend = SyncTrackingLocalServiceUtil.getSyncTracking(syncTrackingQuery.trackingId);

            if(Validator.isNull(syncTrackingResend)) {
                throw new Exception("No tracking log was found with tracking id: " + syncTrackingQuery.trackingId);
            }

            if(Validator.isNull(syncTrackingResend.getBodyRequest()) ||
                    syncTrackingResend.getBodyRequest().equals("-") ||
                    syncTrackingResend.getBodyRequest().isEmpty()) {
                throw new Exception("No body request was found with tracking id: " + syncTrackingQuery.trackingId);
            }

            if(Validator.isNull(syncTrackingResend.getApi()) ||
                    syncTrackingResend.getApi().isEmpty()) {
                throw new Exception("No api LGSP was found with tracking id: " + syncTrackingQuery.trackingId);
            }

            String token = integrationOutsideApi.getToken(config);

            String bodyRequest = syncTrackingResend.getBodyRequest();
            String url         = syncTrackingResend.getApi();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + token);

            integrationOutsideApi.postWithString(url, headers, bodyRequest);
            return true;
        } catch (Exception e) {
            _log.error(e);
            throw new Exception(e.getMessage());
        }
    }
    private ClientHttpRequestFactory setConfigRestTemplate(Integer timeout) {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        return clientHttpRequestFactory;
    }
}
