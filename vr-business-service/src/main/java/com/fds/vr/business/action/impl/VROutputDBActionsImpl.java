package com.fds.vr.business.action.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.service.DeliverableLocalServiceUtil;

import com.fds.vr.business.action.VROutputDBActions;
import com.fds.vr.business.action.util.ConvertJONObjectUtils;
import com.fds.vr.business.model.VRVehicleTypeCertificate;
import com.fds.vr.business.service.VRApplicantProfileLocalServiceUtil;
import com.fds.vr.business.service.VRInspectionStandardLocalServiceUtil;
import com.fds.vr.business.service.VRProductionPlantLocalServiceUtil;
import com.fds.vr.business.service.VRVehicleSpecificationLocalServiceUtil;
import com.fds.vr.business.service.VRVehicleTypeCertificateLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;

public class VROutputDBActionsImpl implements VROutputDBActions{

	private static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	
	@Override
	public boolean processOutputDB() throws ParseException, SearchException, JSONException {

		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME);
		String strDate = sdf.format(now);
//		List<>
		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
		Sort[] sorts = new Sort[] {
				SortFactoryUtil.create(Field.MODIFIED_DATE + "_sortable", Sort.STRING_TYPE, true) };
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(20116);
		
		Hits hits = DeliverableLocalServiceUtil.searchLucene(params, sorts, -1, -1, searchContext);
		List<Document> docList = hits.toList();
		// Add list JSON object
		List<String> formDataList = new ArrayList<String>();
		for (Document doc : docList) {
			String formData = doc.get(DeliverableTerm.FORM_DATA);
			formDataList.add(formData);
		}
		if (formDataList.size() > 0) {
			for (String formDataDetail : formDataList) {
				LinkedHashMap<String, String> mapValues = ConvertJONObjectUtils.getKeyValuesMap(formDataDetail);

				outputDBAction(mapValues);
				
			}
		}

		return true;
	}

	private void outputDBAction(LinkedHashMap<String, String> mapValues) {
		// update table VRVehicleTypeCertificate
		VRVehicleTypeCertificate vrVehicleTypeCertificate = VRVehicleTypeCertificateLocalServiceUtil.updateVehicleTypeCertificate(mapValues);

		long vrVehicleTypeCertificateId = vrVehicleTypeCertificate.getId();

		//update table VR_VEHICLESPECIFICATION
		VRVehicleSpecificationLocalServiceUtil.updateVehicleSpecification(mapValues, vrVehicleTypeCertificateId);
		// update VR_INSPECTIONSTANDARD
		VRInspectionStandardLocalServiceUtil.updateInspectionStandard(mapValues, vrVehicleTypeCertificateId);
		// update VR_APPLICANTPROFILE
		VRApplicantProfileLocalServiceUtil.updateApplicantProfile(mapValues);
		// update VR_PRODUCTIONPLANT
		VRProductionPlantLocalServiceUtil.updateProductionPlant(mapValues);

	}

}
