package org.opencps.jasper.message;

import java.io.File;

import org.opencps.jasper.utils.JRReportUtil;
import org.opencps.jasper.utils.JRReportUtil.DocType;

import com.liferay.portal.kernel.json.JSONArray;
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

public class EnginePreview implements MessageListener {

	@Override
	public void receive(Message message) throws MessageListenerException {
		// TODO Auto-generated method stub
		try {
			_doPreview(message);
		} catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}
	
	private boolean isJsonObject(String jsonString) {
        try {
            JSONArray arr = JSONFactoryUtil.createJSONArray(jsonString);
            if (arr == null)
            	return true;
            else
            	return false;
        } catch (JSONException ex) {
            try {
                JSONObject obj = JSONFactoryUtil.createJSONObject(jsonString);
                if (obj == null) {
                	return false;
                }
                else {
                	return true;
                }
            } catch (JSONException e) {
                return false;
            }
        }
 	}
	
	private void _doPreview(Message message) {
		_log.info("DoPreview.........");
				
		String formReport = message.getString("formReport");
		
		String formData = message.getString("formData");
		String className = message.getString("className");
		_log.info("Object or array: " + isJsonObject(formData));
		if ("org.opencps.dossiermgt.model.DossierDocument".equals(className)) {
			File file = FileUtil.createTempFile(JRReportUtil.DocType.PDF.toString());
			
			try {
				//create file
				JRReportUtil.createReportFile(formReport,
						formData, null, file.getCanonicalPath());
				
				Message responseMessage = MessageBusUtil.createResponseMessage(message);
				
				//JSONObject payload = JSONFactoryUtil.createJSONObject();
				
				//payload.put("status", "DONE");
				//.put("", value)
	
				responseMessage.setPayload(file.getCanonicalPath());
				responseMessage.put("fileDes", file.getCanonicalPath());
				
				MessageBusUtil.sendMessage(responseMessage.getDestinationName(), responseMessage);
	
			} catch (Exception e) {
				_log.error("Generate file exception.........");
				}
		} else {
			String reportType = message.contains("reportType") ? message.getString("reportType") : "pdf";
			File file = null;
			if (reportType.equals("excel")) {
				file = FileUtil.createTempFile(JRReportUtil.DocType.XLS.toString());						
			}
			else if (reportType.equals("word")) {
				file = FileUtil.createTempFile(JRReportUtil.DocType.DOC.toString());	
			}
			else {
				file = FileUtil.createTempFile(JRReportUtil.DocType.PDF.toString());
			}			
			
			if (isJsonObject(formData)) {
				try {
					//create file
					JRReportUtil.createReportFile(formReport,
							formData, null, file.getCanonicalPath(), reportType);
						
					Message responseMessage = MessageBusUtil.createResponseMessage(message);
						
					//JSONObject payload = JSONFactoryUtil.createJSONObject();
						
					//payload.put("status", "DONE");
					//.put("", value)
			
					responseMessage.setPayload(file.getCanonicalPath());
					responseMessage.put("fileDes", file.getCanonicalPath());
						
					MessageBusUtil.sendMessage(responseMessage.getDestinationName(), responseMessage);
			
				} catch (Exception e) {
					_log.error("Generate file exception.........");
				}			
			}
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
			
			JSONObject jsonData = JSONFactoryUtil.createJSONObject();
			
			try {
				jsonData = JSONFactoryUtil.createJSONObject(msgData.getString("formData"));
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Log _log = LogFactoryUtil.getLog(EnginePreview.class);
}
