package org.opencps.dossiermgt.listenner;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.opencps.datamgt.model.FileAttach;
import org.opencps.datamgt.service.FileAttachLocalServiceUtil;
import org.opencps.datamgt.util.DateTimeUtils;
import org.opencps.dossiermgt.constants.ModelKeysDeliverableLog;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.service.DeliverableLocalServiceUtil;
import org.opencps.dossiermgt.service.DeliverableLogLocalServiceUtil;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;

//@Component(immediate = true, service = ModelListener.class)
public class DeliverableListener extends BaseModelListener<Deliverable> {

	@Override
	public void onAfterCreate(Deliverable model) throws ModelListenerException {
		Message message = new Message();

		JSONObject msgData = JSONFactoryUtil.createJSONObject();
		msgData.put("className", Deliverable.class.getName());
		msgData.put("classPK", model.getDeliverableId());
		msgData.put("jrxmlTemplate", getJrxmlTemplate(model));
		msgData.put("formData", model.getFormData());
		msgData.put("userId", model.getUserId());

		message.put("msgToEngine", msgData);
		MessageBusUtil.sendMessage("jasper/engine/out/destination", message);

		JSONObject objectData = JSONFactoryUtil.createJSONObject();

		objectData.put(ModelKeysDeliverableLog.GROUPID, model.getGroupId());
		objectData.put(ModelKeysDeliverableLog.COMPANYID, model.getCompanyId());
		objectData.put(ModelKeysDeliverableLog.USERID, model.getUserId());

		objectData.put(ModelKeysDeliverableLog.DELIVERABLEID, model.getDeliverableId());
		objectData.put(ModelKeysDeliverableLog.DOSSIERUID, model.getUuid());
		objectData.put(ModelKeysDeliverableLog.AUTHOR, model.getUserName());
		objectData.put(ModelKeysDeliverableLog.CONTENT, "ADD");
		objectData.put(ModelKeysDeliverableLog.DELIVERABLEACTION, model.getDeliverableState());
		objectData.put(ModelKeysDeliverableLog.ACTIONDATE, new Date().getTime());

		objectData.put(ModelKeysDeliverableLog.PAYLOAD, model.getFormData());

		DeliverableLogLocalServiceUtil.adminProcessData(objectData);
	}

	@Override
	public void onAfterUpdate(Deliverable model) throws ModelListenerException {
		JSONObject objectData = JSONFactoryUtil.createJSONObject();

		objectData.put(ModelKeysDeliverableLog.GROUPID, model.getGroupId());
		objectData.put(ModelKeysDeliverableLog.COMPANYID, model.getCompanyId());
		objectData.put(ModelKeysDeliverableLog.USERID, model.getUserId());

		objectData.put(ModelKeysDeliverableLog.DELIVERABLEID, model.getDeliverableId());
		objectData.put(ModelKeysDeliverableLog.DOSSIERUID, model.getUuid());
		objectData.put(ModelKeysDeliverableLog.AUTHOR, model.getUserName());
		objectData.put(ModelKeysDeliverableLog.CONTENT, "UPDATE");
		objectData.put(ModelKeysDeliverableLog.DELIVERABLEACTION, model.getDeliverableState());
		objectData.put(ModelKeysDeliverableLog.ACTIONDATE, new Date().getTime());

		objectData.put(ModelKeysDeliverableLog.PAYLOAD, model.getFormData());

		DeliverableLogLocalServiceUtil.adminProcessData(objectData);

		List<FileAttach> fileAttachs = FileAttachLocalServiceUtil.findByF_className_classPK(model.getGroupId(),
				Deliverable.class.getName() + "FileEntryId", String.valueOf(model.getDeliverableId()));

		boolean isAttact = false;

		if (Validator.isNotNull(fileAttachs) && fileAttachs.size() == 0 || Validator.isNull(fileAttachs)) {
			isAttact = true;
		}

		DeliverableType openCPSDeliverableType = DeliverableTypeLocalServiceUtil
				.getByTypeCode(model.getDeliverableType(), model.getGroupId());

		try {
			JSONObject mappingData = JSONFactoryUtil.createJSONObject(openCPSDeliverableType.getMappingData());

			if (Validator.isNotNull(mappingData.getString("deliverableCode"))) {

				if (Validator.isNull(model.getDeliverableCode())) {

					JSONObject formDataObject = JSONFactoryUtil.createJSONObject(model.getFormData());

					model.setDeliverableCode(formDataObject.getString(mappingData.getString("deliverableCode")));

					DeliverableLocalServiceUtil.updateDeliverable(model);
				}

			}

			if (Validator.isNotNull(mappingData.getString("subject"))) {

				if (Validator.isNull(model.getSubject())) {

					JSONObject formDataObject = JSONFactoryUtil.createJSONObject(model.getFormData());

					model.setSubject(formDataObject.getString(mappingData.getString("subject")));

					DeliverableLocalServiceUtil.updateDeliverable(model);
				}

			}

			if (Validator.isNotNull(mappingData.getString("issueDate"))) {

				if (Validator.isNull(model.getIssueDate())) {

					JSONObject formDataObject = JSONFactoryUtil.createJSONObject(model.getFormData());

					String issueDateStr = formDataObject.getString(mappingData.getString("issueDate"));

					try {
						Date issueDate = DateTimeUtils.convertStringToDate(issueDateStr);
						model.setIssueDate(issueDate);

						DeliverableLocalServiceUtil.updateDeliverable(model);
					} catch (Exception e) {
						_log.debug(e);
					}

				}

			}

			if (Validator.isNotNull(mappingData.getString("expireDate"))) {

				if (Validator.isNull(model.getExpireDate())) {

					JSONObject formDataObject = JSONFactoryUtil.createJSONObject(model.getFormData());

					String expireDateStr = formDataObject.getString(mappingData.getString("expireDate"));

					try {
						Date expireDate = DateTimeUtils.convertStringToDate(expireDateStr);
						model.setExpireDate(expireDate);

						DeliverableLocalServiceUtil.updateDeliverable(model);
					} catch (Exception e) {
						_log.debug(e);
					}

				}

			}

			if (Validator.isNotNull(mappingData.getString("revalidate"))) {

				if (Validator.isNull(model.getRevalidate())) {

					JSONObject formDataObject = JSONFactoryUtil.createJSONObject(model.getFormData());

					String revalidateStr = formDataObject.getString(mappingData.getString("revalidate"));

					try {
						Date revalidate = DateTimeUtils.convertStringToDate(revalidateStr);
						model.setRevalidate(revalidate);

						DeliverableLocalServiceUtil.updateDeliverable(model);
					} catch (Exception e) {
						_log.debug(e);
					}

				}

			}

		} catch (JSONException e) {
			_log.debug(e);
		}

		if (!modelBeforeUpdate.getFormData().equals(model.getFormData()) && isAttact) {
			//_log.info("IN DOSSIER FILE UPDATE FORM DATA");
			Message message = new Message();

			JSONObject msgData = JSONFactoryUtil.createJSONObject();
			msgData.put("className", Deliverable.class.getName());
			msgData.put("classPK", model.getDeliverableId());
			msgData.put("jrxmlTemplate", getJrxmlTemplate(model));
			msgData.put("formData", model.getFormData());
			msgData.put("userId", model.getUserId());

			message.put("msgToEngine", msgData);
			MessageBusUtil.sendMessage("jasper/engine/out/destination", message);

			//_log.info("SEND TO CREATED FILE MODEL");
		}	
	}

	@Override
	public void onBeforeCreate(Deliverable model) throws ModelListenerException {
	}

	@Override
	public void onBeforeUpdate(Deliverable model) throws ModelListenerException {
		try {
			modelBeforeUpdate = DeliverableLocalServiceUtil.getDeliverable(model.getDeliverableId());
		} catch (Exception e) {
			_log.debug(e);
		}
	}

	public String getJrxmlTemplate(Deliverable model) {
		String result = StringPool.BLANK;

		DeliverableType openCPSDeliverableType = DeliverableTypeLocalServiceUtil
				.getByTypeCode(model.getDeliverableType(), model.getGroupId());

		InputStream is = null;
		try {

			DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil
					.getFileEntry(openCPSDeliverableType.getFormReportFileId());

			is = dlFileEntry.getContentStream();

			result = IOUtils.toString(is, StandardCharsets.UTF_8);

		} catch (Exception e) {
			_log.debug(e);
			result = StringPool.BLANK;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					_log.debug(e);
				}
			}
		}

		return result;
	}

	public Deliverable modelBeforeUpdate;
	
	private Log _log = LogFactoryUtil.getLog(DeliverableListener.class.getName());
}
