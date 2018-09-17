package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.dossierstatistic.model.DossierStatisticDetailModel;
import org.opencps.api.dossierstatistic.model.DossierStatisticModel;
import org.opencps.api.dossierstatistic.model.DossierStatisticYearModel;
import org.opencps.dossiermgt.constants.DossierStatisticTerm;
import org.opencps.dossiermgt.model.DossierStatistic;
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

	public static List<DossierStatisticDetailModel> mappingToDossierStatisticData(
			List<DossierStatistic> lsDossierStatistic) {

		List<DossierStatisticDetailModel> outputs = new ArrayList<DossierStatisticDetailModel>();

		for (DossierStatistic dossierStatistic : lsDossierStatistic) {

			DossierStatisticDetailModel model = mappingToDossierStatisticModel(dossierStatistic);

			outputs.add(model);
		}

		return outputs;
	}

	public static DossierStatisticDetailModel mappingToDossierStatisticModel(DossierStatistic dossierStatistic) {

		DossierStatisticDetailModel model = new DossierStatisticDetailModel();

		model.setMonth(Integer.valueOf(dossierStatistic.getMonth()));
		model.setYear(Integer.valueOf(dossierStatistic.getYear()));
		model.setReceivedCount(Integer.valueOf(dossierStatistic.getRemainingCount()));
		model.setReceivedCount(Integer.valueOf(dossierStatistic.getReceivedCount()));
		model.setOnlineCount(Integer.valueOf(dossierStatistic.getOnlineCount()));
		model.setUndueCount(Integer.valueOf(dossierStatistic.getUndueCount()));
		model.setOverdueCount(Integer.valueOf(dossierStatistic.getOverdueCount()));
		model.setOntimeCount(Integer.valueOf(dossierStatistic.getOntimeCount()));
		model.setOvertimeCount(Integer.valueOf(dossierStatistic.getOvertimeCount()));
		model.setDomainCode(dossierStatistic.getDomainCode());

		return model;
	}

	public static List<DossierStatisticYearModel> mappingDossierStatisticYearModel(
			List<DossierStatistic> lstDossierStatistic) {
		List<DossierStatisticYearModel> outputs = new ArrayList<DossierStatisticYearModel>();

		for (DossierStatistic dossierStatistic : lstDossierStatistic) {

			DossierStatisticYearModel model = new DossierStatisticYearModel();

			model.setMonth(dossierStatistic.getMonth());
			model.setYear(dossierStatistic.getYear());
			model.setDossierCount(dossierStatistic.getRemainingCount());
			model.setActionCount(dossierStatistic.getOnlineCount());
			model.setOverdueCount(dossierStatistic.getOverdueCount());

			outputs.add(model);
		}

		return outputs;

	}

}
