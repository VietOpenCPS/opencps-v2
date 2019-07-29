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

import java.util.Date;

import org.opencps.dossiermgt.action.util.DossierMgtUtils;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLogLocalServiceUtil;
import org.opencps.usermgt.action.impl.EmployeeActions;
import org.opencps.usermgt.action.impl.JobposActions;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class DossierListenner extends BaseModelListener<Dossier> {

	private static final String ORIGINAL_TODO = "2,3";

	@Override
	public void onAfterCreate(Dossier model) throws ModelListenerException {
		
		/*
		 * 04/01/2017 ThanhNV: 
		 * add doossier log use DossierListennerUtils
		 */
		if (OpenCPSConfigUtil.isDossierLogEnable()) {
			DossierListennerUltils.createDossierLog(model, false, false);
		}
		//Add Applicant
		if (ORIGINAL_TODO.contains(String.valueOf(model.getOriginality()))) {
			//long groupId = model.getGroupId();
			long userId = model.getUserId();
			long companyId = model.getCompanyId();
			String applicantIdNo = model.getApplicantIdNo();
			String applicantName = model.getApplicantName();
			String applicantIdType = model.getApplicantIdType();
			Date applicantIdDate = model.getApplicantIdDate();
			String address = model.getAddress();
			String cityCode = model.getCityCode();
			String cityName = model.getCityName();
			String districtCode = model.getDistrictCode();
			String districtName = model.getDistrictName();
			String wardCode = model.getWardCode();
			String wardName = model.getWardName();
			String contactName = model.getContactName();
			String contactTelNo = model.getContactTelNo();
			String contactEmail = model.getContactEmail();
			
			ApplicantLocalServiceUtil.updateApplicant(0l, userId, companyId, applicantName, applicantIdType, applicantIdNo,
					applicantIdDate, address, cityCode, cityName, districtCode, districtName, wardCode, wardName,
					contactName, contactTelNo, contactEmail);
		}
	
	}

	@Override
	public void onAfterUpdate(Dossier model) throws ModelListenerException {
		_log.debug("Dossiser UpDate.....");
		
/*		//case application has request to cancel Dossier
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
		
	*/
		if (ORIGINAL_TODO.contains(String.valueOf(model.getOriginality()))) {
			//long groupId = model.getGroupId();
			long userId = model.getUserId();
			long companyId = model.getCompanyId();
			String applicantIdNo = model.getApplicantIdNo();
			String applicantName = model.getApplicantName();
			String applicantIdType = model.getApplicantIdType();
			Date applicantIdDate = model.getApplicantIdDate();
			String address = model.getAddress();
			String cityCode = model.getCityCode();
			String cityName = model.getCityName();
			String districtCode = model.getDistrictCode();
			String districtName = model.getDistrictName();
			String wardCode = model.getWardCode();
			String wardName = model.getWardName();
			String contactName = model.getContactName();
			String contactTelNo = model.getContactTelNo();
			String contactEmail = model.getContactEmail();
			
			ApplicantLocalServiceUtil.updateApplicant(0l, userId, companyId, applicantName, applicantIdType, applicantIdNo,
					applicantIdDate, address, cityCode, cityName, districtCode, districtName, wardCode, wardName,
					contactName, contactTelNo, contactEmail);
		}
		
		if (OpenCPSConfigUtil.isDossierLogEnable()) {
			_log.debug("UPDATE DOSSIER LOG.....");
	
			ServiceContext serviceContext = new ServiceContext();
	
			EmployeeActions employeeActions = new EmployeeActions();
	
			JobposActions jobposActions = new JobposActions();
	
			try {
	
				long userId = model.getUserId();
	
				Employee employee = employeeActions.getEmployeeByMappingUserId(model.getGroupId(), userId);
	
				long mainJobposId = employee != null ? employee.getMainJobPostId() : 0;
	
				String jobPosName = StringPool.BLANK;
	
				if (mainJobposId > 0) {
	
					JobPos jobPos = jobposActions.getJobPos(mainJobposId);
	
					jobPosName = (jobPos != null && Validator.isNotNull(jobPos.getTitle())) ? jobPos.getTitle()
							: StringPool.BLANK;
				}
	
				String content = "";
	
				JSONObject payload = JSONFactoryUtil.createJSONObject();
	
				payload.put("jobPosName", jobPosName);
				payload.put("stepName", StringPool.BLANK);
				payload.put("stepInstruction", StringPool.BLANK);
				payload.put("old", JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(modelBeforeUpdate)));
				payload.put("new", JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(model)));
				JSONObject diff = JSONFactoryUtil.createJSONObject();
				if (model.getDossierNo() != null && !model.getDossierNo().equals(modelBeforeUpdate.getDossierNo())) {
					diff.put(DossierTerm.DOSSIER_NO, model.getDossierNo());
				}
				if (model.getReceiveDate() != null && !model.getReceiveDate().equals(modelBeforeUpdate.getReceiveDate())) {
					diff.put(DossierTerm.RECEIVE_DATE, model.getReceiveDate().getTime());
				}
				if (model.getFinishDate() != null && !model.getFinishDate().equals(modelBeforeUpdate.getFinishDate())) {
					diff.put(DossierTerm.FINISH_DATE, model.getFinishDate().getTime());
				}
				payload.put("diff", diff);
				
				serviceContext.setCompanyId(model.getCompanyId());
				serviceContext.setUserId(userId);
	
				DossierLogLocalServiceUtil.addDossierLog(model.getGroupId(), model.getDossierId(),
						model.getApplicantName(), content, "DOSSIER_CHANGE", payload.toString(), serviceContext);
	
			} catch (SystemException | PortalException e) {
				_log.debug(e);
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

	@Override
	public void onAfterRemove(Dossier model) throws ModelListenerException {
		_log.debug("Dossiser Remove.....");
		DossierMgtUtils.processSyncDeleteDossier(model, model.getOriginality());
	}

	public Dossier modelBeforeUpdate;

	private Log _log = LogFactoryUtil.getLog(DossierListenner.class.getName());
}
