package org.opencps.api.controller.impl;

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

import com.liferay.counter.kernel.model.Counter;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

public class CertNumberManagementImpl implements CertNumberManagement{

	public static final String PRE_FIX_CERT = "TCDB_CERT@";
	public static final String PRE_FIX_CERT_CURR = "TCDB_CERT_CURR@";
	public static final String PRE_FIX_CERT_ELM = "TCDB_CERT_ELM@";

	Log _log = LogFactoryUtil.getLog(CertNumberManagementImpl.class);

	@Override
	public Response getCertNumbers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext) {

		// long groupId =
		// GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {
			List<Counter> counters = CounterLocalServiceUtil.getCounters(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			JSONObject jsObj = JSONFactoryUtil.createJSONObject();

			JSONArray jsArr = JSONFactoryUtil.createJSONArray();


			for (Counter cnt : counters) {
				JSONObject elm = JSONFactoryUtil.createJSONObject();

				if (cnt.getName().contains(PRE_FIX_CERT)) {

					String[] splitPattern = StringUtil.split(cnt.getName(), StringPool.AT);

					elm.put("certId", cnt.getName());
					elm.put("pattern", splitPattern[1]);
					elm.put("year", splitPattern[2]);
					elm.put("initNumber", cnt.getCurrentId());

					jsArr.put(elm);
				}
			}
			
			jsObj.put("total", jsArr.length());

			jsObj.put("data", jsArr);

			return Response.status(200).entity(jsObj.toString()).build();
		} catch (Exception e) {
			return Response.status(500).entity("error").build();
		}
	}

	@Override
	public Response getDetailCertNumbers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String certid) {

		// long groupId =
		// GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {
			Counter counter = CounterLocalServiceUtil.getCounter(certid);
			String[] splitPattern = StringUtil.split(counter.getName(), StringPool.AT);

			JSONObject elm = JSONFactoryUtil.createJSONObject();

			elm.put("certId", counter.getName());
			elm.put("pattern", splitPattern[1]);
			elm.put("year", splitPattern[2]);
			elm.put("initNumber", counter.getCurrentId());

			return Response.status(200).entity(elm.toString()).build();

		} catch (Exception e) {
			return Response.status(500).entity("error").build();
		}
	}

	@Override
	public Response addCertNumbers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String pattern, int year, int initNumber) {

		// long groupId =
		// GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		// CounterLocalServiceUtil.increment();
		
		JSONObject jsObj = JSONFactoryUtil.createJSONObject();
		
		try {

			String certId = PRE_FIX_CERT + pattern + StringPool.AT + year;
			
			Counter counterInit = CounterLocalServiceUtil.createCounter(certId);
			counterInit.setCurrentId(initNumber);

			CounterLocalServiceUtil.updateCounter(counterInit);
			
			jsObj.put("status", "done");

			return Response.status(200).entity(jsObj.toString()).build();
		} catch (Exception e) {
			jsObj.put("status", "error");

			return Response.status(500).entity(jsObj.toString()).build();
		}
	}

	@Override
	public Response updateSertNumbers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long certid, String pattern, int year, int initNumber) {

		// long groupId =
		// GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		JSONObject jsObj = JSONFactoryUtil.createJSONObject();

		try {
			String certId = PRE_FIX_CERT + pattern + StringPool.AT + year;

			Counter counter = CounterLocalServiceUtil.getCounter(certId);

			counter.setCurrentId(initNumber);
			
			CounterLocalServiceUtil.updateCounter(counter);

			jsObj.put("status", "done");
			return Response.status(200).entity(jsObj.toString()).build();
		} catch (Exception e) {
			jsObj.put("status", "error");

			return Response.status(500).entity(jsObj.toString()).build();
		}
	}

	@Override
	public Response generatorCertNumbers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String pattern, long dossierid) {

		// long groupId =
		// GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		
		String certNumber;

		try {

			long _counterNumber = 0;

			Calendar cal = Calendar.getInstance();

			cal.setTime(new Date());

			//int curYear = cal.get(Calendar.YEAR);
			
			DateFormat df = new SimpleDateFormat("yyyy");
			DateFormat sdf = new SimpleDateFormat("yy");
			
			String curYear = df.format(cal.getTime());
			String shortCurYear = sdf.format(cal.getTime());

			String certConfigId = PRE_FIX_CERT + pattern + StringPool.AT + curYear;
			
			_log.info("___certConfigId" + certConfigId);

			String certConfigCurrId = PRE_FIX_CERT_CURR + pattern + StringPool.AT + curYear;
			
			_log.info("___certConfigCurrId" + certConfigCurrId);

			Counter counterConfig = CounterLocalServiceUtil.fetchCounter(certConfigId);

			String elmCertId = PRE_FIX_CERT_ELM + pattern + StringPool.AT + curYear + StringPool.AT + dossierid;

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


				certNumber = String.format("%05d", _counterNumber) + StringPool.FORWARD_SLASH + pattern + StringPool.FORWARD_SLASH + shortCurYear ; 
				
			} else {
				throw new Exception("Don't have counter config");
			}

			return Response.status(200).entity(certNumber).build();

		} catch (Exception e) {
			
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

}
