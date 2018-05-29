package org.opencps.dossiermgt.action;

import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.model.DossierStatistic;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

public interface DossierStatisticAction {
	public org.opencps.dossiermgt.model.DossierStatistic insertDossierStatistic(
			org.opencps.dossiermgt.model.DossierStatistic dossierStatistic);

	public org.opencps.dossiermgt.model.DossierStatistic updateDossierStatistic(
			org.opencps.dossiermgt.model.DossierStatistic dossierStatistic);

	public org.opencps.dossiermgt.model.DossierStatistic deleteDossierStatistic(long dossierStatisticId)
			throws PortalException;

	public JSONObject getDossierStatistic(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);

	public DossierStatistic insertDossierStatistic(long groupId, int month, int year, int remainingCount, int receivedCount,
			int onlineCount, int undueCount, int overdueCount, int ontimeCount, int overtimeCount, String govAgencyCode,
			String govAgencyName, String domainCode, String domainName, int administrationLevel, boolean reporting,
			ServiceContext serviceContext) throws PortalException;

	public List<DossierStatistic> getDossierStatisticbyYear(long userId, long groupId, int year);
}
