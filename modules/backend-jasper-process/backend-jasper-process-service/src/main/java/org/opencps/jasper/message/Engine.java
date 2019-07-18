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
		JSONObject msgData = (JSONObject) message.get("msgToEngine");

		File file = FileUtil.createTempFile(JRReportUtil.DocType.PDF.toString());

		try {

			long userId = msgData.getLong("userId");

			long classPK = msgData.getLong("classPK");

			String className = msgData.getString("className");

			if ("org.opencps.dossiermgt.model.DossierFile".equals(className)) {
				JSONObject jsonData = JSONFactoryUtil.createJSONObject();
				try {
					jsonData = JSONFactoryUtil.createJSONObject(msgData.getString("formData"));
				} catch (JSONException e1) {
					_log.error(e1);
				}
	
				String fileExport = JRReportUtil.createReportFile(msgData.getString("jrxmlTemplate"),
						jsonData.toJSONString(), null, file.getCanonicalPath());

				if (Validator.isNotNull(fileExport)) {
				
					_log.info("Jasper export success: " + fileExport);
					
					JSONObject msgDataIn = JSONFactoryUtil.createJSONObject();
					msgDataIn.put("className", className);
					msgDataIn.put("classPK", classPK);
					msgDataIn.put("userId", userId);
					msgDataIn.put("filePath", fileExport );
					
					message.put("msgToEngine", msgDataIn);
					MessageBusUtil.sendMessage("jasper/dossier/in/destination", message);
				}				
			}
			else if ("org.opencps.dossiermgt.model.Deliverable".equals(className)) {
				JSONObject jsonData = JSONFactoryUtil.createJSONObject();
				try {
					jsonData = JSONFactoryUtil.createJSONObject(msgData.getString("formData"));
				} catch (JSONException e1) {
					_log.error(e1);
				}

				String fileExport = JRReportUtil.createReportFile(msgData.getString("jrxmlTemplate"),
						jsonData.toJSONString(), null, file.getCanonicalPath());

				if (Validator.isNotNull(fileExport)) {
					
					_log.info("Jasper export success: " + fileExport);
					
					JSONObject msgDataIn = JSONFactoryUtil.createJSONObject();
					msgDataIn.put("className", className);
					msgDataIn.put("classPK", classPK);
					msgDataIn.put("userId", userId);
					msgDataIn.put("filePath", fileExport );
					
					message.put("msgToEngine", msgDataIn);
					MessageBusUtil.sendMessage("jasper/dossier/in/destination", message);
				}								
			}
			else if ("org.opencps.deliverable.model.OpenCPSDeliverable".equals(className)) {
				JSONObject jsonData = JSONFactoryUtil.createJSONObject();
				try {
					jsonData = JSONFactoryUtil.createJSONObject(msgData.getString("formData"));
				} catch (JSONException e1) {
					_log.error(e1);
				}

				String fileExport = JRReportUtil.createReportFile(msgData.getString("jrxmlTemplate"),
						jsonData.toJSONString(), null, file.getCanonicalPath());

				if (Validator.isNotNull(fileExport)) {
					
					_log.info("Jasper export success: " + fileExport);
					
					JSONObject msgDataIn = JSONFactoryUtil.createJSONObject();
					msgDataIn.put("className", className);
					msgDataIn.put("classPK", classPK);
					msgDataIn.put("userId", userId);
					msgDataIn.put("filePath", fileExport );
					
					message.put("msgToEngine", msgDataIn);
					MessageBusUtil.sendMessage("jasper/dossier/in/destination", message);
				}								
			}
			else if ("org.opencps.dossiermgt.model.DossierDocument".equals(className)) {
				JSONObject jsonData = JSONFactoryUtil.createJSONObject();
				try {
					jsonData = JSONFactoryUtil.createJSONObject(msgData.getString("formData"));
				} catch (JSONException e1) {
					_log.error(e1);
				}

				String fileExport = JRReportUtil.createReportFile(msgData.getString("jrxmlTemplate"),
						jsonData.toJSONString(), null, file.getCanonicalPath());

				if (Validator.isNotNull(fileExport)) {
					
					_log.info("Jasper export success: " + fileExport);
					
					JSONObject msgDataIn = JSONFactoryUtil.createJSONObject();
					msgDataIn.put("className", className);
					msgDataIn.put("classPK", classPK);
					msgDataIn.put("userId", userId);
					msgDataIn.put("filePath", fileExport );
					
					message.put("msgToEngine", msgDataIn);
					MessageBusUtil.sendMessage("jasper/dossier/in/destination", message);
				}												
			}
		} catch (Exception e) {
			_log.error(e);
		}

	}

	private Log _log = LogFactoryUtil.getLog(Engine.class);
}
