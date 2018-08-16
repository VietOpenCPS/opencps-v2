package org.opencps.api.controller.util;

import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatus;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusMessageTranslator;
import com.liferay.portal.kernel.messaging.Message;

public class MyBackgroundTaskStatusMessageTranslator implements BackgroundTaskStatusMessageTranslator{

	@Override public void translate(
	        BackgroundTaskStatus backgroundTaskStatus, Message message) {

//	        backgroundTaskStatus.setAttribute(
//	            "xy", doTranslate(message.getString("xy"));

	     }
}
