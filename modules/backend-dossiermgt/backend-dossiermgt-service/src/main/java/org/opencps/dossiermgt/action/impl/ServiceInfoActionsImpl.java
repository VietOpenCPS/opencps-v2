package org.opencps.dossiermgt.action.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.datamgt.constants.DataMGTConstants;
import org.opencps.datamgt.constants.DictItemTerm;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.action.FileUploadUtils;
import org.opencps.dossiermgt.action.ServiceInfoActions;
import org.opencps.dossiermgt.constants.ServiceInfoTerm;
import org.opencps.dossiermgt.model.ServiceFileTemplate;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.service.ServiceFileTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;

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

		if (serviceInfoId == 0) {
			serviceInfo = ServiceInfoLocalServiceUtil.addServiceInfo(userId, groupId, serviceCode, serviceName,
					processText, methodText, dossierText, conditionText, durationText, applicantText, resultText,
					regularText, feeText, administrationCode, domainCode, maxLevel, public_,
					serviceContext);
		} else {
			serviceInfo = ServiceInfoLocalServiceUtil.updateServiceInfo(groupId, serviceInfoId, serviceCode,
					serviceName, processText, methodText, dossierText, conditionText, durationText, applicantText,
					resultText, regularText, feeText, administrationCode, domainCode, maxLevel,
					public_, serviceContext);
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
	public ServiceFileTemplate addServiceFileTemplate(long userId, long groupId, long serviceInfoId,
			String fileTemplateNo, String templateName, String fileName, InputStream is, String fileType, int fileSize,
			ServiceContext serviceContext) throws PortalException, IOException {

		ServiceFileTemplate serviceFileTemplate = null;

		try {
			
			
			FileEntry fileEntry = FileUploadUtils.uploadFile(userId, groupId, 0, is, fileName, fileType, fileSize, null, serviceContext);
					
			serviceFileTemplate = ServiceFileTemplateLocalServiceUtil.addServiceFileTemplate(serviceInfoId,
					fileTemplateNo, templateName, fileEntry.getFileEntryId(), serviceContext);

		} catch (Exception e) {
			_log.error(e);
		}

		return serviceFileTemplate;
	}

	public static byte[] getBytes(InputStream is) throws IOException {

		int len;
		int size = 1024;
		byte[] buf;

		if (is instanceof ByteArrayInputStream) {
			size = is.available();
			buf = new byte[size];
			len = is.read(buf, 0, size);
		} else {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			buf = new byte[size];
			while ((len = is.read(buf, 0, size)) != -1)
				bos.write(buf, 0, len);
			buf = bos.toByteArray();
		}
		return buf;
	}

	Log _log = LogFactoryUtil.getLog(ServiceInfoActionsImpl.class);

	@Override
	public JSONObject getStatisticByAdministration(ServiceContext context, long groupId)
			throws ParseException, SearchException {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray data = JSONFactoryUtil.createJSONArray();

		long count = 0;

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(context.getCompanyId());

		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

		LinkedHashMap<String, Object> paramsData = new LinkedHashMap<String, Object>();

		params.put(Field.GROUP_ID, String.valueOf(groupId));
		params.put(ServiceInfoTerm.PUBLIC_, "true");

		paramsData.put(Field.GROUP_ID, String.valueOf(groupId));
		paramsData.put(DictItemTerm.DICT_COLLECTION_CODE, DataMGTConstants.GOVERNMENT_AGENCY);

		Sort[] sorts = new Sort[] { SortFactoryUtil.create(StringPool.BLANK + "_sortable", Sort.STRING_TYPE, true) };

		Hits hits = DictItemLocalServiceUtil.luceneSearchEngine(paramsData, sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				searchContext);

		List<Document> documents = hits.toList();

		for (Document doc : documents) {

			long admCount = 0;

			params.put(ServiceInfoTerm.ADMINISTRATION_CODE, doc.get(DictItemTerm.ITEM_CODE));

			admCount = ServiceInfoLocalServiceUtil.countLucene(params, searchContext);

			if (admCount != 0) {
				count = admCount + count;

				JSONObject elm = JSONFactoryUtil.createJSONObject();

				elm.put("administrationCode", doc.get(DictItemTerm.ITEM_CODE));
				elm.put("administrationName", doc.get(DictItemTerm.ITEM_NAME));
				elm.put("count", admCount);

				data.put(elm);
			}

			result.put("total", count);
			result.put("data", data);

		}

		return result;
	}

	@Override
	public JSONObject getStatisticByDomain(ServiceContext context, long groupId)
			throws ParseException, SearchException {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray data = JSONFactoryUtil.createJSONArray();

		long count = 0;

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(context.getCompanyId());

		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

		LinkedHashMap<String, Object> paramsData = new LinkedHashMap<String, Object>();

		params.put(Field.GROUP_ID, String.valueOf(groupId));
		params.put(ServiceInfoTerm.PUBLIC_, "true");

		paramsData.put(Field.GROUP_ID, String.valueOf(groupId));
		paramsData.put(DictItemTerm.DICT_COLLECTION_CODE, DataMGTConstants.SERVICE_DOMAIN);

		Sort[] sorts = new Sort[] { SortFactoryUtil.create(StringPool.BLANK + "_sortable", Sort.STRING_TYPE, true) };

		Hits hits = DictItemLocalServiceUtil.luceneSearchEngine(paramsData, sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				searchContext);

		List<Document> documents = hits.toList();

		for (Document doc : documents) {

			long admCount = 0;

			params.put(ServiceInfoTerm.DOMAIN_CODE, doc.get(DictItemTerm.ITEM_CODE));

			admCount = ServiceInfoLocalServiceUtil.countLucene(params, searchContext);

			if (admCount != 0) {
				count = admCount + count;

				JSONObject elm = JSONFactoryUtil.createJSONObject();

				elm.put("domainCode", doc.get(DictItemTerm.ITEM_CODE));
				elm.put("domainName", doc.get(DictItemTerm.ITEM_NAME));
				elm.put("count", admCount);

				data.put(elm);
			}

			result.put("total", count);
			result.put("data", data);

		}

		return result;
	}

	@Override
	public JSONObject getStatisticByLevel(ServiceContext context, long groupId) throws ParseException, SearchException {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray data = JSONFactoryUtil.createJSONArray();

		long count = 0;

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(context.getCompanyId());

		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

		params.put(Field.GROUP_ID, String.valueOf(groupId));
		params.put(ServiceInfoTerm.PUBLIC_, "true");

		for (int i = 1; i <= 4; i++) {

			long levelCount = 0;

			params.put(ServiceInfoTerm.MAX_LEVEL, i);

			levelCount = ServiceInfoLocalServiceUtil.countLucene(params, searchContext);

			if (levelCount != 0) {
				count = levelCount + count;

				JSONObject elm = JSONFactoryUtil.createJSONObject();

				elm.put("level", i);
				elm.put("levelName", i);
				elm.put("count", levelCount);

				data.put(elm);
			}

			result.put("total", count);
			result.put("data", data);
		}

		return result;
	}
}
