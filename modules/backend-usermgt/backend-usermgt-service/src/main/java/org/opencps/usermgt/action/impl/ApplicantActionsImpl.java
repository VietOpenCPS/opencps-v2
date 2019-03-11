package org.opencps.usermgt.action.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.auth.api.keys.NotificationType;
import org.opencps.communication.model.NotificationQueue;
import org.opencps.communication.service.NotificationQueueLocalServiceUtil;
import org.opencps.usermgt.action.ApplicantActions;
import org.opencps.usermgt.listener.ApplicantListenerMessageKeys;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.util.ServiceProps;
import org.opencps.usermgt.service.util.UserMgtUtils;

public class ApplicantActionsImpl implements ApplicantActions {

	@Override
	public Applicant register(ServiceContext context, long groupId, String applicantName, String applicantIdType,
			String applicantIdNo, String applicantIdDate, String contactEmail, String password)
			throws PortalException, SystemException {

		Applicant applicant = ApplicantLocalServiceUtil.updateApplication(context,groupId, 0, applicantName, applicantIdType,
				applicantIdNo, applicantIdDate, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, contactEmail,
				StringPool.BLANK, password);

		return applicant;
	}

	public Applicant register(ServiceContext context, long groupId,  String applicantName, String applicantIdType,
			String applicantIdNo, String applicantIdDate, String contactEmail, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String password) throws PortalException, SystemException {

		Applicant applicant = ApplicantLocalServiceUtil.updateApplication(context, groupId, 0l, applicantName, applicantIdType,
				applicantIdNo, applicantIdDate, address, cityCode, cityName, districtCode, districtName, wardCode,
				wardName, contactName, contactTelNo, contactEmail, StringPool.BLANK, password);

		return applicant;
	}

	@Override
	public Applicant removeApplicant(ServiceContext context, long applicantId) throws PortalException {

		return ApplicantLocalServiceUtil.removeApplicant(applicantId);
	}

	@Override
	public JSONObject getApplicants(ServiceContext context, long userId, long companyId, long groupId,
			LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = ApplicantLocalServiceUtil.searchLucene(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = ApplicantLocalServiceUtil.countLucene(params, searchContext);

			result.put("total", total);

		} catch (Exception e) {
			_log.error(e);
		}

		return result;

	}

	@Override
	public Applicant getApplicantDetail(ServiceContext context, long applicantId) throws PortalException {
		return ApplicantLocalServiceUtil.getApplicant(applicantId);
	}

	@Override
	public String getApplicantByUserId(ServiceContext serviceContext) throws PortalException {
		return JSONFactoryUtil.looseSerialize(ApplicantLocalServiceUtil.fetchByMappingID(serviceContext.getUserId()));
	}
	
	@Override
	public Applicant updateApplicant(ServiceContext context,long groupId, long applicantId, String applicantName, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail) throws PortalException {

		Applicant applicant = ApplicantLocalServiceUtil.updateApplication(context, groupId,  applicantId, applicantName,
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, address, cityCode, cityName, districtCode,
				districtName, wardCode, wardName, contactName, contactTelNo, contactEmail, StringPool.BLANK,
				StringPool.BLANK);

		return applicant;
	}

	@Override
	public Applicant updateProfile(ServiceContext context, long groupId, long applicantId, String profile) throws PortalException {
		// TODO Auto-generated method stub
		Applicant applicant = ApplicantLocalServiceUtil.updateApplication(context, groupId, applicantId, StringPool.BLANK,
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, profile, StringPool.BLANK);

		return applicant;
	}

	@Override
	public Applicant removeProfile(ServiceContext context, long applicantId) throws PortalException {
		// TODO Auto-generated method stub
		return ApplicantLocalServiceUtil.removeProfile(applicantId);
	}

	@Override
	public Applicant lockApplicant(ServiceContext context, long applicantId) throws PortalException {
		Applicant applicant = ApplicantLocalServiceUtil.lockoutApplicant(applicantId);
		return applicant;
	}

	@Override
	public Applicant activationApplicant(ServiceContext context, long applicantId, String activationCode)
			throws PortalException {

		Applicant applicant = ApplicantLocalServiceUtil.getApplicant(applicantId);

		if (Validator.isNotNull(applicant.getActivationCode())
				&& applicant.getActivationCode().toLowerCase().contentEquals(activationCode.toLowerCase())) {
			return ApplicantLocalServiceUtil.activateApplicant(applicantId, context);
		} else {
			return null;
		}

	}
	
	@Override
	public Applicant getApplicantByMappingUserId(long userId) throws PortalException {
		return ApplicantLocalServiceUtil.fetchByMappingID(userId);
	}

	Log _log = LogFactoryUtil.getLog(ApplicantActionsImpl.class);

	@Override
	public void updateApplicantDB(long userId, long groupId, String applicantIdNo, String appliantName,
			String applicantIdType, Date applicantIdDate, String contactEmail, String contactTelNo,
			ServiceContext serviceContext) throws PortalException {

		ApplicantLocalServiceUtil.updateApplicationDB(groupId, userId, 0l, applicantIdNo, appliantName, applicantIdType,
				applicantIdDate, contactEmail, contactTelNo, serviceContext);

	}

	@Override
	public JSONObject createApplicantAccount(long userId, long companyId, long groupId, long id, String screenName,
			String email, boolean exist, ServiceContext context) throws PortalException {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		//validateAdd(applicantName, applicantIdType, applicantIdNo, applicantIdDate);
		//validateApplicantDuplicate(groupId, context.getCompanyId(), contactTelNo, applicantIdNo, contactEmail);

		Applicant applicant = ApplicantLocalServiceUtil.fetchApplicant(id);
		if (applicant != null) {
			if (applicant.getMappingUserId() > 0 && Validator.isNotNull(applicant.getActivationCode())) {
				//Active account register
				ApplicantLocalServiceUtil.activateApplicant(applicant.getApplicantId(), context);
				//Send Email
				NotificationQueue queue = null;
				long notificationQueueId = CounterLocalServiceUtil.increment(NotificationQueue.class.getName());
				queue = NotificationQueueLocalServiceUtil.createNotificationQueue(notificationQueueId);
				
				Date now = new Date();
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);
				
				queue.setCreateDate(now);
				queue.setModifiedDate(now);
				queue.setGroupId(applicant.getGroupId());
				queue.setCompanyId(applicant.getCompanyId());
				
				queue.setNotificationType(NotificationType.APPLICANT_02);
				queue.setClassName(Applicant.class.getName());
				queue.setClassPK(String.valueOf(applicant.getPrimaryKey()));
				queue.setToUsername(applicant.getApplicantName());
				queue.setToUserId(applicant.getUserId());
				queue.setToEmail(applicant.getContactEmail());
				queue.setToTelNo(applicant.getContactTelNo());
				
				JSONObject payload = JSONFactoryUtil.createJSONObject();
				try {
					payload.put(
						"Applicant", JSONFactoryUtil.createJSONObject(
							JSONFactoryUtil.looseSerialize(applicant)));
				}
				catch (JSONException parse) {
					_log.error(parse);
				}
				queue.setPayload(payload.toJSONString());
				queue.setExpireDate(cal.getTime());
				NotificationQueueLocalServiceUtil.addNotificationQueue(queue);
			} else {
				Role roleDefault = RoleLocalServiceUtil.getRole(context.getCompanyId(), ServiceProps.APPLICANT_ROLE_NAME);
				//String activationCode = PwdGenerator.getPassword(ServiceProps.PASSWORD_LENGHT);

				boolean autoPassword = false;
				boolean autoScreenName = true;
				boolean sendEmail = false;

				long[] groupIds = new long [] {groupId};
				long[] organizationIds = null;
				long[] roleIds = null;
				long[] userGroupIds = null;

				if (Validator.isNull(screenName)) {
					screenName = email.substring(0, email.indexOf(StringPool.AT));
				}

				//
				String secretKey1 = PwdGenerator.getPassword(2, new String[] { "0123456789" });
				String secretKey2 = PwdGenerator.getPassword(2, new String[] { "ABCDEFGHIJKLMNOPQRSTUVWXYZ" });
				String secretKey3 = PwdGenerator.getPassword(2, new String[] { "abcdefghijklmnopqrstuvwxyz" });
				String secretKey4 = PwdGenerator.getPassword(1 , new String[] { "@$" });
				String secretKey5 = PwdGenerator.getPassword(4, new String[] { "0123456789", "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
						"abcdefghijklmnopqrstuvwxyz", "~!@#$%^&*" });
				String password = secretKey1 + secretKey2 + secretKey3 + secretKey4 + secretKey5;

				//String password = PwdGenerator.getPassword(ServiceProps.PASSWORD_LENGHT);

				String firstName = ("citizen".equals(applicant.getApplicantIdType()) ? "Ông/bà"
						: ("business".equals(applicant.getApplicantIdType()) ? "Quý công ty" : "Tổ chức"));
				String lastName = applicant.getApplicantName();

				UserMgtUtils.SplitName spn = UserMgtUtils.splitName(firstName, lastName);

				// add default role
				if (Validator.isNotNull(roleDefault)) {
					roleIds = new long[] { roleDefault.getRoleId() };
				}

				Role adminRole = RoleLocalServiceUtil.getRole(context.getCompanyId(), ServiceProps.ADM_ROLE_NAME);

				List<User> adminUsers = UserLocalServiceUtil.getRoleUsers(adminRole.getRoleId());

				long creatorUserId = 0;

				if (adminUsers.size() != 0) {
					creatorUserId = adminUsers.get(0).getUserId();
				}

				Calendar calendar = Calendar.getInstance();

				calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 20);

				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH); // jan = 0, dec = 11
				int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
				//_log.info("CREATE APPLICANT: " + spn.getLastName() + "," + spn.getFirstName() + "," + spn.getMidName());
				User mappingUser = UserLocalServiceUtil.addUserWithWorkflow(creatorUserId, context.getCompanyId(), autoPassword,
							password, password, autoScreenName, screenName, applicant.getContactEmail(), 0l, StringPool.BLANK,
							LocaleUtil.getDefault(), spn.getFirstName(), spn.getMidName(), spn.getLastName(), 0, 0, true, month,
							dayOfMonth, year, ServiceProps.APPLICANT_JOB_TITLE, groupIds, organizationIds, roleIds,
							userGroupIds, sendEmail, context);
				//_log.info("MAPPING USER: " + mappingUser.getLastName() + "," + mappingUser.getFullName());
				//mappingUser.setStatus(WorkflowConstants.STATUS_PENDING);
				UserLocalServiceUtil.updateStatus(mappingUser.getUserId(), WorkflowConstants.STATUS_APPROVED, context);
				UserLocalServiceUtil.updatePassword(mappingUser.getUserId(), password, password, false, true);

				// Update applicant
				applicant.setMappingUserId(mappingUser.getUserId());
				applicant.setActivationCode(StringPool.BLANK);
				applicant.setTmpPass(password);
				
				ApplicantLocalServiceUtil.updateApplicant(applicant);
				//Send mail
				//_log.info("Applicant Log trigger!");
				if (applicant.getMappingUserId() > 0) {
					NotificationQueue queue = null;
					
					long notificationQueueId = CounterLocalServiceUtil.increment(NotificationQueue.class.getName());
					
					queue = NotificationQueueLocalServiceUtil.createNotificationQueue(notificationQueueId);
					
					Date now = new Date();
					
					Calendar cal = Calendar.getInstance();
					
					cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);
					
					queue.setCreateDate(now);
					queue.setModifiedDate(now);
					queue.setGroupId(applicant.getGroupId());
					queue.setCompanyId(applicant.getCompanyId());
					
					queue.setNotificationType(NotificationType.APPLICANT_02);
					queue.setClassName(Applicant.class.getName());
					queue.setClassPK(String.valueOf(applicant.getPrimaryKey()));
					queue.setToUsername(applicant.getApplicantName());
					queue.setToUserId(applicant.getUserId());
					queue.setToEmail(applicant.getContactEmail());
					queue.setToTelNo(applicant.getContactTelNo());
					
					//JSONObject object = JSONFactoryUtil.createJSONObject();
					
		//			String guestBaseUrl = PropValues.PORTAL_DOMAIN + "/web/cong-dich-vu-cong";
					//String guestBaseUrl = "http://103.21.148.29/web/bo-van-hoa";
					
					//object.put(ApplicantListenerMessageKeys.ACTIVATION_CODE, applicant.getActivationCode());
					//object.put(ApplicantListenerMessageKeys.ACTIVATION_LINK, guestBaseUrl+"/register#/xac-thuc-tai-khoan?active_user_id="+ applicant.getApplicantId());
					//object.put(ApplicantListenerMessageKeys.USER_NAME, applicant.getApplicantName());
					//object.put(ApplicantListenerMessageKeys.HOME_PAGE_URL, "http://v2.opencps.vn");
					//object.put("toName", applicant.getApplicantName());
					//object.put("toAddress", applicant.getContactEmail());
		//			
		//			String payload1 = ApplicantListenerUtils.getPayload(NotificationType.APPLICANT_01, object, applicant.getGroupId()).toString();
		//			_log.info("payloadTest1: "+payload1);
					JSONObject payload = JSONFactoryUtil.createJSONObject();
					try {
						//_log.info("START PAYLOAD: ");
						payload.put(
							"Applicant", JSONFactoryUtil.createJSONObject(
								JSONFactoryUtil.looseSerialize(applicant)));
					}
					catch (JSONException parse) {
						_log.error(parse);
					}
					//_log.info("payloadTest: "+payload.toJSONString());
					queue.setPayload(payload.toJSONString());
					queue.setExpireDate(cal.getTime());
					NotificationQueueLocalServiceUtil.addNotificationQueue(queue);
					
					//binhth add user applicant to siteGroup
					GroupLocalServiceUtil.addUserGroup(applicant.getMappingUserId(), applicant.getGroupId());
					//Add applicant to search
					String applicantIdNo = applicant.getApplicantIdNo();
					String applicantName = applicant.getApplicantName();
					String applicantIdType = applicant.getApplicantIdType();
					Date applicantIdDate = applicant.getApplicantIdDate();
					String address = applicant.getAddress();
					String cityCode = applicant.getCityCode();
					String cityName = applicant.getCityName();
					String districtCode = applicant.getDistrictCode();
					String districtName = applicant.getDistrictName();
					String wardCode = applicant.getWardCode();
					String wardName = applicant.getWardName();
					String contactName = applicant.getContactName();
					String contactTelNo = applicant.getContactTelNo();
					String contactEmail = applicant.getContactEmail();
					
					ApplicantLocalServiceUtil.updateApplicant(0l, applicant.getMappingUserId(), companyId, applicantName, applicantIdType, applicantIdNo,
							applicantIdDate, address, cityCode, cityName, districtCode, districtName, wardCode, wardName,
							contactName, contactTelNo, contactEmail);
				}
			}
		}

		return jsonObject;
	}

}
