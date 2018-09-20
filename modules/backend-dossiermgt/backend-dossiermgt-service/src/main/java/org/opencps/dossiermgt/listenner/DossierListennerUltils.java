package org.opencps.dossiermgt.listenner;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Calendar;
import java.util.Date;

import org.opencps.auth.api.keys.NotificationType;
import org.opencps.communication.model.NotificationQueue;
import org.opencps.communication.model.Notificationtemplate;
import org.opencps.communication.service.NotificationQueueLocalServiceUtil;
import org.opencps.communication.service.NotificationtemplateLocalServiceUtil;
import org.opencps.dossiermgt.action.util.DossierLogUtils;
import org.opencps.dossiermgt.constants.DossierStatusConstants;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierLog;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.service.DossierLogLocalServiceUtil;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;

public class DossierListennerUltils {

	static void createDossierLog(Dossier model, boolean isUpdated, boolean isChangedStatus) {

		try {

			String content;
			String notificationType = StringPool.BLANK;
			String payload = DossierLogUtils.createPayload(null, null, model);
			Applicant applicant = ApplicantLocalServiceUtil.fetchByMappingID(model.getUserId());
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(model.getCompanyId());
			serviceContext.setUserId(model.getUserId());

			// when update dossier and dossierStatus is changed
			if (isUpdated && isChangedStatus) {

				String dossierStatus = model.getDossierStatus();
				switch (dossierStatus) {

				case DossierStatusConstants.WAITING:
					notificationType = NotificationType.DOSSIER_01;
					content = "On Dossiser Updated new status: " + DossierStatusConstants.WAITING;
					break;
				case DossierStatusConstants.PAYING:
					notificationType = NotificationType.DOSSIER_02;
					content = "On Dossiser Updated new status: " + DossierStatusConstants.PAYING;
					break;
				case DossierStatusConstants.PROCESSING:
					notificationType = NotificationType.DOSSIER_03;
					content = "On Dossiser Updated new status: " + DossierStatusConstants.PROCESSING;
					break;
				case DossierStatusConstants.RECEIVING:
					notificationType = NotificationType.DOSSIER_04;
					content = "On Dossiser Updated new status: " + DossierStatusConstants.RECEIVING;
					break;
				case DossierStatusConstants.CANCELLED:
					notificationType = NotificationType.DOSSIER_05;
					content = "On Dossiser Updated new status: " + DossierStatusConstants.CANCELLED;
					break;

				default:
					notificationType = StringPool.BLANK;
					content = "On Dossiser Updated new status: " + dossierStatus;
					break;
				}

			}
			// when update but status isn't changed
			else if (isUpdated) {
				content = "On Dossiser Updated";
			}
			// when create
			else {
				notificationType = "Dossier-00";
				content = "On Dossiser Created";
			}

			DossierLogLocalServiceUtil.addDossierLog(model.getGroupId(), model.getDossierId(),
					(applicant != null && Validator.isNotNull(applicant.getApplicantName()))
							? applicant.getApplicantName() : model.getUserName(),
					content, notificationType, payload, serviceContext);

		} catch (Exception e) {
//			e.printStackTrace();
			_log.error(e);
		}

	}

	static void createNotificationQueue(DossierLog model) {

		try {

			String notificationType = model.getNotificationType();
			Applicant applicant = ApplicantLocalServiceUtil.fetchByMappingID(model.getUserId());
			JSONObject messageKey = JSONFactoryUtil.createJSONObject();
			messageKey.put(DossierListenerMessageKeys.DOSSIER_ID, model.getDossierId());

			switch (notificationType) {

			// TODO: now, only send applicant. To be continue with employee
			case NotificationType.DOSSIER_01:
				// TODO: add message key map to notificationType
				sendToApplicant(applicant, notificationType, messageKey);
				break;
			case NotificationType.DOSSIER_02:
				sendToApplicant(applicant, notificationType, messageKey);
				break;
			case NotificationType.DOSSIER_03:
				sendToApplicant(applicant, notificationType, messageKey);
				break;
			case NotificationType.DOSSIER_04:
				sendToApplicant(applicant, notificationType, messageKey);
				break;
			case NotificationType.DOSSIER_05:
				sendToApplicant(applicant, notificationType, messageKey);
				break;
			case NotificationType.DOSSIER_06:
				// TODO: waiting requirement from BA
				break;
			case NotificationType.DOSSIER_07:
				sendToApplicant(applicant, notificationType, messageKey);
				break;
			case NotificationType.DOSSIER_08:
				sendToApplicant(applicant, notificationType, messageKey);
				break;
			case NotificationType.DOSSIER_09:
				// TODO: waiting requirement from BA
				break;
			case NotificationType.DOSSIER_10:
				// TODO: waiting requirement from BA
				break;

			default:
				break;

			}

		} catch (Exception e) {
			_log.info(e);
		}

	}

	static void createNotificationQueue(Registration model, boolean isUpdated) {

		try {

			String notificationType = isUpdated ? NotificationType.REGISTRATION_02 : NotificationType.REGISTRATION_01;
			Applicant applicant = ApplicantLocalServiceUtil.fetchByMappingID(model.getUserId());
			JSONObject messageKey = JSONFactoryUtil.createJSONObject();
			messageKey.put(RegistrationListenerMessageKeys.REGISTRATION_ID, model.getRegistrationId());

			switch (notificationType) {

			// TODO: now, only send applicant. To be continue with employee
			case NotificationType.REGISTRATION_01:
				// TODO: add message key map to notificationType
				sendToApplicant(applicant, notificationType, messageKey);
				break;
			case NotificationType.REGISTRATION_02:
				sendToApplicant(applicant, notificationType, messageKey);
				break;
			default:
				break;

			}

		} catch (Exception e) {
			_log.info(e);
		}

	}

	static void sendToApplicant(Applicant model, String notificationType, JSONObject messageKey) {

/*		try {
			long notificationQueueId = CounterLocalServiceUtil.increment(NotificationQueue.class.getName());
			NotificationQueue queue = null;
			Date now = new Date();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);
			queue = NotificationQueueLocalServiceUtil.createNotificationQueue(notificationQueueId);
			queue.setCreateDate(now);
			queue.setModifiedDate(now);
			queue.setGroupId(model.getGroupId());
			queue.setCompanyId(model.getCompanyId());
			queue.setNotificationType(notificationType);
			queue.setClassName(Applicant.class.getName());

			queue.setClassPK(String.valueOf(model.getPrimaryKey()));
			queue.setToUsername(model.getApplicantName());
			queue.setToUserId(model.getUserId());
			queue.setToEmail(model.getContactEmail());
			queue.setToTelNo(model.getContactTelNo());

			messageKey.put("$USER_NAME$", model.getApplicantName());
			messageKey.put("toName", model.getApplicantName());
			messageKey.put("toAddress", model.getContactEmail());

			String payload = getPayload(notificationType, messageKey, model.getGroupId()).toString();

			queue.setPayload(payload);
			queue.setExpireDate(cal.getTime());

			NotificationQueueLocalServiceUtil.addNotificationQueue(queue);
		} catch (Exception e) {
			_log.error(e);
		}*/

	}

	static void sendToEmployee(Employee model, String notificationType) {

		// TODO: info to send notification to each employee
		long notificationQueueId = CounterLocalServiceUtil.increment(NotificationQueue.class.getName());
		NotificationQueue queue = null;
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);
		queue = NotificationQueueLocalServiceUtil.createNotificationQueue(notificationQueueId);

		queue.setCreateDate(now);
		queue.setModifiedDate(now);
		queue.setGroupId(model.getGroupId());
		queue.setCompanyId(model.getCompanyId());

		queue.setNotificationType(notificationType);
		queue.setClassName(Applicant.class.getName());
		queue.setClassPK(String.valueOf(model.getPrimaryKey()));
		queue.setToUsername(model.getUserName());
		queue.setToUserId(model.getUserId());
		queue.setToEmail(model.getEmail());
		queue.setToTelNo(model.getTelNo());

		JSONObject object = JSONFactoryUtil.createJSONObject();

		object.put("$USER_NAME$", model.getFullName());
		object.put("toName", model.getFullName());
		object.put("toAddress", model.getEmail());

		String payload = getPayload(notificationType, object, model.getGroupId()).toString();

		queue.setPayload(payload);

		queue.setExpireDate(cal.getTime());

		NotificationQueueLocalServiceUtil.addNotificationQueue(queue);
	}

	public static JSONObject getPayload(String notiType, JSONObject object, long groupId) {
		JSONObject payload = JSONFactoryUtil.createJSONObject();

		try {

			Notificationtemplate notificationtemplate = NotificationtemplateLocalServiceUtil
					.fetchByF_NotificationtemplateByType(groupId, notiType);
			String body = getEmailBody(notificationtemplate, object);
			String subject = notificationtemplate.getEmailSubject();

			payload.put("toName", object.get("toName"));
			payload.put("toAddress", object.get("toAddress"));
			payload.put("subject", subject);
			payload.put("body", body);
		} catch (Exception e) {
			_log.error(e);
		}

		return payload;
	}

	private static String getEmailBody(Notificationtemplate notificationtemplate, JSONObject object) {

		try {

			String emailBody = notificationtemplate.getEmailBody();

			String[] oldSubs = buildOldSubs(object);

			String[] newSubs = buildNewSubs(object);

			return StringUtil.replace(emailBody, oldSubs, newSubs);

		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			return StringPool.BLANK;
		}

	}

	private static String[] buildOldSubs(JSONObject object) {

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < object.names().length(); i++) {
			String key = object.names().getString(i);
			// String value = (String) object.get(key);
			sb.append(key);
			sb.append(StringPool.COMMA);
		}

		return StringUtil.split(sb.toString(), StringPool.COMMA);
	}

	private static String[] buildNewSubs(JSONObject object) {

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < object.names().length(); i++) {
			String key = object.names().getString(i);
			String value = (String) object.get(key);
			sb.append(value);
			sb.append(StringPool.COMMA);
		}

		return StringUtil.split(sb.toString(), StringPool.COMMA);

	}

	static Log _log = LogFactoryUtil.getLog(DossierListennerUltils.class);

}
