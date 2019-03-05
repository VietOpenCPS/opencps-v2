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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.opencps.api.controller.OneGateController;
import org.opencps.api.controller.util.OneGateUtils;
import org.opencps.api.dossier.model.DossierDetailModel;
import org.opencps.api.dossier.model.DossierOnegateInputModel;
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
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.exception.NoSuchDossierTemplateException;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessRoleLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class OneGateControllerImpl implements OneGateController {

	@Override
	public Response getServiceconfigs(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String domain, String public_, Request requestCC) {
		
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		
		BackendAuth auth = new BackendAuthImpl();

		// TODO need implement user in GovAgency

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith("Administrator")) {
					isAdmin = true;
					break;
				}
			}
			
			List<ServiceConfig> serviceConfigs = ServiceConfigLocalServiceUtil.getByGroupId(groupId);
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
			
			JSONObject results = JSONFactoryUtil.createJSONObject();
			Map<Long, List<ProcessOption>> mapProcessOptions = new HashMap<>();
			List<ProcessOption> lstOptions = ProcessOptionLocalServiceUtil.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			for (ProcessOption po : lstOptions) {
				if (mapProcessOptions.get(po.getServiceConfigId()) == null) {
					List<ProcessOption> lstPos = new ArrayList<>();
					mapProcessOptions.put(po.getServiceConfigId(), lstPos);
					lstPos.add(po);
				}
				else {
					List<ProcessOption> lstPos = mapProcessOptions.get(po.getServiceConfigId());
					lstPos.add(po);
				}
			}
			JSONArray data = JSONFactoryUtil.createJSONArray();
			int total = 0;
			long[] roleIds = UserLocalServiceUtil.getRolePrimaryKeys(user.getUserId());
			for (ServiceConfig serviceConfig : serviceConfigs) {
				if (serviceConfig.getServiceLevel() >= 2) {
					JSONObject elmData = JSONFactoryUtil.createJSONObject();
	
					elmData.put("serviceConfigId", serviceConfig.getServiceConfigId());
	
					//Hot fix
					ServiceInfo serviceInfo = null;
					if (mapServiceInfos.containsKey(serviceConfig.getServiceInfoId())) {
						serviceInfo = mapServiceInfos.get(serviceConfig.getServiceInfoId());
						if (Validator.isNull(domain) || serviceInfo.getDomainCode().equals(domain)) {
		//					try {
		//						serviceInfo = ServiceInfoLocalServiceUtil.getServiceInfo(serviceConfig.getServiceInfoId());
		//					} catch (Exception e1) {
		//						_log.debug(e1);
		//						break;
		//					}
							elmData.put("serviceCode", serviceInfo.getServiceCode());
							elmData.put("serviceName", serviceInfo.getServiceName());
							elmData.put("govAgencyCode", serviceConfig.getGovAgencyCode());
							elmData.put("govAgencyName", serviceConfig.getGovAgencyName());
			
	//						List<ProcessOption> processOptions = ProcessOptionLocalServiceUtil
	//								.getByServiceProcessId(serviceConfig.getServiceConfigId());
							List<ProcessOption> processOptions = mapProcessOptions.get(serviceConfig.getServiceConfigId()) != null ? mapProcessOptions.get(serviceConfig.getServiceConfigId())
									: new ArrayList<>();
			
							JSONArray options = JSONFactoryUtil.createJSONArray();
							for (ProcessOption processOption : processOptions) {
			//					_log.info("processOptionId"+ processOption.getDossierTemplateId());
								long serviceProcessId = processOption.getServiceProcessId();
								List<ServiceProcessRole> lstRoles = ServiceProcessRoleLocalServiceUtil.findByS_P_ID(serviceProcessId);
								
								boolean hasPermission = false;
		//						_log.info("List role: " + lstRoles);
								if (lstRoles.size() > 0) {
		//							_log.info("Role of users : " + user);
									for (ServiceProcessRole spr : lstRoles) {
										for (int i = 0; i < roleIds.length; i++) {
											if (roleIds[i] == spr.getRoleId()) {
												hasPermission = true;
												break;										
											}
										}
										if (hasPermission) break;
									}
								}
								if (isAdmin) {
									hasPermission = true;
								}
								if (hasPermission) {
									JSONObject elmOption = JSONFactoryUtil.createJSONObject();
									
									elmOption.put("processOptionId", processOption.getProcessOptionId());
									elmOption.put("optionName", processOption.getOptionName());
									elmOption.put("instructionNote", processOption.getInstructionNote());
									
									try {
										DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.getDossierTemplate(processOption.getDossierTemplateId());
										if (dossierTemplate != null) {
											elmOption.put("templateNo", dossierTemplate.getTemplateNo());
											elmOption.put("templateName", dossierTemplate.getTemplateName());						
										}
									}
									catch (NoSuchDossierTemplateException e) {
										_log.error(e);
									}
									options.put(elmOption);							
								}
								
								if (options.length() > 0) {
									elmData.put("options", options);							
								}
			
							}
							
							if (elmData.has("options")) {
								total++;
								data.put(elmData);						
							}
						}
					}
				}
			}
			
			results.put("total", total);
			results.put("data", data);
			
//			_log.info(results.toJSONString());
			EntityTag etag = new EntityTag(Integer.toString(Long.valueOf(groupId).hashCode()));
		    ResponseBuilder builder = requestCC.evaluatePreconditions(etag);			
		    if (OpenCPSConfigUtil.isHttpCacheEnable() && builder == null) {
				builder = Response.status(200);
				CacheControl cc = new CacheControl();
				cc.setMaxAge(OpenCPSConfigUtil.getHttpCacheMaxAge());
				cc.setPrivate(true);	
				builder.tag(etag);
				return builder.status(200).entity(results.toJSONString()).build();
			}
			else {
				return Response.status(200).entity(results.toJSONString()).build();
			}			

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response createDossierOngate(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierOnegateInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();
		
//		backend.auth.api.BackendAuth auth2 = new backend.auth.api.BackendAuthImpl();
		
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
			
			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	private Log _log = LogFactoryUtil.getLog(OneGateControllerImpl.class);

	@Override
	public Response updateDossierOngate(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierOnegateInputModel input, long dossierId) {

		//long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
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
			
			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDossierOngate(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long dossierId) {
		//long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
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
			
			return Response.status(200).entity(result).build();
//			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(dossier)).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getServiceProcess(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long dossierId, String serviceCode, String govAgencyCode,
			String dossierTemplateNo, String dossierActionId) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
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
				results.put("paymentFeeRequest", serviceProcess.getPaymentFee());
				ProcessAction process = ProcessActionLocalServiceUtil
						.getByServiceProcess(serviceProcess.getServiceProcessId(), String.valueOf(10000));
				if (process != null) {
					results.put("paymentFeeTotal", process.getPaymentFee());
				} else {
					results.put("paymentFeeTotal", 0);
				}
			} else {
				results.put("paymentFeeRequest", 0);
				results.put("paymentFeeTotal", 0);
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

			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(results)).build();

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
//			return Response.status(200).entity(token).build();
//		} catch (Exception e) {
//			_log.info(e);
//			return _processException(e);
//		}
//
//	}

}
