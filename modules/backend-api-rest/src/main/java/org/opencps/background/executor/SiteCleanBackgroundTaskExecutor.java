package org.opencps.background.executor;

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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import java.io.Serializable;
import java.util.Map;

import org.opencps.api.controller.util.SystemUtils;
import org.opencps.background.siteclean.BackgroundSiteClean;
import org.osgi.service.component.annotations.Component;

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
        int progress = 0;
        
    	if(LOGGER.isDebugEnabled()){
    		LOGGER.debug("Site Name : "+ site.getName());
    	}

		BackgroundSiteClean messageContent = new BackgroundSiteClean();
		messageContent.setExecutionLog("Chuẩn bị làm sạch dữ liệu trên " + site.getName());
		messageContent.setPercentage(progress);
		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(site.getCompanyId());
		
		// Clean notification template
		SystemUtils.cleanNotificationTemplate(siteId, 0l, serviceContext);
		SystemUtils.cleanNotificationQueue(siteId);
		
		LOGGER.info("Clean notification complete!");

		progress += 3;		
    	SiteCleanDataHandlerStatusMessageSenderUtil.sendStatusMessage(messageContent);
		messageContent.setExecutionLog("Đã làm sạch mẫu thông báo");
		messageContent.setPercentage(progress);

		progress += 3;
		SystemUtils.cleanDossierFile(siteId, 0l, serviceContext);
		LOGGER.info("Clean dossierfile complete!");
		messageContent.setExecutionLog("Đã làm sạch thành phần hồ sơ");
		messageContent.setPercentage(progress);
		SiteCleanDataHandlerStatusMessageSenderUtil.sendStatusMessage(messageContent);

		progress += 3;
		SystemUtils.cleanDossierLog(siteId, 0l, serviceContext);
		LOGGER.info("Clean dossierlog complete!");
		messageContent.setExecutionLog("Đã làm sạch nhật ký hồ sơ");
		messageContent.setPercentage(progress);
		SiteCleanDataHandlerStatusMessageSenderUtil.sendStatusMessage(messageContent);
		
		progress += 3;
		SystemUtils.cleanDossier(siteId, 0l, serviceContext);
		LOGGER.info("Clean dossier complete!");
		messageContent.setExecutionLog("Đã làm sạch hồ sơ");
		messageContent.setPercentage(progress);
		SiteCleanDataHandlerStatusMessageSenderUtil.sendStatusMessage(messageContent);
		
		messageContent.setExecutionLog("Hoàn thành làm sạch dữ liệu");
		messageContent.setPercentage(100);

		// Sending the data to util for MessageBus
    	SiteCleanDataHandlerStatusMessageSenderUtil.sendStatusMessage(messageContent);

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
