package org.opencps.background.executor;

import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatus;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusMessageTranslator;
import com.liferay.portal.kernel.messaging.Message;

import org.opencps.dossiermgt.action.util.ConstantUtils;

public class SiteCleanBackgroundTaskStatusMessageTranslator implements BackgroundTaskStatusMessageTranslator {

	@Override
	public void translate(BackgroundTaskStatus backgroundTaskStatus, Message message) {
    	backgroundTaskStatus.setAttribute(
    			ConstantUtils.VALUE_PERCENTAGE,
    			message.getInteger(ConstantUtils.VALUE_PERCENTAGE));	
    	backgroundTaskStatus.setAttribute(
    			"executionLog",
    			message.getString("executionLog"));	
	}

}
