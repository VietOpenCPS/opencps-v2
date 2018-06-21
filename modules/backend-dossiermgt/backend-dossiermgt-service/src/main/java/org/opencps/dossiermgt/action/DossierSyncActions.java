package org.opencps.dossiermgt.action;

import java.util.LinkedHashMap;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

public interface DossierSyncActions {

	public JSONObject getDossierSyncList(long userId, String action, int syncType, Sort[] sorts, Integer start,
			Integer end, ServiceContext serviceContext);

	public JSONObject getDossierSyncById(long userId, Integer model, int actionCodeNo, Sort[] sorts, Integer start, Integer end,
			ServiceContext serviceContext);

}
