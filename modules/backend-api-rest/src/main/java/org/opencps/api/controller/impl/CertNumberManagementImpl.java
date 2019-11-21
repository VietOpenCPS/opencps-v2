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
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.CertNumberManagement;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
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

		try {
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ReadFilePropertiesUtils.get(ConstantUtils.ROLE_ADMIN))) {
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
					elm.put(ConstantUtils.PATTERN, splitPattern[1]);
					elm.put(Field.GROUP_ID, splitPattern[2]);
					elm.put(ConstantUtils.INIT_NUBER, cnt.getCurrentId());

					jsArr.put(elm);
				}
			}
			
			jsObj.put(ConstantUtils.TOTAL, jsArr.length());

			jsObj.put(ConstantUtils.DATA, jsArr);

			return Response.status(200).entity(jsObj.toString()).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDetailCertNumbers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String certid) {

		try {
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ReadFilePropertiesUtils.get(ConstantUtils.ROLE_ADMIN))) {
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
			elm.put(ConstantUtils.PATTERN, splitPattern[1]);
			elm.put(ConstantUtils.YEAR, splitPattern[2]);
			elm.put(ConstantUtils.INIT_NUBER, counter.getCurrentId());

			return Response.status(200).entity(elm.toString()).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addCertNumbers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String pattern, int initNumber) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		
		JSONObject jsObj = JSONFactoryUtil.createJSONObject();
		
		try {

			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ReadFilePropertiesUtils.get(ConstantUtils.ROLE_ADMIN))) {
					isAdmin = true;
					break;
				}
			}
			
			if (!isAdmin) {
				throw new UnauthenticationException();
			}
			
			if (Validator.isNotNull(pattern)) {
				String[] patternArr = StringUtil.split(pattern);
				if (patternArr != null && patternArr.length > 0) {
					for (String strPattern : patternArr) {
						String certId = ReadFilePropertiesUtils.get(ConstantUtils.STATUS_DONE) + strPattern + StringPool.AT + groupId;
						Counter counter = null;
						try {
							counter = CounterLocalServiceUtil.getCounter(certId);
						} catch (Exception e) {
							_log.error(e);
						}
						if (counter != null) {
							String contentError = certId + ReadFilePropertiesUtils.get(ConstantUtils.MSG_ERROR);
							return Response.status(HttpURLConnection.HTTP_CONFLICT).entity(contentError).build(); 
						}
						Counter counterInit = CounterLocalServiceUtil.createCounter(certId);
						counterInit.setCurrentId(initNumber);

						CounterLocalServiceUtil.updateCounter(counterInit);
					}
					jsObj.put(DossierTerm.STATUS, ReadFilePropertiesUtils.get(ConstantUtils.STATUS_DONE));
				}
			} else {
				if (initNumber == 0) {
					List<ServiceProcess> processList = ServiceProcessLocalServiceUtil.getByG_ID(groupId);
					if (processList != null && processList.size() > 0) {
						for (ServiceProcess serviceProcess : processList) {
							String servicePattern = serviceProcess.getProcessNo();
							if (Validator.isNotNull(servicePattern)) {
								String certId = ReadFilePropertiesUtils.get(ConstantUtils.PRE_FIX_CERT) + servicePattern + StringPool.AT + groupId;
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
						jsObj.put(DossierTerm.STATUS, ReadFilePropertiesUtils.get(ConstantUtils.STATUS_DONE));
					}
				}
			}

			return Response.status(200).entity(jsObj.toString()).build();
		} catch (Exception e) {
			_log.error(e);
			jsObj.put(DossierTerm.STATUS, ReadFilePropertiesUtils.get(ConstantUtils.MSG_ERROR));

			return Response.status(500).entity(jsObj.toString()).build();
		}
	}

	@Override
	public Response updateSertNumbers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String certid, String pattern, int initNumber) {

		JSONObject jsObj = JSONFactoryUtil.createJSONObject();

		try {
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ReadFilePropertiesUtils.get(ConstantUtils.ROLE_ADMIN))) {
					isAdmin = true;
					break;
				}
			}
			
			if (!isAdmin) {
				throw new UnauthenticationException();
			}

			Counter counter = CounterLocalServiceUtil.getCounter(certid);

			counter.setCurrentId(initNumber);
			
			CounterLocalServiceUtil.updateCounter(counter);

			jsObj.put(DossierTerm.STATUS, ReadFilePropertiesUtils.get(ConstantUtils.STATUS_DONE));
			return Response.status(200).entity(jsObj.toString()).build();
		} catch (Exception e) {
			_log.error(e);
			jsObj.put(DossierTerm.STATUS, ReadFilePropertiesUtils.get(ConstantUtils.MSG_ERROR));

			return Response.status(500).entity(jsObj.toString()).build();
		}
	}

	@Override
	public Response generatorCertNumbers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String pattern, long dossierid) {

		String certNumber;
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAdmin(serviceContext, ReadFilePropertiesUtils.get(ConstantUtils.USER_ADMIN))) {
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED)
						.entity(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_NOT_PERMISSION)).build();
			}

			long _counterNumber = 0;

			Calendar cal = Calendar.getInstance();

			cal.setTime(new Date());

			//int curYear = cal.get(Calendar.YEAR);
			
			DateFormat df = new SimpleDateFormat(ReadFilePropertiesUtils.get(ConstantUtils.PATTERN_YEAR_FULL));
			DateFormat sdf = new SimpleDateFormat(ReadFilePropertiesUtils.get(ConstantUtils.PATTERN_YEAR_HALF));
			
			String curYear = df.format(cal.getTime());
			String shortCurYear = sdf.format(cal.getTime());

			String certConfigId = ReadFilePropertiesUtils.get(ConstantUtils.PRE_FIX_CERT) + pattern + StringPool.AT + curYear;
			String certConfigCurrId = ReadFilePropertiesUtils.get(ConstantUtils.PRE_FIX_CERT_CURR) + pattern + StringPool.AT + curYear;
			Counter counterConfig = CounterLocalServiceUtil.fetchCounter(certConfigId);

			String elmCertId = ReadFilePropertiesUtils.get(ConstantUtils.PRE_FIX_CERT_ELM) + pattern + StringPool.AT + curYear + StringPool.AT + dossierid;

			//Counter counter = CounterLocalServiceUtil.fetchCounter(certId);

			if (Validator.isNotNull(counterConfig)) {
				// create counter config

				Counter currCounter = CounterLocalServiceUtil.fetchCounter(certConfigCurrId);

				if (Validator.isNull(currCounter)) {

					currCounter = CounterLocalServiceUtil.createCounter(certConfigCurrId);

					currCounter.setCurrentId(counterConfig.getCurrentId());

					_counterNumber = counterConfig.getCurrentId() ;

					CounterLocalServiceUtil.updateCounter(currCounter);
					
					//Create elmCounter
					Counter elmCounter = CounterLocalServiceUtil.createCounter(elmCertId);
					
					elmCounter.setCurrentId(_counterNumber);
					
					CounterLocalServiceUtil.updateCounter(elmCounter);
					
				} else {

					//check counter for element
					Counter elmCounter = CounterLocalServiceUtil.fetchCounter(elmCertId);
					
					if (Validator.isNotNull(elmCounter)) {
						_counterNumber = elmCounter.getCurrentId();
					} else {
						//create elm Counter 
						elmCounter = CounterLocalServiceUtil.createCounter(elmCertId);
						
						//increment CurrentCounter 
						
						currCounter.setCurrentId(currCounter.getCurrentId()+1);
						CounterLocalServiceUtil.updateCounter(currCounter);
						
						
						_counterNumber = currCounter.getCurrentId();
						
						elmCounter.setCurrentId(_counterNumber);
						
						CounterLocalServiceUtil.updateCounter(elmCounter);
					}
					
				}


				certNumber = StringPool.FORWARD_SLASH + pattern + StringPool.FORWARD_SLASH + shortCurYear ; 
				
			} else {
				throw new Exception(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_NOT_CONFIG));
			}

			return Response.status(200).entity(certNumber).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response removeCertNumbers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String certId) {
		JSONObject jsObj = JSONFactoryUtil.createJSONObject();

		try {
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ReadFilePropertiesUtils.get(ConstantUtils.ROLE_ADMIN))) {
					isAdmin = true;
					break;
				}
			}
			
			if (!isAdmin) {
				throw new UnauthenticationException();
			}
			CounterLocalServiceUtil.deleteCounter(certId);

			jsObj.put(DossierTerm.STATUS, ReadFilePropertiesUtils.get(ConstantUtils.STATUS_DONE));
			return Response.status(200).entity(jsObj.toString()).build();
		} catch (Exception e) {
			_log.error(e);
			jsObj.put(DossierTerm.STATUS, ReadFilePropertiesUtils.get(ConstantUtils.MSG_ERROR));

			return Response.status(500).entity(jsObj.toString()).build();
		}
	}

	@Override
	public Response removeAllCertNumbers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		JSONObject jsObj = JSONFactoryUtil.createJSONObject();


		try {
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ReadFilePropertiesUtils.get(ConstantUtils.ROLE_ADMIN))) {
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
				jsObj.put(DossierTerm.STATUS, ReadFilePropertiesUtils.get(ConstantUtils.STATUS_DONE));
			}

			return Response.status(200).entity(jsObj.toString()).build();
		} catch (Exception e) {
			_log.error(e);
			jsObj.put(DossierTerm.STATUS, ReadFilePropertiesUtils.get(ConstantUtils.MSG_ERROR));

			return Response.status(500).entity(jsObj.toString()).build();
		}
	}

}
