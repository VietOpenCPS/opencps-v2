package org.opencps.dossiermgt.action;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author trungnt
 *
 */
public interface DVCQGIntegrationAction {
	public String getAccessToken(User user, ServiceContext serviceContext);
	public JSONObject getSharingDictCollection(User user, ServiceContext serviceContext, JSONObject data);
	public JSONObject getSharingData(User user, ServiceContext serviceContext, JSONObject data);
	public JSONObject syncDossier(User user, long groupId, ServiceContext serviceContext, String strDossierId, String isUpdating);
	public JSONObject syncDossierStatus(User user, long groupId, ServiceContext serviceContext, String strDossierId);
	public JSONObject mappingServiceInfo(User user, long groupId, ServiceContext serviceContext, String serviceCode,
			String serviceCodeDVCQG);
	public boolean removeMappingServiceInfo(User user, long groupId, ServiceContext serviceContext, String serviceCode);
	//public JSONObject searchDossier(User user, long groupId, ServiceContext serviceContext, String dossierNo);
	//public boolean checkExistDossier(User user, long groupId, ServiceContext serviceContext, String dossierNo);
}
