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
			String processName, String description, int durationCount, int durationUnit, long counter,
			boolean generateDossierNo, String dossierNoPattern, boolean generateDueDate, String dueDatePattern,
			boolean generatePassword, boolean directNotification, String serverNo, ServiceContext context)
			throws PortalException;

	public ServiceProcess removeServiceProcess(long serviceProcessId, long groupId) throws PortalException;

	public JSONObject getServiceProcessRoles(long serviceProcessId) throws PortalException;

	public ServiceProcessRole updateServiceProcessRole(long groupId, long serviceProcessId, long roleId,
			boolean moderator, String condition) throws PortalException;

	public ServiceProcessRole removeServiceProcessRole(long serviceProcessId, long roleId) throws PortalException;

	public JSONObject getProcessSteps(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) throws PortalException;

	public ProcessStep getProcessStepDetail(long processStepId) throws PortalException;

	public ProcessStep updateProcessStep(long groupId, String oldStepCode, String newStepCode, String stepName,
			long serviceProcessId, String sequenceNo, String dossierStatus, String dossierSubStatus, int durationCount,
			String customProcessUrl, String stepInstruction, String briefNote, boolean editable, ServiceContext context)
			throws PortalException;

	public ProcessStep deleteProcessStep(String stepCode, long groupId, long serviceProcessId) throws PortalException;

	public JSONObject getProcessStepRoles(long processStepId) throws PortalException;

	public ProcessStepRole getProcessStepRoleDetail(long processStepId, long roleId) throws PortalException;

	public ProcessStepRole updateProcessStepRole(long processStepId, long roleId, boolean moderator, String condition)
			throws PortalException;

	public ProcessStepRole deleteProcessStepRole(long processStepId, long roleId) throws PortalException;

	public JSONObject getProcessActions(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) throws PortalException;

	public ProcessAction getProcessActionDetail(long processActionId) throws PortalException;
	
	@Deprecated
	public ProcessAction updateProcessAction(long groupId, long processActionId, long serviceProcessId,
			String preStepCode, String postStepCode, String autoEvent, String preCondition, String actionCode,
			String actionName, boolean allowAssignUser, long assignUserId, boolean requestPayment, String paymentFee,
			String createDossierFiles, String returnDossierFiles, String makeBriefNote, String syncActionCode,
			boolean rollbackable, ServiceContext context) throws PortalException;

	public ProcessAction updateProcessAction(long groupId, long processActionId, long serviceProcessId,
			String preStepCode, String postStepCode, String autoEvent, String preCondition, String actionCode,
			String actionName, boolean allowAssignUser, long assignUserId, boolean requestPayment, String paymentFee,
			String createDossierFiles, String returnDossierFiles, String makeBriefNote, String syncActionCode,
			boolean rollbackable, boolean createDossierNo, boolean eSignature, String configNote, ServiceContext context) throws PortalException;

	public ProcessAction deleteProcessAction(long processActionId) throws PortalException;

}
