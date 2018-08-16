package org.opencps.background.executor;

import java.io.Serializable;
import java.util.Map;

import org.opencps.background.siteclean.BackgroundSiteClean;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskResult;
import com.liferay.portal.kernel.backgroundtask.BaseBackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplay;
import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplayFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

@Component(
        immediate = true,
        property = {"background.task.executor.class.name=org.opencps.background.executor.SiteCleanBackgroundTaskExecutor"},
        service = BackgroundTaskExecutor.class
)
public class SiteCleanBackgroundTaskExecutor extends BaseBackgroundTaskExecutor {
	public static final Log LOGGER = LogFactoryUtil
			.getLog(SiteCleanBackgroundTaskExecutor.class);
	
    public SiteCleanBackgroundTaskExecutor() {
    	setBackgroundTaskStatusMessageTranslator(
				new SiteCleanBackgroundTaskStatusMessageTranslator());
    }
    
	@Override
	public BackgroundTaskResult execute(BackgroundTask backgroundTask) throws Exception {
        Map<String,Serializable> taskContextMap = backgroundTask.getTaskContextMap();

        Long siteId = (Long)taskContextMap.get("groupId") ;
        Group site = GroupLocalServiceUtil.fetchGroup(GetterUtil.getLong(siteId));
        
    	if(LOGGER.isDebugEnabled()){
    		LOGGER.debug("Site Name : "+ site.getName());
    	}

		BackgroundSiteClean messageContent = new BackgroundSiteClean();
		messageContent.setExecutionLog(StringPool.BLANK);
		messageContent.setPercentage(0);

		// Sending the data to util for MessageBus
    	SiteCleanDataHandlerStatusMessageSenderUtil.sendStatusMessage(messageContent);

    	Thread.sleep(20000);
    	
		// Telling the system if, background task is successful or not
    	BackgroundTaskResult backgroundTaskResult = new BackgroundTaskResult(
				BackgroundTaskConstants.STATUS_SUCCESSFUL);
		backgroundTaskResult.setStatusMessage("Success!");
		return backgroundTaskResult;	
	}

	@Override
	public BackgroundTaskDisplay getBackgroundTaskDisplay(BackgroundTask backgroundTask) {
		return BackgroundTaskDisplayFactoryUtil.getBackgroundTaskDisplay(backgroundTask);
	}

	@Override
	public BackgroundTaskExecutor clone() {
		return this;
	}

	@Override
	public boolean isSerial() {
	    return true;
	}	
}
