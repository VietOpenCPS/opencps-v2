package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.OneGateController;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.api.controller.util.OneGateUtils;
import org.opencps.api.controller.util.ServiceConfigUtils;
import org.opencps.api.dossier.model.DossierDetailModel;
import org.opencps.api.dossier.model.DossierOnegateInputModel;
import org.opencps.api.dossier.model.DossierSearchModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.datamgt.utils.DateTimeUtils;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.ServiceProcessActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.action.impl.DossierPermission;
import org.opencps.dossiermgt.action.impl.ServiceProcessActionsImpl;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.dossiermgt.constants.DossierTemplateTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.ProcessOptionTerm;
import org.opencps.dossiermgt.constants.ServiceConfigTerm;
import org.opencps.dossiermgt.constants.ServiceInfoTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.model.ServiceInfoMapping;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoMappingLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessRoleLocalServiceUtil;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class OneGateControllerImpl implements OneGateController {

	@Override
	public Response getServiceconfigs(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String domain, String public_, Request requestCC,
			DossierSearchModel query) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
					isAdmin = true;
					break;
				}
			}
			long startTime = System.currentTimeMillis();
			String searchGovAgencyCode = query.getSearchGovAgencyCode();
			List<ServiceConfig> serviceConfigs = ServiceConfigLocalServiceUtil.getByGroupId(groupId,
					searchGovAgencyCode, user.getUserId());

			Map<Long, ServiceInfo> mapServiceInfos = new HashMap<>();
			List<ServiceInfo> lstServiceInfos = null;
			if (Validator.isNotNull(public_) && !Boolean.parseBoolean(public_)) {
				lstServiceInfos = ServiceInfoLocalServiceUtil.findByGroupAndPublic(groupId,
						Boolean.parseBoolean(public_));
			} else {
				lstServiceInfos = ServiceInfoLocalServiceUtil.findByGroupAndPublic(groupId, true);
			}
			if (lstServiceInfos != null && lstServiceInfos.size() > 0) {
				for (ServiceInfo serviceInfo : lstServiceInfos) {
					mapServiceInfos.put(serviceInfo.getServiceInfoId(), serviceInfo);
				}
			}
			long endTime = System.currentTimeMillis();
			//startTime = System.currentTimeMillis();
			JSONObject results = JSONFactoryUtil.createJSONObject();
			Employee e = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, user.getUserId());

			JSONArray data = JSONFactoryUtil.createJSONArray();
			int total = 0;
			long[] roleIds = UserLocalServiceUtil.getRolePrimaryKeys(user.getUserId());
			//Add Map role
			List<ServiceProcessRole> lstPRoles = (roleIds != null && roleIds.length > 0)
					? ServiceProcessRoleLocalServiceUtil.findByRID(roleIds)
					: null;

			StringBuilder sbProcessId = null;
			if (lstPRoles != null && lstPRoles.size() > 0) {
				sbProcessId = new StringBuilder();
				for (ServiceProcessRole spr : lstPRoles) {
					if (sbProcessId.length() > 0) {
						sbProcessId.append(StringPool.COMMA);
						sbProcessId.append(spr.getServiceProcessId());
					} else {
						sbProcessId.append(spr.getServiceProcessId());
					}
				}
			}
			
			startTime = System.currentTimeMillis();
			/*******/
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.KEYWORD_SEARCH, StringPool.BLANK);
			params.put(ProcessOptionTerm.SERVICE_PROCESS_ID, sbProcessId.toString());

			String querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_SORT), query.getSort());
			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create(querySort, Sort.STRING_TYPE, GetterUtil.getBoolean(query.getOrder())) };

			
			//Search lucene
			SearchContext searchContext = new SearchContext();
			searchContext.setCompanyId(serviceContext.getCompanyId());

			Hits hits = ProcessOptionLocalServiceUtil.searchLucene(params, sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					searchContext);
			List<org.opencps.api.serviceconfig.model.ProcessOption> processList = null;
			if (hits != null) {
				processList = ServiceConfigUtils.mappingToProcessOptionResults(hits.toList());
			}

			/*******/
			endTime = System.currentTimeMillis();
			_log.info("time lucenue: " + String.valueOf(endTime - startTime));
			_log.info("processList: " + processList != null ? processList.size() : 0);
			String serviceCodeOld = "";
			if (serviceConfigs != null) {
				for (ServiceConfig serviceConfig : serviceConfigs) {

					if (serviceConfig.getServiceLevel() >= 2 && ((e != null && (Validator.isNull(e.getScope())))
							|| (e != null && Validator.isNotNull(e.getScope())
									&& e.getScope().indexOf(serviceConfig.getGovAgencyCode()) >= 0))) {
						JSONObject elmData = JSONFactoryUtil.createJSONObject();

						elmData.put(ServiceConfigTerm.SERVICECONFIG_ID, serviceConfig.getServiceConfigId());

						//Hot fix
						ServiceInfo serviceInfo = null;
						ServiceInfoMapping serviceInfoMapping = null;
						if (mapServiceInfos.containsKey(serviceConfig.getServiceInfoId())) {
							serviceInfo = mapServiceInfos.get(serviceConfig.getServiceInfoId());
							if (Validator.isNull(domain) || serviceInfo.getDomainCode().equals(domain)) {
								serviceInfoMapping = ServiceInfoMappingLocalServiceUtil.fetchDVCQGServiceCode(groupId,
										serviceInfo.getServiceCode());
								elmData.put(ServiceInfoTerm.SERVICE_CODE, serviceInfo.getServiceCode());
								elmData.put(ServiceInfoTerm.SERVICE_NAME, serviceInfo.getServiceName());
								elmData.put(ServiceInfoTerm.SERVICE_CODE_DVCQG,
										serviceInfoMapping != null ? serviceInfoMapping.getServiceCodeDVCQG()
												: StringPool.BLANK);
								elmData.put(ServiceInfoTerm.SERVICE_NAME_DVCQG,
										serviceInfoMapping != null ? serviceInfoMapping.getServiceNameDVCQG()
												: StringPool.BLANK);
								elmData.put(ServiceConfigTerm.GOVAGENCY_CODE, serviceConfig.getGovAgencyCode());
								elmData.put(ServiceConfigTerm.GOVAGENCY_NAME, serviceConfig.getGovAgencyName());

								JSONArray options = JSONFactoryUtil.createJSONArray();
								for (org.opencps.api.serviceconfig.model.ProcessOption processOption : processList) {
									boolean hasPermission = false;
									if (processOption.getServiceConfigId() == serviceConfig.getServiceConfigId()) {
										hasPermission = true;
									}
									if (isAdmin) {
										hasPermission = true;
									}
									if (e != null) {
										if (Validator.isNotNull(e.getScope())
												&& Arrays.asList(e.getScope().split(StringPool.COMMA))
														.indexOf(serviceConfig.getGovAgencyCode()) < 0) {
											hasPermission = false;
										}
									}

									if (hasPermission) {
										JSONObject elmOption = JSONFactoryUtil.createJSONObject();

										elmOption.put(ProcessOptionTerm.PROCESSOPTION_ID,
												processOption.getProcessOptionId());
										elmOption.put(ProcessOptionTerm.OPTION_NAME, processOption.getOptionName());
										elmOption.put(ProcessOptionTerm.INSTRUCTION_NOTE,
												processOption.getInstructionNote());
										elmOption.put(DossierTemplateTerm.TEMPLATE_NO,
												processOption.getTemplateNo_0020());
										elmOption.put(DossierTemplateTerm.TEMPLATE_NAME,
												processOption.getTemplateName());

										options.put(elmOption);
									}

									if (options.length() > 0) {
										elmData.put(ProcessOptionTerm.OPTIONS, options);
									}

								}
								boolean groupServiceCode = query.isGroupServiceCode();
								if (groupServiceCode == true) {
									String serviceCodeNew = "";
									// Khởi tạo serviceCodeOld
									// Lần thứ 2 for sẽ vào key if() check nếu serviceCode khác nhau
									// Sẽ gán cho serviceCodeNew = serviceCodeOld để check tiếp theo
									// Danh sách phải được order by
									if (Validator.isNotNull(serviceCodeOld)) {
										serviceCodeNew = (String) elmData.get(ServiceInfoTerm.SERVICE_CODE);
										if (!serviceCodeOld.equals(serviceCodeNew)) {
											serviceCodeOld = serviceCodeNew;
											if (elmData.has(ProcessOptionTerm.OPTIONS)) {
												total++;
												data.put(elmData);
											}
										}
									} else {
										serviceCodeOld = (String) elmData.get(ServiceInfoTerm.SERVICE_CODE);
										if (elmData.has(ProcessOptionTerm.OPTIONS)) {
											total++;
											data.put(elmData);
										}
									}
								} else {
									if (elmData.has(ProcessOptionTerm.OPTIONS)) {
										total++;
										data.put(elmData);
									}
								}
							}
						}
					}
				}
			}
			results.put(ConstantUtils.TOTAL, total);
			results.put(ConstantUtils.DATA, data);

			EntityTag etag = new EntityTag(Integer.toString((groupId + domain).hashCode()));
			ResponseBuilder builder = requestCC.evaluatePreconditions(etag);
			if (OpenCPSConfigUtil.isHttpCacheEnable() && builder == null) {
				builder = Response.status(HttpURLConnection.HTTP_OK);
				CacheControl cc = new CacheControl();
				cc.setMaxAge(OpenCPSConfigUtil.getHttpCacheMaxAge());
				cc.setPrivate(true);
				builder.tag(etag);
				return builder.status(HttpURLConnection.HTTP_OK).entity(results.toJSONString()).build();
			} else {
				return Response.status(HttpURLConnection.HTTP_OK).entity(results.toJSONString()).build();
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response createDossierOngate(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierOnegateInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();
		
		DossierPermission dossierPermission = new DossierPermission();

		DossierActions actions = new DossierActionsImpl();

		_log.info("__INPUT_ONEGATE");
		_log.info(JSONFactoryUtil.looseSerialize(input));
		_log.info("__XXXXXXXXXXXXX");

		try {
			
			

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
//			if (!auth2.checkToken(request, header)) {
//				throw new UnauthenticationException();
//			}

			dossierPermission.hasCreateDossier(groupId, user.getUserId(), input.getServiceCode(),
					input.getGovAgencyCode(), input.getDossierTemplateNo());

			Dossier dossier = actions.createDossier(groupId, input.getServiceCode(), input.getGovAgencyCode(),
					input.getApplicantName(), input.getApplicantIdType(), input.getApplicantIdNo(),
					DateTimeUtils.convertDateTimeToString(input.getApplicantIdDate()), input.getAddress(),
					input.getCityCode(), input.getDistrictCode(), input.getWardCode(), input.getContactName(),
					input.getContactTelNo(), input.getContactEmail(), input.isSameAsApplicant(),
					input.getDelegateName(), input.getDelegateIdNo(), input.getDelegateTelNo(),
					input.getDelegateEmail(), input.getDelegateAddress(), input.getDelegateCityCode(),
					input.getDelegateDistrictCode(), input.getDelegateWardCode(), input.getApplicantNote(),
					StringPool.BLANK, input.getDossierNo(), input.getDossierTemplateNo(), input.getViaPostal(),
					input.getPostalServiceCode(), input.getPostalServiceName(), input.getPostalAddress(),
					input.getPostalCityCode(), input.getPostalDistrictCode(), input.getPostalWardCode(),
					input.getPostalTelNo(), DossierTerm.ORIGINALITY_MOTCUA, serviceContext);

			DossierDetailModel result = null;
			if (dossier != null ) {
				result = OneGateUtils.mappingForGetDetail(dossier);
			}
			
			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	private Log _log = LogFactoryUtil.getLog(OneGateControllerImpl.class);

	@Override
	public Response updateDossierOngate(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierOnegateInputModel input, long dossierId) {

		//long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();
		//DossierPermission dossierPermission = new DossierPermission();
		long dActionId = GetterUtil.getLong(input.getDossierActionId());


		_log.info("__INPUT_ONEGATE_UPDATE");
		_log.info(JSONFactoryUtil.looseSerialize(input));
		_log.info("__XXXXXXXXXXXXX_UPDATE");

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Dossier dossier = DossierLocalServiceUtil.updateDossierOneGate(dossierId, input.getApplicantName(),
					input.getApplicantIdType(), input.getApplicantIdNo(),
					DateTimeUtils.convertDateTimeToString(input.getApplicantIdDate()), input.getAddress(),
					input.getCityCode(), input.getDistrictCode(), input.getWardCode(), input.getContactName(),
					input.getContactTelNo(), input.getContactEmail(), input.isSameAsApplicant(),
					input.getDelegateName(), input.getDelegateIdNo(), input.getDelegateTelNo(),
					input.getDelegateEmail(), input.getDelegateAddress(), input.getDelegateCityCode(),
					input.getDelegateDistrictCode(), input.getDelegateWardCode(), input.getApplicantNote(),
					StringPool.BLANK, input.getDossierNo(), input.getViaPostal(), input.getPostalServiceCode(),
					input.getPostalServiceName(), input.getPostalAddress(), input.getPostalCityCode(),
					input.getPostalDistrictCode(), input.getPostalWardCode(), input.getPostalTelNo(), dActionId,
					input.getPaymentFee(), input.getPaymentFeeNote(), serviceContext);

			DossierDetailModel result = null;
			if (dossier != null ) {
				result = OneGateUtils.mappingForGetDetail(dossier);
			}
			
			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDossierOngate(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long dossierId) {
		//long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();
		//DossierPermission dossierPermission = new DossierPermission();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			
			DossierDetailModel result = null;
			if (dossier != null ) {
				result = OneGateUtils.mappingForGetDetail(dossier);
			}
			
			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
//			return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.looseSerialize(dossier)).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getServiceProcess(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long dossierId, String serviceCode, String govAgencyCode,
			String dossierTemplateNo, String dossierActionId) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
//		long dActionId = GetterUtil.getLong(dossierActionId);
		ServiceProcessActions actions = new ServiceProcessActionsImpl();
	
		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			JSONObject results = JSONFactoryUtil.createJSONObject();

			ServiceProcess serviceProcess = actions.getServiceProcessByCode(groupId, serviceCode, govAgencyCode,
					dossierTemplateNo);
			if (serviceProcess != null) {
				results.put(ConstantUtils.ONEGATE_PAYMENTFEEREQUEST, serviceProcess.getPaymentFee());
				ProcessAction process = ProcessActionLocalServiceUtil
						.getByServiceProcess(serviceProcess.getServiceProcessId(), String.valueOf(10000));
				if (process != null) {
					results.put(ConstantUtils.ONEGATE_PAYMENTFEETOTAL, process.getPaymentFee());
				} else {
					results.put(ConstantUtils.ONEGATE_PAYMENTFEETOTAL, 0);
				}
			} else {
				results.put(ConstantUtils.ONEGATE_PAYMENTFEEREQUEST, 0);
				results.put(ConstantUtils.ONEGATE_PAYMENTFEETOTAL, 0);
			}
//			if (dActionId > 0) {
//				DossierAction dAction = DossierActionLocalServiceUtil.fetchDossierAction(dActionId);
//				ProcessAction process = ProcessActionLocalServiceUtil.getByServiceProcess(dAction.getServiceProcessId(), dAction.getActionCode());
//				results.put("paymentFeeTotal", process.getPaymentFee());
//			} else 
//			if (serviceProcess != null) {
//				ProcessAction process = ProcessActionLocalServiceUtil
//						.getByServiceProcess(serviceProcess.getServiceProcessId(), String.valueOf(10000));
//				if (process != null) {
//					results.put("paymentFeeTotal", process.getPaymentFee());
//				} else {
//					results.put("paymentFeeTotal", 0);
//				}
//			} else {
//				results.put("paymentFeeTotal", 0);
//			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.looseSerialize(results)).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getGovAgencies(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
	
		try {
			List<ServiceConfig> lstConfigs = ServiceConfigLocalServiceUtil.getByGroupId(groupId,"",user.getUserId());
			Map<String, String> mapConfigs = new HashMap<String, String>();
			Map<String, Set<Long>> mapSis = new HashMap<String, Set<Long>>();
			
			for (ServiceConfig sc : lstConfigs) {
				mapConfigs.put(sc.getGovAgencyCode(), sc.getGovAgencyName());
				Set<Long> sInfos = new HashSet<Long>();
				if (mapSis.containsKey(sc.getGovAgencyCode())) {
					sInfos = mapSis.get(sc.getGovAgencyCode());
				}
				else {
					mapSis.put(sc.getGovAgencyCode(), sInfos);
				}
				if (!sInfos.contains(sc.getServiceInfoId())) {
					sInfos.add(sc.getServiceInfoId());
				}
			}
			Map<String, Integer> countConfigs = new HashMap<String, Integer>();
			for (ServiceConfig sc : lstConfigs) {
				if (mapSis.containsKey(sc.getGovAgencyCode())) {
					countConfigs.put(sc.getGovAgencyCode(), mapSis.get(sc.getGovAgencyCode()).size());
				}
			}
			JSONObject result = JSONFactoryUtil.createJSONObject();
			int total = 0;
			
			JSONArray results = JSONFactoryUtil.createJSONArray();
			for (String govAgencyCode : mapConfigs.keySet()) {
				JSONObject govObj = JSONFactoryUtil.createJSONObject();
				govObj.put(ServiceConfigTerm.GOVAGENCY_NAME, mapConfigs.get(govAgencyCode));
				govObj.put(ServiceConfigTerm.GOVAGENCY_CODE, govAgencyCode);
				if (countConfigs.containsKey(govAgencyCode)) {
					govObj.put(ConstantUtils.API_JSON_COUNT, countConfigs.get(govAgencyCode));
				}
				else {
					govObj.put(ConstantUtils.API_JSON_COUNT, 0);
				}
				total += govObj.getInt(ConstantUtils.API_JSON_COUNT);
				results.put(govObj);
			}
			result.put(ConstantUtils.TOTAL, total);
			result.put(ConstantUtils.DATA, results);
			
			return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.looseSerialize(result)).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

//	@Override
//	public Response getToken(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
//			User user) {
//		
//		try {
//			
//			String token = (String) request.getSession().getAttribute(CSRF_TOKEN_FOR_SESSION_NAME);
//			
//			_log.info("CHECK::TOKEN:::::" + token);
//			
//			if (Validator.isNull(token)) {
//				token = PortalUUIDUtil.generate();
//				
//				_log.info("GENERATE_TOKEN:::::" + token);
//
//				request.getSession().setAttribute(CSRF_TOKEN_FOR_SESSION_NAME, token);
//			}
//			return Response.status(HttpURLConnection.HTTP_OK).entity(token).build();
//		} catch (Exception e) {
//			_log.info(e);
//			return _processException(e);
//		}
//
//	}

}
