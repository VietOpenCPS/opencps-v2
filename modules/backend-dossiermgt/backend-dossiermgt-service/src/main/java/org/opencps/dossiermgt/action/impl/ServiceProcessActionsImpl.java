package org.opencps.dossiermgt.action.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.action.ServiceProcessActions;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessSequence;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessSequenceLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.persistence.ProcessStepRolePK;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
			String processName, String description, Double durationCount, int durationUnit, long counter,
			boolean generateDossierNo, String dossierNoPattern, boolean generateDueDate, String dueDatePattern,
			boolean generatePassword, boolean directNotification, String serverNo, String paymentFee,
			ServiceContext context) throws PortalException {

		return ServiceProcessLocalServiceUtil.updateServiceProcess(groupId, serviceProcessId, processNo, processName,
				description, durationCount, durationUnit, counter, generateDossierNo, dossierNoPattern, generateDueDate,
				dueDatePattern, generatePassword, directNotification, serverNo, paymentFee, context);
	}

	@Override
	public ServiceProcess removeServiceProcess(long userId, long groupId, long serviceProcessId,
			ServiceContext serviceContext) throws PortalException {

		boolean flagProRole = deleteAllProcessRole(userId, groupId, serviceProcessId, serviceContext);
		boolean flagStep = deleteAllProcessStep(userId, groupId, serviceProcessId, serviceContext);
		boolean flagProAction = deleteAllProcessAction(userId, groupId, serviceProcessId, serviceContext);
		boolean flagSequence = deleteAllProcessSequence(userId, groupId, serviceProcessId, serviceContext);
		
		if (flagProRole && flagStep && flagProAction && flagSequence) {
			return ServiceProcessLocalServiceUtil.removeServiceProcess(serviceProcessId, groupId);
		}
		return null;
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
			boolean moderator, String condition, String roleCode, String roleName) throws PortalException {

		return ServiceProcessRoleLocalServiceUtil.updateServiceProcessRole(groupId, serviceProcessId, roleId, moderator,
				condition, roleCode, roleName);
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
			String customProcessUrl, String stepInstruction, String briefNote, boolean editable, String lockState, Integer checkInput,
			ServiceContext context) throws PortalException {
		
		ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(oldStepCode, groupId, serviceProcessId);
		
		long processStepId = 0;
		
		if (Validator.isNotNull(step)) {
			processStepId = step.getProcessStepId();
		}
		
		return ProcessStepLocalServiceUtil.updateProcessStep(groupId, processStepId, newStepCode, stepName,
				serviceProcessId, sequenceNo, dossierStatus, dossierSubStatus, durationCount, customProcessUrl,
				stepInstruction, briefNote, editable, lockState, checkInput, context);
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
	public ProcessStepRole updateProcessStepRole(long processStepId, long roleId, boolean moderator, String condition, String roleCode, String roleName)
			throws PortalException {
		return ProcessStepRoleLocalServiceUtil.updateProcessStepRole(processStepId, roleId, moderator, condition, roleCode, roleName);
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
			String actionName, int allowAssignUser, long assignUserId, Integer requestPayment, String paymentFee,
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
			String actionName, int allowAssignUser, long assignUserId, Integer requestPayment, String paymentFee,
			String createDossierFiles, String returnDossierFiles, String makeBriefNote, String syncActionCode,
			boolean rollbackable, boolean createDossierNo, boolean eSignature, String signatureType, String configNote,
			String dossierTemplateNo, ServiceContext context) throws PortalException {
		
		
		
		return ProcessActionLocalServiceUtil.updateProcessAction(groupId, processActionId, serviceProcessId,
				preStepCode, postStepCode, autoEvent, preCondition, actionCode, actionName, allowAssignUser,
				assignUserId, requestPayment, paymentFee, createDossierFiles, returnDossierFiles, makeBriefNote,
				syncActionCode, rollbackable, createDossierNo, eSignature, signatureType, configNote, dossierTemplateNo, context);
	}

	@Override
	public ServiceProcess getServiceProcessByCode(long groupId, String serviceCode, String govAgencyCode,
			String dossierTemplateNo) throws PortalException {
		return ServiceProcessLocalServiceUtil.getServiceByCode(groupId, serviceCode, govAgencyCode,
				dossierTemplateNo);
	}

	@Override
	public long updateServiceProcessDB(long userId, long groupId, String processNo, String processName,
			String description, Double durationCount, Integer durationUnit, boolean generatePassword, String serverNo,
			String serverName, String dossierNoPattern, String dueDatePattern, ServiceContext serviceContext)
			throws PortalException {

		ServiceProcess service =  ServiceProcessLocalServiceUtil.updateServiceProcessDB(userId, groupId, processNo, processName, description,
				durationCount, durationUnit, generatePassword, serverNo, serverName, dossierNoPattern, dueDatePattern,
				serviceContext);
		if (service != null) {
			return service.getServiceProcessId();
		}
		return 0;
	}

	@Override
	public void updateServiceProcessRoleDB(long userId, long groupId, long serviceProcessId, long roleId,String roleCode, String roleName, boolean moderator,
			String condition, ServiceContext serviceContext) {

		ServiceProcessRoleLocalServiceUtil.updateServiceProcessRoleDB(groupId, serviceProcessId, roleId, roleCode,
				roleName, moderator, condition, serviceContext);
	}

	@Override
	public long updateProcessStepDB(long userId, long groupId, long serviceProcessId, String stepCode, String stepName,
			String sequenceNo, String groupName, String dossierStatus, String dossierSubStatus, Double durationCount,
			String instructionNote, String briefNote, String roleAsStep, Integer checkInput,
			ServiceContext serviceContext) throws PortalException {

		ProcessStep processStep = ProcessStepLocalServiceUtil.updateProcessStepDB(userId, groupId, serviceProcessId,
				stepCode, stepName, sequenceNo, groupName, dossierStatus, dossierSubStatus, durationCount,
				instructionNote, briefNote, roleAsStep, checkInput, serviceContext);
		if (processStep != null) {
			return processStep.getProcessStepId();
		}
		return 0;
	}

	@Override
	public void updateProcessStepRoleDB(long userId, long groupId, long processStepId, long roleId, String roleCode,
			String roleName, boolean moderator, String condition, ServiceContext serviceContext) {

		ProcessStepRoleLocalServiceUtil.updateProcessStepRoleDB(userId, groupId, processStepId, roleId, roleCode,
				roleName, moderator, condition, serviceContext);
	}

	@Override
	public void updateProcessActionDB(long userId, long groupId, long serviceProcessId, String actionCode,
			String actionName, String preStepCode, String postStepCode, String autoEvent, String preCondition,
			int allowAssignUser, long assignUserId, String assignUserName, Integer requestPayment, String paymentFee,
			String createDossierFiles, String returnDossierFiles, boolean eSignature, String signatureType,
			String createDossiers, ServiceContext serviceContext) throws PortalException {

		ProcessActionLocalServiceUtil.updateProcessActionDB(userId, groupId, serviceProcessId, actionCode, actionName,
				preStepCode, postStepCode, autoEvent, preCondition, allowAssignUser, assignUserId, assignUserName,
				requestPayment, paymentFee, createDossierFiles, returnDossierFiles, eSignature, signatureType,
				createDossiers, serviceContext);
	}

	@Override
	public boolean deleteAllProcessAction(long userId, long groupId, long serviceProcessId,
			ServiceContext serviceContext) {
		boolean flag = false;
		try {
			List<ProcessAction> actList = ProcessActionLocalServiceUtil.getProcessActionbyServiceProcessId(serviceProcessId);
			if (actList != null && actList.size() > 0) {
				for (ProcessAction act : actList) {
					ProcessActionLocalServiceUtil.deleteProcessAction(act);
					flag = true;
				}
			} else {
				flag = true;
			}
		}catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			return false;
		}

		return flag;
	}

	@Override
	public boolean deleteAllProcessRole(long userId, long groupId, long serviceProcessId,
			ServiceContext serviceContext) {
		boolean flag = false;
		try {
			List<ServiceProcessRole> roleList = ServiceProcessRoleLocalServiceUtil.findByS_P_ID(serviceProcessId);
			if (roleList != null && roleList.size() > 0) {
				for (ServiceProcessRole role : roleList) {
					ServiceProcessRoleLocalServiceUtil.deleteServiceProcessRole(role);
					flag = true;
				}
			} else {
				flag = true;
			}
		}catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			return false;
		}

		return flag;
	}

	private static Log _log = LogFactoryUtil.getLog(ServiceProcessActionsImpl.class);
	@Override
	public boolean deleteAllProcessStep(long userId, long groupId, long serviceProcessId,
			ServiceContext serviceContext) {
		boolean flag = false;
		try {
			List<ProcessStep> stepList = ProcessStepLocalServiceUtil.getProcessStepbyServiceProcessId(serviceProcessId);
			if (stepList != null && stepList.size() > 0) {
//				_log.info("stepList: "+stepList.size());
				long stepId = 0;
				for (ProcessStep step : stepList) {
					stepId = step.getProcessStepId();
//					_log.info("stepId: "+stepId);
					if (stepId > 0) {
						List<ProcessStepRole> stepRoleList = ProcessStepRoleLocalServiceUtil
								.findByP_S_ID(stepId);
//						_log.info("stepRoleList: "+stepRoleList.size());
						if (stepRoleList != null && stepRoleList.size() > 0) {
							for (ProcessStepRole stepRole : stepRoleList) {
								ProcessStepRoleLocalServiceUtil.deleteProcessStepRole(stepRole);
								flag = true;
							}
						} else {
							flag = true;
						}
					}
					if (flag) {
//						_log.info("STARTTT: ");
						ProcessStep processStep = ProcessStepLocalServiceUtil.deleteProcessStep(step);
						if (processStep == null) {
							flag = false;
						}
					}
				}
			} else {
				flag = true;
			}
		}catch (Exception e) {
//			e.printStackTrace();
			_log.debug(e);
			//_log.error(e);
			return false;
		}

		return flag;
	}

	@Override
	public boolean deleteAllProcessSequence(long userId, long groupId, long serviceProcessId,
			ServiceContext serviceContext) {
		boolean flag = false;
		try {
			List<ProcessSequence> seqList = ProcessSequenceLocalServiceUtil.getByServiceProcess(groupId, serviceProcessId);
			if (seqList != null && seqList.size() > 0) {
				for (ProcessSequence seq : seqList) {
					ProcessSequenceLocalServiceUtil.deleteProcessSequence(seq);
					flag = true;
				}
			} else {
				flag = true;
			}
		}catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			return false;
		}

		return flag;
	}

	@Override
	public void updateProcessSequenceDB(long userId, long groupId, long serviceProcessId, String sequenceNo,
			String sequenceName, String sequenceRole, Double durationCount, ServiceContext serviceContext)
			throws PortalException {

		ProcessSequenceLocalServiceUtil.updateProcessSequenceDB(userId, groupId, serviceProcessId, sequenceNo,
				sequenceName, sequenceRole, durationCount, serviceContext);
	}

	@Override
	public long getByRoleCode(long groupId, String roleCode) {

		JobPos job = JobPosLocalServiceUtil.getByJobCode(groupId, roleCode);
		if (job != null) {
			return job.getMappingRoleId();
		}
		return 0;
	}

}
