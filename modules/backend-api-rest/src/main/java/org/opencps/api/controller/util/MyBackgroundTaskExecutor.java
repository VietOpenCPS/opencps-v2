package org.opencps.api.controller.util;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskResult;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskThreadLocal;
import com.liferay.portal.kernel.backgroundtask.BaseBackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplay;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;

@Component(
        immediate = true,
//        property = {"background.task.executor.class.name=com.netcracker.cabinet.background.executor.MigrationBackgroundTaskExecutor"}, 

        		property = {"background.task.executor.class.name=org.opencps.api.controller.util.MyBackgroundTaskExecutor"}, 
        // Without this property osgi will not register this as background executor/handler
        service = BackgroundTaskExecutor.class)
public class MyBackgroundTaskExecutor extends BaseBackgroundTaskExecutor {

	public MyBackgroundTaskExecutor() {
        setBackgroundTaskStatusMessageTranslator(
            new MyBackgroundTaskStatusMessageTranslator ());
        isSerial();
    }


    public BackgroundTaskResult execute(BackgroundTask backgroundTask) {

//        Map taskContextMap = backgroundTask.getTaskContextMap();

        // do your business logic here
        
//        BackgroundTaskVO messageContent = new BackgroundTaskVO();
//        messageContent.setTotalNodes(totalNodes);
     // Sending the data to util for MessageBus
//        sendStatusMessage(messageContent);
        sendStatusMessage();
        
        return null;
    }


	@Override
	public BackgroundTaskDisplay getBackgroundTaskDisplay(BackgroundTask backgroundTask) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public BackgroundTaskExecutor clone() {
		// TODO Auto-generated method stub
		return null;
	}

//	public static void sendStatusMessage(BackgroundTaskVO messageContent) {
//		
////		Message message = new Message();
//		// Background task id needs to be passed
////		message.put("backgroundTaskId", BackgroundTaskThreadLocal.getBackgroundTaskId());
//		// Pass all the necessary attributes here
//		// Send it over the built-in Message Bus to the background task status
//		// destination
////		MessageBusUtil.sendMessage(DestinationNames.BACKGROUND_TASK_STATUS, message);
//
//		// Leave if no background task
//		if (!BackgroundTaskThreadLocal.hasBackgroundTask()) {
//			return;
//		}
//
//		// Message Creation
//		Message message = createMessage(messageContent);
//
//		// Send message to message bus
//		MessageBusUtil.sendMessage(DestinationNames.BACKGROUND_TASK_STATUS, message);
//	}

	public static void sendStatusMessage() {
		Message message = new Message();
		// Background task id needs to be passed
		message.put("backgroundTaskId", BackgroundTaskThreadLocal.getBackgroundTaskId());
		// Pass all the necessary attributes here
		// Send it over the built-in Message Bus to the background task status
		// destination
		MessageBusUtil.sendMessage(DestinationNames.BACKGROUND_TASK_STATUS, message);
	}

	// if it's not serial then multiple instances of this executor can run parallel, to run it in queue mode, we use isSerial true

	@Override
	public boolean isSerial() {
	    return true;
	}
}
