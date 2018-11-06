package org.opencps.usermgt.listener;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;
import org.opencps.auth.api.keys.NotificationType;
import org.opencps.communication.model.NotificationQueue;
import org.opencps.communication.service.NotificationQueueLocalServiceUtil;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;

@Component(immediate = true, service = ModelListener.class)
public class ApplicantListener extends BaseModelListener<Applicant>{
	
	@Override
	public void onAfterUpdate(Applicant model) throws ModelListenerException {
		
		/*
		 * 03/01/2018 ThanhNv: Khong gui thong bao khi cap nhat thong tin doanh nghiep - MR_DUAN
		if (modelBefore.getActivationCode().length() != 0 && Validator.isNull(model.getActivationCode())) {
			try {
				
				
				NotificationQueue queue = null;
				
				long notificationQueueId = CounterLocalServiceUtil.increment(NotificationQueue.class.getName());
				
				queue = NotificationQueueLocalServiceUtil.createNotificationQueue(notificationQueueId);
				
				Date now = new Date();
				
				Calendar cal = Calendar.getInstance();
				
				cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);
				
				queue.setCreateDate(now);
				queue.setModifiedDate(now);
				queue.setGroupId(model.getGroupId());
				queue.setCompanyId(model.getCompanyId());
				
				queue.setNotificationType(NotificationType.USER_02);
				queue.setClassName(Applicant.class.getName());
				queue.setClassPK(String.valueOf(model.getPrimaryKey()));
				queue.setToUsername(model.getApplicantName());
				queue.setToUserId(model.getUserId());
				queue.setToEmail(model.getContactEmail());
				queue.setToTelNo(model.getContactTelNo());
				
				JSONObject object = JSONFactoryUtil.createJSONObject();
				
				
				object.put(ApplicantListenerMessageKeys.PASSWORD, modelBefore.getTmpPass());
				object.put(ApplicantListenerMessageKeys.USER_NAME, model.getApplicantName());
				object.put(ApplicantListenerMessageKeys.HOME_PAGE_URL, "http://v2.opencps.vn");
				object.put("toName", model.getApplicantName());
				object.put("toAddress", model.getContactEmail());
				
				String payload = ApplicantListenerUtils.getPayload(NotificationType.USER_02, object, model.getGroupId()).toString();
				
				queue.setPayload(payload);
				
				queue.setExpireDate(cal.getTime());
				
				NotificationQueueLocalServiceUtil.addNotificationQueue(queue);
			} catch (Exception e) {
				_log.error(e);
			}
		}
		*/
	}
	
	@Override
	public void onAfterCreate(Applicant model) throws ModelListenerException {
		
		try {

			_log.info("Applicant Log trigger!");
			if (model.getMappingUserId() > 0) {
				NotificationQueue queue = null;
				
				long notificationQueueId = CounterLocalServiceUtil.increment(NotificationQueue.class.getName());
				
				queue = NotificationQueueLocalServiceUtil.createNotificationQueue(notificationQueueId);
				
				Date now = new Date();
				
				Calendar cal = Calendar.getInstance();
				
				cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);
				
				queue.setCreateDate(now);
				queue.setModifiedDate(now);
				queue.setGroupId(model.getGroupId());
				queue.setCompanyId(model.getCompanyId());
				
				queue.setNotificationType(NotificationType.APPLICANT_01);
				queue.setClassName(Applicant.class.getName());
				queue.setClassPK(String.valueOf(model.getPrimaryKey()));
				queue.setToUsername(model.getApplicantName());
				queue.setToUserId(model.getUserId());
				queue.setToEmail(model.getContactEmail());
				queue.setToTelNo(model.getContactTelNo());
				
				JSONObject object = JSONFactoryUtil.createJSONObject();
				
	//			String guestBaseUrl = PropValues.PORTAL_DOMAIN + "/web/cong-dich-vu-cong";
				String guestBaseUrl = "http://103.21.148.29/web/bo-van-hoa";
				
				object.put(ApplicantListenerMessageKeys.ACTIVATION_CODE, model.getActivationCode());
				object.put(ApplicantListenerMessageKeys.ACTIVATION_LINK, guestBaseUrl+"/register#/xac-thuc-tai-khoan?active_user_id="+ model.getApplicantId());
				object.put(ApplicantListenerMessageKeys.USER_NAME, model.getApplicantName());
				//object.put(ApplicantListenerMessageKeys.HOME_PAGE_URL, "http://v2.opencps.vn");
				object.put("toName", model.getApplicantName());
				object.put("toAddress", model.getContactEmail());
	//			
	//			String payload1 = ApplicantListenerUtils.getPayload(NotificationType.APPLICANT_01, object, model.getGroupId()).toString();
	//			_log.info("payloadTest1: "+payload1);
				JSONObject payload = JSONFactoryUtil.createJSONObject();
				try {
					_log.info("START PAYLOAD: ");
					payload.put(
						"Applicant", JSONFactoryUtil.createJSONObject(
							JSONFactoryUtil.looseSerialize(model)));
				}
				catch (JSONException parse) {
					_log.error(parse);
				}
				_log.info("payloadTest: "+payload.toJSONString());
				queue.setPayload(payload.toJSONString());
				
				queue.setExpireDate(cal.getTime());
				
				NotificationQueueLocalServiceUtil.addNotificationQueue(queue);
				
				//binhth add user applicant to siteGroup
				
				long userId = model.getMappingUserId();
				
				GroupLocalServiceUtil.addUserGroup(userId, model.getGroupId());
			}
		} catch (Exception e) {
			_log.error(e);
		}
	}

	@Override
	public void onBeforeCreate(Applicant model) throws ModelListenerException {
//		try {
//
//			model.setApplicantName(HtmlUtil.escape(model.getApplicantName()));
//			model.setApplicantIdType(HtmlUtil.escape(model.getApplicantIdType()));
//			model.setApplicantIdNo(HtmlUtil.escape(model.getApplicantIdNo()));
//			model.setAddress(HtmlUtil.escape(model.getAddress()));
//			model.setCityCode(HtmlUtil.escape(model.getCityCode()));
//			model.setCityName(HtmlUtil.escape(model.getCityName()));
//			model.setDistrictCode(HtmlUtil.escape(model.getDistrictCode()));
//			model.setDistrictName(HtmlUtil.escape(model.getDistrictName()));
//			model.setWardCode(HtmlUtil.escape(model.getWardCode()));
//			model.setWardName(HtmlUtil.escape(model.getWardName()));
//			model.setContactName(HtmlUtil.escape(model.getContactName()));
//			model.setContactTelNo(HtmlUtil.escape(model.getContactTelNo()));
//			model.setContactEmail(HtmlUtil.escape(model.getContactEmail()));
//			model.setProfile(HtmlUtil.escape(model.getProfile()));
//			model.setActivationCode(HtmlUtil.escape(model.getActivationCode()));
////			model.setTmpPass(HtmlUtil.escape(model.getTmpPass()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(Applicant model) throws ModelListenerException {
//		try {
//
//			model.setApplicantName(HtmlUtil.escape(model.getApplicantName()));
//			model.setApplicantIdType(HtmlUtil.escape(model.getApplicantIdType()));
//			model.setApplicantIdNo(HtmlUtil.escape(model.getApplicantIdNo()));
//			model.setAddress(HtmlUtil.escape(model.getAddress()));
//			model.setCityCode(HtmlUtil.escape(model.getCityCode()));
//			model.setCityName(HtmlUtil.escape(model.getCityName()));
//			model.setDistrictCode(HtmlUtil.escape(model.getDistrictCode()));
//			model.setDistrictName(HtmlUtil.escape(model.getDistrictName()));
//			model.setWardCode(HtmlUtil.escape(model.getWardCode()));
//			model.setWardName(HtmlUtil.escape(model.getWardName()));
//			model.setContactName(HtmlUtil.escape(model.getContactName()));
//			model.setContactTelNo(HtmlUtil.escape(model.getContactTelNo()));
//			model.setContactEmail(HtmlUtil.escape(model.getContactEmail()));
//			model.setProfile(HtmlUtil.escape(model.getProfile()));
//			model.setActivationCode(HtmlUtil.escape(model.getActivationCode()));
////			model.setTmpPass(HtmlUtil.escape(model.getTmpPass()));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	
//	private static Applicant modelBefore;

	
	
//	protected void _doAddMessageQueue(
//			Activity model, List<String[]> lstInfo, String code) {
//
//			JSONObject payload = JSONFactoryUtil.createJSONObject();
//
//			try {
//				payload.put(
//					"Activity", JSONFactoryUtil.createJSONObject(
//						JSONFactoryUtil.looseSerialize(model)));
//			}
//			catch (JSONException parse) {
//				_log.error(parse);
//			}

//			if (lstInfo != null && model != null) {
//				for (String[] info : lstInfo) {
//					try {
//						NotificationQueueBusinessFactoryUtil.create(
//							model.getUserId(), model.getGroupId(), code,
//							Activity.class.getName(),
//							String.valueOf(model.getActivityId()),
//							payload.toJSONString(), model.getUserName(), info[1],
//							GetterUtil.getLong(info[3]), info[0], info[2], null,
//							null,
//							MBServiceContextFactoryUtil.create(
//								model.getCompanyId(), model.getGroupId(),
//								model.getUserId()));
//					}
//					catch (Exception e) {
//						// Nothing todo
//					}
//				}
//			}
//		}

//		protected void doAddMessageQueue(Activity model) {
//
//			List<String[]> lstInfo =
//				ResourceBusinessFactoryUtil.getJoinResourceInfo(
//					model.getGroupId(), Activity.class.getName(),
//					String.valueOf(model.getActivityId()));
//
//			_doAddMessageQueue(
//				model, lstInfo,
//				NotificationUtil.NotificationType.ACTIVITY.getCode());
//
//		}

	Log _log = LogFactoryUtil.getLog(ApplicantListener.class);

}
