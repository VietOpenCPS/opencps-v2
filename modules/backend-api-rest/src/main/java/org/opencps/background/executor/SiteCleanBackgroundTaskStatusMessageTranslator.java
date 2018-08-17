package org.opencps.background.executor;

import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatus;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusMessageTranslator;
import com.liferay.portal.kernel.messaging.Message;

public class SiteCleanBackgroundTaskStatusMessageTranslator implements BackgroundTaskStatusMessageTranslator {

	@Override
	public void translate(BackgroundTaskStatus backgroundTaskStatus, Message message) {
    	backgroundTaskStatus.setAttribute(
    			"percentage",
    			message.getInteger("percentage"));	
    	backgroundTaskStatus.setAttribute(
    			"executionLog",
    			message.getString("executionLog"));	
	}

}
