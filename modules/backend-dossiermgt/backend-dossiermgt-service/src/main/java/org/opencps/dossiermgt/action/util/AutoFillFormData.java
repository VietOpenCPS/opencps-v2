package org.opencps.dossiermgt.action.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationLocalServiceUtil;
import org.opencps.dossiermgt.service.comparator.DossierActionComparator;
import org.opencps.dossiermgt.service.comparator.DossierFileComparator;
import org.opencps.usermgt.action.ApplicantActions;
import org.opencps.usermgt.action.impl.ApplicantActionsImpl;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

public class AutoFillFormData {

	@SuppressWarnings("deprecation")
	public static String sampleDataBinding(String sampleData, long dossierId, ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {

			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);

			result = JSONFactoryUtil.createJSONObject(sampleData);

			String _subjectName = StringPool.BLANK;
			String _subjectId = StringPool.BLANK;
			String _address = StringPool.BLANK;
			String _cityCode = StringPool.BLANK;
			String _cityName = StringPool.BLANK;
			String _districtCode = StringPool.BLANK;
			String _districtName = StringPool.BLANK;
			String _wardCode = StringPool.BLANK;
			String _wardName = StringPool.BLANK;
			String _contactName = StringPool.BLANK;
			String _contactTelNo = StringPool.BLANK;
			String _contactEmail = StringPool.BLANK;

			// TODO
//			String _dossierFileNo = StringPool.BLANK;
//			String _dossierFileDate = StringPool.BLANK;
			String _receiveDate = StringPool.BLANK;
			String _dossierNo = StringPool.BLANK;

			String _employee_employeeNo = StringPool.BLANK;
			String _employee_fullName = StringPool.BLANK;
			String _employee_title = StringPool.BLANK;
			String _applicantName = StringPool.BLANK;
			String _applicantIdType = StringPool.BLANK;
			String _applicantIdNo = StringPool.BLANK;
			String _applicantIdDate = StringPool.BLANK;
			String _curDate;
			String _representative = StringPool.BLANK;
			String _govAgencyName = StringPool.BLANK;
			String _serviceName = StringPool.BLANK;

			SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			_curDate = sfd.format(new Date());

			if (Validator.isNotNull(dossier)) {
				_receiveDate = Validator.isNotNull(dossier.getReceiveDate()) ? dossier.getReceiveDate().toGMTString()
						: StringPool.BLANK;
				_dossierNo = dossier.getDossierNo();

				// get data applicant or employee
				ApplicantActions applicantActions = new ApplicantActionsImpl();

				try {

					Registration registration = RegistrationLocalServiceUtil
							.getLastSubmittingByUser(dossier.getGroupId(), serviceContext.getUserId(), true);

					if (Validator.isNotNull(registration)) {

						JSONObject applicantJSON = JSONFactoryUtil
								.createJSONObject(JSONFactoryUtil.looseSerialize(registration));

						_subjectName = applicantJSON.getString("applicantName");
						_subjectId = applicantJSON.getString("applicantId");
						_address = applicantJSON.getString("address");
						_cityCode = applicantJSON.getString("cityCode");
						_cityName = applicantJSON.getString("cityName");
						_districtCode = applicantJSON.getString("districtCode");
						_districtName = applicantJSON.getString("districtName");
						_wardCode = applicantJSON.getString("wardCode");
						_wardName = applicantJSON.getString("wardName");
						_contactName = applicantJSON.getString("contactName");
						_contactTelNo = applicantJSON.getString("contactTelNo");
						_contactEmail = applicantJSON.getString("contactEmail");
						_applicantName = applicantJSON.getString("applicantName");
						_applicantIdType = applicantJSON.getString("applicantIdType");
						_applicantIdNo = applicantJSON.getString("applicantIdNo");
//						_applicantIdDate = applicantJSON.getString("applicantIdDate");
						_applicantIdDate = applicantJSON.getString("representativeEnterprise");

					} else {
						String applicantStr = applicantActions.getApplicantByUserId(serviceContext);

						JSONObject applicantJSON = JSONFactoryUtil.createJSONObject(applicantStr);

						_subjectName = applicantJSON.getString("applicantName");
						_subjectId = applicantJSON.getString("applicantId");
						_address = applicantJSON.getString("address");
						_cityCode = applicantJSON.getString("cityCode");
						_cityName = applicantJSON.getString("cityName");
						_districtCode = applicantJSON.getString("districtCode");
						_districtName = applicantJSON.getString("districtName");
						_wardCode = applicantJSON.getString("wardCode");
						_wardName = applicantJSON.getString("wardName");
						_contactName = applicantJSON.getString("contactName");
						_contactTelNo = applicantJSON.getString("contactTelNo");
						_contactEmail = applicantJSON.getString("contactEmail");
						_applicantName = applicantJSON.getString("applicantName");
						_applicantIdType = applicantJSON.getString("applicantIdType");
						_applicantIdNo = applicantJSON.getString("applicantIdNo");
						_applicantIdDate = applicantJSON.getString("applicantIdDate");
						_representative = applicantJSON.getString("representativeEnterprise");

					}

				} catch (PortalException e1) {
					// TODO Auto-generated catch block
//					e1.printStackTrace();
					_log.error(e1);
				}

				try {
					Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(dossier.getGroupId(),
							serviceContext.getUserId());

					// _log.info("GET EMPLOYEE ID ____" +
					// serviceContext.getUserId());

					JSONObject employeeJSON = JSONFactoryUtil
							.createJSONObject(JSONFactoryUtil.looseSerialize(employee));

					// _log.info("GET EMPLOYEE ____");

					// _log.info(employeeJSON);

					_employee_employeeNo = employeeJSON.getString("employeeNo");
					_employee_fullName = employeeJSON.getString("fullName");
					_employee_title = employeeJSON.getString("title");

				} catch (Exception e) {
					_log.info("NOT FOUN EMPLOYEE" + serviceContext.getUserId());
					_log.debug(e);
					//_log.error(e);
				}
				
				_govAgencyName = dossier.getGovAgencyName();
				_serviceName = dossier.getServiceName();
			}
			// process sampleData
//			if (Validator.isNull(sampleData)) {
//				sampleData = "{}";
//			}

			Map<String, Object> jsonMap = jsonToMap(result);

			for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {

				String value = String.valueOf(entry.getValue());

				if (value.startsWith("_") && !value.contains(":")) {

					if ("_subjectName".equals(value)) {
						jsonMap.put(entry.getKey(), _subjectName);
					} else if ("_subjectId".equals(value)) {
						jsonMap.put(entry.getKey(), _subjectId);
					} else if ("_address".equals(value)) {
						jsonMap.put(entry.getKey(), _address);
					} else if ("_cityCode".equals(value)) {
						jsonMap.put(entry.getKey(), _cityCode);
					} else if ("_cityName".equals(value)) {
						jsonMap.put(entry.getKey(), _cityName);
					} else if ("_districtCode".equals(value)) {
						jsonMap.put(entry.getKey(), _districtCode);
					} else if ("_districtName".equals(value)) {
						jsonMap.put(entry.getKey(), _districtName);
					} else if ("_wardCode".equals(value)) {
						jsonMap.put(entry.getKey(), _wardCode);
					} else if ("_wardName".equals(value)) {
						jsonMap.put(entry.getKey(), _wardName);
					} else if ("_contactName".equals(value)) {
						jsonMap.put(entry.getKey(), _contactName);
					} else if ("_contactTelNo".equals(value)) {
						jsonMap.put(entry.getKey(), _contactTelNo);
					} else if ("_contactEmail".equals(value)) {
						jsonMap.put(entry.getKey(), _contactEmail);
					} else if ("_receiveDate".equals(value)) {
						jsonMap.put(entry.getKey(), _receiveDate);
					} else if ("_dossierNo".equals(value)) {
						jsonMap.put(entry.getKey(), _dossierNo);
					} else if ("_employee_employeeNo".equals(value)) {
						jsonMap.put(entry.getKey(), _employee_employeeNo);
					} else if ("_employee_fullName".equals(value)) {
						jsonMap.put(entry.getKey(), _employee_fullName);
					} else if ("_employee_title".equals(value)) {
						jsonMap.put(entry.getKey(), _employee_title);
					} else if ("_applicantName".equals(value)) {
						jsonMap.put(entry.getKey(), _applicantName);
					} else if ("_applicantIdType".equals(value)) {
						jsonMap.put(entry.getKey(), _applicantIdType);
					} else if ("_applicantIdNo".equals(value)) {
						jsonMap.put(entry.getKey(), _applicantIdNo);
					} else if ("_applicantIdDate".equals(value)) {
						jsonMap.put(entry.getKey(), _applicantIdDate);
					} else if ("_curDate".equals(value)) {
						jsonMap.put(entry.getKey(), _curDate);
					} else if ("_representative".equals(value)) {
						jsonMap.put(entry.getKey(), _representative);
					} else if ("_govAgencyName".equals(value)) {
						jsonMap.put(entry.getKey(), _govAgencyName);
					} else if ("_serviceName".equals(value)) {
						jsonMap.put(entry.getKey(), _serviceName);
					}

				} else if (value.startsWith("_") && value.contains(":")) {
					String resultBinding = StringPool.BLANK;
					String[] valueSplit = value.split(":");
					for (String string : valueSplit) {
						if ("_subjectName".equals(string)) {
							resultBinding += ", " + _subjectName;
						} else if ("_subjectId".equals(string)) {
							resultBinding += ", " + _subjectId;
						} else if ("_address".equals(string)) {
							resultBinding += ", " + _address;
						} else if ("_wardCode".equals(string)) {
							resultBinding += ", " + _wardCode;
						} else if ("_wardName".equals(string)) {
							resultBinding += ", " + _wardName;
						} else if ("_districtCode".equals(string)) {
							resultBinding += ", " + _districtCode;
						} else if ("_districtName".equals(string)) {
							resultBinding += ", " + _districtName;
						} else if ("_cityCode".equals(string)) {
							resultBinding += ", " + _cityCode;
						} else if ("_cityName".equals(string)) {
							resultBinding += ", " + _cityName;
						} else if ("_contactName".equals(string)) {
							resultBinding += ", " + _contactName;
						} else if ("_contactTelNo".equals(string)) {
							resultBinding += ", " + _contactTelNo;
						} else if ("_contactEmail".equals(string)) {
							resultBinding += ", " + _contactEmail;
						} else if ("_receiveDate".equals(value)) {
							resultBinding += ", " + _receiveDate;
						} else if ("_dossierNo".equals(value)) {
							resultBinding += ", " + _dossierNo;
						} else if ("_employee_employeeNo".equals(value)) {
							resultBinding += ", " + _employee_employeeNo;
						} else if ("_employee_fullName".equals(value)) {
							resultBinding += ", " + _employee_fullName;
						} else if ("_employee_title".equals(value)) {
							resultBinding += ", " + _employee_title;
						} else if ("_applicantName".equals(value)) {
							resultBinding += ", " + _applicantName;
						} else if ("_applicantIdType".equals(value)) {
							resultBinding += ", " + _applicantIdType;
						} else if ("_applicantIdNo".equals(value)) {
							resultBinding += ", " + _applicantIdNo;
						} else if ("_applicantIdDate".equals(value)) {
							resultBinding += ", " + _applicantIdDate;
						} else if ("_curDate".equals(value)) {
							resultBinding += ", " + _curDate;
						} else if ("_representative".equals(value)) {
							resultBinding += ", " + _representative;
						} else if ("_govAgencyName".equals(value)) {
							jsonMap.put(entry.getKey(), _govAgencyName);
						} else if ("_serviceName".equals(value)) {
							jsonMap.put(entry.getKey(), _serviceName);
						}
					}

					jsonMap.put(entry.getKey(), resultBinding.replaceFirst(", ", StringPool.BLANK));

				} else if (value.startsWith("#") && value.contains("@")) {
					String newString = value.substring(1);
					String[] stringSplit = newString.split("@");
					String variable = stringSplit[0];
					String paper = stringSplit[1];
					try {
						DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO_First(dossierId,
								paper, false, new DossierFileComparator(false, "createDate", Date.class));

						if (Validator.isNotNull(dossierFile) && Validator.isNotNull(dossierFile.getFormData())
								&& dossierFile.getFormData().trim().length() != 0) {
							JSONObject jsonOtherData = JSONFactoryUtil.createJSONObject(dossierFile.getFormData());
							Map<String, Object> jsonOtherMap = jsonToMap(jsonOtherData);
							// _log.info("JSON other map: " +
							// Arrays.toString(jsonOtherMap.entrySet().toArray()));
							String myCHK = StringPool.BLANK;
							try {
								if (variable.contains(":")) {
									String[] variableMuti = variable.split(":");
									for (String string : variableMuti) {
										myCHK += ", " + jsonOtherMap.get(string).toString();
									}
									myCHK = myCHK.replaceFirst(", ", "");
								} else {
									myCHK = jsonOtherMap.get(variable).toString();
								}
							} catch (Exception e) {
								// TODO: handle exception
								_log.debug(e);
								//_log.error(e);
							}

							if (myCHK.startsWith("#")) {
								jsonMap.put(entry.getKey(), "");
							} else {
								jsonMap.put(entry.getKey(), myCHK.toString());
							}
						} else {
							jsonMap.put(entry.getKey(), "");
						}
					} catch (SystemException e) {
//						e.printStackTrace();
						_log.error(e);
					}
				} else if (value.startsWith(StringPool.DOLLAR) && value.contains(StringPool.AT)) {
					//_log.info("START ACTIONCONFIG: "+value);
					String newString = value.substring(1);
					String[] stringSplit = newString.split(StringPool.AT);
					String variable = stringSplit[0];
					String paper = stringSplit[1];
					//_log.info("START variable: "+variable);
					//_log.info("START paper: "+paper);
					try {
						DossierAction dossierAction = DossierActionLocalServiceUtil.getByDID_CODE_First(dossierId,
								paper, new DossierActionComparator(false, "createDate", Date.class));
						//_log.info("START dossierAction: "+dossierAction);

						if (Validator.isNotNull(dossierAction) && Validator.isNotNull(dossierAction.getPayload())
								&& dossierAction.getPayload().trim().length() != 0) {
							JSONObject jsonOtherData = JSONFactoryUtil.createJSONObject(dossierAction.getPayload());
							Map<String, Object> jsonOtherMap = jsonToMap(jsonOtherData);
							// _log.info("JSON other map: " +
							// Arrays.toString(jsonOtherMap.entrySet().toArray()));
							String myActConfig = jsonOtherMap.get(variable).toString();
							//
							jsonMap.put(entry.getKey(), myActConfig);
						} else {
							jsonMap.put(entry.getKey(), StringPool.BLANK);
						}
					} catch (SystemException e) {
//						e.printStackTrace();
						_log.error(e);
					}
				}
			}

			for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
				if (entry.getValue().getClass().getName().contains("JSONArray")) {
					result.put(entry.getKey(), (JSONArray) entry.getValue());
				} else if (entry.getValue().getClass().getName().contains("JSONObject")) {
					result.put(entry.getKey(), (JSONObject) entry.getValue());
				} else {
					result.put(entry.getKey(), entry.getValue() + "");
				}
			}
		} catch (JSONException e) {
			_log.error(e);
//			e.printStackTrace();
		}

		_log.debug("START result: "+result);
		return result.toJSONString();
	}

	public static Map<String, Object> jsonToMap(JSONObject json) {
		Map<String, Object> retMap = new HashMap<String, Object>();

		if (Validator.isNotNull(json)) {
			try {
				retMap = toMap(json);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				_log.error(e);
			}
		}
		return retMap;
	}

	public static Map<String, Object> toMap(JSONObject object) throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();

		Iterator<String> keysItr = object.keys();
		while (keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = null;
			if (Validator.isNotNull(object.getJSONArray(key))) {
				value = (JSONArray) object.getJSONArray(key);
				map.put(key, value);
			}

			else if (Validator.isNotNull(object.getJSONObject(key))) {
				value = (JSONObject) object.getJSONObject(key);
				map.put(key, value);
			}

			else {
				value = object.getString(key);
				map.put(key, value);
			}
		}
		return map;
	}

	public static List<Object> toList(JSONArray array) throws JSONException {
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < array.length(); i++) {
			Object value = array.getJSONObject(i);

			if (value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			list.add(value);
		}
		return list;
	}

	public static String sampleDataBinding(String sampleData, Dossier dossier, ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		JSONObject result = JSONFactoryUtil.createJSONObject();
		try {

			result = JSONFactoryUtil.createJSONObject(sampleData);

			String _subjectName = StringPool.BLANK;
			String _subjectId = StringPool.BLANK;
			String _address = StringPool.BLANK;
			String _cityCode = StringPool.BLANK;
			String _cityName = StringPool.BLANK;
			String _districtCode = StringPool.BLANK;
			String _districtName = StringPool.BLANK;
			String _wardCode = StringPool.BLANK;
			String _wardName = StringPool.BLANK;
			String _contactName = StringPool.BLANK;
			String _contactTelNo = StringPool.BLANK;
			String _contactEmail = StringPool.BLANK;

//			String _dossierFileNo = StringPool.BLANK;
//			String _dossierFileDate = StringPool.BLANK;
			String _receiveDate = StringPool.BLANK;
			String _dossierNo = StringPool.BLANK;

			String _employee_employeeNo = StringPool.BLANK;
			String _employee_fullName = StringPool.BLANK;
			String _employee_title = StringPool.BLANK;
			String _applicantName = StringPool.BLANK;
			String _applicantIdType = StringPool.BLANK;
			String _applicantIdNo = StringPool.BLANK;
			String _applicantIdDate = StringPool.BLANK;
			String _curDate;
			String _representative = StringPool.BLANK;
			String _govAgencyName = StringPool.BLANK;
			String _serviceName = StringPool.BLANK;

			SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			_curDate = sfd.format(new Date());

			if (Validator.isNotNull(dossier)) {
				_receiveDate = Validator.isNotNull(dossier.getReceiveDate()) ? dossier.getReceiveDate().toGMTString()
						: StringPool.BLANK;
				_dossierNo = dossier.getDossierNo();

				// get data applicant or employee
				ApplicantActions applicantActions = new ApplicantActionsImpl();

				try {

					Registration registration = RegistrationLocalServiceUtil
							.getLastSubmittingByUser(dossier.getGroupId(), serviceContext.getUserId(), true);

					if (Validator.isNotNull(registration)) {

						JSONObject applicantJSON = JSONFactoryUtil
								.createJSONObject(JSONFactoryUtil.looseSerialize(registration));

						_subjectName = applicantJSON.getString("applicantName");
						_subjectId = applicantJSON.getString("applicantId");
						_address = applicantJSON.getString("address");
						_cityCode = applicantJSON.getString("cityCode");
						_cityName = applicantJSON.getString("cityName");
						_districtCode = applicantJSON.getString("districtCode");
						_districtName = applicantJSON.getString("districtName");
						_wardCode = applicantJSON.getString("wardCode");
						_wardName = applicantJSON.getString("wardName");
						_contactName = applicantJSON.getString("contactName");
						_contactTelNo = applicantJSON.getString("contactTelNo");
						_contactEmail = applicantJSON.getString("contactEmail");
						_applicantName = applicantJSON.getString("applicantName");
						_applicantIdType = applicantJSON.getString("applicantIdType");
						_applicantIdNo = applicantJSON.getString("applicantIdNo");
//						_applicantIdDate = applicantJSON.getString("applicantIdDate");
						_applicantIdDate = applicantJSON.getString("representativeEnterprise");

					} else {
						String applicantStr = applicantActions.getApplicantByUserId(serviceContext);

						JSONObject applicantJSON = JSONFactoryUtil.createJSONObject(applicantStr);

						_subjectName = applicantJSON.getString("applicantName");
						_subjectId = applicantJSON.getString("applicantId");
						_address = applicantJSON.getString("address");
						_cityCode = applicantJSON.getString("cityCode");
						_cityName = applicantJSON.getString("cityName");
						_districtCode = applicantJSON.getString("districtCode");
						_districtName = applicantJSON.getString("districtName");
						_wardCode = applicantJSON.getString("wardCode");
						_wardName = applicantJSON.getString("wardName");
						_contactName = applicantJSON.getString("contactName");
						_contactTelNo = applicantJSON.getString("contactTelNo");
						_contactEmail = applicantJSON.getString("contactEmail");
						_applicantName = applicantJSON.getString("applicantName");
						_applicantIdType = applicantJSON.getString("applicantIdType");
						_applicantIdNo = applicantJSON.getString("applicantIdNo");
						_applicantIdDate = applicantJSON.getString("applicantIdDate");
						_representative = applicantJSON.getString("representativeEnterprise");

					}

				} catch (PortalException e1) {
					// TODO Auto-generated catch block
//					e1.printStackTrace();
					_log.error(e1);
				}

				try {
					Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(dossier.getGroupId(),
							serviceContext.getUserId());

					// _log.info("GET EMPLOYEE ID ____" +
					// serviceContext.getUserId());

					JSONObject employeeJSON = JSONFactoryUtil
							.createJSONObject(JSONFactoryUtil.looseSerialize(employee));

					// _log.info("GET EMPLOYEE ____");

					// _log.info(employeeJSON);

					_employee_employeeNo = employeeJSON.getString("employeeNo");
					_employee_fullName = employeeJSON.getString("fullName");
					_employee_title = employeeJSON.getString("title");

				} catch (Exception e) {
					_log.info("NOT FOUN EMPLOYEE" + serviceContext.getUserId());
					_log.debug(e);
					//_log.error(e);
				}
				
				_govAgencyName = dossier.getGovAgencyName();
				_serviceName = dossier.getServiceName();
			}
			// process sampleData
//			if (Validator.isNull(sampleData)) {
//				sampleData = "{}";
//			}

			Map<String, Object> jsonMap = jsonToMap(result);

			for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {

				String value = String.valueOf(entry.getValue());

				if (value.startsWith("_") && !value.contains(":")) {

					if ("_subjectName".equals(value)) {
						jsonMap.put(entry.getKey(), _subjectName);
					} else if ("_subjectId".equals(value)) {
						jsonMap.put(entry.getKey(), _subjectId);
					} else if ("_address".equals(value)) {
						jsonMap.put(entry.getKey(), _address);
					} else if ("_cityCode".equals(value)) {
						jsonMap.put(entry.getKey(), _cityCode);
					} else if ("_cityName".equals(value)) {
						jsonMap.put(entry.getKey(), _cityName);
					} else if ("_districtCode".equals(value)) {
						jsonMap.put(entry.getKey(), _districtCode);
					} else if ("_districtName".equals(value)) {
						jsonMap.put(entry.getKey(), _districtName);
					} else if ("_wardCode".equals(value)) {
						jsonMap.put(entry.getKey(), _wardCode);
					} else if ("_wardName".equals(value)) {
						jsonMap.put(entry.getKey(), _wardName);
					} else if ("_contactName".equals(value)) {
						jsonMap.put(entry.getKey(), _contactName);
					} else if ("_contactTelNo".equals(value)) {
						jsonMap.put(entry.getKey(), _contactTelNo);
					} else if ("_contactEmail".equals(value)) {
						jsonMap.put(entry.getKey(), _contactEmail);
					} else if ("_receiveDate".equals(value)) {
						jsonMap.put(entry.getKey(), _receiveDate);
					} else if ("_dossierNo".equals(value)) {
						jsonMap.put(entry.getKey(), _dossierNo);
					} else if ("_employee_employeeNo".equals(value)) {
						jsonMap.put(entry.getKey(), _employee_employeeNo);
					} else if ("_employee_fullName".equals(value)) {
						jsonMap.put(entry.getKey(), _employee_fullName);
					} else if ("_employee_title".equals(value)) {
						jsonMap.put(entry.getKey(), _employee_title);
					} else if ("_applicantName".equals(value)) {
						jsonMap.put(entry.getKey(), _applicantName);
					} else if ("_applicantIdType".equals(value)) {
						jsonMap.put(entry.getKey(), _applicantIdType);
					} else if ("_applicantIdNo".equals(value)) {
						jsonMap.put(entry.getKey(), _applicantIdNo);
					} else if ("_applicantIdDate".equals(value)) {
						jsonMap.put(entry.getKey(), _applicantIdDate);
					} else if ("_curDate".equals(value)) {
						jsonMap.put(entry.getKey(), _curDate);
					} else if ("_representative".equals(value)) {
						jsonMap.put(entry.getKey(), _representative);
					} else if ("_govAgencyName".equals(value)) {
						jsonMap.put(entry.getKey(), _govAgencyName);
					} else if ("_serviceName".equals(value)) {
						jsonMap.put(entry.getKey(), _serviceName);
					}

				} else if (value.startsWith("_") && value.contains(":")) {
					String resultBinding = StringPool.BLANK;
					String[] valueSplit = value.split(":");
					for (String string : valueSplit) {
						if ("_subjectName".equals(string)) {
							resultBinding += ", " + _subjectName;
						} else if ("_subjectId".equals(string)) {
							resultBinding += ", " + _subjectId;
						} else if ("_address".equals(string)) {
							resultBinding += ", " + _address;
						} else if ("_wardCode".equals(string)) {
							resultBinding += ", " + _wardCode;
						} else if ("_wardName".equals(string)) {
							resultBinding += ", " + _wardName;
						} else if ("_districtCode".equals(string)) {
							resultBinding += ", " + _districtCode;
						} else if ("_districtName".equals(string)) {
							resultBinding += ", " + _districtName;
						} else if ("_cityCode".equals(string)) {
							resultBinding += ", " + _cityCode;
						} else if ("_cityName".equals(string)) {
							resultBinding += ", " + _cityName;
						} else if ("_contactName".equals(string)) {
							resultBinding += ", " + _contactName;
						} else if ("_contactTelNo".equals(string)) {
							resultBinding += ", " + _contactTelNo;
						} else if ("_contactEmail".equals(string)) {
							resultBinding += ", " + _contactEmail;
						} else if ("_receiveDate".equals(value)) {
							resultBinding += ", " + _receiveDate;
						} else if ("_dossierNo".equals(value)) {
							resultBinding += ", " + _dossierNo;
						} else if ("_employee_employeeNo".equals(value)) {
							resultBinding += ", " + _employee_employeeNo;
						} else if ("_employee_fullName".equals(value)) {
							resultBinding += ", " + _employee_fullName;
						} else if ("_employee_title".equals(value)) {
							resultBinding += ", " + _employee_title;
						} else if ("_applicantName".equals(value)) {
							resultBinding += ", " + _applicantName;
						} else if ("_applicantIdType".equals(value)) {
							resultBinding += ", " + _applicantIdType;
						} else if ("_applicantIdNo".equals(value)) {
							resultBinding += ", " + _applicantIdNo;
						} else if ("_applicantIdDate".equals(value)) {
							resultBinding += ", " + _applicantIdDate;
						} else if ("_curDate".equals(value)) {
							resultBinding += ", " + _curDate;
						} else if ("_representative".equals(value)) {
							resultBinding += ", " + _representative;
						} else if ("_govAgencyName".equals(value)) {
							jsonMap.put(entry.getKey(), _govAgencyName);
						} else if ("_serviceName".equals(value)) {
							jsonMap.put(entry.getKey(), _serviceName);
						}
					}

					jsonMap.put(entry.getKey(), resultBinding.replaceFirst(", ", StringPool.BLANK));

				} else if (value.startsWith("#") && value.contains("@")) {
					String newString = value.substring(1);
					String[] stringSplit = newString.split("@");
					String variable = stringSplit[0];
					String paper = stringSplit[1];
					try {
						DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO_First(dossier.getDossierId(),
								paper, false, new DossierFileComparator(false, "createDate", Date.class));

						if (Validator.isNotNull(dossierFile) && Validator.isNotNull(dossierFile.getFormData())
								&& dossierFile.getFormData().trim().length() != 0) {
							JSONObject jsonOtherData = JSONFactoryUtil.createJSONObject(dossierFile.getFormData());
							Map<String, Object> jsonOtherMap = jsonToMap(jsonOtherData);
							// _log.info("JSON other map: " +
							// Arrays.toString(jsonOtherMap.entrySet().toArray()));
							String myCHK = StringPool.BLANK;
							try {
								if (variable.contains(":")) {
									String[] variableMuti = variable.split(":");
									for (String string : variableMuti) {
										myCHK += ", " + jsonOtherMap.get(string).toString();
									}
									myCHK = myCHK.replaceFirst(", ", "");
								} else {
									myCHK = jsonOtherMap.get(variable).toString();
								}
							} catch (Exception e) {
								// TODO: handle exception
								_log.debug(e);
								//_log.error(e);
							}

							if (myCHK.startsWith("#")) {
								jsonMap.put(entry.getKey(), "");
							} else {
								jsonMap.put(entry.getKey(), myCHK.toString());
							}
						} else {
							jsonMap.put(entry.getKey(), "");
						}
					} catch (SystemException e) {
//						e.printStackTrace();
						_log.error(e);
					}
				} else if (value.startsWith(StringPool.DOLLAR) && value.contains(StringPool.AT)) {
					//_log.info("START ACTIONCONFIG: "+value);
					String newString = value.substring(1);
					String[] stringSplit = newString.split(StringPool.AT);
					String variable = stringSplit[0];
					String paper = stringSplit[1];
					//_log.info("START variable: "+variable);
					//_log.info("START paper: "+paper);
					try {
						DossierAction dossierAction = DossierActionLocalServiceUtil.getByDID_CODE_First(dossier.getDossierId(),
								paper, new DossierActionComparator(false, "createDate", Date.class));
						//_log.info("START dossierAction: "+dossierAction);

						if (Validator.isNotNull(dossierAction) && Validator.isNotNull(dossierAction.getPayload())
								&& dossierAction.getPayload().trim().length() != 0) {
							JSONObject jsonOtherData = JSONFactoryUtil.createJSONObject(dossierAction.getPayload());
							Map<String, Object> jsonOtherMap = jsonToMap(jsonOtherData);
							// _log.info("JSON other map: " +
							// Arrays.toString(jsonOtherMap.entrySet().toArray()));
							String myActConfig = jsonOtherMap.get(variable).toString();
							//
							jsonMap.put(entry.getKey(), myActConfig);
						} else {
							jsonMap.put(entry.getKey(), StringPool.BLANK);
						}
					} catch (SystemException e) {
//						e.printStackTrace();
						_log.error(e);
					}
				}
			}

			for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
				if (entry.getValue().getClass().getName().contains("JSONArray")) {
					result.put(entry.getKey(), (JSONArray) entry.getValue());
				} else if (entry.getValue().getClass().getName().contains("JSONObject")) {
					result.put(entry.getKey(), (JSONObject) entry.getValue());
				} else {
					result.put(entry.getKey(), entry.getValue() + "");
				}
			}
		} catch (JSONException e) {
			_log.error(e);
//			e.printStackTrace();
		}

		_log.debug("START result: "+result);
		return result.toJSONString();
	}	
	private static final Log _log = LogFactoryUtil.getLog(AutoFillFormData.class);
}
