package org.opencps.api.controller.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.ServiceConfigManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.ServiceConfigUtils;
import org.opencps.api.controller.util.ServiceInfoUtils;
import org.opencps.api.serviceconfig.model.ProcessOptionInputModel;
import org.opencps.api.serviceconfig.model.ProcessOptionResultsModel;
import org.opencps.api.serviceconfig.model.ProcessOptionSearchModel;
import org.opencps.api.serviceconfig.model.ServiceConfigDetailModel;
import org.opencps.api.serviceconfig.model.ServiceConfigInputModel;
import org.opencps.api.serviceconfig.model.ServiceConfigResultsModel;
import org.opencps.api.serviceconfig.model.ServiceConfigSearchModel;
import org.opencps.api.serviceinfo.model.ServiceInfoModel;
import org.opencps.api.serviceinfo.model.ServiceInfoSearchModel;
import org.opencps.api.serviceinfo.model.ServiceInfoServiceConfig;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.auth.api.keys.ActionKeys;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.action.ServiceConfigActions;
import org.opencps.dossiermgt.action.ServiceInfoActions;
import org.opencps.dossiermgt.action.impl.ServiceConfigActionImpl;
import org.opencps.dossiermgt.action.impl.ServiceInfoActionsImpl;
import org.opencps.dossiermgt.constants.ProcessOptionTerm;
import org.opencps.dossiermgt.constants.ServiceConfigTerm;
import org.opencps.dossiermgt.constants.ServiceInfoTerm;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class ServiceConfigManagementImpl implements ServiceConfigManagement {

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

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

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

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };

			JSONObject jsonData = actions.getServiceConfigs(serviceContext.getUserId(), serviceContext.getCompanyId(),
					groupId, params, sorts, query.getStart(), query.getEnd(), serviceContext);

			results.setTotal(jsonData.getInt("total"));

			results.getData()
					.addAll(ServiceConfigUtils.mappingToServiceConfigResults((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response addServiceConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, ServiceConfigInputModel input) {

		ServiceConfigActions actions = new ServiceConfigActionImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
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

			ServiceConfig serviceConfig = actions.updateServiceConfig(0l, userId, groupId,
					(long) input.getServiceInfoId(), input.getGovAgencyCode(), input.getServiceInstruction(),
					(int) input.getServiceLevel(), input.getServiceUrl(), GetterUtil.getBoolean(input.getForCitizen()),
					GetterUtil.getBoolean(input.getForBusiness()), GetterUtil.getBoolean(input.getPostalService()),
					GetterUtil.getBoolean(input.getRegistration()), serviceContext);

			returnModel = ServiceConfigUtils.mapptingToServiceConfig(serviceConfig);

			return Response.status(200).entity(returnModel).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
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
			ErrorMsg error = new ErrorMsg();

			if (e instanceof NotFoundException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(error).build();
			} else {
				error.setMessage("Internal Server Error");
				error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
				error.setDescription(e.getMessage());

				return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

			}
		}

	}

	@Override
	public Response updateServiceConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, ServiceConfigInputModel input) {

		ServiceConfigActions actions = new ServiceConfigActionImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
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

			ServiceConfig serviceConfig = actions.updateServiceConfig(id, userId, groupId,
					(long) input.getServiceInfoId(), input.getGovAgencyCode(), input.getServiceInstruction(),
					(int) input.getServiceLevel(), input.getServiceUrl(), GetterUtil.getBoolean(input.getForCitizen()),
					GetterUtil.getBoolean(input.getForBusiness()), GetterUtil.getBoolean(input.getPostalService()),
					GetterUtil.getBoolean(input.getRegistration()), serviceContext);

			returnModel = ServiceConfigUtils.mapptingToServiceConfig(serviceConfig);

			return Response.status(200).entity(returnModel).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
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
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
		}

	}

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

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.KEYWORD_SEARCH, query.getKeyword());

			String configId = String.valueOf(id);
			String applicantType = query.getApplicant();

			params.put(ProcessOptionTerm.SERVICE_CONFIG_ID, configId);
			params.put(ProcessOptionTerm.APPLICATION_TYPE, applicantType);

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };

			JSONObject jsonData = actions.getProcessOptions(userId, serviceContext.getCompanyId(), groupId, params,
					sorts, query.getStart(), query.getEnd(), serviceContext);

			results.setTotal(jsonData.getInt("total"));

			results.getData()
					.addAll(ServiceConfigUtils.mappingToProcessOptionResults((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response addProcessOption(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, ProcessOptionInputModel input) {

		ServiceConfigActions actions = new ServiceConfigActionImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		org.opencps.api.serviceconfig.model.ProcessOption returnModel = new org.opencps.api.serviceconfig.model.ProcessOption();

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, ServiceConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			ProcessOption serviceConfig = actions.updateOption(groupId, input.getOptionName(), 0l, id,
					input.getSeqOrder(), input.getAutoSelect(), input.getInstructionNote(), input.getSubmissionNote(),
					input.getDossierTemplateId(), input.getServiceProcessId(), serviceContext);

			returnModel = ServiceConfigUtils.mappingToProcessOption(serviceConfig);

			return Response.status(200).entity(returnModel).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
		}

	}

	@Override
	public Response updateProcessOption(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, long optionId, ProcessOptionInputModel input) {
		ServiceConfigActions actions = new ServiceConfigActionImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		org.opencps.api.serviceconfig.model.ProcessOption returnModel = new org.opencps.api.serviceconfig.model.ProcessOption();

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, ServiceConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			ProcessOption processOption = actions.updateOption(groupId, input.getOptionName(), optionId, id,
					input.getSeqOrder(), input.getAutoSelect(), input.getInstructionNote(), input.getSubmissionNote(),
					input.getDossierTemplateId(), input.getServiceProcessId(), serviceContext);

			returnModel = ServiceConfigUtils.mappingToProcessOption(processOption);

			return Response.status(200).entity(returnModel).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
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
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
		}
	}

	@Override
	public Response getServiceConfigsByGovAgency(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, ServiceInfoSearchModel query) {
		
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		DictCollection govAgencyCollection = DictCollectionLocalServiceUtil
				.fetchByF_dictCollectionCode("GOVERNMENT_AGENCY", groupId);

		List<DictItem> govItems = DictItemLocalServiceUtil
				.findByF_dictCollectionId(govAgencyCollection.getDictCollectionId());

		DictCollection domainCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode("SERVICE_DOMAIN",
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

		JSONArray arrGovAgency = JSONFactoryUtil.createJSONArray();

		try {

			for (DictItem govItem : govItems) {

				LinkedHashMap<String, Object> paramsGovAgent = new LinkedHashMap<String, Object>();

				paramsGovAgent.put(Field.GROUP_ID, String.valueOf(groupId));
				paramsGovAgent.put(Field.KEYWORD_SEARCH, query.getKeyword());
				paramsGovAgent.put(ServiceConfigTerm.GOVAGENCY_CODE, govItem.getItemCode());

				JSONObject govElm = JSONFactoryUtil.createJSONObject();

				long countGov = ServiceConfigLocalServiceUtil.countLucene(paramsGovAgent, searchContext);

				if (countGov != 0) {

					govElm.put("govAgencyCode", govItem.getItemCode());
					govElm.put("govAgencyName", govItem.getItemName());

					JSONArray arrDomain = JSONFactoryUtil.createJSONArray();

					for (DictItem domainItem : domainItems) {

						JSONObject domElm = JSONFactoryUtil.createJSONObject();

						LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

						params.put(Field.GROUP_ID, String.valueOf(groupId));
						params.put(Field.KEYWORD_SEARCH, query.getKeyword());
						params.put(ServiceConfigTerm.GOVAGENCY_CODE, govItem.getItemCode());
						params.put(ServiceConfigTerm.DOMAIN_CODE, domainItem.getItemCode());

						Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable",
								Sort.STRING_TYPE, GetterUtil.getBoolean(query.getOrder())) };

						long countGovDomain = ServiceConfigLocalServiceUtil.countLucene(params, searchContext);

						if (countGovDomain != 0) {

							domElm.put("domainCode", domainItem.getItemCode());
							domElm.put("domainName", domainItem.getItemName());

							List<Document> docs = ServiceConfigLocalServiceUtil
									.searchLucene(params, sorts, query.getStart(), query.getEnd(), searchContext)
									.toList();

							JSONArray arrService = JSONFactoryUtil.createJSONArray();

							for (Document doc : docs) {

								JSONObject srvElm = JSONFactoryUtil.createJSONObject();

								srvElm.put("serviceInfoId", doc.get(ServiceConfigTerm.SERVICEINFO_ID));
								srvElm.put("serviceConfigId", doc.get(Field.ENTRY_CLASS_PK));
								srvElm.put("serviceInfoName", doc.get(ServiceConfigTerm.SERVICE_NAME));
								srvElm.put("level", doc.get(ServiceConfigTerm.SERVICE_LEVEL));

								arrService.put(srvElm);
							}

							domElm.put("serviceConfigs", arrService);

							arrDomain.put(domElm);
						}

					}

					govElm.put("domains", arrDomain);
					
					arrGovAgency.put(govElm);

				}

			}

			results.put("govAgencies", arrGovAgency);
			
			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(results)).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
		}

	}

	@Override
	public Response getServiceConfigsByDomain(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, ServiceInfoSearchModel query) {
		
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		//DictCollection govAgencyCollection = DictCollectionLocalServiceUtil
		//		.fetchByF_dictCollectionCode("GOVERNMENT_AGENCY", groupId);

		//List<DictItem> govItems = DictItemLocalServiceUtil
		//		.findByF_dictCollectionId(govAgencyCollection.getDictCollectionId());

		DictCollection domainCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode("SERVICE_DOMAIN",
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

					domain.put("domainId", domainItem.getPrimaryKey());
					domain.put("domainName", domainItem.getItemName());
				
					Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable",
							Sort.STRING_TYPE, GetterUtil.getBoolean(query.getOrder())) };
					
					//Get list service config by Domain code
					List<Document> docs = ServiceConfigLocalServiceUtil
							.searchLucene(paramsDomain, sorts, query.getStart(), query.getEnd(), searchContext)
							.toList();
					
					JSONArray govAgencys = JSONFactoryUtil.createJSONArray();
					
					for (Document doc : docs) {

						String govAgencyCode = GetterUtil.getString(doc.get(ServiceConfigTerm.GOVAGENCY_CODE));
						String govAgencyName = GetterUtil.getString(doc.get(ServiceConfigTerm.GOVAGENCY_NAME));
						
						if(keys.contains(domainItem.getItemCode() + StringPool.DASH + govAgencyCode)){
							continue;
						}
						
						keys.add(domainItem.getItemCode() + StringPool.DASH + govAgencyCode);
						
						JSONObject govAgency = JSONFactoryUtil.createJSONObject();
						
						JSONArray serviceConfigGroupByGov = JSONFactoryUtil.createJSONArray();
						
						paramsDomain.put(ServiceConfigTerm.SERVICE_CODE, doc.get(ServiceConfigTerm.SERVICE_CODE));
						
						paramsDomain.put(ServiceConfigTerm.GOVAGENCY_CODE, govAgencyCode);
						
						List<Document> serviceConfigByInfo = ServiceConfigLocalServiceUtil
								.searchLucene(paramsDomain, sorts, query.getStart(), query.getEnd(), searchContext)
								.toList();
						
						for (Document serDoc : serviceConfigByInfo) {
							JSONObject configElm = JSONFactoryUtil.createJSONObject();
							
							configElm.put("serviceCode", doc.get(ServiceConfigTerm.SERVICE_CODE));
							configElm.put("serviceName", doc.get(ServiceConfigTerm.SERVICE_NAME));
							configElm.put("level", serDoc.get(ServiceConfigTerm.SERVICE_LEVEL));
							configElm.put("serviceConfigId", serDoc.get(Field.ENTRY_CLASS_PK));
							serviceConfigGroupByGov.put(configElm);
						}
						
						govAgency.put("govAgencyCode", govAgencyCode);
						govAgency.put("govAgencyName", govAgencyName);
						govAgency.put("serviceConfigs", serviceConfigGroupByGov);
						govAgencys.put(govAgency);
					}
					domain.put("govAgencys", govAgencys);
					
					domains.put(domain);
				}
				
			}
			results.put("domains", domains);
			
			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(results)).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
		}

	}

	private List<JSONObject> generateGovAgencys(ServiceContext serviceContext) {

		List<JSONObject> govAgencys = new ArrayList<>();

		DictCollection govAgencyCollection = DictCollectionLocalServiceUtil
				.fetchByF_dictCollectionCode("GOVERNMENT_AGENCY", serviceContext.getScopeGroupId());

		List<DictItem> govItems = DictItemLocalServiceUtil
				.findByF_dictCollectionId(govAgencyCollection.getDictCollectionId());

		for (DictItem govItem : govItems) {
			JSONObject govJsonObj = JSONFactoryUtil.createJSONObject();

			govJsonObj.put("govAgencyCode", govItem.getItemCode());
			govJsonObj.put("govAgencyName", govItem.getItemName());

			govJsonObj.put("domains", generateDomains(serviceContext));

			govAgencys.add(govJsonObj);
		}

		return govAgencys;
	}

	private List<JSONObject> generateDomains(ServiceContext serviceContext) {

		List<JSONObject> domains = new ArrayList<>();

		DictCollection domainCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode("SERVICE_DOMAIN",
				serviceContext.getScopeGroupId());

		List<DictItem> domainItems = DictItemLocalServiceUtil
				.findByF_dictCollectionId(domainCollection.getDictCollectionId());

		for (DictItem domainItem : domainItems) {
			JSONObject domainJsonObj = JSONFactoryUtil.createJSONObject();

			domainJsonObj.put("domainCode", domainItem.getItemCode());
			domainJsonObj.put("domainName", domainItem.getItemName());

			domainJsonObj.put("serviceInfos", generateServiceInfos(serviceContext, domainItem.getItemCode()));

			domains.add(domainJsonObj);
		}

		return domains;
	}

	private List<JSONObject> generateServiceInfos(ServiceContext serviceContext, String domainCode) {

		List<JSONObject> serviceInfos = new ArrayList<>();

		ServiceInfoActions serviceInfoActions = new ServiceInfoActionsImpl();

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();
		params.put(Field.GROUP_ID, String.valueOf(serviceContext.getScopeGroupId()));
		params.put(ServiceInfoTerm.DOMAIN_CODE, domainCode);

		JSONObject serviceInfoJson = serviceInfoActions.getServiceInfos(serviceContext.getUserId(),
				serviceContext.getCompanyId(), serviceContext.getScopeGroupId(), params, null, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, serviceContext);

		List<ServiceInfoModel> serviceInfoList = ServiceInfoUtils
				.mappingToServiceInfoResultModel((List<Document>) serviceInfoJson.get("data"), serviceContext);

		if (Validator.isNotNull(serviceInfoList)) {
			for (ServiceInfoModel serviceInfo : serviceInfoList) {
				JSONObject serviceInfoJsonObj = JSONFactoryUtil.createJSONObject();

				serviceInfoJsonObj.put("serviceCode", serviceInfo.getServiceCode());
				serviceInfoJsonObj.put("serviceName", serviceInfo.getServiceName());

				serviceInfoJsonObj.put("serviceConfigs", generateServiceConfigs(serviceInfo.getServiceConfigs()));

				serviceInfos.add(serviceInfoJsonObj);
			}
		}

		return serviceInfos;
	}

	private List<JSONObject> generateServiceConfigs(List<ServiceInfoServiceConfig> serviceConfigList) {

		List<JSONObject> serviceConfigs = new ArrayList<>();

		for (ServiceInfoServiceConfig cf : serviceConfigList) {
			JSONObject cfJson = JSONFactoryUtil.createJSONObject();

			cfJson.put("level", cf.getServiceLevel());
			cfJson.put("govAgencyCode", cf.getGovAgencyCode());
			cfJson.put("govAgencyName", cf.getGovAgencyName());
			// cfJson.put("serviceConfigId", cf.) // TODO need serviceConfigId
		}

		return serviceConfigs;
	}

}
