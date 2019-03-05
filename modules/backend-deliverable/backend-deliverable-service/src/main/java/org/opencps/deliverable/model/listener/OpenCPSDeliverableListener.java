package org.opencps.deliverable.model.listener;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.util.StringPool;
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
import org.opencps.deliverable.model.OpenCPSDeliverable;
import org.opencps.deliverable.model.OpenCPSDeliverableType;
import org.opencps.deliverable.service.OpenCPSDeliverableLocalServiceUtil;
import org.opencps.deliverable.service.OpenCPSDeliverableLogLocalServiceUtil;
import org.opencps.deliverable.service.OpenCPSDeliverableTypeLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import backend.deliverable.service.util.ModelKeysDeliverableLog;

@Component(immediate = true, service = ModelListener.class)
public class OpenCPSDeliverableListener extends BaseModelListener<OpenCPSDeliverable> {

	@Override
	public void onAfterCreate(OpenCPSDeliverable model) throws ModelListenerException {

		// Binhth add message bus to processing jasper file
		//_log.info("IN DOSSIER FILE UPDATE FORM DATA");
		Message message = new Message();

		JSONObject msgData = JSONFactoryUtil.createJSONObject();
		msgData.put("className", OpenCPSDeliverable.class.getName());
		msgData.put("classPK", model.getDeliverableId());
		msgData.put("jrxmlTemplate", getJrxmlTemplate(model));
		msgData.put("formData", model.getFormData());
		msgData.put("userId", model.getUserId());

		message.put("msgToEngine", msgData);
		MessageBusUtil.sendMessage("jasper/engine/out/destination", message);

		//_log.info("SEND TO CREATED FILE MODEL");

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

		OpenCPSDeliverableLogLocalServiceUtil.adminProcessData(objectData);
	}

	@Override
	public void onAfterUpdate(OpenCPSDeliverable model) throws ModelListenerException {

		//_log.info("onAfterUpdate OPENCPS" + model);
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

		OpenCPSDeliverableLogLocalServiceUtil.adminProcessData(objectData);

		List<FileAttach> fileAttachs = FileAttachLocalServiceUtil.findByF_className_classPK(model.getGroupId(),
				OpenCPSDeliverable.class.getName() + "FileEntryId", String.valueOf(model.getDeliverableId()));

		boolean isAttact = false;

		if (Validator.isNotNull(fileAttachs) && fileAttachs.size() == 0 || Validator.isNull(fileAttachs)) {
			isAttact = true;
		}

		OpenCPSDeliverableType openCPSDeliverableType = OpenCPSDeliverableTypeLocalServiceUtil
				.getByTypeCode(model.getDeliverableType(), model.getGroupId());

		try {
			JSONObject mappingData = JSONFactoryUtil.createJSONObject(openCPSDeliverableType.getMappingData());

			if (Validator.isNotNull(mappingData.getString("deliverableCode"))) {

				if (Validator.isNull(model.getDeliverableCode())) {

					JSONObject formDataObject = JSONFactoryUtil.createJSONObject(model.getFormData());

					model.setDeliverableCode(formDataObject.getString(mappingData.getString("deliverableCode")));

					OpenCPSDeliverableLocalServiceUtil.updateOpenCPSDeliverable(model);
				}

			}

			if (Validator.isNotNull(mappingData.getString("subject"))) {

				if (Validator.isNull(model.getSubject())) {

					JSONObject formDataObject = JSONFactoryUtil.createJSONObject(model.getFormData());

					model.setSubject(formDataObject.getString(mappingData.getString("subject")));

					OpenCPSDeliverableLocalServiceUtil.updateOpenCPSDeliverable(model);
				}

			}

			if (Validator.isNotNull(mappingData.getString("issueDate"))) {

				if (Validator.isNull(model.getIssueDate())) {

					JSONObject formDataObject = JSONFactoryUtil.createJSONObject(model.getFormData());

					String issueDateStr = formDataObject.getString(mappingData.getString("issueDate"));

					try {
						Date issueDate = DateTimeUtils.convertStringToDate(issueDateStr);
						model.setIssueDate(issueDate);

						OpenCPSDeliverableLocalServiceUtil.updateOpenCPSDeliverable(model);
					} catch (Exception e) {
						// TODO: handle exception
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

						OpenCPSDeliverableLocalServiceUtil.updateOpenCPSDeliverable(model);
					} catch (Exception e) {
						// TODO: handle exception
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

						OpenCPSDeliverableLocalServiceUtil.updateOpenCPSDeliverable(model);
					} catch (Exception e) {
						// TODO: handle exception
					}

				}

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			_log.error(e);
		}

		if (!modelBeforeUpdate.getFormData().equals(model.getFormData()) && isAttact) {
			//_log.info("IN DOSSIER FILE UPDATE FORM DATA");
			Message message = new Message();

			JSONObject msgData = JSONFactoryUtil.createJSONObject();
			msgData.put("className", OpenCPSDeliverable.class.getName());
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
	public void onBeforeCreate(OpenCPSDeliverable model) throws ModelListenerException {
	}

	@Override
	public void onBeforeUpdate(OpenCPSDeliverable model) throws ModelListenerException {
		try {
			modelBeforeUpdate = OpenCPSDeliverableLocalServiceUtil.getOpenCPSDeliverable(model.getDeliverableId());
		} catch (Exception e) {
			_log.debug(e);
			// _log.error(e);
		}
	}

	public String getJrxmlTemplate(OpenCPSDeliverable model) {
		String result = StringPool.BLANK;

		OpenCPSDeliverableType openCPSDeliverableType = OpenCPSDeliverableTypeLocalServiceUtil
				.getByTypeCode(model.getDeliverableType(), model.getGroupId());

		InputStream is = null;

		try {

			DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil
					.getFileEntry(openCPSDeliverableType.getFormReportFileId());

			is = dlFileEntry.getContentStream();

			result = IOUtils.toString(is, StandardCharsets.UTF_8);

		} catch (Exception e) {
			e.printStackTrace();
			result = StringPool.BLANK;
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public OpenCPSDeliverable modelBeforeUpdate;

	private Log _log = LogFactoryUtil.getLog(OpenCPSDeliverableListener.class.getName());
}
