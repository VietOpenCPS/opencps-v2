package org.opencps.dossiermgt.listenner;

import org.opencps.dossiermgt.model.DossierRequestUD;
import org.opencps.dossiermgt.service.DossierLogLocalServiceUtil;
import org.opencps.usermgt.action.impl.EmployeeActions;
import org.opencps.usermgt.action.impl.JobposActions;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.JobPos;
import org.osgi.service.component.annotations.Component;

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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

@Component(immediate = true, service = ModelListener.class)
public class DossierRequestListener extends BaseModelListener<DossierRequestUD>{
	@Override
	public void onAfterCreate(DossierRequestUD model) throws ModelListenerException {
		
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

			String content = model.getComment();

			// JSONArray payloads = JSONFactoryUtil.createJSONArray();

			JSONObject payload = JSONFactoryUtil.createJSONObject();

			JSONArray files = JSONFactoryUtil.createJSONArray();
			
			
			payload.put("stepName", "type_"+model.getRequestType());

			// payloads.put(payload);

			serviceContext.setCompanyId(20116l);
			serviceContext.setUserId(userId);

			DossierLogLocalServiceUtil.addDossierLog(model.getGroupId(), model.getDossierId(),
					model.getUserName(), content, "PROCESS_TYPE", payload.toString(), serviceContext);

		} catch (SystemException | PortalException e) {
			_log.error(e);
		}
		

	}
	
	private Log _log = LogFactoryUtil.getLog(DossierRequestListener.class.getName());

}
