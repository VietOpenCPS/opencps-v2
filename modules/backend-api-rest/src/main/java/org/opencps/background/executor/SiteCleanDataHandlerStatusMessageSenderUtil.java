package org.opencps.background.executor;

import com.liferay.portal.kernel.backgroundtask.BackgroundTaskThreadLocal;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;

import org.opencps.background.siteclean.BackgroundSiteClean;
import org.opencps.dossiermgt.action.util.ConstantUtils;

public class SiteCleanDataHandlerStatusMessageSenderUtil {
	public static void sendStatusMessage(BackgroundSiteClean messageContent) {
		// Leave if no background task
		if (!BackgroundTaskThreadLocal.hasBackgroundTask()) {
			return;
		}

		// Message Creation
		Message message = createMessage(messageContent);

		// Send message to message bus
		MessageBusUtil.sendMessage(DestinationNames.BACKGROUND_TASK_STATUS,
				message);
	}

	protected static Message createMessage(BackgroundSiteClean messageContent) {

		Message message = new Message();
		message.put("backgroundTaskId",
				BackgroundTaskThreadLocal.getBackgroundTaskId());
		
		message.put(ConstantUtils.VALUE_PERCENTAGE, messageContent.getPercentage());
		message.put("executionLog", messageContent.getExecutionLog());

		return message;
	}
}
