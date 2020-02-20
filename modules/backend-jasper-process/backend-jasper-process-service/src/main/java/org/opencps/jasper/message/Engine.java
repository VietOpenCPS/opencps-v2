package org.opencps.jasper.message;

import java.io.File;

import org.opencps.jasper.utils.JRReportUtil;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Validator;

import backend.jasper.process.service.util.ConfigConstants;
import backend.jasper.process.service.util.ConfigProps;

public class Engine implements MessageListener {

	@Override
	public void receive(Message message) throws MessageListenerException {
		// TODO Auto-generated method stub
		try {
			_doReceiveJasperRequest(message);
		} catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}

	private void _doReceiveJasperRequest(Message message) {
		// TODO Auto-generated method stub
		_log.info("Jasper processing .............................");
		JSONObject msgData = (JSONObject) message.get(ConfigConstants.JASPER_TERM_MSG_TO_ENGINE);

		File file = FileUtil.createTempFile(JRReportUtil.DocType.PDF.toString());

		try {

			long userId = msgData.getLong(ConfigConstants.JASPER_TERM_USER_ID);

			long classPK = msgData.getLong(ConfigConstants.JASPER_TERM_CLASS_PK);

			String className = msgData.getString(ConfigConstants.JASPER_TERM_CLASS_NAME);

			if (ConfigProps.get(ConfigConstants.ENGINE_CLASSNAME_DOSSIERFILE).equals(className)) {
				JSONObject jsonData = JSONFactoryUtil.createJSONObject();
				try {
					jsonData = JSONFactoryUtil.createJSONObject(msgData.getString(ConfigConstants.JASPER_TERM_FORM_DATA));
				} catch (JSONException e1) {
					_log.error(e1);
				}
	
				String fileExport = JRReportUtil.createReportFile(msgData.getString(ConfigConstants.JASPER_TERM_JRXML_TEMPLATE),
						jsonData.toJSONString(), null, file.getCanonicalPath());

				if (Validator.isNotNull(fileExport)) {
				
					_log.info("Jasper export success: " + fileExport);
					
					JSONObject msgDataIn = JSONFactoryUtil.createJSONObject();
					msgDataIn.put(ConfigConstants.JASPER_TERM_CLASS_NAME, className);
					msgDataIn.put(ConfigConstants.JASPER_TERM_CLASS_PK, classPK);
					msgDataIn.put(ConfigConstants.JASPER_TERM_USER_ID, userId);
					msgDataIn.put(ConfigConstants.JASPER_TERM_FILE_PATH, fileExport );
					
					message.put(ConfigConstants.JASPER_TERM_MSG_TO_ENGINE, msgDataIn);
					MessageBusUtil.sendMessage(ConfigConstants.JASPER_DESTINATION_DOSSIER, message);
				}				
			}
			else if (ConfigProps.get(ConfigConstants.ENGINE_CLASSNAME_DELIVERABLE).equals(className)) {
				JSONObject jsonData = JSONFactoryUtil.createJSONObject();
				try {
					jsonData = JSONFactoryUtil.createJSONObject(msgData.getString(ConfigConstants.JASPER_TERM_FORM_DATA));
				} catch (JSONException e1) {
					_log.error(e1);
				}

				String fileExport = JRReportUtil.createReportFile(msgData.getString(ConfigConstants.JASPER_TERM_JRXML_TEMPLATE),
						jsonData.toJSONString(), null, file.getCanonicalPath());

				if (Validator.isNotNull(fileExport)) {
					
					_log.info("Jasper export success: " + fileExport);
					
					JSONObject msgDataIn = JSONFactoryUtil.createJSONObject();
					msgDataIn.put(ConfigConstants.JASPER_TERM_CLASS_NAME, className);
					msgDataIn.put(ConfigConstants.JASPER_TERM_CLASS_PK, classPK);
					msgDataIn.put(ConfigConstants.JASPER_TERM_USER_ID, userId);
					msgDataIn.put(ConfigConstants.JASPER_TERM_FILE_PATH, fileExport );
					
					message.put(ConfigConstants.JASPER_TERM_MSG_TO_ENGINE, msgDataIn);
					MessageBusUtil.sendMessage(ConfigConstants.JASPER_DESTINATION_DOSSIER, message);
				}								
			}
			else if (ConfigProps.get(ConfigConstants.ENGINE_CLASSNAME_OPENCPSDELIVERABLE).equals(className)) {
				JSONObject jsonData = JSONFactoryUtil.createJSONObject();
				try {
					jsonData = JSONFactoryUtil.createJSONObject(msgData.getString(ConfigConstants.JASPER_TERM_FORM_DATA));
				} catch (JSONException e1) {
					_log.error(e1);
				}

				String fileExport = JRReportUtil.createReportFile(msgData.getString(ConfigConstants.JASPER_TERM_JRXML_TEMPLATE),
						jsonData.toJSONString(), null, file.getCanonicalPath());

				if (Validator.isNotNull(fileExport)) {
					
					_log.info("Jasper export success: " + fileExport);
					
					JSONObject msgDataIn = JSONFactoryUtil.createJSONObject();
					msgDataIn.put(ConfigConstants.JASPER_TERM_CLASS_NAME, className);
					msgDataIn.put(ConfigConstants.JASPER_TERM_CLASS_PK, classPK);
					msgDataIn.put(ConfigConstants.JASPER_TERM_USER_ID, userId);
					msgDataIn.put(ConfigConstants.JASPER_TERM_FILE_PATH, fileExport );
					
					message.put(ConfigConstants.JASPER_TERM_MSG_TO_ENGINE, msgDataIn);
					MessageBusUtil.sendMessage(ConfigConstants.JASPER_DESTINATION_DOSSIER, message);
				}								
			}
			else if (ConfigProps.get(ConfigConstants.ENGINE_CLASSNAME_DOSSIERDOCUMENT).equals(className)) {
				JSONObject jsonData = JSONFactoryUtil.createJSONObject();
				try {
					jsonData = JSONFactoryUtil.createJSONObject(msgData.getString(ConfigConstants.JASPER_TERM_FORM_DATA));
				} catch (JSONException e1) {
					_log.error(e1);
				}

				String fileExport = JRReportUtil.createReportFile(msgData.getString(ConfigConstants.JASPER_TERM_JRXML_TEMPLATE),
						jsonData.toJSONString(), null, file.getCanonicalPath());

				if (Validator.isNotNull(fileExport)) {
					
					_log.info("Jasper export success: " + fileExport + ", " + classPK);
					
					JSONObject msgDataIn = JSONFactoryUtil.createJSONObject();
					msgDataIn.put(ConfigConstants.JASPER_TERM_CLASS_NAME, className);
					msgDataIn.put(ConfigConstants.JASPER_TERM_CLASS_PK, classPK);
					msgDataIn.put(ConfigConstants.JASPER_TERM_USER_ID, userId);
					msgDataIn.put(ConfigConstants.JASPER_TERM_FILE_PATH, fileExport );
					
					message.put(ConfigConstants.JASPER_TERM_MSG_TO_ENGINE, msgDataIn);
					MessageBusUtil.sendMessage(ConfigConstants.JASPER_DESTINATION_DOSSIER, message);
				}												
			}
		} catch (Exception e) {
			_log.error(e);
		}

	}

	private Log _log = LogFactoryUtil.getLog(Engine.class);
}
