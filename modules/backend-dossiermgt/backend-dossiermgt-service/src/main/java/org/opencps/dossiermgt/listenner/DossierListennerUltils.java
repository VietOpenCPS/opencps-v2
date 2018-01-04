package org.opencps.dossiermgt.listenner;

import java.util.Calendar;
import java.util.Date;

import org.opencps.auth.api.keys.NotificationType;
import org.opencps.communication.model.NotificationQueue;
import org.opencps.communication.service.NotificationQueueLocalServiceUtil;
import org.opencps.dossiermgt.action.util.DossierLogUtils;
import org.opencps.dossiermgt.constants.DossierStatusConstants;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierLog;
import org.opencps.dossiermgt.service.DossierLogLocalServiceUtil;
import org.opencps.usermgt.listener.ApplicantListenerMessageKeys;
import org.opencps.usermgt.listener.ApplicantListenerUtils;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class DossierListennerUltils {

	static String dossierStatus = StringPool.BLANK;
	static String notificationType = StringPool.BLANK;

	static void createDossierLog(Dossier model, boolean isUpdated, boolean isChangedStatus) {

		try {

			String content = StringPool.BLANK;
			String payload = DossierLogUtils.createPayload(null, null, model);
			Applicant applicant = ApplicantLocalServiceUtil.fetchByMappingID(model.getUserId());
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(model.getCompanyId());
			serviceContext.setUserId(model.getUserId());

			// when update dossier and dossierStatus is changed
			if (isUpdated && isChangedStatus) {

				dossierStatus = model.getDossierStatus();
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
			LogFactoryUtil.getLog(DossierListennerUltils.class.getName()).error(e);

		}

	}

	static void createNotificationQueue(DossierLog model) {

		try {

			System.out.println(">>>>>>>>>>>CREATE NOTI QUE");

			notificationType = model.getNotificationType();
			Applicant applicant = ApplicantLocalServiceUtil.fetchByMappingID(model.getUserId());
			JSONObject messageKey = JSONFactoryUtil.createJSONObject();
			messageKey.put(DossierListenerMessageKeys.DOSSIER_ID, model.getDossierId());

			switch (notificationType) {

			case NotificationType.DOSSIER_01:
				//TODO: add message key map to notificationType
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

			default:
				break;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	static void sendToApplicant(Applicant model, String notificationType, JSONObject messageKey) {
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

		messageKey.put(ApplicantListenerMessageKeys.USER_NAME, model.getApplicantName());
		messageKey.put("toName", model.getApplicantName());
		messageKey.put("toAddress", model.getContactEmail());

		String payload = ApplicantListenerUtils.getPayload(notificationType, messageKey, model.getGroupId())
				.toString();

		queue.setPayload(payload);
		queue.setExpireDate(cal.getTime());

		NotificationQueueLocalServiceUtil.addNotificationQueue(queue);
	}

	static void sendToEmployee(Employee model, String notificationType) {
		
		//TODO: info to send notification to each employee
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

		object.put(ApplicantListenerMessageKeys.USER_NAME, model.getFullName());
		object.put("toName", model.getFullName());
		object.put("toAddress", model.getEmail());

		String payload = ApplicantListenerUtils.getPayload(NotificationType.APPLICANT_01, object, model.getGroupId())
				.toString();

		queue.setPayload(payload);

		queue.setExpireDate(cal.getTime());

		NotificationQueueLocalServiceUtil.addNotificationQueue(queue);
	}
}
