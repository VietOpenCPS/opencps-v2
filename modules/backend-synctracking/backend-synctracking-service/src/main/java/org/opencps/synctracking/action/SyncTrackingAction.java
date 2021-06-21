package org.opencps.synctracking.action;

import com.liferay.portal.kernel.json.JSONObject;
import org.opencps.synctracking.model.*;

public interface SyncTrackingAction {
    public DtoResponse<SyncTrackingResponse> get(SyncTrackingQuery syncTrackingQuery) throws Exception;
    public boolean create(SyncTrackingQuery syncTrackingQuery) throws Exception;
    public boolean resend(SyncTrackingQuery syncTrackingQuery, JSONObject config) throws Exception;
    public boolean createSynTrackingDVCQG(SyncTrackingQuery syncTrackingQuery) throws Exception;
    public boolean createDossierTax(DossierTaxInput dossierTaxInput) throws Exception;
    public boolean updateDossierTax(DossierTaxInput dossierTaxInput) throws Exception;
    public SyncTrackingResponse getSyncTracking(SyncTrackingQuery dossierTaxInput) throws Exception;
    public DossierTaxResponse getDetailDossierTax(DossierTaxInput input) throws Exception;
}
