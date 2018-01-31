package com.fds.vr.business.action.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.service.DeliverableLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationLocalServiceUtil;

import com.fds.vr.business.action.VROutputDBActions;
import com.fds.vr.business.action.util.ConvertJONObjectUtils;
import com.fds.vr.business.model.VRSyncDate;
import com.fds.vr.business.model.VRVehicleTypeCertificate;
import com.fds.vr.business.service.VRSyncDateLocalServiceUtil;
import com.fds.vr.business.service.VRVehicleTypeCertificateLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class VROutputDBActionsImpl implements VROutputDBActions{

	private static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	private Log _log = LogFactoryUtil.getLog(VROutputDBActionsImpl.class);

	@Override
	public boolean processOutputDB() throws ParseException, SearchException, JSONException {

		Date syncDate = null;
		try {
			syncDate = VRSyncDateLocalServiceUtil.getSyncDate();
		} catch (Exception e) {
			_log.error(e);
		}
		
		_log.info("synsDate: "+syncDate);
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME);
		String strSyncDate = sdf.format(syncDate);

		List<Deliverable> deliverableList = DeliverableLocalServiceUtil.getDeliverableByModifiedDate(strSyncDate,
				"GCN_TDTK_XCG", 2);
		_log.info("deliverableList: "+deliverableList);

		if (deliverableList != null && deliverableList.size() > 0) {
//			List<String> formDataList = new ArrayList<String>();
//			List<Date> modifiedDateList = new ArrayList<Date>();
			String formData = StringPool.BLANK;
			Date modifiedDate = null;
			DossierFile dossierFile = null;
			Registration registration = null;
			for (Deliverable deliverable : deliverableList) {
				String applicantIdNo = deliverable.getApplicantIdNo();
				String deliverableCode = deliverable.getDeliverableCode();
				if (Validator.isNotNull(applicantIdNo)) {
					registration = RegistrationLocalServiceUtil.getByApplicantIdNo(applicantIdNo);
				}
				if (Validator.isNotNull(deliverableCode)) {
					dossierFile = DossierFileLocalServiceUtil.getByDeliverableCode(deliverableCode);
				}

				_log.info("registration: "+registration);
				_log.info("dossierFile: "+dossierFile);
				formData = deliverable.getFormData();
				modifiedDate = deliverable.getModifiedDate();
				if (Validator.isNotNull(formData)) {
					LinkedHashMap<String, String> mapValues = ConvertJONObjectUtils.getKeyValuesMap(formData);
					if (mapValues != null) {
						outputDBAction(mapValues, modifiedDate, registration, dossierFile);
					}
				}
			}
		}

		return true;
	}

	private void outputDBAction(LinkedHashMap<String, String> mapValues, Date modifiedDate,
			Registration registration, DossierFile dossierFile) {
		// update table VRVehicleTypeCertificate
		VRVehicleTypeCertificate vrVehicleTypeCertificate = VRVehicleTypeCertificateLocalServiceUtil
				.updateVehicleTypeCertificate(mapValues, modifiedDate, registration, dossierFile);

//		long vrVehicleTypeCertificateId = vrVehicleTypeCertificate.getId();

		// update table VR_VEHICLESPECIFICATION
//		VRVehicleSpecificationLocalServiceUtil.updateVehicleSpecification(mapValues, vrVehicleTypeCertificateId,
//				modifiedDate, registration, dossierFile);
		// update VR_INSPECTIONSTANDARD
//		VRInspectionStandardLocalServiceUtil.updateInspectionStandard(mapValues, vrVehicleTypeCertificateId,
//				modifiedDate, registration, dossierFile);
		// update VR_APPLICANTPROFILE
		// VRApplicantProfileLocalServiceUtil.updateApplicantProfile(mapValues,
		// modifiedDate);
		// update VR_PRODUCTIONPLANT
		// VRProductionPlantLocalServiceUtil.updateProductionPlant(mapValues,
		// modifiedDate);
		// update syncDate
		VRSyncDateLocalServiceUtil.updateSyncDate(modifiedDate);

	}

}
