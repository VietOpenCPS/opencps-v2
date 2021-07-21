package org.opencps.dossiermgt.action;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author trungnt
 *
 */
public interface DVCQGIntegrationAction {
	public String getAccessToken(User user, HttpServletRequest request, HttpServletResponse response, ServiceContext serviceContext);
	public JSONObject getSharingDictCollection(User user, ServiceContext serviceContext, JSONObject data);
	public JSONObject getSharingData(User user, ServiceContext serviceContext, JSONObject data);
	public JSONObject syncDossier(User user, long groupId, ServiceContext serviceContext, String strDossierId, String isUpdating, HttpServletRequest request);
	public JSONObject syncDossierStatus(User user, long groupId, ServiceContext serviceContext, String strDossierId, HttpServletRequest request);
	public JSONObject mappingServiceInfo(User user, long groupId, ServiceContext serviceContext, String serviceCode,
			String serviceCodeDVCQG, String serviceNameDVCQG);
	public boolean removeMappingServiceInfo(User user, long groupId, ServiceContext serviceContext, long id);
	public JSONObject syncServiceInfo(User user, long groupId, ServiceContext serviceContext, String serviceCodes, String type);
	public JSONObject getSharingQA(User user, ServiceContext serviceContext, JSONObject data);
	public JSONObject doSyncSharingQA(User user, ServiceContext serviceContext, JSONObject data);
	public JSONObject doSyncServiceDomain(User user, ServiceContext serviceContext, JSONObject data);
	public JSONObject doSyncGovernmentAgency(User user, ServiceContext serviceContext, JSONObject data);
	public JSONObject doSyncServiceAdministration(User user, ServiceContext serviceContext, JSONObject data);
	public JSONObject doCreateDossierFromDVCQG(Company company, User user, long groupId, ServiceContext serviceContext, JSONObject data);
	public JSONObject doCreateDossierSuaDoiBoSungFromDVCQG(Company company, User user, long groupId,
														   ServiceContext serviceContext, JSONObject data, boolean isUpdating);
	public JSONObject doCreateUpdateDossierFromDVCQG(Company company, User user, long groupId,
														   ServiceContext serviceContext, JSONObject data, boolean isUpdating);
	public JSONObject doCrUpDossierThongBaoThueDatDVCQG(Company company, User user, long groupId,
													   ServiceContext serviceContext, JSONObject data, boolean isUpdating);
	public void syncSummaryVote() throws Exception;
	public JSONObject doSyncServiceConfig(User user, long groupId, String requestBody, ServiceContext context);
	//public JSONObject doSyncAnsewer(User user, ServiceContext serviceContext, JSONObject data);
	//public JSONObject searchDossier(User user, long groupId, ServiceContext serviceContext, String dossierNo);
	//public boolean checkExistDossier(User user, long groupId, ServiceContext serviceContext, String dossierNo);
}
