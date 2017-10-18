package org.opencps.dossiermgt.action;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.ProcessAction;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

public interface DossierActions {

	public JSONObject getDossiers(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);

	public Dossier addDossier(long groupId, long dossierId, String referenceUid, int counter, String serviceCode,
			String serviceName, String govAgencyCode, String govAgencyName, String applicantName,
			String applicantIdType, String applicantIdNo, Date applicantIdDate, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail, String dossierTemplateNo, String dossierNote,
			String submissionNote, String applicantNote, String briefNote, String dossierNo, boolean submitting,
			Date correctingDate, String dossierStatus, String dossierStatusText, String dossierSubStatus,
			String dossierSubStatusText, long folderId, long dossierActionId, int viaPostal, String postalAddress,
			String postalCityCode, String postalCityName, String postalTelNo, String password, boolean notification,
			boolean online, String serverNo, ServiceContext context) throws PortalException;

	public Dossier getDossierDetail(long groupId, long dossierId, String referenceUid) throws PortalException;

	public Dossier removeDossier(long groupId, long dossierId, String referenceUid) throws PortalException;

	public Dossier cancelDossier(long groupId, long dossierId, String referenceUid) throws PortalException;

	public Dossier correctDossier(long groupId, long dossierId, String referenceUid) throws PortalException;

	public Dossier submitDossier(long groupId, long dossierId, String referenceUid) throws PortalException;

	public Dossier resetDossier(long groupId, long dossierId, String referenceUid) throws PortalException;

	public List<ProcessAction> getNextActions(long groupId, long dossierId, String referenceUid) throws PortalException;

	public List<DossierAction> getDossierActions(long groupId, long dossierId, String referenceUid)
			throws PortalException;

	public void doAction(long groupId, long dossierId, String referenceUid, String actionCode, String actionUser,
			String actionNote, long assignUserId) throws PortalException;

	public Dossier markerVisited(long groupId, long dossierId, String referenceUid) throws PortalException;

	public void doRollback(long groupId, long dossierId, String referenceUid, long userId) throws PortalException;

	public void getContacts(long groupId, long dossierId, String referenceUid) throws PortalException;

}
