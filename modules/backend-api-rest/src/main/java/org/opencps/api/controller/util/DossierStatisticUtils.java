package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.dossierstatistic.model.DossierStatisticModel;
import org.opencps.dossiermgt.constants.DossierStatisticTerm;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;

public class DossierStatisticUtils {

	public static List<DossierStatisticModel> mappingToDossierStatistictModel(List<Document> documents) {
		List<DossierStatisticModel> data = new ArrayList<DossierStatisticModel>();

		for (Document doc : documents) {
			DossierStatisticModel model = new DossierStatisticModel();

			model.setMonth(Integer.valueOf(doc.get(DossierStatisticTerm.MONTH)));
			model.setYear(Integer.valueOf(doc.get(DossierStatisticTerm.YEAR)));
			model.setRemainingCount(Integer.valueOf(doc.get(DossierStatisticTerm.REMAINING_COUNT)));
			model.setReceivedCount(Integer.valueOf(doc.get(DossierStatisticTerm.RECEIVED_COUNT)));
			model.setOnlineCount(Integer.valueOf(doc.get(DossierStatisticTerm.ONLINE_COUNT)));
			model.setUndueCount(Integer.valueOf(doc.get(DossierStatisticTerm.UNDUE_COUNT)));
			model.setOverdueCount(Integer.valueOf(doc.get(DossierStatisticTerm.OVERDUE_COUNT)));
			model.setOntimeCount(Integer.valueOf(doc.get(DossierStatisticTerm.ONTIME_COUNT)));
			model.setOvertimeCount(Integer.valueOf(doc.get(DossierStatisticTerm.OVERTIME_COUNT)));
			model.setGovAgencyCode(doc.get(DossierStatisticTerm.GOV_AGENCY_CODE));
			model.setGovAgencyName(doc.get(DossierStatisticTerm.GOV_AGENCY_NAME));
			model.setDomainCode(doc.get(DossierStatisticTerm.DOMAIN_CODE));
			model.setDomainName(doc.get(DossierStatisticTerm.DOMAIN_NAME));
			model.setAdministrationLevel(Integer.valueOf(doc.get(DossierStatisticTerm.ADMINISTRATION_LEVEL)));
			data.add(model);
		}

		return data;
	}

	
	private static Log _log = LogFactoryUtil.getLog(DossierStatisticUtils.class);
}
