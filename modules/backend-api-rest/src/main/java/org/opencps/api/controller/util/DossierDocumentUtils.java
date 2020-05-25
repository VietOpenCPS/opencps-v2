package org.opencps.api.controller.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.opencps.api.constants.ConstantUtils;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.action.util.SpecialCharacterUtils;
import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.constants.DossierActionUserTerm;
import org.opencps.dossiermgt.constants.DossierDocumentTerm;
import org.opencps.dossiermgt.constants.DossierLogTerm;
import org.opencps.dossiermgt.constants.DossierPartTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.NotarizationTerm;
import org.opencps.dossiermgt.constants.PaymentFileTerm;
import org.opencps.dossiermgt.constants.ProcessSequenceTerm;
import org.opencps.dossiermgt.constants.ServiceInfoTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierActionUser;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierLog;
import org.opencps.dossiermgt.model.DossierMark;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.Notarization;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.model.ProcessSequence;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionUserLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLogLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierMarkLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.dossiermgt.service.NotarizationLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessSequenceLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

public class DossierDocumentUtils {
	private static final String GOVERNMENT_AGENCY = "GOVERNMENT_AGENCY";
	private static final String BN_TELEPHONE = "BN_telephone";
	private static final String BN_ADDRESS = "BN_address";
	private static final String BN_EMAIL = "BN_email";

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
		jsonData.put(DossierTerm.APPLICANT_NOTE, dossier.getApplicantNote());

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
			DictCollection govCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(GOVERNMENT_AGENCY, dossier.getGroupId());
			if (govCollection != null) {
				DictItem govAgenItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(dossier.getGovAgencyCode(), govCollection.getDictCollectionId(), dossier.getGroupId());
				String metaDataItem = govAgenItem.getMetaData();
				try {
					JSONObject metaObj = JSONFactoryUtil.createJSONObject(metaDataItem);
					if (govAgenItem != null) {
						if (metaObj.has(BN_TELEPHONE)) {
							jsonData.put(BN_TELEPHONE, metaObj.getString(BN_TELEPHONE));									
						}
						if (metaObj.has(BN_EMAIL)) {
							jsonData.put(BN_EMAIL, metaObj.getString(BN_EMAIL));									
						}
						if (metaObj.has(BN_ADDRESS)) {
							jsonData.put(BN_ADDRESS, metaObj.getString(BN_ADDRESS));									
						}
					}
				}
				catch (Exception e) {
					_log.debug(e);
				}
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
							JSONObject hours = jsonDueDate.getJSONObject(DossierDocumentTerm.HOUR);
							JSONObject processHours = jsonDueDate.getJSONObject(DossierDocumentTerm.PROCESS_HOUR);
							//_log.info("hours: " + hours);
							if (hours != null && hours.has(DossierDocumentTerm.AM) && hours.has(DossierDocumentTerm.PM)) {
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
									String hoursAfterNoon = hours.getString(DossierDocumentTerm.PM);
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
							} else if (processHours != null && processHours.has(DossierDocumentTerm.START_HOUR) && processHours.has(DossierDocumentTerm.DUE_HOUR)) {
								//_log.info("STRART check new: ");
								Calendar receiveCalendar = Calendar.getInstance();
								receiveCalendar.setTime(dossier.getReceiveDate());
								//
								String receiveHour = processHours.getString(DossierDocumentTerm.START_HOUR);
								//_log.info("receiveHour: " + receiveHour);

								if (Validator.isNotNull(receiveHour)) {
									String[] splitHour = StringUtil.split(receiveHour, StringPool.COLON);
									if (splitHour != null) {
										int hourStart = GetterUtil.getInteger(splitHour[0]);
										if (receiveCalendar.get(Calendar.HOUR_OF_DAY) < hourStart) {
											String[] splitdueHour = StringUtil.split(processHours.getString(DossierDocumentTerm.DUE_HOUR),
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
						if (!DossierPartTerm.TP_DIFFERENT.equals(part.getPartNo()) &&
								DossierPartTerm.DOSSIER_PART_TYPE_OTHES == part.getPartType()) {

							jsonMark.put(DossierPartTerm.FORM_DATA, dossierMark.getRecordCount());
						} else {
							jsonMark.put(DossierPartTerm.FORM_DATA, StringPool.BLANK);
						}
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
		DossierMark dossierMark = DossierMarkLocalServiceUtil.getDossierMarkbyDossierId(groupId, dossierId, ConstantUtils.TP99);
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
						jsonMark.put(DossierPartTerm.FORM_DATA, StringPool.BLANK);
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

	public static JSONObject processMergeDossierFormData(Dossier dossier, JSONObject jsonData, ServiceProcess sp) {
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
		jsonData.put(DossierTerm.APPLICANT_NOTE, dossier.getApplicantNote());
		jsonData.put(DossierTerm.ONLINE, String.valueOf(dossier.getOnline()));
		jsonData.put(DossierTerm.FROM_VIA_POSTAL, String.valueOf(dossier.getFromViaPostal()));

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
			DictCollection govCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(GOVERNMENT_AGENCY, dossier.getGroupId());
			if (govCollection != null) {
				DictItem govAgenItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(dossier.getGovAgencyCode(), govCollection.getDictCollectionId(), dossier.getGroupId());
				String metaDataItem = govAgenItem.getMetaData();
				try {
					JSONObject metaObj = JSONFactoryUtil.createJSONObject(metaDataItem);
					if (govAgenItem != null) {
						if (metaObj.has(BN_TELEPHONE)) {
							jsonData.put(BN_TELEPHONE, metaObj.getString(BN_TELEPHONE));									
						}
						if (metaObj.has(BN_EMAIL)) {
							jsonData.put(BN_EMAIL, metaObj.getString(BN_EMAIL));									
						}
						if (metaObj.has(BN_ADDRESS)) {
							jsonData.put(BN_ADDRESS, metaObj.getString(BN_ADDRESS));									
						}
					}
				}
				catch (Exception e) {
					_log.debug(e);
				}
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
							JSONObject hours = jsonDueDate.getJSONObject(DossierDocumentTerm.HOUR);
							JSONObject processHours = jsonDueDate.getJSONObject(DossierDocumentTerm.PROCESS_HOUR);
							//_log.info("hours: " + hours);
							if (hours != null && hours.has(DossierDocumentTerm.AM) && hours.has(DossierDocumentTerm.PM)) {
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
									String hoursAfterNoon = hours.getString(DossierDocumentTerm.PM);
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
							} else if (processHours != null && processHours.has(DossierDocumentTerm.START_HOUR) && processHours.has(DossierDocumentTerm.DUE_HOUR)) {
								//_log.info("STRART check new: ");
								Calendar receiveCalendar = Calendar.getInstance();
								receiveCalendar.setTime(dossier.getReceiveDate());
								//
								String receiveHour = processHours.getString(DossierDocumentTerm.START_HOUR);
								//_log.info("receiveHour: " + receiveHour);

								if (Validator.isNotNull(receiveHour)) {
									String[] splitHour = StringUtil.split(receiveHour, StringPool.COLON);
									if (splitHour != null) {
										int hourStart = GetterUtil.getInteger(splitHour[0]);
										if (receiveCalendar.get(Calendar.HOUR_OF_DAY) < hourStart) {
											String[] splitdueHour = StringUtil.split(processHours.getString(DossierDocumentTerm.DUE_HOUR),
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
		DossierMark dossierMark = DossierMarkLocalServiceUtil.getDossierMarkbyDossierId(groupId, dossierId, ConstantUtils.TP99);
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
		
		jsonData.put(DossierTerm.SEQUENCES, getDossierProcessSequencesJSON(groupId, dossier, sp));
		
		//Notarization
		ServiceInfo si;
		try {
			si = ServiceInfoLocalServiceUtil.getByCode(dossier.getGroupId(), dossier.getServiceCode());
			if (si != null) {
				jsonData.put(ServiceInfoTerm.IS_NOTARIZATION, si.isIsNotarization());
			}
			else {
				jsonData.put(ServiceInfoTerm.IS_NOTARIZATION, false);
			}
		} catch (PortalException e) {
			_log.debug(e);
		}
		
		List<Notarization> lstNotarizations = NotarizationLocalServiceUtil.findByG_DID(groupId, dossierId);
		JSONArray notarizationArr = JSONFactoryUtil.createJSONArray();
		for (Notarization nt : lstNotarizations) {
			JSONObject notObj = JSONFactoryUtil.createJSONObject();
			notObj.put(NotarizationTerm.NOTARIZATION_ID, nt.getNotarizationId());
			notObj.put(NotarizationTerm.DOSSIER_ID, nt.getDossierId());
			notObj.put(NotarizationTerm.GROUP_ID, nt.getGroupId());
			notObj.put(NotarizationTerm.FILE_NAME, nt.getFileName());
			notObj.put(NotarizationTerm.NOTARIZATION_DATE, nt.getNotarizationDate() != null ? APIDateTimeUtils.convertDateToString(nt.getNotarizationDate(), APIDateTimeUtils._NORMAL_PARTTERN) : StringPool.BLANK);
			notObj.put(NotarizationTerm.NOTARIZATION_NO, nt.getNotarizationNo());
			notObj.put(NotarizationTerm.NOTARIZATION_YEAR, nt.getNotarizationYear());
			notObj.put(NotarizationTerm.SIGNER_NAME, nt.getSignerName());
			notObj.put(NotarizationTerm.SIGNER_POSITION, nt.getSignerPosition());
			notObj.put(NotarizationTerm.STATUS_CODE, nt.getStatusCode());
			notObj.put(NotarizationTerm.TOTAL_COPY, nt.getTotalCopy());
			notObj.put(NotarizationTerm.TOTAL_FEE, nt.getTotalFee());
			notObj.put(NotarizationTerm.TOTAL_PAGE, nt.getTotalPage());
			notObj.put(NotarizationTerm.TOTAL_RECORD, nt.getTotalRecord());
			
			notarizationArr.put(notObj);
		}
		jsonData.put(DossierTerm.NOTARIZATIONS, notarizationArr);
		
		// Meta data
		if (Validator.isNotNull(dossier.getMetaData())) {
			try {
				jsonData.put(DossierTerm.META_DATA, JSONFactoryUtil.createJSONObject(dossier.getMetaData()));
			} catch (JSONException e) {
				jsonData.put(DossierTerm.META_DATA, StringPool.BLANK);
			}
		} else {
			jsonData.put(DossierTerm.META_DATA, StringPool.BLANK);
		}

		return jsonData;
	}
	
	private static JSONArray getDossierProcessSequencesJSON(
			long groupId, Dossier dossier, ServiceProcess serviceProcess) {

			List<ProcessSequence> lstSequences =
				ProcessSequenceLocalServiceUtil.getByServiceProcess(
					groupId, serviceProcess.getServiceProcessId());

			JSONArray sequenceArr = JSONFactoryUtil.createJSONArray();
			DossierAction lastDA = DossierActionLocalServiceUtil.fetchDossierAction(
				dossier.getDossierActionId());
			List<DossierActionUser> lstDus =
				DossierActionUserLocalServiceUtil.getListUser(
					dossier.getDossierActionId());
			List<DossierLog> lstLogs = new ArrayList<>();

			try {
				lstLogs = DossierLogLocalServiceUtil.getByDossierAndType(
					dossier.getDossierId(), DossierLogTerm.PROCESS_TYPE, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);
			}
			catch (PortalException e) {
				_log.debug(e);
			}
			Map<Long, JSONArray> mapFiles = new HashMap<>();

			for (DossierLog log : lstLogs) {
				JSONObject payload;
				try {
					payload = JSONFactoryUtil.createJSONObject(log.getPayload());
					if (payload.has(DossierActionTerm.DOSSIERACTION_ID)) {
						mapFiles.put(
							payload.getLong(DossierActionTerm.DOSSIERACTION_ID),
							payload.getJSONArray(DossierActionTerm.FILES));
					}
				}
				catch (JSONException e) {
					_log.debug(e);
				}
			}

			List<DossierAction> dossierActionListCheck =
				DossierActionLocalServiceUtil.findByG_DID(
					groupId, dossier.getDossierId());
			if (dossierActionListCheck != null &&
				dossierActionListCheck.size() == 1 &&
				DossierTerm.STEP_DONE_CODE.equals(dossierActionListCheck.get(0).getStepCode())) {
			}
			else {
				for (ProcessSequence ps : lstSequences) {
					JSONObject sequenceObj = JSONFactoryUtil.createJSONObject();
					sequenceObj.put(ProcessSequenceTerm.SEQUENCE_NO, ps.getSequenceNo());
					sequenceObj.put(ProcessSequenceTerm.SEQUENCE_NAME, ps.getSequenceName());
					sequenceObj.put(ProcessSequenceTerm.SEQUENCE_ROLE, ps.getSequenceRole());
					sequenceObj.put(ProcessSequenceTerm.DURATION_COUNT, ps.getDurationCount());

					if (lastDA != null &&
						lastDA.getSequenceNo().equals(ps.getSequenceNo())) {
						String statusText = String.format(MessageUtil.getMessage(ConstantUtils.PROCESSSEQUENCE_MESSAGE_PROCESSING), lastDA.getStepName());
						sequenceObj.put(
							ProcessSequenceTerm.STATUS_TEXT,
							statusText);
					}
					List<DossierAction> lstDossierActions =
						DossierActionLocalServiceUtil.findDossierActionByG_DID_FSN(
							groupId, dossier.getDossierId(), ps.getSequenceNo());
					List<DossierAction> lstPrevDossierActions =
						DossierActionLocalServiceUtil.findDossierActionByG_DID_SN(
							groupId, dossier.getDossierId(), ps.getSequenceNo());

					DossierAction lastAction = lstPrevDossierActions.size() > 0
						? lstPrevDossierActions.get(
							lstPrevDossierActions.size() - 1)
						: null;
					if (lastAction != null) {
						sequenceObj.put(
							ProcessSequenceTerm.START_DATE, APIDateTimeUtils.convertDateToString(lastAction.getCreateDate(), APIDateTimeUtils._NORMAL_DATE_TIME));
					}
					else {
						sequenceObj.put(
							ProcessSequenceTerm.START_DATE, StringPool.BLANK);
					}

					if (lstDossierActions.size() > 0) {
						DossierAction lastDASequence =
							lstDossierActions.get(lstDossierActions.size() - 1);
						if (lastDASequence.getActionOverdue() != 0) {
							String preText = (lastDASequence.getActionOverdue() > 0
								? MessageUtil.getMessage(ConstantUtils.PROCESSSEQUENCE_MESSAGE_UNDUE) : ConstantUtils.PROCESSSEQUENCE_MESSAGE_OVERDUE);
							sequenceObj.put(
								ProcessSequenceTerm.OVERDUE_TEXT, String.format(preText, lastDASequence.getActionOverdue()));
						}
					}
					StringBuilder assignUserArr = new StringBuilder();
					List<Long> lstUsers = new ArrayList<>();

					if (lastDA.getSequenceNo().equals(ps.getSequenceNo())) {
						for (DossierActionUser dau : lstDus) {
							User u =
								UserLocalServiceUtil.fetchUser(dau.getUserId());
							if (u != null) {
								if (!lstUsers.contains(dau.getUserId()) &&
									dau.getModerator() == DossierActionUserTerm.ASSIGNED_TH) {
									lstUsers.add(dau.getUserId());
									// TODO: Not update user
									Employee emp =
										EmployeeLocalServiceUtil.fetchByF_mappingUserId(
											groupId, u.getUserId());
									if (emp != null) {
										if (!StringPool.BLANK.contentEquals(assignUserArr.toString())) {
											assignUserArr.append(ConstantUtils.HTML_NEW_LINE);
										}
										assignUserArr.append(emp.getFullName());
									}
									else {
										if (!StringPool.BLANK.contentEquals(assignUserArr.toString())) {
											assignUserArr.append(ConstantUtils.HTML_NEW_LINE);
										}
										assignUserArr.append(u.getFullName());
									}
								}
							}
						}
					}
					for (DossierAction da : lstDossierActions) {
						if (!lstUsers.contains(da.getUserId())) {
							lstUsers.add(da.getUserId());
							// TODO: Not update user
							User u =
									UserLocalServiceUtil.fetchUser(da.getUserId());
								if (u != null) {							
									Employee emp =
									EmployeeLocalServiceUtil.fetchByF_mappingUserId(
										groupId, u.getUserId());
									if (emp != null) {
										if (!"".contentEquals(assignUserArr.toString())) {
											assignUserArr.append(ConstantUtils.HTML_NEW_LINE);
										}
										assignUserArr.append(emp.getFullName());
									}
									else {
										if (!"".contentEquals(assignUserArr.toString())) {
											assignUserArr.append(ConstantUtils.HTML_NEW_LINE);
										}
										assignUserArr.append(u.getFullName());
									}
								}
						}
					}

					sequenceObj.put(DossierActionTerm.ASSIGN_USERS, assignUserArr.toString());

					StringBuilder actionsArr = new StringBuilder();
					for (DossierAction da : lstDossierActions) {
						if (!StringPool.BLANK.contentEquals(actionsArr.toString())) {
							actionsArr.append(ConstantUtils.HTML_NEW_LINE);
						}
						if (da.getCreateDate() != null) {
							actionsArr.append(APIDateTimeUtils.convertDateToString(da.getCreateDate(), APIDateTimeUtils._NORMAL_DATE_TIME));							
						}
						actionsArr.append(ConstantUtils.SPLIT_SPACE);
						
						actionsArr.append(da.getActionName());
					}

					sequenceObj.put(DossierActionTerm.ACTIONS, actionsArr.toString());

					sequenceArr.put(sequenceObj);
				}
			}

			return sequenceArr;
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
					keyValue[0] = keyJson + StringPool.AT + key;
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
					keyValue[0] = keyJson + StringPool.AT + key;
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
