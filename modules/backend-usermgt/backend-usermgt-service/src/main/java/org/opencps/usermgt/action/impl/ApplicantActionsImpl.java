package org.opencps.usermgt.action.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import javax.ws.rs.core.HttpHeaders;
import javax.xml.bind.DatatypeConverter;

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.opencps.auth.api.keys.NotificationType;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.backend.usermgt.service.util.ConfigConstants;
import org.opencps.backend.usermgt.service.util.ConfigProps;
import org.opencps.communication.model.NotificationQueue;
import org.opencps.communication.service.NotificationQueueLocalServiceUtil;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.usermgt.action.ApplicantActions;
import org.opencps.usermgt.constants.ApplicantTerm;
import org.opencps.usermgt.listener.ApplicantListenerMessageKeys;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.util.UserMgtUtils;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.text.producer.DefaultTextProducer;

public class ApplicantActionsImpl implements ApplicantActions {

	@Override
	public Applicant register(ServiceContext context, long groupId, String applicantName, String applicantIdType,
			String applicantIdNo, String applicantIdDate, String contactEmail, String password)
			throws PortalException, SystemException {

		Applicant applicant = ApplicantLocalServiceUtil.updateApplication(context, groupId, 0, applicantName,
				applicantIdType, applicantIdNo, applicantIdDate, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
				StringPool.BLANK, contactEmail, StringPool.BLANK, password);

		return applicant;
	}

	public Applicant register(ServiceContext context, long groupId,  String applicantName, String applicantIdType,
			String applicantIdNo, String applicantIdDate, String contactEmail, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String profile, String password) throws PortalException, SystemException {

		Applicant applicant = ApplicantLocalServiceUtil.updateApplication(context, groupId, 0l, applicantName,
				applicantIdType, applicantIdNo, applicantIdDate, address, cityCode, cityName, districtCode,
				districtName, wardCode, wardName, contactName, contactTelNo, contactEmail, profile, password);

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

			result.put(ApplicantTerm.DATA, hits.toList());

			long total = ApplicantLocalServiceUtil.countLucene(params, searchContext);

			result.put(ApplicantTerm.TOTAL, total);

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
		return serviceContext.getUserId() > 0 ? JSONFactoryUtil.looseSerialize(ApplicantLocalServiceUtil.fetchByMappingID(serviceContext.getUserId())) : StringPool.BLANK;
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
	public Applicant updateApplicant(ServiceContext context,long groupId, long applicantId, String applicantName, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail, String profile) throws PortalException {

		Applicant applicant = ApplicantLocalServiceUtil.updateApplication(context, groupId,  applicantId, applicantName,
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, address, cityCode, cityName, districtCode,
				districtName, wardCode, wardName, contactName, contactTelNo, contactEmail, profile,
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
	public Applicant activationLGSPApplicant(ServiceContext context, long applicantId, String activationCode)
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
		return userId > 0 ? ApplicantLocalServiceUtil.fetchByMappingID(userId) : null;
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
	public Applicant registerApproved(ServiceContext context, long groupId, String applicantName, String applicantIdType,
			String applicantIdNo, String applicantIdDate, String contactEmail, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String profile, String secrectKey) throws PortalException {

		return ApplicantLocalServiceUtil.updateApplicantApproved(groupId, 20139, 0l, applicantIdNo, applicantName,
				applicantIdType, new Date(), contactEmail, contactTelNo, secrectKey, context);

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
					payload.put(ApplicantListenerMessageKeys.APPLICANT,
							JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(applicant)));
				} catch (JSONException parse) {
					_log.error(parse);
				}
				queue.setPayload(payload.toJSONString());
				queue.setExpireDate(cal.getTime());
				NotificationQueueLocalServiceUtil.addNotificationQueue(queue);
			} else {
				Role roleDefault = RoleLocalServiceUtil.getRole(context.getCompanyId(),
						ConfigProps.get(ConfigConstants.ROLE_APPLICANT));

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

				String secretKey1 = PwdGenerator.getPassword(2,
						new String[] { ConfigProps.get(ConfigConstants.APPLICANT_NUMBER) });
				String secretKey2 = PwdGenerator.getPassword(2,
						new String[] { ConfigProps.get(ConfigConstants.APPLICANT_TEXT_UPCASE) });
				String secretKey3 = PwdGenerator.getPassword(2,
						new String[] { ConfigProps.get(ConfigConstants.APPLICANT_TEXT_LOWCASE) });
				String secretKey4 = PwdGenerator.getPassword(1,
						new String[] { ConfigProps.get(ConfigConstants.APPLICANT_TEXT_SPECIAL) });
				String secretKey5 = PwdGenerator.getPassword(4,
						new String[] { ConfigProps.get(ConfigConstants.APPLICANT_NUMBER),
								ConfigProps.get(ConfigConstants.APPLICANT_TEXT_UPCASE),
								ConfigProps.get(ConfigConstants.APPLICANT_TEXT_LOWCASE),
								ConfigProps.get(ConfigConstants.APPLICANT_TEXT_SPECIAL_ALL) });
				String password = secretKey1 + secretKey2 + secretKey3 + secretKey4 + secretKey5;

				String applicantTypeCitizen = ConfigProps.get(ConfigConstants.APPLICANT_TYPE_CITIZEN);
				String applicantTypeBussiness = ConfigProps.get(ConfigConstants.APPLICANT_TYPE_BUSSINESS);
				String headerUser = LanguageUtil.get(LocaleUtil.fromLanguageId(ConfigConstants.LANGUAGE_ID),
						ConfigConstants.HEADER_USER);
				String headerCompany = LanguageUtil.get(LocaleUtil.fromLanguageId(ConfigConstants.LANGUAGE_ID),
						ConfigConstants.HEADER_COMPANY);
				String headerBussiness = LanguageUtil.get(LocaleUtil.fromLanguageId(ConfigConstants.LANGUAGE_ID),
						ConfigConstants.HEADER_BUSSINESS);
				String firstName = (applicantTypeCitizen.equals(applicant.getApplicantIdType()) ? headerUser
						: (applicantTypeBussiness.equals(applicant.getApplicantIdType()) ? headerCompany
								: headerBussiness));
				String lastName = applicant.getApplicantName();

				UserMgtUtils.SplitName spn = UserMgtUtils.splitName(firstName, lastName);

				// add default role
				if (Validator.isNotNull(roleDefault)) {
					roleIds = new long[] { roleDefault.getRoleId() };
				}

				Role adminRole = RoleLocalServiceUtil.getRole(context.getCompanyId(),
						ConfigProps.get(ConfigConstants.ROLE_ADMIN));

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
				// _log.info("CREATE APPLICANT: " + spn.getLastName() + "," + spn.getFirstName()
				// + "," + spn.getMidName());
				User mappingUser = UserLocalServiceUtil.addUserWithWorkflow(creatorUserId, context.getCompanyId(),
						autoPassword, password, password, autoScreenName, screenName, applicant.getContactEmail(), 0l,
						StringPool.BLANK, LocaleUtil.getDefault(), spn.getFirstName(), spn.getMidName(),
						spn.getLastName(), 0, 0, true, month, dayOfMonth, year,
						ConfigProps.get(ConfigConstants.APPLICANT_JOB_TITLE), groupIds, organizationIds, roleIds,
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
						// _log.info("START PAYLOAD: ");
						payload.put(ApplicantListenerMessageKeys.APPLICANT,
								JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(applicant)));
					} catch (JSONException parse) {
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

	@Override
	public void importApplicantDB(long userId, long groupId, String applicantIdNo, String applicantName,
			String applicantIdType, String applicantIdDate, String contactEmail, String contactTelNo, String address,
			String cityCode, String districtCode, String wardCode, String contactName,
			String profile, boolean lgsp, ServiceContext serviceContext) throws PortalException {
		
		// Check exits applicantIdNo
		if (lgsp && (Validator.isNotNull(applicantIdNo) || Validator.isNotNull(contactEmail))) {
			int flagUser = 0;
			long mappingUserId = 0;
			if (groupId > 0) {
				if (Validator.isNotNull(applicantIdNo)) {
					List<Applicant> appList = ApplicantLocalServiceUtil.findByAppIds(applicantIdNo);
					if (appList != null && appList.size() > 0) {
						for (Applicant applicant : appList) {
							flagUser = applicant.getMappingUserId() > 0 ? 2 : 1;
							if (flagUser == 2) break;
						}
					}
				}
				// Check exits contactEmail
				if (flagUser != 2 && Validator.isNotNull(contactEmail)) {
					List<Applicant> appList = ApplicantLocalServiceUtil.findByContactEmailList(contactEmail);
					if (appList != null && appList.size() > 0) {
						for (Applicant applicant : appList) {
							flagUser = applicant.getMappingUserId() > 0 ? 2 : 1;
							if (flagUser == 2) break;
						}
					}
				}

				// Check exits contactEmail
				if (flagUser != 2 && flagUser != 1 && Validator.isNotNull(contactEmail)) {
					User appUser = UserLocalServiceUtil.fetchUserByEmailAddress(serviceContext.getCompanyId(), contactEmail);
					if (appUser != null) {
						flagUser = 3;
						mappingUserId = appUser.getUserId();
					}
				}
			} else {
				flagUser = 4;
				if (Validator.isNotNull(applicantIdNo)) {
					Applicant app = ApplicantLocalServiceUtil.fetchByF_APLC_GID(groupId, applicantIdNo);
					if (app != null) flagUser = 2;
				}
				// Check exits contactEmail
				if (flagUser != 2 && Validator.isNotNull(contactEmail)) {
					Applicant app = ApplicantLocalServiceUtil.fetchByF_GID_CTEM(groupId, contactEmail);
					if (app != null) flagUser = 2;
				}

			}

			if (flagUser != 2) {
				DictCollection dc = DictCollectionLocalServiceUtil
						.fetchByF_dictCollectionCode(ConfigProps.get(ConfigConstants.VALUE_ADMINISTRATIVE_REGION), groupId);
				String cityName = getDictItemName(groupId, dc, cityCode);
				String districtName = getDictItemName(groupId, dc, districtCode);
				String wardName = getDictItemName(groupId, dc, wardCode);
				//
				Date appDate = null;
				if (Validator.isNotNull(applicantIdDate)) {
					SimpleDateFormat sdf = new SimpleDateFormat(APIDateTimeUtils._NORMAL_DATE);
					try {
						appDate = sdf.parse(applicantIdDate);
					} catch (ParseException e) {
					}
				}
				
				//Process update applicant and user
				if (flagUser == 0) {
					//Add applicant and user
					Applicant app = ApplicantLocalServiceUtil.importApplicationDB(groupId, userId, 0l, applicantIdNo, applicantName,
							applicantIdType, appDate, contactEmail, contactTelNo, address, cityCode, cityName,
							districtCode, districtName, wardCode, wardName, contactName, profile, serviceContext);
					//Add applicant search
					ApplicantLocalServiceUtil.updateApplicant(0l, app.getMappingUserId(), serviceContext.getCompanyId(),
							applicantName, applicantIdType, applicantIdNo, appDate, address, cityCode, cityName,
							districtCode, districtName, wardCode, wardName, applicantName, contactTelNo, contactEmail, profile);
				} else if (flagUser == 1) {
					//Add applicant and user
					ApplicantLocalServiceUtil.importApplicationDB(groupId, userId, 0l, applicantIdNo, applicantName,
							applicantIdType, appDate, contactEmail, contactTelNo, address, cityCode, cityName,
							districtCode, districtName, wardCode, wardName, contactName, profile, serviceContext);
				} else if (flagUser == 3) {
					ApplicantLocalServiceUtil.importApplicationDB(groupId, userId, 0l, mappingUserId, applicantIdNo, applicantName,
							applicantIdType, appDate, contactEmail, contactTelNo, address, cityCode, cityName,
							districtCode, districtName, wardCode, wardName, contactName, profile, serviceContext);

					//Add applicant search
					ApplicantLocalServiceUtil.updateApplicant(0l, mappingUserId, serviceContext.getCompanyId(),
							applicantName, applicantIdType, applicantIdNo, appDate, address, cityCode, cityName,
							districtCode, districtName, wardCode, wardName, applicantName, contactTelNo, contactEmail,
							 profile);
				} else if (flagUser == 4) {
					//Add applicant search
					ApplicantLocalServiceUtil.updateApplicant(0l, userId, serviceContext.getCompanyId(),
							applicantName, applicantIdType, applicantIdNo, appDate, address, cityCode, cityName,
							districtCode, districtName, wardCode, wardName, applicantName, contactTelNo, contactEmail, profile);
				}
			}
		} // Import project not using LGSP
		else {
			int flagUser = 0;
			
			
			if (Validator.isNotNull(applicantIdNo)) {
				List<Applicant> appList = ApplicantLocalServiceUtil.findByAppIds(applicantIdNo);
				if (appList != null && appList.size() > 0) {
					for (Applicant applicant : appList) {
						flagUser = applicant.getMappingUserId() > 0 ? 2 : 1;
						
						if (flagUser == 2) break;
					}
				}
			}
			// Check exits contactEmail
			if (flagUser != 2 && Validator.isNotNull(contactEmail)) {
				List<Applicant> appList = ApplicantLocalServiceUtil.findByContactEmailList(contactEmail);
				if (appList != null && appList.size() > 0) {
					for (Applicant applicant : appList) {
						flagUser = applicant.getMappingUserId() > 0 ? 2 : 1;
						
						if (flagUser == 2) break;
					}
				}
			}

			// Check exits contactEmail
			long mappingUserId = 0;
			if (flagUser != 2 && flagUser != 1 && Validator.isNotNull(contactEmail)) {
				User appUser = UserLocalServiceUtil.fetchUserByEmailAddress(serviceContext.getCompanyId(), contactEmail);
				if (appUser != null) {
					flagUser = 3;
					mappingUserId = appUser.getUserId();
				}
			}
			
			DictCollection dc = DictCollectionLocalServiceUtil
					.fetchByF_dictCollectionCode(ConfigProps.get(ConfigConstants.VALUE_ADMINISTRATIVE_REGION), groupId);
			String cityName = getDictItemName(groupId, dc, cityCode);
			String districtName = getDictItemName(groupId, dc, districtCode);
			String wardName = getDictItemName(groupId, dc, wardCode);
			//
			Date appDate = null;
			if (Validator.isNotNull(applicantIdDate)) {
				SimpleDateFormat sdf = new SimpleDateFormat(APIDateTimeUtils._NORMAL_DATE);
				try {
					appDate = sdf.parse(applicantIdDate);
				} catch (ParseException e) {
				}
			}

			if (flagUser != 2) {
				

				//Process update applicant and user
				if (flagUser == 0) {
					//Add applicant and user
					Applicant app = ApplicantLocalServiceUtil.importApplicationDB(groupId, userId, 0l, applicantIdNo, applicantName,
							applicantIdType, appDate, contactEmail, contactTelNo, address, cityCode, cityName,
							districtCode, districtName, wardCode, wardName, contactName, profile, serviceContext);
					if (app != null) {
						//Send Email active account
						Date now = new Date();
						long notificationQueueId = CounterLocalServiceUtil.increment(NotificationQueue.class.getName());

						NotificationQueue queue = NotificationQueueLocalServiceUtil.createNotificationQueue(notificationQueueId);
						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);

						queue.setCreateDate(now);
						queue.setModifiedDate(now);
						queue.setGroupId(groupId);
						queue.setCompanyId(serviceContext.getCompanyId());

						queue.setNotificationType(ConfigProps.get(ConfigConstants.APPLICANT_NOTI_TYPE_OLD_ACTIVE));
						queue.setClassName(Applicant.class.getName());
						queue.setClassPK(String.valueOf(app.getApplicantId()));
						queue.setToUsername(applicantName);
						queue.setToUserId(app.getMappingUserId());
						queue.setToEmail(contactEmail);
						queue.setToTelNo(StringPool.BLANK);
						
						JSONObject payload = JSONFactoryUtil.createJSONObject();
						try {
							// _log.info("START PAYLOAD: ");
							payload.put(ApplicantListenerMessageKeys.APPLICANT,
									JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(app)));
						} catch (JSONException parse) {
							_log.error(parse);
						}
						//_log.info("payloadTest: "+payload.toJSONString());
						queue.setPayload(payload.toJSONString());
						
						queue.setExpireDate(cal.getTime());
						
						NotificationQueueLocalServiceUtil.addNotificationQueue(queue);
					}
					//Add applicant search
					ApplicantLocalServiceUtil.updateApplicant(0l, app.getMappingUserId(), serviceContext.getCompanyId(),
							applicantName, applicantIdType, applicantIdNo, appDate, address, cityCode, cityName,
							districtCode, districtName, wardCode, wardName, applicantName, contactTelNo, contactEmail);
				} else if (flagUser == 1) {
					//Add applicant and user
					Applicant app = ApplicantLocalServiceUtil.importApplicationDB(groupId, userId, 0l, applicantIdNo, applicantName,
							applicantIdType, appDate, contactEmail, contactTelNo, address, cityCode, cityName,
							districtCode, districtName, wardCode, wardName, contactName, profile, serviceContext);
					if (app != null) {
						//Send Email active account
						Date now = new Date();
						long notificationQueueId = CounterLocalServiceUtil.increment(NotificationQueue.class.getName());

						NotificationQueue queue = NotificationQueueLocalServiceUtil.createNotificationQueue(notificationQueueId);
						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);

						queue.setCreateDate(now);
						queue.setModifiedDate(now);
						queue.setGroupId(groupId);
						queue.setCompanyId(serviceContext.getCompanyId());

						queue.setNotificationType(ConfigProps.get(ConfigConstants.APPLICANT_NOTI_TYPE_OLD_ACTIVE));
						queue.setClassName(Applicant.class.getName());
						queue.setClassPK(String.valueOf(app.getApplicantId()));
						queue.setToUsername(applicantName);
						queue.setToUserId(app.getMappingUserId());
						queue.setToEmail(contactEmail);
						queue.setToTelNo(StringPool.BLANK);
						
						JSONObject payload = JSONFactoryUtil.createJSONObject();
						try {
							// _log.info("START PAYLOAD: ");
							payload.put(ApplicantListenerMessageKeys.APPLICANT,
									JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(app)));
						} catch (JSONException parse) {
							_log.error(parse);
						}
						//_log.info("payloadTest: "+payload.toJSONString());
						queue.setPayload(payload.toJSONString());
						
						queue.setExpireDate(cal.getTime());
						
						NotificationQueueLocalServiceUtil.addNotificationQueue(queue);
					}
				} else if (flagUser == 3) {
					ApplicantLocalServiceUtil.importApplicationDB(groupId, userId, 0l, mappingUserId, applicantIdNo, applicantName,
							applicantIdType, appDate, contactEmail, contactTelNo, address, cityCode, cityName,
							districtCode, districtName, wardCode, wardName, contactName, profile, serviceContext);
					//
					//Add applicant search
					ApplicantLocalServiceUtil.updateApplicant(0l, mappingUserId, serviceContext.getCompanyId(),
							applicantName, applicantIdType, applicantIdNo, appDate, address, cityCode, cityName,
							districtCode, districtName, wardCode, wardName, applicantName, contactTelNo, contactEmail);
				}
			}else if(flagUser == 2) {
				
				// Update thong tin applicant
				
				Applicant app = null;
				
				List<Applicant> appList = ApplicantLocalServiceUtil.findByAppIds(applicantIdNo);
				if (appList != null && appList.size() > 0) {
					for (Applicant applicant : appList) {
						flagUser = applicant.getMappingUserId() > 0 ? 2 : 1;
						if (flagUser == 2) {
							app = applicant;
							break;	
						}
							
							
					}
				}
				
				 if(Validator.isNull(app)) {
					 
					 appList = ApplicantLocalServiceUtil.findByContactEmailList(contactEmail);
					if (appList != null && appList.size() > 0) {
						for (Applicant applicant : appList) {
							flagUser = applicant.getMappingUserId() > 0 ? 2 : 1;
							if (flagUser == 2){
								
								app = applicant;
								break;
								
								}
						}
					}
				 }
				 
				
				 if(Validator.isNotNull(app)) {

					
					ApplicantLocalServiceUtil.importApplicationDB(groupId, userId, app.getApplicantId(), applicantIdNo, applicantName,
							applicantIdType, appDate, contactEmail, contactTelNo, address, cityCode, cityName,
							districtCode, districtName, wardCode, wardName, contactName, profile, serviceContext);
					
				 }
				
			}
		}
	}

	private String getDictItemName(long groupId, DictCollection dc, String itemCode) {
		if (Validator.isNotNull(dc)) {
			DictItem it = DictItemLocalServiceUtil.fetchByF_dictItemCode(itemCode, dc.getPrimaryKey(), groupId);
			if(Validator.isNotNull(it)){
				return it.getItemName();
			}else{
				return StringPool.BLANK;
			}
		} else {
			return StringPool.BLANK;
		}
	}

	@Override
	public String getSimpleCaptcha(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, Integer width, Integer height) {
		String data = StringPool.BLANK;

		try {
			if (width == null || width == 0) {
				width = 180;
			}

			if (height == null || height == 0) {
				height = 40;
			}

			int size = width + height;

//			Color[] colors = new Color[] { Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN,
//					Color.LIGHT_GRAY, Color.ORANGE, Color.PINK, Color.WHITE, Color.YELLOW, Color.RED, Color.MAGENTA };
			Color[] colors = new Color[] { Color.WHITE};
			int[] noises = new int[] { 1, 2, 3 };

			int fromColorIndex = new Random().nextInt(colors.length);
			int toColorIndex = new Random().nextInt(colors.length);
			int ovalColorIndex = new Random().nextInt(colors.length);
			int noiseIndex = new Random().nextInt(noises.length);
			Captcha captcha = new Captcha.Builder(width, height).addText(new DefaultTextProducer())
					.addBackground(new GradiatedBackgroundProducer(colors[fromColorIndex], colors[toColorIndex]))
					.build();
			Graphics2D g = captcha.getImage().createGraphics();

			//g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(colors[ovalColorIndex]);

			switch (noiseIndex) {
			case 1:
				for (int i = 0; i < size; i += 5) {
					g.drawOval(i, i, size - i, size - i);
				}

				break;
			case 2:
				for (int i = 0; i < size; i += 10) {
					g.drawRect(i, i, size - i, size - i);
				}

				break;
			default:
				break;
			}

			g.dispose();

			//_log.info("---->>>> CAPTCHA: " + captcha.getAnswer());
			//System.out.println("---->>>> CAPTCHA: " + captcha.getAnswer());

			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			ImageIO.write(captcha.getImage(), "png", baos);

			data = DatatypeConverter.printBase64Binary(baos.toByteArray());

			//_log.info("---->>>> CAPTCHA Image: " + data);
			//System.out.println("---->>>> CAPTCHA Image: " + data);

			HttpSession session = request.getSession(true);

			session.invalidate();

			session.setAttribute("_SIMPLE_CAPTCHA", captcha.getAnswer());

		} catch (Exception e) {
			_log.error(e);
		}

		return data;
	}

	@Override
	public boolean validateSimpleCaptcha(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String value) {
		String captcha;// = StringPool.BLANK;
		HttpSession session = request.getSession();
	
		/*Enumeration<String> enumeration = session.getAttributeNames();
		
		List<String> values = Collections.list(enumeration);
		
		for (String tmp : values) {
			System.out.println("========================== > session.getAttributeNames() " + tmp);
		}*/

		if (Validator.isNull(value)) {
			
			session.removeAttribute("_SIMPLE_CAPTCHA");
			return false;
		}

		if (session.getAttribute("_SIMPLE_CAPTCHA") != null) {
			
			captcha = (String) session.getAttribute("_SIMPLE_CAPTCHA");
			
			if (value.equals(captcha)) {
				session.removeAttribute("_SIMPLE_CAPTCHA");
				return true;
			}
			session.removeAttribute("_SIMPLE_CAPTCHA");
			return false;
		} else {
			
			session.removeAttribute("_SIMPLE_CAPTCHA");
			return false;
		}
	}
	public Applicant verifyApplicant(long applicantId) throws PortalException {
		Applicant applicant = ApplicantLocalServiceUtil.verifyApplicant(applicantId);
		return applicant;
	}

	@Override
	public Applicant verifyApplicantWithValue(long applicantId, int verification) throws PortalException {
		Applicant applicant = ApplicantLocalServiceUtil.verifyApplicantWithValue(applicantId, verification);
		return applicant;
	}

	@Override
	public JSONObject updateAccountEmail(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String oldEmail, String newEmail) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		User updateUser = UserLocalServiceUtil.fetchUserByEmailAddress(company.getCompanyId(), newEmail);
		
		if(Validator.isNull(newEmail)) {
			result.put("message", ApplicantTerm.EMAIL_EMPTY);
			result.put("user", JSONFactoryUtil.createJSONObject());
			return result;
		}
		
		if(!Validator.isEmailAddress(newEmail)) {
			result.put("message", ApplicantTerm.EMAIL_FORMAT_INCORRECT);
			result.put("user", JSONFactoryUtil.createJSONObject());
			return result;
		}

		if (updateUser != null) {
			result.put("message", ApplicantTerm.EMAIL_EXISTED);
			result.put("user", JSONFactoryUtil.createJSONObject());
			return result;
		}

		updateUser = UserLocalServiceUtil.fetchUserByEmailAddress(company.getCompanyId(), oldEmail);

		if (updateUser == null) {
			result.put("message", ApplicantTerm.EMAIL_NOTEXIST);
			result.put("user", JSONFactoryUtil.createJSONObject());
			return result;
		}
		String screenName = newEmail.substring(0, newEmail.lastIndexOf("@"));
		updateUser.setEmailAddress(newEmail);
		updateUser.setScreenName(screenName);
		updateUser = UserLocalServiceUtil.updateUser(updateUser);
		
		Applicant applicant = updateUser.getUserId() > 0 ? ApplicantLocalServiceUtil.fetchByMappingID(updateUser.getUserId()) : null;
		
		if(applicant != null) {
			List<Applicant> applicants = ApplicantLocalServiceUtil.findByAppIds(applicant.getApplicantIdNo());
			if(applicants != null) {
				for(Applicant applicant2 : applicants) {
					if(applicant2.getGroupId() == 0) {
						applicant2.setContactEmail(newEmail);
						applicant2 = ApplicantLocalServiceUtil.updateApplicant(applicant2);
						break;
					}
				}
			}
			applicant.setContactEmail(newEmail);
			applicant = ApplicantLocalServiceUtil.updateApplicant(applicant);
		}
		
		result.put("message", ApplicantTerm.EMAIL_UPDATE_SUCCESS);
		
		JSONObject userObj = JSONFactoryUtil.createJSONObject();
		userObj.put("userId", updateUser.getUserId());
		userObj.put("userName", updateUser.getFullName());
		userObj.put("emailAddess", updateUser.getEmailAddress());
		userObj.put("applicantId", applicant != null ? applicant.getApplicantId() : 0);
		result.put("user", userObj);

		return result;
	}

}
