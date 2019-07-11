package org.opencps.dossiermgt.action;

import java.util.LinkedHashMap;

import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.ServiceProcessRole;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

public interface ServiceProcessActions {

	public JSONObject getServiceProcesses(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) throws PortalException;

	public ServiceProcess getServiceProcessDetail(long serviceProcessId) throws PortalException;

	public ServiceProcess updateServiceProcess(long groupId, long serviceProcessId, String processNo,
			String processName, String description, Double durationCount, int durationUnit, long counter,
			boolean generateDossierNo, String dossierNoPattern, boolean generateDueDate, String dueDatePattern,
			boolean generatePassword, boolean directNotification, String serverNo, String paymentFee,
			ServiceContext context) throws PortalException;

	public ServiceProcess removeServiceProcess(long userId, long groupId, long serviceProcessId,
			ServiceContext serviceContext) throws PortalException;

	public JSONObject getServiceProcessRoles(long serviceProcessId) throws PortalException;

	public ServiceProcessRole updateServiceProcessRole(long groupId, long serviceProcessId, long roleId,
			boolean moderator, String condition, String roleCode, String roleName) throws PortalException;

	public ServiceProcessRole removeServiceProcessRole(long serviceProcessId, long roleId) throws PortalException;

	public JSONObject getProcessSteps(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) throws PortalException;

	public ProcessStep getProcessStepDetail(long processStepId) throws PortalException;

	public ProcessStep updateProcessStep(long groupId, String oldStepCode, String newStepCode, String stepName,
			long serviceProcessId, String sequenceNo, String dossierStatus, String dossierSubStatus, int durationCount,
			String customProcessUrl, String stepInstruction, String briefNote, boolean editable, String lockState, Integer checkInput,
			ServiceContext context) throws PortalException;

	public ProcessStep deleteProcessStep(String stepCode, long groupId, long serviceProcessId) throws PortalException;

	public JSONObject getProcessStepRoles(long processStepId) throws PortalException;

	public ProcessStepRole getProcessStepRoleDetail(long processStepId, long roleId) throws PortalException;

	public ProcessStepRole updateProcessStepRole(long processStepId, long roleId, boolean moderator, String condition,
			String roleCode, String roleName) throws PortalException;

	public ProcessStepRole deleteProcessStepRole(long processStepId, long roleId) throws PortalException;

	public JSONObject getProcessActions(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) throws PortalException;

	public ProcessAction getProcessActionDetail(long processActionId) throws PortalException;
	
	@Deprecated
	public ProcessAction updateProcessAction(long groupId, long processActionId, long serviceProcessId,
			String preStepCode, String postStepCode, String autoEvent, String preCondition, String actionCode,
			String actionName, int allowAssignUser, long assignUserId, Integer requestPayment, String paymentFee,
			String createDossierFiles, String returnDossierFiles, String makeBriefNote, String syncActionCode,
			boolean rollbackable, ServiceContext context) throws PortalException;

	public ProcessAction updateProcessAction(long groupId, long processActionId, long serviceProcessId,
			String preStepCode, String postStepCode, String autoEvent, String preCondition, String actionCode,
			String actionName, int allowAssignUser, long assignUserId, Integer requestPayment, String paymentFee,
			String createDossierFiles, String returnDossierFiles, String makeBriefNote, String syncActionCode,
			boolean rollbackable, boolean createDossierNo, boolean eSignature, String signatureType, String configNote,
			String dossierTemplateNo, ServiceContext context) throws PortalException;

	public ProcessAction deleteProcessAction(long processActionId) throws PortalException;

	//LamTV_Process payment fee
	public ServiceProcess getServiceProcessByCode(long groupId, String serviceCode, String govAgencyCode,
			String dossierTemplateNo) throws PortalException;

	//LamTV_Process output DB
	public long updateServiceProcessDB(long userId, long groupId, String processNo, String processName, String description,
			Double durationCount, Integer durationUnit, boolean generatePassword, String serverNo, String serverName,
			String dossierNoPattern, String dueDatePattern, ServiceContext serviceContext) throws PortalException;

	public void updateServiceProcessRoleDB(long userId, long groupId, long serviceProcessId, long roleId,
			String roleCode, String roleName, boolean moderator, String condition, ServiceContext serviceContext);

	public long updateProcessStepDB(long userId, long groupId, long serviceProcessId, String stepCode, String stepName,
			String sequenceNo, String groupName, String dossierStatus, String dossierSubStatus, Double durationCount,
			String instructionNote, String briefNote, String roleAsStep, Integer checkInput,
			ServiceContext serviceContext) throws PortalException;

	public void updateProcessStepRoleDB(long userId, long groupId, long processStepId, long roleId, String roleCode,
			String roleName, boolean moderator, String condition, ServiceContext serviceContext);

	public void updateProcessActionDB(long userId, long groupId, long serviceProcessId, String actionCode,
			String actionName, String preStepCode, String postStepCode, String autoEvent, String preCondition,
			int allowAssignUser, long assignUserId, String assignUserName, Integer requestPayment, String paymentFee,
			String createDossierFiles, String returnDossierFiles, boolean eSignature, String signatureType,
			String createDossiers, ServiceContext serviceContext) throws PortalException;

	public boolean deleteAllProcessAction(long userId, long groupId, long serviceProcessId,
			ServiceContext serviceContext);

	public boolean deleteAllProcessRole(long userId, long groupId, long serviceProcessId,
			ServiceContext serviceContext);

	public boolean deleteAllProcessStep(long userId, long groupId, long serviceProcessId,
			ServiceContext serviceContext);

	public boolean deleteAllProcessSequence(long userId, long groupId, long serviceProcessId,
			ServiceContext serviceContext);

	public void updateProcessSequenceDB(long userId, long groupId, long serviceProcessId, String sequenceNo,
			String sequenceName, String sequenceRole, Double durationCount, ServiceContext serviceContext)
			throws PortalException;

	public long getByRoleCode(long groupId, String roleCode);

}
