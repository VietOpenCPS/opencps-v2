package org.opencps.dossiermgt.listenner;

import java.util.List;

import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierLog;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLogLocalServiceUtil;
import org.opencps.usermgt.action.impl.EmployeeActions;
import org.opencps.usermgt.action.impl.JobposActions;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.JobPos;
import org.osgi.service.component.annotations.Component;

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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import backend.utils.APIDateTimeUtils;

@Component(immediate = true, service = ModelListener.class)
public class DossierListenner extends BaseModelListener<Dossier> {

	@Override
	public void onAfterCreate(Dossier model) throws ModelListenerException {
		
		/*
		 * 04/01/2017 ThanhNV: 
		 * add doossier log use DossierListennerUtils
		 */
		DossierListennerUltils.createDossierLog(model, false, false);
	
	}

	@Override
	public void onAfterUpdate(Dossier model) throws ModelListenerException {
		_log.info("Dossiser UpDate.....");
		
		//case application has request to cancel Dossier
		if (Validator.isNull(modelBeforeUpdate.getCancellingDate()) && Validator.isNotNull(model.getCancellingDate())) {
			//update dossier log
			_log.info("UPDATE DOSSIER LOG.....");

			ServiceContext serviceContext = new ServiceContext();

			EmployeeActions employeeActions = new EmployeeActions();

			JobposActions jobposActions = new JobposActions();

			try {

				long userId = model.getUserId();

				Employee employee = employeeActions.getEmployeeByMappingUserId(model.getGroupId(), userId);

				long mainJobposId = employee != null ? employee.getMainJobPostId() : 0;

				long dossierId = model.getDossierId();

				String jobPosName = StringPool.BLANK;

				if (mainJobposId > 0) {

					JobPos jobPos = jobposActions.getJobPos(mainJobposId);

					jobPosName = (jobPos != null && Validator.isNotNull(jobPos.getTitle())) ? jobPos.getTitle()
							: StringPool.BLANK;
				}

				String content = "yeu-cau-huy-ho-so";

				// JSONArray payloads = JSONFactoryUtil.createJSONArray();

				JSONObject payload = JSONFactoryUtil.createJSONObject();

				JSONArray files = JSONFactoryUtil.createJSONArray();
				
				
				payload.put("jobPosName", "TEST");
				payload.put("stepName", StringPool.BLANK);
				payload.put("stepInstruction", StringPool.BLANK);
				payload.put("files", files);

				// payloads.put(payload);

				serviceContext.setCompanyId(model.getCompanyId());
				serviceContext.setUserId(userId);

				DossierLogLocalServiceUtil.addDossierLog(model.getGroupId(), model.getDossierId(),
						model.getApplicantName(), content, "PROCESS_TYPE", payload.toString(), serviceContext);

			} catch (SystemException | PortalException e) {
				_log.error(e);
			}
			
		}
		
	
	}

	@Override
	public void onBeforeUpdate(Dossier model) throws ModelListenerException {
		try {
			modelBeforeUpdate = DossierLocalServiceUtil.getDossier(model.getPrimaryKey());
		} catch (Exception e) {
			_log.error(e);
		}
	}

	public static Dossier modelBeforeUpdate;

	private Log _log = LogFactoryUtil.getLog(DossierListenner.class.getName());
}
