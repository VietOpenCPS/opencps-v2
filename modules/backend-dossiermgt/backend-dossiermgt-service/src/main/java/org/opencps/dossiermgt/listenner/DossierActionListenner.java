package org.opencps.dossiermgt.listenner;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierLog;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLogLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.usermgt.action.impl.EmployeeActions;
import org.opencps.usermgt.action.impl.JobposActions;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import backend.utils.APIDateTimeUtils;

@Component(immediate = true, service = ModelListener.class)
public class DossierActionListenner extends BaseModelListener<DossierAction> {
	public static final String DOSSIER_SATUS_DC_CODE = "DOSSIER_STATUS";
	
	private JSONObject getStatusText(long groupId, String collectionCode, String curStatus, String curSubStatus) {

		JSONObject jsonData = null;
		DictCollection dc = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(collectionCode, groupId);

		if (Validator.isNotNull(dc) && Validator.isNotNull(curStatus)) {
			jsonData = JSONFactoryUtil.createJSONObject();
			DictItem it = DictItemLocalServiceUtil.fetchByF_dictItemCode(curStatus, dc.getPrimaryKey(), groupId);
			if (Validator.isNotNull(it)) {
				jsonData.put(curStatus, it.getItemName());
				if (Validator.isNotNull(curSubStatus)) {
					DictItem dItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(curSubStatus, dc.getPrimaryKey(),
							groupId);
					if (Validator.isNotNull(dItem)) {
						jsonData.put(curSubStatus, dItem.getItemName());
					}
				}
			}
		}

		return jsonData;
	}
	
	@Override
	public void onAfterCreate(DossierAction model) throws ModelListenerException {

		_log.debug("START Dossier Action");
		Indexer<DossierLog> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(DossierLog.class);
		long userId = model.getUserId();
		long groupId = model.getGroupId();
		
		if (true) {

			ServiceContext serviceContext = new ServiceContext();

			EmployeeActions employeeActions = new EmployeeActions();

			JobposActions jobposActions = new JobposActions();

			try {

				Employee employee = employeeActions.getEmployeeByMappingUserId(model.getGroupId(), userId);

				long mainJobposId = employee != null ? employee.getMainJobPostId() : 0;

				long dossierId = model.getDossierId();

				String jobPosName = StringPool.BLANK;

				if (mainJobposId > 0) {

					JobPos jobPos = jobposActions.getJobPos(mainJobposId);

					jobPosName = (jobPos != null && Validator.isNotNull(jobPos.getTitle())) ? jobPos.getTitle()
							: StringPool.BLANK;
				}

				String content = Validator.isNotNull(model.getActionNote()) ? HtmlUtil.escape(model.getActionNote()) : StringPool.BLANK;

				JSONObject payload = JSONFactoryUtil.createJSONObject();

				JSONArray files = JSONFactoryUtil.createJSONArray();

				if (dossierId > 0) {

					List<DossierLog> dossierLogs = DossierLogLocalServiceUtil.getByDossierAndType(dossierId,
							DossierFileListenerMessageKeys.DOSSIER_LOG_CREATE_TYPE, QueryUtil.ALL_POS,
							QueryUtil.ALL_POS);

					for (DossierLog log : dossierLogs) {
						long dossierFileId = 0;

						try {
							JSONObject payloadFile = JSONFactoryUtil.createJSONObject(log.getPayload());

							dossierFileId = GetterUtil.getLong(payloadFile.get("dossierFileId"));
						} catch (Exception e) {
							_log.debug(e);
						}

						if (dossierFileId != 0) {
							DossierFile dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(dossierFileId);

							if (Validator.isNotNull(dossierFile) && dossierFile.getFileEntryId() > 0) {
								JSONObject file = JSONFactoryUtil.createJSONObject();

								file.put("dossierFileId", dossierFile.getDossierFileId());
								file.put("fileName", dossierFile.getDisplayName());
								file.put("createDate", APIDateTimeUtils.convertDateToString(dossierFile.getCreateDate(),
										APIDateTimeUtils._TIMESTAMP));
								files.put(file);
							}
						}
						

//						DossierLogLocalServiceUtil.deleteDossierLog(log);
						indexer.delete(log);
					}
					DossierLogLocalServiceUtil.deleteByDossierAndType(dossierId,
							DossierFileListenerMessageKeys.DOSSIER_LOG_CREATE_TYPE);
				}

				List<ProcessStep> lstProcessSteps = ProcessStepLocalServiceUtil.getBySC_SPID(model.getStepCode(), model.getServiceProcessId());
				
				if (lstProcessSteps.size() > 0) {
					JSONObject jsonDataStatusText = getStatusText(model.getGroupId(), DOSSIER_SATUS_DC_CODE, lstProcessSteps.get(0).getDossierStatus(), lstProcessSteps.get(0).getDossierSubStatus());
					payload.put("dossierStatusText", jsonDataStatusText != null ? jsonDataStatusText.getString(lstProcessSteps.get(0).getDossierStatus()) : StringPool.BLANK);					
				}
				payload.put("dossierActionId", model.getDossierActionId());
				payload.put("jobPosName", jobPosName);
				payload.put("stepName", model.getActionName());
				payload.put("stepCode", model.getStepCode());
				payload.put("stepInstruction", model.getStepInstruction());
				payload.put("files", files);

				serviceContext.setCompanyId(model.getCompanyId());
				serviceContext.setUserId(userId);

				ProcessAction processAction = ProcessActionLocalServiceUtil
						.getByNameActionNo(model.getServiceProcessId(), model.getActionCode(), model.getActionName());

				boolean ok = true;

				if (Validator.isNotNull(processAction)) {
					if ((processAction.getPreCondition().contains("cancelling")
							&& processAction.getAutoEvent().contains("timmer"))
							|| (processAction.getPreCondition().contains("correcting")
									&& processAction.getAutoEvent().contains("timmer"))
							|| (processAction.getPreCondition().contains("submitting"))
									&& processAction.getAutoEvent().contains("timmer")) {
						ok = false;

					}
				}

				if (Validator.isNotNull(processAction) && (processAction.getPreCondition().contains("reject_cancelling")
						|| processAction.getPreCondition().contains("reject_correcting")
						|| processAction.getPreCondition().contains("reject_submitting"))) {
					ok = false;
				}

				if (ok) {
					String userNameLog = StringPool.BLANK;
					if (userId > 0) {
						Employee emp = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
						userNameLog = emp != null ? emp.getFullName() : model.getActionUser();
					}
					DossierLogLocalServiceUtil.addDossierLog(model.getGroupId(), model.getDossierId(),
							userNameLog, content, "PROCESS_TYPE", payload.toString(),
							serviceContext);
				}

			} catch (SystemException | PortalException e) {
				_log.debug(e);
			}
		}

		if (Validator.isNotNull(model.getSyncActionCode()) && model.getSyncActionCode().length() != 0) {
			String content = "On DossiserAction Created";
			String notificationType = "";
			String payload = "";

			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setCompanyId(model.getCompanyId());
			serviceContext.setUserId(model.getUserId());

			try {
				String userNameLog = StringPool.BLANK;
				if (userId > 0) {
					Employee emp = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
					userNameLog = emp != null ? emp.getFullName() : model.getUserName();
				}
				DossierLogLocalServiceUtil.addDossierLog(model.getGroupId(), model.getDossierId(), userNameLog,
						content, notificationType, payload, serviceContext);
			} catch (SystemException | PortalException e) {
				_log.debug(e);
			}
		}
	}

//	private String getUserName(long userId, long groupId) {
//		String userName = StringPool.BLANK;
//
//		Employee employee = null;
//
//		Applicant applicant = null;
//
//		employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
//
//		if (Validator.isNotNull(employee)) {
//			return employee.getFullName();
//
//		}
//
//		applicant = ApplicantLocalServiceUtil.fetchByMappingID(userId);
//
//		if (Validator.isNotNull(applicant)) {
//			return applicant.getApplicantName();
//		}
//
//		return userName;
//	}

	private Log _log = LogFactoryUtil.getLog(DossierActionListenner.class.getName());
}