package org.opencps.communication.scheduler;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.*;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.communication.constants.NotificationTemplateTerm;
import org.opencps.communication.model.NotificationQueue;
import org.opencps.communication.model.Notificationtemplate;
import org.opencps.communication.service.NotificationtemplateLocalService;
import org.opencps.communication.sms.utils.BCTSMSUtils;
import org.opencps.communication.sms.utils.LGSPSMSUtils;
import org.opencps.communication.sms.utils.ViettelSMSUtils;
import org.opencps.communication.utils.LGSPSendMailUtils;
import org.opencps.communication.utils.NotificationQueueBusinessFactoryUtil;
import org.opencps.communication.utils.NotificationUtil;
import org.opencps.kernel.context.MBServiceContextFactoryUtil;
import org.opencps.kernel.message.MBMessageEntry;
import org.opencps.kernel.message.email.MBEmailSenderFactoryUtil;
import org.opencps.kernel.message.notification.MBNotificationSenderFactoryUtil;
import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.osgi.service.component.annotations.*;
import ws.bulkSms.impl.Result;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component(immediate = true, service = NotificationMinuteScheduler.class)
public class NotificationMinuteScheduler extends BaseMessageListener {
    private volatile boolean isRunning = false;
    private static boolean flagJobMail = Validator.isNotNull(PropsUtil.get("opencps.notify.job.mail"))
            ? GetterUtil.getBoolean(PropsUtil.get("opencps.notify.job.mail"))
            : false;
    private static String agencySMS = Validator.isNotNull(PropsUtil.get("opencps.notify.agency.sms"))
            ? GetterUtil.getString(PropsUtil.get("opencps.notify.agency.sms"))
            : StringPool.BLANK;
//    private static boolean isSendLGSP = Validator.isNotNull(PropsUtil.get("opencps.send.notification.lgsp"))
//            ? GetterUtil.getBoolean(PropsUtil.get("opencps.send.notification.lgsp")) : false;
    private static int timeSendNotify = Validator.isNotNull(PropsUtil.get("opencps.time.send.notification"))
            ? Integer.valueOf(PropsUtil.get("opencps.time.send.notification"))
            : 45;
    private static boolean notificationEnable = Validator.isNotNull(PropsUtil.get("opencps.notify.enable.minute"))
            ? GetterUtil.getBoolean(PropsUtil.get("opencps.notify.enable.minute"))
            : false;

    @Override
    protected void doReceive(Message message) throws Exception {
        _log.info("=======START SEND NOTIFICATION: ====== : isRunning: "+ isRunning);
        if (!isRunning) {
            isRunning = true;
        }
        else {
            return;
        }
        try {
            processNotification(message);
        }
        catch (Exception e) {
            _log.debug(e);
        }
        isRunning = false;
        _log.info("------- END SEND NOTIFICATION: ------: isRunning: "+ isRunning);
    }
    private void processNotification(Message message) {
        if (notificationEnable) {
            List<Notificationtemplate> notificationtemplates =
                    _notificationTemplateLocalService.findByIntervalLike(
                            NotificationTemplateTerm.MINUTELY_PATTERN);
            _log.info("notificationtemplates check SIZE: " + notificationtemplates.size());
            if (notificationtemplates != null && notificationtemplates.size() > 0) {
                for (Notificationtemplate temp : notificationtemplates) {
                    List<NotificationQueue> notificationQueues =
                            NotificationQueueBusinessFactoryUtil.fetchByF_Greate_PublicationDate_Less_ExpireDate(
                                    temp.getNotificationType(), new Date(), new Date());

                    _log.info("notificationQueues check SIZE: " + notificationQueues.size());
                    if (notificationQueues != null && notificationQueues.size() > 0) {
                        for (NotificationQueue notificationQueue : notificationQueues) {
                            try {
                                ServiceContext serviceContext =
                                        MBServiceContextFactoryUtil.create(
                                                notificationQueue.getCompanyId(),
                                                notificationQueue.getGroupId(),
                                                notificationQueue.getUserId());

                                MBMessageEntry messageEntry =
                                        NotificationUtil.createMBMessageEntry(
                                                notificationQueue, temp,
                                                serviceContext);

                                _log.info("messageEntry: " + messageEntry);
                                if (flagJobMail) {
                                    //Process send SMS
                                    Result resultSendSMS = new Result("Success", new Long(1));
                                    if (messageEntry.isSendSMS() && Validator.isNotNull(messageEntry.getToTelNo())) {

                                        if ("BCT".contentEquals(agencySMS)) {
                                            _log.info("dossierNo: " + messageEntry.getDossierNo());
                                            String rsMsg = BCTSMSUtils.sendSMS(notificationQueue.getGroupId(),
                                                    notificationQueue.getClassPK(), messageEntry.getTextMessage(),
                                                    messageEntry.getEmailSubject(), messageEntry.getToTelNo(), messageEntry.getDossierNo());
                                            JSONObject jsonMsg = JSONFactoryUtil.createJSONObject(rsMsg);
                                            if (jsonMsg != null && "Success".equalsIgnoreCase(jsonMsg.getString("message"))) {
                                                resultSendSMS.setMessage("Success");
                                                resultSendSMS.setResult(1L);
                                            }
                                        } else {
                                            //Send viettel
                                            resultSendSMS = ViettelSMSUtils.sendSMS(notificationQueue.getGroupId(), messageEntry.getTextMessage(),
                                                    messageEntry.getEmailSubject(), messageEntry.getToTelNo());
                                        }
                                        _log.info("END SEND SMS");
                                    }

                                    if (messageEntry.isSendNotify() || messageEntry.isSendZalo()) {
                                        _log.info("messageEntry.isSendNotify(): " + messageEntry.isSendNotify());
                                        MBNotificationSenderFactoryUtil.send(
                                                messageEntry, messageEntry.getClassName(),
                                                serviceContext);
                                    }
                                    /* Remove queue when send SMS success Or telNo is null
                                     *
                                     * If Send SMS error, continue until expiredDate
                                     * */
                                    _log.info("resultSendSMS: " + JSONFactoryUtil.looseSerialize(resultSendSMS));
                                    if (temp.getSendEmail()) {
                                        Calendar cal = Calendar.getInstance();
                                        cal.setTime(notificationQueue.getCreateDate());
                                        cal.add(Calendar.SECOND, 30);
                                        notificationQueue.setExpireDate(cal.getTime());
                                        notificationQueue.setModifiedDate(cal.getTime());
                                        NotificationQueueBusinessFactoryUtil.update(notificationQueue, serviceContext);
                                    } else {
                                        NotificationQueueBusinessFactoryUtil
                                                .delete(notificationQueue.getNotificationQueueId(), serviceContext);
                                    }
                                } else {
//                                if (isSendLGSP) {
//                                    // Process send SMS
//                                    Result resultSendSMS = new Result("Success", new Long(1));
//                                    if (messageEntry.isSendSMS() && Validator.isNotNull(messageEntry.getToTelNo())) {
//                                        _log.info("START SEND SMS");
//                                        _log.info("messageEntry.getTextMessage(): "+messageEntry.getTextMessage());
//                                        String rsMsg = LGSPSMSUtils.sendSMS(notificationQueue.getGroupId(),
//                                                messageEntry.getTextMessage(), messageEntry.getEmailSubject(),
//                                                messageEntry.getToTelNo());
//                                        JSONObject jsonMsg = JSONFactoryUtil.createJSONObject(rsMsg);
//                                        if (jsonMsg != null && "Success".equalsIgnoreCase(jsonMsg.getString("message"))) {
//                                            resultSendSMS.setMessage("Success");
//                                            resultSendSMS.setResult(1L);
//                                        }
//                                        _log.info("END SEND SMS");
//                                    }
//
//                                    if (messageEntry.isSendEmail()) {
//                                        _log.info("messageEntry.isSendEmail(): " + messageEntry.isSendEmail());
//                                        LGSPSendMailUtils.sendLGSP(messageEntry, StringPool.BLANK);
//                                    }
//                                    if (messageEntry.isSendNotify() || messageEntry.isSendZalo()) {
//                                        _log.info("messageEntry.isSendNotify(): " + messageEntry.isSendNotify());
//                                        MBNotificationSenderFactoryUtil.send(messageEntry, messageEntry.getClassName(),
//                                                serviceContext);
//                                    }
//                                    /*
//                                     * Remove queue when send SMS success Or telNo is null
//                                     *
//                                     * If Send SMS error, continue until expiredDate
//                                     */
//                                    _log.info("resultSendSMS: " + JSONFactoryUtil.looseSerialize(resultSendSMS));
//                                    if (resultSendSMS.getResult() > 0) {
//                                        NotificationQueueBusinessFactoryUtil
//                                                .delete(notificationQueue.getNotificationQueueId(), serviceContext);
//                                    } else {
//
//                                        // hot fix: stop send noti (spam mail)
//                                        notificationQueue.setExpireDate(notificationQueue.getCreateDate());
//                                        NotificationQueueBusinessFactoryUtil.update(notificationQueue, serviceContext);
//                                    }
//                                }else{
                                    // Process send SMS
                                    Result resultSendSMS = new Result("Failed", new Long(0));
                                    if (messageEntry.isSendSMS() && Validator.isNotNull(messageEntry.getToTelNo())) {

                                        _log.info("notificationQueue.getNotificationType(): "
                                                + notificationQueue.getNotificationType());
                                        if ("BCT".contentEquals(agencySMS)) {
                                            String rsMsg = BCTSMSUtils.sendSMS(notificationQueue.getGroupId(),
                                                    notificationQueue.getClassPK(), messageEntry.getTextMessage(),
                                                    messageEntry.getEmailSubject(), messageEntry.getToTelNo(), messageEntry.getDossierNo());
                                            JSONObject jsonMsg = JSONFactoryUtil.createJSONObject(rsMsg);
                                            if (jsonMsg != null
                                                    && "Success".equalsIgnoreCase(jsonMsg.getString("message"))) {
                                                resultSendSMS.setMessage("Success");
                                                resultSendSMS.setResult(1L);
                                            }
                                        } else {
                                            // Send viettel
                                            resultSendSMS = ViettelSMSUtils.sendSMS(notificationQueue.getGroupId(),
                                                    messageEntry.getTextMessage(), messageEntry.getEmailSubject(),
                                                    messageEntry.getToTelNo());
                                        }
                                        _log.info("END SEND SMS");
                                    }

                                    if (messageEntry.isSendEmail()) {
                                        _log.info("messageEntry.isSendEmail(): " + messageEntry.isSendEmail());
                                        MBEmailSenderFactoryUtil.send(messageEntry, StringPool.BLANK, serviceContext);
                                        resultSendSMS.setResult(1L);
                                    }
                                    if (messageEntry.isSendNotify() || messageEntry.isSendZalo()) {
                                        _log.info("messageEntry.isSendNotify(): " + messageEntry.isSendNotify());
                                        MBNotificationSenderFactoryUtil.send(messageEntry, messageEntry.getClassName(),
                                                serviceContext);
                                        resultSendSMS.setResult(1L);
                                    }
                                    /*
                                     * Remove queue when send SMS success Or telNo is null
                                     *
                                     * If Send SMS error, continue until expiredDate
                                     */
                                    _log.debug("resultSendSMS: " + JSONFactoryUtil.looseSerialize(resultSendSMS));
                                    if (resultSendSMS.getResult() > 0 && resultSendSMS.getResult() == 1L) {
                                        NotificationQueueBusinessFactoryUtil
                                                .delete(notificationQueue.getNotificationQueueId(), serviceContext);
                                    } else {

                                        // hot fix: stop send noti (spam mail)
                                        notificationQueue.setExpireDate(notificationQueue.getCreateDate());
                                        NotificationQueueBusinessFactoryUtil.update(notificationQueue, serviceContext);
                                    }
//                                }
                                }
                            } catch (Exception e) {
                                _log.info(e.getMessage());
                                _log.error("Can't send message from queue " + e);
                            }
                        }
                    }
                }
            }
        }
    }
    /**
     * activate: Called whenever the properties for the component change (ala Config Admin)
     * or OSGi is activating the component.
     * @param properties The properties map from Config Admin.
     * @throws SchedulerException in case of error.
     */
    @Activate
    @Modified
    protected void activate(Map<String,Object> properties) throws SchedulerException {
        String listenerClass = getClass().getName();
        Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null, timeSendNotify, TimeUnit.SECOND);

        _schedulerEntryImpl = new SchedulerEntryImpl(getClass().getName(), jobTrigger);
        _schedulerEntryImpl = new StorageTypeAwareSchedulerEntryImpl(_schedulerEntryImpl, StorageType.MEMORY_CLUSTERED);


        if (_initialized) {
            deactivate();
        }

        _schedulerEngineHelper.register(this, _schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
        _initialized = true;
    }

    @Deactivate
    protected void deactivate() {
        if (_initialized) {
            try {
                _schedulerEngineHelper.unschedule(_schedulerEntryImpl, getStorageType());
            } catch (SchedulerException se) {
                if (_log.isWarnEnabled()) {
                    _log.warn("Unable to unschedule trigger", se);
                }
            }

            _schedulerEngineHelper.unregister(this);
        }
        _initialized = false;
    }

    /**
     * getStorageType: Utility method to get the storage type from the scheduler entry wrapper.
     * @return StorageType The storage type to use.
     */
    protected StorageType getStorageType() {
        if (_schedulerEntryImpl instanceof StorageTypeAware) {
            return ((StorageTypeAware) _schedulerEntryImpl).getStorageType();
        }

        return StorageType.PERSISTED;
    }

    /**
     * setModuleServiceLifecycle: So this requires some explanation...
     *
     * OSGi will start a component once all of it's dependencies are satisfied.  However, there
     * are times where you want to hold off until the portal is completely ready to go.
     *
     * This reference declaration is waiting for the ModuleServiceLifecycle's PORTAL_INITIALIZED
     * component which will not be available until, surprise surprise, the portal has finished
     * initializing.
     *
     * With this reference, this component activation waits until portal initialization has completed.
     * @param moduleServiceLifecycle
     */
    @Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
    protected void setModuleServiceLifecycle(ModuleServiceLifecycle moduleServiceLifecycle) {
    }

    @Reference(unbind = "-")
    protected void setTriggerFactory(TriggerFactory triggerFactory) {
        _triggerFactory = triggerFactory;
    }

    @Reference(unbind = "-")
    protected void setSchedulerEngineHelper(SchedulerEngineHelper schedulerEngineHelper) {
        _schedulerEngineHelper = schedulerEngineHelper;
    }

    private SchedulerEngineHelper _schedulerEngineHelper;
    private TriggerFactory _triggerFactory;
    private volatile boolean _initialized;
    private SchedulerEntryImpl _schedulerEntryImpl = null;
    @Reference
    private NotificationtemplateLocalService _notificationTemplateLocalService;
    private Log _log = LogFactoryUtil.getLog(NotificationMinuteScheduler.class);
}
