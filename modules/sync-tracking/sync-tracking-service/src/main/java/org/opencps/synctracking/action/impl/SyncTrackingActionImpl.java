package org.opencps.synctracking.action.impl;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.synctracking.action.IntegrationOutsideApi;
import org.opencps.synctracking.action.SyncTrackingAction;
import org.opencps.synctracking.action.TransformAction;
import org.opencps.synctracking.model.DtoResponse;
import org.opencps.synctracking.model.SyncTracking;
import org.opencps.synctracking.model.SyncTrackingQuery;
import org.opencps.synctracking.model.SyncTrackingResponse;
import org.opencps.synctracking.service.SyncTrackingLocalServiceUtil;
import org.opencps.synctracking.service.util.CommonServiceUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SyncTrackingActionImpl implements SyncTrackingAction {
    private static final String FORMAT_DATE_FRONT_END = "d/M/yyyy";
    private static final Integer MAX_LIMIT = 1000;
    private TransformAction transformAction;
    private IntegrationOutsideApi integrationOutsideApi;
    private Log _log = LogFactoryUtil.getLog(SyncTrackingActionImpl.class);

    public SyncTrackingActionImpl(TransformAction transformAction, IntegrationOutsideApi integrationOutsideApi) {
        this.transformAction = transformAction;
        this.integrationOutsideApi = integrationOutsideApi;
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
                    syncTrackingQuery.dossierNo = syncTracking.getDossierNo();
                }
                hasDossierNo = true;
            }

            if(Validator.isNotNull(syncTrackingQuery.serviceCode) && !syncTrackingQuery.serviceCode.isEmpty()) {
                hasServiceCode = true;
            }
            List<SyncTracking> syncTrackingList;
            List<SyncTracking> syncTrackingPaginate;

            if(hasDossierNo && !hasServiceCode) {
                syncTrackingList = SyncTrackingLocalServiceUtil.getByGroupIdAndDossierNoAndDate(
                        syncTrackingQuery.groupId, syncTrackingQuery.dossierNo, fromDateParsed, toDateParsed,
                        0, MAX_LIMIT
                );
                syncTrackingPaginate = SyncTrackingLocalServiceUtil.getByGroupIdAndDossierNoAndDate(
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
            throw new Exception(e.getMessage());
        }
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
            throw new Exception(e.getMessage());
        }
    }
}
