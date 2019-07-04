package org.opencps.dossiermgt.action;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.model.ServiceProcess;

import backend.auth.api.exception.ErrorMsgModel;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

public interface DossierActions {
	public JSONObject getDossiers(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);
	public JSONObject getDossiersTest(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);

	public Dossier initDossier(long groupId, long dossierId, String referenceUid, int counter, String serviceCode,
			String serviceName, String govAgencyCode, String govAgencyName, String applicantName,
			String applicantIdType, String applicantIdNo, String applicantIdDate, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail, String dossierTemplateNo, String password,
			int viaPostal, String postalAddress, String postalCityCode, String postalCityName, String postalTelNo,
			boolean online, boolean notification, String applicantNote, int originality, ServiceContext context) throws PortalException;

	public Dossier createDossier(long groupId,String serviceCode, String govAgencyCode, String applicantName,
			String applicantIdType, String applicantIdNo, Date applicantIdDate, String address, String cityCode,
			String districtCode, String wardCode, String contactName, String contactTelNo, String contactEmail,
			boolean isSameAsApplicant, String delegateName, String delegateIdNo, String delegateTelNo,
			String delegateEmail, String delegateAddress, String delegateCityCode, String delegateDistrictCode,
			String delegateWardCode, String applicantNote, String briefNote,
			String dossierNo, String dossierTemplateNo, int viaPostal, String postalServiceCode,
			String postalServiceName, String postalAddress, String postalCityCode, String postalDistrictCode,
			String postalWardCode, String postalTelNo, int originality, ServiceContext context) throws PortalException;

	public Dossier assignDossierToProcess(long dossierId, String dossierNote, String submissionNote, String briefNote,
			String dossierNo, long folderId, long dossierActionId, String serverNo, ServiceContext context)
			throws PortalException;

	public Dossier removeDossier(long groupId, long dossierId, String referenceUid) throws PortalException;

	public Dossier cancelDossier(long groupId, long dossierId, String referenceUid, ServiceContext context)
			throws PortalException;

	public Dossier correctDossier(long groupId, long dossierId, String referenceUid, ServiceContext context)
			throws PortalException;

	public Dossier submitDossier(long groupId, long dossierId, String referenceUid, ServiceContext context)
			throws PortalException;

	public Dossier resetDossier(long groupId, long dossierId, String referenceUid, ServiceContext context)
			throws PortalException;

	public DossierAction doAction(long groupId, long dossierId, String referenceUid, String actionCode,
			long processActionId, String actionUser, String actionNote, long assignUserId, long userId, String subUsers,
			ServiceContext context) throws PortalException;

	public Dossier markerVisited(long groupId, long dossierId, String referenceUid) throws PortalException;

	public DossierAction doRollback(long groupId, long dossierId, String referenceUid, long userId)
			throws PortalException;

	public JSONObject getContacts(long groupId, long dossierId, String referenceUid) throws PortalException;

	public Dossier cloneDossier(long groupId, long dossierId, ServiceContext serviceContext) throws PortalException;

	public JSONObject getDossierTodo(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, ServiceContext serviceContext);
	
//	public JSONObject getDossierTodoPermission(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
//			Sort[] sorts, ServiceContext serviceContext);

	public JSONObject getDossierTodoPermission(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, ServiceContext serviceContext);
	
	
	public Dossier submitPostDossier(long groupId, long dossierId, String referenceUid, ServiceContext context) throws PortalException ;

	public JSONObject getDossierCountTodoPermission(long userId, long companyId, long groupId,
			LinkedHashMap<String, Object> params, Object object, ServiceContext serviceContext);

	//LamTV: Process DossierTodo
	public JSONObject getDossierProcessList(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, Integer start, Integer end, ServiceContext serviceContext);
	public DossierAction doAction(long groupId, long userId, Dossier dossier, ProcessOption option,
			ProcessAction proAction, String actionCode, String actionUser, String actionNote, String payload,
			String assignUsers, String payment, int syncType, ServiceContext serviceContext, ErrorMsgModel errorModel) throws PortalException, Exception;

	public JSONArray getNextActionList(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, Integer start, Integer end, ServiceContext serviceContext);

	public JSONObject getDetailNextActions(long userId, long companyId, long groupId,
			LinkedHashMap<String, Object> params, Sort[] sorts, Integer start, Integer end,
			ServiceContext serviceContext);

	public JSONArray getPayloadNextActions(long userId, long companyId, long groupId,
			LinkedHashMap<String, Object> params, Sort[] sorts, Integer start, Integer end,
			ServiceContext serviceContext);

	public Dossier initUpdateDossier(long groupId, long id, String applicantName, String applicantIdType,
			String applicantIdNo, String applicantIdDate, String address, String cityCode, String cityName,
			String districtCode, String districtName, String wardCode, String wardName, String contactName,
			String contactTelNo, String contactEmail, String dossierTemplateNo, Integer viaPostal, String postalAddress,
			String postalCityCode, String postalCityName, String postalTelNo, String applicantNote, boolean isSameAsApplicant,
			String delegateName, String delegateIdNo, String delegateTelNo, String delegateEmail,
			String delegateAddress, String delegateCityCode, String delegateDistrictCode, String delegateWardCode,
			Long sampleCount, ServiceContext serviceContext);

//	public Dossier publishDossier(long groupId, long dossierId, String referenceUid, int counter, String serviceCode,
//			String serviceName, String govAgencyCode, String govAgencyName, String applicantName,
//			String applicantIdType, String applicantIdNo, String applicantIdDate, String address, String cityCode,
//			String cityName, String districtCode, String districtName, String wardCode, String wardName,
//			String contactName, String contactTelNo, String contactEmail, String dossierTemplateNo, String password,
//			int viaPostal, String postalAddress, String postalCityCode, String postalCityName, String postalTelNo,
//			boolean online, boolean notification, String applicantNote, int originality, 
//			Date createDate, Date modifiedDate, Date submitDate, Date receiveDate, Date dueDate,
//			Date releaseDate, Date finishDate, Date cancellingDate, Date correctingDate, 
//			Date endorsementDate, Date extendDate,
//			Date processDate, ServiceContext context) throws PortalException;
	
	public Dossier publishDossier(long groupId, long dossierId, String referenceUid, int counter, String serviceCode,
			String serviceName, String govAgencyCode, String govAgencyName, String applicantName,
			String applicantIdType, String applicantIdNo, String applicantIdDate, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail, String dossierTemplateNo, String password,
			int viaPostal, String postalAddress, String postalCityCode, String postalCityName, String postalTelNo,
			boolean online, boolean notification, String applicantNote, int originality, 
			Date createDate, Date modifiedDate, Date submitDate, Date receiveDate, Date dueDate,
			Date releaseDate, Date finishDate, Date cancellingDate, Date correctingDate, 
			Date endorsementDate, Date extendDate,
			Date processDate, String dossierNo, String dossierStatus, String dossierStatusText, String dossierSubStatus, String dossierSubStatusText,
			long dossierActionId, String submissionNote, String lockState, String delegateName, String delegateIdNo, String delegateTelNo, String delegateEmail, 
			String delegateAddress, String delegateCityCode, String delegateCityName, String delegateDistrictCode, String delegateDistrictName, 
			String delegateWardCode, String delegateWardName, double durationCount, int durationUnit, String dossierName, String processNo,
			String metaData, ServiceContext context) throws PortalException;

	public List<User> getAssignUsersByStep(Dossier dossier, ProcessStep ps);
	public ProcessOption getProcessOption(long serviceProcessId, long dossierTemplateId);
	
	public Dossier initDossier(long groupId, long dossierId, String referenceUid, int counter, String serviceCode,
			String serviceName, String govAgencyCode, String govAgencyName, String applicantName,
			String applicantIdType, String applicantIdNo, String applicantIdDate, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail, String dossierTemplateNo, String password,
			int viaPostal, String postalAddress, String postalCityCode, String postalCityName, String postalTelNo,
			boolean online, boolean notification, String applicantNote, int originality, 
			ServiceInfo service,
			ServiceProcess serviceProcess,
			ProcessOption processOption,
			ServiceContext context) throws PortalException;
	
	public Dossier initUpdateDossier(long groupId, long id, String applicantName, String applicantIdType,
			String applicantIdNo, String applicantIdDate, String address, String cityCode, String cityName,
			String districtCode, String districtName, String wardCode, String wardName, String contactName,
			String contactTelNo, String contactEmail, String dossierTemplateNo, Integer viaPostal, String postalAddress,
			String postalCityCode, String postalCityName, String postalTelNo, String applicantNote, boolean isSameAsApplicant,
			String delegateName, String delegateIdNo, String delegateTelNo, String delegateEmail,
			String delegateAddress, String delegateCityCode, String delegateDistrictCode, String delegateWardCode,
			Long sampleCount, String dossierName, ServiceContext serviceContext);

	public Dossier initUpdateDossier(long groupId, long id, String applicantName, String applicantIdType,
			String applicantIdNo, String applicantIdDate, String address, String cityCode, String cityName,
			String districtCode, String districtName, String wardCode, String wardName, String contactName,
			String contactTelNo, String contactEmail, String dossierTemplateNo, Integer viaPostal, String postalAddress,
			String postalCityCode, String postalCityName, String postalTelNo, String applicantNote, boolean isSameAsApplicant,
			String delegateName, String delegateIdNo, String delegateTelNo, String delegateEmail,
			String delegateAddress, String delegateCityCode, String delegateDistrictCode, String delegateWardCode,
			Long sampleCount, String dossierName, String briefNote, ServiceContext serviceContext);

	public Dossier publishImportDossier(long groupId, long dossierId, String referenceUid, int counter,
			String serviceCode, String serviceName, String govAgencyCode, String govAgencyName, String applicantName,
			String applicantType, String applicantIdNo, Date applicantIdDate, String address, String contactName,
			String contactTelNo, String contactEmail, String dossierNo, String dossierStatus, String dossierStatusText,
			Boolean online, int originality, int sampleCount, Double durationCount, Integer durationUnit,
			Date createDate, Date modifiedDate, Date submitDate, Date receiveDate, Date dueDate, Date releaseDate,
			Date finishDate, String dossierTemplateNo, String dossierTemplateName, ServiceContext serviceContext);
}
