package org.opencps.synctracking.action.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.synctracking.action.TransformAction;
import org.opencps.synctracking.model.SyncTracking;
import org.opencps.synctracking.model.SyncTrackingResponse;
import org.opencps.synctracking.service.util.CommonConstant;

import java.util.ArrayList;
import java.util.List;

public class TransformActionImpl implements TransformAction {
    private static Log _log = LogFactoryUtil.getLog(TransformActionImpl.class);
    public List<SyncTrackingResponse> transForm(List<SyncTracking> listTracking) throws Exception{
        try {
            List<SyncTrackingResponse> listTrackingTransform = new ArrayList<>();
            SyncTrackingResponse oneTrackingTransform;
            for(SyncTracking syncTracking : listTracking) {
                oneTrackingTransform = new SyncTrackingResponse();
                oneTrackingTransform.api = syncTracking.getApi();
                oneTrackingTransform.bodyRequest = Validator.isNotNull(syncTracking.getBodyRequest())
                        ? syncTracking.getBodyRequest() : "";
                oneTrackingTransform.bodyResponse = Validator.isNotNull(syncTracking.getResponse())
                        ? syncTracking.getResponse() : "";
                oneTrackingTransform.createDate = Validator.isNotNull(syncTracking.getCreateDate())
                        ? syncTracking.getCreateDate().getTime() : 0;
                oneTrackingTransform.dossierNo = Validator.isNotNull(syncTracking.getDossierNo())
                        ? syncTracking.getDossierNo(): "";
                oneTrackingTransform.serviceCode = Validator.isNotNull(syncTracking.getServiceCode())
                        ? syncTracking.getServiceCode(): "";
                oneTrackingTransform.fromUnit = Validator.isNotNull(syncTracking.getFromUnit())
                        ? syncTracking.getFromUnit() : "";
                oneTrackingTransform.toUnit = Validator.isNotNull(syncTracking.getToUnit())
                        ? syncTracking.getToUnit() : "";
                oneTrackingTransform.stateSync = Validator.isNotNull(syncTracking.getStateSync())
                        ? syncTracking.getStateSync() : CommonConstant.STATE_ERROR;
                oneTrackingTransform.trackingId = Validator.isNotNull(syncTracking.getTrackingId())
                        ? syncTracking.getTrackingId() : 0;
                listTrackingTransform.add(oneTrackingTransform);
            }
            return listTrackingTransform;
        } catch (Exception e) {
            _log.error(e);
            throw new Exception(e.getMessage());
        }
    }
}
