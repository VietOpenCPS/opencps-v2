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

package backend.utils;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.SubscriptionSender;
import com.liferay.portal.kernel.util.Validator;

import backend.auth.api.keys.Constants;
import backend.auth.api.keys.ModelNameKeys;

/**
 * 
 * @author binhth
 * @see SendMailUtils
 */
public class SendMailUtils {

	@SuppressWarnings("deprecation")
	public static void sendEmailNotification(JSONObject payLoad, ServiceContext serviceContext) throws SystemException {

		if(Validator.isNotNull(payLoad)){
//			String fromName = PrefsPropsUtil.getString(payLoad.getLong("companyId"), PropsKeys.ADMIN_EMAIL_FROM_NAME);
			String fromName = Constants.SEND_MAIL_UTIL_FROM_NAME;
			
			String fromAddress = PrefsPropsUtil.getString(payLoad.getLong(ModelNameKeys.SEND_MAIL_UTIL_KEY_COMPANY_ID), PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);

			String toName = payLoad.getString(ModelNameKeys.SEND_MAIL_UTIL_KEY_TO_NAME);

			String toAddress = payLoad.getString(ModelNameKeys.SEND_MAIL_UTIL_KEY_TO_ADDRESS);

			String subject = payLoad.getString(ModelNameKeys.SEND_MAIL_UTIL_KEY_SUBJECT);

			String body = payLoad.getString(ModelNameKeys.SEND_MAIL_UTIL_KEY_BODY);

			SubscriptionSender subscriptionSender = new SubscriptionSender();

			subscriptionSender.setBody(body);

			subscriptionSender.setCompanyId(serviceContext.getCompanyId());

			subscriptionSender.setFrom(fromAddress, fromName);

			subscriptionSender.setHtmlFormat(Constants.SEND_MAIL_UTIL_HTML_FORMAT);

			subscriptionSender.setServiceContext(serviceContext);

			subscriptionSender.setSubject(subject);
			
			subscriptionSender.setUserId(payLoad.getLong(ModelNameKeys.SEND_MAIL_UTIL_KEY_USER_ID));
			
			subscriptionSender.setMailId(ModelNameKeys.SEND_MAIL_UTIL_KEY_USER, payLoad.getLong(ModelNameKeys.SEND_MAIL_UTIL_KEY_USER_ID));

			subscriptionSender.addRuntimeSubscribers(toAddress, toName);

			subscriptionSender.flushNotificationsAsync();
			
//			//SendUserNotification
//			try {
//				long toUserId = payLoad.getLong("toUserId");
//				long activityId = payLoad.getLong("activityId");
//	        	JSONObject payloadJSON = JSONFactoryUtil.createJSONObject();
//		        payloadJSON.put("userId", payLoad.getLong("userId"));
//		        payloadJSON.put("toUserId", toUserId);
//		        payloadJSON.put("activityId", activityId);
//		        payloadJSON.put("contentNotification", payLoad.getString("contentNotification"));
//		        payloadJSON.put("subject", payLoad.getString("subject"));
//		        if(toUserId == 0){
//		        	List<MParticipant> listMParticipants = MParticipantLocalServiceUtil.findByF_activityId(activityId);
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
//		        
//			} catch (PortalException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}		
		}
		
	}
}
