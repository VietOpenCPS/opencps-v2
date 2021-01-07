package org.opencps.synctracking.action;

import com.liferay.portal.kernel.json.JSONObject;
import org.opencps.synctracking.model.DtoResponse;
import org.opencps.synctracking.model.SyncTrackingQuery;
import org.opencps.synctracking.model.SyncTrackingResponse;

public interface SyncTrackingAction {
    public DtoResponse<SyncTrackingResponse> get(SyncTrackingQuery syncTrackingQuery) throws Exception;
    public boolean create(SyncTrackingQuery syncTrackingQuery) throws Exception;
    public boolean resend(SyncTrackingQuery syncTrackingQuery, JSONObject config) throws Exception;
}
