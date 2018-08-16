package org.opencps.dossiermgt.action;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

public interface DossierSyncActions {

	public JSONObject getDossierSyncList(long userId, String action, int syncType, Sort[] sorts, Integer start,
			Integer end, ServiceContext serviceContext);

	public JSONObject getDossierSyncById(long userId, Long dossierId, Integer model, int actionCodeNo, Sort[] sorts, Integer start, Integer end,
			ServiceContext serviceContext);

	public JSONObject getDossierSyncByDossierAndInfo(long groupId, String id, Integer info, Integer start, Integer end,
			ServiceContext serviceContext);
	public JSONObject getDossierSyncByAction(long groupId, String actionCode, Integer start, Integer end,
			ServiceContext serviceContext);
	public JSONObject getDossierSyncByDossiers(long groupId, String id, Integer start, Integer end,
			ServiceContext serviceContext);
}
