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
import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.*;
import org.opencps.dossiermgt.service.*;
import org.opencps.dossiermgt.service.comparator.DossierActionComparator;
import org.opencps.dossiermgt.service.comparator.DossierFileComparator;
import org.opencps.usermgt.action.ApplicantActions;
import org.opencps.usermgt.action.impl.ApplicantActionsImpl;
import org.opencps.usermgt.constants.ApplicantTerm;
import org.opencps.usermgt.constants.EmployeeTerm;
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
			String _sampleCount = StringPool.BLANK;
			String _documentDate = StringPool.BLANK;
			String _documentNo = StringPool.BLANK;
			String _govAgencyCode = StringPool.BLANK;
			String _deliverableCode = StringPool.BLANK;

			SimpleDateFormat sfd = new SimpleDateFormat(APIDateTimeUtils._NORMAL_DATE_TIME);

			_curDate = sfd.format(new Date());

			if (Validator.isNotNull(dossier)) {
				_receiveDate = Validator.isNotNull(dossier.getReceiveDate()) ? dossier.getReceiveDate().toGMTString()
						: StringPool.BLANK;
				_dossierNo = dossier.getDossierNo();
				_govAgencyCode = dossier.getGovAgencyCode();

				// get data applicant or employee
				ApplicantActions applicantActions = new ApplicantActionsImpl();

				try {

					Registration registration = RegistrationLocalServiceUtil
							.getLastSubmittingByUser(dossier.getGroupId(), serviceContext.getUserId(), true);

					if (Validator.isNotNull(registration)) {

						JSONObject applicantJSON = JSONFactoryUtil
								.createJSONObject(JSONFactoryUtil.looseSerialize(registration));

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
//						_applicantIdDate = applicantJSON.getString("applicantIdDate");
						_applicantIdDate = applicantJSON.getString(ApplicantTerm.REPRESENTATIVE_ENTERPRISE);

					} else {
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
						_representative = applicantJSON.getString(ApplicantTerm.REPRESENTATIVE_ENTERPRISE);

					}

				} catch (PortalException e1) {
					// TODO Auto-generated catch block
//					e1.printStackTrace();
					_log.error(e1);
				}

				try {
					Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(dossier.getGroupId(),
							serviceContext.getUserId());

//					Deliverable deliverable = DeliverableLocalServiceUtil.getByF_GID_DI_STATE(dossier.getGroupId(), dossier.getDossierId(), DeliverableTerm.DELIVERABLE_STATE_VALID_INT);
					List<DossierFile> lstFile = DossierFileLocalServiceUtil.findByDID_GROUP(dossier.getGroupId(), dossierId);
					if(lstFile !=null && !lstFile.isEmpty()){
						for(DossierFile item : lstFile){
							if(Validator.isNotNull(item.getDeliverableCode())){
								_deliverableCode = item.getDeliverableCode();
								break;
							}
						}
					}
					// _log.info("GET EMPLOYEE ID ____" +
					// serviceContext.getUserId());

					JSONObject employeeJSON = JSONFactoryUtil
							.createJSONObject(JSONFactoryUtil.looseSerialize(employee));

					// _log.info("GET EMPLOYEE ____");

					// _log.info(employeeJSON);

					_employee_employeeNo = employeeJSON.getString(EmployeeTerm.EMPLOYEE_NO);
					_employee_fullName = employeeJSON.getString(EmployeeTerm.FULL_NAME);
					_employee_title = employeeJSON.getString(EmployeeTerm.TITLE);

				} catch (Exception e) {
					_log.info("NOT FOUN EMPLOYEE" + serviceContext.getUserId());
					_log.debug(e);
					//_log.error(e);
				}
				
				_govAgencyName = dossier.getGovAgencyName();
				_serviceName = dossier.getServiceName();
				_applicantName = dossier.getApplicantName();
				_sampleCount = String.valueOf(dossier.getSampleCount());
				_wardCode = dossier.getWardCode();
				_wardName = dossier.getWardName();
				_districtCode = dossier.getDistrictCode();
				_districtName = dossier.getDistrictName();
				_cityCode = dossier.getCityCode();
				_cityName = dossier.getCityName();

				if(Validator.isNotNull(dossier.getDocumentDate())) {
					_documentDate = APIDateTimeUtils.convertDateToString(dossier.getDocumentDate(), APIDateTimeUtils._NORMAL_PARTTERN);
				}
				_documentNo = dossier.getDocumentNo();
			}
			// process sampleData
//			if (Validator.isNull(sampleData)) {
//				sampleData = "{}";
//			}

			Map<String, Object> jsonMap = jsonToMap(result);

			for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {

				String value = String.valueOf(entry.getValue());
				_log.info("VALUE KEYYYY: " + value);
				if (value.startsWith(StringPool.UNDERLINE) && !value.contains(StringPool.COLON)) {

					if ((StringPool.UNDERLINE + ApplicantTerm.SUBJECT_NAME).equals(value)) {
						jsonMap.put(entry.getKey(), _subjectName);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.SUBJECT_ID).equals(value)) {
						jsonMap.put(entry.getKey(), _subjectId);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.ADDRESS).equals(value)) {
						jsonMap.put(entry.getKey(), _address);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.CITYCODE).equals(value)) {
						jsonMap.put(entry.getKey(), _cityCode);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.CITYNAME).equals(value)) {
						jsonMap.put(entry.getKey(), _cityName);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.DISTRICTCODE).equals(value)) {
						jsonMap.put(entry.getKey(), _districtCode);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.DISTRICTNAME).equals(value)) {
						jsonMap.put(entry.getKey(), _districtName);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.WARDCODE).equals(value)) {
						jsonMap.put(entry.getKey(), _wardCode);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.WARDNAME).equals(value)) {
						jsonMap.put(entry.getKey(), _wardName);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.CONTACTNAME).equals(value)) {
						jsonMap.put(entry.getKey(), _contactName);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.CONTACTTELNO).equals(value)) {
						jsonMap.put(entry.getKey(), _contactTelNo);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.CONTACTEMAIL).equals(value)) {
						jsonMap.put(entry.getKey(), _contactEmail);
					} else if ((StringPool.UNDERLINE + DossierTerm.FROM_RECEIVEDATE).equals(value)) {
						jsonMap.put(entry.getKey(), _receiveDate);
					} else if ((StringPool.UNDERLINE + DossierTerm.DOSSIER_NO).equals(value)) {
						jsonMap.put(entry.getKey(), _dossierNo);
					} else if ((StringPool.UNDERLINE + DossierTerm.GOV_AGENCY_CODE).equals(value)) {
						jsonMap.put(entry.getKey(), _govAgencyCode);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.EMPLOYEE_EMPLOYEE_NO).equals(value)) {
						jsonMap.put(entry.getKey(), _employee_employeeNo);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.EMPLOYEE_FULL_NAME).equals(value)) {
						jsonMap.put(entry.getKey(), _employee_fullName);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.EMPLOYEE_TITLE).equals(value)) {
						jsonMap.put(entry.getKey(), _employee_title);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.APPLICANTNAME).equals(value)) {
						jsonMap.put(entry.getKey(), _applicantName);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.APPLICANTIDTYPE).equals(value)) {
						jsonMap.put(entry.getKey(), _applicantIdType);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.APPLICANTIDNO).equals(value)) {
						jsonMap.put(entry.getKey(), _applicantIdNo);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.APPLICANTIDDATE).equals(value)) {
						jsonMap.put(entry.getKey(), _applicantIdDate);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.CUR_DATE).equals(value)) {
						jsonMap.put(entry.getKey(), _curDate);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.REPRESENTATIVE).equals(value)) {
						jsonMap.put(entry.getKey(), _representative);
					} else if ((StringPool.UNDERLINE + DossierTerm.GOV_AGENCY_NAME).equals(value)) {
						jsonMap.put(entry.getKey(), _govAgencyName);
					} else if ((StringPool.UNDERLINE + DossierTerm.SERVICE_NAME).equals(value)) {
						jsonMap.put(entry.getKey(), _serviceName);
					}else if((StringPool.UNDERLINE + DossierTerm.SAMPLE_COUNT).equals(value)){
						jsonMap.put(entry.getKey(), _sampleCount);
					}else if((StringPool.UNDERLINE + DossierTerm.DOCUMENT_DATE).equals(value)){
						jsonMap.put(entry.getKey(), _documentDate);
					}else if((StringPool.UNDERLINE + DossierTerm.DOCUMENT_NO).equals(value)){
						jsonMap.put(entry.getKey(), _documentNo);
					}else if((StringPool.UNDERLINE + DossierTerm.DELIVERABLE_CODE).equals(value)){
						jsonMap.put(entry.getKey(), _deliverableCode);
					}
//					if(value.contains(StringPool.UNDERLINE + DossierTerm.META_DATA)){
					if (value.startsWith(StringPool.UNDERLINE) && value.contains(DossierTerm.META_DATA)){
						String metaDataV = value.substring(value.indexOf(".") + 1, value.length());
						if(Validator.isNotNull(dossier.getMetaData())) {
							try {
								JSONObject jsonMetaData = JSONFactoryUtil.createJSONObject(dossier.getMetaData());
								Iterator<String> keys = jsonMetaData.keys();
								// Key: key || value
								boolean check = true;
								while (keys.hasNext() && check) {
									String key = keys.next();
									String valueMeta = jsonMetaData.getString(key);
									if(key.equals(metaDataV)){
										jsonMap.put(entry.getKey(), valueMeta);
										check = false;
									}
								}
								if (check) {
									jsonMap.put(entry.getKey(),  StringPool.BLANK);
								}
							} catch (JSONException e) {
								_log.debug(e.getMessage());
								e.printStackTrace();
							}
						}
					}
				} else if (value.startsWith(StringPool.UNDERLINE) && value.contains(StringPool.COLON)) {
					String resultBinding = StringPool.BLANK;
					String[] valueSplit = value.split(StringPool.COLON);
					for (String string : valueSplit) {
						if ((StringPool.UNDERLINE + ApplicantTerm.SUBJECT_NAME).equals(string)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _subjectName;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.SUBJECT_ID).equals(string)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _subjectId;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.ADDRESS).equals(string)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _address;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.WARDCODE).equals(string)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _wardCode;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.WARDNAME).equals(string)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _wardName;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.DISTRICTCODE).equals(string)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _districtCode;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.DISTRICTNAME).equals(string)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _districtName;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.CITYCODE).equals(string)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _cityCode;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.CITYNAME).equals(string)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _cityName;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.CONTACTNAME).equals(string)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _contactName;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.CONTACTTELNO).equals(string)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _contactTelNo;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.CONTACTEMAIL).equals(string)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _contactEmail;
						} else if ((StringPool.UNDERLINE + DossierTerm.RECEIVE_DATE).equals(value)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _receiveDate;
						} else if ((StringPool.UNDERLINE + DossierTerm.DOSSIER_NO).equals(value)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _dossierNo;
						}else if ((StringPool.UNDERLINE + DossierTerm.GOV_AGENCY_CODE).equals(value)) {
								resultBinding += StringPool.COMMA_AND_SPACE + _govAgencyCode;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.EMPLOYEE_EMPLOYEE_NO).equals(value)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _employee_employeeNo;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.EMPLOYEE_FULL_NAME).equals(value)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _employee_fullName;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.EMPLOYEE_TITLE).equals(value)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _employee_title;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.APPLICANTNAME).equals(value)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _applicantName;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.APPLICANTIDTYPE).equals(value)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _applicantIdType;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.APPLICANTIDNO).equals(value)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _applicantIdNo;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.APPLICANTIDDATE).equals(value)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _applicantIdDate;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.CUR_DATE).equals(value)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _curDate;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.REPRESENTATIVE).equals(value)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _representative;
						} else if ((StringPool.UNDERLINE + DossierTerm.GOV_AGENCY_NAME).equals(value)) {
							jsonMap.put(entry.getKey(), _govAgencyName);
						} else if ((StringPool.UNDERLINE + DossierTerm.SERVICE_NAME).equals(value)) {
							jsonMap.put(entry.getKey(), _serviceName);
						}else if((StringPool.UNDERLINE + DossierTerm.SAMPLE_COUNT).equals(value)){
							jsonMap.put(entry.getKey(), _sampleCount);
						}else if((StringPool.UNDERLINE + DossierTerm.DOCUMENT_DATE).equals(value)){
							jsonMap.put(entry.getKey(), _documentDate);
						}else if((StringPool.UNDERLINE + DossierTerm.DOCUMENT_NO).equals(value)){
							jsonMap.put(entry.getKey(), _documentNo);
						}else if((StringPool.UNDERLINE + DossierTerm.DELIVERABLE_CODE).equals(value)){
							jsonMap.put(entry.getKey(), _deliverableCode);
						}
					}

					jsonMap.put(entry.getKey(), resultBinding.replaceFirst(StringPool.COMMA_AND_SPACE, StringPool.BLANK));

				} else if (value.startsWith(StringPool.POUND) && value.contains(StringPool.AT)) {
					String newString = value.substring(1);
					String[] stringSplit = newString.split(StringPool.AT);
					String variable = stringSplit[0];
					String paper = stringSplit[1];
					try {
						DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO_First(dossierId,
								paper, false, new DossierFileComparator(false, Field.CREATE_DATE, Date.class));

						if (Validator.isNotNull(dossierFile) && Validator.isNotNull(dossierFile.getFormData())
								&& dossierFile.getFormData().trim().length() != 0) {
							JSONObject jsonOtherData = JSONFactoryUtil.createJSONObject(dossierFile.getFormData());
							Map<String, Object> jsonOtherMap = jsonToMap(jsonOtherData);
							// _log.info("JSON other map: " +
							// Arrays.toString(jsonOtherMap.entrySet().toArray()));
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
								// TODO: handle exception
								_log.debug(e);
								//_log.error(e);
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
//						e.printStackTrace();
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
			String _deliverableCode = StringPool.BLANK;

			SimpleDateFormat sfd = new SimpleDateFormat(APIDateTimeUtils._NORMAL_DATE_TIME);

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
//						_applicantIdDate = applicantJSON.getString(ApplicantTerm.applicantIdDate);
						_applicantIdDate = applicantJSON.getString(ApplicantTerm.REPRESENTATIVE_ENTERPRISE);

					} else {
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
						_representative = applicantJSON.getString(ApplicantTerm.REPRESENTATIVE_ENTERPRISE);

					}

				} catch (PortalException e1) {
					// TODO Auto-generated catch block
//					e1.printStackTrace();
					_log.error(e1);
				}

				try {
					Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(dossier.getGroupId(),
							serviceContext.getUserId());

					Deliverable deliverable = DeliverableLocalServiceUtil.getByF_GID_DI_STATE(dossier.getGroupId(), dossier.getDossierId(), DeliverableTerm.DELIVERABLE_STATE_VALID_INT);
					if(Validator.isNotNull(deliverable)){
						_deliverableCode = deliverable.getDeliverableCode();
					}
					// _log.info("GET EMPLOYEE ID ____" +
					// serviceContext.getUserId());

					JSONObject employeeJSON = JSONFactoryUtil
							.createJSONObject(JSONFactoryUtil.looseSerialize(employee));

					// _log.info("GET EMPLOYEE ____");

					// _log.info(employeeJSON);

					_employee_employeeNo = employeeJSON.getString(EmployeeTerm.EMPLOYEE_NO);
					_employee_fullName = employeeJSON.getString(EmployeeTerm.FULL_NAME);
					_employee_title = employeeJSON.getString(EmployeeTerm.TITLE);

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

				if (value.startsWith(StringPool.UNDERLINE) && !value.contains(StringPool.COLON)) {

					if ((StringPool.UNDERLINE + ApplicantTerm.SUBJECT_NAME).equals(value)) {
						jsonMap.put(entry.getKey(), _subjectName);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.SUBJECT_ID).equals(value)) {
						jsonMap.put(entry.getKey(), _subjectId);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.ADDRESS).equals(value)) {
						jsonMap.put(entry.getKey(), _address);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.CITYCODE).equals(value)) {
						jsonMap.put(entry.getKey(), _cityCode);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.CITYNAME).equals(value)) {
						jsonMap.put(entry.getKey(), _cityName);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.DISTRICTCODE).equals(value)) {
						jsonMap.put(entry.getKey(), _districtCode);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.DISTRICTNAME).equals(value)) {
						jsonMap.put(entry.getKey(), _districtName);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.WARDCODE).equals(value)) {
						jsonMap.put(entry.getKey(), _wardCode);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.WARDNAME).equals(value)) {
						jsonMap.put(entry.getKey(), _wardName);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.CONTACTNAME).equals(value)) {
						jsonMap.put(entry.getKey(), _contactName);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.CONTACTTELNO).equals(value)) {
						jsonMap.put(entry.getKey(), _contactTelNo);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.CONTACTEMAIL).equals(value)) {
						jsonMap.put(entry.getKey(), _contactEmail);
					} else if ((StringPool.UNDERLINE + DossierTerm.RECEIVE_DATE).equals(value)) {
						jsonMap.put(entry.getKey(), _receiveDate);
					} else if ((StringPool.UNDERLINE + DossierTerm.DOSSIER_NO).equals(value)) {
						jsonMap.put(entry.getKey(), _dossierNo);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.EMPLOYEE_EMPLOYEE_NO).equals(value)) {
						jsonMap.put(entry.getKey(), _employee_employeeNo);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.EMPLOYEE_FULL_NAME).equals(value)) {
						jsonMap.put(entry.getKey(), _employee_fullName);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.EMPLOYEE_TITLE).equals(value)) {
						jsonMap.put(entry.getKey(), _employee_title);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.APPLICANTNAME).equals(value)) {
						jsonMap.put(entry.getKey(), _applicantName);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.APPLICANTIDTYPE).equals(value)) {
						jsonMap.put(entry.getKey(), _applicantIdType);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.APPLICANTIDNO).equals(value)) {
						jsonMap.put(entry.getKey(), _applicantIdNo);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.APPLICANTIDDATE).equals(value)) {
						jsonMap.put(entry.getKey(), _applicantIdDate);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.CUR_DATE).equals(value)) {
						jsonMap.put(entry.getKey(), _curDate);
					} else if ((StringPool.UNDERLINE + ApplicantTerm.REPRESENTATIVE).equals(value)) {
						jsonMap.put(entry.getKey(), _representative);
					} else if ((StringPool.UNDERLINE + DossierTerm.GOV_AGENCY_NAME).equals(value)) {
						jsonMap.put(entry.getKey(), _govAgencyName);
					} else if ((StringPool.UNDERLINE + DossierTerm.SERVICE_NAME).equals(value)) {
						jsonMap.put(entry.getKey(), _serviceName);
					}else if((StringPool.UNDERLINE + DossierTerm.DELIVERABLE_CODE).equals(value)){
						jsonMap.put(entry.getKey(), _deliverableCode);
					}

				} else if (value.startsWith(StringPool.UNDERLINE) && value.contains(StringPool.COLON)) {
					String resultBinding = StringPool.BLANK;
					String[] valueSplit = value.split(StringPool.COLON);
					for (String string : valueSplit) {
						if ((StringPool.UNDERLINE + ApplicantTerm.SUBJECT_NAME).equals(string)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _subjectName;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.SUBJECT_ID).equals(string)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _subjectId;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.ADDRESS).equals(string)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _address;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.WARDCODE).equals(string)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _wardCode;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.WARDNAME).equals(string)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _wardName;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.DISTRICTCODE).equals(string)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _districtCode;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.DISTRICTNAME).equals(string)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _districtName;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.CITYCODE).equals(string)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _cityCode;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.CITYNAME).equals(string)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _cityName;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.CONTACTNAME).equals(string)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _contactName;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.CONTACTTELNO).equals(string)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _contactTelNo;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.CONTACTEMAIL).equals(string)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _contactEmail;
						} else if ((StringPool.UNDERLINE + DossierTerm.RECEIVE_DATE).equals(value)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _receiveDate;
						} else if ((StringPool.UNDERLINE + DossierTerm.DOSSIER_NO).equals(value)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _dossierNo;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.EMPLOYEE_EMPLOYEE_NO).equals(value)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _employee_employeeNo;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.EMPLOYEE_FULL_NAME).equals(value)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _employee_fullName;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.EMPLOYEE_TITLE).equals(value)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _employee_title;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.APPLICANTNAME).equals(value)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _applicantName;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.APPLICANTIDTYPE).equals(value)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _applicantIdType;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.APPLICANTIDNO).equals(value)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _applicantIdNo;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.APPLICANTIDDATE).equals(value)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _applicantIdDate;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.CUR_DATE).equals(value)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _curDate;
						} else if ((StringPool.UNDERLINE + ApplicantTerm.REPRESENTATIVE).equals(value)) {
							resultBinding += StringPool.COMMA_AND_SPACE + _representative;
						} else if ((StringPool.UNDERLINE + DossierTerm.GOV_AGENCY_NAME).equals(value)) {
							jsonMap.put(entry.getKey(), _govAgencyName);
						} else if ((StringPool.UNDERLINE + DossierTerm.SERVICE_NAME).equals(value)) {
							jsonMap.put(entry.getKey(), _serviceName);
						}else if((StringPool.UNDERLINE + DossierTerm.DELIVERABLE_CODE).equals(value)){
							jsonMap.put(entry.getKey(), _deliverableCode);
						}
					}

					jsonMap.put(entry.getKey(), resultBinding.replaceFirst(StringPool.COMMA_AND_SPACE, StringPool.BLANK));

				} else if (value.startsWith(StringPool.POUND) && value.contains(StringPool.AT)) {
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
							// Arrays.toString(jsonOtherMap.entrySet().toArray()));
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
								// TODO: handle exception
								_log.debug(e);
								//_log.error(e);
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
//						e.printStackTrace();
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
//			e.printStackTrace();
		}

		_log.debug("START result: "+result);
		return result.toJSONString();
	}	
	private static final Log _log = LogFactoryUtil.getLog(AutoFillFormData.class);
}
