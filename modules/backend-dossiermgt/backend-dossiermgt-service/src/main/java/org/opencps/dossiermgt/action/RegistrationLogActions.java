package org.opencps.dossiermgt.action;

import java.util.List;

import org.opencps.dossiermgt.model.RegistrationLog;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

public interface RegistrationLogActions {

	// get registration logs by id
	public List<RegistrationLog> getRegistrationLogbyId(long groupId, long registrationId) throws PortalException;
	
	// add registration logs by id
	public RegistrationLog addRegistrationLogById(long groupId, long registrationId, String author, String content, String payload, ServiceContext serviceContext);

	//search Lucene
	public JSONObject getRegistrationLog(long groupId, long registrationId, int start, int end, Sort[] sort, String order,
			ServiceContext serviceContext) throws PortalException;

}
