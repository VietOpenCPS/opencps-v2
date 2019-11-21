package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusException;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.opencps.api.controller.ServiceConfigManagement;
import org.opencps.api.controller.util.ServiceConfigUtils;
import org.opencps.api.dictcollection.model.DictItemCompareModel;
import org.opencps.api.serviceconfig.model.ProcessOptionInputModel;
import org.opencps.api.serviceconfig.model.ProcessOptionResultsModel;
import org.opencps.api.serviceconfig.model.ProcessOptionSearchModel;
import org.opencps.api.serviceconfig.model.ServiceConfigDetailModel;
import org.opencps.api.serviceconfig.model.ServiceConfigInputModel;
import org.opencps.api.serviceconfig.model.ServiceConfigResultsModel;
import org.opencps.api.serviceconfig.model.ServiceConfigSearchModel;
import org.opencps.api.serviceinfo.model.ServiceInfoSearchModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.auth.api.keys.ActionKeys;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.action.ServiceConfigActions;
import org.opencps.dossiermgt.action.impl.ServiceConfigActionImpl;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.constants.DossierPartTerm;
import org.opencps.dossiermgt.constants.DossierTemplateTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.ProcessOptionTerm;
import org.opencps.dossiermgt.constants.ServiceConfigTerm;
import org.opencps.dossiermgt.constants.ServiceInfoTerm;
import org.opencps.dossiermgt.model.DocumentType;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.service.DocumentTypeLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;
import org.opencps.usermgt.constants.ApplicantTerm;
import org.opencps.usermgt.constants.EmployeeTerm;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class ServiceConfigManagementImpl implements ServiceConfigManagement {

	@SuppressWarnings("unchecked")
	@Override
	public Response getServiceConfigs(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, ServiceConfigSearchModel query) {

		ServiceConfigActions actions = new ServiceConfigActionImpl();

		ServiceConfigResultsModel results = new ServiceConfigResultsModel();

		try {
			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.KEYWORD_SEARCH, query.getKeyword());

			String level = query.getLevel();
			String agency = query.getAgency();
			String service = query.getService();
			String domain = query.getDomain();
			String applicant = query.getApplicant();

			params.put(ServiceConfigTerm.SERVICE_LEVEL, level);
			params.put(ServiceConfigTerm.GOVAGENCY_CODE, agency);
			params.put(ServiceConfigTerm.SERVICE_CODE, service);
			params.put(ServiceConfigTerm.DOMAIN_CODE, domain);
			params.put(ServiceConfigTerm.APPICATION_TYPE, applicant);

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + ReadFilePropertiesUtils.get(ConstantUtils.SORT_PATTERN), Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };

			JSONObject jsonData = actions.getServiceConfigs(serviceContext.getUserId(), serviceContext.getCompanyId(),
					groupId, params, sorts, query.getStart(), query.getEnd(), serviceContext);

			results.setTotal(jsonData.getInt(ConstantUtils.TOTAL));

			results.getData()
					.addAll(ServiceConfigUtils.mappingToServiceConfigResults((List<Document>) jsonData.get(ConstantUtils.DATA)));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addServiceConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, ServiceConfigInputModel input) {

		ServiceConfigActions actions = new ServiceConfigActionImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long userId = serviceContext.getUserId();

		ServiceConfigDetailModel returnModel = new ServiceConfigDetailModel();

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, ServiceConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			String govAgencyCode = HtmlUtil.escape(input.getGovAgencyCode());
			String serviceInstruction = HtmlUtil.escape(input.getServiceInstruction());
			String serviceUrl = HtmlUtil.escape(input.getServiceUrl());
			String forCitizen = HtmlUtil.escape(String.valueOf(input.getForCitizen()));
			String forBusiness = HtmlUtil.escape(String.valueOf(input.getForBusiness()));
			String postService = HtmlUtil.escape(String.valueOf(input.getPostalService()));
			String registration = HtmlUtil.escape(String.valueOf(input.getRegistration()));
			
			ServiceConfig serviceConfig = actions.updateServiceConfig(0l, userId, groupId,
					(long) input.getServiceInfoId(), govAgencyCode, serviceInstruction,
					(int) input.getServiceLevel(), serviceUrl, GetterUtil.getBoolean(forCitizen),
					GetterUtil.getBoolean(forBusiness), GetterUtil.getBoolean(postService),
					GetterUtil.getBoolean(registration), serviceContext);

			returnModel = ServiceConfigUtils.mapptingToServiceConfig(serviceConfig);

			return Response.status(200).entity(returnModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response getServiceConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {

		ServiceConfigActions actions = new ServiceConfigActionImpl();
		ServiceConfigDetailModel returnModel = new ServiceConfigDetailModel();

		try {

			ServiceConfig serviceConfig = actions.getServiceConfigDetail(id);

			returnModel = ServiceConfigUtils.mapptingToServiceConfig(serviceConfig);

			return Response.status(200).entity(returnModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response updateServiceConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, ServiceConfigInputModel input) {

		ServiceConfigActions actions = new ServiceConfigActionImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long userId = serviceContext.getUserId();

		ServiceConfigDetailModel returnModel = new ServiceConfigDetailModel();

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, ServiceConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			String govAgencyCode = HtmlUtil.escape(input.getGovAgencyCode());
			String serviceInstruction = HtmlUtil.escape(input.getServiceInstruction());
			String serviceUrl = HtmlUtil.escape(input.getServiceUrl());
			String forCitizen = HtmlUtil.escape(String.valueOf(input.getForCitizen()));
			String forBusiness = HtmlUtil.escape(String.valueOf(input.getForBusiness()));
			String postService = HtmlUtil.escape(String.valueOf(input.getPostalService()));
			String registration = HtmlUtil.escape(String.valueOf(input.getRegistration()));
			
			ServiceConfig serviceConfig = actions.updateServiceConfig(id, userId, groupId,
					(long) input.getServiceInfoId(), govAgencyCode, serviceInstruction,
					(int) input.getServiceLevel(), serviceUrl, GetterUtil.getBoolean(forCitizen),
					GetterUtil.getBoolean(forBusiness), GetterUtil.getBoolean(postService),
					GetterUtil.getBoolean(registration), serviceContext);

			returnModel = ServiceConfigUtils.mapptingToServiceConfig(serviceConfig);

			return Response.status(200).entity(returnModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response removeServiceConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {

		ServiceConfigActions actions = new ServiceConfigActionImpl();

		ServiceConfigDetailModel returnModel = new ServiceConfigDetailModel();

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, ServiceConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			ServiceConfig serviceConfig = actions.removeServiceConfig(id);

			returnModel = ServiceConfigUtils.mapptingToServiceConfig(serviceConfig);

			return Response.status(200).entity(returnModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getProcessOptions(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, ProcessOptionSearchModel query) {
		ServiceConfigActions actions = new ServiceConfigActionImpl();

		ProcessOptionResultsModel results = new ProcessOptionResultsModel();

		long userId = user.getUserId();

		try {
			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.KEYWORD_SEARCH, query.getKeyword());

			String configId = String.valueOf(id);
			String applicantType = query.getApplicant();

			params.put(ProcessOptionTerm.SERVICE_CONFIG_ID, configId);
			params.put(ProcessOptionTerm.APPLICATION_TYPE, applicantType);

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + ReadFilePropertiesUtils.get(ConstantUtils.SORT_PATTERN), Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };

			JSONObject jsonData = actions.getProcessOptions(userId, serviceContext.getCompanyId(), groupId, params,
					sorts, query.getStart(), query.getEnd(), serviceContext);

			results.setTotal(jsonData.getInt(ConstantUtils.TOTAL));

			results.getData()
					.addAll(ServiceConfigUtils.mappingToProcessOptionResults((List<Document>) jsonData.get(ConstantUtils.DATA)));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addProcessOption(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, ProcessOptionInputModel input) {

		ServiceConfigActions actions = new ServiceConfigActionImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		org.opencps.api.serviceconfig.model.ProcessOption returnModel = new org.opencps.api.serviceconfig.model.ProcessOption();

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, ServiceConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

//			model.setOptionOrder(Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getOptionOrder()))));
//			model.setAutoSelect(HtmlUtil.escape(model.getAutoSelect()));
//			model.setInstructionNote(HtmlUtil.escape(model.getInstructionNote()));
//			model.setSubmissionNote(HtmlUtil.escape(model.getSubmissionNote()));
//			model.setDossierTemplateId(
//					Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getDossierTemplateId()))));
//			model.setServiceProcessId(
//					Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getServiceProcessId()))));
//			model.setOptionName(HtmlUtil.escape(model.getOptionName()));
			String autoSelect = HtmlUtil.escape(input.getAutoSelect());
			String instructionNote = HtmlUtil.escape(input.getInstructionNote());
			String submissionNote = HtmlUtil.escape(input.getSubmissionNote());
			
			ProcessOption serviceConfig = actions.updateOption(groupId, input.getOptionName(), 0l, id,
					input.getSeqOrder(), autoSelect, instructionNote, submissionNote,
					input.getDossierTemplateId(), input.getServiceProcessId(), serviceContext);

			returnModel = ServiceConfigUtils.mappingToProcessOption(serviceConfig);

			return Response.status(200).entity(returnModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response updateProcessOption(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, long optionId, ProcessOptionInputModel input) {
		ServiceConfigActions actions = new ServiceConfigActionImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		org.opencps.api.serviceconfig.model.ProcessOption returnModel = new org.opencps.api.serviceconfig.model.ProcessOption();

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, ServiceConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			String autoSelect = HtmlUtil.escape(input.getAutoSelect());
			String instructionNote = HtmlUtil.escape(input.getInstructionNote());
			String submissionNote = HtmlUtil.escape(input.getSubmissionNote());
			
			ProcessOption processOption = actions.updateOption(groupId, input.getOptionName(), optionId, id,
					input.getSeqOrder(), autoSelect, instructionNote, submissionNote,
					input.getDossierTemplateId(), input.getServiceProcessId(), serviceContext);

			returnModel = ServiceConfigUtils.mappingToProcessOption(processOption);

			return Response.status(200).entity(returnModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response removeProcessOption(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, long optionId) {

		ServiceConfigActions actions = new ServiceConfigActionImpl();

		org.opencps.api.serviceconfig.model.ProcessOption returnModel = new org.opencps.api.serviceconfig.model.ProcessOption();

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, ServiceConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			ProcessOption processOption = actions.removeProcessOption(optionId);

			returnModel = ServiceConfigUtils.mappingToProcessOption(processOption);

			return Response.status(200).entity(returnModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getServiceConfigsByGovAgency(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, ServiceInfoSearchModel query) {
		
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		DictCollection govAgencyCollection = DictCollectionLocalServiceUtil
				.fetchByF_dictCollectionCode(ReadFilePropertiesUtils.get(ConstantUtils.GOVERNMENT_AGENCY), groupId);

		List<DictItem> govItems = DictItemLocalServiceUtil
				.findByF_dictCollectionId(govAgencyCollection.getDictCollectionId());

		List<DictItemCompareModel> itemGovList = null; 
		if (govItems != null && govItems.size() > 0) {
			itemGovList = new ArrayList<>();
			DictItemCompareModel item = null;
			for (DictItem dictItem : govItems) {
				item = new DictItemCompareModel(dictItem.getItemCode(), dictItem.getItemName(),
						Validator.isNotNull(dictItem.getSibling()) ? GetterUtil.getInteger(dictItem.getSibling()) : 0);
				//
				itemGovList.add(item);
			}
		}

		DictCollection domainCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(ReadFilePropertiesUtils.get(ConstantUtils.SERVICE_DOMAIN),
				groupId);
		List<DictItem> domainItems = DictItemLocalServiceUtil
				.findByF_dictCollectionId(domainCollection.getDictCollectionId());

		List<DictItemCompareModel> itemDomainList = null; 
		if (domainItems != null && domainItems.size() > 0) {
			itemDomainList = new ArrayList<>();
			DictItemCompareModel item = null;
			for (DictItem dictItem : domainItems) {
				item = new DictItemCompareModel(dictItem.getItemCode(), dictItem.getItemName(),
						Validator.isNotNull(dictItem.getSibling()) ? GetterUtil.getInteger(dictItem.getSibling()) : 0);
				//
				itemDomainList.add(item);
			}
		}

		//SearchContext searchContext = new SearchContext();
		//searchContext.setCompanyId(company.getCompanyId());

		JSONObject results = JSONFactoryUtil.createJSONObject();
		JSONArray arrGovAgency = JSONFactoryUtil.createJSONArray();

		try {
			if (itemGovList != null && itemGovList.size() > 0) {
				Collections.sort(itemGovList, DictItemCompareModel.SiblingComparator);
				for (DictItemCompareModel govItem : itemGovList) {
	
	//				LinkedHashMap<String, Object> paramsGovAgent = new LinkedHashMap<String, Object>();
	//				paramsGovAgent.put(Field.GROUP_ID, String.valueOf(groupId));
	//				paramsGovAgent.put(Field.KEYWORD_SEARCH, query.getKeyword());
	//				paramsGovAgent.put(ServiceConfigTerm.GOVAGENCY_CODE, govItem.getItemCode());
	//				long countGov = ServiceConfigLocalServiceUtil.countLucene(paramsGovAgent, searchContext);
	
				JSONObject govElm = JSONFactoryUtil.createJSONObject();
				List<ServiceConfig> lstGovs = ServiceConfigLocalServiceUtil.searchByGovAgency(query.getKeyword(), govItem.getItemCode(), groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
				Map<Long, ServiceInfo> mapServiceInfos = new HashMap<>();
				List<ServiceInfo> lstServiceInfos =	ServiceInfoLocalServiceUtil.findByGroup(groupId);
				for (ServiceInfo si : lstServiceInfos) {
					mapServiceInfos.put(si.getServiceInfoId(), si);
				}
				long countGov = lstGovs.size();
				
				if (countGov != 0) {
	
					govElm.put(DossierTerm.GOV_AGENCY_CODE, govItem.getItemCode());
					govElm.put(DossierTerm.GOV_AGENCY_NAME, govItem.getItemName());
	
					JSONArray arrDomain = JSONFactoryUtil.createJSONArray();
	
						if (itemDomainList != null && itemDomainList.size() > 0) {
							Collections.sort(itemDomainList, DictItemCompareModel.SiblingComparator);
							for (DictItemCompareModel domainItem : itemDomainList) {
		
						JSONObject domElm = JSONFactoryUtil.createJSONObject();
		
		//						LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
		//
		//						params.put(Field.GROUP_ID, String.valueOf(groupId));
		//						params.put(Field.KEYWORD_SEARCH, query.getKeyword());
		//						params.put(ServiceConfigTerm.GOVAGENCY_CODE, govItem.getItemCode());
		//						params.put(ServiceConfigTerm.DOMAIN_CODE, domainItem.getItemCode());
		//
		//						Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable",
		//								Sort.STRING_TYPE, GetterUtil.getBoolean(query.getOrder())) };
		//
		//						long countGovDomain = ServiceConfigLocalServiceUtil.countLucene(params, searchContext);
						List<ServiceConfig> lstGovDomains = new ArrayList<>();
						for (ServiceConfig sc : lstGovs) {
							if (mapServiceInfos.containsKey(sc.getServiceInfoId())
									&& mapServiceInfos.get(sc.getServiceInfoId()).getDomainCode().equals(domainItem.getItemCode())) {
								lstGovDomains.add(sc);
							}
						}
						long countGovDomain = lstGovDomains.size();
						
						if (countGovDomain != 0) {

							domElm.put(DossierTerm.DOMAIN_CODE, domainItem.getItemCode());
							domElm.put(DossierTerm.DOMAIN_NAME, domainItem.getItemName());
							JSONArray arrService = JSONFactoryUtil.createJSONArray();
							for (ServiceConfig sc : lstGovDomains) {
								int level = sc.getServiceLevel();
								if (level > 2) {
									JSONObject srvElm = JSONFactoryUtil.createJSONObject();

									srvElm.put(ServiceInfoTerm.SERVICE_INFO_ID, sc.getServiceInfoId());
									srvElm.put(ServiceConfigTerm.SERVICECONFIG_ID, sc.getServiceConfigId());
									srvElm.put(ServiceInfoTerm.SERVICE_NAME, (mapServiceInfos.containsKey(sc.getServiceInfoId()) ? mapServiceInfos.get(sc.getServiceInfoId()).getServiceName() : StringPool.BLANK));
									srvElm.put(ServiceInfoTerm.LEVEL, sc.getServiceLevel());

									arrService.put(srvElm);									
								}								
							}

							if (arrService.length() > 0) {
								domElm.put(ServiceConfigTerm.SERVICE_CONFIGS, arrService);								
								arrDomain.put(domElm);
							}
		
								}
		
						}
					}
	
					if (arrDomain.length() > 0) {
						govElm.put(ServiceConfigTerm.DOMAINS, arrDomain);						
						arrGovAgency.put(govElm);						
					}
	
					}
	
				}
			}

			results.put(ServiceConfigTerm.GOV_AGENCYS, arrGovAgency);
			
			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(results)).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	Log _log = LogFactoryUtil.getLog(ServiceConfigManagementImpl.class);

	@Override
	public Response getServiceConfigsByDomain(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, ServiceInfoSearchModel query) {
		
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		DictCollection domainCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(ReadFilePropertiesUtils.get(ConstantUtils.SERVICE_DOMAIN),
				groupId);

		List<DictItem> domainItems = DictItemLocalServiceUtil
				.findByF_dictCollectionId(domainCollection.getDictCollectionId());


		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(company.getCompanyId());

		if (query.getEnd() == 0) {

			query.setStart(-1);

			query.setEnd(-1);

		}

		JSONObject results = JSONFactoryUtil.createJSONObject();

		JSONArray domains = JSONFactoryUtil.createJSONArray();
		
		List<String> keys = new ArrayList<String>();

		try {

			for (DictItem domainItem : domainItems) {

				LinkedHashMap<String, Object> paramsDomain = new LinkedHashMap<String, Object>();

				paramsDomain.put(Field.GROUP_ID, String.valueOf(groupId));
				paramsDomain.put(ServiceConfigTerm.DOMAIN_CODE, domainItem.getItemCode());
				paramsDomain.put(Field.KEYWORD_SEARCH, query.getKeyword());

				JSONObject domain = JSONFactoryUtil.createJSONObject();

				long countDomain = ServiceConfigLocalServiceUtil.countLucene(paramsDomain, searchContext);

				if (countDomain != 0) {

					domain.put(DossierTerm.DOMAIN_ID, domainItem.getPrimaryKey());
					domain.put(DossierTerm.DOMAIN_NAME, domainItem.getItemName());
				
					Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + ReadFilePropertiesUtils.get(ConstantUtils.SORT_PATTERN),
							Sort.STRING_TYPE, GetterUtil.getBoolean(query.getOrder())) };
					
					//Get list service config by Domain code
					List<Document> docs = ServiceConfigLocalServiceUtil
							.searchLucene(paramsDomain, sorts, query.getStart(), query.getEnd(), searchContext)
							.toList();
					
					JSONArray govAgencys = JSONFactoryUtil.createJSONArray();
					
					for (Document doc : docs) {

						String govAgencyCode = GetterUtil.getString(doc.get(ServiceConfigTerm.GOVAGENCY_CODE));
						String govAgencyName = GetterUtil.getString(doc.get(ServiceConfigTerm.GOVAGENCY_NAME));
						
						if(keys.contains(doc.get(ServiceConfigTerm.SERVICE_CODE) + StringPool.DASH + govAgencyCode)){
							continue;
						}
						
						keys.add(doc.get(ServiceConfigTerm.SERVICE_CODE) + StringPool.DASH + govAgencyCode);
						
						JSONObject govAgency = JSONFactoryUtil.createJSONObject();
						
						JSONArray serviceConfigGroupByGov = JSONFactoryUtil.createJSONArray();
						
						paramsDomain.put(ServiceConfigTerm.SERVICE_CODE, doc.get(ServiceConfigTerm.SERVICE_CODE));
						
						paramsDomain.put(ServiceConfigTerm.GOVAGENCY_CODE, govAgencyCode);
//						_log.info("SERVICE_CODE: "+doc.get(ServiceConfigTerm.SERVICE_CODE));
						
						List<Document> serviceConfigByInfo = ServiceConfigLocalServiceUtil
								.searchLucene(paramsDomain, sorts, query.getStart(), query.getEnd(), searchContext)
								.toList();
//						_log.info("serviceConfigByInfo: "+serviceConfigByInfo.size());
						
						for (Document serDoc : serviceConfigByInfo) {
							JSONObject configElm = JSONFactoryUtil.createJSONObject();
							
							configElm.put(ServiceInfoTerm.SERVICE_CODE, doc.get(ServiceConfigTerm.SERVICE_CODE));
							configElm.put(ServiceInfoTerm.SERVICE_NAME, doc.get(ServiceConfigTerm.SERVICE_NAME));
							configElm.put(ServiceInfoTerm.LEVEL, serDoc.get(ServiceConfigTerm.SERVICE_LEVEL));
							configElm.put(ServiceConfigTerm.SERVICECONFIG_ID, serDoc.get(Field.ENTRY_CLASS_PK));
							serviceConfigGroupByGov.put(configElm);
						}
						
						govAgency.put(DossierTerm.GOV_AGENCY_CODE, govAgencyCode);
						govAgency.put(DossierTerm.GOV_AGENCY_NAME, govAgencyName);
						govAgency.put(ServiceConfigTerm.SERVICE_CONFIGS, serviceConfigGroupByGov);
						govAgencys.put(govAgency);
					}
					domain.put(ServiceConfigTerm.GOV_AGENCYS, govAgencys);
					
					domains.put(domain);
				}
				
			}
			results.put(ServiceConfigTerm.DOMAINS, domains);
			
			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(results)).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response getGuide(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, String id, ServiceConfigSearchModel search, String reportType) {

		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long serviceConfigId = GetterUtil.getLong(id);

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			ServiceConfig serviceConfig = ServiceConfigLocalServiceUtil.fetchServiceConfig(serviceConfigId);
			JSONObject jsonGuide = JSONFactoryUtil.createJSONObject();
			jsonGuide.put(ServiceConfigTerm.SERVICE_CODE, search.getServiceCode());
			jsonGuide.put(ServiceConfigTerm.SERVICE_NAME, search.getServiceName());
			
			Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, user.getUserId());
			if (employee != null) {
				jsonGuide.put(ServiceConfigTerm.ACTION_USER, employee.getFullName());
			} else {
				jsonGuide.put(ServiceConfigTerm.ACTION_USER, user.getFullName());
			}
			jsonGuide.put(ApplicantTerm.APPLICANTNAME, search.getApplicantName());
			jsonGuide.put(ApplicantTerm.APPLICANTIDTYPE, search.getApplicantIdType());
			jsonGuide.put(ApplicantTerm.ADDRESS, search.getApplicantAddress());
			jsonGuide.put(ApplicantTerm.CONTACTEMAIL, search.getApplicantEmail());
			jsonGuide.put(ApplicantTerm.CONTACTTELNO, search.getApplicantTelNo());
			jsonGuide.put(EmployeeTerm.EMPLOYEE_NAME, search.getEmployeeName());
			jsonGuide.put(DossierTemplateTerm.TEMPLATE_NO, search.getTemplateNo());
			jsonGuide.put(DossierTerm.APPLICANT_NOTE, search.getApplicantNote());
			if (serviceConfig != null) {
				jsonGuide.put(ServiceConfigTerm.SERVICE_INSTRUCTION, serviceConfig.getServiceInstruction());
				jsonGuide.put(ServiceConfigTerm.GOVAGENCY_CODE, serviceConfig.getGovAgencyCode());
				jsonGuide.put(ServiceConfigTerm.GOVAGENCY_NAME, serviceConfig.getGovAgencyName());
			}

			// Get dossierTemplate by dossierTemplateNo
			String type = search.getType();
			jsonGuide.put(ReadFilePropertiesUtils.get(ConstantUtils.VALUE_TYPE), type);
			if (Validator.isNotNull(type)) {
				DossierTemplate template = DossierTemplateLocalServiceUtil.getByTemplateNo(groupId, search.getTemplateNo());
				JSONArray partArr = JSONFactoryUtil.createJSONArray();
				if (template != null) {
					String strPartNo = search.getPartNo();
					jsonGuide.put(DossierTemplateTerm.TEMPLATE_NAME, template.getTemplateName());
					// Get list part by templateNo
					List<DossierPart> partList = DossierPartLocalServiceUtil.getByTemplateNo(groupId,
							template.getTemplateNo());
					if (partList != null && partList.size() > 0) {
						JSONObject jsonPart = null;
						if (Validator.isNotNull(strPartNo)) {
							for (DossierPart part : partList) {
								if (part != null && strPartNo.contains(part.getPartNo())) {
									if (part.getPartType() != DossierPartTerm.DOSSIER_PART_TYPE_OUTPUT) {
										jsonPart = JSONFactoryUtil.createJSONObject();
										jsonPart.put(DossierPartTerm.PART_NO, part.getPartNo());
										jsonPart.put(DossierPartTerm.PART_NAME, part.getPartName());
										jsonPart.put(DossierPartTerm.PART_TIP, part.getPartTip());
										jsonPart.put(DossierPartTerm.PART_TYPE, part.getPartType());
										jsonPart.put(DossierPartTerm.MULTIPLE, part.getMultiple());

										partArr.put(jsonPart);
									}
								}
							}
						} else {
							for (DossierPart part : partList) {
								if (part != null && part.getPartType() != DossierPartTerm.DOSSIER_PART_TYPE_OUTPUT) {
									jsonPart = JSONFactoryUtil.createJSONObject();
									jsonPart.put(DossierPartTerm.PART_NO, part.getPartNo());
									jsonPart.put(DossierPartTerm.PART_NAME, part.getPartName());
									jsonPart.put(DossierPartTerm.PART_TIP, part.getPartTip());
									jsonPart.put(DossierPartTerm.PART_TYPE, part.getPartType());
									jsonPart.put(DossierPartTerm.MULTIPLE, part.getMultiple());

									partArr.put(jsonPart);
								}
							}
						}
					}
					// Add key template in jsonOption
					jsonGuide.put(ProcessOptionTerm.TEMPLATE, partArr);
				} else {
					jsonGuide.put(DossierTemplateTerm.TEMPLATE_NAME, StringPool.BLANK);
					// Add key template in jsonOption
					jsonGuide.put(ProcessOptionTerm.TEMPLATE, partArr);
				}
			}
			
			DocumentType docType = DocumentTypeLocalServiceUtil.getByTypeCode(groupId, search.getTypeCode());
			String documentScript = StringPool.BLANK;
			if (docType != null) {
				documentScript = docType.getDocumentScript();
			}

			Message message = new Message();
			message.put(DeliverableTerm.FORM_REPORT, documentScript);
			message.put(DeliverableTerm.FORM_DATA, jsonGuide.toJSONString());
			if (Validator.isNotNull(reportType)) {
				message.put(DeliverableTerm.REPORT_TYPE, reportType);
			}
			try {
				String previewResponse = (String) MessageBusUtil
						.sendSynchronousMessage(ConstantUtils.JASPER_DESTINATION, message, 10000);

				if (Validator.isNotNull(previewResponse)) {
				}

				File file = new File(previewResponse);

				ResponseBuilder responseBuilder = Response.ok((Object) file);

				responseBuilder.header(ReadFilePropertiesUtils.get(ConstantUtils.TYPE_DISPOSITON), ReadFilePropertiesUtils.get(ConstantUtils.VALUE_PATTERN_FILENAME) + file.getName() + StringPool.QUOTE);
				responseBuilder.header(ConstantUtils.CONTENT_TYPE, ReadFilePropertiesUtils.get(ConstantUtils.CONTENT_TYPE_PDF));					

				return responseBuilder.build();

			} catch (MessageBusException e) {
				_log.error(e);
			}
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);

		}
		return null;
	}

	@Override
	public Response getDomainsByGovAgencyCode(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, String govAgencyCode, ServiceContext serviceContext,
			ServiceInfoSearchModel query) {

		JSONObject results = JSONFactoryUtil.createJSONObject();

		try {
			List<ServiceConfig> configList = ServiceConfigLocalServiceUtil.getByGovAgencyCode(govAgencyCode);

			int total = 0;
			JSONArray domains = JSONFactoryUtil.createJSONArray();
			if (configList != null && configList.size() > 0) {
				for (ServiceConfig config : configList) {
					long serviceInfoId = config.getServiceInfoId();
					if (serviceInfoId > 0) {
						ServiceInfo serviceInfo = ServiceInfoLocalServiceUtil.fetchServiceInfo(serviceInfoId);
						if (serviceInfo != null) {
							String domainCode = serviceInfo.getDomainCode();
							if (Validator.isNotNull(domainCode)) {
								boolean flag = true;
								JSONObject jsonDomain = JSONFactoryUtil.createJSONObject();
								if (domains != null && domains.length() > 0) {
									for (int i = 0; i < domains.length(); i++) {
										JSONObject jsonData = domains.getJSONObject(i);
										if (domainCode.equals(jsonData.getString(ServiceConfigTerm.DOMAIN_CODE))) {
											flag = false;
											break;
										}
									}
									if (flag) {
										total += 1;
										jsonDomain.put(ServiceConfigTerm.DOMAIN_CODE, domainCode);
										jsonDomain.put(ServiceConfigTerm.DOMAIN_NAME, serviceInfo.getDomainName());
										jsonDomain.put(ServiceConfigTerm.DOMAIN_INDEX, serviceInfo.getDomainIndex());
										//
										domains.put(jsonDomain);
									}
								} else {
									total += 1;
									jsonDomain.put(ServiceConfigTerm.DOMAIN_CODE, domainCode);
									jsonDomain.put(ServiceConfigTerm.DOMAIN_NAME, serviceInfo.getDomainName());
									jsonDomain.put(ServiceConfigTerm.DOMAIN_INDEX, serviceInfo.getDomainIndex());
									//
									domains.put(jsonDomain);
								}
							}
						}
					}
				}
			}
			results.put(ConstantUtils.TOTAL, total);
			results.put(ConstantUtils.DATA, domains);

			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(results)).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getConfigByGovAgencys(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, ServiceInfoSearchModel query) {

		JSONObject results = JSONFactoryUtil.createJSONObject();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		int level = GetterUtil.getInteger(query.getLevel());

		try {
			List<ServiceConfig> configList = ServiceConfigLocalServiceUtil.getByLevel(groupId, level);

			int total = 0;
			JSONArray agencys = JSONFactoryUtil.createJSONArray();
			if (configList != null && configList.size() > 0) {
				for (ServiceConfig config : configList) {
							String govAgencyCode = config.getGovAgencyCode();
							if (Validator.isNotNull(govAgencyCode)) {
								boolean flag = true;
								JSONObject jsonGovAgency = JSONFactoryUtil.createJSONObject();
								if (agencys != null && agencys.length() > 0) {
									for (int i = 0; i < agencys.length(); i++) {
										JSONObject jsonData = agencys.getJSONObject(i);
										if (govAgencyCode.equals(jsonData.getString(ServiceConfigTerm.GOVAGENCY_CODE))) {
											flag = false;
											break;
										}
									}
									if (flag) {
										total += 1;
										jsonGovAgency.put(ServiceConfigTerm.GOVAGENCY_CODE, govAgencyCode);
										jsonGovAgency.put(ServiceConfigTerm.GOVAGENCY_NAME, config.getGovAgencyName());
										//
										agencys.put(jsonGovAgency);
									}
								} else {
									total += 1;
									jsonGovAgency.put(ServiceConfigTerm.GOVAGENCY_CODE, govAgencyCode);
									jsonGovAgency.put(ServiceConfigTerm.GOVAGENCY_NAME, config.getGovAgencyName());
									//
									agencys.put(jsonGovAgency);
								}
							}
				}
			}
			results.put(ConstantUtils.TOTAL, total);
			results.put(ConstantUtils.DATA, agencys);

			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(results)).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
