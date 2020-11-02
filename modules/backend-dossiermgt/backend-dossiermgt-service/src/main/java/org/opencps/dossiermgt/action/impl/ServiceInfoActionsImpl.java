package org.opencps.dossiermgt.action.impl;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.petra.string.StringPool;
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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.datamgt.constants.DataMGTConstants;
import org.opencps.datamgt.constants.DictItemTerm;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.action.FileUploadUtils;
import org.opencps.dossiermgt.action.ServiceInfoActions;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.SpecialCharacterUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.ServiceConfigTerm;
import org.opencps.dossiermgt.constants.ServiceInfoTerm;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceFileTemplate;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceFileTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

import javax.ws.rs.core.Response;

public class ServiceInfoActionsImpl implements ServiceInfoActions {

	@Override
	public JSONObject getServiceInfos(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		Hits hits = null;

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {
			params.put(ServiceInfoTerm.PUBLIC_, Boolean.toString(true));
			hits = ServiceInfoLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);

			result.put(ConstantUtils.DATA, hits.toList());

			long total = ServiceInfoLocalServiceUtil.countLucene(params, searchContext);

			result.put(ConstantUtils.TOTAL, total);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public ServiceInfo updateServiceInfo(long userId, long groupId, long serviceInfoId, String serviceCode,
			String serviceName, String processText, String methodText, String dossierText, String conditionText,
			String durationText, String applicantText, String resultText, String regularText, String feeText,
			String administrationCode, String domainCode, int maxLevel, boolean public_, String govAgencyText,
			ServiceContext serviceContext) throws PortalException {

		ServiceInfo serviceInfo = null;

		if (serviceInfoId == 0) {
			serviceInfo = ServiceInfoLocalServiceUtil.addServiceInfo(userId, groupId, serviceCode, serviceName,
					processText, methodText, dossierText, conditionText, durationText, applicantText, resultText,
					regularText, feeText, administrationCode, domainCode, maxLevel, public_, govAgencyText,
					serviceContext);
		} else {
			serviceInfo = ServiceInfoLocalServiceUtil.updateServiceInfo(groupId, serviceInfoId, serviceCode,
					serviceName, processText, methodText, dossierText, conditionText, durationText, applicantText,
					resultText, regularText, feeText, administrationCode, domainCode, maxLevel, public_, govAgencyText,
					serviceContext);
		}

		return serviceInfo;
	}

	@Override
	public ServiceInfo getByCode(String serviceCode, long groupId) {
		try {
			return ServiceInfoLocalServiceUtil.getByCode(groupId, serviceCode);
		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			return null;
		}
	}

	@Override
	public ServiceInfo getById(long serviceInfoId) {
		try {
			return ServiceInfoLocalServiceUtil.fetchServiceInfo(serviceInfoId);
		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
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

		result.put(ConstantUtils.DATA, data);
		result.put(ConstantUtils.TOTAL, total);

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
			_log.debug(e);
			//_log.error(e);
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
//			len = is.read(buf, 0, size);
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
	public JSONObject getStatisticByAdministration(long groupId, Sort[] sorts, ServiceContext context)
			throws ParseException, SearchException {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray data = JSONFactoryUtil.createJSONArray();

		long count = 0;

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(context.getCompanyId());

		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

		LinkedHashMap<String, Object> paramsData = new LinkedHashMap<String, Object>();

		params.put(Field.GROUP_ID, String.valueOf(groupId));

		paramsData.put(Field.GROUP_ID, String.valueOf(groupId));
		paramsData.put(DictItemTerm.DICT_COLLECTION_CODE, DataMGTConstants.ADMINTRATION_CODE);
		

		Hits hits = DictItemLocalServiceUtil.luceneSearchEngine(paramsData, sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				searchContext);

		List<Document> documents = hits.toList();

		for (Document doc : documents) {
			long admCount = 0;

			//params.put(ServiceInfoTerm.ADMINISTRATION_CODE, doc.get(DictItemTerm.ITEM_CODE));
			//Administration Code
			String administrationCode = doc.get(DictItemTerm.ITEM_CODE);
			String administrationCodeSearch = StringPool.BLANK;
			if (Validator.isNotNull(administrationCode)) {
				administrationCodeSearch = SpecialCharacterUtils.splitSpecial(administrationCode);
			}
			params.put(ServiceInfoTerm.ADMINISTRATION_CODE_SEARCH, administrationCodeSearch);
			params.put(ServiceInfoTerm.PUBLIC_, Boolean.toString(true));

			admCount = ServiceInfoLocalServiceUtil.countLucene(params, searchContext);

			if (admCount != 0) {
				count = admCount + count;

				JSONObject elm = JSONFactoryUtil.createJSONObject();

				elm.put(ServiceInfoTerm.ADMINISTRATION_CODE, doc.get(DictItemTerm.ITEM_CODE));
				elm.put(ServiceInfoTerm.ADMINISTRATION_NAME, doc.get(DictItemTerm.ITEM_NAME));
				elm.put(ConstantUtils.VALUE_COUNT, admCount);

				data.put(elm);
			}

			result.put(ConstantUtils.TOTAL, count);
			result.put(ConstantUtils.DATA, data);

		}

		return result;
	}

	@Override
	public JSONObject getStatisticByDomain(long groupId, Sort[] sorts, ServiceContext context, JSONObject jsonObject,LinkedHashMap<String, Object> paramSearch)
			throws ParseException, SearchException {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray data = JSONFactoryUtil.createJSONArray();

		long count = 0;

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(context.getCompanyId());

		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

		LinkedHashMap<String, Object> paramsData = new LinkedHashMap<String, Object>();
		String serviceLevel = (String) paramSearch.get(ServiceConfigTerm.SERVICE_LEVEL);

		params.put(Field.GROUP_ID, String.valueOf(groupId));

		paramsData.put(Field.GROUP_ID, String.valueOf(groupId));
		paramsData.put(DictItemTerm.DICT_COLLECTION_CODE, DataMGTConstants.SERVICE_DOMAIN);
		paramsData.put(ServiceConfigTerm.SERVICE_LEVEL, serviceLevel);

		Hits hits = DictItemLocalServiceUtil.luceneSearchEngine(paramsData, sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				searchContext);

		List<Document> documents = hits.toList();
		String domainCodes = "";
		try {
			//Lấy lĩnh vực theo thủ tục hành chính
			if(Validator.isNotNull(jsonObject)){
				Iterator<String> keys = jsonObject.keys();
				while (keys.hasNext()) {
					String key = keys.next();
					String value = jsonObject.getString(key);
					if(key.equals(DossierTerm.DATA)){
						JSONArray dataArray = JSONFactoryUtil.createJSONArray(value);
						JSONObject currentObject = null;
						for (int i = 0; i < dataArray.length(); i++) {
							currentObject = dataArray.getJSONObject(i);
							if(Validator.isNotNull(currentObject.getString(ServiceInfoTerm.DOMAIN_CODE))) {
								domainCodes += currentObject.getString(ServiceInfoTerm.DOMAIN_CODE) + ",";
							}
						}
					}
				}
			}
		}catch (Exception e){
			_log.info(e);
		}
		if(jsonObject.length() > 0) {
			for (Document doc : documents) {
				String itemCode = "";
				if (Validator.isNotNull(doc.get(DictItemTerm.ITEM_CODE))) {
					itemCode = doc.get(DictItemTerm.ITEM_CODE);
				}
				long admCount = 0;

				//params.put(ServiceInfoTerm.DOMAIN_CODE, doc.get(DictItemTerm.ITEM_CODE));
				//Administration Code
				String domainCode = doc.get(DictItemTerm.ITEM_CODE);
				String domainCodeSearch = StringPool.BLANK;
				if (Validator.isNotNull(domainCode)) {
					domainCodeSearch = SpecialCharacterUtils.splitSpecial(domainCode);
				}
				params.put(ServiceInfoTerm.DOMAIN_CODE_SEARCH, domainCodeSearch);
				params.put(ServiceInfoTerm.PUBLIC_, Boolean.toString(true));

//				admCount = ServiceInfoLocalServiceUtil.countLucene(params, searchContext);
//				if (admCount != 0) {
					JSONObject elm = JSONFactoryUtil.createJSONObject();
					if (Validator.isNotNull(domainCodes)) {
						String domainCodeArr[] = domainCodes.split(StringPool.COMMA);
						for (String code : domainCodeArr) {
							if (code.equals(itemCode)) {
								count = admCount + count;
								elm.put(ServiceInfoTerm.DOMAIN_CODE, doc.get(DictItemTerm.ITEM_CODE));
								elm.put(ServiceInfoTerm.DOMAIN_NAME, doc.get(DictItemTerm.ITEM_NAME));
								elm.put(ConstantUtils.VALUE_COUNT, admCount);
								data.put(elm);
								break;
							}
						}
					}
//				}

//				result.put(ConstantUtils.TOTAL, count);
				result.put(ConstantUtils.DATA, data);
			}
		}else {
			for (Document doc : documents) {
				long admCount = 0;

				String domainCode = doc.get(DictItemTerm.ITEM_CODE);
				String domainCodeSearch = StringPool.BLANK;
				if (Validator.isNotNull(domainCode)) {
					domainCodeSearch = SpecialCharacterUtils.splitSpecial(domainCode);
				}
				params.put(ServiceInfoTerm.DOMAIN_CODE_SEARCH, domainCodeSearch);
				params.put(ServiceInfoTerm.PUBLIC_, Boolean.toString(true));

				admCount = ServiceInfoLocalServiceUtil.countLucene(params, searchContext);
				if (admCount != 0) {
					count = admCount + count;
					JSONObject elm = JSONFactoryUtil.createJSONObject();
					elm.put(ServiceInfoTerm.DOMAIN_CODE, doc.get(DictItemTerm.ITEM_CODE));
					elm.put(ServiceInfoTerm.DOMAIN_NAME, doc.get(DictItemTerm.ITEM_NAME));
					elm.put(ConstantUtils.VALUE_COUNT, admCount);
					data.put(elm);
				}
			}
			result.put(ConstantUtils.TOTAL, count);
			result.put(ConstantUtils.DATA, data);
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

		for (int i = 1; i <= 4; i++) {

			long levelCount = 0;

			params.put(ServiceInfoTerm.MAX_LEVEL, i);
			params.put(ServiceInfoTerm.PUBLIC_, Boolean.toString(true));

			levelCount = ServiceInfoLocalServiceUtil.countLucene(params, searchContext);

			if (levelCount != 0) {
				count = levelCount + count;

				JSONObject elm = JSONFactoryUtil.createJSONObject();

				elm.put(ServiceInfoTerm.LEVEL, i);
				elm.put(ServiceInfoTerm.LEVEL_NAME, i);
				elm.put(ConstantUtils.VALUE_COUNT, levelCount);

				data.put(elm);
			}

			result.put(ConstantUtils.TOTAL, count);
			result.put(ConstantUtils.DATA, data);
		}

		return result;
	}

	@Override
	public long updateServiceInfoDB(long userId, long groupId, String serviceCode, String serviceName, String processText,
			String methodText, String dossierText, String conditionText, String durationText, String applicantText,
			String resultText, String regularText, String feeText, String administrationCode, String administrationName,
			String domainCode, String domainName, Integer maxLevel, boolean public_) throws PortalException {

		ServiceInfo serInfo =  ServiceInfoLocalServiceUtil.updateServiceInfoDB(userId, groupId, serviceCode, serviceName, processText,
				methodText, dossierText, conditionText, durationText, applicantText, resultText, regularText, feeText,
				administrationCode, administrationName, domainCode, domainName, maxLevel, public_);
		if (serInfo != null) {
			return serInfo.getServiceInfoId();
		}
		return 0;
	}

	@Override
	public void updateServiceFileTemplateDB(long serviceInfoId, String fileTemplateNo, String fileTemplateName,
			String fileName, long fileEntryId, boolean eForm, long formScriptFileId, long formReportFileId, String eFormNoPattern,
			String eFormNamePattern) {

		ServiceFileTemplateLocalServiceUtil.updateServiceFileTemplateDB(serviceInfoId, fileTemplateNo, fileTemplateName,
				fileName, fileEntryId, eForm, formScriptFileId, formReportFileId, eFormNoPattern, eFormNamePattern);
	}

	@Override
	public boolean deleteAllFileTemplate(long userId, long groupId, long serviceInfoId, ServiceContext serviceContext) {
		boolean flag = false;
		List<ServiceFileTemplate> fileTemplateList = ServiceFileTemplateLocalServiceUtil.getByServiceInfoId(serviceInfoId);
		if (fileTemplateList != null && fileTemplateList.size() > 0) {
			long fileEntryId = 0;
			for (ServiceFileTemplate serviceFileTemplate : fileTemplateList) {
				fileEntryId = serviceFileTemplate.getFileEntryId();
				if (fileEntryId > 0) {
					try {
						DLAppLocalServiceUtil.deleteFileEntry(fileEntryId);
					} catch (PortalException e) {
						_log.debug(e);
						//_log.error(e);
						return false;
					}
				}
				ServiceFileTemplateLocalServiceUtil.deleteServiceFileTemplate(serviceFileTemplate);
				flag = true;
			}
		} else {
			flag = true;
		}

		return flag;
	}

	@Override
	public boolean deleteAllServiceConfig(long userId, long groupId, long serviceInfoId,
			ServiceContext serviceContext) {
		boolean flag = false;
		try {
			_log.info("serviceInfoId : " + serviceInfoId);
			List<ServiceConfig> configList = ServiceConfigLocalServiceUtil.getByServiceInfo(groupId, serviceInfoId);
			_log.info("LogInfo : " + configList.size());
			if (configList != null && configList.size() > 0) {

				long serviceConfigId = 0;
				for (ServiceConfig config : configList) {
					serviceConfigId = config.getServiceConfigId();
					if (serviceConfigId > 0) {
						List<ProcessOption> optionList = ProcessOptionLocalServiceUtil
								.getByServiceProcessId(serviceConfigId);
						if (optionList != null && optionList.size() > 0) {
							for (ProcessOption processOption : optionList) {
								ProcessOptionLocalServiceUtil.deleteProcessOption(processOption);
								flag = true;
							}
						} else {
							flag = true;
						}
					}
					_log.info("Delete serviceConfig : " + serviceConfigId + " flag : " + flag);
					if (flag) {
						ServiceConfigLocalServiceUtil.deleteServiceConfig(config);
					}
				}
			} else {
				flag = true;
			}
		}catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			return false;
		}

		return flag;
	}

	@Override
	public JSONObject getStatisticByDomainFilterAdministration(long groupId, Sort[] sorts, ServiceContext context,
			String administration) throws ParseException, SearchException {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray data = JSONFactoryUtil.createJSONArray();

		long count = 0;

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(context.getCompanyId());

		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

		LinkedHashMap<String, Object> paramsData = new LinkedHashMap<String, Object>();
		params.put(Field.GROUP_ID, String.valueOf(groupId));

		paramsData.put(Field.GROUP_ID, String.valueOf(groupId));
		paramsData.put(DictItemTerm.DICT_COLLECTION_CODE, DataMGTConstants.SERVICE_DOMAIN);

		Hits hits = DictItemLocalServiceUtil.luceneSearchEngine(paramsData, sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				searchContext);

		List<Document> documents = hits.toList();

		for (Document doc : documents) {

			long admCount = 0;

			//params.put(ServiceInfoTerm.DOMAIN_CODE, doc.get(DictItemTerm.ITEM_CODE));
			//Administration Code
			String domainCode = doc.get(DictItemTerm.ITEM_CODE);
			String domainCodeSearch = StringPool.BLANK;
			if (Validator.isNotNull(domainCode)) {
				domainCodeSearch = SpecialCharacterUtils.splitSpecial(domainCode);
			}
			params.put(ServiceInfoTerm.DOMAIN_CODE_SEARCH, domainCodeSearch);
			//params.put(ServiceInfoTerm.ADMINISTRATION_CODE, administration);
			//Administration Code
			String administrationCodeSearch = StringPool.BLANK;
			if (Validator.isNotNull(administration)) {
				administrationCodeSearch = SpecialCharacterUtils.splitSpecial(administration);
			}
			params.put(ServiceInfoTerm.ADMINISTRATION_CODE_SEARCH, administrationCodeSearch);
			params.put(ServiceInfoTerm.PUBLIC_, Boolean.toString(true));

			admCount = ServiceInfoLocalServiceUtil.countLucene(params, searchContext);

			if (admCount != 0) {
				count = admCount + count;
				JSONObject elm = JSONFactoryUtil.createJSONObject();
				elm.put(ServiceInfoTerm.DOMAIN_CODE, doc.get(DictItemTerm.ITEM_CODE));
				elm.put(ServiceInfoTerm.DOMAIN_NAME, doc.get(DictItemTerm.ITEM_NAME));
				elm.put(ConstantUtils.VALUE_COUNT, admCount);
				data.put(elm);
			}

			result.put(ConstantUtils.TOTAL, count);
			result.put(ConstantUtils.DATA, data);

		}

		return result;
	}

	@Override
	public JSONObject getServiceFileTemplate(long groupId, String id, boolean eForm, int start, int end) {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			long serviceInfoId = GetterUtil.getLong(id);
			if (serviceInfoId > 0) {
				int total = ServiceFileTemplateLocalServiceUtil.countByService_EForm(serviceInfoId, eForm);
				List<ServiceFileTemplate> data = ServiceFileTemplateLocalServiceUtil.getByService_EForm(serviceInfoId,
						eForm, start, end);

				result.put(ConstantUtils.DATA, data);
				result.put(ConstantUtils.TOTAL, total);
			} else {
				ServiceInfo service = ServiceInfoLocalServiceUtil.getByCode(groupId, id);
				if (service != null) {
					int total = ServiceFileTemplateLocalServiceUtil.countByService_EForm(service.getServiceInfoId(),
							eForm);
					List<ServiceFileTemplate> data = ServiceFileTemplateLocalServiceUtil
							.getByService_EForm(service.getServiceInfoId(), eForm, start, end);

					result.put(ConstantUtils.DATA, data);
					result.put(ConstantUtils.TOTAL, total);
				}
			}
		} catch (Exception e) {
			_log.error(e);
		}
		
		return result;
	}

	@Override
	public JSONObject getServiceFileTemplate(long groupId, String id, int start, int end) {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {
			long serviceInfoId = GetterUtil.getLong(id);
			if (serviceInfoId > 0) {
				int total = ServiceFileTemplateLocalServiceUtil.countByServiceInfoId(serviceInfoId);
				List<ServiceFileTemplate> data = ServiceFileTemplateLocalServiceUtil.getByService(serviceInfoId, start,
						end);

				result.put(ConstantUtils.DATA, data);
				result.put(ConstantUtils.TOTAL, total);
			} else {
				ServiceInfo service = ServiceInfoLocalServiceUtil.getByCode(groupId, id);
				if (service != null) {
					int total = ServiceFileTemplateLocalServiceUtil.countByServiceInfoId(service.getServiceInfoId());
					List<ServiceFileTemplate> data = ServiceFileTemplateLocalServiceUtil
							.getByService(service.getServiceInfoId(), start, end);

					result.put(ConstantUtils.DATA, data);
					result.put(ConstantUtils.TOTAL, total);
				}
			}
		} catch (Exception e) {
			_log.error(e);
		}
		
		return result;
	}

	@Override
	public JSONObject doSynServiceInfoFromDVC(long userId, long groupId, ServiceContext serviceContext, String method, String url, String data) {
		return null;
	}

}
