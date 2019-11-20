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
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierLog;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLogLocalServiceUtil;
import org.opencps.usermgt.action.impl.EmployeeActions;
import org.opencps.usermgt.action.impl.JobposActions;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.JobPos;
import org.osgi.service.component.annotations.Component;

import backend.utils.APIDateTimeUtils;

@Component(immediate = true, service = ModelListener.class)
public class DossierActionListenner extends BaseModelListener<DossierAction> {

	@Override
	public void onAfterCreate(DossierAction model) throws ModelListenerException {

		_log.debug("START Dossier Action");
		Indexer<DossierLog> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(DossierLog.class);
		long userId = model.getUserId();

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

							dossierFileId = GetterUtil.getLong(payloadFile.get(DossierFileTerm.DOSSIER_FILE_ID));
						} catch (Exception e) {
							_log.debug(e);
						}

						if (dossierFileId != 0) {
							DossierFile dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(dossierFileId);

							if (Validator.isNotNull(dossierFile) && dossierFile.getFileEntryId() > 0) {
								JSONObject file = JSONFactoryUtil.createJSONObject();

								file.put(DossierFileTerm.DOSSIER_FILE_ID, dossierFile.getDossierFileId());
								file.put(DossierFileTerm.FILE_NAME, dossierFile.getDisplayName());
								file.put(Field.CREATE_DATE, APIDateTimeUtils.convertDateToString(dossierFile.getCreateDate(),
										APIDateTimeUtils._TIMESTAMP));
								files.put(file);
							}
						}
						

						indexer.delete(log);
					}
					DossierLogLocalServiceUtil.deleteByDossierAndType(dossierId,
							DossierFileListenerMessageKeys.DOSSIER_LOG_CREATE_TYPE);
				}

				payload.put(DossierActionTerm.DOSSIERACTION_ID, model.getDossierActionId());
				payload.put(DossierActionTerm.JOB_POS_NAME, jobPosName);
				payload.put(DossierActionTerm.STEP_NAME, model.getActionName());
				payload.put(DossierActionTerm.STEP_CODE, model.getStepCode());
				payload.put(DossierActionTerm.STEP_INSTRUCTION, model.getStepInstruction());
				payload.put(DossierActionTerm.FILES, files);

				serviceContext.setCompanyId(model.getCompanyId());
				serviceContext.setUserId(userId);

			} catch (SystemException | PortalException e) {
				_log.debug(e);
			}
		}

	}

	private Log _log = LogFactoryUtil.getLog(DossierActionListenner.class.getName());
}