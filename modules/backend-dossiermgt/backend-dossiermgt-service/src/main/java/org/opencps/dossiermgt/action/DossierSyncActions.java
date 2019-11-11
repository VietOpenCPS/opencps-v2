package org.opencps.dossiermgt.action;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;

public interface DossierSyncActions {

	public JSONObject getDossierSyncByDossierAndInfo(long groupId, String id, Integer info, Integer start, Integer end,
			ServiceContext serviceContext);
	public JSONObject getDossierSyncByAction(long groupId, String actionCode, Integer start, Integer end,
			ServiceContext serviceContext);
	public JSONObject getDossierSyncByDossiers(long groupId, String id, Integer start, Integer end,
			ServiceContext serviceContext);
}
