package org.opencps.dossiermgt.action.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationLocalServiceUtil;
import org.opencps.dossiermgt.service.comparator.DossierFileComparator;
import org.opencps.usermgt.action.ApplicantActions;
import org.opencps.usermgt.action.impl.ApplicantActionsImpl;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class AutoFillFormData {

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
			String _dossierFileNo = StringPool.BLANK;
			String _dossierFileDate = StringPool.BLANK;
			String _receiveDate = StringPool.BLANK;
			String _dossierNo = StringPool.BLANK;

			String _employee_employeeNo = StringPool.BLANK;
			String _employee_fullName = StringPool.BLANK;
			String _employee_title = StringPool.BLANK;
			String _applicantName = StringPool.BLANK;
			String _applicantIdType = StringPool.BLANK;
			String _applicantIdNo = StringPool.BLANK;
			String _applicantIdDate = StringPool.BLANK;
			String _curDate = StringPool.BLANK;
			String _representative = StringPool.BLANK;
			
			SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			
			_curDate = sfd.format(new Date());
			
			if (Validator.isNotNull(dossier)) {
				_receiveDate = Validator.isNotNull(dossier.getReceiveDate())?dossier.getReceiveDate().toGMTString():StringPool.BLANK;
				_dossierNo = dossier.getDossierNo();
			}
			
			// get data applicant or employee
			ApplicantActions applicantActions = new ApplicantActionsImpl();

			try {
				
				Registration registration = RegistrationLocalServiceUtil.getLastSubmittingByUser(dossier.getGroupId(), serviceContext.getUserId(), true);
				
				if (Validator.isNotNull(registration)) {

					JSONObject applicantJSON = JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(registration));

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
				e1.printStackTrace();
			}
			
			try {
				Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(dossier.getGroupId(), serviceContext.getUserId());
				
//				_log.info("GET EMPLOYEE ID ____" + serviceContext.getUserId());
				
				JSONObject employeeJSON = JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(employee));
				
//				_log.info("GET EMPLOYEE ____");

//				_log.info(employeeJSON);
				
				_employee_employeeNo = employeeJSON.getString("employeeNo");
				_employee_fullName = employeeJSON.getString("fullName");
				_employee_title = employeeJSON.getString("title");
				
			} catch (Exception e) {
				_log.info("NOT FOUN EMPLOYEE" + serviceContext.getUserId());
				_log.error(e);
			}

			// process sampleData
			if (Validator.isNull(sampleData)) {
				sampleData = "{}";
			}

			Map<String, Object> jsonMap = jsonToMap(result);

			for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {

				String value = String.valueOf(entry.getValue());

				if (value.startsWith("_") && !value.contains(":")) {

					if (value.equals("_subjectName")) {
						jsonMap.put(entry.getKey(), _subjectName);
					} else if (value.equals("_subjectId")) {
						jsonMap.put(entry.getKey(), _subjectId);
					} else if (value.equals("_address")) {
						jsonMap.put(entry.getKey(), _address);
					} else if (value.equals("_cityCode")) {
						jsonMap.put(entry.getKey(), _cityCode);
					} else if (value.equals("_cityName")) {
						jsonMap.put(entry.getKey(), _cityName);
					} else if (value.equals("_districtCode")) {
						jsonMap.put(entry.getKey(), _districtCode);
					} else if (value.equals("_districtName")) {
						jsonMap.put(entry.getKey(), _districtName);
					} else if (value.equals("_wardCode")) {
						jsonMap.put(entry.getKey(), _wardCode);
					} else if (value.equals("_wardName")) {
						jsonMap.put(entry.getKey(), _wardName);
					} else if (value.equals("_contactName")) {
						jsonMap.put(entry.getKey(), _contactName);
					} else if (value.equals("_contactTelNo")) {
						jsonMap.put(entry.getKey(), _contactTelNo);
					} else if (value.equals("_contactEmail")) {
						jsonMap.put(entry.getKey(), _contactEmail);
					} else if (value.equals("_receiveDate")) {
						jsonMap.put(entry.getKey(), _receiveDate);
					} else if (value.equals("_dossierNo")) {
						jsonMap.put(entry.getKey(), _dossierNo);
					} else if (value.equals("_employee_employeeNo")) {
						jsonMap.put(entry.getKey(), _employee_employeeNo);
					} else if (value.equals("_employee_fullName")) {
						jsonMap.put(entry.getKey(), _employee_fullName);
					}else if (value.equals("_employee_title")) {
						jsonMap.put(entry.getKey(), _employee_title);
					} else if (value.equals("_applicantName")) {
						jsonMap.put(entry.getKey(), _applicantName);
					} else if (value.equals("_applicantIdType")) {
						jsonMap.put(entry.getKey(), _applicantIdType);
					} else if (value.equals("_applicantIdNo")) {
						jsonMap.put(entry.getKey(), _applicantIdNo);
					} else if (value.equals("_applicantIdDate")) {
						jsonMap.put(entry.getKey(), _applicantIdDate);
					}else if (value.equals("_curDate")) {
						jsonMap.put(entry.getKey(), _curDate);
					} else if (value.equals("_representative")) {
						jsonMap.put(entry.getKey(), _representative);
					}

				} else if (value.startsWith("_") && value.contains(":")) {
					String resultBinding = StringPool.BLANK;
					String[] valueSplit = value.split(":");
					for (String string : valueSplit) {
						if (string.equals("_subjectName")) {
							resultBinding += ", " + _subjectName;
						} else if (string.equals("_subjectId")) {
							resultBinding += ", " + _subjectId;
						} else if (string.equals("_address")) {
							resultBinding += ", " + _address;
						} else if (string.equals("_wardCode")) {
							resultBinding += ", " + _wardCode;
						} else if (string.equals("_wardName")) {
							resultBinding += ", " + _wardName;
						} else if (string.equals("_districtCode")) {
							resultBinding += ", " + _districtCode;
						} else if (string.equals("_districtName")) {
							resultBinding += ", " + _districtName;
						} else if (string.equals("_cityCode")) {
							resultBinding += ", " + _cityCode;
						} else if (string.equals("_cityName")) {
							resultBinding += ", " + _cityName;
						} else if (string.equals("_contactName")) {
							resultBinding += ", " + _contactName;
						} else if (string.equals("_contactTelNo")) {
							resultBinding += ", " + _contactTelNo;
						} else if (string.equals("_contactEmail")) {
							resultBinding += ", " + _contactEmail;
						} else if (value.equals("_receiveDate")) {
							resultBinding += ", " + _receiveDate;
						} else if (value.equals("_dossierNo")) {
							resultBinding += ", " + _dossierNo;
						} else if (value.equals("_employee_employeeNo")) {
							resultBinding += ", " + _employee_employeeNo;
						} else if (value.equals("_employee_fullName")) {
							resultBinding += ", " + _employee_fullName;
						}else if (value.equals("_employee_title")) {
							resultBinding += ", " + _employee_title;
						} else if (value.equals("_applicantName")) {
							resultBinding += ", " + _applicantName;
						} else if (value.equals("_applicantIdType")) {
							resultBinding += ", " + _applicantIdType;
						} else if (value.equals("_applicantIdNo")) {
							resultBinding += ", " + _applicantIdNo;
						} else if (value.equals("_applicantIdDate")) {
							resultBinding += ", " + _applicantIdDate;
						} else if (value.equals("_curDate")) {
							resultBinding += ", " + _curDate;
						} else if (value.equals("_representative")) {
							resultBinding += ", " + _representative;
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
						
						if (Validator.isNotNull(dossierFile) && Validator.isNotNull(dossierFile.getFormData()) && dossierFile.getFormData().trim().length() != 0) {
							JSONObject jsonOtherData = JSONFactoryUtil.createJSONObject(dossierFile.getFormData());
							Map<String, Object> jsonOtherMap = jsonToMap(jsonOtherData);
//							_log.info("JSON other map: " + Arrays.toString(jsonOtherMap.entrySet().toArray()));
							String myCHK = StringPool.BLANK;
							try {
								if (variable.contains(":")) {
									String[] variableMuti = variable.split(":");
									for (String string : variableMuti) {
										myCHK += ", " + jsonOtherMap.get(string).toString();
									}
									myCHK = myCHK.replaceFirst(", ", "");
								}else{
									myCHK = jsonOtherMap.get(variable).toString();
								}
							} catch (Exception e) {
								// TODO: handle exception
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
						e.printStackTrace();
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
			e.printStackTrace();
		}
		
		return result.toJSONString();
	}

	public static Map<String, Object> jsonToMap(JSONObject json) {
		Map<String, Object> retMap = new HashMap<String, Object>();

		if (Validator.isNotNull(json)) {
			try {
				retMap = toMap(json);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	
	private static final Log _log = LogFactoryUtil.getLog(AutoFillFormData.class);
}
