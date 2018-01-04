package org.opencps.dossiermgt.action;


import org.opencps.dossiermgt.model.DossierLog;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

public interface DossierLogActions {

	public JSONObject getDossierLogs(long groupId, String notificationType, Boolean owner, int start, int end, String sort, String order,
			ServiceContext serviceContext);

	public DossierLog addDossierLog(long groupId, long dossierId, String author, String content,
			String notificationType, String payload, ServiceContext serviceContext)
			throws PortalException, SystemException;

	public JSONObject getDossierLogById(long groupId, long id, String password, Boolean owner, int start, int end, String sort,
			String order, ServiceContext serviceContext);

	public JSONObject getDossiers(long groupId, long dossierId, Sort[] sorts, int start, int end,
			ServiceContext serviceContext);

}
