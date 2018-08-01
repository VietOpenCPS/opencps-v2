package org.opencps.statistic.service.persistence.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.opencps.statistic.model.OpencpsDossier;
import org.opencps.statistic.model.impl.OpencpsDossierImpl;
import org.opencps.statistic.service.persistence.OpencpsDossierFinder;
import org.opencps.statistic.service.util.StatisticServiceConstants;

import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

public class OpencpsDossierFinderImpl extends OpencpsDossierStatisticFinderBaseImpl implements OpencpsDossierFinder {

	private final static Log LOG = LogFactoryUtil.getLog(OpencpsDossierFinderImpl.class);

	private static final String SEARCH_DOSSIER = OpencpsDossierFinder.class.getName() + ".getDossier";

	private static final String START_HOUR_MINUTE_SECOND = "00:00:00";
	private static final String END_HOUR_MINUTE_SECOND = "23:59:59";
	private static final String RELEASE_DATE = "opencps_dossier.releaseDate";

	private static final String CONDITION_REGISTER_BOOK_CODE = "(opencps_dossier.registerBookCode = ?) AND";
	private static final String CONDITION_PROCESS_NO = "(opencps_dossier.processNo = ?) AND";
	private static final String CONDITION_SERVICE_CODE = "(opencps_dossier.serviceCode = ?) AND";
	private static final String CONDITION_GOVAGENCY_CODE = "(opencps_dossier.govAgencyCode = ?) AND";
	private static final String CONDITION_APPLICANT_ID_TYPE = "(opencps_dossier.applicantIdType = ?) AND";
	private static final String CONDITION_APPLICANT_ID_NO = "(opencps_dossier.applicantIdNo = ?) AND";
	private static final String CONDITION_CITY_CODE = "(opencps_dossier.cityCode = ?) AND";
	private static final String CONDITION_DISTRICT_CODE = "(opencps_dossier.districtCode = ?) AND";
	private static final String CONDITION_WARD_CODE = "(opencps_dossier.wardCode = ?) AND";
	private static final String CONDITION_CONTACT_TEL_NO = "(opencps_dossier.contactTelNo = ?) AND";
	private static final String CONDITION_CONTACT_EMAIL = "(opencps_dossier.contactEmail = ?) AND ";
	private static final String CONDITION_DELEGATE_ID_NO = "(opencps_dossier.delegateIdNo = ?) AND";
	private static final String CONDITION_DELEGATE_TEL_NO = "(opencps_dossier.delegateTelNo = ?) AND";
	private static final String CONDITION_DOSSIER_STATUS = "(opencps_dossier.dossierStatus = ?) AND";
	private static final String CONDITION_DOSSIER_SUB_STATUS = "(opencps_dossier.dossierSubStatus = ?) AND";
	private static final String CONDITION_DOSSIER_ACTION_ID = "(opencps_dossier.dossierActionId = ?) AND";
	private static final String CONDITION_VIA_POSTAL = "(opencps_dossier.viaPostal = ?) AND";
	private static final String CONDITION_ONLINE = "(opencps_dossier.online_ = ?) AND";
	private static final String CONDITION_SERVER_NO = "(opencps_dossier.serverNo = ?) AND";
	private static final String CONDITION_ORIGIN_DOSSIER_ID = "(opencps_dossier.originDossierId = ?) AND";
	private static final String CONDITION_GROUP_ID = "(opencps_dossier.groupId = ?) AND";
	private static final String CONDITION_ORIGINALITY = "(opencps_dossier.originality = ?) AND";
	
	
	private static final String CONDITION_RECEIVE_DATE = "(opencps_dossier.receiveDate >= ?) AND (opencps_dossier.status != 'denied' OR opencps_dossier.status != 'cancelled') AND";
	private static final String CONDITION_RELEASE_DATE = "(opencps_dossier.releaseDate IS NOT NULL) AND";
	private static final String CONDITION_BETIMES_COUNT  = "(opencps_dossier.dueDate IS NOT NULL) AND (opencps_dossier.extendDate IS NOT NULL) AND (opencps_dossier.extendDate > opencps_dossier.dueDate) AND";
	private static final String CONDITION_ONTIME_COUNT = "(opencps_dossier.dueDate IS NULL) OR (opencps_dossier.releaseDate <= opencps_dossier.dueDate) AND";
	private static final String CONDITION_UNDUE_TIME = "(opencps_dossier.status = 'procesing' OR opencps_dossier.status = 'interoperating') AND (opencps_dossier.dueDate IS NULL OR opencps_dossier.dueDate > ?) AND";

	@SuppressWarnings("unchecked")
	public List<OpencpsDossier> searchDossiers(long groupId, String keyword, String registerBookCode, String processNo,
			String serviceCode, String govAgencyCode, String applicantIdType, String applicantIdNo, String cityCode,
			String districtCode, String wardCode, String contactTelNo, String contactEmail, String delegateIdNo,
			String delegateTelNo, String dossierStatus, String dossierSubStatus, long dossierActionId, int viaPostal,
			boolean online, boolean onlineValue, int originality, String serverNo, long originDossierId,
			boolean isReceiveDate, boolean isReleaseDate, boolean isBetimes, boolean isOntime, boolean isUndue, boolean order, String orderBy,
			int start, int end) throws PortalException, SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), SEARCH_DOSSIER);

			//LOG.info(sql);

			boolean hasRegisterBookCode = Validator.isNotNull(registerBookCode);
			boolean hasProcessNo = Validator.isNotNull(processNo);
			boolean hasServiceCode = Validator.isNotNull(serviceCode);
			boolean hasGovAgencyCode = Validator.isNotNull(govAgencyCode);
			boolean hasApplicantIdType = Validator.isNotNull(applicantIdType);
			boolean hasApplicantIdNo = Validator.isNotNull(applicantIdNo);
			boolean hasCityCode = Validator.isNotNull(cityCode);
			boolean hasDistrictCode = Validator.isNotNull(districtCode);
			boolean hasWardCode = Validator.isNotNull(wardCode);
			boolean hasContactTelNo = Validator.isNotNull(contactTelNo);
			boolean hasContactEmail = Validator.isNotNull(contactEmail);
			boolean hasDelegateIdNo = Validator.isNotNull(delegateIdNo);
			boolean hasDelegateTelNo = Validator.isNotNull(delegateTelNo);
			boolean hasDossierStatus = Validator.isNotNull(dossierStatus);
			boolean hasDossierSubStatus = Validator.isNotNull(dossierSubStatus);
			boolean hasDossierActionId = dossierActionId != 0;
			boolean hasViaPostal = viaPostal != 0;
			boolean hasOnline = online;
			boolean hasOriginality = originality != 0;
			boolean hasServerNo = Validator.isNotNull(serverNo);
			boolean hasOriginDossierId = originDossierId != 0;
			boolean hasGroupId = groupId != 0;
			
			if (!isReceiveDate) {
				sql = StringUtil.replace(sql, CONDITION_RECEIVE_DATE, StringPool.BLANK);
			}
			
			if (!isReleaseDate) 
				sql = StringUtil.replace(sql, CONDITION_RELEASE_DATE, StringPool.BLANK);
			
			if (!isBetimes) 
				sql = StringUtil.replace(sql, CONDITION_BETIMES_COUNT, StringPool.BLANK);
			
			if (!isOntime)
				sql = StringUtil.replace(sql, CONDITION_ONTIME_COUNT, StringPool.BLANK);
			
			if (!isUndue) 
				sql = StringUtil.replace(sql, CONDITION_UNDUE_TIME, StringPool.BLANK);
			
			if (!hasRegisterBookCode) {
				sql = StringUtil.replace(sql, CONDITION_REGISTER_BOOK_CODE, StringPool.BLANK);
			}

			if (!hasProcessNo) {
				sql = StringUtil.replace(sql, CONDITION_PROCESS_NO, StringPool.BLANK);
			}

			if (!hasServiceCode) {
				sql = StringUtil.replace(sql, CONDITION_SERVICE_CODE, StringPool.BLANK);
			}

			if (!hasGovAgencyCode) {
				sql = StringUtil.replace(sql, CONDITION_GOVAGENCY_CODE, StringPool.BLANK);
			}

			if (!hasApplicantIdType) {
				sql = StringUtil.replace(sql, CONDITION_APPLICANT_ID_TYPE, StringPool.BLANK);
			}

			if (!hasApplicantIdNo) {
				sql = StringUtil.replace(sql, CONDITION_APPLICANT_ID_NO, StringPool.BLANK);
			}

			if (!hasCityCode) {
				sql = StringUtil.replace(sql, CONDITION_CITY_CODE, StringPool.BLANK);
			}

			if (!hasDistrictCode) {
				sql = StringUtil.replace(sql, CONDITION_DISTRICT_CODE, StringPool.BLANK);
			}

			if (!hasWardCode) {
				sql = StringUtil.replace(sql, CONDITION_WARD_CODE, StringPool.BLANK);
			}

			if (!hasContactTelNo) {
				sql = StringUtil.replace(sql, CONDITION_CONTACT_TEL_NO, StringPool.BLANK);
			}

			if (!hasContactEmail) {
				sql = StringUtil.replace(sql, CONDITION_CONTACT_EMAIL, StringPool.BLANK);
			}

			if (!hasDelegateIdNo) {
				sql = StringUtil.replace(sql, CONDITION_DELEGATE_ID_NO, StringPool.BLANK);
			}

			if (!hasDelegateTelNo) {
				sql = StringUtil.replace(sql, CONDITION_DELEGATE_TEL_NO, StringPool.BLANK);
			}

			if (!hasDossierStatus) {
				sql = StringUtil.replace(sql, CONDITION_DOSSIER_STATUS, StringPool.BLANK);
			}

			if (!hasDossierSubStatus) {
				sql = StringUtil.replace(sql, CONDITION_DOSSIER_SUB_STATUS, StringPool.BLANK);
			}

			if (!hasDossierActionId) {
				sql = StringUtil.replace(sql, CONDITION_DOSSIER_ACTION_ID, StringPool.BLANK);
			}

			if (!hasViaPostal) {
				sql = StringUtil.replace(sql, CONDITION_VIA_POSTAL, StringPool.BLANK);
			}

			if (!hasOnline) {
				sql = StringUtil.replace(sql, CONDITION_ONLINE, StringPool.BLANK);
			}

			if (!hasOriginality) {
				sql = StringUtil.replace(sql, CONDITION_ORIGINALITY, StringPool.BLANK);
			}

			if (!hasServerNo) {
				sql = StringUtil.replace(sql, CONDITION_SERVER_NO, StringPool.BLANK);
			}

			if (!hasOriginDossierId) {
				sql = StringUtil.replace(sql, CONDITION_ORIGIN_DOSSIER_ID, StringPool.BLANK);
			}

			if (!hasGroupId) {
				sql = StringUtil.replace(sql, CONDITION_GROUP_ID, StringPool.BLANK);
			}

			sql = StringUtil.replace(sql, StatisticServiceConstants.SPECIFY_RELEASE_DATE, buildReleaseDataCondition());

			//LOG.info(sql);
			//LOG.info("GROUP_ID_" + groupId);

			SQLQuery q = session.createSQLQuery(sql);

			q.setCacheable(false);
			q.addEntity("OpencpsDossier", OpencpsDossierImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			if (hasRegisterBookCode) {
				qPos.add(registerBookCode);
			}

			if (hasProcessNo) {
				qPos.add(processNo);
			}

			if (hasServiceCode) {
				qPos.add(serviceCode);
			}

			if (hasGovAgencyCode) {
				qPos.add(govAgencyCode);
			}

			if (hasApplicantIdType) {
				qPos.add(applicantIdType);
			}

			if (hasApplicantIdNo) {
				qPos.add(applicantIdNo);
			}

			if (hasCityCode) {
				qPos.add(cityCode);
			}

			if (hasDistrictCode) {
				qPos.add(districtCode);
			}

			if (hasWardCode) {
				qPos.add(wardCode);
			}

			if (hasContactTelNo) {
				qPos.add(contactTelNo);
			}

			if (hasContactEmail) {
				qPos.add(contactEmail);
			}

			if (hasDelegateIdNo) {
				qPos.add(delegateIdNo);
			}

			if (hasDelegateTelNo) {
				qPos.add(delegateTelNo);
			}

			if (hasDossierStatus) {
				qPos.add(dossierStatus);
			}

			if (hasDossierSubStatus) {
				qPos.add(dossierSubStatus);
			}

			if (hasDossierActionId) {
				qPos.add(dossierActionId);
			}

			if (hasViaPostal) {
				qPos.add(viaPostal);
			}

			if (hasOnline) {
				qPos.add(onlineValue);
			}

			if (hasOriginality) {
				qPos.add(originality);
			}

			if (hasServerNo) {
				qPos.add(serverNo);
			}

			if (hasOriginDossierId) {
				qPos.add(originDossierId);
			}

			if (hasGroupId) {
				qPos.add(groupId);
			}
			
			if (isReceiveDate) {
				qPos.add(buildFirstTimeOfMonth());
			}
			
			if (isUndue) {
				qPos.add(buildCurrentDateTime());
			}
			
			return (List<OpencpsDossier>) QueryUtil.list(q, getDialect(), start, end);

		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		} finally {
			closeSession(session);
		}

		return null;

	}
	
	private static String buildCurrentDateTime() {
		return LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		
	}
	
	/**
	 * @return
	 */
	private static String buildFirstTimeOfMonth() {
		LocalDate todayDate = LocalDate.now();
		
		LocalDate firstDayOfCurMonth = todayDate.with(TemporalAdjusters.firstDayOfMonth());
		
		StringBuilder builder = new StringBuilder();
		
		builder.append(StringPool.APOSTROPHE);
		builder.append(firstDayOfCurMonth.toString());
		builder.append(StringPool.SPACE);
		builder.append(START_HOUR_MINUTE_SECOND);
		builder.append(StringPool.APOSTROPHE);
		
		return builder.toString();
	}

	/**
	 * @return
	 */
	private static String buildReleaseDataCondition() {

		LocalDate todayDate = LocalDate.now();

		LocalDate firstDayOfCurMonth = todayDate.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate lastDayOfCurMonth = todayDate.with(TemporalAdjusters.lastDayOfMonth());

		StringBuilder builder = new StringBuilder();

		builder.append(StringPool.OPEN_PARENTHESIS);
		builder.append(RELEASE_DATE);
		builder.append(StringPool.SPACE);
		builder.append(StringPool.GREATER_THAN_OR_EQUAL);
		builder.append(StringPool.APOSTROPHE);
		builder.append(firstDayOfCurMonth.toString());
		builder.append(StringPool.SPACE);
		builder.append(START_HOUR_MINUTE_SECOND);
		builder.append(StringPool.APOSTROPHE);
		builder.append(StringPool.SPACE);
		builder.append(StringPool.AMPERSAND);
		builder.append(StringPool.AMPERSAND);
		builder.append(StringPool.SPACE);
		builder.append(RELEASE_DATE);
		builder.append(StringPool.SPACE);
		builder.append(StringPool.LESS_THAN_OR_EQUAL);
		builder.append(StringPool.SPACE);
		builder.append(StringPool.APOSTROPHE);
		builder.append(lastDayOfCurMonth.toString());
		builder.append(StringPool.SPACE);
		builder.append(END_HOUR_MINUTE_SECOND);
		builder.append(StringPool.APOSTROPHE);
		builder.append(StringPool.CLOSE_PARENTHESIS);

		return builder.toString();
	}
}
