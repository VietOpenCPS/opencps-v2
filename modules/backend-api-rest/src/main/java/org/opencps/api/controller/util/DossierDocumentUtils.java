package org.opencps.api.controller.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.action.util.SpecialCharacterUtils;
import org.opencps.dossiermgt.constants.DossierPartTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.PaymentFileTerm;
import org.opencps.dossiermgt.constants.ServiceInfoTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierMark;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierMarkLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;

public class DossierDocumentUtils {

	//LamTV_ Mapping process dossier and formData
	public static JSONObject processMergeDossierFormData(Dossier dossier, JSONObject jsonData) {
		jsonData.put(DossierTerm.GOV_AGENCY_CODE, dossier.getGovAgencyCode());
		jsonData.put(DossierTerm.GOV_AGENCY_NAME, dossier.getGovAgencyName());
		jsonData.put(DossierTerm.APPLICANT_ID_NO, dossier.getApplicantIdNo());
		jsonData.put(DossierTerm.APPLICANT_ID_TYPE, dossier.getApplicantIdType());
		jsonData.put(DossierTerm.APPLICANT_ID_DATE,
				APIDateTimeUtils.convertDateToString(dossier.getApplicantIdDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		jsonData.put(DossierTerm.CITY_CODE, dossier.getCityCode());
		jsonData.put(DossierTerm.CITY_NAME, dossier.getCityName());
		jsonData.put(DossierTerm.DISTRICT_CODE, dossier.getDistrictCode());
		jsonData.put(DossierTerm.DISTRICT_NAME, dossier.getDistrictName());
		jsonData.put(DossierTerm.WARD_CODE, dossier.getWardCode());
		jsonData.put(DossierTerm.WARD_NAME, dossier.getWardName());
		jsonData.put(DossierTerm.DOSSIER_NO, dossier.getDossierNo());
		jsonData.put(DossierTerm.APPLICANT_NAME, dossier.getApplicantName());
		jsonData.put(DossierTerm.ADDRESS, dossier.getAddress());
		jsonData.put(DossierTerm.CONTACT_TEL_NO, dossier.getContactTelNo());
		jsonData.put(DossierTerm.CONTACT_EMAIL, dossier.getContactEmail());
		jsonData.put(DossierTerm.CONTACT_NAME, dossier.getContactName());
		jsonData.put(DossierTerm.DELEGATE_ADDRESS, dossier.getDelegateAddress());
		jsonData.put(DossierTerm.SERVICE_CODE, dossier.getServiceCode());
		jsonData.put(DossierTerm.SERVICE_NAME, dossier.getServiceName());
		jsonData.put(DossierTerm.SAMPLE_COUNT, dossier.getSampleCount());
		jsonData.put(DossierTerm.DURATION_UNIT, dossier.getDurationUnit());
		jsonData.put(DossierTerm.DURATION_COUNT, dossier.getDurationCount());
		jsonData.put(DossierTerm.SECRET_KEY, dossier.getPassword());
		jsonData.put(DossierTerm.RECEIVE_DATE,
				APIDateTimeUtils.convertDateToString(dossier.getReceiveDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		jsonData.put(DossierTerm.DELEGATE_NAME, dossier.getDelegateName());
		jsonData.put(DossierTerm.DELEGATE_EMAIL, dossier.getDelegateEmail());
		jsonData.put(DossierTerm.DELEGATE_TELNO, dossier.getDelegateTelNo());
		jsonData.put(DossierTerm.DOSSIER_NAME, dossier.getDossierName());
		jsonData.put(DossierTerm.VIA_POSTAL, dossier.getViaPostal());
		jsonData.put(DossierTerm.POSTAL_ADDRESS, dossier.getPostalAddress());

		// MetaData
		String metaData = dossier.getMetaData();
		if (Validator.isNotNull(metaData)) {
			try {
				JSONObject jsonMetaData = JSONFactoryUtil.createJSONObject(metaData);
				//
				Iterator<String> keys = jsonMetaData.keys();

				while (keys.hasNext()) {
					String key = keys.next();
					String value = jsonMetaData.getString(key);
					if (Validator.isNotNull(value)) {
						try {
							JSONArray valueObject = JSONFactoryUtil.createJSONArray(value);
							jsonData.put(key, valueObject);
						} catch (JSONException e) {
							_log.debug(e);
							try {
								JSONObject valueObject = JSONFactoryUtil.createJSONObject(value);
								jsonData.put(key, valueObject);
							} catch (JSONException e1) {
								_log.debug(e1);
								jsonData.put(key, value);
							}
						}
						
					}
				}
			} catch (JSONException e) {
				_log.debug(e);
			}
		}
		
		try {
			ServiceInfo service = ServiceInfoLocalServiceUtil.getByCode(dossier.getGroupId(), dossier.getServiceCode());
			jsonData.put(ServiceInfoTerm.FEE_TEXT, service != null ? service.getFeeText() : StringPool.BLANK);
			jsonData.put(ServiceInfoTerm.DURATION_TEXT, service != null ? service.getDurationText() : StringPool.BLANK);
		} catch (PortalException e1) {
			_log.debug(e1);
			jsonData.put(ServiceInfoTerm.FEE_TEXT, StringPool.BLANK);
			jsonData.put(ServiceInfoTerm.DURATION_TEXT, StringPool.BLANK);
		}
		//
		Date dueDate = dossier.getDueDate();
		if (dueDate != null) {
			ServiceProcess process = ServiceProcessLocalServiceUtil.getByG_PNO(dossier.getGroupId(),
					dossier.getProcessNo());
			if (process != null) {
				String dueDatePattern = process.getDueDatePattern();
				//_log.info("dueDatePattern: " + dueDatePattern);
				// _log.info("START DUEDATE TEST");
				if (Validator.isNotNull(dueDatePattern)) {
					//_log.info("START DUEDATE TEST");
					// _log.info("dueDatePattern: "+dueDatePattern);
					try {
						JSONObject jsonDueDate = JSONFactoryUtil.createJSONObject(dueDatePattern);
						//_log.info("jsonDueDate: " + jsonDueDate);
						if (jsonDueDate != null) {
							JSONObject hours = jsonDueDate.getJSONObject("hour");
							JSONObject processHours = jsonDueDate.getJSONObject("processHour");
							//_log.info("hours: " + hours);
							if (hours != null && hours.has("AM") && hours.has("PM")) {
								//_log.info("AM-PM: ");
								Calendar receiveCalendar = Calendar.getInstance();
								receiveCalendar.setTime(dossier.getReceiveDate());

								Calendar dueCalendar = Calendar.getInstance();
								//_log.info("hours: " + receiveCalendar.get(Calendar.HOUR_OF_DAY));
								if (receiveCalendar.get(Calendar.HOUR_OF_DAY) < 12) {
									dueCalendar.setTime(dossier.getDueDate());

									String hoursAfterNoon = hours.getString("AM");
									//_log.info("hoursAfterNoon: " + hoursAfterNoon);

									if (Validator.isNotNull(hoursAfterNoon)) {
										String[] splitAfter = StringUtil.split(hoursAfterNoon, StringPool.COLON);
										if (splitAfter != null) {
											dueCalendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(splitAfter[0]));
											dueCalendar.set(Calendar.MINUTE, Integer.valueOf(splitAfter[1]));
										}
									}
								} else {
									dueCalendar.setTime(dossier.getDueDate());
									String hoursAfterNoon = hours.getString("PM");
									if (Validator.isNotNull(hoursAfterNoon)) {
										String[] splitAfter = StringUtil.split(hoursAfterNoon, StringPool.COLON);
										if (splitAfter != null) {
											if (Integer.valueOf(splitAfter[0]) < 12) {
												dueCalendar.add(Calendar.DAY_OF_MONTH, 1);
												dueCalendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(splitAfter[0]));
												dueCalendar.set(Calendar.MINUTE, Integer.valueOf(splitAfter[1]));
											} else {
												//dueCalendar.add(Calendar.DAY_OF_MONTH, 1);
												dueCalendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(splitAfter[0]));
												dueCalendar.set(Calendar.MINUTE, Integer.valueOf(splitAfter[1]));
											}
										}
									}
								}
								jsonData.put(DossierTerm.DUE_DATE, APIDateTimeUtils
										.convertDateToString(dueCalendar.getTime(), APIDateTimeUtils._NORMAL_PARTTERN));
							} else if (processHours != null && processHours.has("startHour") && processHours.has("dueHour")) {
								//_log.info("STRART check new: ");
								Calendar receiveCalendar = Calendar.getInstance();
								receiveCalendar.setTime(dossier.getReceiveDate());
								//
								String receiveHour = processHours.getString("startHour");
								//_log.info("receiveHour: " + receiveHour);

								if (Validator.isNotNull(receiveHour)) {
									String[] splitHour = StringUtil.split(receiveHour, StringPool.COLON);
									if (splitHour != null) {
										int hourStart = GetterUtil.getInteger(splitHour[0]);
										if (receiveCalendar.get(Calendar.HOUR_OF_DAY) < hourStart) {
											String[] splitdueHour = StringUtil.split(processHours.getString("dueHour"),
													StringPool.COLON);
											Calendar dueCalendar = Calendar.getInstance();
											if (splitdueHour != null) {
												dueCalendar.set(Calendar.HOUR_OF_DAY,
														GetterUtil.getInteger(splitdueHour[0]));
												dueCalendar.set(Calendar.MINUTE,
														GetterUtil.getInteger(splitdueHour[1]));
											} else {
												jsonData.put(DossierTerm.DUE_DATE, APIDateTimeUtils.convertDateToString(
														dossier.getDueDate(), APIDateTimeUtils._NORMAL_PARTTERN));
											}
											jsonData.put(DossierTerm.DUE_DATE, APIDateTimeUtils.convertDateToString(
													dueCalendar.getTime(), APIDateTimeUtils._NORMAL_PARTTERN));
										} else {
											jsonData.put(DossierTerm.DUE_DATE, APIDateTimeUtils.convertDateToString(
													dossier.getDueDate(), APIDateTimeUtils._NORMAL_PARTTERN));
										}
									}
								}
							} else {
								jsonData.put(DossierTerm.DUE_DATE, APIDateTimeUtils
										.convertDateToString(dossier.getDueDate(), APIDateTimeUtils._NORMAL_PARTTERN));
							}
						} else {
							jsonData.put(DossierTerm.DUE_DATE, APIDateTimeUtils
									.convertDateToString(dossier.getDueDate(), APIDateTimeUtils._NORMAL_PARTTERN));
						}
					} catch (JSONException e) {
						_log.error(e);
						jsonData.put(DossierTerm.DUE_DATE, APIDateTimeUtils.convertDateToString(dossier.getDueDate(),
								APIDateTimeUtils._NORMAL_PARTTERN));
					}
				} else {
					jsonData.put(DossierTerm.DUE_DATE, APIDateTimeUtils.convertDateToString(dossier.getDueDate(),
							APIDateTimeUtils._NORMAL_PARTTERN));
				}
			} else {
				jsonData.put(DossierTerm.DUE_DATE,
						APIDateTimeUtils.convertDateToString(dossier.getDueDate(), APIDateTimeUtils._NORMAL_PARTTERN));
			}
		} else {
			jsonData.put(DossierTerm.DUE_DATE, StringPool.BLANK);
		}
		//
		jsonData.put(DossierTerm.POSTAL_ADDRESS, dossier.getPostalAddress());
		jsonData.put(DossierTerm.COUNTER, dossier.getCounter());
		jsonData.put(DossierTerm.REGISTER_BOOK_CODE, dossier.getRegisterBookCode());
		jsonData.put(DossierTerm.SECRET, dossier.getPassword());
		jsonData.put(DossierTerm.BRIEF_NOTE, dossier.getBriefNote());
		jsonData.put(DossierTerm.DOSSIER_ID, dossier.getDossierId());
		//
		long groupId = dossier.getGroupId();
		JSONArray dossierMarkArr = JSONFactoryUtil.createJSONArray();
		long dossierId = dossier.getDossierId();
		String templateNo = dossier.getDossierTemplateNo();
		List<DossierMark> dossierMarkList = DossierMarkLocalServiceUtil.getDossierMarksByFileMark(groupId, dossierId, 0);
		if (dossierMarkList != null && dossierMarkList.size() > 0) {
			JSONObject jsonMark = null;
			String partNo;
			for (DossierMark dossierMark : dossierMarkList) {
				jsonMark = JSONFactoryUtil.createJSONObject();
				partNo = dossierMark.getDossierPartNo();
				if (Validator.isNotNull(partNo)) {
					DossierPart part = DossierPartLocalServiceUtil.getByTempAndPartNo(groupId, templateNo, partNo);
					if (part != null) {
						jsonMark.put(DossierPartTerm.DOSSIERPART_ID, part.getDossierPartId());
						jsonMark.put(DossierPartTerm.PART_NAME, part.getPartName());
						jsonMark.put(DossierPartTerm.PART_TIP, part.getPartTip());
						jsonMark.put(DossierPartTerm.PART_TYPE, part.getPartType());
					}
				}
				jsonMark.put(DossierPartTerm.PART_NO, partNo);
				jsonMark.put(DossierPartTerm.FILE_MARK, dossierMark.getFileMark());
				jsonMark.put(DossierPartTerm.FILE_CHECK, dossierMark.getFileCheck());
				jsonMark.put(DossierPartTerm.FILE_COMMENT, dossierMark.getFileComment());
				jsonMark.put(DossierPartTerm.RECORD_COUNT, dossierMark.getRecordCount());
//				String strDossierMark = JSONFactoryUtil.looseSerialize(dossierMark);
				dossierMarkArr.put(jsonMark);
			}
		}
		
		//Hot fix TP99
		DossierMark dossierMark = DossierMarkLocalServiceUtil.getDossierMarkbyDossierId(groupId, dossierId, "TP99");
		if (dossierMark != null) {
			JSONObject jsonMark = null;
			String partNo = dossierMark.getDossierPartNo();
			if (Validator.isNotNull(partNo)) {
				List<DossierFile> fileList = DossierFileLocalServiceUtil.getDossierFileByDID_DPNO(dossierId, partNo, false);
				DossierPart part = DossierPartLocalServiceUtil.getByTempAndPartNo(groupId, templateNo, partNo);
				if (fileList != null && part != null) {
					for (DossierFile dossierFile : fileList) {
						jsonMark = JSONFactoryUtil.createJSONObject();
						jsonMark.put(DossierPartTerm.PART_NAME, dossierFile.getDisplayName());
						jsonMark.put(DossierPartTerm.DOSSIERPART_ID, part.getDossierPartId());
						jsonMark.put(DossierPartTerm.PART_TIP, part.getPartTip());
						jsonMark.put(DossierPartTerm.PART_TYPE, part.getPartType());
						jsonMark.put(DossierPartTerm.PART_NO, partNo);
						jsonMark.put(DossierPartTerm.FILE_MARK, dossierMark.getFileMark());
						jsonMark.put(DossierPartTerm.FILE_CHECK, dossierMark.getFileCheck());
						jsonMark.put(DossierPartTerm.FILE_COMMENT, dossierMark.getFileComment());
						jsonMark.put(DossierPartTerm.RECORD_COUNT, dossierMark.getRecordCount());
//						String strDossierMark = JSONFactoryUtil.looseSerialize(dossierMark);
						dossierMarkArr.put(jsonMark);
					}
				}
			}
		}
		
		jsonData.put(DossierTerm.DOSSIER_MARKS, dossierMarkArr);

		PaymentFile payment = PaymentFileLocalServiceUtil.getByDossierId(groupId, dossierId);
		if (payment != null) {
			jsonData.put(PaymentFileTerm.ADVANCE_AMOUNT, payment.getAdvanceAmount());
			jsonData.put(PaymentFileTerm.PAYMENT_AMOUNT, payment.getPaymentAmount());
			jsonData.put(PaymentFileTerm.PAYMENT_FEE, payment.getPaymentFee());
			jsonData.put(PaymentFileTerm.SERVICE_AMOUNT, payment.getServiceAmount());
			jsonData.put(PaymentFileTerm.SHIP_AMOUNT, payment.getShipAmount());
		}
		
		if (dossier.getOriginality() == DossierTerm.ORIGINALITY_HOSONHOM) {
			JSONArray groupDossierArr = JSONFactoryUtil.createJSONArray();
			List<Dossier> lstDossiers = DossierLocalServiceUtil.findByG_GDID(groupId, dossier.getDossierId());
			for (Dossier d : lstDossiers) {
				JSONObject dObject = JSONFactoryUtil.createJSONObject();
				dObject.put(DossierTerm.DOSSIER_NO, d.getDossierNo());
				dObject.put(DossierTerm.APPLICANT_NAME, d.getApplicantName());
				dObject.put(DossierTerm.ADDRESS, d.getAddress());
				dObject.put(DossierTerm.CONTACT_TEL_NO, d.getContactTelNo());
				dObject.put(DossierTerm.CONTACT_EMAIL, d.getContactEmail());
				dObject.put(DossierTerm.CONTACT_NAME, d.getContactName());
				dObject.put(DossierTerm.DELEGATE_ADDRESS, d.getDelegateAddress());
				dObject.put(DossierTerm.SERVICE_CODE, d.getServiceCode());
				dObject.put(DossierTerm.SERVICE_NAME, d.getServiceName());
				dObject.put(DossierTerm.SAMPLE_COUNT, d.getSampleCount());
				dObject.put(DossierTerm.DURATION_UNIT, d.getDurationUnit());
				dObject.put(DossierTerm.DURATION_COUNT, d.getDurationCount());
				dObject.put(DossierTerm.SECRET_KEY, d.getPassword());
				dObject.put(DossierTerm.RECEIVE_DATE,
						APIDateTimeUtils.convertDateToString(d.getReceiveDate(), APIDateTimeUtils._NORMAL_PARTTERN));
				dObject.put(DossierTerm.DELEGATE_NAME, d.getDelegateName());
				dObject.put(DossierTerm.DELEGATE_EMAIL, d.getDelegateEmail());
				dObject.put(DossierTerm.DELEGATE_TELNO, d.getDelegateTelNo());
				dObject.put(DossierTerm.DOSSIER_NAME, d.getDossierName());
				dObject.put(DossierTerm.VIA_POSTAL, d.getViaPostal());
				dObject.put(DossierTerm.POSTAL_ADDRESS, d.getPostalAddress());
	
				groupDossierArr.put(dObject);
			}
			jsonData.put(DossierTerm.GROUP_DOSSIERS, groupDossierArr);
		}
		return jsonData;
	}

	protected List<Object[]> parseJSONObject(List<Object[]> keyValues, JSONObject json) {

//		List<Object[]> objects = new ArrayList<Object[]>();
		if (json != null) {
			Iterator<String> itr = json.keys();
			while (itr.hasNext()) {
				String key = itr.next();
				String strObject = String.valueOf(json.get(key));
				// check json
				try {
					JSONArray valueObject = JSONFactoryUtil.createJSONArray(strObject);
					Object[] keyValue = new Object[2];
					keyValue[0] = key;
					if (Validator.isNotNull(valueObject.toString())) {
						keyValue[1] = SpecialCharacterUtils.splitSpecial(valueObject.toString());
//						keyValue[1] = valueObject.toString().replaceAll(Pattern.quote("/"), "_").replaceAll(Pattern.quote("-"), "_");
					} else {
						keyValue[1] = valueObject.toString();
					}
					keyValues.add(keyValue);
					parseJSONObjectIndex(keyValues, json.getJSONObject(key), key);
				} catch (JSONException e) {
					_log.debug(e);
					// string
					Object[] keyValue = new Object[2];
					keyValue[0] = key;
					if (Validator.isNotNull(strObject.toString())) {
//						keyValue[1] = strObject.toString().replaceAll(Pattern.quote("/"), "_").replaceAll(Pattern.quote("-"), "_");
						keyValue[1] = SpecialCharacterUtils.splitSpecial(strObject.toString());
					} else {
						keyValue[1] = strObject.toString();
					}
					keyValues.add(keyValue);
				}
			}
		}

		return keyValues;
	}

	protected List<Object[]> parseJSONObjectIndex(List<Object[]> keyValues, JSONObject json, String keyJson) {

		if (json != null) {
			Iterator<String> itr = json.keys();
			while (itr.hasNext()) {
				String key = itr.next();
				String strObject = String.valueOf(json.get(key));
				// check json
				try {
					JSONObject valueObject = JSONFactoryUtil.createJSONObject(strObject);
					Object[] keyValue = new Object[2];
					keyValue[0] = keyJson + "@" + key;
					if (Validator.isNotNull(valueObject.toString())) {
//						keyValue[1] = valueObject.toString().replaceAll(Pattern.quote("/"), "_").replaceAll(Pattern.quote("-"), "_");
						keyValue[1] = SpecialCharacterUtils.splitSpecial(valueObject.toString());
					} else {
						keyValue[1] = valueObject.toString();
					}
					keyValues.add(keyValue);
					parseJSONObjectIndex(keyValues, json.getJSONObject(key), keyValue[0].toString());
				} catch (JSONException e) {
					_log.error(e);
					// string
					Object[] keyValue = new Object[2];
					keyValue[0] = keyJson + "@" + key;
					if (Validator.isNotNull(strObject.toString())) {
//						keyValue[1] = strObject.toString().replaceAll(Pattern.quote("/"), "_").replaceAll(Pattern.quote("-"), "_");
						keyValue[1] = SpecialCharacterUtils.splitSpecial(strObject.toString());
					} else {
						keyValue[1] = strObject.toString();
					}
					keyValues.add(keyValue);
				}
			}
		}

		return keyValues;
	}

	private static Log _log = LogFactoryUtil.getLog(DossierDocumentUtils.class);
}
