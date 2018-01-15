package org.opencps.dossiermgt.listenner;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.opencps.dossiermgt.action.util.DossierLogUtils;
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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

@Component(immediate = true, service = ModelListener.class)
public class DossierFileListenner extends BaseModelListener<DossierFile> {

	@Override
	public void onBeforeCreate(DossierFile model) throws ModelListenerException {
		_log.info("Before Created........... ==> " + model.getDossierId());
	}

	@Override
	public void onAfterCreate(DossierFile model) throws ModelListenerException {
		_log.info("DossierFileCreate........... ");
		/*
		 * String content = "On DossiserFile Created"; String notificationType =
		 * "File-01"; String payload = DossierLogUtils.createPayload(model,
		 * null, null);
		 */
		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(model.getCompanyId());
		serviceContext.setUserId(model.getUserId());
		/*
		 * try { DossierLogLocalServiceUtil.addDossierLog(model.getGroupId(),
		 * model.getDossierId(), model.getUserName(), content, notificationType,
		 * payload, serviceContext);
		 * 
		 * // Binhth add message bus to processing KySO file Message message =
		 * new Message(); DossierPart dossierPart =
		 * DossierPartLocalServiceUtil.fetchByTemplatePartNo(model.getGroupId(),
		 * model.getDossierTemplateNo(), model.getDossierPartNo());
		 * 
		 * JSONObject msgDataESign = JSONFactoryUtil.createJSONObject();
		 * msgDataESign.put("userId", model.getUserId());
		 * msgDataESign.put("eSign", dossierPart.getESign());
		 * msgDataESign.put("fileEntryId", model.getFileEntryId());
		 * 
		 * message.put("msgToEngine", msgDataESign);
		 * MessageBusUtil.sendMessage("kyso/engine/out/destination", message); }
		 * catch (SystemException | PortalException e) { e.printStackTrace(); }
		 */

		// update deliverable

		try {
			DossierPart dossierPart = DossierPartLocalServiceUtil.fetchByTemplatePartNo(model.getGroupId(),
					model.getDossierTemplateNo(), model.getDossierPartNo());

			String deliverableType = dossierPart.getDeliverableType();
			// int deliverableAction = dossierPart.getDeliverableAction();

			String deliverableCode = model.getDeliverableCode();

			if (Validator.isNotNull(deliverableCode)) {
				Dossier dossier = DossierLocalServiceUtil.getDossier(model.getDossierId());

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
					_log.error("Parser JSONDATA error_DELIVERABLE");
				}

				String subject = StringPool.BLANK;
				String issueDate = StringPool.BLANK;
				String expireDate = StringPool.BLANK;
				String revalidate = StringPool.BLANK;
				String deliverableState = "0";
				String formData = StringPool.BLANK;

				if (Validator.isNotNull(formDataContent)) {
					subject = formDataContent.getString("subject");
					issueDate = formDataContent.getString("issueDate");
					expireDate = formDataContent.getString("expireDate");
					revalidate = formDataContent.getString("revalidate");

					formData = formDataContent.toString();
					
					_log.info("FormData........... "+formData);

				}

				if (Validator.isNull(dlv)) {
					// add deliverable

					dlv = DeliverableLocalServiceUtil.addDeliverable(model.getGroupId(), deliverableType,
							deliverableCode, dossier.getGovAgencyCode(), dossier.getApplicantIdNo(),
							dossier.getApplicantName(), subject, issueDate, expireDate, revalidate, deliverableState,
							serviceContext);

					DeliverableLocalServiceUtil.updateFormData(model.getGroupId(), dlv.getDeliverableId(), formData,
							serviceContext);

				}

			}

		} catch (Exception e) {
			_log.error(e);
		}

	}

	private JSONObject mappingContent(JSONObject mappingSrc, JSONObject srcFormData, long dossierId) {
		JSONObject returnOBJ = JSONFactoryUtil.createJSONObject();

		// dossierId

		// subject

		// deliverableCode

		//

		Map<String, Object> jsonMap = jsonToMap(mappingSrc);

		for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {

			String entryKey = entry.getKey();

			String entryValue = StringPool.BLANK;
			
			_log.info("EntryKey"+entryKey);

			if (entryKey.startsWith("#") && entryKey.contains("@")) {
				_log.info("GetElementForm___"+entryValue);
				entryValue = getValueElementFormData(srcFormData, entryKey);
			}

			if (entryKey.contains(SPEC_DELIVERABLES) || entryKey.contains(SPEC_DOSSIER_FILE_ID)
					|| entryKey.contains(SPEC_DELIVERABLE_CODE) || entryKey.contains(SPEC_SUBJECT)) {
				_log.info("SpecialForm"+entryValue);

				entryValue = getSpecialValue(entryKey);
			}

			if (entryKey.startsWith("#") && !entryKey.contains("@")) {
				_log.info("SpecialForm"+entryValue);

				entryValue = getValueFormData(entryKey, dossierId);
			}

			entry.setValue(entryValue);

		}

		returnOBJ = convertToJSON(jsonMap);

		return returnOBJ;
	}

	private String getValueFormData(String fileTemplateNo, long dossierId) {
		DossierFile dossierFile = null;
		String formValue = StringPool.BLANK;
		try {
			dossierFile = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO_DPT_First(dossierId, fileTemplateNo, 2,
					false, new DossierFileComparator(false, "createDate", Date.class));
			
			_log.info("dossierFile_____"+Validator.isNotNull(dossierFile));


		} catch (Exception e) {

		}
		if (Validator.isNotNull(dossierFile)) {
			formValue = dossierFile.getFormData();
		}

		return formValue;
	}

	private String getValueElementFormData(JSONObject formData, String key) {

		String elmValue = StringPool.BLANK;

		if (Validator.isNotNull(elmValue)) {
			formData.getString(key);
		}

		return elmValue;
	}

	private String getSpecialValue(String key) {
		return null;
	}

	private JSONObject convertToJSON(Map<String, Object> jsonMap) {
		JSONObject returnJSO = JSONFactoryUtil.createJSONObject();

		for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
			returnJSO.put(entry.getKey(), entry.getValue());
		}

		return returnJSO;
	}

	private static final String SPEC_DOSSIER_FILE_ID = "dossierFileId";
	private static final String SPEC_DELIVERABLE_CODE = "deliverableCode";
	private static final String SPEC_DELIVERABLES = "deliverables";
	private static final String SPEC_SUBJECT = "subject";

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
			e.printStackTrace();
		}
	}

	@Override
	public void onBeforeUpdate(DossierFile model) throws ModelListenerException {
		try {
			modelBeforeUpdate = DossierFileLocalServiceUtil.getDossierFile(model.getPrimaryKey());
		} catch (Exception e) {
			_log.error(e);
		}
	}

	@Override
	public void onAfterUpdate(DossierFile model) throws ModelListenerException {
		_log.info("Update DossierFile_________-");

		/*
		 * String content = "On DossiserFile Update"; String notificationType =
		 * ""; String payload = DossierLogUtils.createPayload(model, null,
		 * null);
		 */
		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(model.getCompanyId());
		serviceContext.setUserId(model.getUserId());

		// update derliverable

		// update deliverable

		try {
			DossierPart dossierPart = DossierPartLocalServiceUtil.fetchByTemplatePartNo(model.getGroupId(),
					model.getDossierTemplateNo(), model.getDossierPartNo());

			String deliverableType = dossierPart.getDeliverableType();
			// int deliverableAction = dossierPart.getDeliverableAction();

			String deliverableCode = model.getDeliverableCode();
			_log.info("deliverableCode DossierFile_________-" + deliverableCode);

			if (Validator.isNotNull(deliverableCode)) {
				Dossier dossier = DossierLocalServiceUtil.getDossier(model.getDossierId());

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
						
						_log.info("FORM_DATA"+jsFormData);

						formDataContent = mappingContent(jsMappingData, jsFormData, model.getDossierId());

					}

				} catch (Exception e) {
					_log.error("Parser JSONDATA error_DELIVERABLE");
				}

				String subject = StringPool.BLANK;
				String issueDate = StringPool.BLANK;
				String expireDate = StringPool.BLANK;
				String revalidate = StringPool.BLANK;
				String deliverableState = "0";

				if (Validator.isNotNull(formDataContent)) {
					subject = formDataContent.getString("subject");
					issueDate = formDataContent.getString("issueDate");
					expireDate = formDataContent.getString("expireDate");
					revalidate = formDataContent.getString("revalidate");

					_log.info("formDataContent DossierFile_________-" + formDataContent.toString());
				}

				if (Validator.isNull(dlv)) {
					// add deliverable
					dlv = DeliverableLocalServiceUtil.addDeliverable(model.getGroupId(), deliverableType,
							deliverableCode, dossier.getGovAgencyCode(), dossier.getApplicantIdNo(),
							dossier.getApplicantName(), subject, issueDate, expireDate, revalidate, deliverableState,
							serviceContext);

				} else {
					// update deliverable

					try {

						if (Validator.isNotNull(dlvType.getMappingData())) {
							JSONObject jsMappingData = JSONFactoryUtil.createJSONObject(dlvType.getMappingData());

							JSONObject jsFormData = JSONFactoryUtil.createJSONObject();

							if (Validator.isNotNull(model.getFormData()))
								jsFormData = JSONFactoryUtil.createJSONObject(model.getFormData());

							formDataContent = mappingContent(jsMappingData, jsFormData, model.getDossierId());

						}

					} catch (Exception e) {
						_log.error("Parser JSONDATA error_DELIVERABLE");
					}

					String formData = StringPool.BLANK;

					if (Validator.isNotNull(formDataContent)) {
						formData = formDataContent.toString();

					}

					DeliverableLocalServiceUtil.updateFormData(model.getGroupId(), dlv.getDeliverableId(), formData,
							serviceContext);
				}

			}

		} catch (Exception e) {
			_log.error(e);
		}

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

	public static DossierFile modelBeforeUpdate;

	private Log _log = LogFactoryUtil.getLog(DossierFileListenner.class.getName());
}
