package org.opencps.statistic.rest.service;

import java.util.List;

import org.opencps.statistic.model.OpencpsDossier;
import org.opencps.statistic.rest.converter.DossierStatisticConverter;
import org.opencps.statistic.rest.dto.DossierRequest;
import org.opencps.statistic.rest.dto.DossierResponse;
import org.opencps.statistic.service.OpencpsDossierLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;

public class DossierFinderServiceImpl implements DossierFinderService {

	@Override
	public DossierResponse searchDossierService(DossierRequest payload) throws PortalException {

		List<OpencpsDossier> dossiers = OpencpsDossierLocalServiceUtil.searchDossier(payload.getGroupId(),
				payload.getKeyword(), payload.getRegisterBookCode(), payload.getProcessNo(), payload.getServiceCode(),
				payload.getGovAgencyCode(), payload.getApplicantIdType(), payload.getApplicantIdNo(),
				payload.getCityCode(), payload.getDistrictCode(), payload.getWardCode(), payload.getContactTelNo(),
				payload.getContactEmail(), payload.getDelegateIdNo(), payload.getDelegateTelNo(),
				payload.getDossierStatus(), payload.getDossierSubStatus(), payload.getDossierActionId(),
				payload.getViaPostal(), payload.isOnline(), payload.getOriginality(), payload.getServerNo(),
				payload.getOriginDossierId(), payload.isOrder(), payload.getSort(), payload.getStart(),
				payload.getEnd());
		

		return DossierStatisticConverter.getDossierResponse().convert(dossiers);
	}

}
