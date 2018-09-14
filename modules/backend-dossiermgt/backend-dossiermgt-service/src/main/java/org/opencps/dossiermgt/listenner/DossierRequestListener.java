package org.opencps.dossiermgt.listenner;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierRequestUD;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLogLocalServiceUtil;
import org.opencps.usermgt.action.impl.EmployeeActions;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class DossierRequestListener extends BaseModelListener<DossierRequestUD>{
	@Override
	public void onAfterCreate(DossierRequestUD model) throws ModelListenerException {
		
		ServiceContext serviceContext = new ServiceContext();

		EmployeeActions employeeActions = new EmployeeActions();

//		JobposActions jobposActions = new JobposActions();

		try {

			long userId = model.getUserId();

			Employee employee = employeeActions.getEmployeeByMappingUserId(model.getGroupId(), userId);

			long mainJobposId = employee != null ? employee.getMainJobPostId() : 0;

			//long dossierId = model.getDossierId();

//			String jobPosName = StringPool.BLANK;

			if (mainJobposId > 0) {

//				JobPos jobPos = jobposActions.getJobPos(mainJobposId);

//				jobPosName = (jobPos != null && Validator.isNotNull(jobPos.getTitle())) ? jobPos.getTitle()
//						: StringPool.BLANK;
			}

			//TODO: Hot fix
			String requestType = model.getRequestType();
			long companyId = model.getCompanyId();
			long groupId = model.getGroupId();
			boolean flagRequestType = false;
			if (Validator.isNotNull(requestType)) {
				if (requestType.toLowerCase().contentEquals("reject_submitting")
					|| requestType.toLowerCase().contentEquals("reject_correcting")
					|| requestType.toLowerCase().contentEquals("reject_cancelling")) {
					flagRequestType = true;
				}
			}
			if (Validator.isNotNull(requestType)) {
				if (requestType.toLowerCase().contentEquals("submitting")
						|| requestType.toLowerCase().contentEquals("correcting")
						|| requestType.toLowerCase().contentEquals("cancelling")) {
					if (companyId == 0 && groupId == 55217) {
						flagRequestType = true;
					}
				}
			}
			if (!flagRequestType) {

				String content = model.getComment();


				JSONObject payload = JSONFactoryUtil.createJSONObject();

				
				payload.put("stepName", "type_"+model.getRequestType());
				
				String userName = getUserName(userId, model.getGroupId());

				// payloads.put(payload);

				serviceContext.setCompanyId(20116l);
				serviceContext.setUserId(userId);

				DossierLogLocalServiceUtil.addDossierLog(model.getGroupId(), model.getDossierId(),
						userName, content, "PROCESS_TYPE", payload.toString(), serviceContext);
				
				// Add applicationNote
				
				Dossier dossier = DossierLocalServiceUtil.fetchDossier(model.getDossierId());
				
				String dossierNote = _buildDossierNote(dossier, content, dossier.getGroupId(), "DN");
				
				dossier.setApplicantNote(dossierNote);
				
				DossierLocalServiceUtil.syncDossier(dossier);
			}

		} catch (SystemException | PortalException e) {
			_log.error(e);
		}
		
	}
	
	
	private String _buildDossierNote(Dossier dossier, String actionNote, long groupId, String type) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String defaultTimezone = TimeZone.getDefault().getID();
		sdf.setTimeZone(TimeZone.getTimeZone(defaultTimezone));
		Date date = new Date();

		StringBuilder sb = new StringBuilder();

		String oldNote = dossier.getApplicantNote();

		if (Validator.isNotNull(oldNote) && oldNote.contains("<br>")) {
			if (Validator.isNotNull(actionNote)) {
				if (groupId != 55217) {
					sb.append("<br>");
					sb.append("[" + sdf.format(date) + "]");
					sb.append(": ");
					sb.append(actionNote);
					sb.append(oldNote);
				} else {
					sb.append("<br>");
					sb.append("[" + sdf.format(date) + "]");
					sb.append(": ");
					sb.append(actionNote);
				}
			} else {
				if (groupId != 55217) {
					sb.append(oldNote);
				}
			}
		} else if (Validator.isNotNull(actionNote)) {
			sb.append("<br>");
			sb.append("[" + sdf.format(date) + "]");
			sb.append(": ");
			sb.append(actionNote);
		}

		return sb.toString();

	}
	
	private String getUserName(long userId, long groupId) {
		String userName = StringPool.BLANK;
		
		Employee employee = null;
		
		Applicant applicant = null;
		
		employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
		
		if (Validator.isNotNull(employee)) {
			return employee.getFullName();
			
		}
		
		applicant = ApplicantLocalServiceUtil.fetchByMappingID(userId);
		
		if (Validator.isNotNull(applicant)) {
			return applicant.getApplicantName();
		}
		
		return userName;
	}
	
	private Log _log = LogFactoryUtil.getLog(DossierRequestListener.class.getName());

}
