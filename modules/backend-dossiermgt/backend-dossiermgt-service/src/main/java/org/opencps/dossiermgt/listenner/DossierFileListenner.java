
package org.opencps.dossiermgt.listenner;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.action.util.AutoFillFormData;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.DossierLogUtils;
import org.opencps.dossiermgt.constants.DeliverableLogTerm;
import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.service.DeliverableLocalServiceUtil;
import org.opencps.dossiermgt.service.DeliverableLogLocalServiceUtil;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLogLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.dossiermgt.service.comparator.DossierFileComparator;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class DossierFileListenner extends BaseModelListener<DossierFile> {

	@Override
	public void onBeforeCreate(DossierFile model)
		throws ModelListenerException {

		_log.debug("Before Created........... ==> " + model.getDossierId());
	}

	@Override
	public void onAfterCreate(DossierFile model)
		throws ModelListenerException {

		_log.debug("DossierFileCreate........... ");

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(model.getCompanyId());
		serviceContext.setUserId(model.getUserId());

		// Update DossierLog

		try {

			JSONObject payload = JSONFactoryUtil.createJSONObject();

			payload.put(
				DossierFileTerm.DOSSIER_FILE_ID, String.valueOf(model.getDossierFileId()));

			DossierLogLocalServiceUtil.addDossierLog(
				model.getGroupId(), model.getDossierId(), model.getUserName(),
				StringPool.BLANK,
				DossierFileListenerMessageKeys.DOSSIER_LOG_CREATE_TYPE,
				payload.toJSONString(), serviceContext);

			DossierPart dossierPart =
				DossierPartLocalServiceUtil.fetchByTemplatePartNo(
					model.getGroupId(), model.getDossierTemplateNo(),
					model.getDossierPartNo());

			String deliverableType = dossierPart.getDeliverableType();

			String deliverableCode = model.getDeliverableCode();

			if (Validator.isNotNull(deliverableCode)) {
				// Dossier dossier =
				// DossierLocalServiceUtil.getDossier(model.getDossierId());

				// Exist Deliverable checking

				Deliverable dlv =
					DeliverableLocalServiceUtil.getByCode(deliverableCode);

				DeliverableType dlvType =
					DeliverableTypeLocalServiceUtil.getByCode(
						model.getGroupId(), deliverableType);

				JSONObject formDataContent = JSONFactoryUtil.createJSONObject();

				try {

					if (Validator.isNotNull(dlvType.getMappingData())) {
						JSONObject jsMappingData =
							JSONFactoryUtil.createJSONObject(
								dlvType.getMappingData());

						JSONObject jsFormData =
							JSONFactoryUtil.createJSONObject();

						if (Validator.isNotNull(model.getFormData()))
							jsFormData = JSONFactoryUtil.createJSONObject(
								model.getFormData());

						formDataContent = mappingContent(
							jsMappingData, jsFormData, model.getDossierId());

					}

				}
				catch (Exception e) {
					_log.debug(e);
					// _log.error(e);
					_log.error("Parser JSONDATA error_DELIVERABLE");
				}

				// String subject = StringPool.BLANK;
				// String issueDate = StringPool.BLANK;
				// String expireDate = StringPool.BLANK;
				// String revalidate = StringPool.BLANK;
				// String deliverableState = "2";
				// String formData = StringPool.BLANK;

				if (Validator.isNotNull(formDataContent)) {
					// subject = formDataContent.getString("subject");
					// issueDate = formDataContent.getString("issueDate");
					// expireDate = formDataContent.getString("expireDate");
					// revalidate = formDataContent.getString("revalidate");
					//
					// formData = formDataContent.toString();

				}

				if (Validator.isNull(dlv)) {
					// add deliverable

					/*
					 * dlv = DeliverableLocalServiceUtil.addDeliverable(model.
					 * getGroupId(), deliverableType, deliverableCode,
					 * dossier.getGovAgencyCode(), dossier.getApplicantIdNo(),
					 * dossier.getApplicantName(), subject, issueDate,
					 * expireDate, revalidate, deliverableState,
					 * serviceContext);
					 * DeliverableLocalServiceUtil.updateFormData(model.
					 * getGroupId(), dlv.getDeliverableId(), formData,
					 * serviceContext);
					 */
				}

			}

		}
		catch (Exception e) {
			_log.debug(e);
			// _log.error(e);
		}

	}

	private JSONObject mappingContent(
		JSONObject mappingSrc, JSONObject srcFormData, long dossierId) {

		JSONObject returnOBJ;

		// dossierId

		// subject

		// deliverableCode

		Map<String, Object> jsonMap = jsonToMap(mappingSrc);

		for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {

			// String entryKey = entry.getKey();

			String entryValue = GetterUtil.getString(entry.getValue());

			String uEntryValue;

			JSONObject jEntryValue;

			if (entryValue.startsWith(StringPool.POUND) && entryValue.contains(StringPool.AT)) {
				// _log.info("INTO->getElement");
				uEntryValue =
					getValueElementFormData(srcFormData, entryValue, dossierId);
				entry.setValue(uEntryValue);

			}

			if (_checkContains(entryValue)) {
				// _log.info("INTO->getSpecical");

				uEntryValue = getSpecialValue(entryValue, dossierId);
				entry.setValue(uEntryValue);

			}

			if (entryValue.startsWith(StringPool.POUND) && !entryValue.contains(StringPool.AT)) {
				// _log.info("INTO->getAllForm");

				entryValue =
					StringUtil.replaceFirst(entryValue, StringPool.POUND, StringPool.BLANK);

				jEntryValue = getValueFormData(entryValue, dossierId);

				entry.setValue(jEntryValue);

			}

		}

		returnOBJ = convertToJSON(jsonMap);

		return returnOBJ;
	}

	private JSONObject mappingContent(
		JSONObject mappingSrc, JSONObject srcFormData) {

		JSONObject returnOBJ;

		// dossierId

		// subject

		// deliverableCode

		Map<String, Object> jsonMap = jsonToMap(mappingSrc);

		for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {

			String entryValue = GetterUtil.getString(entry.getValue());
			entry.setValue(srcFormData.getString(entryValue));
		}

		returnOBJ = convertToJSON(jsonMap);

		return returnOBJ;
	}

	public enum SpecialKey {
			APPLICANTNAME, APPLICANTIDNO, RECEIVEDATE, DOSSIERIDCNT, DOSSIERNO,
			SUBMITDATE
	};

	private boolean _checkContains(String key) {

		boolean isContain = false;

		EnumMap<SpecialKey, String> map =
			new EnumMap<SpecialKey, String>(SpecialKey.class);

		map.put(SpecialKey.APPLICANTNAME, DossierFileTerm.UNDERLINE_APPLICANTNAME);
		map.put(SpecialKey.APPLICANTIDNO, DossierFileTerm.UNDERLINE_APPLICANTIDNO);
		map.put(SpecialKey.DOSSIERIDCNT, DossierFileTerm.UNDERLINE_DOSSIERIDCNT);
		map.put(SpecialKey.DOSSIERNO, DossierFileTerm.UNDERLINE_DOSSIERNO);
		map.put(SpecialKey.SUBMITDATE, DossierFileTerm.UNDERLINE_SUBMITDATE);
		map.put(SpecialKey.RECEIVEDATE, DossierFileTerm.UNDERLINE_RECEIVEDATE);

		isContain = map.containsValue(key);

		return isContain;
	}

	private JSONObject getValueFormData(String fileTemplateNo, long dossierId) {

		DossierFile dossierFile = null;
		JSONObject formValue = JSONFactoryUtil.createJSONObject();

		try {

			dossierFile =
				DossierFileLocalServiceUtil.getDossierFileByDID_FTNO_DPT_First(
					dossierId, fileTemplateNo, 2, false,
					new DossierFileComparator(false, Field.CREATE_DATE, Date.class));

			if (Validator.isNotNull(dossierFile)) {
				formValue =
					JSONFactoryUtil.createJSONObject(dossierFile.getFormData());
			}

		}
		catch (Exception e) {
			_log.debug(e);
			// _log.error(e);
			_log.info(
				"File" + fileTemplateNo + "is null or json is not correct");
		}

		return formValue;
	}

	private String getValueElementFormData(
		JSONObject formData, String key, long dossierId) {

		String elmValue = StringPool.BLANK;

		String keyJs = stripKey(key);

		String formTemplate = stripForm(key);

		List<DossierFile> dfs =
			DossierFileLocalServiceUtil.getDossierFileByDID_FTNO(
				dossierId, formTemplate, false);

		if (Validator.isNotNull(dfs) && dfs.size() != 0) {
			DossierFile df = dfs.get(0);

			try {
				JSONObject jsformData =
					JSONFactoryUtil.createJSONObject(df.getFormData());

				elmValue = jsformData.getString(keyJs);

			}
			catch (Exception e) {
				_log.debug(e);
				// _log.error(e);
				_log.info(
					"File" + formTemplate + "is null or json is not correct");
			}
		}

		return elmValue;
	}

	private String stripKey(String key) {

		String rtn = StringPool.BLANK;

		if (Validator.isNotNull(key)) {
			String[] strArr = StringUtil.split(key, StringPool.AT);

			if (strArr.length == 2) {
				rtn = strArr[0];

				rtn = StringUtil.replaceFirst(rtn, StringPool.POUND, StringPool.BLANK);
			}
		}
		// _log.info(rtn+"_____"+key);

		return rtn;
	}

	private String stripForm(String key) {

		String rtn = StringPool.BLANK;

		if (Validator.isNotNull(key)) {
			String[] strArr = StringUtil.split(key, StringPool.AT);

			if (strArr.length == 2) {
				rtn = strArr[1];
			}
		}
		// _log.info(rtn+"_____"+key);

		return rtn;
	}

	private String getSpecialValue(String key, long dossierId) {

		String val = StringPool.BLANK;

		// _log.info("SPECIAL_KEY________" + key);

		try {
			Dossier dossier = DossierLocalServiceUtil.getDossier(dossierId);

			if (key.contentEquals(DossierFileTerm.UNDERLINE_APPLICANTNAME)) {
				val = dossier.getApplicantName();
			}

			if (key.contentEquals(DossierFileTerm.UNDERLINE_APPLICANTIDNO)) {
				val = dossier.getApplicantIdNo();

			}

			if (key.contentEquals(DossierFileTerm.UNDERLINE_DOSSIERIDCNT)) {

				Document dossierDoc = DossierLocalServiceUtil.getDossierById(
					dossierId, dossier.getCompanyId());

				_log.info("DossierIsNull_" + dossierId);

				String dossierCTN = StringPool.BLANK;

				if (Validator.isNotNull(dossierDoc)) {
					_log.info("DossierIsNotNull_" + dossierId);
					dossierCTN = dossierDoc.get(DossierTerm.DOSSIER_ID_CTN);
				}

				val = dossierCTN;

			}

			if (key.contentEquals(DossierFileTerm.UNDERLINE_DOSSIERNO)) {
				val = dossier.getDossierNo();

			}

			if (key.contentEquals(DossierFileTerm.UNDERLINE_SUBMITDATE)) {
				if (Validator.isNotNull(dossier.getSubmitDate())) {
					val = APIDateTimeUtils.convertDateToString(
						dossier.getSubmitDate(),
						APIDateTimeUtils._NORMAL_PARTTERN);

				}

			}
			if (key.contentEquals(DossierFileTerm.UNDERLINE_RECEIVEDATE)) {
				val = APIDateTimeUtils.convertDateToString(
					dossier.getReceiveDate(),
					APIDateTimeUtils._NORMAL_PARTTERN);

			}

		}
		catch (Exception e) {
			_log.error(e);
		}

		return val;
	}

	private JSONObject convertToJSON(Map<String, Object> jsonMap) {

		JSONObject returnJSO = JSONFactoryUtil.createJSONObject();

		for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
			returnJSO.put(entry.getKey(), entry.getValue());
		}

		return returnJSO;
	}

	@Override
	public void onAfterRemove(DossierFile model)
		throws ModelListenerException {

		String content = "On DossiserFile Delete";
		String notificationType = StringPool.BLANK;
		String payload = DossierLogUtils.createPayload(model, null, null);

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(model.getCompanyId());
		serviceContext.setUserId(model.getUserId());

		try {
			DossierLogLocalServiceUtil.addDossierLog(
				model.getGroupId(), model.getDossierId(), model.getUserName(),
				content, notificationType, payload, serviceContext);
		}
		catch (SystemException | PortalException e) {
			// e.printStackTrace();
			_log.error(e);
		}
	}

	@Override
	public void onBeforeUpdate(DossierFile model)
		throws ModelListenerException {

		try {
			modelBeforeUpdate = DossierFileLocalServiceUtil.getDossierFile(
				model.getPrimaryKey());
		}
		catch (Exception e) {
			_log.debug(e);
			// _log.error(e);
		}
	}

	@Override
	public void onAfterUpdate(DossierFile model)
		throws ModelListenerException {

		_log.info("Update DossierFile_________-");

		if (model.getIsNew()) {
			return;
		}

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(model.getCompanyId());
		serviceContext.setUserId(model.getUserId());

		try {
			// Nhung ho so co chung deliverableType va deliverableCode se
			// chung 1 giay phep
			DossierPart dossierPart =
				DossierPartLocalServiceUtil.fetchByTemplatePartNo(
					model.getGroupId(), model.getDossierTemplateNo(),
					model.getDossierPartNo());
			_log.info("TRACE LOG DossierPart :" + model.getGroupId() + " // " + model.getDossierTemplateNo() + "//" + model.getDossierPartNo());

			// Neu ho so khong co ky so thi khong can tao giay phep
			if (!dossierPart.getESign() ||
				Validator.isNull(dossierPart.getDeliverableType())) {
				return;
			}

			// DeliverableType
			DeliverableType dlvType =
				DeliverableTypeLocalServiceUtil.getByCode(
					model.getGroupId(), dossierPart.getDeliverableType());

			Dossier dossier =
				DossierLocalServiceUtil.getDossier(model.getDossierId());

			DossierFile dossierFileAttach =
				DossierFileLocalServiceUtil.getByGID_DID_TEMP_PART_EFORM(
					model.getGroupId(), model.getDossierId(),
					model.getDossierTemplateNo(), model.getDossierPartNo(),
					false, false);

			JSONObject formDataContent =
				JSONFactoryUtil.createJSONObject(model.getFormData());

			JSONObject jsMappingData =
				JSONFactoryUtil.createJSONObject(dlvType.getMappingData());
			_log.debug("jsMappingData: " + jsMappingData);

			JSONObject jsFormData = JSONFactoryUtil.createJSONObject();

			if (Validator.isNotNull(model.getFormData()))
				jsFormData =
					JSONFactoryUtil.createJSONObject(model.getFormData());
			_log.debug("jsFormData: " + jsFormData);

			// All mappingData from deliverableType must format like:
			// "deliverableCode": "#deliverableCode@KQGP"

			if (jsMappingData.has(DossierFileTerm.DELIVERABLES)) {

				// map data with fileTemplateNo
				JSONObject mapp = mappingContent(
					jsMappingData, jsFormData, model.getDossierId());
				// uu tien cac truong thong tin formData
//				formDataContent =
//					mergeObject(mapp.toString(), jsFormData.toString());
				formDataContent = AutoFillFormData.sampleDataBindingDeliverable(
						dlvType.getMappingData(), dossier.getDossierId(),
						serviceContext);
				_log.info("Log formData :" + formDataContent.toString());
			}
			// else if (jsMappingData.has("deliverables")) {
			// formDataContent = mappingContent(jsMappingData, jsFormData);
			// }

			// them truong luu danh sach cac file quyet dinh
			String fileAttachs = StringPool.BLANK;
			if (jsMappingData.has(DossierFileTerm.FILE_ATTACHS)) {

				fileAttachs = getFileAttachsInDeliverable(
					model.getDossierId(),
					jsMappingData.getString(DossierFileTerm.FILE_ATTACHS));
			}
			_log.info("--- File Attach : " + fileAttachs);

			// them trang thai mong muon khi hoan thanh thu tuc
			String expertState = DossierFileTerm.EXPECT_STATE_DEFAULT;
			if (jsMappingData.has(DossierFileTerm.EXPECT_STATE) && Validator.isNotNull(dossier)) {

				String expertStates = jsMappingData.getString(DossierFileTerm.EXPECT_STATE);
				if (expertStates.contains(dossier.getServiceCode())) {

					expertState = StringUtil.split(expertStates, dossier.getServiceCode() + StringPool.AT)[1].substring(0, 1);
				}
			}
			formDataContent.put(DossierFileTerm.EXPECT_STATE, expertState);

			String dDeliverableCode = model.getDeliverableCode();

			if (model.getEForm()) {
				// TODO: check ton tai
				dDeliverableCode = formDataContent.has(DossierFileTerm.DELIVERABLE_CODE)
					? formDataContent.getString(DossierFileTerm.DELIVERABLE_CODE)
					: dDeliverableCode;
				model.setDeliverableCode(dDeliverableCode);
				model.setIsNew(true);
				DossierFileLocalServiceUtil.updateDossierFile(model);
			}

			String applicantName =
				formDataContent.getString(DossierFileTerm.APPLICANT_NAME);
			String subject = formDataContent.getString(DossierFileTerm.SUBJECT);
			String issueDate = formDataContent.getString(DossierFileTerm.ISSUE_DATE);
			String expireDate = formDataContent.getString(DossierFileTerm.EXPIRE_DATE);
			String revalidate = formDataContent.getString(DossierFileTerm.REVALIDATE);

			// check exits deliverable
			Deliverable deliverable = Validator.isNotNull(dDeliverableCode)
				? DeliverableLocalServiceUtil.getByF_GID_DCODE(
					model.getGroupId(), dDeliverableCode)
				: DeliverableLocalServiceUtil.fetchByGID_DID(
					model.getGroupId(), model.getDossierId());
			_log.info("------------------------------------ Deliverable " + deliverable);
			_log.info("LOG TRACE DOSSIER ORIGINALITY: " + dossier.getOriginality() + " -- DossierNo :" + dossier.getDossierNo() + " " + dossier.getDossierId());
			if (dossierPart.getDeliverableAction() == 0 &&
				Validator.isNull(deliverable) && dossier.getOriginality() != 9) {

				// add new deliverable

				_log.info(
					"============addDeliverableSign=============" +
						model.getDeliverableCode() + "___" + deliverable);
				_log.info("Dossier Originality : " + dossier.getOriginality());
				deliverable =
					DeliverableLocalServiceUtil.addDeliverableSign(
						model.getGroupId(),
						dossierPart.getDeliverableType(),
						dlvType.getTypeName(), dDeliverableCode,
						dossier.getGovAgencyCode(),
						dossier.getGovAgencyName(),
						dossier.getApplicantIdNo(), applicantName, subject,
						issueDate, expireDate, revalidate, String.valueOf(0),
						dossier.getDossierId(),
						dossierFileAttach != null
							? dossierFileAttach.getFileEntryId() : 0l,
						dlvType.getFormScriptFileId(),
						dlvType.getFormReportFileId(),
						formDataContent.toString(), fileAttachs,
						serviceContext);
			}
			else if (Validator.isNotNull(deliverable)) {
				if(model.getDossierPartType() != 7) {
					// backup a deliverable log and update deliverable

					_log.info(
							"============backup a deliverable log and update deliverable=============");
					// backup
					JSONObject deliverableLog =
							JSONFactoryUtil.createJSONObject();
					long actionDate =
							Validator.isNotNull(deliverable.getIssueDate())
									? deliverable.getIssueDate().getTime()
									: Validator.isNotNull(deliverable.getRevalidate())
									? deliverable.getRevalidate().getTime()
									: new Date().getTime();

					deliverableLog.put(Field.GROUP_ID, model.getGroupId());
					deliverableLog.put(Field.COMPANY_ID, model.getCompanyId());
					deliverableLog.put(Field.USER_ID, model.getUserId());
					deliverableLog.put(Field.USER_NAME, model.getUserName());
					deliverableLog.put(
							DeliverableTerm.DELIVERABLE_ID, deliverable.getDeliverableId());
					deliverableLog.put(DeliverableLogTerm.DOSSIERUID, dossier.getReferenceUid());
					deliverableLog.put(DeliverableLogTerm.AUTHOR, model.getUserName());
					deliverableLog.put(DeliverableLogTerm.CONTENT, DeliverableLogTerm.CONTENT_DEFAULT);
					deliverableLog.put(
							DeliverableLogTerm.DELIVERABLEACTION,
							dossierPart.getDeliverableAction());
					deliverableLog.put(DeliverableLogTerm.ACTIONDATE, actionDate);
					deliverableLog.put(DeliverableLogTerm.PAYLOAD, deliverable.getFormData());
					deliverableLog.put(
							DeliverableLogTerm.FILEENTRYID, deliverable.getFileEntryId());
					DeliverableLogLocalServiceUtil.adminProcessData(
							deliverableLog);

					// update
					deliverable.setDeliverableCode(dDeliverableCode);
//					_log.info("---- LOG FILE entryId :" + dossierFileAttach.getFileEntryId() + " ----------");
					deliverable.setFileEntryId(
							dossierFileAttach != null
									? dossierFileAttach.getFileEntryId() : 0l);
					deliverable.setApplicantName(
							Validator.isNotNull(applicantName)
									? applicantName : deliverable.getApplicantName());
					deliverable.setSubject(
							Validator.isNotNull(subject)
									? applicantName : deliverable.getSubject());
					deliverable.setIssueDate(
							Validator.isNotNull(issueDate)
									? APIDateTimeUtils.convertStringToDate(
									issueDate, APIDateTimeUtils._NORMAL_DATE)
									: deliverable.getIssueDate());
					deliverable.setExpireDate(
							Validator.isNotNull(expireDate)
									? APIDateTimeUtils.convertStringToDate(
									expireDate, APIDateTimeUtils._NORMAL_DATE)
									: deliverable.getExpireDate());
					deliverable.setRevalidate(
							Validator.isNotNull(revalidate)
									? APIDateTimeUtils.convertStringToDate(
									revalidate, APIDateTimeUtils._NORMAL_DATE)
									: deliverable.getRevalidate());

//					formDataContent = mergeObject(
//							deliverable.getFormData(), formDataContent.toString());
					formDataContent = AutoFillFormData.sampleDataBindingDeliverable(
							dlvType.getMappingData(), dossier.getDossierId(),
							serviceContext);
					if(Validator.isNotNull(formDataContent)) {
						if (formDataContent.has(DeliverableTerm.DELIVERABLE_CODE)) {
							formDataContent.remove(DeliverableTerm.DELIVERABLE_CODE);
						}
						if (formDataContent.has(DeliverableTerm.GOV_AGENCY_CODE)) {
							formDataContent.remove(DeliverableTerm.GOV_AGENCY_CODE);
						}
						if (formDataContent.has(DeliverableTerm.DELIVERABLE_STATE)) {
							formDataContent.remove(DeliverableTerm.DELIVERABLE_STATE);
						}
						if (formDataContent.has(DeliverableTerm.ISSUE_DATE)) {
							formDataContent.remove(DeliverableTerm.ISSUE_DATE);
						}
					}
					deliverable.setFormData(formDataContent.toString());

					deliverable.setFileAttachs(fileAttachs);
					_log.info("UPDATE Deliverable :" + deliverable.getFormData());

					DeliverableLocalServiceUtil.updateDeliverable(deliverable);
				}
			}
			else {
				_log.info(
					"==============deliverable other key update============");
			}

			// update fileEntryId if not exits dossierFileAttach
			long formReportFileId = 0;
			if(deliverable !=null) {
				 formReportFileId = deliverable.getFormReportFileId() > 0
						? deliverable.getFormReportFileId()
						: dlvType.getFormReportFileId();
			}

			if (deliverable != null && formReportFileId > 0 &&
				!(dossierFileAttach != null &&
					dossierFileAttach.getFileEntryId() > 0)) {
//				_log.info(" ------- DossierFileAttach : " +  dossierFileAttach.getFileEntryId());
				InputStream is = null;
				String jrxmlTemplate = StringPool.BLANK;

				try {
					DLFileEntry dlFileEntry =
						DLFileEntryLocalServiceUtil.getFileEntry(
							formReportFileId);

					is = dlFileEntry.getContentStream();

					jrxmlTemplate =
						IOUtils.toString(is, StandardCharsets.UTF_8);

				}
				catch (Exception e) {
					_log.debug(e);
					jrxmlTemplate = StringPool.BLANK;
				}
				finally {
					if (is != null) {
						try {
							is.close();
						}
						catch (IOException e) {
							_log.debug(e);
						}
					}
				}

				_log.info(
					"========update fileEntryId if not exits dossierFileAttach");
				// Process update deliverable file Id
				Message message = new Message();

				if(Validator.isNull(jrxmlTemplate)){
					_log.info("Log dossierPart :" + dossierPart.getDossierPartId());
					jrxmlTemplate = dossierPart.getFormReport();
				}
				_log.info(" FORM JRXMLTemplate " + jrxmlTemplate);
				JSONObject msgData = JSONFactoryUtil.createJSONObject();
				msgData.put(ConstantUtils.CLASS_NAME, Deliverable.class.getName());
				msgData.put(Field.CLASS_PK, deliverable.getDeliverableId());
				msgData.put(ConstantUtils.JRXML_TEMPLATE, jrxmlTemplate);
				msgData.put(
					ConstantUtils.FORM_DATA,
					formDataContent != null
						? formDataContent.toJSONString()
						: StringPool.BLANK);
				msgData.put(Field.USER_ID, model.getUserId());

				message.put(ConstantUtils.MSG_ENG, msgData);
				MessageBusUtil.sendMessage(
					ConstantUtils.JASPER_DESTINATION, message);
				_log.info("LOG INFO message : " + JSONFactoryUtil.looseSerialize(message));
				_log.info("LOG INFO message : " + JSONFactoryUtil.looseSerialize(fileAttachs));

			}
//			_log.info("End onAfterUpdate Deliverable" + deliverable.getFileEntryId());
		}
		catch (Exception e) {
			_log.error(e);
			_log.info(e.getMessage());
			e.printStackTrace();
		}

	}

	public String getFileAttachsInDeliverable(
		long dossierId, String filesTemplateNo) {

		String fileAttachs = StringPool.BLANK;
		String commaFlag = StringPool.BLANK;
		if (dossierId <= 0 || Validator.isNull(filesTemplateNo)) {

			return fileAttachs;
		}
		try {
			String[] filesTemplateNoArr =
				filesTemplateNo.split(StringPool.COMMA);
			// fileTemplateNo in mappingData
			for (String fileTemplateNo : filesTemplateNoArr) {

				List<DossierFile> dfs =
					DossierFileLocalServiceUtil.getDossierFileByDID_FTNO(
						dossierId, fileTemplateNo.trim(), false);

				// all dossierFile in fileTemplateNo
				for (DossierFile df : dfs) {

					if (df.getFileEntryId() > 0) {

						fileAttachs += commaFlag + df.getFileEntryId();
						commaFlag = StringPool.COMMA;
					}
				}
			}
		}
		catch (Exception e) {
			_log.error(e);
		}
		return fileAttachs;
	}

	public JSONObject mergeObject(String oldObj, String newObj) {

		JSONObject mergedObj = JSONFactoryUtil.createJSONObject();

		try {

			_log.debug("=======map=======" + oldObj + " " + newObj);
			JSONObject o1 = Validator.isNotNull(oldObj)
				? JSONFactoryUtil.createJSONObject(oldObj)
				: JSONFactoryUtil.createJSONObject();
			JSONObject o2 = Validator.isNotNull(newObj)
				? JSONFactoryUtil.createJSONObject(newObj)
				: JSONFactoryUtil.createJSONObject();
			Iterator i1 = o1.keys();
			Iterator i2 = o2.keys();
			String tmp_key;
			while (i1.hasNext()) {
				tmp_key = (String) i1.next();
				mergedObj.put(tmp_key, o1.get(tmp_key));
			}
			while (i2.hasNext()) {
				tmp_key = (String) i2.next();
				// only update if not null
				if (Validator.isNotNull(o2.get(tmp_key))) {
					mergedObj.put(tmp_key, o2.get(tmp_key));
				}
			}
		}
		catch (Exception e) {
			// e.printStackTrace();
			_log.error(e);
		}

		return mergedObj;
	}

	public Map<String, Object> jsonToMap(JSONObject json) {

		Map<String, Object> retMap = new HashMap<String, Object>();

		if (Validator.isNotNull(json)) {
			try {
				retMap = toMap(json);
			}
			catch (JSONException e) {
				_log.error(e);
			}
		}
		return retMap;
	}

	public static Map<String, Object> toMap(JSONObject object)
		throws JSONException {

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

	public static List<Object> toList(JSONArray array)
		throws JSONException {

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

	public DossierFile modelBeforeUpdate;

	private Log _log =
		LogFactoryUtil.getLog(DossierFileListenner.class.getName());
}
