package org.opencps.dossiermgt.listenner;

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
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.action.util.DossierLogUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.service.DeliverableLocalServiceUtil;
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
	public void onBeforeCreate(DossierFile model) throws ModelListenerException {
		_log.debug("Before Created........... ==> " + model.getDossierId());
	}

	@Override
	public void onAfterCreate(DossierFile model) throws ModelListenerException {
		_log.debug("DossierFileCreate........... ");

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(model.getCompanyId());
		serviceContext.setUserId(model.getUserId());

		// Update DossierLog

		try {

			JSONObject payload = JSONFactoryUtil.createJSONObject();

			payload.put("dossierFileId", String.valueOf(model.getDossierFileId()));

			DossierLogLocalServiceUtil.addDossierLog(model.getGroupId(), model.getDossierId(), model.getUserName(),
					StringPool.BLANK, DossierFileListenerMessageKeys.DOSSIER_LOG_CREATE_TYPE, payload.toJSONString(),
					serviceContext);

			DossierPart dossierPart = DossierPartLocalServiceUtil.fetchByTemplatePartNo(model.getGroupId(),
					model.getDossierTemplateNo(), model.getDossierPartNo());

			String deliverableType = dossierPart.getDeliverableType();

			String deliverableCode = model.getDeliverableCode();

			if (Validator.isNotNull(deliverableCode)) {
				//Dossier dossier = DossierLocalServiceUtil.getDossier(model.getDossierId());

				// Exist Deliverable checking

				Deliverable dlv = DeliverableLocalServiceUtil.getByCode(deliverableCode);

				DeliverableType dlvType = DeliverableTypeLocalServiceUtil.getByCode(model.getGroupId(),
						deliverableType);

				JSONObject formDataContent = JSONFactoryUtil.createJSONObject();

				try {

					if (Validator.isNotNull(dlvType.getMappingData())) {
						JSONObject jsMappingData = JSONFactoryUtil.createJSONObject(dlvType.getMappingData());

						JSONObject jsFormData = JSONFactoryUtil.createJSONObject();

						if (Validator.isNotNull(model.getFormData()))
							jsFormData = JSONFactoryUtil.createJSONObject(model.getFormData());

						formDataContent = mappingContent(jsMappingData, jsFormData, model.getDossierId());

					}

				} catch (Exception e) {
					_log.debug(e);
					//_log.error(e);
					_log.error("Parser JSONDATA error_DELIVERABLE");
				}

//				String subject = StringPool.BLANK;
//				String issueDate = StringPool.BLANK;
//				String expireDate = StringPool.BLANK;
//				String revalidate = StringPool.BLANK;
//				String deliverableState = "2";
//				String formData = StringPool.BLANK;

				if (Validator.isNotNull(formDataContent)) {
//					subject = formDataContent.getString("subject");
//					issueDate = formDataContent.getString("issueDate");
//					expireDate = formDataContent.getString("expireDate");
//					revalidate = formDataContent.getString("revalidate");
//
//					formData = formDataContent.toString();

				}

				if (Validator.isNull(dlv)) {
					// add deliverable

/*					dlv = DeliverableLocalServiceUtil.addDeliverable(model.getGroupId(), deliverableType,
							deliverableCode, dossier.getGovAgencyCode(), dossier.getApplicantIdNo(),
							dossier.getApplicantName(), subject, issueDate, expireDate, revalidate, deliverableState,
							serviceContext);

					DeliverableLocalServiceUtil.updateFormData(model.getGroupId(), dlv.getDeliverableId(), formData,
							serviceContext);
*/
				}

			}

		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
		}

	}

	private JSONObject mappingContent(JSONObject mappingSrc, JSONObject srcFormData, long dossierId) {
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

			if (entryValue.startsWith("#") && entryValue.contains("@")) {
				// _log.info("INTO->getElement");
				uEntryValue = getValueElementFormData(srcFormData, entryValue, dossierId);
				entry.setValue(uEntryValue);

			}

			if (_checkContains(entryValue)) {
				// _log.info("INTO->getSpecical");

				uEntryValue = getSpecialValue(entryValue, dossierId);
				entry.setValue(uEntryValue);

			}

			if (entryValue.startsWith("#") && !entryValue.contains("@")) {
				// _log.info("INTO->getAllForm");

				entryValue = StringUtil.replaceFirst(entryValue, "#", StringPool.BLANK);

				jEntryValue = getValueFormData(entryValue, dossierId);

				entry.setValue(jEntryValue);

			}

		}

		returnOBJ = convertToJSON(jsonMap);

		return returnOBJ;
	}

	private JSONObject mappingContent(JSONObject mappingSrc, JSONObject srcFormData) {
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
		APPLICANTNAME, APPLICANTIDNO, RECEIVEDATE, DOSSIERIDCNT, DOSSIERNO, SUBMITDATE
	};

	private boolean _checkContains(String key) {
		boolean isContain = false;

		EnumMap<SpecialKey, String> map = new EnumMap<SpecialKey, String>(SpecialKey.class);

		map.put(SpecialKey.APPLICANTNAME, "_applicantName");
		map.put(SpecialKey.APPLICANTIDNO, "_applicantIdNo");
		map.put(SpecialKey.DOSSIERIDCNT, "_dossierIdCTN");
		map.put(SpecialKey.DOSSIERNO, "_dossierNo");
		map.put(SpecialKey.SUBMITDATE, "_submitDate");
		map.put(SpecialKey.RECEIVEDATE, "_receiveDate");

		isContain = map.containsValue(key);

		return isContain;
	}

	private JSONObject getValueFormData(String fileTemplateNo, long dossierId) {
		DossierFile dossierFile = null;
		JSONObject formValue = JSONFactoryUtil.createJSONObject();

		try {

			dossierFile = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO_DPT_First(dossierId, fileTemplateNo, 2,
					false, new DossierFileComparator(false, "createDate", Date.class));

			if (Validator.isNotNull(dossierFile)) {
				formValue = JSONFactoryUtil.createJSONObject(dossierFile.getFormData());
			}

		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			_log.info("File" + fileTemplateNo + "is null or json is not correct");
		}

		return formValue;
	}

	private String getValueElementFormData(JSONObject formData, String key, long dossierId) {

		String elmValue = StringPool.BLANK;

		String keyJs = stripKey(key);

		String formTemplate = stripForm(key);

		List<DossierFile> dfs = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO(dossierId, formTemplate, false);

		if (Validator.isNotNull(dfs) && dfs.size() != 0) {
			DossierFile df = dfs.get(0);

			try {
				JSONObject jsformData = JSONFactoryUtil.createJSONObject(df.getFormData());

				elmValue = jsformData.getString(keyJs);

			} catch (Exception e) {
				_log.debug(e);
				//_log.error(e);
				_log.info("File" + formTemplate + "is null or json is not correct");
			}
		}

		return elmValue;
	}

	private String stripKey(String key) {
		String rtn = StringPool.BLANK;

		if (Validator.isNotNull(key)) {
			String[] strArr = StringUtil.split(key, "@");

			if (strArr.length == 2) {
				rtn = strArr[0];

				rtn = StringUtil.replaceFirst(rtn, "#", StringPool.BLANK);
			}
		}
		// _log.info(rtn+"_____"+key);

		return rtn;
	}

	private String stripForm(String key) {
		String rtn = StringPool.BLANK;

		if (Validator.isNotNull(key)) {
			String[] strArr = StringUtil.split(key, "@");

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

			if (key.contentEquals("_applicantName")) {
				val = dossier.getApplicantName();
			}

			if (key.contentEquals("_applicantIdNo")) {
				val = dossier.getApplicantIdNo();

			}

			if (key.contentEquals("_dossierIdCTN")) {

				Document dossierDoc = DossierLocalServiceUtil.getDossierById(dossierId, dossier.getCompanyId());
				
				_log.info("DossierIsNull_" + dossierId);

				String dossierCTN = StringPool.BLANK;

				if (Validator.isNotNull(dossierDoc)) {
					_log.info("DossierIsNotNull_" + dossierId);
					dossierCTN = dossierDoc.get(DossierTerm.DOSSIER_ID + "CTN");
				}

				val = dossierCTN;

			}

			if (key.contentEquals("_dossierNo")) {
				val = dossier.getDossierNo();

			}

			if (key.contentEquals("_submitDate")) {
				if (Validator.isNotNull(dossier.getSubmitDate())) {
					val = APIDateTimeUtils.convertDateToString(dossier.getSubmitDate(),
							APIDateTimeUtils._NORMAL_PARTTERN);

				}

			}
			if (key.contentEquals("_receiveDate")) {
				val = APIDateTimeUtils.convertDateToString(dossier.getReceiveDate(), APIDateTimeUtils._NORMAL_PARTTERN);

			}

		} catch (Exception e) {
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
	public void onAfterRemove(DossierFile model) throws ModelListenerException {
		String content = "On DossiserFile Delete";
		String notificationType = "";
		String payload = DossierLogUtils.createPayload(model, null, null);

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(model.getCompanyId());
		serviceContext.setUserId(model.getUserId());

		try {
			DossierLogLocalServiceUtil.addDossierLog(model.getGroupId(), model.getDossierId(), model.getUserName(),
					content, notificationType, payload, serviceContext);
		} catch (SystemException | PortalException e) {
//			e.printStackTrace();
			_log.error(e);
		}
	}

	@Override
	public void onBeforeUpdate(DossierFile model) throws ModelListenerException {
		try {
			modelBeforeUpdate = DossierFileLocalServiceUtil.getDossierFile(model.getPrimaryKey());
		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
		}
	}

	@Override
	public void onAfterUpdate(DossierFile model) throws ModelListenerException {
		_log.info("Update DossierFile_________-");

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(model.getCompanyId());
		serviceContext.setUserId(model.getUserId());

		// update deliverable
		try {
			DossierPart dossierPart = DossierPartLocalServiceUtil.fetchByTemplatePartNo(model.getGroupId(),
					model.getDossierTemplateNo(), model.getDossierPartNo());

			String deliverableType = StringPool.BLANK;
			boolean eSign = false;
			if (dossierPart != null) {
				deliverableType = dossierPart.getDeliverableType();
				eSign = dossierPart.getESign();
			}

			String deliverableCode = model.getDeliverableCode();
			// _log.info("deliverableCode DossierFile_________-" +
			// deliverableCode);

			if (Validator.isNotNull(deliverableCode)) {
				Dossier dossier = DossierLocalServiceUtil.getDossier(model.getDossierId());

				// Exist Deliverable checking

				Deliverable dlv = DeliverableLocalServiceUtil.getByF_GID_DCODE(model.getGroupId(), deliverableCode);

				DeliverableType dlvType = DeliverableTypeLocalServiceUtil.getByCode(model.getGroupId(),
						deliverableType);

				JSONObject formDataContent = JSONFactoryUtil.createJSONObject(model.getFormData());

				String subject = StringPool.BLANK;
				String issueDate = StringPool.BLANK;
				String expireDate = StringPool.BLANK;
				String revalidate = StringPool.BLANK;
				String codeForm = StringPool.BLANK;
				String applicantName = StringPool.BLANK;
				String deliverableState = (dlv != null ? String.valueOf(dlv.getDeliverableState()) : StringPool.BLANK);
				if (eSign && Validator.isNull(deliverableState)) {
					deliverableState = "0";
				} else {
					deliverableState = "1";
				}

				// update deliverable
				try {

					if (Validator.isNotNull(dlvType.getMappingData())) {
						JSONObject jsMappingData = JSONFactoryUtil.createJSONObject(dlvType.getMappingData());
						_log.info("jsMappingData: "+jsMappingData);

						JSONObject jsFormData = JSONFactoryUtil.createJSONObject();

						if (Validator.isNotNull(model.getFormData()))
							jsFormData = JSONFactoryUtil.createJSONObject(model.getFormData());
						_log.info("jsFormData: "+jsFormData);

						if (jsMappingData.has("deliverables") && Validator.isNotNull(jsMappingData.get("deliverbles"))) {
							formDataContent = mappingContent(jsMappingData, jsFormData, model.getDossierId());
						}
						else if (jsMappingData.has("deliverables")){
							formDataContent = mappingContent(jsMappingData, jsFormData);
						}
					}

				} catch (Exception e) {
					_log.debug(e);
					_log.error("Parser JSONDATA error_DELIVERABLE");
				}

				String formData = StringPool.BLANK;

				if (Validator.isNotNull(formDataContent)) {
					formData = formDataContent.toString();

				}
				if (Validator.isNotNull(formDataContent)) {
					subject = formDataContent.getString("subject");
					issueDate = formDataContent.getString("issueDate");
					expireDate = formDataContent.getString("expireDate");
					revalidate = formDataContent.getString("revalidate");
					codeForm = formDataContent.getString("deliverableCode");
					applicantName = formDataContent.getString("applicantName");
				}

				if (Validator.isNull(dlv)) {
					// add deliverable
					if (Validator.isNotNull(codeForm)) {
						dlv = DeliverableLocalServiceUtil.addDeliverable(model.getGroupId(), deliverableType, codeForm,
								dossier.getGovAgencyCode(), dossier.getGovAgencyName(), dossier.getApplicantIdNo(),
								applicantName, subject, issueDate, expireDate, revalidate,
								deliverableState, model.getDossierId(), model.getFileEntryId(), serviceContext);
						if (dlv != null) {
							model.setDeliverableCode(codeForm);
							DossierFileLocalServiceUtil.updateDossierFile(model);
						}
					} else {
						dlv = DeliverableLocalServiceUtil.addDeliverable(model.getGroupId(), deliverableType,
								deliverableCode, dossier.getGovAgencyCode(), dossier.getGovAgencyName(),
								dossier.getApplicantIdNo(), dossier.getApplicantName(), subject, issueDate, expireDate,
								revalidate, deliverableState, model.getDossierId(), model.getFileEntryId(),
								serviceContext);
					}
				}
				if (dlv != null) {
					dlv.setFileEntryId(model.getFileEntryId());
					DeliverableLocalServiceUtil.updateDeliverable(dlv);
				}

				//_log.info("Update deliverable form data: " + formData);
				
				DeliverableLocalServiceUtil.updateFormData(model.getGroupId(), dlv != null ? dlv.getDeliverableId() : 0, formData,
						serviceContext);

			}

		} catch (Exception e) {
			_log.error(e);
		}

	}

	public Map<String, Object> jsonToMap(JSONObject json) {
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

	public DossierFile modelBeforeUpdate;

	private Log _log = LogFactoryUtil.getLog(DossierFileListenner.class.getName());
}
