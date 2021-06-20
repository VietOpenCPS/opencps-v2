package org.opencps.synctracking.action;

import org.opencps.synctracking.model.DossierTax;
import org.opencps.synctracking.model.DossierTaxResponse;
import org.opencps.synctracking.model.SyncTracking;
import org.opencps.synctracking.model.SyncTrackingResponse;

import java.util.List;

public interface TransformAction {
    public List<SyncTrackingResponse> transForm(List<SyncTracking> listTracking) throws Exception;

    public SyncTrackingResponse transForm(SyncTracking listTracking) throws Exception;

    public DossierTaxResponse transFormDossierTax(DossierTax dossierTax) throws Exception;
}
