package org.opencps.dossiermgt.action.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.action.ServiceProcessActions;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.persistence.ProcessStepRolePK;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

public class ServiceProcessActionsImpl implements ServiceProcessActions {

	@Override
	public JSONObject getServiceProcesses(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) throws PortalException {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		Hits hits = null;
		long count = 0;

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		hits = ServiceProcessLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);
		count = ServiceProcessLocalServiceUtil.countLucene(params, searchContext);

		result.put("total", count);
		result.put("data", hits.toList());

		return result;
	}

	@Override
	public ServiceProcess getServiceProcessDetail(long serviceProcessId) throws PortalException {

		return ServiceProcessLocalServiceUtil.getServiceProcess(serviceProcessId);
	}

	@Override
	public ServiceProcess updateServiceProcess(long groupId, long serviceProcessId, String processNo,
			String processName, String description, int durationCount, int durationUnit, long counter,
			boolean generateDossierNo, String dossierNoPattern, boolean generateDueDate, String dueDatePattern,
			boolean generatePassword, boolean directNotification, String serverNo, ServiceContext context)
			throws PortalException {

		return ServiceProcessLocalServiceUtil.updateServiceProcess(groupId, serviceProcessId, processNo, processName,
				description, durationCount, durationUnit, counter, generateDossierNo, dossierNoPattern, generateDueDate,
				dueDatePattern, generatePassword, directNotification, serverNo, context);
	}

	@Override
	public ServiceProcess removeServiceProcess(long serviceProcessId, long groupId) throws PortalException {
		return ServiceProcessLocalServiceUtil.removeServiceProcess(serviceProcessId, groupId);
	}

	@Override
	public JSONObject getServiceProcessRoles(long serviceProcessId) throws PortalException {

		JSONObject results = JSONFactoryUtil.createJSONObject();

		List<ServiceProcessRole> processRoles = ServiceProcessRoleLocalServiceUtil.findByS_P_ID(serviceProcessId);
		long count = processRoles.size();

		results.put("total", count);
		results.put("data", processRoles);

		return results;
	}

	@Override
	public ServiceProcessRole updateServiceProcessRole(long groupId, long serviceProcessId, long roleId,
			boolean moderator, String condition) throws PortalException {

		return ServiceProcessRoleLocalServiceUtil.updateServiceProcessRole(groupId, serviceProcessId, roleId, moderator,
				condition);
	}

	@Override
	public ServiceProcessRole removeServiceProcessRole(long serviceProcessId, long roleId) throws PortalException {
		return ServiceProcessRoleLocalServiceUtil.removeServiceProcessRole(serviceProcessId, roleId);
	}

	@Override
	public JSONObject getProcessSteps(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) throws PortalException {

		JSONObject results = JSONFactoryUtil.createJSONObject();

		Hits hits = null;
		long count = 0;

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		hits = ProcessStepLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);
		count = ProcessStepLocalServiceUtil.countLucene(params, searchContext);

		results.put("total", count);
		results.put("data", hits.toList());

		return results;
	}

	@Override
	public ProcessStep getProcessStepDetail(long processStepId) throws PortalException {
		return ProcessStepLocalServiceUtil.getProcessStep(processStepId);
	}

	@Override
	public ProcessStep updateProcessStep(long groupId, String oldStepCode, String newStepCode, String stepName,
			long serviceProcessId, String sequenceNo, String dossierStatus, String dossierSubStatus, int durationCount,
			String customProcessUrl, String stepInstruction, String briefNote, boolean editable, ServiceContext context)
			throws PortalException {
		
		ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(oldStepCode, groupId, serviceProcessId);
		
		long processStepId = 0;
		
		if (Validator.isNotNull(step)) {
			processStepId = step.getProcessStepId();
		}
		
		return ProcessStepLocalServiceUtil.updateProcessStep(groupId, processStepId, newStepCode, stepName,
				serviceProcessId, sequenceNo, dossierStatus, dossierSubStatus, durationCount, customProcessUrl,
				stepInstruction, briefNote, editable, context);
	}

	@Override
	public ProcessStep deleteProcessStep(String stepCode, long groupId, long serviceProcessId) throws PortalException {
		
		ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(stepCode, groupId, serviceProcessId);
		
		return ProcessStepLocalServiceUtil.deleteProcessStep(step.getPrimaryKey());
	}

	@Override
	public JSONObject getProcessStepRoles(long processStepId) throws PortalException {
		JSONObject results = JSONFactoryUtil.createJSONObject();

		List<ProcessStepRole> processStepRoles = ProcessStepRoleLocalServiceUtil.findByP_S_ID(processStepId);
		long count = processStepRoles.size();

		results.put("total", count);
		results.put("data", processStepRoles);

		return results;
	}

	@Override
	public ProcessStepRole getProcessStepRoleDetail(long processStepId, long roleId) throws PortalException {
		ProcessStepRolePK pk = new ProcessStepRolePK(processStepId, roleId);

		return ProcessStepRoleLocalServiceUtil.getProcessStepRole(pk);
	}

	@Override
	public ProcessStepRole updateProcessStepRole(long processStepId, long roleId, boolean moderator, String condition)
			throws PortalException {
		return ProcessStepRoleLocalServiceUtil.updateProcessStepRole(processStepId, roleId, moderator, condition);
	}

	@Override
	public ProcessStepRole deleteProcessStepRole(long processStepId, long roleId) throws PortalException {
		return ProcessStepRoleLocalServiceUtil.removeProcessStepRole(processStepId, roleId);
	}

	@Override
	public JSONObject getProcessActions(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) throws PortalException {
		JSONObject results = JSONFactoryUtil.createJSONObject();

		Hits hits = null;
		long count = 0;

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		hits = ProcessActionLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);
		count = ProcessActionLocalServiceUtil.searchCount(params, searchContext);

		results.put("total", count);
		results.put("data", hits.toList());

		return results;
	}

	@Override
	public ProcessAction getProcessActionDetail(long processActionId) throws PortalException {

		return ProcessActionLocalServiceUtil.getProcessAction(processActionId);
	}

	@Deprecated
	@Override
	public ProcessAction updateProcessAction(long groupId, long processActionId, long serviceProcessId,
			String preStepCode, String postStepCode, String autoEvent, String preCondition, String actionCode,
			String actionName, boolean allowAssignUser, long assignUserId, boolean requestPayment, String paymentFee,
			String createDossierFiles, String returnDossierFiles, String makeBriefNote, String syncActionCode,
			boolean rollbackable, ServiceContext context) throws PortalException {

		return ProcessActionLocalServiceUtil.updateProcessAction(groupId, processActionId, serviceProcessId,
				preStepCode, postStepCode, autoEvent, preCondition, actionCode, actionName, allowAssignUser,
				assignUserId, requestPayment, paymentFee, createDossierFiles, returnDossierFiles, makeBriefNote,
				syncActionCode, rollbackable, context);
	}

	@Override
	public ProcessAction deleteProcessAction(long processActionId) throws PortalException {
		
		return ProcessActionLocalServiceUtil.removeProcessAction(processActionId);
	}

	@Override
	public ProcessAction updateProcessAction(long groupId, long processActionId, long serviceProcessId,
			String preStepCode, String postStepCode, String autoEvent, String preCondition, String actionCode,
			String actionName, boolean allowAssignUser, long assignUserId, boolean requestPayment, String paymentFee,
			String createDossierFiles, String returnDossierFiles, String makeBriefNote, String syncActionCode,
			boolean rollbackable, boolean createDossierNo, boolean eSignature, String configNote,
			String dossierTemplateNo, ServiceContext context) throws PortalException {
		
		
		
		return ProcessActionLocalServiceUtil.updateProcessAction(groupId, processActionId, serviceProcessId,
				preStepCode, postStepCode, autoEvent, preCondition, actionCode, actionName, allowAssignUser,
				assignUserId, requestPayment, paymentFee, createDossierFiles, returnDossierFiles, makeBriefNote,
				syncActionCode, rollbackable, createDossierNo, eSignature,configNote, dossierTemplateNo, context);
	}
	
	

}
