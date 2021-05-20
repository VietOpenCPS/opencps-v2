package org.opencps.api.controller.impl;

import com.liferay.counter.kernel.model.Counter;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.CertNumberManagement;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.dossiermgt.constants.ConstantsUtils;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;

import backend.auth.api.BackendAuth;
import backend.auth.api.BackendAuthImpl;
import backend.auth.api.exception.BusinessExceptionImpl;

public class CertNumberManagementImpl implements CertNumberManagement{

	Log _log = LogFactoryUtil.getLog(CertNumberManagementImpl.class);

	@Override
	public Response getCertNumbers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
//		BackendAuth auth = new BackendAuthImpl();

		try {
//			if (!auth.isAdmin(serviceContext, "admin")) {
//				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
//						.entity("User not permission process action!!!").build();
//			}
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
					isAdmin = true;
					break;
				}
			}
			
			if (!isAdmin) {
				throw new UnauthenticationException();
			}

			List<Counter> counters = CounterLocalServiceUtil.getCounters(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			JSONObject jsObj = JSONFactoryUtil.createJSONObject();

			JSONArray jsArr = JSONFactoryUtil.createJSONArray();

			for (Counter cnt : counters) {
				JSONObject elm = JSONFactoryUtil.createJSONObject();

				String valueCheck = StringPool.AT + groupId;
				if (cnt.getName().contains(valueCheck)) {

					String[] splitPattern = StringUtil.split(cnt.getName(), StringPool.AT);

					elm.put(ConstantUtils.CERT_ID, cnt.getName());
					elm.put(ConstantUtils.CERT_PATTERN, splitPattern[1]);
					elm.put(ConstantUtils.CERT_GROUPID, splitPattern[2]);
					elm.put(ConstantUtils.CERT_INITNUMBER, cnt.getCurrentId());

					jsArr.put(elm);
				}
			}
			
			jsObj.put(ConstantUtils.TOTAL, jsArr.length());

			jsObj.put(ConstantUtils.DATA, jsArr);

			return Response.status(org.apache.commons.httpclient.util.HttpURLConnection.HTTP_OK).entity(jsObj.toString()).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDetailCertNumbers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String certid) {

		// long groupId =
		// GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

//		BackendAuth auth = new BackendAuthImpl();

		try {
//			if (!auth.isAdmin(serviceContext, "admin")) {
//				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
//						.entity("User not permission process action!!!").build();
//			}
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
					isAdmin = true;
					break;
				}
			}
			
			if (!isAdmin) {
				throw new UnauthenticationException();
			}
			Counter counter = CounterLocalServiceUtil.getCounter(certid);
			String[] splitPattern = StringUtil.split(counter.getName(), StringPool.AT);

			JSONObject elm = JSONFactoryUtil.createJSONObject();

			elm.put(ConstantUtils.CERT_ID, counter.getName());
			elm.put(ConstantUtils.CERT_PATTERN, splitPattern[1]);
			elm.put(ConstantUtils.CERT_YEAR, splitPattern[2]);
			elm.put(ConstantUtils.CERT_INITNUMBER, counter.getCurrentId());

			return Response.status(HttpURLConnection.HTTP_OK).entity(elm.toString()).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addCertNumbers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String pattern, int initNumber) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		
		JSONObject jsObj = JSONFactoryUtil.createJSONObject();
		
//		BackendAuth auth = new BackendAuthImpl();

		try {
//			if (!auth.isAdmin(serviceContext, "admin")) {
//				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
//						.entity("User not permission process action!!!").build();
//			}

			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
					isAdmin = true;
					break;
				}
			}
			
			if (!isAdmin) {
				throw new UnauthenticationException();
			}
			
			if (Validator.isNotNull(pattern)) {
				String[] patternArr = StringUtil.split(pattern);
				_log.info("pattern: "+pattern);
				_log.info("patternArr: "+patternArr);
				if (patternArr != null && patternArr.length > 0) {
					for (String strPattern : patternArr) {
						_log.info("strPattern: "+strPattern);
						String certId = ConstantsUtils.PRE_FIX_CERT + strPattern + StringPool.AT + groupId;
						_log.info("strPattern: "+strPattern);
						Counter counter = null;
						try {
							counter = CounterLocalServiceUtil.getCounter(certId);
						} catch (Exception e) {
							_log.error(e);
						}
						if (counter != null) {
							String contentError = String.format(MessageUtil.getMessage(ConstantUtils.CERT_MESSAGE_DUPLICATE), certId);
							return Response.status(HttpURLConnection.HTTP_CONFLICT).entity(contentError).build(); 
						}
						_log.info("counter: "+counter);
						Counter counterInit = CounterLocalServiceUtil.createCounter(certId);
						counterInit.setCurrentId(initNumber);

						CounterLocalServiceUtil.updateCounter(counterInit);
					}
					jsObj.put(ConstantUtils.API_JSON_STATUS, MessageUtil.getMessage(ConstantUtils.API_JSON_STATUS_DONE));
				}
			} else {
				if (initNumber == 0) {
					List<ServiceProcess> processList = ServiceProcessLocalServiceUtil.getByG_ID(groupId);
					if (processList != null && processList.size() > 0) {
						for (ServiceProcess serviceProcess : processList) {
							String servicePattern = serviceProcess.getProcessNo();
							if (Validator.isNotNull(servicePattern)) {
								String certId = ConstantsUtils.PRE_FIX_CERT + servicePattern + StringPool.AT + groupId;
								Counter counter = null;
								try {
									counter = CounterLocalServiceUtil.getCounter(certId);
								} catch (Exception e) {
									_log.error(e);
								}
								if (counter != null) continue;
								Counter counterInit = CounterLocalServiceUtil.createCounter(certId);
								counterInit.setCurrentId(1);

								CounterLocalServiceUtil.updateCounter(counterInit);
							}
						}
						jsObj.put(ConstantUtils.API_JSON_STATUS, MessageUtil.getMessage(ConstantUtils.API_JSON_STATUS_DONE));					}
				}
			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(jsObj.toString()).build();
		} catch (Exception e) {
			_log.error(e);
			jsObj.put(ConstantUtils.API_JSON_STATUS, MessageUtil.getMessage(ConstantUtils.API_JSON_STATUS_ERROR));

			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(jsObj.toString()).build();
		}
	}

	@Override
	public Response updateSertNumbers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String certid, String pattern, int initNumber) {

//		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		JSONObject jsObj = JSONFactoryUtil.createJSONObject();

//		BackendAuth auth = new BackendAuthImpl();

		try {
//			if (!auth.isAdmin(serviceContext, "admin")) {
//				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
//						.entity("User not permission process action!!!").build();
//			}
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
					isAdmin = true;
					break;
				}
			}
			
			if (!isAdmin) {
				throw new UnauthenticationException();
			}
//			String certId = ConstantsUtils.PRE_FIX_CERT + pattern + StringPool.AT + groupId;

			Counter counter = CounterLocalServiceUtil.getCounter(certid);

			counter.setCurrentId(initNumber);
			
			CounterLocalServiceUtil.updateCounter(counter);

			jsObj.put(ConstantUtils.API_JSON_STATUS, MessageUtil.getMessage(ConstantUtils.API_JSON_STATUS_DONE));
			return Response.status(HttpURLConnection.HTTP_OK).entity(jsObj.toString()).build();
		} catch (Exception e) {
			_log.error(e);
			jsObj.put(ConstantUtils.API_JSON_STATUS, MessageUtil.getMessage(ConstantUtils.API_JSON_STATUS_ERROR));

			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(jsObj.toString()).build();
		}
	}

	@Override
	public Response generatorCertNumbers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String pattern, long dossierid) {

		// long groupId =
		// GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		
		String certNumber;

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAdmin(serviceContext, ConstantUtils.ROLE_ADMIN_LOWER)) {
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
						.entity(MessageUtil.getMessage(ConstantUtils.API_USER_NOTHAVEPERMISSION)).build();
			}

			long _counterNumber = 0;

			Calendar cal = Calendar.getInstance();

			cal.setTime(new Date());

			//int curYear = cal.get(Calendar.YEAR);
			
			DateFormat df = new SimpleDateFormat(ConstantUtils.DATEFORMAT_YYYY);
			DateFormat sdf = new SimpleDateFormat(ConstantUtils.DATEFORMAT_YY);
			
			String curYear = df.format(cal.getTime());
			String shortCurYear = sdf.format(cal.getTime());

			String certConfigId = ConstantsUtils.PRE_FIX_CERT + pattern + StringPool.AT + curYear;
			
			_log.info("___certConfigId" + certConfigId);

			String certConfigCurrId = ConstantsUtils.PRE_FIX_CERT_CURR + pattern + StringPool.AT + curYear;
			
			_log.info("___certConfigCurrId" + certConfigCurrId);

			Counter counterConfig = CounterLocalServiceUtil.fetchCounter(certConfigId);

			String elmCertId = ConstantsUtils.PRE_FIX_CERT_ELM + pattern + StringPool.AT + curYear + StringPool.AT + dossierid;

			//Counter counter = CounterLocalServiceUtil.fetchCounter(certId);

			if (Validator.isNotNull(counterConfig)) {
				// create counter config

				Counter currCounter = CounterLocalServiceUtil.fetchCounter(certConfigCurrId);

				if (Validator.isNull(currCounter)) {
					_log.info("COUTER_CURR_CONFIG_IS_NULL");

					currCounter = CounterLocalServiceUtil.createCounter(certConfigCurrId);

					currCounter.setCurrentId(counterConfig.getCurrentId());

					_counterNumber = counterConfig.getCurrentId() ;

					CounterLocalServiceUtil.updateCounter(currCounter);
					
					//Create elmCounter
					Counter elmCounter = CounterLocalServiceUtil.createCounter(elmCertId);
					
					elmCounter.setCurrentId(_counterNumber);
					
					CounterLocalServiceUtil.updateCounter(elmCounter);
					
				} else {
					_log.info("COUTER_CURR_CONFIG_IS_NOT_NULL");

					//check counter for element
					Counter elmCounter = CounterLocalServiceUtil.fetchCounter(elmCertId);
					
					if (Validator.isNotNull(elmCounter)) {
						_log.info("ELM_COUTER_CONFIG_IS_NOT_NULL");

						_counterNumber = elmCounter.getCurrentId();
					} else {
						//create elm Counter 
						_log.info("ELM_COUTER_CONFIG_IS_NULL");
						elmCounter = CounterLocalServiceUtil.createCounter(elmCertId);
						
						//increment CurrentCounter 
						
						currCounter.setCurrentId(currCounter.getCurrentId()+1);
						CounterLocalServiceUtil.updateCounter(currCounter);
						
						
						_counterNumber = currCounter.getCurrentId();
						
						elmCounter.setCurrentId(_counterNumber);
						
						CounterLocalServiceUtil.updateCounter(elmCounter);
					}
					
				}


				certNumber = String.format(ConstantUtils.CERT_NUMBER_FORMAT, _counterNumber) + StringPool.FORWARD_SLASH + pattern + StringPool.FORWARD_SLASH + shortCurYear ; 
				
			} else {
				throw new Exception("Don't have counter config");
			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(certNumber).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response removeCertNumbers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String certId) {
		// long groupId =
		// GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		JSONObject jsObj = JSONFactoryUtil.createJSONObject();
//		_log.info("certId: "+certId);

//		BackendAuth auth = new BackendAuthImpl();

		try {
//			if (!auth.isAdmin(serviceContext, "admin")) {
//				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
//						.entity("User not permission process action!!!").build();
//			}
//			String nameCounter = ConstantsUtils.PRE_FIX_CERT + pattern + StringPool.AT + year;
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
					isAdmin = true;
					break;
				}
			}
			
			if (!isAdmin) {
				throw new UnauthenticationException();
			}
			CounterLocalServiceUtil.deleteCounter(certId);

			jsObj.put(ConstantUtils.API_JSON_STATUS, MessageUtil.getMessage(ConstantUtils.API_JSON_STATUS_DONE));
			return Response.status(HttpURLConnection.HTTP_OK).entity(jsObj.toString()).build();
		} catch (Exception e) {
			_log.error(e);
			jsObj.put(ConstantUtils.API_JSON_STATUS, MessageUtil.getMessage(ConstantUtils.API_JSON_STATUS_ERROR));

			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(jsObj.toString()).build();
		}
	}

	@Override
	public Response removeAllCertNumbers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		JSONObject jsObj = JSONFactoryUtil.createJSONObject();

//		BackendAuth auth = new BackendAuthImpl();

		try {
//			if (!auth.isAdmin(serviceContext, "admin")) {
//				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
//						.entity("User not permission process action!!!").build();
//			}
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
					isAdmin = true;
					break;
				}
			}
			
			if (!isAdmin) {
				throw new UnauthenticationException();
			}
			List<Counter> counters = CounterLocalServiceUtil.getCounters(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			String counterCheck = StringPool.AT + groupId;
			if (counters != null && counters.size() > 0) {
				for (Counter counter : counters) {
					String certName = counter.getName();
					if (Validator.isNotNull(certName) && certName.contains(counterCheck)) {
						CounterLocalServiceUtil.deleteCounter(certName);
					}
				}
				jsObj.put(ConstantUtils.API_JSON_STATUS, MessageUtil.getMessage(ConstantUtils.API_JSON_STATUS_DONE));
			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(jsObj.toString()).build();
		} catch (Exception e) {
			_log.error(e);
			jsObj.put(ConstantUtils.API_JSON_STATUS, MessageUtil.getMessage(ConstantUtils.API_JSON_STATUS_ERROR));

			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(jsObj.toString()).build();
		}
	}
	
	@Override
	public Response getCounter(HttpServletRequest request, HttpHeaders header,
			Company company, Locale locale, User user,
			ServiceContext serviceContext, String registerBookCode,
			String govAgencyCode) {


		try {
//			if (!auth.isAdmin(serviceContext, ConstantUtils.ROLE_ADMIN_LOWER)) {
//				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
//						.entity(MessageUtil.getMessage(ConstantUtils.API_USER_NOTHAVEPERMISSION)).build();
//			}

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			String certConfigName = ConstantsUtils.PRE_FIX_COUNTER + registerBookCode + govAgencyCode + StringPool.AT + groupId;
			
			_log.info("___certConfigId" + certConfigName);

			Counter counterConfig = CounterLocalServiceUtil.fetchCounter(certConfigName);

			if (Validator.isNotNull(counterConfig)) {
				return Response.status(HttpURLConnection.HTTP_OK).entity(String.valueOf(counterConfig.getCurrentId())).build();
			} else {
				return Response.status(HttpURLConnection.HTTP_OK).entity(String.valueOf(0)).build();
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}
	
	@Override
	public Response updateCounter(HttpServletRequest request, HttpHeaders header,
			Company company, Locale locale, User user,
			ServiceContext serviceContext,
			String registerBookCode, String govAgencyCode,
			 int countNum) {

		try {
//			if (!auth.isAdmin(serviceContext, ConstantUtils.ROLE_ADMIN_LOWER)) {
//				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
//						.entity(MessageUtil.getMessage(ConstantUtils.API_USER_NOTHAVEPERMISSION)).build();
//			}

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			String certConfigName = ConstantsUtils.PRE_FIX_COUNTER + registerBookCode + govAgencyCode + StringPool.AT + groupId;
			
			_log.info("___certConfigId" + certConfigName);
			Counter counterConfig = CounterLocalServiceUtil.fetchCounter(certConfigName);

			if (Validator.isNull(counterConfig)) {
				counterConfig = CounterLocalServiceUtil.createCounter(certConfigName);
			}
			counterConfig.setCurrentId(countNum);
			CounterLocalServiceUtil.updateCounter(counterConfig);
			return Response.status(HttpURLConnection.HTTP_OK).entity(String.valueOf(counterConfig.getCurrentId())).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
