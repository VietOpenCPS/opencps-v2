package org.opencps.dossiermgt.action.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.action.ServiceInfoActions;
import org.opencps.dossiermgt.model.ServiceFileTemplate;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.service.ServiceFileTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

public class ServiceInfoActionsImpl implements ServiceInfoActions {

	@Override
	public JSONObject getServiceInfos(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		Hits hits = null;

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = ServiceInfoLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = ServiceInfoLocalServiceUtil.countLucene(params, searchContext);

			result.put("total", total);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public ServiceInfo updateServiceInfo(long userId, long groupId, long serviceInfoId, String serviceCode,
			String serviceName, String processText, String methodText, String dossierText, String conditionText,
			String durationText, String applicantText, String resultText, String regularText, String feeText,
			String administrationCode, String domainCode, int maxLevel, boolean public_, ServiceContext serviceContext)
			throws PortalException {

		ServiceInfo serviceInfo = null;

		if (serviceInfoId != 0) {
			serviceInfo = ServiceInfoLocalServiceUtil.addServiceInfo(userId, groupId, serviceCode, serviceName,
					processText, methodText, dossierText, conditionText, durationText, applicantText, resultText,
					regularText, feeText, administrationCode, domainCode, maxLevel, GetterUtil.getInteger(public_),
					serviceContext);
		} else {
			serviceInfo = ServiceInfoLocalServiceUtil.updateServiceInfo(groupId, serviceInfoId, serviceCode,
					serviceName, processText, methodText, dossierText, conditionText, durationText, applicantText,
					resultText, regularText, feeText, administrationCode, domainCode, maxLevel,
					GetterUtil.getInteger(public_), serviceContext);
		}

		return serviceInfo;
	}

	@Override
	public ServiceInfo getByCode(String serviceCode, long groupId) {
		try {
			return ServiceInfoLocalServiceUtil.getByCode(groupId, serviceCode);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ServiceInfo getById(long serviceInfoId) {
		try {
			return ServiceInfoLocalServiceUtil.fetchServiceInfo(serviceInfoId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ServiceInfo removeServiceInfo(long serviceInfoId) throws PortalException {
		return ServiceInfoLocalServiceUtil.removeServiceInfo(serviceInfoId);
	}

	@Override
	public JSONObject getServiceFileTemplate(long serviceInfoId) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		int total = ServiceFileTemplateLocalServiceUtil.countByServiceInfoId(serviceInfoId);

		List<ServiceFileTemplate> data = ServiceFileTemplateLocalServiceUtil.getByServiceInfoId(serviceInfoId);

		result.put("data", data);
		result.put("total", total);

		return result;
	}

	@Override
	public ServiceFileTemplate updateServiceFileTemplate(long userId, long groupId, long folderId, long serviceInfoId,
			String fileTemplateNo, String templateName, String sourceFileName, InputStream inputStream,
			ServiceContext serviceContext) throws PortalException, IOException {
		
		return ServiceFileTemplateLocalServiceUtil.updateServiceFileTemplate(userId, groupId, folderId, serviceInfoId,
				fileTemplateNo, templateName, sourceFileName, inputStream, serviceContext);
	}

	@Override
	public ServiceFileTemplate removeServiceFileTemplate(long serviceInfoId, String fileTemplateNo)
			throws PortalException {

		return ServiceFileTemplateLocalServiceUtil.removeServiceFileTemplate(serviceInfoId, fileTemplateNo);
	}

	@Override
	public ServiceFileTemplate addServiceFileTemplate(long userId, long groupId, long folderId, long serviceInfoId,
			String fileTemplateNo, String templateName, String sourceFileName, InputStream inputStream,
			ServiceContext serviceContext) throws PortalException, IOException {
		return ServiceFileTemplateLocalServiceUtil.addServiceFileTemplate(userId, groupId, folderId, serviceInfoId,
				fileTemplateNo, templateName, sourceFileName, inputStream, serviceContext);
	}

	Log _log = LogFactoryUtil.getLog(ServiceInfoActionsImpl.class);
}
