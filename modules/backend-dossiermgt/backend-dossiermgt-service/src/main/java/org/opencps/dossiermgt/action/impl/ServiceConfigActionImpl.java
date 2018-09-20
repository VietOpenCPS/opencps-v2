package org.opencps.dossiermgt.action.impl;

import java.util.LinkedHashMap;

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.dossiermgt.action.ServiceConfigActions;
import org.opencps.dossiermgt.exception.NoSuchServiceConfigException;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

public class ServiceConfigActionImpl implements ServiceConfigActions {

	@Override
	public JSONObject getServiceConfigs(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		Hits hits = null;

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = ServiceConfigLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = ServiceConfigLocalServiceUtil.countLucene(params, searchContext);

			result.put("total", total);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public ServiceConfig updateServiceConfig(long serviceConfigId, long userId, long groupId, long serviceInfoId,
			String govAgencyCode, String serviceInstruction, int serviceLevel, String serviceUrl, boolean forCitizen,
			boolean forBusiness, boolean postalService, boolean registration, ServiceContext context)
			throws PortalException {
		return ServiceConfigLocalServiceUtil.updateServiceConfig(serviceConfigId, userId, groupId, serviceInfoId,
				govAgencyCode, serviceInstruction, serviceLevel, serviceUrl, forCitizen, forBusiness, postalService,
				registration, context);
	}

	@Override
	public ServiceConfig getServiceConfigDetail(long serviceConfigId) throws PortalException {

		try {
			return ServiceConfigLocalServiceUtil.getServiceConfig(serviceConfigId);
		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			throw new NotFoundException();
		}
	}

	@Override
	public ServiceConfig removeServiceConfig(long serviceConfigId) throws PortalException {
		return ServiceConfigLocalServiceUtil.removeServiceConfigById(serviceConfigId);
	}

	@Override
	public JSONObject getProcessOptions(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = ProcessOptionLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = ProcessOptionLocalServiceUtil.countLucene(params, searchContext);

			result.put("total", total);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;

	}

	@Override
	public ProcessOption updateOption(long groupId, String optionName, long processOptionId, long serviceConfigId, int seqOrder,
			String autoSelect, String instructionNote, String submissionNote, long dossierTemplateId,
			long serviceProcessId, ServiceContext context) throws PortalException {
		return ProcessOptionLocalServiceUtil.updateProcessOption(groupId, optionName, processOptionId, serviceConfigId, seqOrder,
				autoSelect, instructionNote, submissionNote, dossierTemplateId, serviceProcessId, context);
	}

	@Override
	public ProcessOption removeProcessOption(long processOptionId) throws PortalException {
		return ProcessOptionLocalServiceUtil.removeProcessOption(processOptionId);
	}

	Log _log = LogFactoryUtil.getLog(ServiceConfig.class);

	@Override
	public long updateServiceConfigDB(long userId, long groupId, long serviceInfoId, String govAgencyCode,
			String govAgencyName, String serviceInstruction, Integer serviceLevel, String serviceUrl,
			boolean forCitizen, boolean forBusiness, boolean postalService, boolean registration,
			ServiceContext context) throws NoSuchServiceConfigException {
		// TODO Auto-generated method stub
		ServiceConfig config = ServiceConfigLocalServiceUtil.updateServiceConfigDB(userId, groupId, serviceInfoId, govAgencyCode,
				govAgencyName, serviceInstruction, serviceLevel, serviceUrl, forCitizen, forBusiness, postalService,
				registration, context);
		if (config != null) {
			return config.getServiceConfigId();
		}
		return 0;
	}

	@Override
	public void updateOptionDB(long userId, long groupId, String optionCode, String optionName, long serviceConfigId,
			Integer seqOrder, String autoSelect, String instructionNote, String submissionNote, String templateNo,
			String templateName, String processNo, String processName, String registerBookCode, Integer sampleCount,
			ServiceContext context) {

		ProcessOptionLocalServiceUtil.updateOptionDB(userId, groupId, optionCode, optionName, serviceConfigId, seqOrder,
				autoSelect, instructionNote, submissionNote, templateNo, templateName, processNo, processName,
				registerBookCode, sampleCount, context);
	}

}
