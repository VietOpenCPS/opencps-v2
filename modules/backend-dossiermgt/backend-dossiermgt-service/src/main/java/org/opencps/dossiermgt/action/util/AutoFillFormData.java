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
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.comparator.DossierActionComparator;
import org.opencps.dossiermgt.service.comparator.DossierFileComparator;
import org.opencps.usermgt.action.ApplicantActions;
import org.opencps.usermgt.action.impl.ApplicantActionsImpl;
import org.opencps.usermgt.constants.ApplicantTerm;
import org.opencps.usermgt.constants.EmployeeTerm;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

public class AutoFillFormData {

	public static Map<String, Object> jsonToMap(JSONObject json) {
		Map<String, Object> retMap = new HashMap<String, Object>();

		if (Validator.isNotNull(json)) {
			try {
				retMap = toMap(json);
			} catch (JSONException e) {
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

			SimpleDateFormat sfd = new SimpleDateFormat(APIDateTimeUtils._NORMAL_PARTTERN);

			_curDate = sfd.format(new Date());

			if (Validator.isNotNull(dossier)) {
				_receiveDate = Validator.isNotNull(dossier.getReceiveDate()) ? dossier.getReceiveDate().toGMTString()
						: StringPool.BLANK;
				_dossierNo = dossier.getDossierNo();

				// get data applicant or employee
				ApplicantActions applicantActions = new ApplicantActionsImpl();

				try {

						String applicantStr = applicantActions.getApplicantByUserId(serviceContext);

						JSONObject applicantJSON = JSONFactoryUtil.createJSONObject(applicantStr);

						_subjectName = applicantJSON.getString(ApplicantTerm.APPLICANTNAME);
						_subjectId = applicantJSON.getString(ApplicantTerm.APPLICANT_ID);
						_address = applicantJSON.getString(ApplicantTerm.ADDRESS);
						_cityCode = applicantJSON.getString(ApplicantTerm.CITYCODE);
						_cityName = applicantJSON.getString(ApplicantTerm.CITYNAME);
						_districtCode = applicantJSON.getString(ApplicantTerm.DISTRICTCODE);
						_districtName = applicantJSON.getString(ApplicantTerm.DISTRICTNAME);
						_wardCode = applicantJSON.getString(ApplicantTerm.WARDCODE);
						_wardName = applicantJSON.getString(ApplicantTerm.WARDNAME);
						_contactName = applicantJSON.getString(ApplicantTerm.CONTACTNAME);
						_contactTelNo = applicantJSON.getString(ApplicantTerm.CONTACTTELNO);
						_contactEmail = applicantJSON.getString(ApplicantTerm.CONTACTEMAIL);
						_applicantName = applicantJSON.getString(ApplicantTerm.APPLICANTNAME);
						_applicantIdType = applicantJSON.getString(ApplicantTerm.APPLICANTIDTYPE);
						_applicantIdNo = applicantJSON.getString(ApplicantTerm.APPLICANTIDNO);
						_applicantIdDate = applicantJSON.getString(ApplicantTerm.APPLICANTIDDATE);
						_representative = applicantJSON.getString(ApplicantTerm.REPRE_ENTERPRISE);


					} catch (PortalException e1) {
						_log.error(e1);
					}

				try {
					Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(dossier.getGroupId(),
							serviceContext.getUserId());

					JSONObject employeeJSON = JSONFactoryUtil
							.createJSONObject(JSONFactoryUtil.looseSerialize(employee));


					_employee_employeeNo = employeeJSON.getString(EmployeeTerm.EMPLOYEE_NO);
					_employee_fullName = employeeJSON.getString(EmployeeTerm.FULL_NAME);
					_employee_title = employeeJSON.getString(EmployeeTerm.TITLE);

				} catch (Exception e) {
					_log.info("NOT FOUN EMPLOYEE" + serviceContext.getUserId());
					_log.debug(e);
				}
				
				_govAgencyName = dossier.getGovAgencyName();
				_serviceName = dossier.getServiceName();
			}
			// process sampleData

			Map<String, Object> jsonMap = jsonToMap(result);

			for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {

				String value = String.valueOf(entry.getValue());

				if (value.startsWith(StringPool.POUND) && value.contains(StringPool.AT)) {
					String newString = value.substring(1);
					String[] stringSplit = newString.split(StringPool.AT);
					String variable = stringSplit[0];
					String paper = stringSplit[1];
					try {
						DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO_First(dossier.getDossierId(),
								paper, false, new DossierFileComparator(false, Field.CREATE_DATE, Date.class));

						if (Validator.isNotNull(dossierFile) && Validator.isNotNull(dossierFile.getFormData())
								&& dossierFile.getFormData().trim().length() != 0) {
							JSONObject jsonOtherData = JSONFactoryUtil.createJSONObject(dossierFile.getFormData());
							Map<String, Object> jsonOtherMap = jsonToMap(jsonOtherData);
							// _log.info("JSON other map: " +
							String myCHK = StringPool.BLANK;
							try {
								if (variable.contains(StringPool.COLON)) {
									String[] variableMuti = variable.split(StringPool.COLON);
									for (String string : variableMuti) {
										myCHK += StringPool.COMMA_AND_SPACE + jsonOtherMap.get(string).toString();
									}
									myCHK = myCHK.replaceFirst(StringPool.COMMA_AND_SPACE, StringPool.BLANK);
								} else {
									myCHK = jsonOtherMap.get(variable).toString();
								}
							} catch (Exception e) {
								_log.debug(e);
							}

							if (myCHK.startsWith(StringPool.POUND)) {
								jsonMap.put(entry.getKey(), StringPool.BLANK);
							} else {
								jsonMap.put(entry.getKey(), myCHK.toString());
							}
						} else {
							jsonMap.put(entry.getKey(), StringPool.BLANK);
						}
					} catch (SystemException e) {
						_log.error(e);
					}
				} else if (value.startsWith(StringPool.DOLLAR) && value.contains(StringPool.AT)) {
					//_log.info("START ACTIONCONFIG: "+value);
					String newString = value.substring(1);
					String[] stringSplit = newString.split(StringPool.AT);
					String variable = stringSplit[0];
					String paper = stringSplit[1];
					//_log.info("START paper: "+paper);
					try {
						DossierAction dossierAction = DossierActionLocalServiceUtil.getByDID_CODE_First(dossier.getDossierId(),
								paper, new DossierActionComparator(false, Field.CREATE_DATE, Date.class));
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
						_log.error(e);
					}
				}
			}

			for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
				if (entry.getValue().getClass().getName().contains(ConstantUtils.KEY_JSON_ARRAY)) {
					result.put(entry.getKey(), (JSONArray) entry.getValue());
				} else if (entry.getValue().getClass().getName().contains(ConstantUtils.KEY_JSON_OBJECT)) {
					result.put(entry.getKey(), (JSONObject) entry.getValue());
				} else {
					result.put(entry.getKey(), entry.getValue() + StringPool.BLANK);
				}
			}
		} catch (JSONException e) {
			_log.error(e);
		}

		_log.debug("START result: "+result);
		return result.toJSONString();
	}	
	private static final Log _log = LogFactoryUtil.getLog(AutoFillFormData.class);
}
