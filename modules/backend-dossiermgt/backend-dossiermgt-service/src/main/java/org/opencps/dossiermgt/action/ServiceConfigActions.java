package org.opencps.dossiermgt.action;

import java.util.LinkedHashMap;

import org.opencps.dossiermgt.exception.NoSuchServiceConfigException;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ServiceConfig;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

public interface ServiceConfigActions {
	
	public JSONObject getServiceConfigs(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);

	public ServiceConfig updateServiceConfig(long serviceConfigId, long userId, long groupId, long serviceInfoId,
			String govAgencyCode, String serviceInstruction, int serviceLevel, String serviceUrl, boolean forCitizen,
			boolean forBusiness, boolean postalService, boolean registration, ServiceContext context)
			throws PortalException;

	public ServiceConfig getServiceConfigDetail(long serviceConfigId) throws PortalException;

	public ServiceConfig removeServiceConfig(long serviceConfigId) throws PortalException;

	public JSONObject getProcessOptions(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);

	public ProcessOption updateOption(long groupId, String optionName, long processOptionId, long serviceConfigId, int seqOrder,
			String autoSelect, String instructionNote, String submissionNote, long dossierTemplateId,
			long serviceProcessId, ServiceContext context) throws PortalException;
	
	public ProcessOption removeProcessOption(long processOptionId) throws PortalException;

	public long updateServiceConfigDB(long userId, long groupId, long serviceInfoId, String govAgencyCode, String govAgencyName,
			String serviceInstruction, Integer serviceLevel, String serviceUrl, boolean forCitizen, boolean forBusiness,
			boolean postalService, boolean registration, ServiceContext context) throws NoSuchServiceConfigException;

	public void updateOptionDB(long userId, long groupId, String optionCode, String optionName, long serviceConfigId,
			Integer seqOrder, String autoSelect, String instructionNote, String submissionNote, String templateNo,
			String templateName, String processNo, String processName, String registerBookCode, Integer sampleCount,
			ServiceContext context);
}
