/**
* OpenCPS is the open source Core Public Services software
* Copyright (C) 2016-present OpenCPS community

* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Affero General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* any later version.

* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Affero General Public License for more details.
* You should have received a copy of the GNU Affero General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>
*/

package org.opencps.kernel.util;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.SubscriptionSender;
import com.liferay.portal.kernel.util.Validator;

import org.opencps.auth.api.keys.Constants;

/**
 * 
 * @author binhth
 * @see SendMailUtil
 */
public class SendMailUtil {

	private static Log _log = LogFactoryUtil.getLog(SendMailUtil.class);

	@SuppressWarnings("deprecation")
	public static void sendEmailNotification(JSONObject payLoad, ServiceContext serviceContext) throws SystemException {

		if(Validator.isNotNull(payLoad)){
//			String fromName = PrefsPropsUtil.getString(payLoad.getLong("companyId"), PropsKeys.ADMIN_EMAIL_FROM_NAME);
			String fromName = "OpenCPS Mail System";
			
			String fromAddress = PrefsPropsUtil.getString(payLoad.getLong(Field.COMPANY_ID), PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);

			String toName = payLoad.getString(Constants.MAIL_TO_NAME);

			String toAddress = payLoad.getString(Constants.MAIL_TO_ADDRESS);

			String subject = payLoad.getString(Constants.MAIL_SUBJECT);

			String body = payLoad.getString(Constants.MAIL_BODY);
			
//			MailMessage mailMessage = new MailMessage();
			
			//mailMessage.setTo(InternetAddress);
			
			//InternetAddress d;
			
			//mailMessage.setFr
			
			//MailServiceUtil.sendEmail(mailMessage);

			SubscriptionSender subscriptionSender = new SubscriptionSender();

			subscriptionSender.setBody(body);

			subscriptionSender.setCompanyId(serviceContext.getCompanyId());

			subscriptionSender.setFrom(fromAddress, fromName);

			subscriptionSender.setHtmlFormat(true);

			subscriptionSender.setServiceContext(serviceContext);

			subscriptionSender.setSubject(subject);
			
			subscriptionSender.setUserId(payLoad.getLong(Field.USER_ID));
			
			subscriptionSender.setMailId(Constants.MAIL_USER, payLoad.getLong(Field.USER_ID));

			subscriptionSender.addRuntimeSubscribers(toAddress, toName);
		
			subscriptionSender.flushNotificationsAsync();
			
			//subscriptionSender.
			
			//UserNotificationEventLocalServiceUtil
			
			//SendUserNotification
			try {
				long toUserId = payLoad.getLong(Constants.MAIL_TO_USERID);
				long activityId = payLoad.getLong(Constants.MAIL_ACTIVITY_ID);
	        	JSONObject payloadJSON = JSONFactoryUtil.createJSONObject();
		        payloadJSON.put(Field.USER_ID, payLoad.getLong(Field.USER_ID));
		        payloadJSON.put(Constants.MAIL_TO_USERID, toUserId);
		        payloadJSON.put(Constants.MAIL_ACTIVITY_ID, activityId);
		        payloadJSON.put(Constants.MAIL_CONTENT_NOTIFICATION, payLoad.getString(Constants.MAIL_CONTENT_NOTIFICATION));
		        payloadJSON.put(Constants.MAIL_SUBJECT, payLoad.getString(Constants.MAIL_SUBJECT));
		       // NotificationEvent event = NotificationEventFactoryUtil.createNotificationEvent(timestamp, type, payloadJSONObject)
		        //UserNotificationEventLocalServiceUtil.
//		        if(toUserId == 0){
//		        	List<Participant> listMParticipants = ParticipantLocalServiceUtil.findByF_activityId(activityId);
//		        	for (MParticipant mParticipant : listMParticipants) {
//		        		UserNotificationEventLocalServiceUtil.addUserNotificationEvent(mParticipant.getUserMappingId(), 
//								ActivitiesConstants.MEETUP_MGT_CENTER, 
//								(new Date()).getTime(),
//								mParticipant.getUserId(),
//								payloadJSON.toString(),
//								false, serviceContext);
//					}
//		        }else{
//		        	UserNotificationEventLocalServiceUtil.addUserNotificationEvent(toUserId, 
//							ActivitiesConstants.MEETUP_MGT_CENTER, 
//							(new Date()).getTime(),
//							payLoad.getLong("userId"),
//							payloadJSON.toString(),
//							false, serviceContext);
//		        }
		        
			} catch (Exception e) {
//				e.printStackTrace();
				_log.error(e);
			}		
		}
		
	}
}
