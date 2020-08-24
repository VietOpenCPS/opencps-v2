package org.opencps.backend.dossiermgt.logistic;

import com.liferay.portal.kernel.json.JSONObject;
import org.opencps.dossiermgt.rest.model.ViettelPostUpdateOrder;

public interface ViettelPostManagement {
    public String getToken() throws Exception;
    public void postBill(String token, JSONObject dossierObj) throws Exception;
    public boolean updateBill(ViettelPostUpdateOrder updateInfo) throws Exception;
}
